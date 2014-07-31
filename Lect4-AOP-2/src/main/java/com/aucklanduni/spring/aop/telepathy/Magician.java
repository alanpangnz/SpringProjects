package com.aucklanduni.spring.aop.telepathy;

public class Magician implements MindReader {
	
	public void readThoughts(String thoughts) {
		System.out.println("Just read these thoughts: " + thoughts);
	}
}
