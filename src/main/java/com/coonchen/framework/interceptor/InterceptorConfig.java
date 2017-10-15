package com.coonchen.framework.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.coonchen.framework.utils.PropertiesUtil;
import com.coonchen.framework.utils.StringUtil;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String pattern = PropertiesUtil.getPropertiesValueByName("system.url.pattern");
		if(StringUtil.isEmpty(pattern)) pattern = "/**";
		registry.addInterceptor(new InterceptorStartup()).addPathPatterns(pattern.split(","));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resource/**").addResourceLocations("resource/");
		super.addResourceHandlers(registry);
	}
}
