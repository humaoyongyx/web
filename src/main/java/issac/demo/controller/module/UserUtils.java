package issac.demo.controller.module;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;

import issac.demo.model.UserBean;

public class UserUtils {

	public static UserBean getCurrentUser() {
		UserBean userBean = (UserBean) SecurityUtils.getSubject().getSession().getAttribute("user");
		return userBean;
	}

	public static void handlePermissions(HttpServletRequest request, String moduleId) {
		HashMap<String, String> permissonMap = (HashMap<String, String>) SecurityUtils.getSubject().getSession().getAttribute("permissionMap");
		UserBean currentUser = UserUtils.getCurrentUser();
		if (currentUser.getRoleId().contains("1")) {
			request.setAttribute("add", true);
			request.setAttribute("delete", true);
			request.setAttribute("modify", true);
			request.setAttribute("show", true);
		} else {
			request.setAttribute("add", false);
			request.setAttribute("delete", false);
			request.setAttribute("modify", false);
			request.setAttribute("show", false);
			String action = permissonMap.get(moduleId);
			if (action != null) {
				if (action.contains("add")) {
					request.setAttribute("add", true);
				}
				if (action.contains("delete")) {
					request.setAttribute("delete", true);
				}
				if (action.contains("modify")) {
					request.setAttribute("modify", true);
				}
				if (action.contains("show")) {
					request.setAttribute("show", true);
				}
			}
		}
	}
}
