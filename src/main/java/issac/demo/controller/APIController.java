package issac.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import issac.demo.bo.params.DataTableBasicParams;
import issac.demo.dto.DataTableResult;
import issac.demo.utils.ESUtils;
import issac.demo.utils.Pinyin4jUtil;

@RestController
@RequestMapping("api")
public class APIController {
	
	//@RequestMapping(value="test",produces="application/json;charset=UTF-8")
	@RequestMapping("test")
	public String test(String value){
		System.out.println(value);
		return "success";
	}
	
	@RequestMapping(value="es",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object esFind(String value){
		long begin=System.currentTimeMillis();
		Map<String, Object> searchHighlight = ESUtils.searchBoolShouldHighlight("user", "user"
				, ESUtils.QuerySetting().setField("name", ESUtils.MatchType.PREFIX, true)
				.setField("name.pinyin", ESUtils.MatchType.TEXT)
				.setValue(value)
				);
		long end=System.currentTimeMillis();
		System.out.println("total time :"+(end-begin)+"ms");
		return searchHighlight;
		
	}
	
	@RequestMapping(value="esPage",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object esPage(String value,DataTableBasicParams params){
		long begin=System.currentTimeMillis();
		DataTableResult result = new DataTableResult();
		System.out.println(params);
		Map<String, Object> searchHighlight = ESUtils.searchBoolShouldHighlight("user", "user"
				, ESUtils.QuerySetting().setField("name", ESUtils.MatchType.PREFIX, true)
				.setField("name.pinyin", ESUtils.MatchType.TEXT)
				.setValue(value)
				,"tag",params.getStart(),params.getLength(),false);
		result.setDraw(params.getDraw());
		Integer total=((Long) searchHighlight.get("total")).intValue();
		List data2=(List) searchHighlight.get("data");
		result.setRecordsFiltered(total);
		result.setRecordsTotal(total);
		result.setData(data2);
		long end=System.currentTimeMillis();
		System.out.println("total time :"+(end-begin)+"ms");
		return result;
	}

}
