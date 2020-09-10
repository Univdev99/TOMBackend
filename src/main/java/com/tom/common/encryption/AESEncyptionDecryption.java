package com.tom.common.encryption;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To encrypt and decrypt using AES algorithm(Alternate of OmniOneUI CryptoJs Encrypt & Decrypt)
 * 
 * @author KBaria
 * @reference https://github.com/brix/crypto-js/issues/165
 */
public class AESEncyptionDecryption {
	static final Logger LOGGER = LoggerFactory.getLogger(AESEncyptionDecryption.class);

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String AES = "AES";

	/**
	 * Encrypt
	 * 
	 * @param plaintext
	 * plain string
	 * @param secretKey
	 * string
	 * @return
	 */
	public static String encrypt(String plaintext, String secretKey) {
		try {
			// fixKeyLength();
			final int keySize = 256;
			final int ivSize = 128;

			// Create empty key and iv
			byte[] key = new byte[keySize / 8];
			byte[] iv = new byte[ivSize / 8];

			// Create random salt
			byte[] saltBytes = generateSalt(8);

			// Derive key and iv from passphrase and salt
			EvpKDF(secretKey.getBytes(StandardCharsets.UTF_8), keySize, ivSize, saltBytes, key, iv);

			// Actual encrypt
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, AES), new IvParameterSpec(iv));
			byte[] cipherBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

			/**
			 * Create CryptoJS-like encrypted string from encrypted data This is how CryptoJS do: 1. Create new byte array to hold ecrypted string (b) 2. Concatenate 8 bytes to b 3. Concatenate salt
			 * to b 4. Concatenate encrypted data to b 5. Encode b using Base64
			 */
			byte[] sBytes = "Salted__".getBytes(StandardCharsets.UTF_8);
			byte[] b = new byte[sBytes.length + saltBytes.length + cipherBytes.length];
			System.arraycopy(sBytes, 0, b, 0, sBytes.length);
			System.arraycopy(saltBytes, 0, b, sBytes.length, saltBytes.length);
			System.arraycopy(cipherBytes, 0, b, sBytes.length + saltBytes.length, cipherBytes.length);

			byte[] base64b = Base64.encodeBase64(b);

			return new String(base64b);
		} catch (Exception e) {
			LOGGER.error("AESEncyptionDecryption---> encrypt ---> ", e);
		}

		return null;
	}

	/**
	 * Decrypt
	 * 
	 * @param ciphertext
	 * encrypted string
	 * @param secretKey
	 * string
	 */
	public static String decrypt(String ciphertext, String secretKey) {
		try {
			// fixKeyLength();
			final int keySize = 256;
			final int ivSize = 128;

			// Decode from base64 text
			byte[] ctBytes = Base64.decodeBase64(ciphertext);

			// Get salt
			byte[] saltBytes = Arrays.copyOfRange(ctBytes, 8, 16);

			// Get ciphertext
			byte[] ciphertextBytes = Arrays.copyOfRange(ctBytes, 16, ctBytes.length);

			// Get key and iv from passphrase and salt
			byte[] key = new byte[keySize / 8];
			byte[] iv = new byte[ivSize / 8];
			EvpKDF(secretKey.getBytes(StandardCharsets.UTF_8), keySize, ivSize, saltBytes, key, iv);

			// Actual decrypt
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, AES), new IvParameterSpec(iv));
			byte[] recoveredPlaintextBytes = cipher.doFinal(ciphertextBytes);

			return new String(recoveredPlaintextBytes);
		} catch (Exception e) {
			LOGGER.error("AESEncyptionDecryption---> decrypt ---> ", e);
		}

		return null;
	}

	/**
	 * @return a new pseudorandom salt of the specified length
	 */
	private static byte[] generateSalt(int length) {
		Random r = new SecureRandom();
		byte[] salt = new byte[length];
		r.nextBytes(salt);
		return salt;
	}

	private static byte[] EvpKDF(byte[] password, int keySize, int ivSize, byte[] salt, byte[] resultKey, byte[] resultIv) throws NoSuchAlgorithmException {
		return EvpKDF(password, keySize, ivSize, salt, 1, "MD5", resultKey, resultIv);
	}

	private static byte[] EvpKDF(byte[] password, int keySize, int ivSize, byte[] salt, int iterations, String hashAlgorithm, byte[] resultKey, byte[] resultIv) throws NoSuchAlgorithmException {
		keySize = keySize / 32;
		ivSize = ivSize / 32;
		int targetKeySize = keySize + ivSize;
		byte[] derivedBytes = new byte[targetKeySize * 4];
		int numberOfDerivedWords = 0;
		byte[] block = null;
		MessageDigest hasher = MessageDigest.getInstance(hashAlgorithm);
		while (numberOfDerivedWords < targetKeySize) {
			if (block != null) {
				hasher.update(block);
			}
			hasher.update(password);
			block = hasher.digest(salt);
			hasher.reset();

			// Iterations
			for (int i = 1; i < iterations; i++) {
				block = hasher.digest(block);
				hasher.reset();
			}

			System.arraycopy(block, 0, derivedBytes, numberOfDerivedWords * 4, Math.min(block.length, (targetKeySize - numberOfDerivedWords) * 4));

			numberOfDerivedWords += block.length / 4;
		}

		System.arraycopy(derivedBytes, 0, resultKey, 0, keySize * 4);
		System.arraycopy(derivedBytes, keySize * 4, resultIv, 0, ivSize * 4);

		return derivedBytes; // key + iv
	}

	// public static void fixKeyLength() {
	// String errorString = "Failed manually overriding key-length permissions.";
	// try {
	// int newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
	// if (newMaxKeyLength < 256) {
	// Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
	// Constructor con = c.getDeclaredConstructor();
	// con.setAccessible(true);
	// Object allPermissionCollection = con.newInstance();
	// Field f = c.getDeclaredField("all_allowed");
	// f.setAccessible(true);
	// f.setBoolean(allPermissionCollection, true);
	//
	// c = Class.forName("javax.crypto.CryptoPermissions");
	// con = c.getDeclaredConstructor();
	// con.setAccessible(true);
	// Object allPermissions = con.newInstance();
	// f = c.getDeclaredField("perms");
	// f.setAccessible(true);
	// ((Map) f.get(allPermissions)).put("*", allPermissionCollection);
	//
	// c = Class.forName("javax.crypto.JceSecurityManager");
	// f = c.getDeclaredField("defaultPolicy");
	// f.setAccessible(true);
	// Field mf = Field.class.getDeclaredField("modifiers");
	// mf.setAccessible(true);
	// mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	// f.set(null, allPermissions);
	//
	// newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
	// }
	// } catch (Exception e) {
	// throw new RuntimeException(errorString, e);
	// }
	// }

	public static void main(String[] args) {
		// String key = "Oiuibnsd787554YUTGj";
		String key = "Oiuibnsd787554YUOLIUYTCHT124asoO";
		// String key = "Oiuibnsd787554YU";
		String originalString = "mtom123";
		System.out.println("Original String to encrypt - " + originalString);
		String encryptedString = encrypt(originalString, key);
		System.out.println("Encrypted String - " + encryptedString);
		String decryptedString = decrypt("U2FsdGVkX18+ZDQZ7GP/0t8HHN3cpU2Fltfp2PBELoI=", "Oiuibnsd787554YUTGj");
		System.out.println("After decryption - " + decryptedString);
	}

}
