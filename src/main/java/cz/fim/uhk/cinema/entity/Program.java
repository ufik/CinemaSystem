package cz.fim.uhk.cinema.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import com.sun.istack.NotNull;

@Entity
public class Program implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_program;

	private Timestamp date;
	
	@NotNull
    @Valid
    @OneToOne
	private Movie movie;
	
	@NotNull
    @Valid
    @OneToOne
	private Hall hall;
	
	public Program(){}
	
	public Program(Timestamp date, Movie movie, Hall hall) {
		super();
		this.date = date;
		this.movie = movie;
		this.hall = hall;
	}

	public int getId_program() {
		return id_program;
	}

	public void setId_program(int id_program) {
		this.id_program = id_program;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}
	
	public boolean getOld(){
		if(this.date.compareTo(new Timestamp(new java.util.Date().getTime())) > 0) return true;
		
		return false;
	}
}
