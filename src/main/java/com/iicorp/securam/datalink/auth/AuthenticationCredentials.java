package com.iicorp.securam.datalink.auth;

public interface AuthenticationCredentials
{
    public String getCredentialsName();

    public byte[] getLocalSecret();

    public byte[] getRemoteSecret();
}
