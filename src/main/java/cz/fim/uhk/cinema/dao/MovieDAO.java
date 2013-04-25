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

	public void addMovie(Movie movie) {
		sessionFactory.getCurrentSession().save(movie);
	}

	public List<Movie> listMovie() {

		return sessionFactory.getCurrentSession().createQuery("from Movie").list();
	}
	
	public Movie getMovie(Integer movieId) {

		List<Movie> l = sessionFactory.getCurrentSession().createQuery("from Movie WHERE id_movie = " + movieId).list();
		return l.get(0);
	}
	
	public void updateMovie(Movie movie) {
		sessionFactory.getCurrentSession().update(movie);
	}
	
	public void removeMovie(Integer id) {
		Movie contact = (Movie) sessionFactory.getCurrentSession().load(
				Movie.class, id);
		if (null != contact) {
			sessionFactory.getCurrentSession().delete(contact);
		}

	}

	public Movie getMovie(String name) {
		List<Movie> l = sessionFactory.getCurrentSession().createQuery("from Movie WHERE name = " + name).list();
		return l.get(0);
	}
}
