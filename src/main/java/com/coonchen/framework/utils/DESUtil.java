package com.coonchen.framework.utils;


import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

import org.springframework.util.StringUtils;




/**
 * 
 * 加密工具类
 * 
 * @author cw
 * 
 */
public class DESUtil {

	/**
	 * 加密方法
	 * 
	 * @param keyData
	 *            密钥(用户名)
	 * @param str
	 *            要加密的字符串(密码)
	 * @return
	 * @throws Exception
	 * @author sigangjun
	 */
	public static String encrypt(String keyData, String str) throws Exception {
		if (!StringUtils.isEmpty(str)) {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			Key key = getKey(keyData.getBytes("utf-8"));
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return byteArr2HexStr(cipher.doFinal(str.getBytes("utf-8")));
		} else {
			return str;
		}
	}

	/**
	 * 解密方法
	 * 
	 * @param keyData密钥
	 * @param str要解密的字符串
	 * @return
	 * @throws Exception
	 * @author sigangjun
	 */
	public static String decrypt(String keyData, String str) throws Exception {
		if (!StringUtils.isEmpty(str)) {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			Key key = getKey(keyData.getBytes("utf-8"));
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(hexStr2ByteArr(str)), "utf-8");
		} else {
			return str;
		}
	}

	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append(0);
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	private static byte[] hexStr2ByteArr(String strln) throws Exception {
		byte[] arrB = strln.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	private static Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	public static void main(String[] args) {
		String username = "xxxx@qq.com";
		String password = "sigangjunsigangjunsigangjun";
		try {
			System.out.println("加密之前的字符串为：" + password);
			System.out.println("密钥为：" + username);
			String b = DESUtil.encrypt(username, password);
			System.out.println("加密之后的字符串为：" + b);
			System.out.println("加密之后的字符串长度为：" + b.length());
			password = DESUtil.decrypt(username, "6b98381edf03b91cbb5f176bd163f7ba");
			System.out.println("解密之后的字符串为：" + password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}