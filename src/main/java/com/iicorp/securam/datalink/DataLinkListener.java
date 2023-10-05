package com.iicorp.securam.datalink;

public interface DataLinkListener
{
    void linkConnected();

    void linkDisconnected();

    void linkAuthenticationFailed();

    void dataIndication(byte[] message);
}
