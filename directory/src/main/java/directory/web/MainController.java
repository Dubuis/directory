package directory.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import directory.business.dao.IDao;
import directory.model.Group;
import directory.model.Person;

/**
 * This class is controller for personList page
 * @author Eldoran
 *
 */
@Controller
public class MainController {
	@Autowired
	IDao dao;
	
	/**
	 * Format the date
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	/**
	 * Used as ModelAttribute
	 * @return
	 */
	@ModelAttribute(value="persons")
	public Collection<Person> persons() {
		return dao.findAllPersons();
	}
	
	/**
	 * Used as ModelAttribute
	 * @return
	 */
	@ModelAttribute("groups")
	Collection<Group> groups() {
		return dao.findAllGroups();
	}
	
	/**
	 * Root redirect to personList.jsp page
	 * @return
	 */
	@RequestMapping("")
	public String root() {
		return "redirect:personList";
	}
	
	/**
	 * personList print
	 * @return
	 */
	@RequestMapping("personList")
	public String personList() {
		return "personList"; 
	}
}
