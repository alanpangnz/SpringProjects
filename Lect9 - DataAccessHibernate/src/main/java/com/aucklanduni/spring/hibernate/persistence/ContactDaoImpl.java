package com.aucklanduni.spring.hibernate.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aucklanduni.spring.hibernate.domain.Contact;


@Repository("contactDao")
@Transactional
public class ContactDaoImpl implements ContactDao {
	
	private Logger _logger = LoggerFactory.getLogger(ContactDaoImpl.class);
	
	private SessionFactory _sessionFactory;	

	public SessionFactory getSessionFactory() {
		return _sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this._sessionFactory = sessionFactory;
		_logger.debug("SessionFactory class: " + sessionFactory.getClass().getName());
	}

	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		List<Contact> result = _sessionFactory.getCurrentSession().createQuery("from Contact c").list();
		return result;
	}

	public List<Contact> findAllWithDetail() {
		return _sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
	}

	public Contact findById(Long id) {
		return (Contact) _sessionFactory.getCurrentSession().
				getNamedQuery("Contact.findById").setParameter("id", id).uniqueResult();
	}

	public Contact save(Contact contact) {
		_sessionFactory.getCurrentSession().saveOrUpdate(contact);
		_logger.info("Contact saved with id: " + contact.getId());
		return contact;
	}

	public void delete(Contact contact) {
		_sessionFactory.getCurrentSession().delete(contact);
		_logger.info("Contact deleted with id: " + contact.getId());
	}
	
}
