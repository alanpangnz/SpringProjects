package com.aucklanduni.spring.labs.basicauthenticator;

public class AccessManager {
	private SecurityManager _dependancy;
	public void setDependancy(SecurityManager dependancy){
		_dependancy = dependancy;
	}
	public void checkAuthentication() throws Exception {
		if (_dependancy == null) 
			throw new Exception();
	}
}