package com.duobao.yidianyou.utils;

public class DesUtils  extends CryptoBase{
   
	private static String key="asde34$.";

	protected DesUtils(String desKeyStr) {
		super(desKeyStr);
	}
	/**
	 * des加密
	 * @param srcString
	 * @return
	 */
	public static String desEncrypt(String srcString){
		DesUtils desutil=new DesUtils(key);
		return desutil.encrypt(srcString);
	}
	/**
	 * des 解密
	 * @param srcString
	 * @return
	 */
	public static String desDecrypt(String srcString){
		DesUtils desutil=new DesUtils(key);
		return desutil.decrypt(srcString);
	}
	
	public static String desDecrypt(String mykey,String srcString){
		DesUtils desutil=new DesUtils(mykey);
		return desutil.decrypt(srcString);
	}
	
	public static String desEncrypt(String mykey,String srcString){
		DesUtils desutil=new DesUtils(mykey);
		return desutil.encrypt(srcString);
	}

}
