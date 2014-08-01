package com.aucklanduni.spring.labs.translation;

import java.lang.reflect.Method;

public class DefaultTarget implements Target {

	@Override
	public void generateAnError() {
		// Cause a NullPointerException.
		Object object = null;
		object.toString();
	}
	

}
