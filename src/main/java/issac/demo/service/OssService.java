package issac.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

public class OssService {
	
	private Logger logger=Logger.getLogger(OssService.class);
	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
	private String accessUrl;

	public String uploadFile(String key, File file) throws IOException {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			logger.info("upload file "+key+" to OSS!");
			ossClient.putObject(bucketName, key, file);
		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} finally {
			ossClient.shutdown();
		}
		return accessUrl + key;
	}

	public String uploadFile(String key, InputStream file, String contentType) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		try {
			objectMetadata.setContentLength(file.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
		objectMetadata.setCacheControl("no-cache");
		objectMetadata.setHeader("Pragma", "no-cache");
		if (contentType == null || contentType.trim().equals("")) {
			int lastIndexOf = key.lastIndexOf(".");
			String fileExtensionName = "";
			if (lastIndexOf > -1) {
				fileExtensionName = key.substring(lastIndexOf + 1);
			}
			contentType = getContentType(fileExtensionName);
		}
		objectMetadata.setContentType(contentType);
		try {
			logger.info("upload file "+key+" to OSS!");
			ossClient.putObject(bucketName, key, file, objectMetadata);

		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} finally {
			ossClient.shutdown();
		}
		return accessUrl + key;
	}

	public InputStream downloadOssFile(String key) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			boolean exists = ossClient.doesObjectExist(bucketName, key);
			if (exists) {
				logger.info("download file "+key+" from OSS!");
				OSSObject object = ossClient.getObject(bucketName, key);
				logger.info("Content-Type: " + object.getObjectMetadata().getContentType());
				return object.getObjectContent();
			}
		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} finally {
			ossClient.shutdown();
		}

		return null;

	}
	/**
	 * 
	 * @param key accessURL+key
	 * @return
	 */
	public InputStream downloadFile(String url) {
		return downloadOssFile(url.replace(accessUrl, ""));
	}
	public boolean deleteOssFile(String key) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			boolean exists = ossClient.doesObjectExist(bucketName, key);
			if (exists) {
				logger.info("delete file "+key+" from OSS!");
				ossClient.deleteObject(bucketName, key);
				return true;
			}
		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} finally {
			ossClient.shutdown();
		}
		return true;

	}
	
	/**
	 * 
	 * @param key accessURL+key
	 * @return
	 */
	public boolean deleteFile(String url) {
		return deleteOssFile(url.replace(accessUrl, ""));
	}

	private static String getContentType(String fileExtensionName) {
		if (fileExtensionName.toLowerCase().equals("bmp")) {
			return "image/bmp";
		}
		if (fileExtensionName.toLowerCase().equals("gif")) {
			return "image/gif";
		}
		if (fileExtensionName.toLowerCase().equals("jpeg") || fileExtensionName.toLowerCase().equals("jpg") || fileExtensionName.toLowerCase().equals("png")) {
			return "image/jpeg";
		}
		if (fileExtensionName.toLowerCase().equals("html")) {
			return "text/html";
		}
		if (fileExtensionName.toLowerCase().equals("txt")) {
			return "text/plain";
		}
		if (fileExtensionName.toLowerCase().equals("vsd")) {
			return "application/vnd.visio";
		}
		if (fileExtensionName.toLowerCase().equals("pptx") || fileExtensionName.toLowerCase().equals("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (fileExtensionName.toLowerCase().equals("docx") || fileExtensionName.toLowerCase().equals("doc")) {
			return "application/msword";
		}
		if (fileExtensionName.toLowerCase().equals("xml")) {
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