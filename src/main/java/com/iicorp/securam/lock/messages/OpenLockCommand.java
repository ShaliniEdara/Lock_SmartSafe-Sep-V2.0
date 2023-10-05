package com.iicorp.securam.lock.messages;

import java.nio.ByteBuffer;

public class OpenLockCommand implements LockControlMessage
{
    private final byte lock;

    private final int duration;

    private short signatureLength;

    private byte[] signature;

    public OpenLockCommand(byte lock, int duration)
    {
        this.lock = lock;
        this.duration = duration;
        signatureLength = 0;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.OPEN_LOCK_COMMAND;
    }

    @Override
    public byte[] marshall() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(MessageType.OPEN_LOCK_COMMAND.code());
        buffer.put(lock);
        buffer.putInt(duration);
        buffer.putShort(signatureLength);
        buffer.flip();
        byte[] frame = new byte[buffer.limit()];
        buffer.get(frame);
        return frame;
    }
}
