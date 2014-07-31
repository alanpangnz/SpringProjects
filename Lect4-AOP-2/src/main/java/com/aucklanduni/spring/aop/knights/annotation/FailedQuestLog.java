package com.aucklanduni.spring.aop.knights.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class FailedQuestLog {
	private static final Logger _logger = LoggerFactory.getLogger(FailedQuestLog.class);

	@Pointcut("execution(* *.embarkOnQuest(..))")
	public void quest() {
	}
	
	@AfterThrowing(pointcut="quest()", throwing="problem")
	public void logFailure(JoinPoint joinpoint, Throwable problem) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Exception ");
		buffer.append(problem.getClass().getName());
		buffer.append(" (" + problem.getMessage() + ")");
		buffer.append(" thrown by " + joinpoint.getTarget().getClass().getName() );
		buffer.append("." + joinpoint.getSignature().getName());
		
		_logger.info(buffer.toString());
	}
}
