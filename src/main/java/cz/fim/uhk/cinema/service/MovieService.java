package cz.fim.uhk.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fim.uhk.cinema.dao.MovieDAO;
import cz.fim.uhk.cinema.form.Movie;


@Service
public class MovieService {

	@Autowired
	private MovieDAO movieDAO;
	
	@Transactional
	public List<Movie> list() {		
		return movieDAO.listMovie();
	}
	
	@Transactional
	public void addMovie(Movie movie) {
		movieDAO.addMovie(movie);
	}
	
	@Transactional
	public void removeMovie(Integer id) {
		movieDAO.removeMovie(id);
	}

}
