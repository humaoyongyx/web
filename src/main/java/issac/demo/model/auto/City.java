package issac.demo.model.auto;

public class City {
    private Integer id;

    private String name;

    private Integer pid;

    private Integer level;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", pid=" + pid + ", level=" + level + "]";
	}

}