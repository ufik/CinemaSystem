package cz.fim.uhk.cinema.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.fim.uhk.cinema.entity.Reservation;

@Repository
public class ReservationsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addReservation(Reservation r) {
		sessionFactory.getCurrentSession().save(r);
	}

	public List<Reservation> listReservations() {

		return sessionFactory.getCurrentSession().createQuery("from Reservation").list();
	}

	public void removeReservation(Integer id) {
		Reservation r = (Reservation) sessionFactory.getCurrentSession().load(
				Reservation.class, id);
		
		if (null != r) {
			sessionFactory.getCurrentSession().delete(r);
		}
	}

	public Reservation getReservation(Integer reservationId) {
		List<Reservation> l = sessionFactory.getCurrentSession().createQuery("from Reservation WHERE id_reservation = " + reservationId).list();
		return l.get(0);
	}
}
