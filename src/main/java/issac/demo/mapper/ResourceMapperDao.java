package issac.demo.mapper;

import java.util.List;

import issac.demo.bo.params.ResourceParams;
import issac.demo.model.ResourceBean;
import issac.demo.model.RoleResourceBean;

public interface ResourceMapperDao {

	public List<ResourceBean> getResourceByMenuId(ResourceParams params);

	public void updateResourceBatch(List<ResourceBean> resourceBeans);

	public void replaceIntoSelective(ResourceBean resourceBean);

	public void replaceIntoBeans(List<RoleResourceBean> roleResourceBeans);

	public void deleteResourceByMenuId(Integer menuId);

	public void deleteResourceByMenuIds(List<Integer> menuIds);

	public void deleteByRoleIdAndResourceId(RoleResourceBean resourceBean);

	public void deleteByRoleId(Integer id);

	public void deleteByRoleIds(List<Integer> ids);
}