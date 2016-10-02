package issac.demo.test.test;

public class Parent {

	protected int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void say() {
		System.out.println(this.value);
		System.out.println(this.getValue());
	}

	public static void main(String[] args) {
		final String test = "xx";
		System.out.println(test);
	}

}
