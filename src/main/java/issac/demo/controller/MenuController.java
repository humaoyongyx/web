package issac.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.TreeViewResult;
import issac.demo.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	MenuService menuService;

	@RequestMapping("/getMenus")
	public @ResponseBody TreeViewResult getMenus() {
		return menuService.getTreeViewMenus();
	}

}
