package cz.uhk.fim.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.fim.cinema.dao.ReservationsDAO;
import cz.uhk.fim.cinema.entity.Reservation;
import cz.uhk.fim.cinema.entity.ReservationItem;

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
	public void addReservationItem(ReservationItem item) {
		reservationsDAO.addReservationItem(item);
	}
	
	@Transactional
	public void removeReservation(Integer id) {
		reservationsDAO.removeReservation(id);
	}
	
	@Transactional
	public Reservation getReservation(Integer reservationId) {
		return reservationsDAO.getReservation(reservationId);
	}

	@Transactional
	public List listReservationItems(int idProgram) {		
		return reservationsDAO.listReservationItems(idProgram);
	}

	@Transactional
	public void removeReservationItem(Integer id) {
		reservationsDAO.removeReservationItem(id);
	}

	@Transactional
	public boolean checkSeatAvailability(int row, int column, int idProgram) {
		return reservationsDAO.getReservationItem(row, column, idProgram) == null ? true : false;
	}


}
