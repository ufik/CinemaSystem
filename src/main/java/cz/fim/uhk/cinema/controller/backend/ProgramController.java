package cz.fim.uhk.cinema.controller.backend;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cz.fim.uhk.cinema.form.Program;
import cz.fim.uhk.cinema.service.ProgramService;

@Controller
public class ProgramController {
	
	@Autowired
	private ProgramService programService;

	@RequestMapping("/admin/program")
	public String listMovies(Map<String, Object> map) {

		map.put("title", "Správa programu");
		map.put("movieList", programService.list());
		return "backend/program";
	}

	@RequestMapping(value = "/admin/program/add", method = RequestMethod.POST)	
	public String addContact(ModelMap model, @Valid @ModelAttribute("movie")
	 Program program, BindingResult result) {
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "forward:/admin/program";
		}
		
		programService.addProgram(program);
		return "redirect:/admin/program";
	}

	@RequestMapping("/admin/program/delete/{movieId}")
	public String deleteContact(@PathVariable("movieId")
	Integer movieId) {

		programService.removeProgram(movieId);

		return "redirect:/admin/program";
	}
}
