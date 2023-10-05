package com.iicorp.securam.datalink.auth;

public interface AuthenticatorDataLink
{
    int sendAuthData(byte[] data);

    void authenticationComplete(boolean success);
}
