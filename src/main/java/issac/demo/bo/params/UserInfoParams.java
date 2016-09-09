package issac.demo.bo.params;

public class UserInfoParams extends DataTableBasicParams {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserInfoParams [name=" + name + "]";
	}

}
