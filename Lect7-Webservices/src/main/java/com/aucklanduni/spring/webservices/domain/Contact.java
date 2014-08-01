
package com.aucklanduni.spring.webservices.domain;


import java.io.Serializable;

import org.joda.time.DateTime;


public class Contact implements Serializable {

	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private DateTime birthDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public DateTime getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}	
	
	public String toString() {		
		return "Contact - Id: " + id + ", First name: " + firstName 
				+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}		
	
}
