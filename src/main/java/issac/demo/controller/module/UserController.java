package issac.demo.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module/user")
public class UserController {

	@RequestMapping("/")
	public String page() {

		return "/module/userPage";
	}

}
