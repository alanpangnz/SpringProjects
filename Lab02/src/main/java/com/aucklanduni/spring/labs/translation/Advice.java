package com.aucklanduni.spring.labs.translation;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class Advice implements ThrowsAdvice {
	
	public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
		
		throw new CustomUncheckedException(method, args, target, e);
	}

}
