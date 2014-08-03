package com.aucklanduni.spring.hibernate.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "CONTACT")
@NamedQueries({
	@NamedQuery(name="Contact.findById", 
			    query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
	@NamedQuery(name="Contact.findAllWithDetail", 
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")
})
public class Contact implements Serializable {
	private static final Logger _logger = LoggerFactory.getLogger(Contact.class);

	private Long _id;
	private int _version;
	private String _firstName;
	private String _lastName;
	private Date _birthDate;
	private Set<Hobby> _hobbies = new HashSet<Hobby>();
	private Set<ContactTelDetail> _contactTelDetails = new HashSet<ContactTelDetail>();

	public Contact() {
	}

	public Contact(String firstName, String lastName) {
		this._firstName = firstName;
		this._lastName = lastName;
	}

	public Contact(String firstName, String lastName, Date birthDate,
			Set<Hobby> hobbies, Set<ContactTelDetail> contactTelDetails) {
		this._firstName = firstName;
		this._lastName = lastName;
		this._birthDate = birthDate;
		this._hobbies = hobbies;
		this._contactTelDetails = contactTelDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this._id;
	}

	public void setId(Long id) {
		this._id = id;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return this._version;
	}

	public void setVersion(int version) {
		this._version = version;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this._firstName;
	}

	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this._lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	public Date getBirthDate() {
		return this._birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this._birthDate = birthDate;
	}

	@ManyToMany
	@JoinTable(name = "CONTACT_HOBBY_DETAIL", 
	      joinColumns = @JoinColumn(name = "CONTACT_ID"), 
	      inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return this._hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this._hobbies = hobbies;
	}

	@OneToMany(mappedBy = "contact", targetEntity=ContactTelDetail.class, orphanRemoval=true, cascade=CascadeType.ALL)
	public Set<ContactTelDetail> getContactTelDetails() {
		return this._contactTelDetails;
	}

	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		this._contactTelDetails = contactTelDetails;
	}
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}	
	
	public String toString() {		
		return "Contact - Id: " + _id + ", First name: " + _firstName 
				+ ", Last name: " + _lastName + ", Birthday: " + _birthDate;
	}	

}
