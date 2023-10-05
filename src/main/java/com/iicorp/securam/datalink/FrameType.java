package com.iicorp.securam.datalink;

public enum FrameType {
    LINK_RESET(1),
    RESET_ACK(2),
    CONNECT_REQUEST(3),
    CONNECT_CONFIRM(4),
    CONNECT_REJECT(5),
    DISCONNECT_REQUEST(6),
    DISCONNECT_CONFIRM(7),
    LINK_DATA(9),
    FRAME_REJECT(16),
    FRAME_ACK(127),
    AUTHENTICATION_DATA(173), // 0xad
    KEEPALIVE(202), // 0xca
    KEEPALIVE_ACK(212), //0xd4
    CRYPTO_DATA(205), // 0xcd
    LINK_DISCONNECT(255);

    private final byte code;

    FrameType(int code) {
        this.code = (byte) code;
    }

    public byte code() {
        return code;
    }

    public static FrameType ofCode(byte code) {
        for (FrameType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        final String message = String.format("Unknown frame type code : %02X", code);
        throw new IllegalArgumentException(message);
    }

}
