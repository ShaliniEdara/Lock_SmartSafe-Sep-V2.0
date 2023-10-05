package com.iicorp.securam.datalink;

public class DataLinkFrame extends MessageBuffer
{
    public static final byte FLAG = 0x7e;
    public static final byte ADDRESS = (byte) 0xff;
    public static final byte CONTROL = 0x01;
    public static final short PROTOCOL = 0x405b;
    public static final byte PROTO_HIGH = (byte)((PROTOCOL >> 8) & 0xff);
    public static final byte PROTO_LOW = (byte)(PROTOCOL & 0xff);
    public static final byte PROTOCOL_VERSION = 1;

    // Constants for frame components

    public static final int FRAME_OVERHEAD = 14;
    public static final int MAX_FRAME_DATA = 4096;

    // Option bits

    protected static final Byte PAYLOAD_ENCRYPTED = (byte) 0x80;

    // Offsets

    protected static final int VERSION_OFFSET = 5;
    protected static final int OPTIONS_OFFSET = 6;
    protected static final int LENGTH_OFFSET = 7;
    protected static final int TYPE_OFFSET = 9;
    protected static final int SEQUENCE_OFFSET = 10;
    protected static final int DATA_OFFSET = 11;
    protected static final int L3_ID_OFFSET = DATA_OFFSET;
    protected static final int PORT_OFFSET = L3_ID_OFFSET;
    protected static final int L3_ID_SIZE = 2;
    protected static final int L3_ID_OFFSET1 = PORT_OFFSET + L3_ID_SIZE;
    protected static final int PROXY_DATA_OFFSET = L3_ID_OFFSET + L3_ID_SIZE;

    protected boolean	marshalled;
    protected boolean	shallowCopy;
    protected byte		options;
    protected byte		version;
    protected int       length;
    protected byte      type;
    protected byte      sequence;

    public DataLinkFrame(int capacity)
    {
        super(capacity);
    }

    public DataLinkFrame(int capacity, FrameType type)
    {
        super(capacity);
        this.version = PROTOCOL_VERSION;
        this.append(FLAG).append(ADDRESS).append(CONTROL).append(PROTOCOL).append(PROTOCOL_VERSION).append(options).append((short)(buffer.position() - 1)).append(type.code()).append((byte)0);
    }

    public DataLinkFrame(byte[] data)
    {
        super(data.length);
        append(data);
        this.version = PROTOCOL_VERSION;
    }

    public DataLinkFrame(final MessageBuffer other)
    {
        super(other.buffer.duplicate());
        this.shallowCopy = true;
        this.version = buffer.get(VERSION_OFFSET);
        this.options = buffer.get(OPTIONS_OFFSET);
    }

    public byte getSequence() { return buffer.get(SEQUENCE_OFFSET); }

    public void setSequence(byte sequence) { buffer.put(SEQUENCE_OFFSET, sequence); recalculateCRC(); }

    public FrameType getType() { return FrameType.ofCode(buffer.get(TYPE_OFFSET)); }

    public boolean isPayloadEncrypted() { return (options & PAYLOAD_ENCRYPTED) != 0; }

    public void setPayloadEncrypted() { options |= PAYLOAD_ENCRYPTED; buffer.put(OPTIONS_OFFSET, options); }

    public byte getVersion() { return this.version; }

    public byte getProtocolVersion() { return buffer.get(VERSION_OFFSET); }

    public void insertDataField(byte[] data)
    {
        buffer.put(data);
    }

    public byte[] getMessage()
    {
        if (!marshalled)
        {
            byte[] message = new byte[length - 2];
            buffer.get(message);
            return message;
        }
        throw new IllegalStateException("Attempt to read message from marshalled frame");
    }

    // Marshall and unmarshal

    public boolean marshal()
    {
        // Compute the CRC and add it and the closing flag to the frame
        // CRC is calculated from the ADDRESS byte through the last data byte,
        // after inserting the length field. The length field is the length of the data between the
        // length field itself and the CRC.

        int length = buffer.position() - 9;
        buffer.put(LENGTH_OFFSET, (byte)((length >>> 8) & 0x000000ff));
        buffer.put(LENGTH_OFFSET + 1, (byte)(length & 0x000000ff));
        short crc = DataLinkCRC.instance().calculateCRC(buffer, 1, buffer.position() - 1);
        append(crc).append(FLAG);
        marshalled = true;
        return true;
    }

    public boolean unMarshal()
    {
        buffer.position(VERSION_OFFSET);
        version = buffer.get();
        options = buffer.get();
        length = buffer.getShort() & 0x0000ffff;
        type = buffer.get();
        sequence = buffer.get();
        marshalled = false;
        return true;
    }

    public boolean recalculateCRC()
    {
        if (marshalled)
        {
            final short crc = DataLinkCRC.instance().calculateCRC(buffer, 1, buffer.position() - 4);
            final int offset = buffer.position() - 3;
            buffer.put(offset, (byte) (crc >> 8 & 0x00ff));
            buffer.put(offset - 1, (byte) (crc & 0x00ff));
            return true;
        }
        return false;
    }

}
