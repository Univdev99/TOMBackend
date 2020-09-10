package com.tom.common.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class that provides Encryption and Decryption using DESede
 * (Triple-DES) algorithm
 * 
 * @author CCJoshi
 *
 */
public class TripleDES {
	static final Logger LOGGER = LoggerFactory.getLogger(TripleDES.class);
	private static final String DIGEST_STRING = "HG58YZ3CR9";

	private TripleDES() {

	}

	private static void print(String text) throws Exception {
		// USE sysout to print text
		LOGGER.debug("Original String => ", text);

		String codedtext = TripleDES.encrypt(text);
		LOGGER.debug("Encoded String  => ", codedtext);
		System.out.println(codedtext);

		String decodedtext = TripleDES.decrypt(codedtext);
		LOGGER.debug("Decoded String  => ", decodedtext);
		System.out.println(decodedtext);
	}

	public static void main(String[] args) throws Exception {
//		print("");
		String decodedtext = TripleDES.decrypt("b2763376a710b26a5159c3dcfd4022d9b2b021ce8338d2f67c2dae9f6d1b24a30c05c51274639549b30e90b6b2699eefa40ed422b2b9cead");
		LOGGER.debug("Decoded String  => ", decodedtext);
		System.out.println(decodedtext);
	}

	public static void main(String x) {
		System.out.println(x);
	}

	/**
	 * Performs encryption on given String which will return encoded bytes, these
	 * bytes will be converted to hex String.
	 * 
	 * @param message The String to be encrypted
	 * @return The encrypted String in hex
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encrypt(String message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfMessage = md.digest(DIGEST_STRING.getBytes("utf-8"));

		final byte[] keyBytes = Arrays.copyOf(digestOfMessage, 24);
		for (int j = 0, k = 16; j < 8; k++, j++) {
			keyBytes[k] = keyBytes[j];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		final byte[] plainTextBytes = message.getBytes();
		final byte[] cipherText = cipher.doFinal(plainTextBytes);

		return String.valueOf(Hex.encodeHex(cipherText));
	}

	/**
	 * 
	 * Performs Decryption on given String (in hex format)
	 * 
	 * @param hexMessage The String (in hex format) to be decrypted
	 * @return The decrypted String
	 * 
	 * @throws DecoderException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(String hexMessage) throws Exception {
		byte[] messageBytes = Hex.decodeHex(hexMessage.toCharArray());

		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8; k++, j++) {
			keyBytes[k] = keyBytes[j];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		decipher.init(Cipher.DECRYPT_MODE, key, iv);

		final byte[] plainText = decipher.doFinal(messageBytes);
		return new String(plainText);
	}

}
