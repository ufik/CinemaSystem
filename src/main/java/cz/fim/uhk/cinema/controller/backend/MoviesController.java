package cz.fim.uhk.cinema.controller.backend;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.fim.uhk.cinema.form.Movie;
import cz.fim.uhk.cinema.service.MovieService;

@Controller
public class MoviesController {
	
	@Autowired
	private MovieService movieService;

	@RequestMapping("/admin/movies")
	public String listMovies(Map<String, Object> map) {

		map.put("movieList", movieService.list());
		
		return "backend/movies";
	}

	@RequestMapping(value = "/admin/movies/add", method = RequestMethod.POST)	
	public String addContact(ModelMap model, @Valid @ModelAttribute("movie")
	 Movie movie, BindingResult result) {
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "forward:/admin/movies";
		}
		
		movieService.addMovie(movie);
		return "redirect:/admin/movies";
	}

	@RequestMapping("/admin/movies/delete/{movieId}")
	public String deleteContact(@PathVariable("movieId")
	Integer movieId) {

		movieService.removeMovie(movieId);

		return "redirect:/admin/movies";
	}
}
