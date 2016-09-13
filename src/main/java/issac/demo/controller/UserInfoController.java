package issac.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import issac.demo.bo.params.UserInfoParams;
import issac.demo.dto.Result;
import issac.demo.model.UserInfo;
import issac.demo.model.UserInfoBean;
import issac.demo.service.UploadPictureService;
import issac.demo.service.UserInfoService;
import issac.demo.utils.CommonUtils;
import issac.demo.utils.ExcelUtils;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	Logger logger = Logger.getLogger(UserInfoController.class);
	@Resource
	UserInfoService userInfoService;
	@Resource
	UploadPictureService uploadPictureService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String userInfoPage(HttpServletRequest request) {
		List<UserInfoBean> userInfos = userInfoService.findAll();
		request.setAttribute("userInfos", userInfos);
		return "userinfo";
	}

	@RequestMapping(value = "/page2", method = RequestMethod.GET)
	public String userInfoPage2(HttpServletRequest request) {
		List<UserInfoBean> userInfos = userInfoService.findAll();
		request.setAttribute("userInfos", userInfos);
		return "userinfo2";
	}
	@RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object getAll() {
		List<UserInfoBean> userInfos = userInfoService.findAll();
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
			e.printStackTrace();
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

	@RequestMapping(value = "/exportExcel", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportExcel(HttpServletResponse response, String name) {
		System.out.println(name);
		String[] header = { "姓名", "薪资", "性别", "描述", "id" };
		String[] fieldNames = { "sex", "salary", "sex", "descn", "id" };
		try {
			//	ExcelUtils.exportExcel("test", header, userInfoService.findAll(), response);
			//ExcelUtils.exportExcel("测试", "测试sheet", header, fieldNames, userInfoService.findAll(), response);
			ExcelUtils.exportExcel("测试", userInfoService.getList(name), response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getUserInfoPage", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object getUserInfoPage(UserInfoParams params) {
		System.out.println(params);
		return userInfoService.getUserInfoPage(params);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object add(@RequestParam(value = "photoFile", required = false) MultipartFile photoFile, UserInfoParams params) {
		if (photoFile != null && !photoFile.isEmpty()) {
			System.out.println(photoFile.getOriginalFilename());
			params.setPhoto(uploadPictureService.upload(photoFile));
		}
		System.out.println(params);
		if (params.getName() == null || "".equals(params.getName().trim())) {
			return "fail";
		}
		userInfoService.insert(CommonUtils.transferClass(params, UserInfoBean.class));
		return "success";
	}
}
