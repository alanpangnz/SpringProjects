package com.aucklanduni.spring.labs.simplelock;

public class DefaultCounter implements Counter {
	private int _count;

	@Override
	public void increment() {
		int current = _count;
		int next = current + 1;
		_count = next;
	}
	
	@Override
	public int value() {
		return _count;
	}

}
