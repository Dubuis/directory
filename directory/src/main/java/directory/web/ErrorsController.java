package directory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController {
	/**
	 * Error 403 page
	 * @return
	 */
	@RequestMapping("E403")
	public String E403() {
		return "E403";
	}
	
	/**
	 * Error 404 page
	 * @return
	 */
	@RequestMapping("E404")
	public String E404() {
		return "E404";
	}
	
	/**
	 * Error 500 page
	 * @return
	 */
	@RequestMapping("E500")
	public String E500() {
		return "E500";
	}
	
	/**
	 * Error generic page
	 * @return
	 */
	@RequestMapping("Error")
	public String Error() {
		return "Error";
	}
}
