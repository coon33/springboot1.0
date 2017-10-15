package com.coonchen.framework.utils;


import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringUtil {

	private static ApplicationContext applicationContext = null;

	public static void initContext(ServletContext sc){
		if(applicationContext==null)
			applicationContext =WebApplicationContextUtils.getWebApplicationContext(sc);
	}
	
	// 获取applicationContext
	public static ApplicationContext getContext() {
		return applicationContext;
	}

	// 通过name获取 Bean.
	public static Object getBean(String name) {
		return getContext().getBean(name);
	}

	// 通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	// 通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name, Class<T> clazz) {
		return getContext().getBean(name, clazz);
	}
}
