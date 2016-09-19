package issac.demo.controller.module;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.bo.params.MenuParams;
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
		data.put("data", menuService.find(menuParams));
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
		return "success";
	}

	@RequestMapping("/delete")
	public @ResponseBody Object delete(MenuParams menuParams) {
		menuService.delete(menuParams);
		return "success";
	}
}
