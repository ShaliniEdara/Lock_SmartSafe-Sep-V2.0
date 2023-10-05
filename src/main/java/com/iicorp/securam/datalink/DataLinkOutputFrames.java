package com.iicorp.securam.datalink;

public class DataLinkOutputFrames
{
    public static class NetworkData extends DataLinkFrame
    {
        public NetworkData(final byte[] data, int size, byte sequence, FrameType type)
        {
            super(size + FRAME_OVERHEAD, type);
            setSequence(sequence);
            buffer.put(data);
            marshal();
        }

        public NetworkData(final byte[] data, byte sequence, FrameType type)
        {
            super(data.length + FRAME_OVERHEAD, type);
            setSequence(sequence);
            buffer.put(data);
            marshal();
        }

        public NetworkData(final byte[] data)
        {
            this(data, (byte) 0, FrameType.LINK_DATA);
        }
    }

    public static class CryptoData extends DataLinkFrame
    {
        public CryptoData(byte protocol, final byte[] data, int size)
        {
            super(size + FRAME_OVERHEAD, FrameType.CRYPTO_DATA);
            setSequence((byte) 0);
            buffer.put(protocol);
            buffer.put(data);
            marshal();
        }

        public CryptoData(DataLinkFrame input)
        {
            super(input);
        }

        byte cryptoProtocol()
        {
            return buffer.get(FRAME_OVERHEAD);
        }

        boolean unMarshall()
        {
            buffer.position(DATA_OFFSET + 1);
            return super.unMarshal();
        }
    }

    public static class FrameAck extends DataLinkFrame
    {
        public FrameAck(byte sequence)
        {
            super(16, FrameType.FRAME_ACK);
            setSequence(sequence);
            marshal();
        }
    }

    public static class LinkReset extends DataLinkFrame
    {
        public LinkReset()
        {
            super(16, FrameType.LINK_RESET);
            marshal();
        }
    }

    public static class ResetAck extends DataLinkFrame
    {
        public ResetAck()
        {
            super(16, FrameType.RESET_ACK);
            marshal();
        }
    }

    public static class KeepAlive extends  DataLinkFrame
    {
        public KeepAlive()
        {
            super(16, FrameType.KEEPALIVE);
            marshal();
        }
    }

    public static class LinkDisconnect extends DataLinkFrame
    {
        public LinkDisconnect()
        {
            super(16, FrameType.LINK_DISCONNECT);
            marshal();
        }
    }

    public static class FrameReject extends DataLinkFrame
    {
        private byte code;

        private String reason;

        public FrameReject(byte code, String reason)
        {
            super(FRAME_OVERHEAD + reason.length() + 1, FrameType.FRAME_REJECT);
            this.code = code;
            this.reason = reason;
            append(code).append(reason);
            marshal();
        }

        public FrameReject(DataLinkFrame input)
        {
            super(input);
            this.code = buffer.get();
            this.reason = new String(buffer.array(), buffer.position(), buffer.capacity() - buffer.position());
        }

        public String getReason() { return this.reason; }

    }
}
