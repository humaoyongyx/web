package issac.demo.vo;

public class TestData extends DataTableBasicBean {

	private String name;
	private String position;
	private Double salary;
	private String startDate;

	public String getName() {
		return name;
	}

	public TestData setName(String name) {
		this.name = name;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public TestData setPosition(String position) {
		this.position = position;
		return this;
	}

	public Double getSalary() {
		return salary;
	}

	public TestData setSalary(Double salary) {
		this.salary = salary;
		return this;
	}

	public String getStartDate() {
		return startDate;
	}

	public TestData setStartDate(String startDate) {
		this.startDate = startDate;
		return this;
	}


}
