package issac.demo.controller.module;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.Result;
import issac.demo.model.UserBean;
import issac.demo.service.module.ModuleService;
import issac.demo.utils.ExcelUtils;

@Controller
@RequestMapping("/module/{moduleId}")
public class ModuleController {

	private Logger logger = Logger.getLogger(ModuleController.class);

	@RequestMapping("/")
	public String page(@PathVariable("moduleId") String moduleId, HttpServletRequest request) {

		ControllerUtils.handlePermissions(request, moduleId);
		logger.info("module<" + moduleId + "> [/] invoked!");
		return "module/" + moduleId + "Page";
	}



	@RequestMapping("/show")
	public @ResponseBody Object show(@PathVariable("moduleId") String moduleId, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UserBean userBean = (UserBean) subject.getSession().getAttribute("user");
		Map<String, Object> params = handleRequestParamsToMap(request);
		params.put("userId", userBean.getId());
		params.put("roleId", userBean.getRoleId());
		logger.info("module<" + moduleId + "> [/show] invoked!");
		Map<String, Object> data = new HashMap<>();
		data.put("data", new ModuleService<>(moduleId).getPageList(params));
		return data;
	}



	@RequestMapping("/addOrUpdate")
	public @ResponseBody Object addOrUpdate(@PathVariable("moduleId") String moduleId, HttpServletRequest request) {
		Map<String, Object> params = handleRequestParamsToMap(request);
		logger.info("module<" + moduleId + "> [/addOrUpdate] invoked!");
		new ModuleService<>(moduleId).addOrUpdate(params);
		return Result.SuccessBean;
	}

	@RequestMapping("/delete")
	public @ResponseBody Object delete(@PathVariable("moduleId") String moduleId, HttpServletRequest request) {
		Map<String, Object> params = handleRequestParamsToMap(request);
		logger.info("module<" + moduleId + "> [/delete] invoked!");
		new ModuleService<>(moduleId).delete(params);
		return Result.SuccessBean;
	}

	@RequestMapping("/deleteAll")
	public @ResponseBody Object deleteAll(@PathVariable("moduleId") String moduleId, @RequestParam("ids[]") List<Integer> ids) {
		logger.info("module<" + moduleId + "> [/deleteAll] invoked!");
		new ModuleService<>(moduleId).deleteAll(ids);
		return Result.SuccessBean;
	}

	@RequestMapping(value = "/exportExcel", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportExcel(HttpServletResponse response, @PathVariable("moduleId") String moduleId, HttpServletRequest request) {
		Map<String, Object> params = handleRequestParamsToMap(request);
		logger.info("module<" + moduleId + "> [/exportExcel] invoked!");
		try {
			ExcelUtils.exportExcel("导出", new ModuleService<>(moduleId).getPageList(params), response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Map<String, Object> handleRequestParamsToMap(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			String value = request.getParameter(key);
			if (value == null || value.trim().equals("")) {
				value = null;
			}
			params.put(key, value);
		}
		return params;
	}
}
