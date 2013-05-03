package cz.fim.uhk.cinema.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.fim.uhk.cinema.entity.Contact;
import cz.fim.uhk.cinema.entity.Hall;

@Repository
public class ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addContact(Contact contact) {
		sessionFactory.getCurrentSession().save(contact);
	}

	public List<Contact> listContact() {

		return sessionFactory.getCurrentSession().createQuery("from Contact").list();
	}

	public void removeContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().load(
				Contact.class, id);
		
		if (null != contact) {
			sessionFactory.getCurrentSession().delete(contact);
		}

	}

	public Contact getContact(Integer idContact) {
		List<Contact> l = sessionFactory.getCurrentSession().createQuery("from Contact WHERE id_contact = " + idContact).list();
		return l.get(0);
	}

	public void updateContact(Contact contact) {
		sessionFactory.getCurrentSession().update(contact);
	}
}
