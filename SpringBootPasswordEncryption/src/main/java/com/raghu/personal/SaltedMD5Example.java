package com.raghu.personal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class SaltedMD5Example {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		// TODO Auto-generated method stub
		String passwordToHash = "password";
		byte[] salt = getSalt();

		String securePassword = getSecurePassword(passwordToHash, salt);
		System.out.println(securePassword);
		String regeneratedPasswordToVerify = getSecurePassword(passwordToHash, salt);
		System.out.println(regeneratedPasswordToVerify);
	}

	private static String getSecurePassword(String passwordToHash, byte[] salt) {
		// TODO Auto-generated method stub
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return generatedPassword;
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
		// TODO Auto-generated method stub
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

}
