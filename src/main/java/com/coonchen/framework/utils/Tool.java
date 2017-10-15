package com.coonchen.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Tool {
	
	private static Pattern patt;
	
	static {
		patt = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	}
	
	public static long getNumber(long number) {
		return (long) Math.pow(2, number);
	}

	public static String getCharacterAndNumber(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val = val + (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val = val + String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	public static String get_onlineip(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		String forward_header = request.getHeader("X-Forwarded-For");
		if ((forward_header != null) && (!forward_header.trim().equals(""))) {
			String[] forward_headers = forward_header.split(",");
			String[] arrayOfString1;
			int j = (arrayOfString1 = forward_headers).length;
			for (int i = 0; i < j; i++) {
				String s = arrayOfString1[i];
				if (!s.trim().equalsIgnoreCase("unknown")) {
					return s;
				}
			}
		}
		if (StringUtil.isEmpty(ip))
			return request.getRemoteAddr();
		Matcher mat = patt.matcher(ip);
		return mat.matches() ? ip : request.getRemoteAddr();
	}

	public static String getcurrentPath() {
		try {
			return URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource("").getPath(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ");
	}

	public static String getResourcePath(String properties){
		try {
			return URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource(properties).getPath(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Thread.currentThread().getContextClassLoader().getResource(properties).getPath().replace("%20", " ");
	}

	public static void main(String[] args) {
		System.out.println(Tool.getNumber(3));
	}
}
