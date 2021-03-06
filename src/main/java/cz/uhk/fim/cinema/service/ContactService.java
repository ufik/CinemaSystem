package cz.uhk.fim.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.fim.cinema.dao.ContactDAO;
import cz.uhk.fim.cinema.entity.Contact;

@Service
public class ContactService {

	@Autowired
	private ContactDAO contactDAO;
	
	@Transactional
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);
	}

	@Transactional
	public List<Contact> list() {

		return contactDAO.listContact();
	}

	@Transactional
	public void removeContact(Integer id) {
		contactDAO.removeContact(id);
	}
	
	@Transactional
	public Contact getContact(Integer id) {
		return contactDAO.getContact(id);
	}
	
	@Transactional
	public void updateContact(Contact contact) {
		contactDAO.updateContact(contact);
	}
}
