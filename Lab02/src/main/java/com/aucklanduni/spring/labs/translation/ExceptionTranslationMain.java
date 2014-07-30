package com.aucklanduni.spring.labs.translation;

import org.springframework.aop.framework.ProxyFactory;

public class ExceptionTranslationMain {
	public static void main(String[] args) {
		Target target = new DefaultTarget();
		
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);
		factory.setInterfaces(new Class[] {Target.class});
		Target proxy = (Target)factory.getProxy();
		
		try {
			proxy.generateAnError();
		} catch(CustomUncheckedException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
