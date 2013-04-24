package cz.fim.uhk.cinema.form;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_movie;
	@NotEmpty
	private String name;
	private String version;
	@Range(min = 1, max=999)
	private int length;
	private double price;
	private int accessibility;
	private String description;
		
	public Movie() {
	}

	public Movie(String name, String version, int accessibility, double price) {	
		this.name = name;
		this.version = version;
		this.accessibility = accessibility;
		this.price = price;
	}

	public int getId_movie() {
		return id_movie;
	}

	public void setId_movie(int id_movie) {
		this.id_movie = id_movie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(int accessibility) {
		this.accessibility = accessibility;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
