package com.aucklanduni.spring.labs.task1;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		ApplicationContext context = new ClassPathXmlApplicationContext("pointcut.xml"); 
		Interface1 c = context.getBean("target", Interface1.class); //Have to have context set to the interface that has the method rather than the class that implements it
		c.hello();
	}
}
