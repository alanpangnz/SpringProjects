package com.aucklanduni.spring.aop.knights.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlayDragonQuest implements Quest {
	private static final Logger _loggerFactory = LoggerFactory.getLogger(SlayDragonQuest.class);

  public void embark() throws QuestException {
    _loggerFactory.info("Slaying Dragon!");
  }

}
