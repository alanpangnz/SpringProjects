package com.aucklanduni.spring.di.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aucklanduni.spring.di.MessageRenderer;

public class HelloWorldMain {

	public static void main(String[] args) {

		// Initialize Spring application context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/app-context-annotation.xml");
		
		MessageRenderer mr = ctx.getBean("messageRenderer", MessageRenderer.class);
		mr.render();
		
	}

}
