package issac.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Resource
	TestService testService;

	@RequestMapping("/test1")
	public @ResponseBody Object testOne() {
		return testService.getData();
	}

	@RequestMapping("/test2")
	@ResponseBody
	public void testTwo() {
		// testService.insert();
	}

}
