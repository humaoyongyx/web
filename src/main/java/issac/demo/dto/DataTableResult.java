package issac.demo.dto;

import java.util.List;

import issac.demo.bo.DataTableBasicBean;

public class DataTableResult {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<? extends DataTableBasicBean> data;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<? extends DataTableBasicBean> getData() {
		return data;
	}

	public void setData(List<? extends DataTableBasicBean> data) {
		this.data = data;
	}




}
