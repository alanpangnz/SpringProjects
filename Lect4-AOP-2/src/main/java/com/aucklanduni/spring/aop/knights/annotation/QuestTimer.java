package com.aucklanduni.spring.aop.knights.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class QuestTimer {
	private static Logger _logger = LoggerFactory.getLogger(QuestTimer.class);

	@Pointcut("execution(* *.embarkOnQuest(..))")
	public void quest() {
	}

	@Around("quest()")
	public void timeQuest(ProceedingJoinPoint joinpoint) {
		_logger.info("The quest is about to start!");
		long start = System.currentTimeMillis();

		try {
			joinpoint.proceed();
		} catch (Throwable t) {
			t.printStackTrace();
		}

		long end = System.currentTimeMillis();
		_logger.info("The quest took " + (end - start) + "ms.");
	}
}
