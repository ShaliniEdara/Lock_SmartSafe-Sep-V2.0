package com.iicorp.securam.datalink;

import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class LockPortLocator
{
    private static final Logger logger = LoggerFactory.getLogger("LockPortLocator");

    private static final long LISTEN_TIMEOUT = 15000;

    public static Optional<SerialPort> locateLockPort()
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        List<String> portnames = new ArrayList<>();
        for (SerialPort port : ports)
        {
            if (port.getPortDescription().contains("Dual Serial"))
            {
                portnames.add(port.getSystemPortName());
            }
        }
        if (portnames.isEmpty())
        {
            logger.info("No Dual Serial ports located on this system");
            return Optional.empty();
        }

        logger.info("Found " + portnames.size() + " candidate ports");
        portnames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String portName : portnames)
        {
            SerialPort serialPort = SerialPort.getCommPort(portName);
            serialPort.openPort();
            serialPort.flushIOBuffers();
            DataLinkInputFrame frame = new DataLinkInputFrame(256);
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10000, 10000);
            long startTime = System.currentTimeMillis();
            byte[] input = new byte[256];
            logger.info("Checking port " + portName);
            while ((System.currentTimeMillis() - startTime) <  LISTEN_TIMEOUT)
            {
                int readLength = serialPort.readBytes(input, input.length);
                if (readLength > 0)
                {
                    for (int i=0; i < readLength; i++)
                    {
                        boolean complete = frame.processInput(input[i]);
                        if (complete)
                        {
                            logger.info("Controller detected on port " + portName + " selected");
                            serialPort.closePort();
                            return Optional.of(serialPort);
                        }
                    }
                }
            }
            logger.info("No controller detected on port " + portName);
            serialPort.closePort();
        }
        logger.info("No lock controllers detected on comm ports");
        return Optional.empty();
    }
}
