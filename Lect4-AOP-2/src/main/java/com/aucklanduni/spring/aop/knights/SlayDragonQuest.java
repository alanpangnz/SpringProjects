package com.aucklanduni.spring.aop.knights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlayDragonQuest implements Quest {
	private static final Logger _logger = LoggerFactory.getLogger(SlayDragonQuest.class);

  public void embark() throws QuestException {
    _logger.info("Slaying Dragon!");
  }
}
