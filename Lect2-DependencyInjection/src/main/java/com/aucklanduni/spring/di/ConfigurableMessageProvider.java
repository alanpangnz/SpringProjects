package com.aucklanduni.spring.di;

public class ConfigurableMessageProvider implements MessageProvider {
	
	private String _message;

	public ConfigurableMessageProvider(String message) {
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}

}
