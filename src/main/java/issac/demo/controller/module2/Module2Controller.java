package issac.demo.controller.module2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.controller.module.ControllerUtils;
import issac.demo.dto.Result;
import issac.demo.model.ModuleBean;
import issac.demo.model.UserBean;
import issac.demo.service.module2.IModule2Dao;
import issac.demo.service.module2.Module2Service;
import issac.demo.utils.SpringUtils;

@Controller
@RequestMapping("/module2/{moduleId}")
public class Module2Controller {
	private Logger logger = Logger.getLogger(Module2Controller.class);

	@RequestMapping("/")
	public String page(@PathVariable("moduleId") String moduleId, HttpServletRequest request) {
		ControllerUtils.handlePermissions(request, moduleId);
		logger.info("module2<" + moduleId + "> [/] invoked,will show page:" + "/module2/" + moduleId + "Page");
		return "module2/" + moduleId + "Page";
	}


	@RequestMapping("/show")
	public @ResponseBody Object show(@PathVariable("moduleId") final String moduleId, HttpServletRequest request) {
		UserBean userBean = ControllerUtils.getCurrentUser();
		Map<String, Object> params = ControllerUtils.handleRequestParamsToMap(request);
		params.put("roleId", userBean.getRoleId());
		logger.info("module2<" + moduleId + "> [/show] invoked!");
		Map<String, Object> data = new HashMap<>();
		Module2Service<?> moduleService2;
		
		List<?> list = null;
		if (SpringUtils.containsBean(moduleId + "Service")) {
			moduleService2 = SpringUtils.getBean(moduleId + "Service");
			list = moduleService2.show(params);
		} else {
			if (!SpringUtils.containsBean(moduleId + "MapperDao")) {
				return Result.fail("没有定义：" + moduleId + "MapperDao");
			}
			list = new Module2Service<ModuleBean>() {
				@Override
				public IModule2Dao<ModuleBean> getModule() {
					return SpringUtils.getBean(moduleId + "MapperDao");
				}
			}.show(params);
		}
		data.put("data", list);
		return data;
	}

	@RequestMapping("/add")
	public @ResponseBody Object add(@PathVariable("moduleId") final String moduleId, HttpServletRequest request) {
		Map<String, Object> params = ControllerUtils.handleRequestParamsToMap(request);
		logger.info("module2<" + moduleId + "> [/add] invoked!");
		Module2Service<?> moduleService2;

		if (SpringUtils.containsBean(moduleId + "Service")) {
			moduleService2 = SpringUtils.getBean(moduleId + "Service");
			Result check = moduleService2.check(params);
			if (check != null) {
				return check;
			}

			Result checkAdd = moduleService2.checkAdd(params);
			if (checkAdd != null) {
				return checkAdd;
			}
			moduleService2.add(params);
		} else {
			if (!SpringUtils.containsBean(moduleId + "MapperDao")) {
				return Result.fail("没有定义：" + moduleId + "MapperDao");
			}
			new Module2Service<ModuleBean>() {
				@Override
				public IModule2Dao<ModuleBean> getModule() {
					return SpringUtils.getBean(moduleId + "MapperDao");
				}
			}.add(params);
		}
		return Result.SuccessBean;
	}

	@RequestMapping("/update")
	public @ResponseBody Object update(@PathVariable("moduleId") final String moduleId, HttpServletRequest request) {
		Map<String, Object> params = ControllerUtils.handleRequestParamsToMap(request);
		logger.info("module<" + moduleId + "> [/update] invoked!");
		Module2Service<?> moduleService2;

		if (SpringUtils.containsBean(moduleId + "Service")) {
			moduleService2 = SpringUtils.getBean(moduleId + "Service");
			Result check = moduleService2.check(params);
			if (check != null) {
				return check;
			}
			Result checkUpdate = moduleService2.checkUpdate(params);
			if (checkUpdate != null) {
				return checkUpdate;
			}
			moduleService2.update(params);
		} else {
			if (!SpringUtils.containsBean(moduleId + "MapperDao")) {
				return Result.fail("没有定义：" + moduleId + "MapperDao");
			}
			new Module2Service<ModuleBean>() {
				@Override
				public IModule2Dao<ModuleBean> getModule() {
					return SpringUtils.getBean(moduleId + "MapperDao");
				}
			}.update(params);
		}
		return Result.SuccessBean;
	}


	@RequestMapping("/deleteAll")
	public @ResponseBody Object deleteAll(@PathVariable("moduleId") final String moduleId, @RequestParam("ids[]") List<Integer> ids) {
		logger.info("module2<" + moduleId + "> [/deleteAll] invoked!");

		Module2Service<?> moduleService2;
		if (SpringUtils.containsBean(moduleId + "Service")) {
			moduleService2 = SpringUtils.getBean(moduleId + "Service");
			Result check2 = moduleService2.checkDelete(ids);
			if (check2 != null) {
				return check2;
			}
			moduleService2.deleteAll(ids);
		} else {
			if (!SpringUtils.containsBean(moduleId + "MapperDao")) {
				return Result.fail("没有定义：" + moduleId + "MapperDao");
			}
			new Module2Service<ModuleBean>() {
				@Override
				public IModule2Dao<ModuleBean> getModule() {
					return SpringUtils.getBean(moduleId + "MapperDao");
				}
			}.deleteAll(ids);
		}
		return Result.SuccessBean;
	}

}
