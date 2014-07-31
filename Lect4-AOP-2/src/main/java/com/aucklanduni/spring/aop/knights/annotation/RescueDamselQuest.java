package com.aucklanduni.spring.aop.knights.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RescueDamselQuest implements Quest {
	private static final Logger _logger = LoggerFactory
			.getLogger(RescueDamselQuest.class);

	public void embark() throws QuestException {
		_logger.info("Rescuing damsel in distress");
	}
}
