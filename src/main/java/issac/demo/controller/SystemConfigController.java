package issac.demo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.controller.module.ControllerUtils;
import issac.demo.dto.Result;
import issac.demo.scheduler.SchedulerService;

@Controller
@RequestMapping("sys")
public class SystemConfigController {

	@Resource
	SchedulerService schedulerService;

	@RequestMapping("sysConfig/")
	public String page(HttpServletRequest request) {
		ControllerUtils.handlePermissions(request, "sysConfig");
		return "sys/sysConfigPage";
	}

	@RequestMapping("showScheduler/")
	public String showSchedulerPage(HttpServletRequest request) {
		Map<String, Object> params = ControllerUtils.handleRequestParamsToMap(request);
		request.setAttribute("schedulerList", schedulerService.show(params));
		return "sys/sysConfigSchedulerPage";
	}

	@RequestMapping("addSchedulerStop/")
	public @ResponseBody Object addSchedulerStop(HttpServletRequest request, Integer id) {
		schedulerService.stopJob(id);
		return Result.SuccessBean;
	}

	@RequestMapping("addSchedulerStart/")
	public @ResponseBody Object addSchedulerStart(HttpServletRequest request, Integer id) {
		Map<String, Object> params = ControllerUtils.handleRequestParamsToMap(request);
		schedulerService.startJob(id);
		return Result.SuccessBean;
	}

	@RequestMapping("updateSchedulerRefresh/")
	public @ResponseBody Object updateSchedulerRefresh(HttpServletRequest request, Integer id) {
		Map<String, Object> params = ControllerUtils.handleRequestParamsToMap(request);
		schedulerService.refreshJob(id);
		return Result.SuccessBean;
	}


}
