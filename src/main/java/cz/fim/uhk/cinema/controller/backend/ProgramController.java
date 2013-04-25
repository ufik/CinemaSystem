package cz.fim.uhk.cinema.controller.backend;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cz.fim.uhk.cinema.form.Hall;
import cz.fim.uhk.cinema.form.Movie;
import cz.fim.uhk.cinema.form.Program;
import cz.fim.uhk.cinema.propertyEditors.HallPropertyEditor;
import cz.fim.uhk.cinema.propertyEditors.MoviePropertyEditor;
import cz.fim.uhk.cinema.service.HallService;
import cz.fim.uhk.cinema.service.MovieService;
import cz.fim.uhk.cinema.service.ProgramService;

@Controller
public class ProgramController {
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private HallService hallService;
	
	@Autowired
	private MovieService movieService;

	private MoviePropertyEditor moviePropertyEditor;
	private HallPropertyEditor hallPropertyEditor;

	@RequestMapping("/admin/program")
	public String listProgram(Map<String, Object> map) {

		map.put("title", "Správa programu");
		map.put("program", programService.list());
		return "backend/program";
	}
	
	@RequestMapping(value = "/admin/program/form")	
	public String addFormProgram(Map<String, Object> map) {
		
		map.put("halls", hallService.list());
		map.put("movies", movieService.list());
		map.put("program", new Program());
		return "backend/programForm";
	}
	
	@RequestMapping(value = "/admin/program/form/{programId}")	
	public String updateFormProgram(@PathVariable("programId") Integer programId, Map<String, Object> map) {
		
		map.put("halls", hallService.list());
		map.put("movies", movieService.list());
		map.put("program", programService.getProgram(programId));
		return "backend/programForm";
	}

	@RequestMapping(value = "/admin/program/add", method = RequestMethod.POST)	
	public String addProgram(ModelMap model, @Valid @ModelAttribute("program")
	 Program program, BindingResult result) {
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/programForm";
		}
		
		programService.addProgram(program);
		return "redirect:/admin/program";
	}

	@RequestMapping("/admin/program/delete/{programId}")
	public String deleteProgram(@PathVariable("programId")
	Integer programId) {

		programService.removeProgram(programId);

		return "redirect:/admin/program";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    
		binder.registerCustomEditor(Movie.class, moviePropertyEditor);
        binder.registerCustomEditor(Hall.class, hallPropertyEditor);
	}
}
