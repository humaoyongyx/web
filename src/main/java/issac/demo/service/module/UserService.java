package issac.demo.service.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import issac.demo.model.UserBean;

public class UserService {

	public List<UserBean> getPageList() {

		return generate();

	}

	public List<UserBean> generate() {
		UserBean userBean = new UserBean();
		userBean.setId(0);
		userBean.setName("root");
		userBean.setNameId("root");
		userBean.setCreateTime(new Date());
		userBean.setStatus(1);
		userBean.setGroupId(0);

		UserBean userBean2 = new UserBean();
		userBean2.setId(0);
		userBean2.setName("user");
		userBean2.setNameId("user");
		userBean2.setCreateTime(new Date());
		userBean2.setStatus(1);
		userBean.setGroupId(1);
		List<UserBean> userBeans = new ArrayList<>();
		userBeans.add(userBean);
		userBeans.add(userBean2);

		return userBeans;

	}


}
