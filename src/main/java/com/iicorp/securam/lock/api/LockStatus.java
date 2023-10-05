package com.iicorp.securam.lock.api;

import com.iicorp.securam.lock.messages.LockState;

public enum LockStatus
{
    LS_CLOSED("Closed"),
    LS_OPENING("Opening"),
    LS_OPEN("Open"),
    LS_CLOSING("Closing"),
    LS_OPEN_TIMEOUT("Opening Timeout"),
    LS_CLOSE_TIMEOUT("Closing Timeout");

    private String description;

    private LockStatus(String description)
    {
       this.description = description;
    }

    public String description() { return description; }

    public static LockStatus fromLockState(LockState lockState)
    {
        return values()[lockState.ordinal()];
    }

    public static LockStatus fromByteCode(byte code)
    {
        return values()[(int) code];
    }
}
