package application;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

//import sun.misc.BASE64Encoder;

/**
 *
 * @author ahmed
 */
public class AES {

	private SecretKey secretkey;

	public AES() {
		try {
			generateKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Step 1. Generate a DES key using KeyGenerator
	 */

	public void generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		this.setSecretkey(keyGen.generateKey());
	}

	public void saveKey(String secretKeyFileName) throws FileNotFoundException, IOException {
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(secretKeyFileName));
		try {
			oout.writeObject(this.secretkey);
			System.out.println("nice");
		} finally {
			oout.close();
		}
	}

	public void sendKey(Socket sock) throws IOException {
		ObjectOutputStream oout = new ObjectOutputStream(sock.getOutputStream());
		try {
			oout.writeObject(this.secretkey);
			System.out.println("nice");
		} finally {
		}
	}

	public void loadKey(String secretKeyFileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(secretKeyFileName));
		try {
			this.secretkey = (SecretKey) in.readObject();
		} finally {

		}
	}

	public void receiveKey(Socket sock) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
		try {
			this.secretkey = (SecretKey) in.readObject();
		} finally {

		}
	}

	public String encrypt(String strDataToEncrypt) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher desCipher = Cipher.getInstance("AES"); // Must specify the mode explicitly as most JCE providers default
														// to ECB mode!!
		desCipher.init(Cipher.ENCRYPT_MODE, this.getSecretkey());
		byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
		byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);

		return encode(byteCipherText);
	}

	private String encode(byte[] byteCipherText) {
		// Encodes a given byte[]
		return Base64.getEncoder().encodeToString(byteCipherText);
	}

	public String decrypt(String strCipherText) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher desCipher = Cipher.getInstance("AES"); // Must specify the mode explicitly as most JCE providers default
														// to ECB mode!!
		desCipher.init(Cipher.DECRYPT_MODE, this.getSecretkey());
		byte[] decodedCipherText = decode(strCipherText);
		byte[] byteDecryptedText = desCipher.doFinal(decodedCipherText);
		return new String(byteDecryptedText);
	}

	private byte[] decode(String stringCipherText) {
// Decodes a given String
		System.out.println(stringCipherText);
		return Base64.getDecoder().decode(stringCipherText);
	}

	/**
	 * @return the secretkey
	 */
	public SecretKey getSecretkey() {
		return secretkey;
	}

	/**
	 * @param secretkey the secretkey to set
	 */
	public void setSecretkey(SecretKey secretkey) {
		this.secretkey = secretkey;
	}
}
