package com.aucklanduni.spring.aop.telepathy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TelepathyMain {
  public static void main(String[] args) {
    ApplicationContext context = 
        new ClassPathXmlApplicationContext("telepathy.xml");
    
    Thinker thinker = context.getBean("thinker", Thinker.class);
    
    thinker.thinkOfSomething("Queen of Hearts");
  }
}
