package issac.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataTableBasicBean {

	@JsonProperty(value = "DT_RowId")
	private String DT_RowId;
	@JsonProperty(value = "DT_RowClass")
	private String DT_RowClass;

	public String getDT_RowId() {
		return DT_RowId;
	}

	public DataTableBasicBean setDT_RowId(String dT_RowId) {
		DT_RowId = dT_RowId;
		return this;
	}

	public String getDT_RowClass() {
		return DT_RowClass;
	}

	public DataTableBasicBean setDT_RowClass(String dT_RowClass) {
		DT_RowClass = dT_RowClass;
		return this;
	}




}
