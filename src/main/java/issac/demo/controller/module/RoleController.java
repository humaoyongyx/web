package issac.demo.controller.module;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.mapper.MenuMapperDao;
import issac.demo.model.MenuBean;
import issac.demo.model.RoleBean;
import issac.demo.model.RoleResourceBean;
import issac.demo.service.module.ResourceService;
import issac.demo.service.module.RoleService;

@Controller
@RequestMapping("/module/role")
public class RoleController {
	@Resource
	MenuMapperDao menuMapperDao;
	@Resource
	RoleService roleService;
	@Resource
	ResourceService resourceService;

	@RequestMapping("/showRoleResourcePage")
	public String showRoleResourcePage(Integer roleId, HttpServletRequest request) {
		HashMap<Integer, LinkedHashMap<Integer, LinkedList<RoleResourceBean>>> roleResourcePage = roleService.getRoleResourceMapPage(roleId);
		List<MenuBean> menuList = menuMapperDao.getAll();
		HashMap<Integer, MenuBean> menuMap = new HashMap<>();
		for (MenuBean menuBean : menuList) {
			menuMap.put(menuBean.getId(), menuBean);
		}
		request.setAttribute("roleResourcePage", roleResourcePage);
		request.setAttribute("menuMap", menuMap);
		return "/module/roleResourcePage";
	}

	@RequestMapping("/addOrUpdate")
	public @ResponseBody String addOrUpdate(Integer id, String name, Integer[] resourceIds) {

		if (name != null) {
			RoleBean roleBean = roleService.getRoleBeanByName(name);
			if (roleBean != null) {
				if (id == null || id != roleBean.getId()) {
					return "duplicate";
				}

			}
		}

		if (id != null) {
			roleService.updateRoleAndResource(id, name, resourceIds);
		} else {
			RoleBean roleBean = new RoleBean();
			roleBean.setName(name);
			roleService.insert(roleBean);
			List<RoleResourceBean> resourceBeans = roleService.getResourceList(roleBean.getId(), resourceIds);
			resourceService.insertResourceBatch(resourceBeans);

		}
		System.out.println(Arrays.toString(resourceIds));
		return "success";
	}

	@RequestMapping("/deleteAll")
	public @ResponseBody Object deleteAll(@RequestParam("ids[]") List<Integer> ids) {
		roleService.deleteAll(ids);
		return "success";
	}

}
