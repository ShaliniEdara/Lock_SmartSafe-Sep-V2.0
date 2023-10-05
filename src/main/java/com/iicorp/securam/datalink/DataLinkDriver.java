package com.iicorp.securam.datalink;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListenerWithExceptions;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.iicorp.securam.common.HexFormatter;
import com.iicorp.securam.datalink.auth.AuthenticatorDataLink;
import com.iicorp.securam.datalink.auth.DataLinkAuthenticator;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataLinkDriver implements Runnable, AuthenticatorDataLink, SerialPortDataListenerWithExceptions
{
    private static final Logger logger = Logger.getLogger("DataLinkDriver");

    private static final int ACK_TIMEOUT_MILLIS = 2000;

    private static final int MAX_RETRANSMITS = 3;

    private final SerialPort serialPort;

    private final BlockingQueue<DataLinkFrame> sendQueue = new LinkedBlockingQueue<>();

    private DataLinkFrame lastOutputFrame;

    private DataLinkInputFrame currentInputFrame;

    private long ackTimeoutAt;

    private AtomicInteger retries = new AtomicInteger(0);

    private AtomicInteger sendSequence = new AtomicInteger(0);

    private int receiveSequence;

    private LinkState linkState;

    private final DataLinkAuthenticator authenticator;

    private final DataLinkListener dataLinkListener;

    private Thread linkDriverThread;

    private boolean linkActive;

    private boolean enableDebugLogging = true;

    public DataLinkDriver(SerialPort serialPort,
                          DataLinkAuthenticator authenticator,
                          DataLinkListener dataLinkListener)
    {
        this.serialPort = serialPort;
        if (!serialPort.openPort())
        {
            throw new IllegalStateException("Unable to open the USB serial port");
        }
        serialPort.flushIOBuffers();
        serialPort.addDataListener(this);
        linkState = LinkState.WS_DISCONNECTED;
        this.authenticator = authenticator;
        this.authenticator.setDataLink(this);
        this.dataLinkListener = dataLinkListener;
    }

    public void start()
    {
        if (linkDriverThread != null)
        {
            logger.warning("Link driver start called while driver is running, ignored");
            return;
        }
        linkDriverThread = new Thread(this, "LinkDriverThread");
        linkDriverThread.start();
    }

    public void stop()
    {
        if (linkDriverThread == null)
        {
            logger.warning("Stop called on inactive link driver - ignored");
            return;
        }
        linkActive = false;
        linkDriverThread.interrupt();
        try { linkDriverThread.join(10000); } catch (InterruptedException e) {}
        linkDriverThread = null;
    }

    public void enableDebugLogging(boolean enable)
    {
        this.enableDebugLogging = enable;
    }

    public void sendLinkData(byte[] data)
    {
        DataLinkOutputFrames.NetworkData frame = new DataLinkOutputFrames.NetworkData(data, nextSendSequence(), FrameType.LINK_DATA);
        queueFrame(frame);
    }

    protected void queueFrame(DataLinkFrame frame)
    {
        try {
            sendQueue.put(frame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sendAuthData(byte[] data) {

        switch (linkState)
        {
            case WS_DISCONNECTED:
            case WS_CONNECTING:
            case WS_L1_DOWN:
                logger.log(Level.WARNING, "Attempt to send authentication data on a disconnected link");
                return -1;
            case WS_AUTHENTICATING:
                break;
            default:
                logger.log(Level.INFO, "Sending authentication data while connected");
                break;
        }
        logger.log(Level.INFO, "Sending Authentication Data");
        DataLinkOutputFrames.NetworkData frame = new DataLinkOutputFrames.NetworkData(data, nextSendSequence(), FrameType.AUTHENTICATION_DATA);
        queueFrame(frame);
        return 0;
    }

    protected void sendKeepAlive()
    {
        DataLinkOutputFrames.NetworkData frame = new  DataLinkOutputFrames.NetworkData("KeepAlive".getBytes(StandardCharsets.UTF_8), nextSendSequence(), FrameType.KEEPALIVE);
        queueFrame(frame);
    }

    protected byte nextSendSequence()
    {
        return (byte) (sendSequence.incrementAndGet() & 0x000000ff);
    }

    private void checkSendQueue()
    {
        if (lastOutputFrame != null)
        {
            if (System.currentTimeMillis() > ackTimeoutAt)
            {
                if (retries.decrementAndGet() > 0)
                {
                    writeFrame(lastOutputFrame);
                    ackTimeoutAt = System.currentTimeMillis() + ACK_TIMEOUT_MILLIS;
                }
                else
                {
                    logger.warning("Transmit retries exhausted, shutting down link");
                    linkActive = false;
                }
            }
        }
        else
        {
            try {
                lastOutputFrame = sendQueue.poll(50, TimeUnit.MILLISECONDS);
                if (lastOutputFrame != null) {
                    writeFrame(lastOutputFrame);
                    ackTimeoutAt = System.currentTimeMillis() + ACK_TIMEOUT_MILLIS;
                    retries.set(MAX_RETRANSMITS);
                }

            } catch (InterruptedException e) {
                logger.warning("Send queue POLL interrupted, exiting");
            }
        }
    }

    protected void processLinkReset()
    {
        logger.log(Level.INFO, "Processing link reset");
        sendSequence.set(0);
        receiveSequence = 0;
        sendQueue.clear();
        lastOutputFrame = null;

        switch (linkState)
        {
            case WS_CONNECTING -> {
                // TODO: cancel any connect timer
                logger.log(Level.INFO, "Initiating authentication");
                linkState = LinkState.WS_AUTHENTICATING;
            }
            case WS_CONNECTED -> {
                logger.log(Level.INFO, "Received link reset while connected - attempt re-connect");
                linkState = LinkState.WS_DISCONNECTED;
            }
        }

        DataLinkOutputFrames.ResetAck ack = new DataLinkOutputFrames.ResetAck();
        writeFrame(ack);
    }

    protected void processResetAck()
    {
        logger.log(Level.INFO, "Processing reset ack");
    }

    protected void processFrameAck(DataLinkFrame ack)
    {
        logger.log(Level.INFO, "processing frame ack - sequence: " + ack.getSequence());
        if (lastOutputFrame != null)
        {
            if (lastOutputFrame.getSequence() == ack.getSequence())
            {
                if (lastOutputFrame.getType() == FrameType.AUTHENTICATION_DATA)
                {
                    if (authenticator != null)
                    {
                        authenticator.authFrameAcked(lastOutputFrame);
                    }
                }
                lastOutputFrame = null;
            }
        }
    }

    protected void processLinkDisconnect()
    {
        logger.log(Level.INFO, "Received link disconnect");
    }

    protected void sendLinkReset()
    {
        DataLinkOutputFrames.LinkReset reset = new DataLinkOutputFrames.LinkReset();
        writeFrame(reset);
    }

    protected void sendAck(int sequence)
    {
        byte ackSequence = (byte) (sequence & 0x000000ff);
        DataLinkOutputFrames.FrameAck ack = new DataLinkOutputFrames.FrameAck(ackSequence);
        writeFrame(ack);
    }


    protected void processAuthenticationData(DataLinkFrame frame)
    {
        logger.log(Level.INFO, "Processing authentication data");
        frame.unMarshal();
        byte[] message = frame.getMessage();
        authenticator.authDataReceived(message);
    }

    protected void processKeepAlive()
    {
        logger.log(Level.INFO, "Processing keep alive");
        DataLinkOutputFrames.NetworkData response = new DataLinkOutputFrames.NetworkData("KeepAliveAck".getBytes(StandardCharsets.UTF_8), nextSendSequence(), FrameType.KEEPALIVE_ACK);
        queueFrame(response);
    }

    protected void processKeepAliveAck(DataLinkFrame frame)
    {
        logger.log(Level.INFO, "Process keep alive ack");
    }

    protected void processLinkData(DataLinkFrame frame)
    {
        logger.log(Level.INFO, "Processing network data");
        frame.unMarshal();
        dataLinkListener.dataIndication(frame.getMessage());
    }

    private void processInputFrame(DataLinkFrame frame)
    {
        logger.log(Level.INFO, "Received: " + frame.getType().name());
        switch (frame.getType())
        {
            case LINK_RESET:
                processLinkReset();
                return;
            case RESET_ACK:
                processResetAck();
                return;
            case FRAME_ACK:
                processFrameAck(frame);
                return;
            case FRAME_REJECT:
                logger.log(Level.INFO, "Processing frame reject ");
                return;
            case LINK_DISCONNECT:
                processLinkDisconnect();
                return;
            default:
                break;
        }

        // Check sequence numbers on other frame types and ignore duplicates

        int sequence = frame.getSequence() & 0x000000ff;
        if (sequence == receiveSequence)
        {
            logger.log(Level.WARNING, "Duplicate sequence {0} received, ignored", sequence);
            sendAck(sequence);
            return;
        }
        int expected = (int) ((receiveSequence + 1) & 0x000000ff);
        if (sequence != expected)
        {
            logger.log(Level.WARNING, "DataLinkManager: received sequence error, expected {0}, received {1} continuing...", new Integer[]{Integer.valueOf(expected), Integer.valueOf(sequence)});
        }
        receiveSequence = (byte) (sequence & 0x000000ff);
        sendAck(sequence);

        // Process by message type

        switch (frame.getType())
        {
            case AUTHENTICATION_DATA:
                processAuthenticationData(frame);
                break;
            case LINK_DATA:
                processLinkData(frame);
                break;
            case KEEPALIVE:
                processKeepAlive();
                break;
            case KEEPALIVE_ACK:
                processKeepAliveAck(frame);
                break;
        }
    }

    private void writeFrame(DataLinkFrame frame)
    {
        try {
            serialPort.writeBytes(frame.getBuffer(), frame.getSize());
            if (enableDebugLogging) {
                System.out.println("Sent: " + HexFormatter.bytesToHex(frame.getBuffer()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE | SerialPort.LISTENING_EVENT_DATA_RECEIVED | SerialPort.LISTENING_EVENT_PORT_DISCONNECTED;
    }

    @Override
    public void catchException(Exception e) {
        logger.log(Level.WARNING, "Got exception on serial port: " + e.getClass().getName(), e);
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        switch (serialPortEvent.getEventType())
        {
            case SerialPort.LISTENING_EVENT_DATA_RECEIVED:
                byte[] data = serialPortEvent.getReceivedData();
                if (enableDebugLogging)
                {
                    System.out.println("Received: " + HexFormatter.bytesToHex(data));
                }
                for (byte input : data)
                {
                    if (currentInputFrame == null)
                    {
                        currentInputFrame = new DataLinkInputFrame(256);
                    }
                    boolean complete = currentInputFrame.processInput(input);
                    if (complete) {
                        processInputFrame(currentInputFrame);
                        currentInputFrame = null;
                    }
                }
                break;
            case SerialPort.LISTENING_EVENT_PORT_DISCONNECTED:
                logger.log(Level.WARNING, "Lock data link port disconnected");
                stop();
                break;
        }
    }

    public void run()
    {
        logger.log(Level.INFO, "Lock data link started");
        if (serialPort.isOpen()) {
            serialPort.flushIOBuffers();
            serialPort.addDataListener(this);
            linkActive = true;
            logger.log(Level.INFO, "Opened lock interface port");
            logger.log(Level.INFO, "Sending link reset");
            linkState = LinkState.WS_DISCONNECTED;
        }
        else {
            logger.log(Level.SEVERE, "LinkDriver - Unable to open the lock interface port, exiting");
        }

        long lastKeepAlive = System.currentTimeMillis();
        while (linkActive) {
            try {
                Thread.sleep(10);
                if (linkState == LinkState.WS_DISCONNECTED)
                {
                    linkState = LinkState.WS_CONNECTING;
                    sendLinkReset();
                }
                if ((System.currentTimeMillis() - lastKeepAlive) > 15000)
                {
                    if (linkState == LinkState.WS_CONNECTED)
                    {
                        sendKeepAlive();
                    }
                    lastKeepAlive = System.currentTimeMillis();
                }
                checkSendQueue();
            } catch (InterruptedException e) {
                logger.info("DataLinkDriver interrupted");
            }
        }
        logger.log(Level.INFO, "Lock interface driver thread exiting");
        serialPort.removeDataListener();
        serialPort.closePort();
        if (dataLinkListener != null)
        {
            dataLinkListener.linkDisconnected();
        }
    }

    @Override
    public void authenticationComplete(boolean success)
    {
        if (success)
        {
            linkState = LinkState.WS_CONNECTED;
            dataLinkListener.linkConnected();
        }
        else
        {
            dataLinkListener.linkAuthenticationFailed();
        }
    }

}
