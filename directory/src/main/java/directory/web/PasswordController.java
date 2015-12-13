package directory.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import directory.business.dao.IDao;
import directory.model.Person;

/**
 * This class is used as controller for modification of standard user password
 * @author Eldoran
 * TODO Add back for user
 */
@Controller
public class PasswordController {
	@Autowired
	IDao dao;
	
	/**
	 * Used for print form and save modification after validation
	 * @param currentPassword
	 * @param newPassword
	 * @param confirmPassword
	 * @param id
	 * @param modified
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("editPassword")
	public String editPassword(
			@RequestParam(required=false) String currentPassword,
			@RequestParam(required=false) String newPassword,
			@RequestParam(required=false) String confirmPassword,
			@RequestParam(required=true) Long id,
			@RequestParam(required=false) Boolean modified,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		Person logged = (Person) request.getSession().getAttribute("logged");
		if(logged == null) {
			return "redirect:";
		}
		if(logged.getId() != id.longValue()) {
			return "redirect:";
		}
		if(currentPassword == null || currentPassword.isEmpty() ||
				newPassword == null || newPassword.isEmpty() ||
				confirmPassword == null || confirmPassword.isEmpty()) {
			return "editPassword";
		}
		if(modified != null && modified) {
			if(!currentPassword.equals(logged.getPassword())) {
				return "editPassword";
			}
			if(!newPassword.equals(confirmPassword)) {
				return "editPassword";
			}
			if(!newPassword.equals(currentPassword)) {
				Person p = dao.findPerson(id);
				p.setPassword(newPassword);
				dao.savePerson(p);
			}
			return "redirect:";
		}
		return "editPassword";
	}
}
