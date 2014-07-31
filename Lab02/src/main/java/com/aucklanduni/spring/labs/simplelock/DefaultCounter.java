package com.aucklanduni.spring.labs.simplelock;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DefaultCounter implements Counter, MethodInterceptor {
	private int _count;

	@Override
	public synchronized void increment() {
		int current = _count;
		int next = current + 1;
		_count = next;
	}
	
	@Override
	public int value() {
		return _count;
	}

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
