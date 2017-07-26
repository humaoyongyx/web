package issac.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class APIController {
	
	//@RequestMapping(value="test",produces="application/json;charset=UTF-8")
	@RequestMapping("test")
	public String test(String value){
		System.out.println(value);
		return "success";
	}

}
