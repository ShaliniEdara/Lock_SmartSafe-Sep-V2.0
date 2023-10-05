package com.iicorp.securam.lock.messages;

public interface LockControlMessage
{
    MessageType getMessageType();

    default MessageType getMessageType(byte[] rawMessage)
    {
        return MessageType.ofCode(rawMessage[0]);
    }

    default void unMarshall(byte[] frame) {}

    default byte[] marshall() { return null; };
}
