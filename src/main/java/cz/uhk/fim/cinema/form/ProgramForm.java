package cz.uhk.fim.cinema.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ProgramForm{
	
	private Integer id_program;
	
	@NotEmpty
	private String date;
	
	private Integer id_movie;
	
	private Integer id_hall;
	
	public ProgramForm(){}
	
	public ProgramForm(Integer id_program, String date, Integer id_movie, Integer id_hall) {
		super();
		this.setId_program(id_program);
		this.date = date;
		this.id_movie = id_movie;
		this.id_hall = id_hall;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the id_movie
	 */
	public Integer getId_movie() {
		return id_movie;
	}
	/**
	 * @param id_movie the id_movie to set
	 */
	public void setId_movie(Integer id_movie) {
		this.id_movie = id_movie;
	}
	/**
	 * @return the id_hall
	 */
	public Integer getId_hall() {
		return id_hall;
	}
	/**
	 * @param id_hall the id_hall to set
	 */
	public void setId_hall(Integer id_hall) {
		this.id_hall = id_hall;
	}

	public Integer getId_program() {
		return id_program;
	}

	public void setId_program(Integer id_program) {
		this.id_program = id_program;
	}
	
	
	
}