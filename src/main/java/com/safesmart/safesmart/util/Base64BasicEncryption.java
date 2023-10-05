package com.safesmart.safesmart.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class Base64BasicEncryption {

	Base64.Encoder encoder = Base64.getEncoder();

	Base64.Decoder decoder = Base64.getDecoder();

	public String encodePassword(String password) {
		return encoder.encodeToString(password.getBytes());
	}

	public String decodePassword(String encryptedPassword) {
		return new String(decoder.decode(encryptedPassword));
	}

}