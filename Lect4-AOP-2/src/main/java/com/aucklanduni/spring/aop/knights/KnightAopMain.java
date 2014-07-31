package com.aucklanduni.spring.aop.knights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class KnightAopMain {
	private static final Logger _logger = LoggerFactory.getLogger(KnightAopMain.class);
	
  public static void main(String[] args) {
    ApplicationContext context = 
        new ClassPathXmlApplicationContext("knights-aop.xml");
    
    Knight knight = context.getBean("knight", Knight.class);
    
    try {
    	knight.embarkOnQuest();
    } catch(QuestException e) {
    	_logger.info("Caught QuestException: " + e.getMessage());
    }
  }
}
