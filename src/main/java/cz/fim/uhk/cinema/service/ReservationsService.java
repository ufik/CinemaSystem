package cz.fim.uhk.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fim.uhk.cinema.dao.ReservationsDAO;
import cz.fim.uhk.cinema.entity.Movie;
import cz.fim.uhk.cinema.entity.Reservation;

@Service
public class ReservationsService {

	@Autowired
	private ReservationsDAO reservationsDAO;
	
	@Transactional
	public List<Reservation> list() {		
		return reservationsDAO.listReservations();
	}
	
	@Transactional
	public void addReservation(Reservation reservation) {
		reservationsDAO.addReservation(reservation);
	}
	
	@Transactional
	public void removeReservation(Integer id) {
		reservationsDAO.removeReservation(id);
	}

	public Reservation getReservation(Integer reservationId) {
		return reservationsDAO.getReservation(reservationId);
	}

}
