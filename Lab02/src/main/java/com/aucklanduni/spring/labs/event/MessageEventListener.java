package com.aucklanduni.spring.labs.event;

import org.springframework.context.ApplicationListener;

public class MessageEventListener implements ApplicationListener<MessageEvent> {

	public void onApplicationEvent(MessageEvent event) {
		MessageEvent msgEvent = (MessageEvent)event;
		System.out.println("Received: " + msgEvent.getMessage());
	}
}
