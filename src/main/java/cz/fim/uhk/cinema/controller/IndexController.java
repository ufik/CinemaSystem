package cz.fim.uhk.cinema.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cz.fim.uhk.cinema.service.ContactService;
import cz.fim.uhk.cinema.service.MovieService;
import cz.fim.uhk.cinema.service.ProgramService;

@Controller
public class IndexController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MovieService movieService;

	@Autowired
	private ProgramService programService;

	@RequestMapping("/")
	public String index(Map<String, Object> map) {

		// dnesni program
		map.put("programToday", programService.list(this.getDate(0), this.getDate(1)));
		// zitra
		map.put("programTommorow", programService.list(this.getDate(1), this.getDate(2)));
		// pozitri
		map.put("program2Days", programService.list(this.getDate(2), this.getDate(3)));
		
		map.put("movies", movieService.list());
		return "index";
	}
	
	@RequestMapping("/movie/{movieId}")
	public String movieDetail(@PathVariable("movieId") Integer movieId, Map<String, Object> map){
		
		map.put("movies", movieService.list());
		map.put("movie", movieService.getMovie(movieId));
		return "frontend/movieDetail";
	}
	
	private Date getDate(int days){
		Calendar c = new GregorianCalendar();
	   
	    c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    
		c.add(Calendar.DAY_OF_YEAR, days);
	    
	    return c.getTime();
	}

}
