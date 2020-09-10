package com.tom.common.encryption;

import java.security.MessageDigest;

public class SHA1Encryptor {
	
	private SHA1Encryptor(){
		throw new IllegalAccessError("Utility class");
	}

	private static String convertToHex(byte[] data) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int twoHalfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9)) {
					buf.append((char) ('0' + halfbyte));
				} else {
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = data[i] & 0x0F;
			} while (twoHalfs++ < 1);
		}
		return buf.toString();
	}

	public static String sHA1(String text) throws Exception {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash;
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	public static void main(String[] args) {
		// test
	}

}
