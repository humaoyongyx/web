package issac.demo.service.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.mapper.MenuMapperDao;
import issac.demo.mapper.ResourceMapperDao;
import issac.demo.mapper.RoleMapperDao;
import issac.demo.model.MenuBean;
import issac.demo.model.RoleBean;
import issac.demo.model.RoleResourceBean;
import issac.demo.model.UserBean;
import issac.demo.model.UserRoleBean;

@Service
public class RoleService {
	@Resource
	RoleMapperDao roleMapperDao;
	@Resource
	MenuMapperDao menuMapperDao;
	@Resource
	ResourceMapperDao resourceMapperDao;

	public HashMap<Integer, LinkedHashMap<Integer, LinkedList<RoleResourceBean>>> getRoleResourceMapPage(Integer roleId) {

		List<RoleResourceBean> roleResourceList = roleMapperDao.getRoleResourcePageList(roleId);
		HashMap<Integer, LinkedHashMap<Integer, LinkedList<RoleResourceBean>>> roleResourceMap = new HashMap<>();
		HashMap<Integer, LinkedHashMap<Integer, LinkedList<RoleResourceBean>>> orderRoleResourceMap = new LinkedHashMap<>();
		for (RoleResourceBean roleResourceBean : roleResourceList) {
			LinkedHashMap<Integer, LinkedList<RoleResourceBean>> map = roleResourceMap.get(roleResourceBean.getMenuPid());
			if (map != null) {
				LinkedList<RoleResourceBean> linkedList = map.get(roleResourceBean.getMenuId());
				if (linkedList != null) {
					linkedList.add(roleResourceBean);
				} else {
					LinkedList<RoleResourceBean> linkedList2 = new LinkedList<>();
					linkedList2.add(roleResourceBean);
					map.put(roleResourceBean.getMenuId(), linkedList2);
				}
			} else {
				map = new LinkedHashMap<>();
				LinkedList<RoleResourceBean> linkedList = new LinkedList<>();
				linkedList.add(roleResourceBean);
				map.put(roleResourceBean.getMenuId(), linkedList);
				roleResourceMap.put(roleResourceBean.getMenuPid(), map);
			}
		}
		LinkedList<MenuBean> orderMenuFolder = getOrderMenuFolder();

		for (MenuBean menuBean : orderMenuFolder) {
			int id = menuBean.getId();
			if (roleResourceMap.get(id) != null) {
				orderRoleResourceMap.put(id, roleResourceMap.get(id));
			}
		}
		return orderRoleResourceMap;
	}

	public LinkedHashMap<Integer, LinkedList<RoleResourceBean>> getRoleResourceListPage(Integer roleId) {

		List<RoleResourceBean> roleResourceList = roleMapperDao.getRoleResourcePageList(roleId);
		HashMap<Integer, LinkedList<RoleResourceBean>> roleResourceMap = new HashMap<>();
		LinkedHashMap<Integer, LinkedList<RoleResourceBean>> orderRoleResourceMap = new LinkedHashMap<>();
		for (RoleResourceBean roleResourceBean : roleResourceList) {
			LinkedList<RoleResourceBean> list = roleResourceMap.get(roleResourceBean.getMenuPid());
			if (list != null) {
				list.add(roleResourceBean);
			} else {
				LinkedList<RoleResourceBean> linkedList = new LinkedList<>();
				linkedList.add(roleResourceBean);
				roleResourceMap.put(roleResourceBean.getMenuPid(), linkedList);
			}
		}
		LinkedList<MenuBean> orderMenuFolder = getOrderMenuFolder();

		for (MenuBean menuBean : orderMenuFolder) {
			int id = menuBean.getId();
			if (roleResourceMap.get(id) != null) {
				orderRoleResourceMap.put(id, roleResourceMap.get(id));
			}
		}
		return orderRoleResourceMap;
	}

	public void handleMenus(MenuBean root, List<MenuBean> menuList, LinkedList<MenuBean> resultList) {
		TreeMap<Integer, MenuBean> map = new TreeMap<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				map.put(menuBean.getOrderNo(), menuBean);
				if (checkFolder(menuBean, menuList)) {
					orderMenus(menuBean, resultList);
					handleMenus(menuBean, menuList, resultList);
				}
			}
		}
	}

	public void orderMenus(MenuBean menuBean, LinkedList<MenuBean> resultList) {
		int index = resultList.size();
		for (int i = 0; i < resultList.size(); i++) {
			MenuBean menu = resultList.get(i);
			if (menuBean.getPid() == menu.getPid()) {
				if (menuBean.getOrderNo() < menu.getOrderNo()) {
					index = i;
				}
			}
		}

		resultList.add(index, menuBean);
	}

	public boolean checkFolder(MenuBean root, List<MenuBean> menuList) {
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				return true;
			}
		}
		return false;
	}

	public LinkedList<MenuBean> getOrderMenuFolder() {
		List<MenuBean> menuList = menuMapperDao.getAll();
		TreeMap<Integer, MenuBean> rootMenus = new TreeMap<>();
		Map<Integer, MenuBean> menuMap = new HashMap<>();
		List<MenuBean> menuBeans = new LinkedList<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() == null) {
				rootMenus.put(menuBean.getOrderNo(), menuBean);
			}
			menuMap.put(menuBean.getId(), menuBean);
		}
		Set<Entry<Integer, MenuBean>> entrySet = rootMenus.entrySet();
		LinkedList<MenuBean> resultList = new LinkedList<>();
		for (Entry<Integer, MenuBean> entry : entrySet) {
			MenuBean menuBean = entry.getValue();
			resultList.add(menuBean);
			handleMenus(menuBean, menuList, resultList);
		}
		return resultList;
	}

	public void insert(RoleBean roleBean) {
		roleMapperDao.insert(roleBean);
	}

	public RoleBean getRoleBeanByName(String name) {
		return roleMapperDao.getRoleBeanByName(name);
	}

	public void updateRoleAndResource(Integer roleId, String name, Integer[] resourceIds) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("id", roleId);
		params.put("name", name);
		roleMapperDao.updateByPrimaryKeySelective(params);
		resourceMapperDao.deleteByRoleId(roleId);
		resourceMapperDao.replaceIntoBeans(getResourceList(roleId, resourceIds));
	}

	public List<RoleResourceBean> getResourceList(Integer roleId, Integer[] resourceIds) {
		List<RoleResourceBean> roleResourceBeans = new ArrayList<>();
		RoleResourceBean roleResourceBean;
		for (Integer resourceId : resourceIds) {
			roleResourceBean = new RoleResourceBean();
			roleResourceBean.setRoleId(roleId);
			roleResourceBean.setResourceId(resourceId);
			roleResourceBeans.add(roleResourceBean);
		}
		return roleResourceBeans;
	}

	public void deleteAll(List<Integer> ids) {
		roleMapperDao.deleteAll(ids);
		resourceMapperDao.deleteByRoleIds(ids);
	}

	public List<UserRoleBean> findUserRoleByRoleIds(List<Integer> ids) {
		return roleMapperDao.findUserRoleByRoleIds(ids);
	}

	public List<RoleBean> getUserRoleList(UserBean userBean) {
		Integer userId = userBean.getId();

		if (userId == 1) {
			//root
			return roleMapperDao.getRootRoleList();
		} else {
			List<RoleResourceBean> userRoleResouceBeans = roleMapperDao.getRoleResourceByUserId(userId);
			boolean rootFlag = false;
			for (RoleResourceBean roleResourceBean : userRoleResouceBeans) {
				if (roleResourceBean.getRoleId() == 1) {
					rootFlag = true;
					break;
				}
			}

			if (rootFlag) {
				return roleMapperDao.getRootRoleList();
			}
			Map<Integer, List<RoleResourceBean>> roleResouceMap = new HashMap<>();
			List<RoleResourceBean> rootRoleResourceList = roleMapperDao.getRootRoleResourceList();
			Map<Integer, String> roleMap = new HashMap<>();
			for (RoleResourceBean roleResourceBean : rootRoleResourceList) {
				Integer roleId = roleResourceBean.getRoleId();
				roleMap.put(roleId, roleResourceBean.getRoleName());
				List<RoleResourceBean> list = roleResouceMap.get(roleId);
				if (list == null) {
					list = new ArrayList<>();
					list.add(roleResourceBean);
					roleResouceMap.put(roleId, list);
				} else {
					list.add(roleResourceBean);
				}
			}
			Set<Entry<Integer, List<RoleResourceBean>>> entrySet = roleResouceMap.entrySet();
			List<RoleBean> userRoleBeanList = new ArrayList<>();
			RoleBean roleBean = null;
			for (Entry<Integer, List<RoleResourceBean>> entry : entrySet) {
				if (userRoleResouceBeans.containsAll(entry.getValue())) {
					Integer roleId = entry.getKey();
					roleBean = new RoleBean();
					roleBean.setId(roleId);
					roleBean.setName(roleMap.get(roleId));
					userRoleBeanList.add(roleBean);
				}
			}

			return userRoleBeanList;
		}
	}


}
