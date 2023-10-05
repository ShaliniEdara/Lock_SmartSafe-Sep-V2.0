package com.iicorp.securam.lock.api;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;
import com.iicorp.securam.datalink.DataLinkInterface;
import com.iicorp.securam.datalink.LockPortLocator;
import com.iicorp.securam.datalink.auth.AuthenticationCredentials;
import com.iicorp.securam.lock.messages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class LockController
{
    private static final Logger logger = Logger.getLogger("LockController");

    private static LockController instance;

    private LockControlListener lockControlListener;

    private LockIndicationDispatcher dispatcher;

    private final AuthenticationCredentials authenticationCredentials;

    private final String serialPortName;

    private boolean debugLoggingEnabled;

    public static List<String> getSerialPorts()
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        List<String> serialPorts = new ArrayList<>();
        for (SerialPort port : ports) {
            serialPorts.add(port.getSystemPortName() + " - " + port.getDescriptivePortName() + " - " + port.getPortDescription());
        }
        return serialPorts;
    }

    public static Optional<String> getLockPortName()
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        List<String> portnames = new ArrayList<>();
        for (SerialPort port : ports)
        {
          //  if (port.getPortDescription().contains("Dual Serial"))
            {
                portnames.add(port.getSystemPortName());
            }
        }
        return portnames.stream().findFirst();
    }

    public static Optional<SerialPort> getLockPort()
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        List<String> portnames = new ArrayList<>();
        Optional<SerialPort> serialPort = Optional.empty();
        for (SerialPort port : ports)
        {
            if (port.getPortDescription().contains("Dual Serial"))
            {
                port.openPort();
                portnames.add(port.getSystemPortName());
            }
        }
        return serialPort;
    }

    public static LockController instance(String serialPortName, AuthenticationCredentials secrets)
    {
        if (instance == null)
        {
            instance = new LockController(serialPortName, secrets);
        }
        return instance;
    }

    public static LockController instance(AuthenticationCredentials secrets)
    {
        String lockPortName = getLockPortName().orElseThrow(() -> new IllegalStateException("No lock port detected"));
        return instance(lockPortName, secrets);
    }

    private LockController(String serialPortName, AuthenticationCredentials secrets)
    {
        this.serialPortName = serialPortName;
        this.authenticationCredentials = secrets;
    }

    protected void setListener(LockControlListener lockControlListener)
    {
        this.lockControlListener = lockControlListener;
    }

    public void start(LockControlListener listener)
    {
        logger.log(Level.ALL, "Securam Lock Controller Library version 1.5");
        if (!debugLoggingEnabled) {
            LogManager.getLogManager().reset();
        }

        if (listener == null && this.lockControlListener == null)
        {
            throw new IllegalArgumentException("Lock Controller Listener must be specified");
        }
        if (this.lockControlListener == null) {
            setListener(listener);
        }
        this.dispatcher = new LockIndicationDispatcher(this.lockControlListener, this.authenticationCredentials, debugLoggingEnabled);
        this.dispatcher.start();
        logger.log(Level.INFO, "Lock Controller started");
    }

    public void stop()
    {
        this.dispatcher.stop();
        logger.log(Level.INFO, "Lock Controller stopped");
    }

    public void enableLogging(boolean enabled)
    {
        debugLoggingEnabled = enabled;
    }

    public void requestLockStatus()
    {
        this.dispatcher.getLinkInterface().getLockStatus();
    }

    public void requestOpenLock(int lockNumber, int forSeconds)
    {
        this.dispatcher.getLinkInterface().openLock((byte) lockNumber, forSeconds);
    }

    private static class LockIndicationDispatcher implements Runnable, Consumer<LockDataIndication>
    {
        private final Logger logger = Logger.getLogger("LockIndicationDispatcher");

        private final BlockingQueue<LockDataIndication> notificationQueue = new LinkedBlockingQueue<>();

        private Optional<SerialPort> serialPort = Optional.empty();

        private DataLinkInterface linkInterface;

        private final AuthenticationCredentials authenticationCredentials;

        private final LockControlListener listener;

        private final boolean enableLogging;

        private Thread dispatcherThread;

        private AtomicBoolean running = new AtomicBoolean(false);

        private byte[] lockStatus = new byte[]{0,0,0,0};

        public LockIndicationDispatcher(final LockControlListener listener, AuthenticationCredentials authenticationCredentials, boolean enbaleLogging)
        {
            this.listener = listener;
            this.authenticationCredentials = authenticationCredentials;
            this.enableLogging = enbaleLogging;
        }

        public void start()
        {
            if (running.compareAndSet(false, true)) {
                if (dispatcherThread != null) {
                    logger.log(Level.WARNING, "Attempt to start LockIndicationDispatcher while it is still running - waiting for it to exit");
                    try {dispatcherThread.join(10000); } catch (Exception e) {}
                    return;
                }
                dispatcherThread = new Thread(this, "LockIndicationDispatcher");
                dispatcherThread.start();
            }
        }

        protected void startLinkInterface(SerialPort port)
        {
            this.linkInterface = new DataLinkInterface(port, authenticationCredentials, this);
            if (!enableLogging) {
                LogManager.getLogManager().reset();
            }
            linkInterface.enableDebugLogging(enableLogging);
            this.linkInterface.start();
            logger.log(Level.INFO, "Data Link Interface Started");
        }

        public void stop()
        {
            if (running.compareAndSet(true, false))
            {
                if (dispatcherThread != null)
                {
                    if (linkInterface != null) { linkInterface.stop(); }
                    dispatcherThread.interrupt();
                    try { dispatcherThread.join(10000); } catch (Exception e) {}
                }
            }
        }

        public DataLinkInterface getLinkInterface()
        {
            return linkInterface;
        }

        @Override
        public void run() {

            while (running.get()) {
                if (serialPort.isEmpty()) {
                    LockPortLocator.locateLockPort().ifPresentOrElse(port -> {
                        try {
                            serialPort = Optional.of(port);
                            startLinkInterface(port);
                        } catch (SerialPortInvalidPortException e) {
                            logger.log(Level.SEVERE, e.getMessage());
                        }
                    }, new Runnable() {
                        @Override
                        public void run() {
                            try { Thread.sleep(1000); } catch (InterruptedException e) {}
                        }
                    });
                }
                else
                {
                    try {
                        LockDataIndication dataIndication = notificationQueue.take();
                        if (listener != null) {
                            switch (dataIndication.getType()) {
                                case CONTROLLER_CONNECTED:
                                    listener.controllerConnected();
                                    break;
                                case CONTROLLER_DISCONNECTED:
                                    serialPort = Optional.empty();
                                    linkInterface = null;
                                    listener.controllerDisconnected();
                                    break;
                                case AUTHENTICATION_FAILED:
                                    listener.controllerAuthFailed();
                                    break;
                                case LOCK_DATA:
                                    LockControlMessage message = dataIndication.getMessage();
                                    switch (message.getMessageType()) {
                                        case LOCK_STATUS: {
                                            LockStatusMessage lockStatusMessage = (LockStatusMessage) message;
                                            byte[] previousStatus = lockStatus;
                                            lockStatus = lockStatusMessage.getLockStatus();
                                            for (byte lock = 0; lock < lockStatusMessage.getNumberOfLocks(); lock++) {
                                                if (lockStatus[lock] != previousStatus[lock]) {
                                                    LockState previousState = LockState.values()[previousStatus[lock]];
                                                    LockState state = LockState.values()[lockStatus[lock]];
                                                    logger.log(Level.INFO, "Lock {0} state changed - new state: {1}", new Object[]{lock, state.name()});
                                                    listener.lockStatusChange(lock, LockStatus.fromLockState(previousState), LockStatus.fromLockState(state));
                                                    if (state == LockState.LS_OPEN) {
                                                        listener.lockOpen(lock);
                                                    } else if (state == LockState.LS_CLOSED) {
                                                        listener.lockClosed(lock);
                                                    }
                                                }
                                            }
                                            // Check alarms
                                            List<AlarmCode> codes = lockStatusMessage.getAlarmCodes();
                                            if (!codes.isEmpty())
                                            {
                                                listener.lockAlarms(codes);
                                            }
                                        }
                                        break;
                                        case OPEN_LOCK_RESPONSE: {
                                            OpenLockResponse response = (OpenLockResponse) message;
                                            int lockStatus = response.getLockStatus();
                                            LockStatus state = LockStatus.fromByteCode(response.getStatus());
                                            String logMessage = String.format("Got open lock command response - status = %d, lock state = %s", lockStatus, state.description());
                                            logger.log(Level.FINE, logMessage);
                                            listener.openLockCommandResponse(response.getLockNumber(), state, (byte) lockStatus);
                                        }
                                        break;
                                        default:
                                            break;
                                    }
                            }
                        }
                    } catch (InterruptedException e) {}
                }
            }
        }

        @Override
        public void accept(LockDataIndication lockDataIndication) {
            notificationQueue.add(lockDataIndication);
        }
    }
}
