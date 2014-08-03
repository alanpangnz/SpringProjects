package com.aucklanduni.spring.hibernate.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "CONTACT_TEL_DETAIL")
public class ContactTelDetail implements Serializable {

	private Long _id;
	private int _version;
	private Contact _contact;
	private String _telType;
	private String _telNumber;

	public ContactTelDetail() {
	}

	public ContactTelDetail(String telType, String telNumber) {
		this._telType = telType;
		this._telNumber = telNumber;
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

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	public Contact getContact() {
		return this._contact;
	}

	public void setContact(Contact contact) {
		this._contact = contact;
	}

	@Column(name = "TEL_TYPE")
	public String getTelType() {
		return this._telType;
	}

	public void setTelType(String telType) {
		this._telType = telType;
	}

	@Column(name = "TEL_NUMBER")
	public String getTelNumber() {
		return this._telNumber;
	}

	public void setTelNumber(String telNumber) {
		this._telNumber = telNumber;
	}

	public String toString() {
		return "Contact Tel Detail - Id: " + _id + ", Contact id: " + getContact().getId()
				+ ", Type: " + _telType + ", Number: " + _telNumber;
	}	
}
