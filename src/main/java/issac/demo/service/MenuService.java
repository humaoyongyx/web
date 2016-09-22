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
import issac.demo.mapper.ResourceMapperDao;
import issac.demo.model.MenuBean;
import issac.demo.model.ResourceBean;

@Service
public class MenuService {

	@Resource
	MenuMapperDao menuMapperDao;

	@Resource
	MenuMapper menuMapper;

	@Resource
	ResourceMapperDao resourceMapperDao;


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

	public List<MenuBean> find(MenuParams menuParams) {
		return menuMapperDao.getAllMenus(menuParams);
	}

	public void addOrUpdate(MenuParams menuParams) {
		if (menuParams.getId() != null && menuParams.getId() != 0) {
			menuMapper.updateByPrimaryKeySelective(menuParams);
		} else {
			menuMapperDao.insert(menuParams);
		}

	}

	public void addMenuResources(MenuParams menuParams) {
		ResourceBean show = new ResourceBean();
		show.setAction("show");
		show.setMenuId(menuParams.getId());
		show.setName("查看");
		show.setUrl("/,/show");
		resourceMapperDao.replaceIntoSelective(show);
		ResourceBean add = new ResourceBean();
		add.setAction("add");
		add.setMenuId(menuParams.getId());
		add.setName("增加");
		add.setUrl("/add");
		resourceMapperDao.replaceIntoSelective(add);
		ResourceBean delete = new ResourceBean();
		delete.setAction("delete");
		delete.setMenuId(menuParams.getId());
		delete.setName("删除");
		delete.setUrl("/delete");
		resourceMapperDao.replaceIntoSelective(delete);
		ResourceBean modfiy = new ResourceBean();
		modfiy.setAction("modfiy");
		modfiy.setMenuId(menuParams.getId());
		modfiy.setName("修改");
		modfiy.setUrl("/modfiy");
		resourceMapperDao.replaceIntoSelective(modfiy);
	}
	public void delete(MenuParams menuParams) {
		menuMapper.deleteByPrimaryKey(menuParams.getId());
		resourceMapperDao.deleteResourceByMenuId(menuParams.getId());
	}

}
