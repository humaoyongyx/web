package issac.demo.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/applicationContext-mybatis.xml", "/applicationContext-shiro.xml", "/applicationContext-captcha.xml" })
abstract public class AbstractBaseTest {

}
