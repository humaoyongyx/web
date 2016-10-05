package issac.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPUtils {
	private Logger logger = Logger.getLogger(FTPUtils.class);

	private String username = "ftp"; //FTP 登录用户名 
	private String password = "123456"; //FTP 登录密码 
	private String host = "192.168.0.102"; //FTP 服务器地址IP地址
	private int port = 21; //FTP 端口 
	private boolean showCommond = false;
	private FTPClient ftpClient;

	public void login() throws SocketException, IOException {
		if (ftpClient == null) {
			ftpClient = new FTPClient();
			connect();
		} else {
			if (!ftpClient.isConnected()) {
				connect();
			}
		}

	}

	private void connect() throws IOException {
		int reply;
		ftpClient.connect(host, port);
		if (showCommond) {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		}
		ftpClient.setControlEncoding("UTF-8");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
		conf.setServerLanguageCode("zh");
		ftpClient.setDataTimeout(120000);
		ftpClient.login(username, password);
		reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			logger.info("connect fail...!");
		}
		logger.info("login in ftp server successfully!");
	}

	public void showCommond() {

	}

	/**  
	* 释放FTP  
	*/
	public void logout() {
		if (ftpClient == null) {
			return;
		}
		if (ftpClient.isAvailable()) {
			try {
				// 退出FTP    
				ftpClient.logout();
			} catch (IOException e) {
				logger.error("FTP登录退出异常:" + e.getMessage());
			}
		}
		if (ftpClient.isConnected()) {
			try {
				// 断开连接    
				ftpClient.disconnect();
			} catch (IOException e) {
				logger.error("FTP断开连接异常:" + e.getMessage());
			}
		}
	}

	public void uploadFile(String localFilePath, String remoteFilePath) {
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			String remoteFileName = remoteFilePath;
			if (remoteFileName.contains("/")) {
				remoteFileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
				String directory = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("/") + 1);
				if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {
					int start = 0, end = 0;
					if (directory.startsWith("/")) {
						start = 1;
					} else {
						start = 0;
					}
					end = directory.indexOf("/", start);
					while (true) {
						String subDirectory = remoteFilePath.substring(start, end);
						if (!ftpClient.changeWorkingDirectory(subDirectory)) {
							if (ftpClient.makeDirectory(subDirectory)) {
								ftpClient.changeWorkingDirectory(subDirectory);
							} else {
								logger.info("创建目录失败");
							}
						}
						start = end + 1;
						end = directory.indexOf("/", start);
						// 检查所有目录是否创建完毕    
						if (end <= start) {
							break;
						}
					}
				}
			}

			/*****执行文件上传******/
			InputStream input = null;

			File f = new File(localFilePath);
			FTPFile[] fs = ftpClient.listFiles(remoteFilePath);
			input = new FileInputStream(f);
			ftpClient.storeFile(remoteFileName, input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFile(String remoteFilePath) {
		try {
			ftpClient.deleteFile(remoteFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public void downFile(String remoteFileDir, String remoteFileName, HttpServletResponse response) {
		try {
			ftpClient.changeWorkingDirectory(remoteFileDir);
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ftpFile : fs) {
				if (ftpFile.getName().equals(remoteFileName)) {
					response.setHeader("Content-disposition", "attachment;localFileName=" + URLEncoder.encode(remoteFileName, "UTF-8"));
					File f = new File(remoteFileName);
					OutputStream os = new FileOutputStream(f);
					ftpClient.retrieveFile(new String(ftpFile.getName().getBytes("UTF-8"), "ISO-8859-1"), os);
					os.flush();
					os.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFileContent(String remoteFileFolder, String remoteFileName) {
		String content = null;
		try {
			ftpClient.changeWorkingDirectory(remoteFileFolder);
			FTPFile[] fs = ftpClient.listFiles();
				for (FTPFile ftpFile : fs) {
				if (ftpFile.getName().equals(remoteFileName)) {
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ftpClient.retrieveFile(ftpFile.getName(), bos);
						bos.flush();
						bos.close();
						content = new String(bos.toByteArray(), "UTF-8");
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public void renameFile(String oldFileName, String newFileName) {
		try {
			ftpClient.rename(oldFileName, newFileName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) throws SocketException, IOException {
		FTPUtils ftpUtils = new FTPUtils();
		ftpUtils.login();
		/*	ftpUtils.uploadFile("E:\\test\\upload\\test.xlsx", "/upload/test.xlsx");*/
//		ftpUtils.deleteFile("/upload/test.xlsx");

		System.out.println(ftpUtils.readFileContent("/upload", "test.txt"));
		/*	FTPFile[] listFiles = ftpUtils.ftpClient.listFiles();
		
			for (FTPFile ftpFile : listFiles) {
				System.out.println(ftpFile.getName());
			}*/
		/*	ftpUtils.getFtpClient().changeWorkingDirectory("/upload");
			ftpUtils.renameFile("test", "test.txt.t");*/
		ftpUtils.logout();
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isShowCommond() {
		return showCommond;
	}

	public void setShowCommond(boolean showCommond) {
		this.showCommond = showCommond;
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

}
