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

import cz.fim.uhk.cinema.entity.Reservation;
import cz.fim.uhk.cinema.form.ReservationForm;
import cz.fim.uhk.cinema.service.ProgramService;
import cz.fim.uhk.cinema.service.ReservationsService;

@Controller
public class ReservationsController {
	
	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private ProgramService programService;

	private MessageSource messageSource;
	
	private void setId(Map<String, Object> map){
		map.put("id", "reservations");
	}
	
	@RequestMapping("/admin/reservations")
	public String listReservations(Map<String, Object> map) {
		this.setId(map);
		
		map.put("title", "Správa rezervací");
		map.put("reservations", reservationsService.list());
		return "backend/reservations";
	}
	
	@RequestMapping(value = "/admin/reservations/form")	
	public String addFormReservation(Map<String, Object> map) {
		this.setId(map);
		
		map.put("program", programService.list());
		map.put("reservationForm", new ReservationForm());
		return "backend/reservationForm";
	}
	
	@RequestMapping(value = "/admin/reservations/form/{reservationId}")	
	public String updateFormReservation(@PathVariable("reservationId") Integer reservationId, Map<String, Object> map) {
		this.setId(map);
		
		map.put("program", programService.list());
		map.put("reservationForm", reservationsService.getReservation(reservationId));
		return "backend/reservationForm";
	}
	
	@RequestMapping(value = "/admin/reservations/add", method = RequestMethod.POST)	
	public String addContact(ModelMap model, @Valid @ModelAttribute("reservationForm")
	 Reservation reservation, BindingResult result, RedirectAttributes ra, Locale locale) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
			model.put("program", programService.list());
            return "backend/reservationForm";
		}
		
		reservationsService.addReservation(reservation);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("reservationsavestatus", null, locale));
		
		return "redirect:/admin/reservations";
	}

	@RequestMapping("/admin/reservations/delete/{movieId}")
	public String deleteContact(@PathVariable("movieId")
	Integer movieId, RedirectAttributes ra, Locale locale) {

		reservationsService.removeReservation(movieId);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("reservationdeletestatus", null, locale));
		
		return "redirect:/admin/reservations";
	}
}
