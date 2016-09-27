package issac.demo.model;

import java.util.List;

public class MenuBean {

	private Integer id;
	private Integer pid;
	private String text;
	private String nameId;
	private String icon;
	private int selectable;
	private String[] tags;
	private String url;
	private List<MenuBean> nodes;
	private int orderNo;


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNameId() {
		nameId = "_menu" + id;
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getSelectable() {
		if (nodes != null || pid == null) {
			return 0;
		} else {
			return 1;
		}
	}

	public void setSelectable(int selectable) {
		this.selectable = selectable;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuBean> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuBean> nodes) {
		this.nodes = nodes;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public String[] getTags() {

		if (pid == null && nodes == null) {
			return new String[] { "0" };
		}
		if (nodes != null) {
			return new String[] { nodes.size() + "" };
		}
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MenuBean [id=" + id + ", pid=" + pid + ", text=" + text + ", nameId=" + nameId + ", icon=" + icon + ", url=" + url + ", orderNo=" + orderNo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuBean other = (MenuBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
