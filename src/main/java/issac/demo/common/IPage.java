package issac.demo.common;

import java.util.List;

import issac.demo.bo.DataTableBasicBean;
import issac.demo.bo.params.DataTableBasicParams;

public interface IPage {
	public List<DataTableBasicBean> getPageList(DataTableBasicParams params);
	public int getTotal(DataTableBasicParams params);
}
