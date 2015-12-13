package directory.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import directory.business.dao.IDao;
import directory.model.Person;

@Controller
public class PersonController {
	@Autowired
	IDao dao;

	PersonValidator personValidator = new PersonValidator();

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute("person")
	Person person(@RequestParam(required = true) Long idPerson) {
		try {
			return dao.findPerson(idPerson.longValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("editPerson")
	public String personFormController(
			@RequestParam(required=true) Long idPerson,
			@RequestParam(required=false) String birthDate,
			@RequestParam(required=false) Boolean modified,
			@ModelAttribute Person person,
			BindingResult result,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			try {
				Person logged = 
						(Person) request.getSession().getAttribute("logged");
				if(logged == null) {
					return "redirect:";
				} else {
					if(logged.getId() != idPerson.longValue()) {
						return "redirect:";
					}
				}
			} catch (Exception e) {
				throw e;
			}
			if(idPerson != null) {
				if(modified != null) {
					person.setPassword(
							dao.findPerson(idPerson.longValue()).getPassword());
					personValidator.validate(person, result);
					if(result.hasErrors()) {
						return "personForm";
					} else {
						dao.savePerson(person);
						return "redirect:";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "personForm";
	}
}
