package cz.fim.uhk.cinema.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fim.uhk.cinema.form.User;

public class UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public User findByUsername(String username) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, username);
		
		return user;
	}
}
