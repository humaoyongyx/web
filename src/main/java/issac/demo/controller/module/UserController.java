package issac.demo.controller.module;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.Result;
import issac.demo.model.UserBean;
import issac.demo.model.UserRoleBean;
import issac.demo.service.module.UserService;

@Controller
@RequestMapping("/module/user")
public class UserController {
	@Resource
	UserService userService;
	@RequestMapping("/addOrUpdate")
	public @ResponseBody Object addOrUpdate(UserBean userBean, Integer[] roleIdTo) {
		UserBean userBeanByNameId = userService.getUserBeanByNameId(userBean.getNameId());
		if (userBeanByNameId != null) {
			Integer idExist = userBeanByNameId.getId();
			Integer id = userBean.getId();
			if (id == null || id != idExist) {
				return Result.FailBean.setMessage(userBean.getNameId() + ":此用户名ID已存在！");
			}

		}
		if (userBean.getId() != null) {
			userService.updateUser(userBean);
			Integer userId = userBean.getId();
			userService.deleteUserRole(userId);
			if (roleIdTo != null) {
				List<UserRoleBean> userRoleBeans = new ArrayList<>();
				UserRoleBean bean = null;
				for (Integer roleId : roleIdTo) {
					bean = new UserRoleBean();
					bean.setUserId(userId);
					bean.setRoleId(roleId);
					userRoleBeans.add(bean);
				}
				userService.addUserRole(userRoleBeans);
			}
		} else {
			userService.addUser(userBean);
			Integer userId = userBean.getId();
			System.out.println(userId);
			if (roleIdTo != null) {
				List<UserRoleBean> userRoleBeans = new ArrayList<>();
				UserRoleBean bean = null;
				for (Integer roleId : roleIdTo) {
					bean = new UserRoleBean();
					bean.setUserId(userId);
					bean.setRoleId(roleId);
					userRoleBeans.add(bean);
				}
				userService.addUserRole(userRoleBeans);
			}
		}
		return Result.SuccessBean;
	}

	@RequestMapping("/deleteAll")
	public @ResponseBody Result deleteAll(@RequestParam("ids[]") List<Integer> ids) {
		userService.deleteAll(ids);
		return Result.SuccessBean;
	}
}
