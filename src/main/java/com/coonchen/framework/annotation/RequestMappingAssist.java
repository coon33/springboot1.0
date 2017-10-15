package com.coonchen.framework.annotation;

import java.lang.annotation.*;


/**
 * 
* @ClassName: RequestMappingAssist 
* @Description: 验证注解
* @author cw
* @date 2017年8月1日 上午8:11:12 
*
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMappingAssist {
	ValidatorAnnotation[] value();
}

