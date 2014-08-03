package com.aucklanduni.spring.jdbc.persistence.xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.aucklanduni.spring.jdbc.domain.Contact;
import com.aucklanduni.spring.jdbc.domain.ContactTelDetail;
import com.aucklanduni.spring.jdbc.persistence.ContactDao;

public class JdbcContactDao implements ContactDao {
	private static Logger _logger = LoggerFactory.getLogger(JdbcContactDao.class);

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Contact> findAll() {
		String sql = "select id, first_name, last_name, birth_date from contact";
		return _namedParameterJdbcTemplate.query(sql, new ContactMapper());	
	}

	public List<Contact> findAllWithDetail() {
		String sql = "select c.id, c.first_name, c.last_name, c.birth_date" + 
		             ", t.id as contact_tel_id, t.tel_type, t.tel_number from contact c " + 
			         "left join contact_tel_detail t on c.id = t.contact_id";
		return _namedParameterJdbcTemplate.query(sql, new ContactWithDetailExtractor());
	}

	public String findFirstNameById(Long id) {
		String sql = "select first_name from contact where id = :contactId";
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("contactId", id);
		return _namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
	}

	public String findLastNameById(Long id) {
		String sql = "select last_name from contact where id = :contactId";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("contactId", id);
		return _namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
	}

	public List<Contact> findByFirstName(String firstName) {
		String sql = "select id, first_name, last_name, birth_date from contact where first_name = :firstName";
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("firstName", firstName);
		return _namedParameterJdbcTemplate.query(sql, namedParameters, new ContactMapper());	
	}

	public void insert(Contact contact) {
		String sql = "insert into contact (first_name, laste_name, birth_date) values :firstName, :lastName, :birthDate";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("firstName", contact.getFirstName());
		
		retrieveIdentity(contact);
	}

	public void update(Contact contact) {
		String sql = "update contact set first_name = :firstName, last_name = :lastName, birth_date = :birthDate where id = :contactId";
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("firstName", contact.getFirstName());
		namedParameters.put("lastName", contact.getLastName());
		namedParameters.put("birthDate", contact.getBirthDate());
		
		_namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	public void delete(Long contactId) {
		String sql = "delete from contact where id = :contactId";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("contactId", contactId);
		_namedParameterJdbcTemplate.update(sql, namedParameters);
	}	
	
	public void insertWithDetail(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	protected void retrieveIdentity(final Contact contact) {
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		contact.setId(_namedParameterJdbcTemplate.queryForObject("select last_insert_id()", namedParameters, Long.class)); 
	}
	
	private static final class ContactMapper implements RowMapper<Contact> {

		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setBirthDate(rs.getDate("birth_date"));
			return contact;
		}
		
	}	
	
	private static final class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>> {

		public List<Contact> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			int count = 0;
			
			Map<Long, Contact> map = new HashMap<Long, Contact>();
			Contact contact = null;
			while (rs.next()) {
				count++;
				_logger.info("Processing record: " + count);
				Long id = rs.getLong("id");
				contact = map.get(id);
				if (contact == null) {  // new contact record
					contact = new Contact();
					contact.setId(id);
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));
					contact.setBirthDate(rs.getDate("birth_date"));
					contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
					map.put(id, contact);
				}
				// Process contact tel. detail (if exists)
				Long contactTelDetailId = rs.getLong("contact_tel_id");
				if (contactTelDetailId > 0) {
					ContactTelDetail contactTelDetail = new ContactTelDetail();
					contactTelDetail.setId(contactTelDetailId);
					contactTelDetail.setContactId(id);
					contactTelDetail.setTelType(rs.getString("tel_type"));
					contactTelDetail.setTelNumber(rs.getString("tel_number"));
					contact.getContactTelDetails().add(contactTelDetail);
				}
			}
			
			return new ArrayList<Contact> (map.values());
		}
		
	}
}
