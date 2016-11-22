package issac.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;

public class OssService {
	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
	private String accessUrl;

	public String uploadFile(String key, File file) throws IOException {
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			client.putObject(bucketName, key, file);

		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} finally {
			client.shutdown();
		}
		return accessUrl + key;
	}

	public String uploadFile(String key, InputStream file, String contentType) {
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		try {
			objectMetadata.setContentLength(file.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
		objectMetadata.setCacheControl("no-cache");
		objectMetadata.setHeader("Pragma", "no-cache");
		//	objectMetadata.setContentType(contentType);
		try {
			client.putObject(bucketName, key, file, objectMetadata);

		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} finally {
			client.shutdown();
		}
		return accessUrl + key;
	}

	public static String contentType(String FileNameExtension) {
		if (FileNameExtension.toLowerCase().equals("bmp")) {
			return "image/bmp";
		}
		if (FileNameExtension.toLowerCase().equals("gif")) {
			return "image/gif";
		}
		if (FileNameExtension.toLowerCase().equals("jpeg") || FileNameExtension.toLowerCase().equals("jpg") || FileNameExtension.toLowerCase().equals("png")) {
			return "image/jpeg";
		}
		if (FileNameExtension.toLowerCase().equals("html")) {
			return "text/html";
		}
		if (FileNameExtension.toLowerCase().equals("txt")) {
			return "text/plain";
		}
		if (FileNameExtension.toLowerCase().equals("vsd")) {
			return "application/vnd.visio";
		}
		if (FileNameExtension.toLowerCase().equals("pptx") || FileNameExtension.toLowerCase().equals("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FileNameExtension.toLowerCase().equals("docx") || FileNameExtension.toLowerCase().equals("doc")) {
			return "application/msword";
		}
		if (FileNameExtension.toLowerCase().equals("xml")) {
			return "text/xml";
		}
		return "application/octet-stream";
	}


	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
}
