package com.coonchen.framework.listener;



import javax.servlet.ServletContext;

public interface ContextListenerInterface {
	public void contextInitialized(ServletContext arg0);
	public void contextDestroyed(ServletContext arg0);
}
