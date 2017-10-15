package com.coonchen.framework.utils.tool;


import javax.crypto.Cipher;

public class SecurityPlus {
	private static SecurityPlus securityplus = null;

	public static synchronized SecurityPlus getInstance(String key) throws Exception {
		if (securityplus == null)
			securityplus = new SecurityPlus(key);
		return securityplus;
	}

	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;

		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];

			while (intTmp < 0) {
				intTmp += 256;
			}

			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i += 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
		}
		return arrOut;
	}

	private SecurityPlus(String strKey) throws Exception {
		java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
		java.security.Key key = getKey(strKey.getBytes());
		this.encryptCipher = Cipher.getInstance("DES");
		this.encryptCipher.init(1, key);
		this.decryptCipher = Cipher.getInstance("DES");
		this.decryptCipher.init(2, key);
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return this.encryptCipher.doFinal(arrB);
	}

	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return this.decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private java.security.Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];

		for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); i++) {
			arrB[i] = arrBTmp[i];
		}

		java.security.Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	public static void main(String[] args) {
		try {
			String test = "晴空万里,微风拂来.";

			SecurityPlus des = new SecurityPlus("#sir_omg!@_@key");
			long time = System.currentTimeMillis();

			System.out.println("加密后的字符：" + des.encrypt(test));
			System.out.println(
					"解密后的字符：" + des.decrypt("00bc1887c205608a450c02c372f56d81e74bdb519711e48e107cbdd887ac72e0"));
			long time1 = System.currentTimeMillis();
			System.out.println(time1 - time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
