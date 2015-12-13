package directory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController {
	@RequestMapping("E403")
	public String E403() {
		return "E403";
	}
	
	@RequestMapping("E404")
	public String E404() {
		return "E404";
	}
	
	@RequestMapping("E500")
	public String E500() {
		return "E500";
	}
	
	@RequestMapping("Error")
	public String Error() {
		return "Error";
	}
}
