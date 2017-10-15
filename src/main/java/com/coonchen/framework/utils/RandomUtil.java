package com.coonchen.framework.utils;


import java.util.UUID;

public class RandomUtil {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
