package cz.fim.uhk.cinema.controller.backend;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import cz.fim.uhk.cinema.entity.Program;
import cz.fim.uhk.cinema.form.ProgramForm;
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

	@Autowired
	private MessageSource messageSource;

	private void setId(Map<String, Object> map){
		map.put("id", "program");
	}
	
	@RequestMapping("/admin/program")
	public String listProgram(Map<String, Object> map) {
		this.setId(map);
		
		map.put("title", "Správa programu");
		map.put("program", programService.list());
		return "backend/program";
	}
	
	@RequestMapping(value = "/admin/program/form")	
	public String addFormProgram(Map<String, Object> map) {
		this.setId(map);
		
		map.put("halls", hallService.list());
		map.put("movies", movieService.list());
		map.put("programForm", new ProgramForm());
		return "backend/programForm";
	}
	
	@RequestMapping(value = "/admin/program/form/{programId}")	
	public String updateFormProgram(@PathVariable("programId") Integer programId, Map<String, Object> map) {
		this.setId(map);
		
		Program program = programService.getProgram(programId);
		ProgramForm programForm = new ProgramForm(program.getId_program(), new SimpleDateFormat("YYYY-MM-dd hh:mm").format(program.getDate()), program.getMovie().getId_movie(), program.getId_program());
		
		map.put("halls", hallService.list());
		map.put("movies", movieService.list());
		map.put("programForm", programForm);
		return "backend/programForm";
	}

	@RequestMapping(value = "/admin/program/add", method = RequestMethod.POST)	
	public String addProgram(ModelMap model, @Valid @ModelAttribute("programForm")
	 ProgramForm programForm, BindingResult result, RedirectAttributes ra, Locale locale) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("halls", hallService.list());
			model.put("movies", movieService.list());
			model.put("error", "1");
            return "backend/programForm";
		}
		
		Program program = new Program();
				
		java.sql.Timestamp timestamp = Timestamp.valueOf(programForm.getDate() + ":00.0");
		program.setDate(timestamp);
		program.setHall(hallService.getHall(programForm.getId_hall()));
		program.setMovie(movieService.getMovie(programForm.getId_movie()));
		
		programService.addProgram(program);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("programsavestatus", null, locale));
		
		return "redirect:/admin/program";
	}
	
	@RequestMapping(value = "/admin/program/udpate", method = RequestMethod.POST)	
	public String updateProgram(ModelMap model, @Valid @ModelAttribute("programForm")
	 ProgramForm programForm, BindingResult result, RedirectAttributes ra, Locale locale) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/programForm?error";
		}
		
		Program program = new Program();
		program.setId_program(programForm.getId_program());
		
		java.sql.Timestamp timestamp = Timestamp.valueOf(programForm.getDate() + ":00.0");
		program.setDate(timestamp);
		program.setHall(hallService.getHall(programForm.getId_hall()));
		program.setMovie(movieService.getMovie(programForm.getId_movie()));
		
		programService.addProgram(program);	
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("programsavestatus", null, locale));
		
		return "redirect:/admin/program";
	}
	
	@RequestMapping("/admin/program/delete/{programId}")
	public String deleteProgram(ModelMap model, @PathVariable("programId")
	Integer programId, RedirectAttributes ra, Locale locale) {
		this.setId(model);
		
		programService.removeProgram(programId);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("programdeletestatus", null, locale));
		
		return "redirect:/admin/program";
	}
}
