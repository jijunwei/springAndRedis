package com.example.utils.enordecryptutil;


import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;



public class AESTool {
	
	private static final String CHARSET = "UTF-8";

	public String key = new String();

	private byte[] initVector = { 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30,
			0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30 };


	/**
	 * Encrypt the content with a given key using aes algorithm.
	 * 
	 * @param content

	 * 			must contain exactly 32 characters
	 * @return
	 * @throws Exception 
	 */
	public String encrypt(String content) throws Exception {
		getSysKey();
		if (key == null) {
		
			throw new IllegalArgumentException("Key cannot be null!");
		}
		String encrypted = null;
		byte[] keyBytes = key.getBytes(CHARSET);
		if (keyBytes.length != 32 && keyBytes.length != 24
				&& keyBytes.length != 16) {
			throw new IllegalArgumentException(
					"Key length must be 128/192/256 bits!");
		}
		byte[] encryptedBytes = null;
		encryptedBytes = encrypt(Base64.encode(content.getBytes(CHARSET)), keyBytes, initVector);
		encrypted = new String(Hex.encode(encryptedBytes));
		return encrypted;
	}

	/**
	 * Decrypt the content with a given key using aes algorithm.
	 * 
	 * @param content

	 * 			must contain exactly 32 characters
	 * @return
	 * @throws Exception 
	 */
	public String decrypt(String content) throws Exception {
		getSysKey();
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null!");
		}
		String decrypted = null;
		byte[] encryptedContent = Hex.decode(content);
		byte[] keyBytes = key.getBytes(CHARSET);
		byte[] decryptedBytes = null;
		if (keyBytes.length != 32 && keyBytes.length != 24
				&& keyBytes.length != 16) {
			throw new IllegalArgumentException(
					"Key length must be 128/192/256 bits!");
		}
		decryptedBytes = decrypt(encryptedContent, keyBytes, initVector);
		decrypted = new String(Base64.decode(decryptedBytes));
		return decrypted;
	}

	/**
	 * Encrypt data.
	 * 
	 * @param plain
	 * @param key
	 * @param iv
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] plain, byte[] key, byte[] iv) throws Exception {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
				new CBCBlockCipher(new AESFastEngine()));
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key),
				iv);
		aes.init(true, ivAndKey);
		return cipherData(aes, plain);
	}

	/**
	 * Decrypt data.
	 * 
	 * @param cipher
	 * @param key
	 * @param iv
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] cipher, byte[] key, byte[] iv)
			throws Exception {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
				new CBCBlockCipher(new AESFastEngine()));
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key),
				iv);
		aes.init(false, ivAndKey);
		return cipherData(aes, cipher);
	}

	/**
	 * Encrypt or decrypt data.
	 * 
	 * @param cipher
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data)
			throws Exception {
		int minSize = cipher.getOutputSize(data.length);
		byte[] outBuf = new byte[minSize];
		int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
		int length2 = cipher.doFinal(outBuf, length1);
		int actualLength = length1 + length2;
		byte[] result = new byte[actualLength];
		System.arraycopy(outBuf, 0, result, 0, result.length);
		return result;
	}

public static void main(String[] args) throws Exception {
		AESTool aesTool = new AESTool();
		aesTool.setKey("d6379ad6899d16aa");
		String str = "jdbc:mysql://10.29.30.26:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8";
		String encrypted = aesTool.encrypt(str);
		System.out.println("encrypted: " + encrypted);
		System.out.println("encrypted length: " + encrypted.length());
		String decrypted = aesTool.decrypt(encrypted);
		System.out.println("decrypted:" + decrypted);
		System.out.println("decrypted length:" + decrypted.length());
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private void getSysKey () throws Exception {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().indexOf("win")>-1){
			File source = new File("c:/sysKey.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			String str = "";
			if(source.exists()){
				while ((str = in.readLine()) != null) {
					setKey(str);
				}
			}
		}else if(os.toLowerCase().indexOf("mac")>-1){
			File source = new File(File.separator+"Users"+File.separator+"sysKey"+File.separator+"sysKey.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			String str = "";
			if(source.exists()){
				while ((str = in.readLine()) != null) {
					setKey(str);
				}
			}
		}else if(os.toLowerCase().indexOf("linux")>-1){
			File source = new File(File.separator+"opt"+File.separator+"sysKey.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			String str = "";
			if(source.exists()){
				while ((str = in.readLine()) != null) {
					setKey(str);
				}
			}
		}
	}
}