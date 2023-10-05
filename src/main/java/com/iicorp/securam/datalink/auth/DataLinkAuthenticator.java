package com.iicorp.securam.datalink.auth;


import com.iicorp.securam.datalink.DataLinkFrame;

import java.util.ArrayList;
import java.util.List;

public interface DataLinkAuthenticator
{
     public enum AuthenticationMode
     {
         ACTIVE,
         PASSIVE;
     }

     public enum AuthenticationState {
         AS_UNKNOWN,
         AS_NOT_AUTHENTICATED,
         AS_WAIT_LOGIN,
         AS_SENT_LOGIN,
         AS_SENT_CHALLENGE,
         AS_SENT_RESPONSE,
         AS_AUTHENTICATED;
     }

    static final byte LOGIN_REQUEST = 1;
    static final byte CHALLENGE = 2;
    static final byte CHALLENGE_RESPONSE = 3;
    static final byte AUTHENTICATION_RESPONSE = 4;

    void setDataLink(AuthenticatorDataLink dataLink);
    default void aetPeerId(String id) {};
    default void setCredentials(final AuthenticationCredentials credentials) {};
    int  authenticate(AuthenticationMode mode);
    boolean isAuthenticated();
    void cancelAuthentication();
    void authDataReceived(final byte[] data);
    void authTimerExpired();
    default void authFrameAcked(DataLinkFrame frame) {};
    void setEncryptionData(byte[] encdata);
    default List<Byte> getEncryptionData() {return new ArrayList<>();}
    default List<Byte> getRawKeyMaterial() {return new ArrayList<>();}

}
