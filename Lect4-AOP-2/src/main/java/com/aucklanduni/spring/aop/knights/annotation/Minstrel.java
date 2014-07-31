package com.aucklanduni.spring.aop.knights.annotation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class Minstrel {
	private static final Logger _logger = LoggerFactory.getLogger(Minstrel.class);
	
	@Pointcut("execution(* *.embarkOnQuest(..))")
	public void quest() {
	}
	
	@Before("quest()")
	public void singBeforeQuest() {
		_logger.info("Fa la la; The knight is so brave!");
	}

	@AfterReturning("quest()")
	public void singAfterQuest() {
		_logger.info("Tee hee he; The brave knight did embark on a quest!");
	}
}
