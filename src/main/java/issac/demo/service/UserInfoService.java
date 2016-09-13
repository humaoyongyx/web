package issac.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.bo.params.UserInfoParams;
import issac.demo.common.Page;
import issac.demo.dto.DataTableResult;
import issac.demo.mapper.UserInfoMapper;
import issac.demo.mapper.UserInfoMapperDao;
import issac.demo.model.UserInfo;
import issac.demo.model.UserInfoBean;

@Service
public class UserInfoService {
	@Resource
	UserInfoMapperDao userInfoMapperDao;
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

	public void insert(UserInfoBean userInfoBean) {
		userInfoMapperDao.insertSelective(userInfoBean);
	}
	public List<UserInfoBean> findAll() {
		return userInfoMapperDao.selectAll();
	}

	public List<UserInfoBean> getList(String name) {
		return userInfoMapperDao.getList(name);
	}

	public DataTableResult getUserInfoPage(UserInfoParams params) {

		return new Page(userInfoMapperDao).getPageResult(params);

	}

}
