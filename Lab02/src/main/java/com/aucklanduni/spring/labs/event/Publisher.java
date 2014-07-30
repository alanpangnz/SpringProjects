package com.aucklanduni.spring.labs.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationContextAware {

	private ApplicationContext _context;
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:events.xml");
		
		Publisher publisher = (Publisher)context.getBean("publisher");
		publisher.publish("The Godfather is the greatest movie of all time");
		publisher.publish("Lawrence of Arabia is a classic movie");
		
	}
	
	public void publish(String message) {
		_context.publishEvent(new MessageEvent(this, message));
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		_context = context;
		
	}
}
