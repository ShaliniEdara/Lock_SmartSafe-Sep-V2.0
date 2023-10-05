package com.iicorp.securam.datalink;

public enum LinkState
{
    WS_DISCONNECTED,
    WS_CONNECTING,
    WS_AUTHENTICATING,
    WS_CRYPTO_NEGOTIATING,
    WS_CONNECTED,
    WS_L1_DOWN,
    WS_DISCONNECTING;
}
