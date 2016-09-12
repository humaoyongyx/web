package issac.demo.bo.params;

public class UserInfoParams extends DataTableBasicParams {
	private String name;
	private String date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UserInfoParams [name=" + name + ", date=" + date + "]";
	}


}
