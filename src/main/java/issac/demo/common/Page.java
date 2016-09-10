package issac.demo.common;

import org.apache.log4j.Logger;

import issac.demo.bo.params.DataTableBasicParams;
import issac.demo.dto.DataTableResult;

public class Page {
	private Logger logger = Logger.getLogger(Page.class);
	private IPage iPage;

	public Page(IPage iPage) {
		this.iPage = iPage;
	}

	public DataTableResult getPageResult(DataTableBasicParams params) {
		logger.info("Begin to handle pagination...");
		long begin = System.currentTimeMillis();
		DataTableResult result = new DataTableResult();
		result.setDraw(params.getDraw());
		result.setRecordsFiltered(iPage.getPageTotal(params));
		result.setRecordsTotal(iPage.getPageTotal(params));
		result.setData(iPage.getPageList(params));
		long end = System.currentTimeMillis();
		logger.info("End pagination,about time : " + (end - begin) / 1000.0 + "s");
		return result;
   }

}
