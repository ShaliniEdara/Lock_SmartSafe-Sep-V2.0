package com.iicorp.securam.lock.messages;

import java.nio.ByteBuffer;
import java.util.List;

public class LockStatusMessage implements LockControlMessage
{
    private byte numberOfLocks;

    private byte[] lockStatus = new byte[8];

    private short batteryADCValue;

    private double batteryVoltage;

    private int alarmFlags;

    @Override
    public MessageType getMessageType() {
        return MessageType.LOCK_STATUS;
    }

    @Override
    public void unMarshall(byte[] frame) {
        ByteBuffer buffer = ByteBuffer.allocate(frame.length);
        buffer.put(frame);
        buffer.flip();
        buffer.get(); // Skip the message type
        numberOfLocks = buffer.get();
        for (int i = 0; i < numberOfLocks; i++)
        {
            lockStatus[i] = buffer.get();
        }
        for (int i = 0; i < (8 - numberOfLocks); i++)
        {
            buffer.get();
        }
        batteryADCValue = buffer.getShort();
        byte[] voltageString = new byte[6];
        buffer.get(voltageString, 0, 6);
        batteryVoltage = Double.valueOf(new String(voltageString));
        alarmFlags = buffer.getInt();
    }

    public int getNumberOfLocks()
    {
        return ((int) numberOfLocks & 0x000000ff);
    }

    public byte[] getLockStatus() { return lockStatus; }

    public short getBatteryADCValue() { return batteryADCValue; }

    public double getBatteryVoltage()
    {
        return batteryVoltage;
    }

    public List<AlarmCode> getAlarmCodes()
    {
        return AlarmCode.getAlarms(alarmFlags);
    }
}
