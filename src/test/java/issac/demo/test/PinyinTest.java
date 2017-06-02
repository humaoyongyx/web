package issac.demo.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import issac.demo.model.CityBean;
import issac.demo.service.CityService;
import issac.demo.utils.Pinyin4jUtil;

public class PinyinTest extends AbstractBaseTest {
	@Resource
	CityService cityService;

	@Test
	public void test1() {
		long begin = System.currentTimeMillis();

		System.out.println(begin);
		List<CityBean> byPid = cityService.getAll();
		long end = System.currentTimeMillis();
		System.out.println("execute---->" + (end - begin) + " ms");
		List<CityBean> temp = new ArrayList<>();
		for (CityBean cityBean : byPid) {
			String name = cityBean.getName();
			name = Pinyin4jUtil.converterToFirstSpell(name);
			if (name != null && !name.trim().equals("")) {
				String[] split = name.split(",");
				for (String string : split) {
					if (string.matches("^cq.*")) {
						temp.add(cityBean);
						break;
					}
				}

			}

		}
		if (temp != null) {
			for (CityBean cityBean2 : temp) {
				String name = cityBean2.getName();
				System.out.println(name);
				System.out.println(Pinyin4jUtil.converterToFirstSpell(name));
			}
		}


		end = System.currentTimeMillis();
		System.out.println("execute---->" + (end - begin) + " ms");
	}

	public static void main(String[] args) {
		System.out.println("ab33sdf".matches("^ab.*"));
		
	}
}
