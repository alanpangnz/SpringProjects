package com.aucklanduni.spring.jdbc;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.aucklanduni.spring.jdbc.domain.Contact;
import com.aucklanduni.spring.jdbc.domain.ContactTelDetail;
import com.aucklanduni.spring.jdbc.persistence.ContactDao;

public class JdbcContactDaoMain {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context-xml.xml");
		ctx.refresh();
		
		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
		
		// Find first name by id
		System.out.println("First name for contact id 1 is: " + contactDao.findFirstNameById(1l));
	
		// Find last name by id
		System.out.println("Last name for contact id 1 is: " + contactDao.findLastNameById(1l));
				
		// Find and list all contacts
		List<Contact> contacts = contactDao.findAll();	
		for (Contact contact: contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
		
		// Find and list all contacts with details
		List<Contact> contactsWithDetail = contactDao.findAllWithDetail();	
		for (Contact contact: contactsWithDetail) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}		
	}
}
