package com.iicorp.securam.lock.messages;

import java.nio.ByteBuffer;
import java.util.TimeZone;

public class TimeResponse implements LockControlMessage
{
    private final TimeRequest request;

    public TimeResponse(final TimeRequest request)
    {
        this.request = request;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.TIME_RESPONSE;
    }

    @Override
    public byte[] marshall() {
        TimeZone tz = TimeZone.getDefault();
        int offsetSeconds = tz.getOffset(System.currentTimeMillis()) / 1000;
        int offsetTimeSeconds = (int) (System.currentTimeMillis() / 1000L) + offsetSeconds;
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(MessageType.TIME_RESPONSE.code());
        buffer.putInt(request.getLockTime());
        buffer.putInt(offsetTimeSeconds);
        buffer.flip();
        byte[] result = new byte[buffer.limit()];
        buffer.get(result);
        return result;
    }
}
