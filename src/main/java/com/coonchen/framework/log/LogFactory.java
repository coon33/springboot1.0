package com.coonchen.framework.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {
	public static Logger getLogger(Class<?> target){
		return LoggerFactory.getLogger(target);
	}
}
