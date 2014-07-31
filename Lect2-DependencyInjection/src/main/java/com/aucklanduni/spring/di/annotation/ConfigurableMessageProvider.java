package com.aucklanduni.spring.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aucklanduni.spring.di.MessageProvider;

@Service("messageProvider")
public class ConfigurableMessageProvider implements MessageProvider {
	
	private String _message;

	@Autowired
	public ConfigurableMessageProvider(String message) {
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}

}
