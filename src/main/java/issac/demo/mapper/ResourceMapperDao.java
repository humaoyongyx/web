package issac.demo.mapper;

import java.util.List;

import issac.demo.bo.params.ResourceParams;
import issac.demo.model.ResourceBean;

public interface ResourceMapperDao {

	public List<ResourceBean> getResourceByMenuId(ResourceParams params);

	public void updateResourceBatch(List<ResourceBean> resourceBeans);

	public void replaceIntoSelective(ResourceBean resourceBean);

	public void deleteResourceByMenuId(Integer menuId);

}