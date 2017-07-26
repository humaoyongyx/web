package issac.demo.test.api;

import java.util.HashMap;
import java.util.Map;

import issac.demo.utils.http.HttpClientUtil;
import issac.demo.utils.http.MethodNotSupportException;
import issac.demo.utils.http.Request;
import issac.demo.utils.http.response.Response;

public class APITest {
	
	public static void main(String[] args) throws MethodNotSupportException {
		testGet();
		testPost();
	}
	
	
	public static void test1() throws MethodNotSupportException{
		Request request= new Request("http://localhost:9081/web/api/test");
	    Response doRequest = HttpClientUtil.doRequest(request);
	    System.out.println(doRequest.getResponseText());
	    System.out.println(doRequest.getCode());
	    System.out.println(doRequest.getContentType());
	    System.out.println("xxx");
	}
	
	public static void testGet() {
			Map<String, Object> params=new HashMap<>();
			params.put("value", "hello world!!!你好世界");
         String string = HttpClientUtil.get("http://localhost:9081/web/api/test", params);
         System.out.println(string);
	}
	
	public static void testPost() {
		Map<String, Object> params=new HashMap<>();
		params.put("value", "hello world!!!你好世界");
        String string = HttpClientUtil.post("http://localhost:9081/web/api/test",params);
        System.out.println(string);
	}

}
