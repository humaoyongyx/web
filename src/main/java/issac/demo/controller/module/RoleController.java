package issac.demo.controller.module;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import issac.demo.model.RoleResourceBean;
import issac.demo.service.module.RoleService;

@Controller
@RequestMapping("/module/role")
public class RoleController {

	@Resource
	RoleService roleService;
	@RequestMapping("/showRoleResourcePage")
	public String showRoleResourcePage(Integer roleId, HttpServletRequest request) {
		LinkedHashMap<Integer, LinkedList<RoleResourceBean>> roleResourcePage = roleService.getRoleResourcePage(roleId);
		request.setAttribute("roleResourcePage", roleResourcePage);
		return "/module/roleResourcePage";

	}
}
