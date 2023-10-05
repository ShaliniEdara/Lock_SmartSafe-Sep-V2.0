package com.iicorp.securam.lock.api;

import com.iicorp.securam.datalink.DataLinkInterface;
import com.iicorp.securam.lock.messages.LockControlMessage;

import java.time.LocalDateTime;

public class LockDataIndication {

    private static int nextSequence = 0;

    private final int sequence;

    private final LocalDateTime timestamp;

    private DataLinkInterface dataLinkInterface;

    public enum IndicationType
    {
        CONTROLLER_CONNECTED,
        CONTROLLER_DISCONNECTED,
        AUTHENTICATION_FAILED,
        LOCK_DATA;
    };

    private final IndicationType type;

    private LockControlMessage message;

    public LockDataIndication(IndicationType type, DataLinkInterface dataLinkInterface)
    {
        this.sequence = nextSequence++;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.dataLinkInterface = dataLinkInterface;
    }
    public LockDataIndication(IndicationType type, DataLinkInterface dataLinkInterface, LockControlMessage message)
    {
        this(type, dataLinkInterface);
        this.message = message;
    }

    public int getSequence() {
        return sequence;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public IndicationType getType() {
        return type;
    }

    public LockControlMessage getMessage() {
        return message;
    }

    public DataLinkInterface getLockInterface() { return this.dataLinkInterface; }
}
