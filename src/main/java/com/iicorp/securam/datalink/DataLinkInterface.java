package com.iicorp.securam.datalink;

import com.fazecast.jSerialComm.SerialPort;
import com.iicorp.securam.datalink.auth.AuthenticationCredentials;
import com.iicorp.securam.lock.api.LockDataIndication;
import com.iicorp.securam.lock.messages.*;
import com.iicorp.securam.common.HexFormatter;
import com.iicorp.securam.datalink.auth.DigestLinkAuthenticator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataLinkInterface implements DataLinkListener
{
    private static final Logger logger = Logger.getLogger("DataLinkInterface");

    private static final String VERSION = "1.02";

    private DataLinkDriver linkDriver;

    private Consumer<LockDataIndication> messageListener;

    private AtomicBoolean running = new AtomicBoolean(false);

    public DataLinkInterface(SerialPort port, AuthenticationCredentials secrets, Consumer<LockDataIndication> messageListener) {
        logger.log(Level.ALL, "Starting DataLinkInterface version " + VERSION);
        DigestLinkAuthenticator linkAuthenticator = new DigestLinkAuthenticator(secrets);
        linkDriver = new DataLinkDriver(port, linkAuthenticator, this);
        if (messageListener != null) {
            this.messageListener = messageListener;
        }
        else
        {
            logger.log(Level.SEVERE, "Message listener must not be null");
            throw new IllegalArgumentException("Error: message listener cannot be null");
        }
    }

    private SerialPort getLockPort(String portName)
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports)
        {
            if (port.getSystemPortName().toUpperCase().equals(portName))
            {
                logger.log(Level.INFO, "Using serial port: " + portName);
                return port;
            }
        }
        throw new IllegalArgumentException("USB Port " + portName + " not found");
    }

    public void start()
    {
        running.set(true);
        linkDriver.start();
    }

    public void stop()
    {
        running.set(false);
        linkDriver.stop();
    }

    public void enableDebugLogging(boolean enable)
    {
        linkDriver.enableDebugLogging(enable);
    }

    public void openLock(int lockNumber, int duration)
    {
        logger.log(Level.INFO, "Sending command to open lock " + lockNumber + " for " + duration + " seconds");
        OpenLockCommand command = new OpenLockCommand((byte) lockNumber, duration);
        linkDriver.sendLinkData(command.marshall());
    }

    public void getLockStatus()
    {
        LockStatusRequest request = new LockStatusRequest();
        linkDriver.sendLinkData(request.marshall());
    }

    @Override
    public void linkConnected()
    {
        logger.log(Level.INFO, "Link is connected");
        LockDataIndication dataIndication = new LockDataIndication(LockDataIndication.IndicationType.CONTROLLER_CONNECTED, this);
        messageListener.accept(dataIndication);
    }

    @Override
    public void linkDisconnected()
    {
        logger.log(Level.INFO, "Link is disconnected, restarting");
        LockDataIndication dataIndication = new LockDataIndication(LockDataIndication.IndicationType.CONTROLLER_DISCONNECTED, this);
        messageListener.accept(dataIndication);
//        if (running.get())
//        {
//            start();
 //       }
    }

    @Override
    public void linkAuthenticationFailed() {
        logger.log(Level.SEVERE, "Authentication failed");
        LockDataIndication dataIndication = new LockDataIndication(LockDataIndication.IndicationType.AUTHENTICATION_FAILED, this);
        messageListener.accept(dataIndication);
    }

    @Override
    public void dataIndication(byte[] message) {
        logger.log(Level.INFO, "Received data: " + HexFormatter.bytesToHex(message));
        MessageType type = MessageType.ofCode(message[0]);
        LockControlMessage receivedMessage = null;
        logger.info("Data Indication - received " + type.name());
        switch (type)
        {
            case LOCK_STATUS: {
                LockStatusMessage lockStatus = new LockStatusMessage();
                lockStatus.unMarshall(message);
                LockDataIndication dataIndication = new LockDataIndication(LockDataIndication.IndicationType.LOCK_DATA, this, lockStatus);
                messageListener.accept(dataIndication);
            }
                break;
            case TIME_REQUEST: {
                TimeRequest timeRequest = new TimeRequest();
                timeRequest.unMarshall(message);
                TimeResponse timeResponse = new TimeResponse(timeRequest);
                timeResponse.marshall();
                linkDriver.sendLinkData(timeResponse.marshall());
            }
                break;
            case OPEN_LOCK_RESPONSE: {
                OpenLockResponse openLockResponse = new OpenLockResponse();
                openLockResponse.unMarshall(message);
                LockDataIndication dataIndication = new LockDataIndication(LockDataIndication.IndicationType.LOCK_DATA, this, openLockResponse);
                messageListener.accept(dataIndication);
            }
                break;
        }
    }
}
