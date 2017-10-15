package com.coonchen.framework.validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coonchen.framework.annotation.RequestMappingAssist;
import com.coonchen.framework.annotation.ValidatorAnnotation;
import com.coonchen.framework.annotation.ValidatorPath;
import com.coonchen.framework.utils.JsonProcessUtil;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.coonchen.framework.log.LogFactory;
import com.coonchen.framework.utils.StringUtil;

public class ValidatorInterceptor {
	public static boolean validator(HttpServletRequest request, HttpServletResponse response,Object handler) {
		boolean dovalidate=handler instanceof HandlerMethod;
		if(dovalidate){
			HandlerMethod hm = (HandlerMethod)handler;
			RequestMappingAssist ra = hm.getMethodAnnotation(RequestMappingAssist.class);
			if(ra==null || (ra!=null && ra.value()==null))
				return true;
			ValidatorAnnotation[] validatorAnnotations = ra.value();
			//String clzPackage = hm.getBean().getClass().getPackage().getName();
			Map map = getParam(request);
			Map<String,Object> mapError = new HashMap<String,Object>();
			for(ValidatorAnnotation validatorAnnotation : validatorAnnotations){
				if(map.get(validatorAnnotation.name())==null){
					mapError.put(validatorAnnotation.name(),"The parameter "+validatorAnnotation.name()+" was not found");
				}else{
					String error = ValidatorExecutor.validator(map.get(validatorAnnotation.name()),validatorAnnotation.explain(),validatorAnnotation.rule());
					if(error!=null) mapError.put(validatorAnnotation.name(),error);
				}
			}
			if(!mapError.isEmpty() && mapError.size()>0){
				ResponseBody rb = hm.getMethodAnnotation(ResponseBody.class);
				if(rb==null) {
					ValidatorPath vPath = hm.getMethodAnnotation(ValidatorPath.class);
					if(vPath==null || StringUtil.isEmpty(vPath.path())) {
						String uri = request.getRequestURI();
						LogFactory.getLogger(ValidatorInterceptor.class).error("please ["+uri+"] set ValidatorPath input!");
					}
					try {
						request.setAttribute("valid_error",mapError);
						request.getRequestDispatcher(vPath.path()).forward(request,response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return false;
				}else{
					response.setContentType("application/json;charset=UTF-8");
					PrintWriter out = null;
					try {
						out = response.getWriter();
						Map<String,Object> mapErrorJson = new HashMap<String,Object>();
						mapErrorJson.put("success",false);
						mapErrorJson.put("valid_error",mapError);
						out.print(JsonProcessUtil.beanToJson(mapErrorJson));
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return false;
				}
			}
		}
		return true;
	}


	private static Map getParam(HttpServletRequest request){
		Map map = request.getParameterMap();
		return map;
	}
}
