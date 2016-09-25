package issac.demo.mapper;

import java.util.List;

import issac.demo.model.UserBean;
import issac.demo.service.module.IModule;

public interface UserMapperDao extends IModule<UserBean> {

	public void insertUserSelective(UserBean userBean);

	public UserBean getUserBeanByNameId(String nameId);

	public void deleteAll(List<Integer> ids);
}