package cz.uhk.fim.cinema.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cz.uhk.fim.cinema.entity.User;

public class UserDAO{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public User findByUsername(String username) {
		/*User user = (User) sessionFactory.getCurrentSession().createQuery("from user where username = ?")
				.setEntity(0, username)
			    .uniqueResult();*/
		

		List<User> l = sessionFactory.getCurrentSession().createQuery("from user WHERE username = " + username).list();
		return l.get(0);
	}
}
