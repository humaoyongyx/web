package issac.demo.controller.module;

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

import issac.demo.dto.Result;
import issac.demo.mapper.MenuMapperDao;
import issac.demo.model.MenuBean;
import issac.demo.model.RoleBean;
import issac.demo.model.RoleResourceBean;
import issac.demo.model.UserBean;
import issac.demo.model.UserRoleBean;
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
	public @ResponseBody Result addOrUpdate(Integer id, String name, Integer[] resourceIds) {

		if (name != null) {
			RoleBean roleBean = roleService.getRoleBeanByName(name);
			if (roleBean != null) {
				if (id == null || id != roleBean.getId()) {
					return Result.FailBean.setMessage(name + ":该角色名已经存在，请重新填写！");
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
		return Result.SuccessBean;
	}

	@RequestMapping("/deleteAll")
	public @ResponseBody Result deleteAll(@RequestParam("ids[]") List<Integer> ids) {
		List<UserRoleBean> findUserRoleByRoleIds = roleService.findUserRoleByRoleIds(ids);
		if (findUserRoleByRoleIds != null) {
			return Result.FailBean.setMessage("删除的角色有关联的用户，请先解除这些角色与这些用户的关联！");
		}
		roleService.deleteAll(ids);
		return Result.SuccessBean;
	}

	@RequestMapping("/getUserRoles")
	public @ResponseBody Object getUserRoleList(Integer userId) {
		UserBean userBean=new UserBean();
		userBean.setId(userId);
		return roleService.getUserRoleList(userBean);
	}

}
