package com.aucklanduni.spring.labs.basicauthenticator;

import org.springframework.stereotype.Component;

@Component("secureBean")
public class DefaultSecureBean implements SecureBean {

	@Override
	public void writeSecureMessage() {
		System.out.println("Every time I learn something new, "
				+ "it pushes some old stuff out of my brain");	
	}
	
}
