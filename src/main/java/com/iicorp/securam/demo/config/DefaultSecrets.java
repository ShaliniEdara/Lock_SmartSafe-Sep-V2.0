package com.iicorp.securam.demo.config;

import com.iicorp.securam.datalink.auth.AuthenticationCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@ConfigurationProperties(prefix = "iicorp.controller.secrets.default")
public class DefaultSecrets implements AuthenticationCredentials {
    private String name;

    private byte[] localSecret;

    private byte[] remoteSecret;

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setLocalSecret(String secret)
    {
        this.localSecret = secret.getBytes(StandardCharsets.UTF_8);
    }

    public void setRemoteSecret(String secret)
    {
        this.remoteSecret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String getCredentialsName() {
        return name;
    }

    @Override
    public byte[] getLocalSecret() {
        return localSecret;
    }

    @Override
    public byte[] getRemoteSecret() {
        return remoteSecret;
    }
}
