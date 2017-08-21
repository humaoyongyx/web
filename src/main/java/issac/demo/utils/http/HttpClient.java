package issac.demo.utils.http;

import java.util.List;
import java.util.Map;

import org.apache.http.Header;

public interface HttpClient {

	public String getUriContentUsingGetImpl(String url, Map<String, Object> params);

	public String getUriContentUsingGetImpl(String url, Map<String, Object> params,
			List<Header> headerList);

	public String getUriContentUsingPostImpl(String url,
			Map<String, Object> params, String charset);

	public String getUriContentUsingPostImpl(String url,
			Map<String, Object> params, List<Header> headerList, String charset);

}
