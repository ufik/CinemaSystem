package cz.fim.uhk.cinema.form;

public class MovieForm{
	
	private Integer id_movie;
	private Integer length;
	private Integer price;
	private String description;
	
	public MovieForm(){}
	
	public MovieForm(Integer id_movie, Integer length, Integer price,
			String description) {
		super();
		this.id_movie = id_movie;
		this.length = length;
		this.price = price;
		this.description = description;
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
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}