package issac.demo.service.module;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.mapper.UserMapperDao;
import issac.demo.model.UserBean;

@Service
public class UserService {

	@Resource
	UserMapperDao userMapperDao;

	public IModule<UserBean> getModule() {
		return userMapperDao;
	}


}
