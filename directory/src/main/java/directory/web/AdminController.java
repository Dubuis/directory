package directory.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import directory.business.dao.IDao;
import directory.model.Group;
import directory.model.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static Long ADMIN_ID = 0L;
	private static String ADMIN_PASSWORD = "admin";
	
	@Autowired
	IDao dao;
	
	@ModelAttribute(value="persons")
	public Collection<Person> persons() {
		return dao.findAllPersons();
	}
	
	@ModelAttribute("groups")
	Collection<Group> groups() {
		return dao.findAllGroups();
	}
	
	@RequestMapping("")
	public String admin() {
		return "adminLogin";
	}
	
	@RequestMapping("logout")
	public String adminLogout(
			HttpServletRequest request
			) {
		request.getSession().setAttribute("admin", null);
		return "redirect:../";
	}
	
	@RequestMapping("/login")
	public String adminLogin(
			@RequestParam(required=true) Long id,
			@RequestParam(required=true) String password,
			HttpServletRequest request
			) {
		if(id != ADMIN_ID.longValue() || !password.equals(ADMIN_PASSWORD)) {
			return "redirect:";
		}
		request.getSession().setAttribute("admin", true);
		return "redirect:adminManager";
	}
	
	@RequestMapping("/adminManager")
	public String adminManager(
			HttpServletRequest request
			) {
		if(request.getSession().getAttribute("admin") == null) {
			return "redirect:../";
		}
		return "adminManager";
	}
	
	@RequestMapping("/addGroup")
	public String addGroup(
			@ModelAttribute Group group,
			@RequestParam(required=false) Boolean modified,
			HttpServletRequest request
			) {
		if(request.getSession().getAttribute("admin") == null) {
			return "redirect:../";
		}
		if(modified != null) {
			dao.saveGroup(group);
			return "redirect:adminManager";
		}
		return "adminAddGroup";
	}
	
	@RequestMapping("/editPerson")
	public String editPerson() {
		return "adminEditPerson";
	}
}
