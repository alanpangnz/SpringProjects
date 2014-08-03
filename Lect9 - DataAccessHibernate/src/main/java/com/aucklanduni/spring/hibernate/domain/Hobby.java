package com.aucklanduni.spring.hibernate.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "hobby")
public class Hobby implements Serializable {

	private String _hobbyId;
	private Set<Contact> _contacts = new HashSet<Contact>();

	public Hobby() {
	}

	public Hobby(String hobbyId) {
		this._hobbyId = hobbyId;
	}

	public Hobby(String hobbyId, Set<Contact> contacts) {
		this._hobbyId = hobbyId;
		this._contacts = contacts;
	}

	@Id
	@Column(name = "HOBBY_ID")
	public String getHobbyId() {
		return this._hobbyId;
	}

	public void setHobbyId(String hobbyId) {
		this._hobbyId = hobbyId;
	}

	@ManyToMany(mappedBy="hobbies")
	public Set<Contact> getContacts() {
		return this._contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this._contacts = contacts;
	}

	public String toString() {
		return "Hobby :" + getHobbyId();
	}
}
