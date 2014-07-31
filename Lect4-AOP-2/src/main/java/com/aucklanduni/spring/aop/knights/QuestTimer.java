package com.aucklanduni.spring.aop.knights;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuestTimer {
	private static final Logger _logger = LoggerFactory.getLogger(QuestTimer.class);
	
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
