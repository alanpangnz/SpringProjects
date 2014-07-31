package com.aucklanduni.spring.aop.telepathy;

public class Volunteer implements Thinker {
	private String _thoughts;
	
	public void thinkOfSomething(String thoughts) {
		_thoughts = thoughts;
	}

}
