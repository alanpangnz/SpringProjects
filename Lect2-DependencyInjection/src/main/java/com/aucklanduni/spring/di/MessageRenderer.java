package com.aucklanduni.spring.di;

public interface MessageRenderer {

	public void render();
	
	public void setMessageProvider(MessageProvider provider);
	
	public MessageProvider getMessageProvider();
	
}
