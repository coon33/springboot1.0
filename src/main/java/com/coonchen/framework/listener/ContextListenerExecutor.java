package com.coonchen.framework.listener;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.core.OrderComparator;
import org.springframework.core.annotation.Order;

import com.coonchen.framework.utils.OrderData;
import com.coonchen.framework.utils.SpringUtil;


public class ContextListenerExecutor {
	private static volatile boolean order=false;
	private static List<OrderData<ContextListenerInterface>> lstListener;
	
	public static synchronized void initListener(ServletContext servletContext){
		String[] listener = SpringUtil.getContext().getBeanNamesForType(ContextListenerInterface.class);
		if(listener!=null){
			if(lstListener==null) lstListener = new ArrayList<OrderData<ContextListenerInterface>>();
			for(String handlerName : listener){
				ContextListenerInterface webContextListener = (ContextListenerInterface)SpringUtil.getBean(handlerName);
				OrderData<ContextListenerInterface> orderData = new OrderData<ContextListenerInterface>();
				orderData.setData(webContextListener);
				Order order = webContextListener.getClass().getAnnotation(Order.class);
				if(order!=null) orderData.setOrder(order.value());
				lstListener.add(orderData);
			}
			
			getWebContextListener();
			
			for(OrderData<ContextListenerInterface> orderData : lstListener){
				orderData.getData().contextInitialized(servletContext);
			}
		}
	}
	
	public static void getWebContextListener(){
		if(!order){
			OrderComparator.sort(lstListener);
			order = true;
		}
	}
	
	public static synchronized void destroyedListener(ServletContext servletContext){
		if(lstListener!=null){
			getWebContextListener();
			for(OrderData<ContextListenerInterface> orderData : lstListener){
				orderData.getData().contextDestroyed(servletContext);
			}
		}
	}
}
