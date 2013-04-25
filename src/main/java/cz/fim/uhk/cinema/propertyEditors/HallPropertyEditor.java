package cz.fim.uhk.cinema.propertyEditors;

import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fim.uhk.cinema.form.Hall;
import cz.fim.uhk.cinema.service.HallService;

public class HallPropertyEditor extends PropertyEditorSupport {
	
	@Autowired
	HallService hallService;
	
	public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && text.length() > 0) {
                
        		Hall hall = hallService.getHallByName(text);
        	
                super.setValue(hall);
        } else {
                super.setValue(null);
        }
	}

	public String getAsText() {
        Hall hall = (Hall) super.getValue();
        return (hall != null ? hall.getName() : "");
	}
	
}
