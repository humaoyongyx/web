package issac.demo.controller.module;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.bo.params.ResourceBeanModel;
import issac.demo.bo.params.ResourceParams;
import issac.demo.dto.Result;
import issac.demo.model.ResourceBean;
import issac.demo.service.module.ResourceService;

@Controller
@RequestMapping("/module/resource")
public class ResourceController {
	@Resource
	ResourceService resourceService;
	

	@RequestMapping("/show")
	public String show(HttpServletRequest request, ResourceParams params) {
		List<ResourceBean> resourceBeans = resourceService.getResourceByMenuId(params);
		request.setAttribute("resourceBeans", resourceBeans);
		return "/module/resourcePagePart";
	}

	@RequestMapping("/addOrUpdate")
	public @ResponseBody Object addOrUpdate(ResourceBeanModel model) {
		List<ResourceBean> resourceBeans = model.getResource();
		if (resourceBeans != null && !resourceBeans.isEmpty()) {
			resourceService.updateResourceBatch(resourceBeans);
		}
		return Result.SuccessBean;
	}

}
