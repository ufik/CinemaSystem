package cz.uhk.fim.cinema.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriceCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_price_category;
	private String name;
	private Double price;
	
	public PriceCategory(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public int getId_price_category() {
		return id_price_category;
	}

	public void setId_price_category(int id_price_category) {
		this.id_price_category = id_price_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
