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

/**
 * This class is the controller of admin interface
 * @author Eldoran
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static Long ADMIN_ID = 0L;
	private static String ADMIN_PASSWORD = "admin";
	
	@Autowired
	IDao dao;
	
	/**
	 * Use as ModelAttribute
	 * @return all persons
	 */
	@ModelAttribute(value="persons")
	public Collection<Person> persons() {
		return dao.findAllPersons();
	}
	
	/**
	 * Use as ModelAttribute
	 * @return all groups
	 */
	@ModelAttribute("groups")
	Collection<Group> groups() {
		return dao.findAllGroups();
	}
	
	/**
	 * Root of admin page
	 * @return adminLogin.jsp page
	 */
	@RequestMapping("")
	public String admin() {
		return "adminLogin";
	}
	
	/**
	 * Logout an admin
	 * @param request
	 * @return root page of standard user
	 */
	@RequestMapping("logout")
	public String adminLogout(
			HttpServletRequest request
			) {
		request.getSession().setAttribute("admin", null);
		return "redirect:../";
	}
	
	/**
	 * Login an admin
	 * @param id
	 * @param password
	 * @param request
	 * @return redirect to root if id/pwd false, redirect to adminManager.jsp else
	 */
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
	
	/**
	 * adminManager page servlet
	 * @param request
	 * @return redirect to root of standard user if not logged, in adminManager.jsp page else.
	 */
	@RequestMapping("/adminManager")
	public String adminManager(
			HttpServletRequest request
			) {
		if(request.getSession().getAttribute("admin") == null) {
			return "redirect:../";
		}
		return "adminManager";
	}
	
	/**
	 * addGroup page servlet
	 * @param group
	 * @param modified
	 * @param request
	 * @return redirect to root standard page id not logged in adminAddGroup.jsp page else.
	 */
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
	
	/**
	 * TODO
	 * @return
	 */
	@RequestMapping("/editPerson")
	public String editPerson() {
		return "adminEditPerson";
	}
}
