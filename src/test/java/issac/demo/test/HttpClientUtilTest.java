package issac.demo.test;

import org.junit.Test;

import issac.demo.utils.http.HttpClientUtil;
import issac.demo.utils.http.RequestMethod;
import issac.demo.utils.http.UrlEncodedFormRequest;
import issac.demo.utils.http.response.Response;


public class HttpClientUtilTest {

    @Test
    public void doResponse() throws Exception {
		UrlEncodedFormRequest request = new UrlEncodedFormRequest("http://192.168.66.230:8081/cas/login", RequestMethod.POST);

        //url form param
		request.addParam("username", "root");
		request.addParam("password", "root");

        //query string param
		//  request.addUrlParam("version", "v1");

        //ssl
		//request.setUseSSL(true);

        Response response = HttpClientUtil.doRequest(request);
        System.out.println(response.getResponseText()); //response text
        System.out.println(response.getCode()); //response code
        System.out.println(response.getHeader("Set-Cookie"));
    }

}