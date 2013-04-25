package cz.fim.uhk.cinema.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.fim.uhk.cinema.form.Hall;

@Repository
public class HallDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addHall(Hall hall) {
		sessionFactory.getCurrentSession().save(hall);
	}

	public List<Hall> listHall() {

		return sessionFactory.getCurrentSession().createQuery("from Hall").list();
	}
	
	public Hall getHall(Integer hallId) {

		List<Hall> l = sessionFactory.getCurrentSession().createQuery("from Hall WHERE id_hall = " + hallId).list();
		return l.get(0);
	}
	
	public void updateHall(Hall hall) {
		sessionFactory.getCurrentSession().update(hall);
	}
	
	public void removeHall(Integer id) {
		Hall hall = (Hall) sessionFactory.getCurrentSession().load(
				Hall.class, id);
		if (null != hall) {
			sessionFactory.getCurrentSession().delete(hall);
		}

	}

	public Hall getHall(String text) {
		List<Hall> l = sessionFactory.getCurrentSession().createQuery("from Hall WHERE name = " + text).list();
		return l.get(0);
	}
}
