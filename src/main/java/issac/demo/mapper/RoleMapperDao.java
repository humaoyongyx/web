package issac.demo.mapper;

import java.util.List;

import issac.demo.model.RoleBean;
import issac.demo.model.RoleResourceBean;
import issac.demo.model.UserRoleBean;
import issac.demo.service.module.IModule;

public interface RoleMapperDao extends IModule<RoleBean> {

	public List<RoleResourceBean> getRoleResourcePageList(int roleId);

	public void insert(RoleBean roleBean);

	public RoleBean getRoleBeanByName(String name);

	public List<UserRoleBean> findUserRoleByRoleIds(List<Integer> ids);

	public List<UserRoleBean> findUserRoleByUserId(Integer userId);

	public List<RoleResourceBean> findRoleResourceByRoleIds(List<Integer> roleIds);

	public List<RoleBean> getRootRoleList();

	public List<RoleResourceBean> getRootRoleResourceList();

	public List<RoleResourceBean> getRoleResourceByUserId(Integer userId);
}