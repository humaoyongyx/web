package issac.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import issac.demo.dto.TreeViewResult;
import issac.demo.mapper.UserInfoMapper;
import issac.demo.mapper.UserInfoMapperDao;
import issac.demo.model.UserInfo;
import issac.demo.model.UserInfoBean;
import issac.demo.service.MenuService;
import issac.demo.utils.ExcelUtils;


public class SimpleTest extends AbstractBaseTest {
	@Resource
	UserInfoMapper userInfoMapper;

	@Resource
	UserInfoMapperDao userInfoMapperDao;

	@Resource
	MenuService menuService;

	@Test
	public void testOne() {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
		System.out.println(userInfo);
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
		TreeViewResult treeViewMenus = menuService.getTreeViewMenus();
		System.out.println(JSON.toJSON(treeViewMenus));
	}

}
