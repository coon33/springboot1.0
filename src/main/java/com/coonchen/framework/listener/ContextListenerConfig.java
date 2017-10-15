package com.coonchen.framework.listener;



import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoader;

import com.coonchen.framework.interceptor.InterceptorProvider;
import com.coonchen.framework.utils.SpringUtil;



@WebListener
public class ContextListenerConfig  extends ContextLoader implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		SpringUtil.initContext(servletContext);
		//VelocityTemplateManager.getInstance().init(servletContext);
		ContextListenerExecutor.initListener(servletContext);
		InterceptorProvider.initInterceptor(servletContext);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		ContextListenerExecutor.destroyedListener(servletContext);
	}


}
