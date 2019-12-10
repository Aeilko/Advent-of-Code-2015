package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public static byte[] toMD5(String input){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] inputBytes = input.getBytes("UTF-8");
			return md.digest(inputBytes);
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.err.println("utils.Crypto.toMD5: Can't find MD5");
			e.printStackTrace();
			return null;
		}
	}
}
