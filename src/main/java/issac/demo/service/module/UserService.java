package issac.demo.service.module;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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

	private String algorithmName = "md5";
	private final int hashIterations = 2;

	public String encryptPassword(String password, String salt) {
		String encryptPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
		return encryptPassword;
	}

	public void encryptPassword(UserBean userBean) {
		String encryptPassword = new SimpleHash(algorithmName, userBean.getPassword(), ByteSource.Util.bytes(userBean.getSalt()), hashIterations).toHex();
		userBean.setPassword(encryptPassword);
	}
}
