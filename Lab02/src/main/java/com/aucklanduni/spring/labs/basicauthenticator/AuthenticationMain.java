package com.aucklanduni.spring.labs.basicauthenticator;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuthenticationMain {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationMain.class);
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:authenticator.xml");
		
		UserInfo user1 = (UserInfo)context.getBean("user1");
		
		SecurityManager mgr = (SecurityManager)context.getBean("mgr");
		mgr.login(user1.getUsername(), user1.getPassword());
		
		SecureBean target = (SecureBean)context.getBean("secureBean");
		
		try {
			target.writeSecureMessage();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}

}
