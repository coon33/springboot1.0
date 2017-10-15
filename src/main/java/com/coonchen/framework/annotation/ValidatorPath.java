package com.coonchen.framework.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorPath {
	String path() default "";
}

