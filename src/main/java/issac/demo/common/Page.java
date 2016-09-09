package issac.demo.common;

import issac.demo.bo.params.DataTableBasicParams;
import issac.demo.dto.DataTableResult;

public class Page {
	private IPage iPage;

	public Page(IPage iPage) {
		this.iPage = iPage;
	}

	public DataTableResult getPageResult(DataTableBasicParams params) {
		DataTableResult result = new DataTableResult();
		result.setDraw(params.getDraw());
		result.setRecordsFiltered(iPage.getTotal(params));
		result.setRecordsTotal(iPage.getTotal(params));
		result.setData(iPage.getPageList(params));
		return result;
   }

}
