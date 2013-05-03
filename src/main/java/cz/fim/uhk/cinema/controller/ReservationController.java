package cz.fim.uhk.cinema.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import cz.fim.uhk.cinema.form.ReservationForm;
import cz.fim.uhk.cinema.service.MovieService;
import cz.fim.uhk.cinema.service.ProgramService;

@SessionAttributes({"reservationForm"})
@Controller
public class ReservationController {
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/reserve/{programId}")
	public String programReserve(@PathVariable("programId") Integer programId, Map<String, Object> map, @ModelAttribute("reservationForm") ReservationForm reservationForm){
		
		if(reservationForm == null) reservationForm = new ReservationForm();
		
		map.put("movies", movieService.list());
		map.put("program", programService.getProgram(programId));
		map.put("reservationForm", reservationForm);
		return "frontend/reserve";
	}
	
	@RequestMapping(value = "/reserve/save", method = RequestMethod.POST)
	public String reserveSave(ModelMap model, @ModelAttribute("reservationForm") ReservationForm reservationForm){
		
		return "redirect:/";
	}

}