package issac.demo.test;

import java.util.HashMap;
import java.util.Map;

public class Test {

	private Parent child = new Child();
	
	public Integer i=0;

	public void test() {
		this.child.test();
	}

	public static void main(String[] args) {
	    Integer integer=0;
	    Integer integer2=0;
	    System.out.println(new Test().i==new Parent().i);
	}


}
