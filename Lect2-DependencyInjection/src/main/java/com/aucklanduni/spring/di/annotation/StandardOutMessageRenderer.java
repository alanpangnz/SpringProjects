
package com.aucklanduni.spring.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aucklanduni.spring.di.MessageProvider;
import com.aucklanduni.spring.di.MessageRenderer;


@Service("messageRenderer")
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

	@Autowired
	public void setMessageProvider(MessageProvider provider) {
        _messageProvider = provider;		
	}

	public MessageProvider getMessageProvider() {
        return _messageProvider;
	}
	
}
