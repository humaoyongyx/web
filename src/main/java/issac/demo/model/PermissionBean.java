package issac.demo.model;

public class PermissionBean {
	private Integer id;
	private String moduleUrl;
	private String actionUrl;
	private String action;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "PermissionBean [id=" + id + ", moduleUrl=" + moduleUrl + ", actionUrl=" + actionUrl + ", action=" + action + "]";
	}

}
