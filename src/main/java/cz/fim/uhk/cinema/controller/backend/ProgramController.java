package cz.fim.uhk.cinema.controller.backend;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		ProgramForm programForm = new ProgramForm(program.getId_program(), new SimpleDateFormat("YYYY-MM-dd'T'hh:mm'Z'").format(program.getDate()), program.getMovie().getId_movie(), program.getHall().getId_hall());
		
		map.put("halls", hallService.list());
		map.put("movies", movieService.list());
		map.put("programForm", programForm);
		return "backend/programForm";
	}

	@RequestMapping(value = "/admin/program/add", method = RequestMethod.POST)	
	public String addProgram(ModelMap model, @Valid @ModelAttribute("programForm")
	 ProgramForm programForm, BindingResult result, RedirectAttributes ra, Locale locale) throws IndexOutOfBoundsException, ParseException {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("halls", hallService.list());
			model.put("movies", movieService.list());
			model.put("error", "1");
            return "backend/programForm";
		}
		
		Program program = new Program();
				
		program.setDate(parseRFC3339Date(programForm.getDate()));
		program.setHall(hallService.getHall(programForm.getId_hall()));
		program.setMovie(movieService.getMovie(programForm.getId_movie()));
		
		programService.addProgram(program);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("programsavestatus", null, locale));
		
		return "redirect:/admin/program";
	}
	
	@RequestMapping(value = "/admin/program/udpate", method = RequestMethod.POST)	
	public String updateProgram(ModelMap model, @Valid @ModelAttribute("programForm")
	 ProgramForm programForm, BindingResult result, RedirectAttributes ra, Locale locale) throws IndexOutOfBoundsException, ParseException {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/programForm?error";
		}
		
		Program program = new Program();
		program.setId_program(programForm.getId_program());
		
		program.setDate(parseRFC3339Date(programForm.getDate()));
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
	
	public static Timestamp parseRFC3339Date(String datestring) throws java.text.ParseException, IndexOutOfBoundsException{
	    Date d = new Date();

	        //if there is no time zone, we don't need to do any special parsing.
	    if(datestring.endsWith("Z")){
	      try{
	        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");//spec for RFC3339					
	        d = s.parse(datestring);		  
	      }
	      catch(java.text.ParseException pe){//try again with optional decimals
	        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");//spec for RFC3339 (with fractional seconds)
	        s.setLenient(true);
	        d = s.parse(datestring);		  
	      }
	      
	      long time = d.getTime();
	      return new Timestamp(time);
	    }

	         //step one, split off the timezone. 
	    String firstpart = datestring.substring(0,datestring.lastIndexOf('-'));
	    String secondpart = datestring.substring(datestring.lastIndexOf('-'));
			
	          //step two, remove the colon from the timezone offset
	    secondpart = secondpart.substring(0,secondpart.indexOf(':')) + secondpart.substring(secondpart.indexOf(':')+1);
	    datestring  = firstpart + secondpart;
	    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");//spec for RFC3339		
	    try{
	      d = s.parse(datestring);		  
	    }
	    catch(java.text.ParseException pe){//try again with optional decimals
	      s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");//spec for RFC3339 (with fractional seconds)
	      s.setLenient(true);
	      d = s.parse(datestring);		  
	    }
	    long time = d.getTime();
	     return new Timestamp(time);
	  }
}
