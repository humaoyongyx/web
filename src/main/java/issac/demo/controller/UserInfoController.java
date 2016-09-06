package issac.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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

	Logger logger = Logger.getLogger(UserInfoController.class);
	@Resource
	UserInfoService userInfoService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String userInfoPage(HttpServletRequest request) {
		List<UserInfo> userInfos = userInfoService.findAll();
		request.setAttribute("userInfos", userInfos);
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
		logger.info("begin to delete" + id);
		Result result = new Result();
		if (id == null) {
			result.setMessage("id can not be null");
			return result;
		}
		userInfoService.delete(id);
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object insert(UserInfo userInfo) {
		Result result = new Result();
		try {
			userInfoService.insert(userInfo);
		} catch (Exception e) {
			result.setMessage("insert error");
			return result;
		}
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object update(UserInfo userInfo) {
		Result result = new Result();

		if (userInfo.getId() == null) {
			result.setMessage("userInfo id can not be null");
			return result;
		}
		userInfoService.update(userInfo);
		result.setStatus(Result.SUCCESS);
		return result;
	}
}
