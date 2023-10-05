package com.iicorp.securam.datalink.auth;

import com.iicorp.securam.common.HexFormatter;
import com.iicorp.securam.datalink.DataLinkFrame;
import com.iicorp.securam.datalink.MessageBuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigestLinkAuthenticator implements DataLinkAuthenticator
{
    private static final Logger logger = Logger.getLogger("DataLinkAuthenticator");

    private AuthenticationState state;

    private boolean authenticated;

    private AuthenticatorDataLink dataLink;

    private String peerId;

    private byte[] localSecret;

    private byte[] remoteSecret;

    private Random random;

    private int nonce1;

    private int nonce2;

    private byte[] digest;

    private List<Byte> encryptionData = new ArrayList<>();

    private final Timer authenticationTimer;

    private final AuthTimerCallback timerCallback;

    public DigestLinkAuthenticator(AuthenticationCredentials secrets, AuthenticatorDataLink dataLink)
    {
        this(secrets);
        this.dataLink = dataLink;
    }

    public DigestLinkAuthenticator(AuthenticationCredentials secrets)
    {
        this.random = new Random();
        this.localSecret = "Ask me no questions, I'll tell you no lies. Maybe.".getBytes(StandardCharsets.UTF_8);
        this.remoteSecret = "Error: unauthorized use of binary exchanges".getBytes(StandardCharsets.UTF_8);
        //System.out.println(HexFormatter.bytesToHex(remoteSecret));
        this.authenticationTimer  = new Timer("AuthTimer");
        this.timerCallback = new AuthTimerCallback();
    }

    @Override
    public void setDataLink(AuthenticatorDataLink dataLink) {
       this.dataLink = dataLink;
    }

    @Override
    public int authenticate(AuthenticationMode mode) {
        authenticated = false;
        if (mode == AuthenticationMode.ACTIVE)
        {
            // Not supported for now
        }
        else
        {
            state = AuthenticationState.AS_WAIT_LOGIN;
            setAuthenticationTimer(60000L);
        }
        return 0;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void cancelAuthentication() {
        authenticated = false;
        state = AuthenticationState.AS_NOT_AUTHENTICATED;
        cancelAuthenticationTimer();
    }

    @Override
    public void authDataReceived(byte[] data) {

        int type = data[0];
        switch (type)
        {
            case LOGIN_REQUEST:
                logger.log(Level.INFO, "authenticator: LOGIN REQUEST");
                sendChallenge();
                break;
            case CHALLENGE:
                logger.log(Level.INFO, "authenticator: CHALLENGE");
                processChallenge(data);
                break;
            case CHALLENGE_RESPONSE:
                logger.log(Level.INFO, "authenticator: CHALLENGE RESPONSE");
                processChallengeResponse(data);
                break;
            case AUTHENTICATION_RESPONSE:
                logger.log(Level.INFO, "authenticator: AUTHENTICATION RESPONSE");
                processAuthenticationResponse(data);
                break;
            default:
                break;
        }
    }

    @Override
    public void authTimerExpired() {
        switch (state)
        {
            case AS_SENT_LOGIN:
            case AS_WAIT_LOGIN:
            case AS_SENT_CHALLENGE:
            case AS_SENT_RESPONSE:
                state = AuthenticationState.AS_NOT_AUTHENTICATED;
                authenticated = false;
                dataLink.authenticationComplete(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void authFrameAcked(DataLinkFrame frame) {
        switch (state)
        {
            case AS_SENT_RESPONSE:
                dataLink.authenticationComplete(authenticated);
                cancelAuthenticationTimer();
                break;
            default:
                break;
        }
    }

    @Override
    public void setEncryptionData(byte[] encdata) {
        for (byte data : encdata)
        {
            encryptionData.add(data);
        }
    }

    protected void sendChallenge()
    {
        nonce1 = random.nextInt(0x7ffffffe);
        random.nextInt(0x7ffffffe);
        nonce2 = random.nextInt(0x7ffffffe);
        ByteBuffer buffer = ByteBuffer.allocate(64);
        computeDigest(remoteSecret);
        buffer.put(CHALLENGE).putInt(nonce1).putInt(nonce2).put(digest);
        state = AuthenticationState.AS_SENT_CHALLENGE;
        setAuthenticationTimer(60000L);
        dataLink.sendAuthData(buffer.array());
    }

    protected void processChallenge(byte[] data)
    {
        // Not implemented in server only mode
    }

    protected void processChallengeResponse(byte[] data)
    {
        if (state != AuthenticationState.AS_SENT_CHALLENGE)
        {
            return;
        }
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();
        buffer.get(); // Skip the type byte
        byte[] remoteDigest = new byte[16];
        buffer.get(remoteDigest);
        computeDigest(localSecret);
        if (Arrays.compare(digest, remoteDigest) == 0)
        {
            logger.log(Level.INFO, "Digest authentication succeeded");
            authenticated = true;
        }
        else
        {
            authenticated = false;
            state = AuthenticationState.AS_NOT_AUTHENTICATED;
            cancelAuthenticationTimer();
            dataLink.authenticationComplete(false);
        }
        sendLoginResponse(authenticated);
    }

    protected void processAuthenticationResponse(byte[] data)
    {
        // Not implemented in server only mode
    }

    protected void sendLoginResponse(boolean authenticated)
    {
        MessageBuffer response = new MessageBuffer(32);
        response.append(AUTHENTICATION_RESPONSE);
        if (authenticated)
        {
            response.append(nonce1);
        }
        else
        {
            response.append(nonce2);
        }
        response.append(encryptionData);
        state = AuthenticationState.AS_SENT_RESPONSE;
        dataLink.sendAuthData(response.getBuffer());
    }

    protected void computeDigest(byte[] secret)
    {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(secret.length + 8);
            buffer.putInt(nonce1).put(secret).putInt(nonce2);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            this.digest = md5.digest(buffer.array());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected void setAuthenticationTimer(long timeout)
    {
        this.authenticationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                authTimerExpired();
            }
        }, timeout);
    }

    protected void cancelAuthenticationTimer()
    {
        this.authenticationTimer.cancel();
        this.authenticationTimer.purge();
    }

    private static class AuthTimerCallback extends TimerTask
    {

        @Override
        public void run() {

        }
    }
}
