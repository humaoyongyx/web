package issac.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 支持es5.4.x
 * @author issac.hu
 *
 */
public class ESUtils {
	private static volatile TransportClient client = null;
	private static String clusterName = null;// 实例名称
	private static String hosts = null;// elasticSearch服务器ip
	private static List<Map<String, Integer>> hostsList = new LinkedList<>();
	private static String index = null;// 索引名称
	private static boolean sniff = true;

	private static final String ES_DEFAULT_CLUSTER_NAME = "elasticsearch";
	private static final String ES_DEFAULT_IP = "localhost";
	private static final int ES_DEFAULT_PORT = 9300;

	public static final int ERROR = -1;
	public static final int CREATED = 0;
	public static final int UPDATED = 1;
	public static final int DELETED = 2;
	public static final int NOT_FOUND = 3;
	public static final int NOOP = 4;

	static {
		try {
			// 读取elasticsearch.properties文件
			Properties props = new Properties();
			InputStream in = ESUtils.class.getResourceAsStream("/elasticsearch.properties");
			props.load(in);// 加载文件
			props.list(System.out);
			// 读取信息
			clusterName = props.getProperty("clusterName");
			if (clusterName == null || clusterName.trim().equals("")) {
				clusterName = ES_DEFAULT_CLUSTER_NAME;
			}
			hosts = props.getProperty("hosts");

			if (hosts == null || hosts.trim().equals("")) {
				HashMap<String, Integer> host = new HashMap<>();
				host.put(ES_DEFAULT_IP, ES_DEFAULT_PORT);
				hostsList.add(host);
			} else {
				String[] hostRaws = hosts.split(",");
				for (String hostRawItem : hostRaws) {
					String[] ipPort = hostRawItem.split(":");
					HashMap<String, Integer> host = new HashMap<>();
					if (ipPort.length > 1) {
						host.put(ipPort[0], Integer.parseInt(ipPort[1]));
					} else {
						host.put(ipPort[0], ES_DEFAULT_PORT);
					}
					hostsList.add(host);
				}
			}

			index = props.getProperty("index");

			String sniffs = props.getProperty("sniff");
			if (sniffs == null || sniffs.trim().equals("") || !sniffs.equals("true") || !sniffs.equals("false")) {
				sniff = true;
			} else {
				sniff = Boolean.getBoolean(sniffs);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("加载数据库配置文件出错！");
		}
	}

	public static TransportClient getClientInstance() {

		if (client == null) {
			synchronized (ESUtils.class) {
				if (client == null) {
					Settings settings = Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", sniff).build();// 设置集群名称
					client = new PreBuiltTransportClient(settings);// 创建client

					try {
						for (Map<String, Integer> host : hostsList) {
							for (Entry<String, Integer> ipPort : host.entrySet()) {
								client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipPort.getKey()), ipPort.getValue()));// 增加地址和端口
							}
						}

					} catch (UnknownHostException e) {
						System.err.println("ElasticSearch连接失败！");
						e.printStackTrace();

					}
				}
			}
		}

		return client;
	}

	/**
	 * 反射的方式
	 * @param index  
	 * @param type
	 * @param object  普通对象，必须有id字段，否则Id为null
	 * @return   0 created 1 updated 2 deleted 3 not found 4 noop
	 */
	public static int saveOrUpdate(String index, String type, Object object) {
		if (object == null || object.toString().trim().equals("")) {
			return NOOP;
		}
		JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
		String id = null;
		if (jsonObject.get("id") != null) {
			id = jsonObject.get("id").toString();
		}

		return saveOrUpdate(index, type, id, object);
	}

	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @param object
	 * @return    0 created 1 updated 2 deleted 3 not found 4 noop
	 */
	public static int saveOrUpdate(String index, String type, String id, Object object) {
		if (object == null || object.toString().trim().equals("")) {
			return NOOP;
		}
		IndexResponse indexResponse = getClientInstance().prepareIndex(index, type, id).setSource(JSON.toJSONString(object), XContentType.JSON).get();
		System.out.println(indexResponse);
		if (indexResponse.getResult() == Result.CREATED) {
			return CREATED;
		} else if (indexResponse.getResult() == Result.UPDATED) {
			return UPDATED;
		}
		return indexResponse.getResult().getOp();
	}

	/**
	 * 异步操作，不保证先后顺序
	 * @param index
	 * @param type
	 * @param data 必须有id字段，否则不成功,支持普通java对象和map对象
	 * @return 0 成功 -1 失败
	 */
	public static int saveOrUpdate(String index, String type, List<?> data) {
		if (data == null || data.isEmpty()) {
			return NOOP;
		}
		BulkRequestBuilder bulkRequest = getClientInstance().prepareBulk();
		for (Object object : data) {
			JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
			String id = null;
			if (jsonObject.get("id") != null) {
				id = jsonObject.get("id").toString();
			}
			if (id != null && !id.trim().equals("")) {
				bulkRequest.add(getClientInstance().prepareIndex(index, type, id).setSource(JSON.toJSONString(object), XContentType.JSON));
			}

		}
		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			return ERROR;
		}
		return 0;
	}

	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @return   0 created 1 updated 2 deleted 3 not found 4 noop
	 */
	public static int delete(String index, String type, String id) {
		DeleteResponse response = getClientInstance().prepareDelete(index, type, id).get();
		System.out.println(response);
		if (response.getResult() == Result.NOT_FOUND) {
			return NOT_FOUND;
		} else if (response.getResult() == Result.DELETED) {
			return DELETED;
		}
		return response.getResult().getOp();
	}

	/**
	 * 
	 * @param indices
	 * @return 如果不存在或者删除失败返回false 有且只有存在index且删除成功时删除返回true
	 */
	public static boolean deleteIndex(String... indices) {
		IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indices);

		try {
			IndicesExistsResponse indicesExistsResponse = getClientInstance().admin().indices().exists(inExistsRequest).get();
			boolean exists = indicesExistsResponse.isExists();
			if (!exists) {
				return false;
			}
		} catch (InterruptedException | ExecutionException e) {
			return false;
		}

		DeleteIndexResponse deleteIndexResponse = getClientInstance().admin().indices().prepareDelete(indices).get();
		boolean acknowledged = deleteIndexResponse.isAcknowledged();
		if (acknowledged) {
			return true;
		}
		return false;

	}

	public static long deleteType(String index, String type) {

		BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(getClientInstance()).filter(QueryBuilders.matchQuery("_type", type)).source(index).get();

		long deleted = response.getDeleted();
		return deleted;
	}

	/**
	 * 
	 * @param index
	 * @param type
	 * @param id 
	 * @return GetResponse
	 */
	public static GetResponse get(String index, String type, String id) {
		GetResponse response = getClientInstance().prepareGet(index, index, id).get();
		return response;
	}

	/**
	 * 
	 * @param index
	 * @param type
	 * @param id 为null自动创建id
	 * @param object
	 * @return   0 created 1 updated 2 deleted 3 not found 4 noop
	 */
	public static int update(String index, String type, String id, Object object) {
		if (object == null || object.toString().trim().equals("")) {
			return ERROR;
		}
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(id);
		updateRequest.doc(JSON.toJSONString(object), XContentType.JSON);
		try {
			UpdateResponse updateResponse = getClientInstance().update(updateRequest).get();
			if (updateResponse.getResult() == Result.UPDATED) {
				return UPDATED;
			}
		} catch (InterruptedException | ExecutionException e) {
			return NOT_FOUND;
		}
		return NOOP;
	}

	/**
	 * 
	 * @param index
	 * @param type
	 * @param field
	 * @param value 搜索的值
	 * @param tag    高亮的tag名如输入tag那么返回<tag></tag>
	 * @param from  分页从第几个
	 * @param to      分页到第几个
	 * @return
	 */
	public static SearchHits search(String index, String type, String field, String value, String tag, int from, int to) {
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.preTags("<" + tag + ">");
		highlightBuilder.postTags("</" + tag + ">");
		highlightBuilder.field(field);
		SearchResponse response = getClientInstance().prepareSearch(index, type).setQuery(QueryBuilders.matchQuery(field, value)).highlighter(highlightBuilder).setFrom(from).setSize(to).get();
		SearchHits hits = response.getHits();
		return hits;
	}
	
	
	/**
	 * 此种形式适用于将拼音搜索分拆成全拼、首字母拼写和中文，然后搜索匹配组合
	 * 这种方式需要将中文先转换成拼音预存储，且不支持高亮
	 * @param index
	 * @param type
	 * @param fields
	 * @param value
	 * @param tag
	 * @param from
	 * @param to
	 * @return
	 */
	public static SearchHits search(String index, String type, String[] fields, String value, int from, int to) {
		SearchResponse response = getClientInstance().prepareSearch(index, type).setQuery(QueryBuilders.multiMatchQuery(value, fields).type(Type.PHRASE_PREFIX)).setFrom(from).setSize(to).get();
		SearchHits hits = response.getHits();
		return hits;
	}

	public static SearchHits search(String index, String type, String field, String value) {
		return search(index, type, field, value, "em", 0, 100);
	}

	/**
	 * 
	 * @param index
	 * @param type
	 * @param field
	 * @param value  搜索的值
	 * @param tag    高亮的tag名如输入tag那么返回<tag></tag>
	 * @param from 分页从第几个
	 * @param to 分页到第几个
	 * @param replace 是否替换原有字段内容为高亮
	 * @return Map<String, Object> 可以获取total和data两个key其中total为long，data为 List<JSONObject>
	 */
	public static Map<String, Object> searchHighlight(String index, String type, String field, String value, String tag, int from, int to, boolean replace) {
		SearchHits search = search(index, type, field, value, tag, from, to);
		Map<String, Object> map = new HashMap<>();
		map.put("total", search.totalHits);
		List<JSONObject> data = new ArrayList<>();
		for (SearchHit searchHit : search) {
			Text text = searchHit.getHighlightFields().get("name").getFragments()[0];
			JSONObject jsonObject = JSON.parseObject(searchHit.getSourceAsString());
			jsonObject.put(field + "Highlight", text.toString());
			if (replace) {
				jsonObject.put(field, text.toString());
			}
			data.add(jsonObject);
		}
		map.put("data", data);
		return map;
	}

	public static Map<String, Object> searchHighlight(String index, String type, String field, String value) {
		return searchHighlight(index, type, field, value, "em", 0, 100, false);
	}

	public static void main(String[] args) {
		 SearchHits search = search("test", "test", new String[]{"name","name2","name3"}, "ld",  0,100);
	
		 for (SearchHit searchHit : search) {
			 System.out.println(searchHit.getSourceAsString());
		}
		 
		 Map<String, Object> searchHighlight = searchHighlight("user", "user", "name", "ld");
		 System.out.println(searchHighlight);

	}

}
