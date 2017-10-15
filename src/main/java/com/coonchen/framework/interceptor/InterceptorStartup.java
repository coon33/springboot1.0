package com.coonchen.framework.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.coonchen.framework.validator.ValidatorInterceptor;



public class InterceptorStartup extends InterceptorExecutor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String projectPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		request.setAttribute("basePath",projectPath);
		
		if(!super.preHandle(request, response, handler)) return false;
		return ValidatorInterceptor.validator(request,response,handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	public void super_postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		super.postHandle(arg0, arg1, arg2, arg3);
	}
	
	public boolean super_preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception{
		return super.preHandle(arg0, arg1, arg2);
	}
	
	
	public enum ViewType{
		json,
		view;
	}
}
