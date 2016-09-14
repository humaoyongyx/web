package issac.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import issac.demo.dto.Result;

@Controller
public class LoginController {

	@RequestMapping("/main")
	public String mainPage(HttpServletRequest request) {
		Result result = new Result();
		result.setMessage("message\"+sdf\"sdsd");
		result.setStatus(1);
		request.setAttribute("obj", JSON.toJSON(result));
		return "main";
	}

}
