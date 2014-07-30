package com.aucklanduni.spring.labs.basicauthenticator;

public class UserInfo {
	private String _username;
	private String _password;
	
	public UserInfo(String username, String password) {
		_username = username;
		_password = password;
	}
	
	public String getUsername() {
		return _username;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public boolean equals(Object other) {
		boolean equals = false;
		
		if(other instanceof UserInfo) {
			UserInfo otherUserInfo = (UserInfo)other;
			equals = _username.equals(otherUserInfo._username) 
					&& _password.equals(otherUserInfo._password);
		}
		return equals;
	}
}
