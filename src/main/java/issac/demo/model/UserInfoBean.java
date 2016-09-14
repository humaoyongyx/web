package issac.demo.model;

import issac.demo.bo.DataTableBasicBean;

public class UserInfoBean extends DataTableBasicBean {
	private Integer id;

	private String name;

	private Double salary;

	private String sex;

	private String descn;

	private String photo;

	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
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
		this.sex = sex == null ? null : sex.trim();
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn == null ? null : descn.trim();
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo == null ? null : photo.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserInfoBean [id=" + id + ", name=" + name + ", salary=" + salary + ", sex=" + sex + ", descn=" + descn + ", photo=" + photo + ", createTime=" + createTime + "]";
	}

}
