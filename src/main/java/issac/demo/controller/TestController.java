package issac.demo.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import issac.demo.bo.DataTableBasicBean;
import issac.demo.bo.params.DataTableBasicParams;
import issac.demo.bo.params.UserInfoParams;
import issac.demo.dto.DataTableResult;
import issac.demo.dto.TestData;
import issac.demo.service.OssService;
import issac.demo.service.OssService.OssObj;
import issac.demo.service.TestService;
import issac.demo.service.UploadPictureService;
import issac.demo.utils.CommonUtils;

@Controller
@RequestMapping("/test")
public class TestController {

	@Value("#{propertiesReader[uploadDir]}")
	private String uploadDir;

	@Resource
	TestService testService;
	
	@Resource
	OssService ossService;

	@RequestMapping("/test1")
	public @ResponseBody Object testOne() {
		return testService.getData();
	}

	@Resource
	UploadPictureService uploadPictureService;

	@RequestMapping("/test2")
	@ResponseBody
	public void testTwo() {
		// testService.insert();
	}

	@RequestMapping("/test")
	@ResponseBody
	public void test(HttpServletRequest request) {
		System.out.println(uploadDir);
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
	public @ResponseBody String upload(@RequestParam(value = "file", required = false) MultipartFile file, String hidden, HttpServletRequest request, ModelMap model) {

		System.out.println("开始");
		System.out.println("hidden:" + hidden);
		String result = "fail";
		if (file != null && !file.isEmpty()) {
			result = uploadPictureService.upload(file);
		}
		return result;
	}
	
	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	public @ResponseBody void upload2(MultipartFile file,  HttpServletRequest request, ModelMap model) {

		if (file != null && !file.isEmpty()) {
               try {
				ossService.uploadFile("test.docx", file.getInputStream(), file.getContentType());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/upload3", method = RequestMethod.POST)
	public @ResponseBody void upload3(MultipartFile file,  HttpServletRequest request, ModelMap model) {

		if (file != null && !file.isEmpty()) {
               try {
				ossService.uploadFile("upload3.docx", file.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/download")
	public @ResponseBody void download(String key, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(key);
        CommonUtils.outputToPage("测试test.docx", response, ossService.downloadFileWithKey(key));
	}
	
	
	@RequestMapping(value = "/download2")
	public @ResponseBody void download2(String key, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(key);
		OssObj ossObj = ossService.downloadOssObjWithKey(key);
        CommonUtils.outputToPage("测试test.docx", response, ossObj.getData(),ossObj.getContentType());
	}
	
	@RequestMapping(value = "/deleteFile")
	public @ResponseBody void deleteFile(String key, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(key);
	     ossService.deleteFileWithKey(key);
	}

	@RequestMapping("/getDataTablePage")
	public @ResponseBody Object getDataTablePage(HttpServletRequest request, DataTableBasicParams params) {
		return generateData(params.getDraw(), params.getStart(), params.getLength());
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static DataTableResult generateData(int draw, int base, int length) {
		List<DataTableBasicBean> testDatas = new ArrayList<>();
		for (int i = base; i < base + length; i++) {
			testDatas.add(new TestData().setName("name" + i).setPosition("position" + i).setSalary((double) i).setStartDate(sdf.format(new Date())).setDTRowClass("rowClass" + i).setDTRowId("rowId" + i));
        }
		DataTableResult dtResult = new DataTableResult();
		dtResult.setDraw(draw);
		dtResult.setRecordsTotal(101);
		dtResult.setRecordsFiltered(101);
		dtResult.setData(testDatas);
		return dtResult;
	}

	@RequestMapping("/testParams")
	@ResponseBody
	public void testParams(UserInfoParams params) {
		System.out.println(params);
	}

	@RequestMapping("/testftl")
	public String testFtl(HttpServletRequest request) {
		request.setAttribute("message", "Say hi for Freemarker.");
		return "testFtl";
	}

}
