package issac.demo.test;

import javax.annotation.Resource;

import org.junit.Test;

import issac.demo.mapper.UserInfoMapper;
import issac.demo.model.UserInfo;

public class SimpleTest extends AbstractBaseTest {
	@Resource
	UserInfoMapper userInfoMapper;

	@Test
	public void testOne() {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
		System.out.println(userInfo);
	}

}
