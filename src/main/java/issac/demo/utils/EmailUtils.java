package issac.demo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import issac.demo.model.sys.EmailBean;
import issac.demo.model.sys.EmailConfigBean;


public class EmailUtils {


	public static void sendMail(EmailBean emailBean, EmailConfigBean emailSetting, JavaMailSenderImpl mailSender) throws MessagingException {
		sendMail(emailBean, emailSetting, null, mailSender);
	}

	public static void sendMail(EmailBean emailBean, EmailConfigBean emailSetting, Map<String, InputStream> attachmentFiles, JavaMailSenderImpl mailSender) throws MessagingException {

		// 设定mail server  
		mailSender.setHost(emailSetting.getHost());
		mailSender.setProtocol(emailSetting.getProtocol());
		mailSender.setPort(Integer.parseInt(emailSetting.getPort()));
		// 建立邮件消息  
		MimeMessage mailMessage = mailSender.createMimeMessage();

		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		// 设置发件人邮箱  
		messageHelper.setFrom(emailSetting.getUsername());
		// 设置收件人邮箱  
		String[] receivers = emailBean.getReceivers();
		if (!CommonUtils.isNotEmpty(receivers)) {
			throw new RuntimeException("收件人地址为空！");
		}
		messageHelper.setTo(receivers);
		// 邮件主题  
		messageHelper.setSubject(emailBean.getSubject());
		// true 表示启动HTML格式的邮件  
		messageHelper.setText(emailBean.getContent(), true);

		Map<String, String> pictures = emailBean.getPictures();
		// 添加图片  
		if (null != pictures) {
			for (Iterator<Map.Entry<String, String>> it = pictures.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("图片" + filePath + "不存在！");
				}

				FileSystemResource img = new FileSystemResource(file);
				messageHelper.addInline(cid, img);
			}
		}

		Map<String, String> attachments = emailBean.getAttachments();
		// 添加附件  
		if (null != attachments) {
			for (Iterator<Map.Entry<String, String>> it = attachments.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("附件" + filePath + "不存在！");
				}

				FileSystemResource fileResource = new FileSystemResource(file);
				messageHelper.addAttachment(cid, fileResource);
			}
		}

		if (attachmentFiles != null) {
			for (Iterator<Entry<String, InputStream>> it = attachmentFiles.entrySet().iterator(); it.hasNext();) {
				Entry<String, InputStream> entry = it.next();
				String cid = entry.getKey();
				InputStream fileInputStream = entry.getValue();
				EmailFileInputStream fileResource = new EmailFileInputStream(fileInputStream);
				messageHelper.addAttachment(cid, fileResource);
			}
		}

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", emailSetting.getAuth());
		prop.put("mail.smtp.timeout", emailSetting.getTimeout());

		// 添加验证  
		SimpleAuthenticator auth = new SimpleAuthenticator(emailSetting.getUsername(), emailSetting.getPassword());
		Session session = Session.getInstance(prop, auth);
		mailSender.setSession(session);
		// 发送邮件  
		mailSender.send(mailMessage);
	}

	public static void sendMail(EmailBean emailBean, JavaMailSenderImpl mailSender) throws MessagingException {

		// 建立邮件消息  
		MimeMessage mailMessage = mailSender.createMimeMessage();

		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		// 设置发件人邮箱  
		messageHelper.setFrom(mailSender.getUsername());
		// 设置收件人邮箱  
		String[] receivers = emailBean.getReceivers();
		if (!CommonUtils.isNotEmpty(receivers)) {
			throw new RuntimeException("收件人地址为空！");
		}
		messageHelper.setTo(receivers);
		// 邮件主题  
		messageHelper.setSubject(emailBean.getSubject());
		// true 表示启动HTML格式的邮件  
		messageHelper.setText(emailBean.getContent(), true);

		Map<String, String> pictures = emailBean.getPictures();
		// 添加图片  
		if (null != pictures) {
			for (Iterator<Map.Entry<String, String>> it = pictures.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("图片" + filePath + "不存在！");
				}

				FileSystemResource img = new FileSystemResource(file);
				messageHelper.addInline(cid, img);
			}
		}

		Map<String, String> attachments = emailBean.getAttachments();
		// 添加附件  
		if (null != attachments) {
			for (Iterator<Map.Entry<String, String>> it = attachments.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("附件" + filePath + "不存在！");
				}
				FileSystemResource fileResource = new FileSystemResource(file);
				messageHelper.addAttachment(cid, fileResource);
			}
		}

		// 发送邮件  
		mailSender.send(mailMessage);
	}

	static class SimpleAuthenticator extends Authenticator {  
	    private String username;  
	    private String password;  
	  
	    public SimpleAuthenticator(String username, String password) {  
	        super();  
	        this.username = username;  
	        this.password = password;  
	    }  
	  
	    protected PasswordAuthentication getPasswordAuthentication() {  
	        return new PasswordAuthentication(username, password);  
	    }  

	}

	static class EmailFileInputStream implements InputStreamSource {
		private InputStream inputStream;

		public EmailFileInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		@Override
		public InputStream getInputStream() throws IOException {
			return inputStream;
		}

	}

	public static void main(String[] args) throws Exception {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			EmailConfigBean emailSetting = new EmailConfigBean();
			emailSetting.setUsername("help@yx.com");
			emailSetting.setHost("localhost");
			emailSetting.setPassword("1");
		
			EmailBean emailBean=new EmailBean();
			emailBean.setReceivers(new String[] { "admin@yx.com" });
			Map<String, String> attachments = new HashMap<String, String>();
			attachments.put("d1.rar", "C:\\Users\\Administrator\\Desktop\\新建文件夹/bootstrap-login-forms_form-3_index.html_files.rar");
			attachments.put("d2.jpg", "C:\\Users\\Administrator\\Desktop\\新建文件夹/home1.jpg");
		
			Map<String, String> pictures = new HashMap<String, String>();
			pictures.put("d1", "C:\\Users\\Administrator\\Desktop\\新建文件夹/home1.jpg");
			pictures.put("d2", "C:\\Users\\Administrator\\Desktop\\新建文件夹/home.jpg");
		
			StringBuilder builder = new StringBuilder();
			builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
			builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
			builder.append("<img src=\"cid:d1\" /><br />");
			builder.append("<img src=\"cid:d2\" /><br />");
			builder.append("</body></html>");
			String content = builder.toString();
			emailBean.setSubject("测试邮件");
			emailBean.setContent(content);
			emailBean.setPictures(pictures);
			emailBean.setAttachments(attachments);
			sendMail(emailBean, emailSetting, mailSender);

	}
}
