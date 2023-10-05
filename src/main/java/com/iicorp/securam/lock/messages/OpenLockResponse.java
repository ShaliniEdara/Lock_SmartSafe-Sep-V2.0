package com.iicorp.securam.lock.messages;

import java.nio.ByteBuffer;

public class OpenLockResponse implements LockControlMessage
{
    private byte lockNumber;

    private byte status;

    private byte lockStatus;

    @Override
    public MessageType getMessageType() {
        return MessageType.OPEN_LOCK_RESPONSE;
    }

    @Override
    public void unMarshall(byte[] frame) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(frame);
        buffer.flip();
        byte typeCode = buffer.get();
        if (typeCode != MessageType.OPEN_LOCK_RESPONSE.code())
        {
            throw new IllegalArgumentException("Attempt to unmarshall an OPEN_LOCK_RESPONSE from message type " + typeCode);
        }
        lockNumber = buffer.get();
        status = buffer.get();
        lockStatus = buffer.get();
    }

    public byte getLockNumber() {
        return lockNumber;
    }

    public byte getStatus() {
        return status;
    }

    public byte getLockStatus() {
        return lockStatus;
    }
}
