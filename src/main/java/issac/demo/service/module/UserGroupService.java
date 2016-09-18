package issac.demo.service.module;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import issac.demo.model.UserGroupBean;

@Service
public class UserGroupService {
	public List<UserGroupBean> getPageList() {

		return generate();

	}

	public List<UserGroupBean> generate() {
		UserGroupBean userGroupBean = new UserGroupBean();
		userGroupBean.setId(0);
		userGroupBean.setName("root");
		userGroupBean.setResourceId(0);
		userGroupBean.setMenuId(1);
		userGroupBean.setResourceName("菜单查看");
		userGroupBean.setUrl("/module/menu/");

		List<UserGroupBean> userGroupBeans = new ArrayList<>();
		userGroupBeans.add(userGroupBean);

		return userGroupBeans;
	}
}
