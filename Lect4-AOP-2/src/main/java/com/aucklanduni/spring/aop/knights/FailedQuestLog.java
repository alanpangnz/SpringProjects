package com.aucklanduni.spring.aop.knights;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FailedQuestLog {
	private static final Logger _logger = LoggerFactory.getLogger(FailedQuestLog.class);
	
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
