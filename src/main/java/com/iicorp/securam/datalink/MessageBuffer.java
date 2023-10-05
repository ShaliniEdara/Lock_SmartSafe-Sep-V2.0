package com.iicorp.securam.datalink;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageBuffer {

    protected ByteBuffer buffer;

    public MessageBuffer(final int size)
    {
        buffer = ByteBuffer.allocate(size);
    }

    public MessageBuffer(final byte[] source)
    {
        buffer = ByteBuffer.allocate(source.length);
        buffer.put(source);
    }

    public MessageBuffer(final MessageBuffer other)
    {
        buffer = ByteBuffer.allocate(other.getTotalCapacity());
        buffer.put(other.buffer);
    }

    protected MessageBuffer(ByteBuffer source)
    {
        this.buffer = source;
    }

    public byte[] getBuffer() { return buffer.array(); }

    public int getSize() { return buffer.position(); }

    public int getTotalCapacity() { return buffer.capacity(); }

    public int getCurrentCapacity() { return buffer.remaining(); }

    public void reset() { buffer.rewind(); }

    public void reset(int to) { buffer.position(to); }

    public void flip() { buffer.flip(); }

    public MessageBuffer skip(final int offset)
    {
        buffer.position(buffer.position() + offset);
        return this;
    }

    public MessageBuffer append(final byte value) {
        buffer.put(value);
        return this;
    }

    public MessageBuffer append(final short value) {
        buffer.putShort(value);
        return this;
    }

    public MessageBuffer append(final int value) {
        buffer.putInt(value);
        return this;
    }

    public MessageBuffer append(final long value) {
        buffer.putLong(value);
        return this;
    }

    public MessageBuffer append(final String value) {
        buffer.put(value.getBytes(StandardCharsets.UTF_8));
        return this;
    }

    public MessageBuffer append(byte[] value) {
        buffer.put(value);
        return this;
    }

    public MessageBuffer append(byte[] value, int length) {
        buffer.put(value, 0, length);
        return this;
    }

    public MessageBuffer append(List<Byte> value) {
        final byte[] data = new byte[value.size()];
        for (int i = 0; i < value.size(); i++)
        {
            data[i] = value.get(i);
        }
        append(data);
        return this;
    }

    public byte get() {
        return buffer.get();
    }

    public short getShort() {
        return buffer.getShort();
    }

    public int getInt() {
        return buffer.getInt();
    }

    public long getLong() {
        return buffer.getLong();
    }
}
