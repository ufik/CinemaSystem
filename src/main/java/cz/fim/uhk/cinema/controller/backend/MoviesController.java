package cz.fim.uhk.cinema.controller.backend;

import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cz.fim.uhk.cinema.entity.Movie;
import cz.fim.uhk.cinema.service.MovieService;

@Controller
public class MoviesController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MessageSource messageSource;
	
	private void setId(Map<String, Object> map){
		map.put("id", "movies");
	}
	
	@RequestMapping("/admin/movies")
	public String listMovies(Map<String, Object> map) {
		this.setId(map);
		
		map.put("title", "Správa filmů");
		map.put("movies", movieService.list());
		return "backend/movies";
	}
	
	@RequestMapping(value = "/admin/movies/form")	
	public String addFormContact(Map<String, Object> map) {
		this.setId(map);
		
		map.put("movie", new Movie());
		return "backend/movieForm";
	}
	
	@RequestMapping(value = "/admin/movies/form/{movieId}")	
	public String addFormMovie(@PathVariable("movieId") Integer movieId, Map<String, Object> map) {
		this.setId(map);
		
		map.put("movie", movieService.getMovie(movieId));
		return "backend/movieForm";
	}
	
	@RequestMapping(value = "/admin/movies/add", method = RequestMethod.POST)	
	public String addMovie(ModelMap model, @Valid @ModelAttribute("movie")
	 Movie movie, BindingResult result, Locale locale, RedirectAttributes ra) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/movieForm";
		}
		
		movieService.addMovie(movie);

		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("moviesavestatus", null, locale));
		
		return "redirect:/admin/movies";
	}
	
	@RequestMapping(value = "/admin/movies/udpate", method = RequestMethod.POST)	
	public String updateMovie(ModelMap model, @Valid @ModelAttribute("movie")
	 Movie movie, BindingResult result, Locale locale, RedirectAttributes ra) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/movieForm";
		}
		
		movieService.updateMovie(movie);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("moviesavestatus", null, locale));
		
		return "redirect:/admin/movies";
	}
	
	@RequestMapping("/admin/movies/delete/{movieId}")
	public String deleteMovie(@PathVariable("movieId") Integer movieId, Locale locale, RedirectAttributes ra) {

		movieService.removeMovie(movieId);
		
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("moviedeletestatus", null, locale));
		
		return "redirect:/admin/movies";
	}
}
