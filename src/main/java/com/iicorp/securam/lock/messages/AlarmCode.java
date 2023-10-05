package com.iicorp.securam.lock.messages;

import java.util.ArrayList;
import java.util.List;

public enum AlarmCode
{
    NO_ALARMS(0, "No Alarms"),
    BATTERY_DISCONNECTED(0x00000001, "9V Power Unavailable"),
    LOCK_POWER_UNAVAILABLE(0x00000001, "9V Power Unavailable"),
    LOW_BATTERY(0x00000002, "Lock Power Low");

    private final int bitMask;

    private final String description;

    private AlarmCode(int bitMask, final String description)
    {
        this.description = description;
        this.bitMask = bitMask;
    }

    public boolean isPresentIn(int flags)
    {
        return (flags & bitMask) != 0;
    }

    public static List<AlarmCode> getAlarms(int flags)
    {
        List<AlarmCode> codes = new ArrayList<>();
        for (AlarmCode code : values())
        {
            if (code.isPresentIn(flags))
            {
                codes.add(code);
            }
        }
        return codes;
    }
}
