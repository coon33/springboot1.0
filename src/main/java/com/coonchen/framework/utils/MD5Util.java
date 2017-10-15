package com.coonchen.framework.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	'f' };
	
	// 静态方法，便于作为工具类
	public static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getMD5(byte[] source) {
		String s = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte[] tmp = md.digest();

			char[] str = new char[32];

			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			s = new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		// 测试
		System.out.println(MD5Util.getMd5("hello"));
	}
}
