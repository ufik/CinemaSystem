package cz.uhk.fim.cinema.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@FetchProfile(name = "reservation-with-items", fetchOverrides = {
		   @FetchProfile.FetchOverride(entity = Reservation.class, association = "reservationItems", mode = FetchMode.JOIN)
		})
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_reservation;

	private Timestamp date;
	
	@NotEmpty
	private String firstname;
	
	@NotEmpty
	private String lastname;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String telephone;
	
	@NotEmpty
	@OneToMany
	private List<ReservationItem> reservationItems = new ArrayList<ReservationItem>();
	
	public Reservation(){
		this.date = new Timestamp(new java.util.Date().getTime());
	}
	
	public Reservation(List<ReservationItem> reservationItems) {
		super();
	
		this.date = new Timestamp(new java.util.Date().getTime());
		this.reservationItems = reservationItems;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	
	/**
	 * @return the programItems
	 */
	public List<ReservationItem> getReservationItems() {
		return reservationItems;
	}

	/**
	 * @param programItems the programItems to set
	 */
	public void setReservationItems(List<ReservationItem> programItems) {
		this.reservationItems = programItems;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @return the id_reservation
	 */
	public int getId_reservation() {
		return id_reservation;
	}

	/**
	 * @param id_reservation the id_reservation to set
	 */
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}

	public int getTotalPrice() {
		
		int total = 0;
		for (ReservationItem item : reservationItems) {
			total += item.getProgram().getMovie().getPrice();
		}
		
		return total;
	}

}
