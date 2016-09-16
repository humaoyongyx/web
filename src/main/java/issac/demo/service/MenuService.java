package issac.demo.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import issac.demo.bo.params.MenuParams;
import issac.demo.dto.TreeViewResult;
import issac.demo.mapper.MenuMapper;
import issac.demo.mapper.MenuMapperDao;
import issac.demo.model.MenuBean;

@Service
public class MenuService {

	@Resource
	MenuMapperDao menuMapperDao;

	@Resource
	MenuMapper menuMapper;


	public void handleMenus(MenuBean root, List<MenuBean> menuList) {
		TreeMap<Integer, MenuBean> map = new TreeMap<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				map.put(menuBean.getOrderNo(), menuBean);
				if (checkFolder(menuBean, menuList)) {
					handleMenus(menuBean, menuList);
				}
			}
		}
		List<MenuBean> nodes = new LinkedList<>();
		Set<Entry<Integer, MenuBean>> entrySet = map.entrySet();
		for (Entry<Integer, MenuBean> entry : entrySet) {
			nodes.add(entry.getValue());
		}
		root.setNodes(nodes);
	}

	public boolean checkFolder(MenuBean root, List<MenuBean> menuList) {
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				return true;
			}
		}
		return false;
	}

	public TreeViewResult getTreeViewMenus() {
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
		for (Entry<Integer, MenuBean> entry : entrySet) {
			MenuBean menuBean = entry.getValue();
			handleMenus(menuBean, menuList);
			menuBeans.add(menuBean);
		}
		TreeViewResult treeViewResult = new TreeViewResult();
		treeViewResult.setData(menuBeans);
		return treeViewResult;
	}

	public List<MenuBean> getAll(MenuParams menuParams) {
		return menuMapperDao.getAllMenus(menuParams);
	}

	public void add(MenuParams menuParams) {
		System.out.println(menuParams);
		if (menuParams.getId() != null && menuParams.getId() != 0) {
			menuMapper.updateByPrimaryKeySelective(menuParams);
		} else {
			menuMapper.insert(menuParams);
		}

	}

	public void delete(MenuParams menuParams) {
		menuMapper.deleteByPrimaryKey(menuParams.getId());
	}

}
