package com.aucklanduni.spring.jdbc.persistence;

import java.util.List;

import com.aucklanduni.spring.jdbc.domain.Contact;


public interface ContactDao {

	public List<Contact> findAll();
	
	public List<Contact> findAllWithDetail();
	
	public List<Contact> findByFirstName(String firstName);
	
	public String findFirstNameById(Long id);
	
	public String findLastNameById(Long id);
	
	public void insert(Contact contact);
	
	public void update(Contact contact);
	
	public void delete(Long contactId);
	
	public void insertWithDetail(Contact contact);
	
}
