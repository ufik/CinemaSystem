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
import cz.fim.uhk.cinema.form.Reservation;
import cz.fim.uhk.cinema.service.ContactService;
import cz.fim.uhk.cinema.service.ProgramService;
import cz.fim.uhk.cinema.service.ReservationsService;

@Controller
public class ReservationsController {
	
	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private ProgramService programService;

	@RequestMapping("/admin/reservations")
	public String listReservations(Map<String, Object> map) {
		
		map.put("title", "Správa rezervací");
		map.put("reservations", reservationsService.list());
		return "backend/reservations";
	}
	
	@RequestMapping(value = "/admin/reservations/form")	
	public String addFormReservation(Map<String, Object> map) {
		
		map.put("program", programService.list());
		map.put("reservation", new Reservation());
		return "backend/reservationForm";
	}
	
	@RequestMapping(value = "/admin/reservations/form/{reservationId}")	
	public String updateFormReservation(@PathVariable("reservationId") Integer reservationId, Map<String, Object> map) {
		
		map.put("program", programService.list());
		map.put("reservation", reservationsService.getReservation(reservationId));
		return "backend/reservationForm";
	}
	
	@RequestMapping(value = "/admin/reservations/add", method = RequestMethod.POST)	
	public String addContact(ModelMap model, @Valid @ModelAttribute("reservation")
	 Reservation reservation, BindingResult result) {
		
		if (result.hasErrors()) {
			model.put("error", "1");
			model.put("program", programService.list());
            return "backend/reservationForm";
		}
		
		reservationsService.addReservation(reservation);
		return "redirect:/admin/reservations";
	}

	@RequestMapping("/admin/reservations/delete/{movieId}")
	public String deleteContact(@PathVariable("movieId")
	Integer movieId) {

		reservationsService.removeReservation(movieId);

		return "redirect:/admin/reservations";
	}
}
