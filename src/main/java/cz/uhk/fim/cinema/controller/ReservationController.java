package cz.uhk.fim.cinema.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cz.uhk.fim.cinema.entity.Contact;
import cz.uhk.fim.cinema.entity.Program;
import cz.uhk.fim.cinema.entity.Reservation;
import cz.uhk.fim.cinema.entity.ReservationItem;
import cz.uhk.fim.cinema.form.ReservationForm;
import cz.uhk.fim.cinema.service.ContactService;
import cz.uhk.fim.cinema.service.MovieService;
import cz.uhk.fim.cinema.service.ProgramService;
import cz.uhk.fim.cinema.service.ReservationsService;

@SessionAttributes("reservation")
@Controller
public class ReservationController {
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private ContactService contactService;
	
	private Reservation reservation;
	
	@ModelAttribute("reservation")
	public Reservation createReservation(){
		
		return reservation = new Reservation();
	}
	
	@RequestMapping("/reserve/{programId}")
	public String programReserve(@PathVariable("programId") Integer programId, Map<String, Object> map, @ModelAttribute("reservationForm") ReservationForm reservationForm,
			@ModelAttribute("reservation") Reservation reservation){
		
		map.put("movies", movieService.list());
		map.put("program", programService.getProgram(programId));
		map.put("reservations", reservationsService.listReservationItems(programId));
		map.put("reservationForm", reservationForm);
		map.put("reservation", reservation);
		return "frontend/reserve";
	}
	
	@RequestMapping(value = "/reserve/save", method = RequestMethod.POST)
	public String reserveSave(@ModelAttribute("reservationForm") ReservationForm reservationForm, @ModelAttribute("reservation") Reservation reservation){
				
		saveReservation(reservation, reservationForm);
		
		return "redirect:/reserve/finish";
	}
	
	private void saveReservation(Reservation reservation, ReservationForm reservationForm){
		List<String> programItems = reservationForm.getProgramItems();
		List<ReservationItem> tmpList = new ArrayList<ReservationItem>();
		
		reservation.setFirstname(reservationForm.getFirstname());
		reservation.setLastname(reservationForm.getLastname());
		reservation.setEmail(reservationForm.getEmail());
		reservation.setTelephone(reservationForm.getTelephone());

		if(programItems != null){
			for (int i = 0; i < programItems.size(); i++) {
				
				List<String> items = Arrays.asList(programItems.get(i).split("\\s*,\\s*"));
				if(items.size() == 3){
					ReservationItem tmp = new ReservationItem();
					// nastaveni rady a sedadla
					tmp.setColumn(Integer.valueOf(items.get(1)));
					tmp.setRow(Integer.valueOf(items.get(0)));
					// ziskani instance programu
					Program tmpProgram = programService.getProgram(Integer.valueOf(items.get(2)));
					tmp.setProgram(tmpProgram);
					
					tmpList.add(tmp);
				}
			}
			
			reservation.setReservationItems(tmpList);
		}
	}
	
	@RequestMapping(value = "/reserve/finish", method = RequestMethod.GET)
	public String controllReserve(Map<String, Object> map, @ModelAttribute("reservation") Reservation reservation){
		
		// pokud nejsou zadne objednavky
		if(reservation.getReservationItems().size() == 0)
			return "redirect:/";
		
		// overeni zda jiz nejsou mista obsazene
		if(!areReservationItemsFree()){
			map.put("error", "Bylo odebráno některé ze sedadel, protože již bylo obsazené. Zkontrolujte prosím svou rezervaci.");
            return "frontend/controllReservation";
		}
		
		map.put("movies", movieService.list());
		map.put("reservation", reservation);
		return "frontend/controllReservation";
	}
	
	@RequestMapping(value = "/reserve/finish", method = RequestMethod.POST)
	public String finishReserve(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult result, ModelMap model){
		
		if (result.hasErrors()) {
			model.put("movies", movieService.list());
			model.put("error", "1");
            return "frontend/controllReservation";
		}
		
		// vytvoreni a ulozeni kontaktu
		Contact c = new Contact();
		c.setFirstname(reservation.getFirstname());
		c.setLastname(reservation.getLastname());
		c.setEmail(reservation.getEmail());
		c.setTelephone(reservation.getTelephone());
		contactService.addContact(c);
		
		// ulozeni polozek rezervace
		for (ReservationItem item : reservation.getReservationItems()) {
			reservationsService.addReservationItem(item);
		}
		// aktualni datum a ulozeni rezervace
		reservation.setDate(new Timestamp(new java.util.Date().getTime()));
		reservationsService.addReservation(reservation);
		
		return "redirect:/reserve/finished";
	}
	
	@RequestMapping(value = "/reserve/finished", method = RequestMethod.GET)
	public String finishedReserve(Map<String, Object> map, SessionStatus sessionStatus){
		
		// smazani session
		sessionStatus.setComplete();
		
		map.put("movies", movieService.list());
		return "frontend/finishedReservation";
	}
	
	private Boolean areReservationItemsFree(){
		
		Boolean isAnyItemDeleted = false;

		if(reservation != null){
			for (ReservationItem item : reservation.getReservationItems()) {
				if(!reservationsService.checkSeatAvailability(item.getRow(), item.getColumn(), item.getProgram().getId_program())){
					isAnyItemDeleted = true;
					// smazani polozky
					removeReservationItem(item.getRow(), item.getColumn(), item.getProgram().getId_program());
				}
			}
		}
		
		return !isAnyItemDeleted;
	}
	
	private void removeReservationItem(Integer row, Integer column, Integer idProgram){
		
		List<ReservationItem> tmp = new ArrayList<ReservationItem>();
		for (ReservationItem item : reservation.getReservationItems()) {
			System.out.println(item.getRow() +" "+ row);
			System.out.println(item.getColumn()  +" "+ column);
			System.out.println(item.getProgram().getId_program() +" "+ idProgram);
			if(item.getRow() != row || item.getColumn() != column || item.getProgram().getId_program() != idProgram){
				tmp.add(item);
			}
		}
		
		reservation.setReservationItems(tmp);
	}

}