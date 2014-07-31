package com.aucklanduni.spring.di;


public class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider _messageProvider = null;
	
	public void render() {
        if (_messageProvider == null) {
            throw new RuntimeException(
                    "You must set the property messageProvider of class:"
                            + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(_messageProvider.getMessage());	
	}

	public void setMessageProvider(MessageProvider provider) {
        this._messageProvider = provider;		
	}

	public MessageProvider getMessageProvider() {
        return this._messageProvider;
	}
	
}
