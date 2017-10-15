package com.coonchen.framework.exception;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coonchen.framework.log.LogFactory;

@ControllerAdvice  
public class GlobalExceptionHandler {
	private Logger logger = LogFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String,Object> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("success", false);
    	map.put("msg", e.getMessage());
    	map.put("url", req.getRequestURL().toString());
    	map.put("code", 500);
    	logger.error(e.getMessage()+"---from url："+req.getRequestURL().toString());
    	return map;
    }
}
