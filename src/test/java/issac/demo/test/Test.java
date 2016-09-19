package issac.demo.test;

import java.util.HashMap;
import java.util.Map;

public class Test {

	private Parent child = new Child();

	public void test() {
		this.child.test();
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("xx", null);
		System.out.println(map);
	}


}
