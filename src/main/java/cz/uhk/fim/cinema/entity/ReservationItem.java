package cz.uhk.fim.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ReservationItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReservationItem;
	
	@OneToOne
	private Program program;
	
	@Column(name = "`row`")
	private int row;
	
	@Column(name = "`column`")
	private int column;
	
	public ReservationItem(){
		
	}

	/**
	 * @return the idReservationItem
	 */
	public int getIdReservationItem() {
		return idReservationItem;
	}

	/**
	 * @param idReservationItem the idReservationItem to set
	 */
	public void setIdReservationItem(int idReservationItem) {
		this.idReservationItem = idReservationItem;
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