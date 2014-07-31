package com.aucklanduni.spring.aop.knights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Minstrel {
	private static final Logger _logger = LoggerFactory
			.getLogger(Minstrel.class);

	public void singBeforeQuest() {
		_logger.info("Fa la la; The knight is so brave!");
	}

	public void singAfterQuest() {
		_logger.info("Tee hee he; The brave knight did embark on a quest!");
	}
}
