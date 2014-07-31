
package com.aucklanduni.spring.di.annotation;

import org.springframework.stereotype.Service;

import com.aucklanduni.spring.di.MessageProvider;


//@Service("messageProvider")
public class HelloWorldMessageProvider implements MessageProvider {

	public String getMessage() {
		
		return "Hello World!";
	}
	
}
