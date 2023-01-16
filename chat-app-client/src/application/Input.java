package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Input extends BufferedReader {

	private AES aes;

	public Input(Reader in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public void setAES(AES aes) {
		// TODO Auto-generated method stub
		this.aes = aes;
	}

	public String readLine() throws IOException {
		if (aes != null) {
			try {
				return aes.decrypt(super.readLine());
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return super.readLine();
		}

	}

}
