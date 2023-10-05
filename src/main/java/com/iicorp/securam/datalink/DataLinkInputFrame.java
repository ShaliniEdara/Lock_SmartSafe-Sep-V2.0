package com.iicorp.securam.datalink;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataLinkInputFrame extends DataLinkFrame
{
    private static final Logger logger = Logger.getLogger("DataLinkInputFrame");

    private enum FrameInputState
    {
        WAIT_START_FLAG,
        WAIT_ADDRESS,
        WAIT_CONTROL,
        WAIT_PROTO1,
        WAIT_PROTO2,
        WAIT_VERSION,
        WAIT_OPTIONS,
        WAIT_LEN1,
        WAIT_LEN2,
        WAIT_DATA,
        WAIT_CRC1,
        WAIT_CRC2,
        WAIT_END_FLAG;
    }

    private FrameInputState state;

    private short length;

    private short count;

    private short crc;

    public DataLinkInputFrame(int size)
    {
        super(size);
        state = FrameInputState.WAIT_START_FLAG;
        length = 0;
        crc = 0;
    }

    int getDataLength() { return buffer.position() - 2; }

    public boolean processInput(byte input)
    {
        switch (state)
        {
            case WAIT_START_FLAG:
                crc = 0;
                if (input == FLAG)
                {
                    buffer.put(input);
                    state = FrameInputState.WAIT_ADDRESS;
                }
                break;
            case WAIT_ADDRESS:
                if (input == ADDRESS)
                {
                    buffer.put(input);
                    state = FrameInputState.WAIT_CONTROL;
                }
                else if (input != FLAG)
                {
                    resetState();
                }
                break;
            case WAIT_CONTROL:
                if (input == CONTROL)
                {
                    buffer.put(input);
                    state = FrameInputState.WAIT_PROTO1;
                }
                else
                {
                    resetState();
                }
                break;
            case WAIT_PROTO1:
                if (input == PROTO_HIGH)
                {
                    buffer.put(input);
                    state = FrameInputState.WAIT_PROTO2;
                }
                else
                {
                    resetState();
                }
                break;
            case WAIT_PROTO2:
                if (input == PROTO_LOW)
                {
                    buffer.put(input);
                    state = FrameInputState.WAIT_VERSION;
                }
                else
                {
                    resetState();
                }
                break;
            case WAIT_VERSION:
                version = input;
                buffer.put(version);
                state = FrameInputState.WAIT_OPTIONS;
                break;
            case WAIT_OPTIONS:
                options = input;
                buffer.put(input);
                state = FrameInputState.WAIT_LEN1;
                break;
            case WAIT_LEN1:
                buffer.put(input);
                this.length = (short) (input << 8);
                state = FrameInputState.WAIT_LEN2;
                break;
            case WAIT_LEN2:
                buffer.put(input);
                this.length |= (((short) input) & 0x00ff);
                count = length;
                if (count > 0)
                {
                    state = FrameInputState.WAIT_DATA;
                }
                else
                {
                    state = FrameInputState.WAIT_CRC1;
                }
                break;
            case WAIT_DATA:
                buffer.put(input);
                --count;
                if (count == 0)
                {
                    state = FrameInputState.WAIT_CRC1;
                }
                break;
            case WAIT_CRC1:
                buffer.put(input);
                crc = (short) (input << 8);
                state = FrameInputState.WAIT_CRC2;
                break;
            case WAIT_CRC2:
                buffer.put(input);
                crc |= ((short) input) & 0x00ff;
                state = FrameInputState.WAIT_END_FLAG;
                break;
            case WAIT_END_FLAG:
                buffer.put(input);
                short frameCRC = DataLinkCRC.instance().calculateCRC(buffer, 1, buffer.position() - 4);
                if (frameCRC == crc)
                {
                    marshalled = true;
                    return true;
                }
                logger.log(Level.WARNING, "CRC error on incoming frame, ignored");
                resetState();
        }
        return false;
    }

    public void resetState()
    {
        state = FrameInputState.WAIT_START_FLAG;
        crc = 0;
        length = 0;
        count = 0;
        reset();
    }
}
