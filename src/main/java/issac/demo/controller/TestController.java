package issac.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import issac.demo.bo.DataTableBasicParams;
import issac.demo.service.TestService;
import issac.demo.vo.DataTableBasicBean;
import issac.demo.vo.DataTableResult;
import issac.demo.vo.TestData;

@Controller
@RequestMapping("/test")
public class TestController {

	@Resource
	TestService testService;

	@RequestMapping("/test1")
	public @ResponseBody Object testOne() {
		return testService.getData();
	}

	@RequestMapping("/test2")
	@ResponseBody
	public void testTwo() {
		// testService.insert();
	}


	@RequestMapping("/page")
	public String userinfo() {
		return "test";
	}
	
	@RequestMapping("/getDataTablePage")
	public @ResponseBody Object getDataTablePage(HttpServletRequest request, DataTableBasicParams params) {
		System.out.println(params);
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			System.out.println(key + request.getParameter(key));

		}
		return generateData(params.getDraw(), params.getStart(), params.getLength());
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static DataTableResult generateData(int draw, int base, int length) {
		List<DataTableBasicBean> testDatas = new ArrayList<>();
		for (int i = base; i < base + length; i++) {
			testDatas.add(new TestData().setName("name" + i).setPosition("position" + i).setSalary((double) i).setStartDate(sdf.format(new Date())).setDT_RowClass("rowClass" + i).setDT_RowId("rowId" + i));
        }
		DataTableResult dtResult = new DataTableResult();
		dtResult.setDraw(draw);
		dtResult.setRecordsTotal(101);
		dtResult.setRecordsFiltered(101);
		dtResult.setData(testDatas);
		return dtResult;
	}


}
