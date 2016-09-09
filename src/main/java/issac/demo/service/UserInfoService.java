package issac.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.bo.params.UserInfoParams;
import issac.demo.common.Page;
import issac.demo.dto.DataTableResult;
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

	public List<UserInfo> getList(String name) {
		return userInfoMapper.getList(name);
	}

	public DataTableResult getUserInfoPage(UserInfoParams params) {
		/*DataTableResult result = new DataTableResult();
		List<UserInfo> pageList = userInfoMapper.getPageList(params.getStart(), params.getLength(), params.getName());
		result.setDraw(params.getDraw());
		int total = userInfoMapper.getTotal(params.getName());
		result.setRecordsTotal(total);
		result.setRecordsFiltered(total);
		result.setData(pageList);
		return result;*/
		
		return new Page(userInfoMapper).getPageResult(params);

	}

}
