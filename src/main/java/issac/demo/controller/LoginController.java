package issac.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.dto.Result;
import issac.demo.utils.CommonUtils;

@Controller
public class LoginController {


	@RequestMapping("/main")
	public String mainPage(HttpServletRequest request) {
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Object loginValidate(String username, String password, String rememberMe) {

		System.out.println(username);
		System.out.println(password);
		System.out.println(rememberMe);
		boolean rememberMeFlag = false;
		if (rememberMe != null) {
			rememberMeFlag = true;
		}
		if (CommonUtils.isNotEmpty(username, password)) {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMeFlag);
			try {
				subject.login(token);
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
