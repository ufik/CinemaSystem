package cz.fim.uhk.cinema.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ReservationItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReservationItem;
	@OneToOne
	private Program program;
	@NotEmpty
	private int row;
	@NotEmpty
	private int column;
	
	public ReservationItem(){
		
	}
	
	/**
	 * @return the program
	 */
	public Program getProgram() {
		return program;
	}
	/**
	 * @param program the program to set
	 */
	public void setProgram(Program program) {
		this.program = program;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	
}