package issac.demo.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import issac.demo.mapper.UserMapperDao;
import issac.demo.model.UserBean;

public class WebRealm extends AuthorizingRealm {

	@Resource
	UserMapperDao userMapperDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); //得到用户名  
		String password = new String((char[]) token.getCredentials()); //得到密码  
		UserBean user = userMapperDao.getUserBeanByNameId(username);
		if (user == null) {
			throw new UnknownAccountException();//没找到帐号  
		}
		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); //帐号锁定  
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getNameId(), user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
		return authenticationInfo;
	}

}
