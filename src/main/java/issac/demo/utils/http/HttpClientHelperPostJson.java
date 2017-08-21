package issac.demo.utils.http;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpClientHelperPostJson {

	/** HttpClient */
	private static CloseableHttpClient httpClient;
	/** 连接超时时间(ms) */
	private static int connectTimeout = 5000;
	/** socket响应超时时间(ms) */
	private static int socketTimeout = 20000;
	/** 每个host最多可以有多少连接 */
	private static int maxConnectionsPerHost = 50;
	/** 所有Host总共连接数 */
	private static int maxTotalConnections = 1000;

	private static PoolingHttpClientConnectionManager connManager;

	private static RequestConfig requestConfig;

	private static List<Header> defaultHeaders;

	static {
		init();
	}

	private static void init() {
		connManager = new PoolingHttpClientConnectionManager();
		connManager.setDefaultMaxPerRoute(maxConnectionsPerHost);
		connManager.setMaxTotal(maxTotalConnections);

		requestConfig = RequestConfig.custom()
				.setConnectTimeout(connectTimeout)
				.setSocketTimeout(socketTimeout).build();

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setConnectionManager(connManager);
		builder.setDefaultRequestConfig(requestConfig);
		if (defaultHeaders != null) {
			builder.setDefaultHeaders(defaultHeaders);
		}
		httpClient = builder.build();
	}

	public static String getUriContentUsingPostJson(String url, String json,
			String charset, String contentTypeStr)
			throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		ContentType contentType;
		// String contentType = "application/x-www-form-urlencoded";
		
		switch (contentTypeStr) {
		case "application/x-www-form-urlencoded":
			contentType = ContentType.APPLICATION_FORM_URLENCODED;
			break;
		case "application/atom+xml":
			contentType = ContentType.APPLICATION_ATOM_XML;
			break;
		case "application/json":
			contentType = ContentType.APPLICATION_JSON;
			break;
		case "application/xml":
			contentType = ContentType.APPLICATION_XML;
			break;
		case "text/html":
			contentType = ContentType.TEXT_HTML;
			break;
		case "text/xml":
			contentType = ContentType.TEXT_XML;
			break;
		default:
			contentType = ContentType.WILDCARD;
			break;
		}

		StringEntity se = new StringEntity(json, contentType);
		se.setContentType(contentTypeStr);
		se.setContentEncoding(new BasicHeader("Content-Encoding", charset));
		post.setEntity(se);
		CloseableHttpResponse response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		String uriRequestContent = EntityUtils.toString(entity, charset);
		return uriRequestContent;
	}

}
