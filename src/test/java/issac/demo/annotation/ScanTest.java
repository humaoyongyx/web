package issac.demo.annotation;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import issac.demo.test.JUnit4ClassRunner;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/scan.xml"})

public class ScanTest {
	//配置文件和java配置 貌似文件优先级高
	@Autowired
	@Qualifier("scanApi")
	ScanApi scanApi;
	
	
	@Test
	public void simpleTest(){
		System.out.println(scanApi.getClass());
		scanApi.say("hllo", "cccon");
		System.out.println(scanApi.getClass());
	}

}
