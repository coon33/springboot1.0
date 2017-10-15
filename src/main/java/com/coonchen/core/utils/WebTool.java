package com.coonchen.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebTool{
	
	public static String formatDateMinute(Integer time) {
		return formatDate(time,"yyyy/MM/dd HH:mm");
	}
	
	public static String formatDate(int time,String format) {
		Date date = new Date();
		date.setTime(time*1000L);
		DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return dateFormat.format(date);
	}
}