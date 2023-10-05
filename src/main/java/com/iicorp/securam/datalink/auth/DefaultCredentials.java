package com.iicorp.securam.datalink.auth;

import java.nio.charset.StandardCharsets;

public class DefaultCredentials implements AuthenticationCredentials
{
    private final String credentialsName;

    private final byte[] localSecret;

    private final byte[] remoteSecret;

    public DefaultCredentials()
    {
        credentialsName = "TEST_CREDENTIALS";
        this.localSecret = "Ask me no questions, I'll tell you no lies. Maybe.".getBytes(StandardCharsets.UTF_8);
        this.remoteSecret = "Error: unauthorized use of binary exchanges".getBytes(StandardCharsets.UTF_8);
    }

    public DefaultCredentials(String credentialsName, byte[] localSecret, byte[] remoteSecret)
    {
        this.credentialsName = credentialsName;
        this.localSecret = localSecret;
        this.remoteSecret = remoteSecret;
    }

    @Override
    public String getCredentialsName() { return credentialsName; }

    @Override
    public byte[] getLocalSecret() { return localSecret; }

    @Override
    public byte[] getRemoteSecret() { return remoteSecret; }
}
