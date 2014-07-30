package com.aucklanduni.spring.labs.basicauthenticator;

import org.springframework.stereotype.Component;

@Component("securityManager")
public class SecurityManager {

	private UserInfo _loggedOnUser;
	
	public void login(String username, String password) {
		_loggedOnUser = new UserInfo(username, password);
	}
	
	public void logout() {
		_loggedOnUser = null;
	}
	
	public UserInfo getLoggedOnUser() {
		return _loggedOnUser;
	}
}
