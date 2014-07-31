package com.aucklanduni.spring.aop.telepathy.annotation;

import org.springframework.stereotype.Component;

@Component("thinker")
public class Volunteer implements Thinker {
	private String _thoughts;
	
	public Volunteer() {
		System.out.println("Creating volunteer");
	}
	
	public void thinkOfSomething(String thoughts) {
		_thoughts = thoughts;
	}

}
