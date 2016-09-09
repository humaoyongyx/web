package issac.demo.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataTableBasicBean {

	@JsonProperty(value = "DT_RowId")
	private String dTRowId;
	@JsonProperty(value = "DT_RowClass")
	private String dTRowClass;

	@JsonIgnore
	public String getDTRowId() {
		return dTRowId;
	}

	@JsonIgnore
	public DataTableBasicBean setDTRowId(String dTRowId) {
		this.dTRowId = dTRowId;
		return this;
	}

	@JsonIgnore
	public String getDTRowClass() {
		return dTRowClass;
	}

	@JsonIgnore
	public DataTableBasicBean setDTRowClass(String dTRowClass) {
		this.dTRowClass = dTRowClass;
		return this;
	}




}
