package issac.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.bo.params.MenuParams;
import issac.demo.dto.TreeViewResult;
import issac.demo.service.MenuService;
import issac.demo.utils.ExcelUtils;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	MenuService menuService;

	@RequestMapping("/getMenus")
	public @ResponseBody TreeViewResult getMenus() {
		return menuService.getTreeViewMenus();
	}

	@RequestMapping("/settings")
	public String menuSettings() {
		return "menu/settings";
	}

	@RequestMapping("/getAll")
	public @ResponseBody Object getAll(MenuParams menuParams) {
		Map<String, Object> data = new HashMap<>();
		data.put("data", menuService.getAll(menuParams));
		return data;
	}

	@RequestMapping(value = "/exportExcel", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportExcel(HttpServletResponse response, MenuParams menuParams) {
		String[] header = { "id", "父目录ID", "名称", "图标", "资源链接", "顺序" };
		String[] fieldNames = { "id", "pid", "text", "icon", "url", "orderNo" };
		try {
			ExcelUtils.exportExcel("目录设置", "目录设置", header, fieldNames, menuService.getAll(menuParams), response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/add")
	public @ResponseBody Object add(MenuParams menuParams) {
		menuService.add(menuParams);
		return "success";
	}

	@RequestMapping("/delete")
	public @ResponseBody Object delete(MenuParams menuParams) {
		menuService.delete(menuParams);
		return "success";
	}
}
