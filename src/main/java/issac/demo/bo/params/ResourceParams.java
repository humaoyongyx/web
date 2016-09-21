package issac.demo.bo.params;

import issac.demo.model.auto.Resource;

public class ResourceParams extends Resource {
	private Integer id;

	private String name;

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

	@Override
	public String toString() {
		return "ResourceParams [id=" + id + ", name=" + name + "]";
	}
}
