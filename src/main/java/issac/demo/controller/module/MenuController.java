package issac.demo.controller.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.bo.params.MenuParams;
import issac.demo.dto.Result;
import issac.demo.model.UserBean;
import issac.demo.service.MenuService;
import issac.demo.utils.ExcelUtils;

@Controller
@RequestMapping("/module/menu")
public class MenuController {
	@Resource
	MenuService menuService;
	
	@RequestMapping("/")
	public String page() {

		return "/module/menuPage";
	}

	@RequestMapping("/show")
	public @ResponseBody Object show(MenuParams menuParams) {
		Map<String, Object> data = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		UserBean userBean = (UserBean) subject.getSession().getAttribute("user");
		data.put("data", menuService.getUserMenus(userBean.getId()));
		return data;
	}

	@RequestMapping(value = "/exportExcel", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportExcel(HttpServletResponse response, MenuParams menuParams) {
		String[] header = { "id", "父目录ID", "名称", "图标", "资源链接", "顺序" };
		String[] fieldNames = { "id", "pid", "text", "icon", "url", "orderNo" };
		try {
			ExcelUtils.exportExcel("目录设置", "目录设置", header, fieldNames, menuService.find(menuParams), response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/addOrUpdate")
	public @ResponseBody Object addOrUpdate(MenuParams menuParams) {
		menuService.addOrUpdate(menuParams);
		if (menuParams.getPid() != null && menuParams.getUrl() != null && !menuParams.getUrl().trim().equals("")) {
			menuService.addMenuResources(menuParams);
		}
		return Result.SuccessBean;
	}

	@RequestMapping("/delete")
	public @ResponseBody Object delete(MenuParams menuParams) {
		menuService.delete(menuParams);
		return Result.SuccessBean;
	}

	@RequestMapping("/deleteAll")
	public @ResponseBody Object deleteAll(@RequestParam(value = "ids[]", required = false) List<Integer> ids) {
		if (ids != null && !ids.isEmpty()) {
			menuService.deleteAll(ids);
		}
		return "success";
	}
}
