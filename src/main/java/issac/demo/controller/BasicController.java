package issac.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import issac.demo.dto.Result;
import issac.demo.dto.TreeViewResult;
import issac.demo.model.CityBean;
import issac.demo.model.UserBean;
import issac.demo.service.CityService;
import issac.demo.service.MenuService;
import issac.demo.service.UploadPictureService;
import issac.demo.service.module.UserService;
import issac.demo.utils.CommonUtils;

@Controller
@RequestMapping("/basic")
public class BasicController {
	@Resource
	MenuService menuService;

	@Resource
	UserService userService;

	@Resource
	CityService cityService;

	@Resource
	UploadPictureService uploadPictureService;

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
	
	@RequestMapping("/showUserProfilePart")
	public String getUser(HttpServletRequest request) {
		UserBean userBean = (UserBean) SecurityUtils.getSubject().getSession().getAttribute("user");
		UserBean userBeanByNameId = userService.getUserBeanByNameId(userBean.getNameId());
		System.out.println(userBeanByNameId);
		request.setAttribute("userBean", userBeanByNameId);
		return "module/userProfilePagePart";

	}

	@ModelAttribute("user")
	public UserBean getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		UserBean userBean = (UserBean) session.getAttribute("user");
		return userBean;
	}

	@RequestMapping("/updatePassword")
	public @ResponseBody Object updatePassword(@ModelAttribute("user") UserBean user, String oldPassword, String newPassword, String confirmPassword) {

		if (CommonUtils.isNotEmpty(oldPassword, newPassword, confirmPassword)) {
			if (newPassword.equals(confirmPassword)) {
				String encryptPassword = userService.encryptPassword(oldPassword, user.getSalt());
				if (encryptPassword.equals(user.getPassword())) {
					user.setPassword(userService.encryptPassword(newPassword, user.getSalt()));
					userService.updateUser(user);
				} else {
					return Result.FailBean.setMessage("旧密码输入不正确！");
				}
			} else {
				return Result.FailBean.setMessage("新密码两次输入不一致！");
			}
		}else {
			return Result.FailBean.setMessage("输入不能为空！");
		}
		return Result.SuccessBean;
	}

	@RequestMapping("/updateUserProfile")
	public @ResponseBody Object updateUserProfile(UserBean user, MultipartFile photoFile) {

		String photoName = null;
		if (photoFile != null && !photoFile.isEmpty()) {
			if (uploadPictureService.checkFileSize(photoFile)) {
				String newFileName = System.currentTimeMillis() + CommonUtils.getFileExtension(photoFile.getOriginalFilename());
				photoName = uploadPictureService.upload(photoFile, newFileName);
			}else {
				return Result.FailBean.setMessage("图片大小不能超过1.5M！");
			}
		}

		if (user != null) {
			user.setPassword(null);
			uploadPictureService.removeFile(user.getPhoto());
			user.setPhoto(photoName);
			userService.updateUser(user);
		}
		return Result.SuccessBean;
	}

	@RequestMapping("getCites")
	public @ResponseBody List<CityBean> getCites(Integer pid) {
		return cityService.getByPid(pid);
	}

	@RequestMapping("showMap")
	public String getMap() {
		return "module/map";
	}

}
