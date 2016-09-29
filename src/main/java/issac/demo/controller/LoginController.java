package issac.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.Result;
import issac.demo.mapper.ResourceMapperDao;
import issac.demo.mapper.UserMapperDao;
import issac.demo.model.UserBean;
import issac.demo.utils.CommonUtils;

@Controller
public class LoginController {

	@Resource
	UserMapperDao userMapperDao;
	@Resource
	ResourceMapperDao resourceMapperDao;

	@RequestMapping("/main")
	public String mainPage(HttpServletRequest request) {
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	public @ResponseBody Object unauthorizedPage(HttpServletRequest request) {
		return Result.FailBean.setMessage("您没有权限，请联系管理员！");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Object loginValidate(String username, String password, String rememberMe) {

		boolean rememberMeFlag = false;
		if (rememberMe != null) {
			rememberMeFlag = true;
		}
		if (CommonUtils.isNotEmpty(username, password)) {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
			try {
				subject.login(token);
				Session session = subject.getSession();
				session.setTimeout(1800000);//设置半小时session过期时间
				UserBean user = userMapperDao.getUserBeanByNameId(username);
				if (!CommonUtils.isNotEmpty(user.getPhoto())) {
					user.setPhoto("/pics/default_avatar_male.jpg");
				}
				session.setAttribute("permission", resourceMapperDao.getResourceByUserId(user.getId()));
				session.setAttribute("user", user);
			} catch (LockedAccountException e) {
				return Result.FailBean.setMessage("用户已被锁定，请联系管理员！");
			} catch (AuthenticationException e) {
				return Result.FailBean.setMessage("用户名或者密码错误！");
			}
		} else {
			return Result.FailBean.setMessage("用户名或者密码不能为空！");
		}

		return Result.SuccessBean;
	}

}
