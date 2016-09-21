package issac.demo.service.module;

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
import issac.demo.mapper.RoleMapperDao;
import issac.demo.model.MenuBean;
import issac.demo.model.RoleResourceBean;

@Service
public class RoleService {
	@Resource
	RoleMapperDao roleMapperDao;
	@Resource
	MenuMapperDao menuMapperDao;

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
}
