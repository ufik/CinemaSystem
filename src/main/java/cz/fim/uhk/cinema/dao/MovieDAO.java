package cz.fim.uhk.cinema.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.fim.uhk.cinema.form.Movie;

@Repository
public class MovieDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addMovie(Movie contact) {
		sessionFactory.getCurrentSession().save(contact);
	}

	public List<Movie> listMovie() {

		return sessionFactory.getCurrentSession().createQuery("from Movie")
				.list();
	}

	public void removeMovie(Integer id) {
		Movie contact = (Movie) sessionFactory.getCurrentSession().load(
				Movie.class, id);
		if (null != contact) {
			sessionFactory.getCurrentSession().delete(contact);
		}

	}
}
