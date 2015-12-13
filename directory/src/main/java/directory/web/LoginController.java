package directory.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import directory.business.dao.IDao;

@Controller
public class LoginController {
	@Autowired
	IDao dao;
	
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
	
	@RequestMapping("/logout")
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("logged", null);
		return "redirect:";
	}
}
