package issac.demo.test.es;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import issac.demo.model.CityBean;
import issac.demo.service.CityService;
import issac.demo.test.AbstractBaseTest;
import issac.demo.utils.ESUtils;

public class EsTest extends AbstractBaseTest{
	
	@Resource
	CityService cityService;
	
	@Test
	public void testAddCitiesToEs(){
		List<CityBean> all = cityService.getAll();
		System.out.println(all);
		ESUtils.saveOrUpdate("user", "user", all);
	}
	

}
