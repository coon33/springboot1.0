package com.allwell.erp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.coonchen.framework.interceptor.InterceptorInterface;

@Component
@Order(1)
public class SystemInterceptor implements InterceptorInterface{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String projectPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		request.setAttribute("imagePath",projectPath+"/resource/");
		return true;
	}

}
