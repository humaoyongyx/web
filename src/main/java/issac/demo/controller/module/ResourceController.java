package issac.demo.controller.module;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import issac.demo.bo.params.ResourceParams;
import issac.demo.model.ResourceBean;
import issac.demo.service.module.ResourceService;

@Controller
@RequestMapping("/module/resource")
public class ResourceController {
	@Resource
	ResourceService resourceService;
	
	@RequestMapping("/")
	public String page() {

		return "/module/resourcePage";
	}

	@RequestMapping("/show")
	public String show(HttpServletRequest request, ResourceParams params) {
		List<ResourceBean> resourceBeans = resourceService.getResourceByMenuId(params);
		request.setAttribute("resourceBeans", resourceBeans);
		return "/module/resourcePagePart";
	}

}
