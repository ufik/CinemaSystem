package cz.uhk.fim.cinema.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.fim.cinema.entity.Program;
import cz.uhk.fim.cinema.entity.Reservation;
import cz.uhk.fim.cinema.entity.ReservationItem;

@Repository
public class ReservationsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addReservation(Reservation r) {
		sessionFactory.getCurrentSession().save(r);
	}
	
	public void addReservationItem(ReservationItem r) {
		sessionFactory.getCurrentSession().save(r);
	}

	public List<Reservation> listReservations() {

		return sessionFactory.getCurrentSession().createQuery("from Reservation ORDER BY date DESC").list();
	}
	
	public List listReservationItems(int idProgram) {
		
		return sessionFactory.getCurrentSession().createQuery("from ReservationItem WHERE program_id_program = "+idProgram).list();
	}
	
	public void removeReservation(Integer id) {
		Reservation r = (Reservation) sessionFactory.getCurrentSession().load(Reservation.class, id);
		
		if (null != r) {
			sessionFactory.getCurrentSession().delete(r);
		}
	}

	public Reservation getReservation(Integer reservationId) {
		
		sessionFactory.getCurrentSession().enableFetchProfile( "reservation-with-items" );  // name matches @FetchProfile name
		Reservation r = (Reservation) sessionFactory.getCurrentSession().get( Reservation.class, reservationId );
		sessionFactory.getCurrentSession().disableFetchProfile( "reservation-with-items" ); // or just close the session
		
		return r;
	}

	public void removeReservationItem(Integer id) {
		ReservationItem r = (ReservationItem) sessionFactory.getCurrentSession().load(ReservationItem.class, id);
		
		if (null != r) {
			sessionFactory.getCurrentSession().delete(r);
		}
	}

	public ReservationItem getReservationItem(int row, int column, int idProgram) {
		List<ReservationItem> l = sessionFactory.getCurrentSession().createQuery("from ReservationItem WHERE program_id_program = " + idProgram + " AND row = " + row + "AND column = " + column).list();
		if(l.size() > 0) return l.get(0);
		else return null;
	}
}
