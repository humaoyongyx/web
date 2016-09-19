package issac.demo.service.module;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.bo.params.ResourceParams;
import issac.demo.mapper.ResourceMapperDao;
import issac.demo.model.ResourceBean;

@Service
public class ResourceService {

	@Resource
	ResourceMapperDao resourceMapperDao;

	public List<ResourceBean> getResourceByMenuId(ResourceParams params) {
		return resourceMapperDao.getResourceByMenuId(params);
	}

}
