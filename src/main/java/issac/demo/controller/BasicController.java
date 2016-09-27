package issac.demo.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.TreeViewResult;
import issac.demo.model.UserBean;
import issac.demo.service.MenuService;

@Controller
@RequestMapping("/basic")
public class BasicController {
	@Resource
	MenuService menuService;

	@RequestMapping("/menus")
	public @ResponseBody TreeViewResult getMenus() {
		Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			UserBean userBean = (UserBean) session.getAttribute("user");
		if (userBean.getRoleId().contains("1")) {
			return menuService.getTreeViewMenus();
		} else {
			return menuService.getTreeViewMenus(userBean.getId());
		}

	}
	
}
