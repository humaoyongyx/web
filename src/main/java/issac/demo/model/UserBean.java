package issac.demo.model;

import org.apache.commons.codec.digest.DigestUtils;

public class UserBean extends ModuleBean {

	private Integer id;

	private String name;

	private String nameId;

	private String password;

	private String salt;

	private Integer status;

	private String mobile;

	private String sex;

	private String photo;

	private String address;

	private String email;

	private String roleId;

	private String roleName;

	private Boolean locked;

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
		this.name = name;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		salt = DigestUtils.md5Hex("09" + nameId + "@Salt");
		return salt;
	}

	public Boolean getLocked() {
		if (status == null)
			return false;
		if (status == 2) {
			return true;
		} else {
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", nameId=" + nameId + ", password=" + password + ", salt=" + salt + ", status=" + status + ", mobile=" + mobile + ", sex=" + sex + ", photo=" + photo
				+ ", address=" + address + ", email=" + email + ", roleId=" + roleId + ", roleName=" + roleName + ", locked=" + locked + "]";
	}

}
