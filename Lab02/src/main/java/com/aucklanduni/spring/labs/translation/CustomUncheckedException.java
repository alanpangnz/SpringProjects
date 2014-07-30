package com.aucklanduni.spring.labs.translation;

import java.lang.reflect.Method;

public class CustomUncheckedException extends RuntimeException {
	private Method _method;
	private Object[] _args;
	private Object _target;
	private RuntimeException _cause;
	
	public CustomUncheckedException(Method method, Object[] args, Object target, RuntimeException exception) {
		_method = method;
		_args = args;
		_target = target;
		_cause = exception;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(_cause.getClass().getName() + " caught.\n");
		buffer.append("Thrown by: " + _target.getClass().getCanonicalName() + "." + _method.getName() + "\n");
		
		return buffer.toString();
	}

}
