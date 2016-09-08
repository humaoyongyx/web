package issac.demo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping("/uploadPage")
	public String uploadPage() {
		return "uploadPage";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, String hidden, HttpServletRequest request, ModelMap model) {

		System.out.println("开始");
		System.out.println("hidden:" + hidden);
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		//	        String fileName = new Date().getTime()+".jpg";  
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		//保存  
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);

		return "forward:/test/uploadPage";
	}

	@RequestMapping("/getDataTablePage")
	public @ResponseBody Object getDataTablePage(HttpServletRequest request, DataTableBasicParams params) {
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
