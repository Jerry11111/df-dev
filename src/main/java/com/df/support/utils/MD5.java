package com.df.support.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MD5 {
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public static String encrypt(String encryptText){
		
		byte[] encryptData = encryptText.getBytes(DEFAULT_CHARSET);
		
		return formatMD5(digest(encryptData));
	}
	
	public static String encrypt(byte[] encryptData) {
		
		return formatMD5(digest(encryptData));
	}
	
	public static byte[] encryptAsBytes(String encryptText) {
		
		byte[] encryptData = encryptText.getBytes(DEFAULT_CHARSET);
		return digest(encryptData);
	}
	
	public static byte[] encryptAsBytes(byte[] encryptData) {
		
		return digest(encryptData);
	}
	
	private static byte[] digest(byte[] encryptData) {
		
		byte[] data =null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(encryptData);
			data = md.digest();

		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(),t);
		}
		return data;
	}
	
	private static String formatMD5(byte[]data){
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			builder.append(Integer.toHexString(
					(0x000000ff & data[i]) | 0xffffff00).substring(6));
		}
		
		return builder.toString();
	}

}
