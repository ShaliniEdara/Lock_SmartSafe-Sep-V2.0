package com.iicorp.securam.demo.config;

import com.iicorp.securam.demo.config.DefaultSecrets;
import com.iicorp.securam.lock.api.LockControlListener;
import com.iicorp.securam.lock.api.LockController;
import com.iicorp.securam.lock.api.LockStatus;
import com.iicorp.securam.lock.messages.AlarmCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DemoControllerInterface implements LockControlListener
{
    private static final Logger logger = LoggerFactory.getLogger(DemoControllerInterface.class);

    private LockController lockController;

    private final DefaultSecrets secrets;

    private boolean connected = false;

    @Autowired
    public DemoControllerInterface(DefaultSecrets secrets)
    {
        this.secrets = secrets;
    }

    @Override
    public void controllerConnected()
    {
        System.out.println("Controller connected\n");
        connected = true;
    }

    @Override
    public void controllerDisconnected()
    {
        System.out.println("Controller disconnected\n");
        connected = false;
    }

    @Override
    public void lockStatusChange(byte lockNumber, LockStatus previousStatus, LockStatus currentStatus)
    {
        System.out.printf("Lock status changed - lock %d status changed from %s to %s\n", lockNumber, previousStatus.description(), currentStatus.description());
    }

    @Override
    public void lockOpen(byte lockNumber) {
        System.out.printf("lock %d is open\n", lockNumber);
    }

    @Override
    public void lockClosed(byte lockNumber) {
        System.out.printf("Lock %d is closed\n", lockNumber);
    }

    @Override
    public void openLockCommandResponse(byte lockNumber, LockStatus state, byte status) {
        LockStatus commandStatus = LockStatus.fromByteCode(status);
        System.out.printf("Open lock #%d command completed, state = %s, lock status = %d\n", lockNumber, state.description(), status);
    }

    //Close lock not yet implemented
    @Override
    public void closeLockCommandResponse(byte lockNumber, LockStatus state, byte status) {
        LockControlListener.super.closeLockCommandResponse(lockNumber, state, status);
    }

    @Override
    public void lockAlarms(List<AlarmCode> alarms) {
        for (AlarmCode code : alarms)
        {
            System.out.println("Received alarm: " + code.name());
        }
        LockControlListener.super.lockAlarms(alarms);
    }

    public void run(String... args) throws Exception {

        System.out.println("Starting lock controller demo");
        lockController = LockController.instance(secrets);
        lockController.enableLogging(false);
        lockController.start(this);

        // Start up reading commands from the console

        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running)
        {
            System.out.printf(">");
            String command = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (!command.equals("exit"))
            {
                if (!connected)
                {
                    System.out.println("Error - lock controller is not connected.");
                }
                else
                {
                    StringTokenizer st = new StringTokenizer(command);
                    try {
                        final String verb = st.nextToken();
                        switch (verb) {
                            case "test":
                                try {
                                    String lockName = st.nextToken();
                                    try {
                                        int lock = Integer.valueOf(lockName);
                                        if (lock < 1 || lock > 4) {
                                            throw new IllegalArgumentException();
                                        }
                                        lock--;
                                        System.out.printf("Opening lock %d for 5 seconds\n", lock);
                                        lockController.requestOpenLock(lock, 5);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("'" + lockName + "' is not a valid lock number");
                                    }
                                } catch (NoSuchElementException e) {
                                    System.out.println("Test: opening locks 1 to 4 for 5 seconds");
                                }
                        }
                    }
                    catch (NoSuchElementException e) {}
                }
            }
            else
            {
                System.out.println("shutting down controller link");
                lockController.stop();
                System.exit(0);
            }
        }
    }
}