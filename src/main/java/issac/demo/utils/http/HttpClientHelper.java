package issac.demo.utils.http;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientHelper implements HttpClient {

	/**
	 * HttpClient
	 */
	private static CloseableHttpClient httpClient;
	/**
	 * 连接超时时间(ms)
	 */
	private static int connectTimeout = 5000;
	/**
	 * socket响应超时时间(ms)
	 */
	private static int socketTimeout = 20000;
	/**
	 * 每个host最多可以有多少连接
	 */
	private static int maxConnectionsPerHost = 200;
	/**
	 * 所有Host总共连接数
	 */
	private static int maxTotalConnections = 10000;

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

	/**
	 * 使用GET方式请求指定URI并以字符串形式获取响应内容，响应的文本内容根据Content-Type头来解码，如果没有此Header则默认使用ISO
	 * -8859-1来解码
	 * 
	 * impl结尾的为需要ssl验证和不需要ssl验证不同环境下切换使用
	 *
	 * @param url
	 * @param params
	 * @return 响应内容
	 */
	public String getUriContentUsingGetImpl(String url,
			Map<String, Object> params) {
		return getUriRequestContent(url, true, params, null, null);
	}

	public String getUriContentUsingGetImpl(String url,
			Map<String, Object> params, List<Header> headerList) {
		return getUriRequestContent(url, true, params, headerList, null);
	}

	public String getUriContentUsingPostImpl(String url,
			Map<String, Object> params, String charset) {
		return getUriRequestContent(url, false, params, null, charset);
	}

	public String getUriContentUsingPostImpl(String url,
			Map<String, Object> params, List<Header> headerList, String charset) {
		return getUriRequestContent(url, false, params, headerList, charset);
	}

	public static String getUriContentUsingGet(String url,
			Map<String, Object> params) {
		return getUriRequestContent(url, true, params, null, null);
	}

	public static String getUriContentUsingGet(String url,
			Map<String, Object> params, List<Header> headerList) {
		return getUriRequestContent(url, true, params, headerList, null);
	}

	public static String getUriContentUsingPost(String url,
			Map<String, Object> params, String charset) {
		return getUriRequestContent(url, false, params, null, charset);
	}

	public static String getUriContentUsingPost(String url,
			Map<String, Object> params, List<Header> headerList, String charset) {
		return getUriRequestContent(url, false, params, headerList, charset);
	}

	/**
	 * 以GET或POST方法请求指定URI并以字符串形式获取响应内容，响应的文本内容根据Content-Type头来解码，
	 * 如果没有此Header则默认使用ISO-8859-1来解码
	 *
	 * @param url
	 *            请求地址
	 * @param useGet
	 *            是否使用GET方式
	 * @param params
	 *            请求参数
	 * @param headers
	 * @param charset
	 *            POST时指定的参数编码，如果为空则默认使用ISO-8859-1编码参数
	 * @return 响应内容，字符串的解码字符集首先从响应头Content-Type头获取，如果没有此Header则默认使用ISO-8859-1来解码
	 */
	public static String getUriRequestContent(String url, boolean useGet,
			Map<String, Object> params, List<Header> headers, String charset) {
		HttpUriRequest request = null;
		try {
			// GET request
			if (useGet) {
				if (params != null && params.size() > 0) {
					URIBuilder builder = new URIBuilder(url);
					for (Map.Entry<String, Object> param : params.entrySet()) {
						Object value = param.getValue();
						// 值为null时忽略参数
						if (value == null) {
							continue;
						}
						builder.addParameter(param.getKey(), value.toString());
					}
					request = new HttpGet(builder.build());
				} else {
					request = new HttpGet(url);
				}
			}
			// POST request
			else {
				request = new HttpPost(url);
				HttpPost post = (HttpPost) request;
				if (params != null && params.size() > 0) {
					List<NameValuePair> nvList = new ArrayList<NameValuePair>();
					for (Map.Entry<String, Object> param : params.entrySet()) {
						Object value = param.getValue();
						if (value == null) {
							continue;
						}
						nvList.add(new BasicNameValuePair(param.getKey(), value
								.toString()));
					}
					UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
							nvList, charset);
					post.setEntity(formEntity);
				}
			}
			// add header
			if (headers != null && headers.size() > 0) {
				for (Header header : headers) {
					request.addHeader(header);
				}
			}

			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			} else {
				return charset == null ? EntityUtils.toString(entity)
						: EntityUtils.toString(entity, charset);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			((HttpRequestBase) request).releaseConnection();
		}
	}

	/**
	 * 请求指定URL地址并调用提供的响应回调方法来处理响应
	 *
	 * @param url
	 *            请求地址
	 * @param isGet
	 *            是否使用GET方式
	 * @param params
	 *            请求参数
	 * @param charset
	 *            POST时指定的参数编码，如果为空则默认使用ISO-8859-1编码参数
	 * @param callback
	 */
	public static <T> T requestUri(String url, boolean isGet,
			Map<String, Object> params, String charset,
			ResponseHandler<? extends T> responseHandler) {
		HttpUriRequest request = null;
		try {
			// GET request
			if (isGet) {
				if (params != null && params.size() > 0) {
					URIBuilder builder = new URIBuilder(url);
					for (Map.Entry<String, Object> param : params.entrySet()) {
						Object value = param.getValue();
						if (value == null) {
							continue;
						}
						builder.addParameter(param.getKey(), value.toString());
					}
					request = new HttpGet(builder.build());
				} else {
					request = new HttpGet(url);
				}
			}
			// POST request
			else {
				request = new HttpPost(url);
				HttpPost post = (HttpPost) request;
				if (params != null && params.size() > 0) {
					List<NameValuePair> nvList = new ArrayList<NameValuePair>();
					for (Map.Entry<String, Object> param : params.entrySet()) {
						Object value = param.getValue();
						if (value == null) {
							continue;
						}
						nvList.add(new BasicNameValuePair(param.getKey(), value
								.toString()));
					}
					UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
							nvList, charset);
					post.setEntity(formEntity);
				}
			}
			return httpClient.execute(request, responseHandler);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			((HttpRequestBase) request).releaseConnection();
		}
	}

	public static String execute(HttpUriRequest request) {
		try {
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			} else {
				return EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T execute(HttpUriRequest request,
			ResponseHandler<? extends T> responseHandler) {
		try {
			if (responseHandler == null) {
				throw new NullPointerException(
						"response handler can not be null");
			}
			return httpClient.execute(request, responseHandler);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void config(int connectTimeout, int socketTimeout) {
		try {
			httpClient.close();
			HttpClientHelper.socketTimeout = socketTimeout;
			HttpClientHelper.connectTimeout = connectTimeout;
			init();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
