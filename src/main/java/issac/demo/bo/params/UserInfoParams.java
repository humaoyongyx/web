package issac.demo.bo.params;

public class UserInfoParams extends DataTableBasicParams {

	private String name;

	private Double salary;

	private String sex;

	private String descn;

	private String photo;

	private String createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserInfoParams [name=" + name + ", salary=" + salary + ", sex=" + sex + ", descn=" + descn + ", photo=" + photo + ", createTime=" + createTime + "]";
	}

}
