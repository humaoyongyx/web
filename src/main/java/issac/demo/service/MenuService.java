package issac.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import issac.demo.dto.TreeViewResult;
import issac.demo.mapper.MenuMapperDao;
import issac.demo.model.MenuBean;

@Service
public class MenuService {

	@Resource
	MenuMapperDao menuMapperDao;

	public Object getMenus() {

		return null;
	}

	public void handleMenus(MenuBean root, List<MenuBean> menuList) {
		TreeMap<Integer, MenuBean> map = new TreeMap<>();
		for (MenuBean menuBean : menuList) {
			if (menuBean.getPid() != null && menuBean.getPid() == root.getId()) {
				
				if (checkFolder(menuBean, menuList)) {
					handleMenus(menuBean, menuList);
				} else {
					map.put(menuBean.getOrderNo(), menuBean);
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

	public static Object generate() {

		List<MenuBean> nodes = new ArrayList<>();

		MenuBean menuBean = new MenuBean();
		menuBean.setId(1);
		menuBean.setNameId("menu01");
		menuBean.setText("菜单目录");
		menuBean.setTags(new String[] { "2" });

		MenuBean menuBean3 = new MenuBean();
		menuBean3.setId(2);
		menuBean3.setNameId("menu02");
		menuBean3.setText("菜单目录");
		menuBean3.setTags(new String[] { "0" });

		MenuBean menuBean1 = new MenuBean();
		menuBean1.setId(101);
		menuBean1.setNameId("menu001");
		menuBean1.setText("菜单文件");
		menuBean1.setOrderNo(1);
		menuBean1.setPid(1);
		menuBean1.setUrl("/test/page");
		menuBean1.setIcon("glyphicon glyphicon-leaf");

		MenuBean menuBean2 = new MenuBean();
		menuBean2.setId(102);
		menuBean2.setNameId("menu002");
		menuBean2.setText("菜单文件");
		menuBean2.setOrderNo(2);
		menuBean2.setIcon("glyphicon glyphicon-leaf");
		menuBean2.setPid(1);
		menuBean2.setUrl("/test/page2");

		nodes.add(menuBean1);
		nodes.add(menuBean2);
		menuBean.setNodes(nodes);
		TreeViewResult treeViewResult = new TreeViewResult();
		List<MenuBean> data = new ArrayList<>();
		data.add(menuBean);
		data.add(menuBean3);
		treeViewResult.setData(data);
		System.out.println(JSON.toJSON(treeViewResult));
		return null;

	}

	public static void main(String[] args) {

	}

}
