package com.aucklanduni.spring.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.aucklanduni.spring.jdbc.domain.Contact;
import com.aucklanduni.spring.jdbc.domain.ContactTelDetail;
import com.aucklanduni.spring.jdbc.persistence.ContactDao;


public class AnnotationJdbcContactDaoMain {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context-annotation.xml");
		ctx.refresh();
		
		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
		
		// Find and list all contacts
		List<Contact> contacts = contactDao.findAll();	
		listContacts(contacts);
		
		// Find and list all contacts
		contacts = contactDao.findAllWithDetail();	
		listContacts(contacts);	
		
		// Find contacts by first name
		contacts = contactDao.findByFirstName("Bruce");
		listContacts(contacts);		
		
		Contact contact;
		
		// Update contact
		contact = new Contact();
		contact.setId(1l);
		contact.setFirstName("Samuel");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date((new GregorianCalendar(1948, 12, 21)).getTime().getTime()));
		contactDao.update(contact);
		contacts = contactDao.findAll();	
		listContacts(contacts);	
		
		// Insert contact
		contact = new Contact();
		contact.setFirstName("Morgan");
		contact.setLastName("Freeman");
		contact.setBirthDate(new Date((new GregorianCalendar(1937, 6, 1)).getTime().getTime()));
		contactDao.insert(contact);
		contacts = contactDao.findAll();	
		listContacts(contacts);		
		
		// Insert contact with details
		contact = new Contact();
		contact.setFirstName("Jack");
		contact.setLastName("Nicholson");
		contact.setBirthDate(new Date((new GregorianCalendar(1937, 4, 22)).getTime().getTime()));
		List<ContactTelDetail> contactTelDetails = new ArrayList<ContactTelDetail>();
		ContactTelDetail contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Home");
		contactTelDetail.setTelNumber("11111111");
		contactTelDetails.add(contactTelDetail);
		contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Mobile");
		contactTelDetail.setTelNumber("22222222");
		contactTelDetails.add(contactTelDetail);
		contact.setContactTelDetails(contactTelDetails);
		contactDao.insertWithDetail(contact);
		contacts = contactDao.findAllWithDetail();	
		listContacts(contacts);			
		
	}

	private static void listContacts(List<Contact> contacts) {
		for (Contact contact: contacts) {
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
