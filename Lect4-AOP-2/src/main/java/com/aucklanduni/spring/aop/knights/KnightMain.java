package com.aucklanduni.spring.aop.knights;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
  public static void main(String[] args) {
    ApplicationContext context = 
        new ClassPathXmlApplicationContext("knights.xml"); 
    
    Knight knight = context.getBean("knight", Knight.class); 
    
    knight.embarkOnQuest();
  }
}
