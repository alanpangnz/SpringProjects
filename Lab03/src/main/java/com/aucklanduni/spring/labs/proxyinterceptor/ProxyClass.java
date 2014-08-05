package com.aucklanduni.spring.labs.proxyinterceptor;

public class ProxyClass implements Interface1, Interface2 {
	
	@Override
	public void goodbye() {
		System.out.println("Goodbye");
		
	}

	@Override
	public void hello() {
		System.out.println("Hello");
		
	}

}
