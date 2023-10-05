package com.iicorp.securam.lock.messages;

public class LockStatusRequest implements LockControlMessage
{
    @Override
    public MessageType getMessageType() {
        return MessageType.LOCK_STATUS_REQUEST;
    }

    @Override
    public byte[] marshall() {
        byte[] request = new byte[1];
        request[0] = MessageType.LOCK_STATUS_REQUEST.code();
        return request;
    }
}
