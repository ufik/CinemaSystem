package cz.fim.uhk.cinema.form;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_hall;
	private String name;
	private int capacity;
	private int rows;
	private int columns;
	
	public Hall(String name, int capacity, int rows, int columns) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.rows = rows;
		this.columns = columns;
	}

	public int getId_hall() {
		return id_hall;
	}

	public void setId_hall(int id_hall) {
		this.id_hall = id_hall;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
}
