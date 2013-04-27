package cz.fim.uhk.cinema.form;

import java.sql.Timestamp;
import java.util.List;

public class ReservationForm{
	
	private Integer id_reservation;
	private Timestamp date;
	private Integer contact_id;
	private String firstname;
	private String lastname;
	private String email;
	private String telephone;
	private List<Integer> programItems;
	
	public ReservationForm(Timestamp date, Integer contact_id,
			String firstname, String lastname, String email, String telephone, List<Integer> programItems) {
		super();
		this.date = date;
		this.contact_id = contact_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.telephone = telephone;
		this.setProgramItems(programItems);
	}
	public ReservationForm() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	/**
	 * @return the contact_id
	 */
	public Integer getContact_id() {
		return contact_id;
	}
	/**
	 * @param contact_id the contact_id to set
	 */
	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<Integer> getProgramItems() {
		return programItems;
	}
	public void setProgramItems(List<Integer> programItems) {
		this.programItems = programItems;
	}
	public Integer getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(Integer id_reservation) {
		this.id_reservation = id_reservation;
	}
	
}