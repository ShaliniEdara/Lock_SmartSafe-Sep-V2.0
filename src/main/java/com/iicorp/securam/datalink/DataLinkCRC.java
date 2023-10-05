package com.iicorp.securam.datalink;

import java.nio.ByteBuffer;

public class DataLinkCRC {

    private final short[] crc_tab;

    private static final short CRC_POLY = 0x1021;

    private static DataLinkCRC instance;

    public static DataLinkCRC instance()
    {
        if (instance == null)
        {
            instance = new DataLinkCRC();
        }
        return instance;
    }

    private DataLinkCRC() {
        crc_tab = new short[256];
        int accum = 0;
        int data = 0;
        for (int idx = 0; idx < 256; idx++) {
            accum = 0;
            data = idx << 8;
            for (int bit = 0; bit < 8; bit++) {
                if (((accum ^ data) & 0x8000) != 0) {

                    accum <<= 1;
                    accum ^= CRC_POLY;
                } else {
                    accum <<= 1;
                }
                data <<= 1;
            }
            crc_tab[idx] = (short) (accum & 0xffff);
        }
    }

    public short calculateCRC(final ByteBuffer buffer, int offset, int length)
    {
        int shifter = 0;
        while (length-- > 0)
        {
            int idx = (buffer.get(offset++) ^ (byte) ((shifter >>> 24) & 0x000000ff)) & 0x000000ff;
            int temp = (((shifter >>> 8) & 0x0000ffff) ^ crc_tab[idx]);
            shifter = (shifter & 0x0000ffff) | (temp << 16 );
        }
        return (short) ((shifter >> 16) & 0x0000ffff);
    }
}
