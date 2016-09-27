package issac.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import issac.demo.bo.params.MenuParams;
import issac.demo.mapper.MenuMapperDao;
import issac.demo.mapper.RoleMapperDao;
import issac.demo.mapper.UserInfoMapper;
import issac.demo.mapper.UserInfoMapperDao;
import issac.demo.mapper.auto.ResourceMapper;
import issac.demo.model.MenuBean;
import issac.demo.model.RoleResourceBean;
import issac.demo.model.UserInfoBean;
import issac.demo.service.MenuService;
import issac.demo.utils.ExcelUtils;


public class SimpleTest extends AbstractBaseTest {
	@Resource
	UserInfoMapper userInfoMapper;

	@Resource
	UserInfoMapperDao userInfoMapperDao;
	@Resource
	MenuMapperDao menuMapperDao;
	@Resource
	MenuService menuService;

	@Resource
	ResourceMapper resourceMapper;

	@Resource
	RoleMapperDao roleMapperDao;

	@Test
	public void testOne() {
		List<RoleResourceBean> roleResourceList = roleMapperDao.getRoleResourcePageList(-1);
		
		HashMap<Integer, LinkedList<RoleResourceBean>> roleResourceMap=new HashMap<>();
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

		System.out.println(orderRoleResourceMap);
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
		int index= resultList.size();
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

	@Test
	public void testImportExcel() {
		String path = SimpleTest.class.getResource("").getPath();
		try {
			List<UserInfoBean> importExcel = ExcelUtils.importExcel(new FileInputStream(new File(path + "test.xlsx")), null, UserInfoBean.class);
			//userInfoMapperDao.batchInsertSelective(importExcel);
			System.out.println(importExcel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testTreeView() {
		//TreeViewResult treeViewMenus = menuService.getTreeViewMenusUseTreeMap();
		/*TreeViewResult treeViewMenus = menuService.getTreeViewMenus(1);*/
		System.out.println(JSON.toJSON(menuService.getUserMenus(3)));
	}

	@Test
	public void testTransaction() {
		MenuParams menuParams = new MenuParams();
		menuParams.setPid(1);
		menuParams.setText("xxx");
		menuParams.setUrl("tesurl");
		menuService.addOrUpdate(menuParams);
		System.out.println(menuParams.getId());
		/*   MenuParams addOrUpdate = menuService.addOrUpdate(menuParams);
			if (menuParams.getPid() != null && menuParams.getUrl() != null && !menuParams.getUrl().trim().equals("")) {
				System.out.println(menuParams.getId());
			}*/
	}


}
