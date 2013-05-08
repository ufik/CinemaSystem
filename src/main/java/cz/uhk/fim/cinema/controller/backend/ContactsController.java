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
import cz.uhk.fim.cinema.entity.Contact;
import cz.uhk.fim.cinema.service.ContactService;

@Controller
public class ContactsController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MessageSource messageSource;
	
	private void setId(Map<String, Object> map){
		map.put("id", "contacts");
	}
	
	@RequestMapping("/admin/contacts")
	public String listMovies(Map<String, Object> map) {
		this.setId(map);
		
		map.put("title", "Správa kontaktů");
		map.put("contacts", contactService.list());
		return "backend/contacts";
	}
	
	@RequestMapping(value = "/admin/contacts/form")	
	public String addFormContact(Map<String, Object> map) {
		this.setId(map);
		
		map.put("contact", new Contact());
		return "backend/contactForm";
	}
	
	@RequestMapping(value = "/admin/contacts/form/{contactId}")	
	public String addFormContact(@PathVariable("contactId") Integer contactId, Map<String, Object> map) {
		this.setId(map);
		
		map.put("contact", contactService.getContact(contactId));
		return "backend/contactForm";
	}
	
	@RequestMapping(value = "/admin/contacts/add", method = RequestMethod.POST)	
	public String addContact(ModelMap model, @Valid @ModelAttribute("contact")
	 Contact contact, BindingResult result, Locale locale, RedirectAttributes ra) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/contactForm";
		}
		
		contactService.addContact(contact);

		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("contactsavestatus", null, locale));
		
		return "redirect:/admin/contacts";
	}
	
	@RequestMapping(value = "/admin/contacts/udpate", method = RequestMethod.POST)	
	public String updateContact(ModelMap model, @Valid @ModelAttribute("contact")
	 Contact contact, BindingResult result, Locale locale, RedirectAttributes ra) {
		this.setId(model);
		
		if (result.hasErrors()) {
			model.put("error", "1");
            return "backend/contactForm";
		}
		
		contactService.updateContact(contact);
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("contactsavestatus", null, locale));
		
		return "redirect:/admin/contacts";
	}
	
	@RequestMapping("/admin/contacts/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId, Locale locale, RedirectAttributes ra) {

		contactService.removeContact(contactId);
		
		ra.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("contactdeletestatus", null, locale));
		
		return "redirect:/admin/contacts";
	}
}
