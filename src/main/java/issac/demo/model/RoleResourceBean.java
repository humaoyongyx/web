package issac.demo.model;

public class RoleResourceBean {
	private int menuId;
	private int menuPid;
	private String menuName;
	private int orderNo;
	private int resourceId;
	private String resourceName;
	private int roleId;
	private String roleName;
	private Integer rsId;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(int menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRsId() {
		return rsId;
	}

	public void setRsId(Integer rsId) {
		this.rsId = rsId;
	}

	@Override
	public String toString() {
		return "RoleResourceBean [menuId=" + menuId + ", menuPid=" + menuPid + ", menuName=" + menuName + ", orderNo=" + orderNo + ", resourceId=" + resourceId + ", resourceName=" + resourceName + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", rsId=" + rsId + "]";
	}

}
