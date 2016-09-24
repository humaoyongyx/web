package issac.demo.service.module;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.bo.params.ResourceParams;
import issac.demo.mapper.ResourceMapperDao;
import issac.demo.model.ResourceBean;
import issac.demo.model.RoleResourceBean;

@Service
public class ResourceService {

	@Resource
	ResourceMapperDao resourceMapperDao;

	public List<ResourceBean> getResourceByMenuId(ResourceParams params) {
		return resourceMapperDao.getResourceByMenuId(params);
	}

	public void updateResourceBatch(List<ResourceBean> resourceBeans) {
		resourceMapperDao.updateResourceBatch(resourceBeans);
	}

	public void insertResourceBatch(List<RoleResourceBean> roleResourceBeans) {
		resourceMapperDao.replaceIntoBeans(roleResourceBeans);
	}

	public void deleteByRoleIdAndResourceId(RoleResourceBean resourceBean) {
		resourceMapperDao.deleteByRoleIdAndResourceId(resourceBean);
	}

	public void deleteByRoleId(Integer id) {
		resourceMapperDao.deleteByRoleId(id);
	}

}
