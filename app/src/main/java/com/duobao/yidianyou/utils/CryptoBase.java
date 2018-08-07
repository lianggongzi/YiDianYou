
package com.duobao.yidianyou.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

abstract class CryptoBase {

	private SecretKey secretKey;
	private IvParameterSpec iv;

	protected CryptoBase(String desKeyStr) {
		try {
			DESKeySpec keySpec = new DESKeySpec(desKeyStr.getBytes("UTF-8"));
			secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
			 iv = new IvParameterSpec(desKeyStr.getBytes("UTF-8")); 
		}catch(Exception e) {
		}
	}
	protected String encrypt(String srcStr) {
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,iv);
			byte[] bs = cipher.doFinal(srcStr.getBytes("UTF-8"));
			return byte2Hex(bs);
		}catch(Exception e) {
			return null;
		}
	}
	protected String decrypt(String srcStr) {
		try{
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
			cipher.init(Cipher.DECRYPT_MODE, secretKey,iv);
			byte[] bs = cipher.doFinal(hex2Byte(srcStr.getBytes("UTF-8")));
			return new String(bs,"UTF-8");
		}catch(Exception e){
			return null;
		}
	}
	private  String byte2Hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for(int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if(stmp.length() == 1) {
				hs = hs + "0" + stmp;
			}else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	private  byte[] hex2Byte(byte[] b) {

		if(b.length % 2 != 0) {
			return null;
		}
		byte[] b2 = new byte[b.length / 2];
		for(int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte)Integer.parseInt(item, 16);
		}
		return b2;
	}
}
