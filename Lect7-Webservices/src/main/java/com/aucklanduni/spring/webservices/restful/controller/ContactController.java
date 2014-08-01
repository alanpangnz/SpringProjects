
package com.aucklanduni.spring.webservices.restful.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aucklanduni.spring.webservices.domain.Contact;
import com.aucklanduni.spring.webservices.domain.Contacts;
import com.aucklanduni.spring.webservices.service.ContactService;


@Controller
@RequestMapping(value="/contacts")
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	
	@Autowired
	private ContactService contactService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Contacts listData(WebRequest webRequest) {
		return new Contacts(contactService.findAll());
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Contact findContactById(@PathVariable Long id) {		
		return contactService.findById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Contact create(@RequestBody Contact contact, HttpServletResponse response) {
		logger.info("Creating contact: " + contact);
		contactService.save(contact);
		logger.info("Contact created successfully with info: " + contact);
		response.setHeader("Location",  "/contacts/" + contact.getId());
		return contact;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Contact contact, @PathVariable Long id) {
		logger.info("Updating contact: " + contact);
		contactService.save(contact);
		logger.info("Contact updated successfully with info: " + contact);
		//return contact;
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		logger.info("Deleting contact with id: " + id);
		Contact contact = contactService.findById(id);
		contactService.delete(contact);
		logger.info("Contact deleted successfully");
	}	
	
}
