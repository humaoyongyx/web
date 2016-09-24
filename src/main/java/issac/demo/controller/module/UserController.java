package issac.demo.controller.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.model.UserBean;

@Controller
@RequestMapping("/module/user")
public class UserController {
	@RequestMapping("/addOrUpdate")
	public @ResponseBody Object addOrUpdate(HttpServletRequest request, UserBean userBean, String[] roleIdFrom, String[] roleIdTo) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			System.out.println(entry.getKey() + ":");
			String[] value = entry.getValue();
			for (String string : value) {
				System.out.print(string + ",");
			}
			System.out.println("");
		}
		List<Integer> list = new ArrayList<>();
		System.out.println(userBean);
		System.out.println(Arrays.toString(roleIdFrom));
		System.out.println(Arrays.toString(roleIdTo));
		return "fail";
	}
}
