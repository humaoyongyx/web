package issac.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import issac.demo.model.CityBean;
import issac.demo.model.MenuBean;
import issac.demo.model.RoleResourceBean;
import issac.demo.model.SchedulerBean;
import issac.demo.model.UserInfoBean;
import issac.demo.scheduler.SchedulerUtils;
import issac.demo.scheduler.SimpleJob;
import issac.demo.scheduler.SimpleJob2;
import issac.demo.service.CityService;
import issac.demo.service.MenuService;
import issac.demo.service.OssService;
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

	@Resource
	CityService cityService;

	@Resource
	SchedulerUtils commonScheduler;

	@Resource
	OssService ossService;

	@Test
	public void testOss() throws IOException {
		File file = new File("D:\\test.docx");
		String uploadFile = ossService.uploadFile("test/df2.docx", new FileInputStream(file), "");
		System.out.println(uploadFile);
		//OssObj downloadOssObj = ossService.downloadOssObjWithKey("df2.docx");
		//System.out.println(downloadOssObj.getContentType());
		
		
	}
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

	@Test
	public void testCitys() throws FileNotFoundException {
		List<CityBean> list = ExcelUtils.importExcel(new FileInputStream("e:/test/citys.xlsx"), null, CityBean.class);
		for (CityBean cityBean : list) {
			Integer id = cityBean.getId();
			String name = cityBean.getName();
			if (id % 10000 == 0) {
				System.out.println(id + name + "level:1");
				cityBean.setLevel(1);
				cityService.add(cityBean);
			} else if (id % 100 == 0) {
				Integer pid = id / 10000 * 10000;
				System.out.println(id + name + "level:2,parentId:" + pid);
				cityBean.setPid(pid);
				cityBean.setLevel(2);
				cityService.add(cityBean);
			} else {
				Integer pid = id / 100 * 100;
				System.out.println(id + name + "level:3,parentId:" + id / 100 * 100);
				cityBean.setPid(pid);
				cityBean.setLevel(3);
				cityService.add(cityBean);
			}

		}
		System.out.println(list);
	}

	@Test
	public void testScheduler() throws InterruptedException {
		SchedulerBean schedulerBean=new SchedulerBean();
		schedulerBean.setId(1);
		schedulerBean.setCron("* * * * * ?");
		schedulerBean.setParams("test,test");
		commonScheduler.addJob(schedulerBean, SimpleJob.class);
		commonScheduler.start();
		Thread.sleep(3000);
		schedulerBean.setId(2);
		schedulerBean.setParams("test2,test2");
		commonScheduler.addJob(schedulerBean, SimpleJob2.class);
		Thread.sleep(3000);
		schedulerBean.setId(1);
		schedulerBean.setParams("test3,test3");
		commonScheduler.modifyJob(schedulerBean);
		/*	commonScheduler.addJob("test", "test", TestJob.class, "* * * * * ?");
			commonScheduler.start();
			Thread.sleep(3000);
			commonScheduler.pauseJob("test", "test");
			Thread.sleep(3000);
			commonScheduler.resumeJob("test", "test");
			Thread.sleep(3000);
			//	quartzUtils.removeJob("test", "test");
			Thread.sleep(6000);
			commonScheduler.addJob("test", "test", TestJob2.class, "* * * * * ?");*/

	}

}
