package directory.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import directory.business.dao.IDao;

/**
 * This class is the controller for connection
 * @author Eldoran
 *
 */
@Controller
public class LoginController {
	@Autowired
	IDao dao;
	
	/**
	 * Login a standard user
	 * @param id
	 * @param password
	 * @param request
	 * @return redirect to personList.jsp page
	 * @throws Exception
	 */
	@RequestMapping("login")
	public String login(
			@RequestParam(required = true) Long id,
			@RequestParam(required = true) String password,
			HttpServletRequest request
			) throws Exception {
		try {
			if(id != null && password != null) {
				String pwd = dao.findPerson(id).getPassword();
				if(pwd.equals(password)) {
					request.getSession().setAttribute(
							"logged", dao.findPerson(id.longValue()));
				}
			}
			return "redirect:personList";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Logout the standard user connected
	 * @param request
	 * @param response
	 * @return redirect to root page
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("logged", null);
		return "redirect:";
	}
}
