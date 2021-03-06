package cz.uhk.fim.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.fim.cinema.dao.MovieDAO;
import cz.uhk.fim.cinema.entity.Movie;
import cz.uhk.fim.cinema.entity.Program;


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
	public void updateMovie(Movie movie) {
		movieDAO.updateMovie(movie);
	}
	
	@Transactional
	public void removeMovie(Integer id) {
		movieDAO.removeMovie(id);
	}

	@Transactional
	public Movie getMovie(Integer movieId) {
		return movieDAO.getMovie(movieId);
	}

	@Transactional
	public Movie getMovieByName(String name) {
		return movieDAO.getMovie(name);
	}


}
