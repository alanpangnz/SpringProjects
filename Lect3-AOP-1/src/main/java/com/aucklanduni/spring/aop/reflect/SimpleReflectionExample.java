package com.aucklanduni.spring.aop.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleReflectionExample {
	public static void main(String[] args) {
		try {
			Class stringClass = Class.forName("java.lang.String");
			
			// Instantiate the class using the default constructor.
			String emptyString = (String)stringClass.newInstance();
			
			// Instantiate the class using String's one-argument constructor.
			Constructor cons = stringClass.getConstructor(String.class);
			String arg = "Hello, World!";
			String string = (String)cons.newInstance(arg);
			invokeMethods(string);
			
		} catch (ClassNotFoundException e) {
			// Thrown by Class.forName() if the specified class doesn't exist.
			e.printStackTrace();
		} catch (InstantiationException e) {
			// Thrown by newInstance() if the class cannot be instantiated (e.g. it's an
			// abstract class).
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// Thrown by newInstance if the constructor being called is hidden (e.g.
			// declared private).
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// Thrown by getConstructor() if the class doesn't provide a constructor
			// with the specified arguments.
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// Thrown by newInstance() if the wrong kind of argument is supplied.
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// An InvocationTargetException wraps any exception thrown by an invoked
			// method or constructor.
			e.printStackTrace();
		} 
		
		
	}
	
	private static void invokeMethods(Object object) {
		try {
			Class clazz = object.getClass();
			
			// Attempt to call length() on object.
			Method lengthMethod = clazz.getMethod("length", null);
			int length = (Integer)lengthMethod.invoke(object, null);
			System.out.println("Length: " + length);
		
			// Attempt to call charAt() on object.
			Method charAtMethod = clazz.getMethod("charAt", int.class);
			int arg = 4;
			char charAt = (Character)charAtMethod.invoke(object, arg);
			System.out.println("Char at position " + arg + ": " + charAt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
