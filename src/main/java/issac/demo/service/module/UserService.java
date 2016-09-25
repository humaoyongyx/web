package issac.demo.service.module;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.mapper.RoleMapperDao;
import issac.demo.mapper.UserMapperDao;
import issac.demo.model.UserBean;
import issac.demo.model.UserRoleBean;
import issac.demo.utils.CommonUtils;

@Service
public class UserService {
	@Resource
	UserMapperDao userMapperDao;

	@Resource
	RoleMapperDao roleMapperDao;

	public void addUser(UserBean userBean) {
		userMapperDao.insertUserSelective(userBean);
	}

	public void updateUser(UserBean userBean) {
		userMapperDao.updateByPrimaryKeySelective(CommonUtils.beanToMap(userBean));
	}

	public void addUserRole(List<UserRoleBean> userRoleBeans) {
		roleMapperDao.insertBatchUserRole(userRoleBeans);
	}

	public void deleteUserRole(Integer userId) {
		roleMapperDao.deleteUserRoleByUserId(userId);
	}

	public UserBean getUserBeanByNameId(String nameId) {
		return userMapperDao.getUserBeanByNameId(nameId);
	}

	public void deleteAll(List<Integer> ids) {
		userMapperDao.deleteAll(ids);
		roleMapperDao.deleteUserRoleByUserIds(ids);
	}
}
