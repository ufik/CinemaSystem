package cz.fim.uhk.cinema.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String CSFD_MOVIE_URL = "http://csfdapi.cz/movie/";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_movie;
	
	private String name;
	
	private String genres;
	@Column(columnDefinition="text")
	private String countries;
	private int rating;
	@Column(columnDefinition="text")
	private String directors;
	@Column(columnDefinition="text")
	private String original;
	@Column(columnDefinition="text")
	private String script;
	@Column(columnDefinition="text")
	private String actors;
	private String length;
	@Min(value = 1)
	private int price;
	@Column(columnDefinition="text")
	private String description;
	private String poster;
	private int year;
	private int csfdId;
	
	
	/**
	 * @return the poster
	 */
	public String getPoster() {
		return poster;
	}

	/**
	 * @param poster the poster to set
	 */
	public void setPoster(String poster) {
		this.poster = poster;
	}

	/**
	 * @return the genres
	 */
	public String getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(String genres) {
		this.genres = genres;
	}

	/**
	 * @return the countries
	 */
	public String getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(String countries) {
		this.countries = countries;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the original
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}

	/**
	 * @param script the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * @return the actors
	 */
	public String getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(String actors) {
		this.actors = actors;
	}
		
	public Movie() {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCsfdId() {
		return csfdId;
	}

	public void setCsfdId(int csfdId) {
		this.csfdId = csfdId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	/**
	 * Naplni atributy instance aktualnimi daty stazenymi z csfd.cz
	 */
	public void fullFillCsfdData() {
		try {
			JSONObject jsonO = readJsonFromUrl(CSFD_MOVIE_URL + this.csfdId);

			if(jsonO.has("names")) this.name = jsonO.getJSONObject("names").getString("cs");
			if(jsonO.has("poster_url")) this.poster = jsonO.getString("poster_url");
			if(jsonO.has("year")) this.year = jsonO.getInt("year");
			if(jsonO.has("rating")) this.rating = jsonO.getInt("rating");
			if(jsonO.has("runtime")) this.length = (String) jsonO.get("runtime");
			if(jsonO.has("genres")) this.genres = arrayToString(jsonO.getJSONArray("genres"), false);			
			if(jsonO.has("countries")) this.countries = arrayToString(jsonO.getJSONArray("countries"), false);
			if(jsonO.getJSONObject("authors").has("directors")) this.directors = arrayToString(jsonO.getJSONObject("authors").getJSONArray("directors"), true);
			if(jsonO.getJSONObject("authors").has("original"))this.original = arrayToString(jsonO.getJSONObject("authors").getJSONArray("original"), true);
			if(jsonO.getJSONObject("authors").has("script"))this.script = arrayToString(jsonO.getJSONObject("authors").getJSONArray("script"), true);
			if(jsonO.getJSONObject("authors").has("actors"))this.actors = arrayToString(jsonO.getJSONObject("authors").getJSONArray("actors"), true);
			if(jsonO.has("plot")) this.description = new String(jsonO.getString("plot").getBytes(), "UTF-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Prevede jsonArray na string. Hodnoty jsou oddeleny carkou.
	 * @param ja
	 * @return
	 */
	private String arrayToString(JSONArray ja, boolean nestedObject){
		String tmp = "";
		for (int i = 0; i < ja.length(); i++) {
			  try {
				if(!nestedObject) tmp += ja.getString(i) + ", ";
				else tmp += ja.getJSONObject(i).getString("name") + ", ";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return tmp.substring(0, tmp.length() - 2);
	}
	
	/**
	 * Funkce stahne aktualni data o filmu z csfd.cz pomoci api http://csfdapi.cz/ 
	 * @param url ve tvaru http://csfdapi.cz/movie/{idMovie}
	 * @return JSONObject
	 * @throws IOException
	 * @throws JSONException
	 */
	 private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	  StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	   
	  String jsonText = sb.toString();
	  JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	 }
	
}
