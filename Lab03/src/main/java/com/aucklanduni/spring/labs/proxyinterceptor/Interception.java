package com.aucklanduni.spring.labs.proxyinterceptor;

public class Interception {
	  public static void main(String[] args) {
		
		ProxyClass target = new ProxyClass();
		
		method1(target);
		
		
	  }
	  static void method1(ProxyClass target) {
		  System.out.println("intercept and touchdown");
		  target.goodbye();
		  
	  }
	  
	  

}
