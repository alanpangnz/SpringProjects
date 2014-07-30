package com.aucklanduni.spring.labs.translation;

public class DefaultTarget implements Target {

	@Override
	public void generateAnError() {
		// Cause a NullPointerException.
		Object object = null;
		object.toString();
	}

}
