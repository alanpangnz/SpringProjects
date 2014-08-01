package com.aucklanduni.spring.webservices.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.aucklanduni.spring.webservices.domain.Contact;
import com.aucklanduni.spring.webservices.domain.Contacts;

@Service("contactService")
public class ContactServiceMockImplementation implements ContactService {
	
	private Contacts _contacts;
	
	public ContactServiceMockImplementation() {
		Contact contact1 = new Contact();
		contact1.setId(1L);
		contact1.setVersion(1);
		contact1.setFirstName("Clint");
		contact1.setLastName("Eastwood");
		contact1.setBirthDate(new DateTime(1930, 5, 31, 0, 0));
		
		Contact contact2 = new Contact();
		contact2.setId(1L);
		contact2.setVersion(1);
		contact2.setFirstName("Robert");
		contact2.setLastName("Redford");
		contact2.setBirthDate(new DateTime(1936, 8, 18, 0, 0));
		
		Contact contact3 = new Contact();
		contact3.setId(1L);
		contact3.setVersion(1);
		contact3.setFirstName("Michael");
		contact3.setLastName("Caine");
		contact3.setBirthDate(new DateTime(1933, 3, 14, 0, 0));
		
		List<Contact> contactList = new ArrayList<Contact>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactList.add(contact3);
		
		_contacts = new Contacts(contactList);
	}

	@Override
	public List<Contact> findAll() {
		return _contacts.getContacts();
	}

	@Override
	public List<Contact> findByFirstName(String firstName) {
		List<Contact> results = new ArrayList<Contact>();
		
		for(Contact contact : _contacts.getContacts()) {
			if(contact.getFirstName().equals(firstName)) {
				results.add(contact);
			}
		}
		return results;
	}

	@Override
	public Contact findById(Long id) {
		Contact result = null;
		
		for(Contact contact : _contacts.getContacts()) {
			if(contact.getId() == id) {
				result = contact;
				break;
			}
		}
		return result;
	}

	@Override
	public Contact save(Contact contact) {
		return contact;
	}

	@Override
	public void delete(Contact contact) {
		// TODO Auto-generated method stub
	}
	
}
