package com.aucklanduni.spring.labs.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
	private String _message;
	
	public MessageEvent(Object source, String message) {
		super(source);
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}
}
