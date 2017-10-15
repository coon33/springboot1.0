package com.coonchen.framework.interceptor;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.coonchen.framework.utils.OrderData;



public abstract class InterceptorExecutor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		int index = -1;
		List<OrderData<InterceptorInterface>> lstWebInterceptor = InterceptorProvider.getLstHandlerInterceptorAdapter();
		if(lstWebInterceptor!=null){
			for(OrderData<InterceptorInterface> orderData : lstWebInterceptor){
				if(!orderData.getData().preHandle(request, response, handler)){
					for(int i = index;i>=0;i--){
						lstWebInterceptor.get(i).getData().afterCompletion(request, response, handler, null);
					}
					return false;
				}
				index++;
			}
		}
		return true; 
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		List<OrderData<InterceptorInterface>> lstWebInterceptor = InterceptorProvider.getLstHandlerInterceptorAdapter();
		if(lstWebInterceptor!=null){
			for(int i = lstWebInterceptor.size();i>0;i--){
				lstWebInterceptor.get(i-1).getData().postHandle(request, response, handler, modelAndView);
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		List<OrderData<InterceptorInterface>> handlerInterceptors = InterceptorProvider.getLstHandlerInterceptorAdapter();
		if(handlerInterceptors!=null){
			for(int i = handlerInterceptors.size();i>0;i--){
				handlerInterceptors.get(i-1).getData().afterCompletion(request,response,handler,ex);
			}
		}
	}

}
