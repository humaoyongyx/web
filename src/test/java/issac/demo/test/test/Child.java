package issac.demo.test.test;

public class Child extends Parent {

	public int value = 2;

	public static void main(String[] args) {
		Parent child = new Child();
		//child.setValue(3);
		child.value = 3;
		child.say();
		System.out.println(child.value);
		System.out.println(child.getValue());

	}

	public int getValue() {
			return value;
		}

}
