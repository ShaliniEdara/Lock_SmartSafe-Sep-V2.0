package com.iicorp.securam.lock.messages;

public enum MessageType {

    TIME_REQUEST((byte) 1),
    TIME_RESPONSE((byte) 11),
    LOCK_STATUS_REQUEST((byte) 2),
    LOCK_STATUS((byte )22),
    OPEN_LOCK_COMMAND((byte) 3),
    OPEN_LOCK_RESPONSE((byte) 33),
    CLOSE_LOCK_COMMAND((byte) 4),
    CLOSE_LOCK_RESPONSE((byte) 44),
    LOG_READ_REQUEST((byte) 5),
    LOG_READ_RESPONSE((byte) 55);

    private byte code;

    private MessageType(byte code)
    {
        this.code = code;
    }

    public byte code() {return code; }

    public static MessageType ofCode(byte code)
    {
        for (MessageType type : values())
        {
            if (type.code == code)
            {
                return type;
            }
        }
        throw new IllegalArgumentException("No message type corresponds to code " + code);
    }
}
