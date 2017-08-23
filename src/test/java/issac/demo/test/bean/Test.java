package issac.demo.test.bean;

import com.alibaba.fastjson.JSON;

public class Test {
	
	public static void main(String[] args) {
		PlainObject plainObject = new PlainObject(2,"dsd");
		plainObject.getId();
		System.out.println(JSON.toJSONString(plainObject));
		System.out.println(plainObject);
	   
	}
	
}
