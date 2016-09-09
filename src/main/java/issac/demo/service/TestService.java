package issac.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.mapper.UserInfoMapper;
import issac.demo.model.UserInfo;

@Service
public class TestService {

	@Resource
	UserInfoMapper userInfoMapper;

	public Object getData() {
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "key1");
		List<String> list = new ArrayList<>();
		list.add("list1");
		list.add("list21");
		list.add("list13");
		map.put("key2", list);
		return map;
	}

	public void insert() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(2);
		userInfo.setName("name2");
		userInfo.setSalary(13.4);
		userInfoMapper.insertSelective(userInfo);
	}

}
