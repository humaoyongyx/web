package issac.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.TreeViewResult;
import issac.demo.service.MenuService;

@Controller
@RequestMapping("/basic")
public class BasicController {
	@Resource
	MenuService menuService;

	@RequestMapping("/menus")
	public @ResponseBody TreeViewResult getMenus() {
		return menuService.getTreeViewMenus();
	}
	
}
