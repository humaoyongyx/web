package issac.demo.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module/userGroup")
public class UserGroupController {
	@RequestMapping("/")
	public String page() {

		return "/module/userGroupPage";
	}
}
