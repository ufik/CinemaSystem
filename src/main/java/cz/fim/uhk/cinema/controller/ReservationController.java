package cz.fim.uhk.cinema.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import cz.fim.uhk.cinema.entity.Program;
import cz.fim.uhk.cinema.entity.Reservation;
import cz.fim.uhk.cinema.entity.ReservationItem;
import cz.fim.uhk.cinema.form.ReservationForm;
import cz.fim.uhk.cinema.service.MovieService;
import cz.fim.uhk.cinema.service.ProgramService;

@SessionAttributes("reservation")
@Controller
public class ReservationController {
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private MovieService movieService;
	
	private Reservation reservation;
	
	@ModelAttribute("reservation")
	public Reservation createReservation(){
		
		return reservation = new Reservation();
	}
	
	@RequestMapping("/reserve/{programId}")
	public String programReserve(@PathVariable("programId") Integer programId, Map<String, Object> map, @ModelAttribute("reservationForm") ReservationForm reservationForm){
		
		map.put("movies", movieService.list());
		map.put("program", programService.getProgram(programId));
		map.put("reservationForm", reservationForm);
		map.put("reservation", reservation);
		return "frontend/reserve";
	}
	
	@RequestMapping(value = "/reserve/save", method = RequestMethod.POST)
	public String reserveSave(@ModelAttribute("reservationForm") ReservationForm reservationForm){
		
		List<String> programItems = reservationForm.getProgramItems();
		List<ReservationItem> tmpList = reservation.getReservationItems();
		
		reservation.setFirstname(reservationForm.getFirstname());
		reservation.setLastname(reservationForm.getLastname());
		reservation.setEmail(reservationForm.getEmail());
		reservation.setTelephone(reservationForm.getTelephone());
		
		for (int i = 0; i < programItems.size(); i++) {
			
			List<String> items = Arrays.asList(programItems.get(i).split("\\s*,\\s*"));
			
			ReservationItem tmp = new ReservationItem();
			// nastaveni rady a sedadla
			if(items.size() > 1) tmp.setColumn(Integer.valueOf(items.get(1)));
			tmp.setRow(Integer.valueOf(items.get(0)));
			// ziskani instance programu
			Program tmpProgram = programService.getProgram(Integer.valueOf(items.get(2)));
			tmp.setProgram(tmpProgram);
			
			tmpList.add(tmp);
		}
		
		reservation.setReservationItems(tmpList);
		
		return "redirect:/";
	}

}