package issac.demo.service;

import java.util.ArrayList;
import java.util.Collections;
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
import issac.demo.mapper.MenuMapperDao;
import issac.demo.mapper.ResourceMapperDao;
import issac.demo.mapper.auto.MenuMapper;
import issac.demo.model.MenuBean;
import issac.demo.model.ResourceBean;
import issac.demo.model.comparator.MenuComparator;

@Service
public class MenuService {

	@Resource
	MenuMapperDao menuMapperDao;

	@Resource
	MenuMapper menuMapper;

	@Resource
	ResourceMapperDao resourceMapperDao;


	public void handleMenusUseTreeMap(MenuBean root, List<MenuBean> menuList) {
		TreeMap<Integer, MenuBean> map = new TreeMap<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				map.put(menuBean.getOrderNo(), menuBean);
				if (checkFolder(menuBean, menuList)) {
					handleMenusUseTreeMap(menuBean, menuList);
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

	public void handleMenus(MenuBean root, List<MenuBean> menuList) {
		List<MenuBean> list = new ArrayList<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				list.add(menuBean);
				if (checkFolder(menuBean, menuList)) {
					handleMenus(menuBean, menuList);
				}
			}
		}
		List<MenuBean> nodes = new LinkedList<>();
		Collections.sort(list, new MenuComparator());
		for (MenuBean menuBean : list) {
			nodes.add(menuBean);
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

	public TreeViewResult getTreeViewMenusUseTreeMap() {
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
			handleMenusUseTreeMap(menuBean, menuList);
			menuBeans.add(menuBean);
		}
		TreeViewResult treeViewResult = new TreeViewResult();
		treeViewResult.setData(menuBeans);
		return treeViewResult;
	}

	public TreeViewResult getTreeViewMenus() {
		List<MenuBean> menuList = menuMapperDao.getAll();
		List<MenuBean> rootMenus = new ArrayList<>();
		List<MenuBean> menuBeans = new LinkedList<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() == null) {
				rootMenus.add(menuBean);
			}
		}
		Collections.sort(rootMenus, new MenuComparator());
		for (MenuBean menuBean : rootMenus) {
			handleMenus(menuBean, menuList);
			menuBeans.add(menuBean);
		}
		TreeViewResult treeViewResult = new TreeViewResult();
		treeViewResult.setData(menuBeans);
		return treeViewResult;
	}

	public TreeViewResult getTreeViewMenus(Integer userId) {
		List<MenuBean> menuList = menuMapperDao.getAll();
		List<MenuBean> userMenuList = menuMapperDao.getMenuByUserId(userId);
		List<MenuBean> rootMenus = new ArrayList<>();
		List<MenuBean> menuBeans = new LinkedList<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() == null) {
				rootMenus.add(menuBean);
			}
		}
		Collections.sort(rootMenus, new MenuComparator());
		for (MenuBean menuBean : rootMenus) {
			handleUserMenus(menuBean, menuList, userMenuList);
			menuBeans.add(menuBean);
		}
		TreeViewResult treeViewResult = new TreeViewResult();
		treeViewResult.setData(menuBeans);
		return treeViewResult;

	}

	public List<MenuBean> getUserMenus(Integer userId) {
		List<MenuBean> menuList = menuMapperDao.getAll();
		List<MenuBean> userMenuList = menuMapperDao.getMenuByUserId(userId);
		List<MenuBean> rootMenus = new ArrayList<>();
		List<MenuBean> menuBeans = new LinkedList<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() == null) {
				rootMenus.add(menuBean);
			}
		}
		Collections.sort(rootMenus, new MenuComparator());
		for (MenuBean menuBean : rootMenus) {
			handleUserMenus(menuBean, menuList, userMenuList);
			menuBeans.add(menuBean);
		}

		List<MenuBean> resultMenus = new LinkedList<>();
		handleMenus(menuBeans, resultMenus);
		return resultMenus;

	}

	public void handleMenus(List<MenuBean> menuBeans,List<MenuBean> resultMenus) {
		for (MenuBean menuBean : menuBeans) {
			resultMenus.add(menuBean);
			List<MenuBean> nodes = menuBean.getNodes();
			if (nodes != null) {
				handleMenus(nodes, resultMenus);
			}
		}
	}

	public void handleUserMenus(MenuBean root, List<MenuBean> menuList, List<MenuBean> userMenuList) {
		List<MenuBean> list = new ArrayList<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				if (checkFolder(menuBean, menuList)) {
					handleUserMenus(menuBean, menuList, userMenuList);
					if (menuBean.getNodes() != null) {
						list.add(menuBean);
					}
				} else {
					if (userMenuList.contains(menuBean)) {
						list.add(menuBean);
					}
				}
			}
		}
		List<MenuBean> nodes = new LinkedList<>();
		Collections.sort(list, new MenuComparator());
		for (MenuBean menuBean : list) {
			nodes.add(menuBean);
		}
		root.setNodes(nodes);
	}

	public List<MenuBean> find(MenuParams menuParams) {
		return menuMapperDao.getAllMenus(menuParams);
	}

	public void addOrUpdate(MenuParams menuParams) {
		if (menuParams.getId() != null) {
			menuMapperDao.updateByPrimaryKeySelective(menuParams);
		} else {
			menuMapperDao.insert(menuParams);
		}

	}

	public void addMenuResources(MenuParams menuParams) {
		ResourceBean show = new ResourceBean();
		show.setAction("show");
		show.setMenuId(menuParams.getId());
		show.setName("查看");
		show.setUrl("show.*|get.*|find.*|load.*|search.*");
		resourceMapperDao.replaceIntoSelective(show);
		ResourceBean add = new ResourceBean();
		add.setAction("add");
		add.setMenuId(menuParams.getId());
		add.setName("增加");
		add.setUrl("add.*|insert.*|save.*");
		resourceMapperDao.replaceIntoSelective(add);
		ResourceBean delete = new ResourceBean();
		delete.setAction("delete");
		delete.setMenuId(menuParams.getId());
		delete.setName("删除");
		delete.setUrl("delete.*|remove.*");
		resourceMapperDao.replaceIntoSelective(delete);
		ResourceBean modfiy = new ResourceBean();
		modfiy.setAction("modfiy");
		modfiy.setMenuId(menuParams.getId());
		modfiy.setName("修改");
		modfiy.setUrl("modfiy.*|update.*|edit.*");
		resourceMapperDao.replaceIntoSelective(modfiy);
	}
	public void delete(MenuParams menuParams) {
		menuMapperDao.deleteByPrimaryKey(menuParams.getId());
		resourceMapperDao.deleteResourceByMenuId(menuParams.getId());
	}

	public void deleteAll(List<Integer> ids) {
		menuMapperDao.deleteAll(ids);
		resourceMapperDao.deleteResourceByMenuIds(ids);
	}

}
