package cz.fim.uhk.cinema.form;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
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
	@Range(min=9, max=12)
	private String telephone;
	
	@NotEmpty
	@OneToMany
	private List<Program> programItems;
	
	public Reservation(){
		this.date = new Timestamp(new java.util.Date().getTime());
	}
	
	public Reservation(List<Program> programItems) {
		super();
	
		this.date = new Timestamp(new java.util.Date().getTime());
		this.programItems = programItems;
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
	public List<Program> getProgramItems() {
		return programItems;
	}

	/**
	 * @param programItems the programItems to set
	 */
	public void setProgramItems(List<Program> programItems) {
		this.programItems = programItems;
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

}
