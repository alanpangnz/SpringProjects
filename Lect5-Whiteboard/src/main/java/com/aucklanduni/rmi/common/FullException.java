package com.aucklanduni.rmi.common;

/**
 * Class of exception thrown by an implementation of the ShapeFactory 
 * interface when a ShapeFactory is unable to create an additional Shape
 * instance. Note that class Exception implements Serializable, so instances of
 * any subclass of Exception are Serializable.  
 */
public class FullException extends Exception {

	/**
	 * Creates a FullException object without an associated message.
	 */
	public FullException() {
		super();
	}
	
	/**
	 * Creates a FullException object with a given message.
	 */
	public FullException(String message) {
		super(message);
	}
}
