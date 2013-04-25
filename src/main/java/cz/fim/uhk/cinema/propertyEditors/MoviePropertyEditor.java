package cz.fim.uhk.cinema.propertyEditors;

import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fim.uhk.cinema.form.Movie;
import cz.fim.uhk.cinema.service.MovieService;

public class MoviePropertyEditor extends PropertyEditorSupport {
	
	@Autowired
	MovieService movieService;
	
	public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && text.length() > 0) {
                
        		Movie movie = movieService.getMovieByName(text);
        	
                super.setValue(movie);
        } else {
                super.setValue(null);
        }
	}

	public String getAsText() {
        Movie movie = (Movie) super.getValue();
        return (movie != null ? movie.getName() : "");
	}
}
