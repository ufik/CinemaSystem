package cz.uhk.fim.cinema.controller.backend;

import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
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
import cz.uhk.fim.cinema.entity.Reservation;
import cz.uhk.fim.cinema.entity.ReservationItem;
import cz.uhk.fim.cinema.form.ReservationForm;
import cz.uhk.fim.cinema.service.ProgramService;
import cz.uhk.fim.cinema.service.ReservationsService;

@Controller
public class ReservationsController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private ProgramService programService;

	@Autowired
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
		
	@RequestMapping(value = "/admin/reservations/form/{reservationId}")	
	public String updateFormReservation(@PathVariable("reservationId") Integer reservationId, Map<String, Object> map) {
		this.setId(map);

		map.put("reservation", reservationsService.getReservation(reservationId));
		return "backend/reservationForm";
	}

	@RequestMapping("/admin/reservations/delete/{reservationId}")
	public String deleteContact(@PathVariable("reservationId")
	Integer reservationId, RedirectAttributes ra, Locale locale) {

		Reservation r = reservationsService.getReservation(reservationId);
		
		reservationsService.removeReservation(reservationId);
		
		for (ReservationItem item : r.getReservationItems()) {
			reservationsService.removeReservationItem(item.getIdReservationItem());
		}
		
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("reservationdeletestatus", null, locale));
		
		return "redirect:/admin/reservations";
	}
}
