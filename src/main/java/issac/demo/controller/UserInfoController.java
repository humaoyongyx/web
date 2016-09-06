package issac.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.model.UserInfo;
import issac.demo.service.UserInfoService;
import issac.demo.vo.Result;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	@Resource
	UserInfoService userInfoService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String userInfoPage() {
		return "userinfo";
	}

	@RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object getAll() {
		List<UserInfo> userInfos = userInfoService.findAll();
		Map<String, Object> data = new HashMap<>();
		data.put("data", userInfos);
		return data;
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object delete(Integer id) {
		if (id == null) {
			Result result = new Result();
			result.setMessage("id can not be null");
			return result;
		}
		System.out.println(id);
		List<UserInfo> userInfos = userInfoService.findAll();
		Map<String, Object> data = new HashMap<>();
		data.put("data", userInfos);
		return data;
	}

}
