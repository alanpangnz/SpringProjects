package com.aucklanduni.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldMain {

	public static void main(String[] args) {

		// Initialize Spring application context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		
		MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
		mr.render();
		
	}

}
