package com.coonchen.framework.interceptor;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.core.OrderComparator;
import org.springframework.core.annotation.Order;

import com.coonchen.framework.utils.OrderData;
import com.coonchen.framework.utils.SpringUtil;




public class InterceptorProvider {
	private static volatile boolean order=false;
	public static List<OrderData<InterceptorInterface>> lstHandlerInterceptorAdapter;

	public static synchronized void initInterceptor(ServletContext servletContext){
		initInterceptor();
	}
	
	public static synchronized void initInterceptor(){
		String[] handlerNames = SpringUtil.getContext().getBeanNamesForType(InterceptorInterface.class);
		if(handlerNames!=null){
			if(lstHandlerInterceptorAdapter==null) lstHandlerInterceptorAdapter = new ArrayList<OrderData<InterceptorInterface>>();
			for(String handlerName : handlerNames){
				InterceptorInterface handlerInterceptorAdapter = (InterceptorInterface)SpringUtil.getBean(handlerName);
				OrderData<InterceptorInterface> orderData = new OrderData<InterceptorInterface>();
				orderData.setData(handlerInterceptorAdapter);
				Order order = handlerInterceptorAdapter.getClass().getAnnotation(Order.class);
				if(order!=null) orderData.setOrder(order.value());
				lstHandlerInterceptorAdapter.add(orderData);
			}
		}
	}
	
	public static List<OrderData<InterceptorInterface>> getLstHandlerInterceptorAdapter(){
		if(lstHandlerInterceptorAdapter==null) return null;
		if(!order){
			OrderComparator.sort(lstHandlerInterceptorAdapter);
			order = true;
		}
		return lstHandlerInterceptorAdapter;
	}

}
