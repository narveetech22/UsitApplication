package com.narvee.usit.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestClassDummy {

	
	
	
	public static void main(String[] args) {
		String AlphaNumericString = "kiran";
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		System.out.println(sb.toString() + " your password is");
		String generatedpsw = "kiran";//sb.toString();
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(sb.toString().getBytes());
		// commented for mail error
		byte[] bytes = m.digest();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		String encryptedpassword = s.toString();
		System.out.println(encryptedpassword);
	}
}
