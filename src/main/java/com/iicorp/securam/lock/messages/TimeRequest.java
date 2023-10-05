package com.iicorp.securam.lock.messages;

import java.nio.ByteBuffer;

public class TimeRequest implements LockControlMessage
{
    private int lockTime;

    @Override
    public void unMarshall(byte[] frame) {
        ByteBuffer buffer = ByteBuffer.allocate(frame.length);
        buffer.put(frame);
        buffer.flip();
        byte type = buffer.get();
        lockTime = buffer.getInt();
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.TIME_REQUEST;
    }

    public int getLockTime() { return this.lockTime; }
}
