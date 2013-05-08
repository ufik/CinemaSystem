package cz.uhk.fim.cinema.controller.backend;

import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cz.uhk.fim.cinema.entity.Hall;
import cz.uhk.fim.cinema.service.HallService;

@Controller
public class HallsController {
	
	@Autowired
	private HallService hallService;
	
	@Autowired
	private MessageSource messageSource;
	
	private void setId(Map<String, Object> map){
		map.put("id", "halls");
	}
	
	@RequestMapping("/admin/halls")
	public String listMovies(Map<String, Object> map) {
		this.setId(map);
		
		map.put("title", "Správa sálů");
		map.put("halls", hallService.list());
		return "backend/halls";
	}
	
	@RequestMapping(value = "/admin/halls/form")	
	public String addFormHall(Map<String, Object> map) {
		this.setId(map);
		
		map.put("hall", new Hall());
		return "backend/hallForm";
	}
	
	@RequestMapping(value = "/admin/halls/form/{hallId}")	
	public String addFormHall(@PathVariable("hallId") Integer hallId, Map<String, Object> map) {
		this.setId(map);
		
		map.put("hall", hallService.getHall(hallId));
		return "backend/hallForm";
	}
	
	@RequestMapping(value = "/admin/halls/add", method = RequestMethod.POST)	
	public String addHall(ModelMap model, @Valid @ModelAttribute("hall")
	 Hall hall, BindingResult result, Locale locale, RedirectAttributes ra) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/hallForm";
		}
		
		hallService.addHall(hall);

		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("hallsavestatus", null, locale));
		
		return "redirect:/admin/halls";
	}
	
	@RequestMapping(value = "/admin/halls/udpate", method = RequestMethod.POST)	
	public String updateHall(ModelMap model, @Valid @ModelAttribute("hall")
	 Hall hall, BindingResult result, Locale locale, RedirectAttributes ra) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/hallForm";
		}
		
		hallService.updateHall(hall);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("hallsavestatus", null, locale));
		
		return "redirect:/admin/halls";
	}
	
	@RequestMapping("/admin/halls/delete/{hallId}")
	public String deleteHall(@PathVariable("hallId") Integer hallId, Locale locale, RedirectAttributes ra) {

		hallService.removeHall(hallId);
		
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("halldeletestatus", null, locale));
		
		return "redirect:/admin/halls";
	}
}
