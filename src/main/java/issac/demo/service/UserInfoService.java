package issac.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.mapper.UserInfoMapper;
import issac.demo.model.UserInfo;

@Service
public class UserInfoService {
	@Resource
	UserInfoMapper userInfoMapper;

	public void insert(UserInfo userInfo) {
		userInfoMapper.insertSelective(userInfo);
	}

	public void update(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	public void delete(Integer id) {
		userInfoMapper.deleteByPrimaryKey(id);
	}

	public UserInfo findById(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	public List<UserInfo> findAll() {
		return userInfoMapper.selectAll();
	}

}
