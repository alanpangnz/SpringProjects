package com.aucklanduni.spring.aop.telepathy.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Magician implements MindReader {
	
	@Pointcut("execution(* com.aucklanduni.spring.aop.telepathy.annotation.Thinker.thinkOfSomething(String)) && args(thoughts)")
	public void thinking(String thoughts) {
	}
	
	@Before("thinking(thoughts)")
	public void readThoughts(String thoughts) {
		System.out.println("Just read these thoughts: " + thoughts);
	}
}
