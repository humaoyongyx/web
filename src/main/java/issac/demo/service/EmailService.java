package issac.demo.service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import issac.demo.model.sys.EmailBean;
import issac.demo.model.sys.EmailConfigBean;
import issac.demo.model.sys.SysConfigModuleBean;
import issac.demo.utils.EmailUtils;
import issac.demo.utils.FreemarkerUtils;

public class EmailService extends SysConfigService<EmailConfigBean> {

	private Logger logger = Logger.getLogger(EmailService.class);

	private JavaMailSenderImpl mailSenderImpl;
	private ThreadPoolTaskExecutor threadPool;


	private EmailConfigBean emailConfig;

	@PostConstruct
	private void init() {
		emailConfig = getConfig();
	}

	public EmailConfigBean refresh(SysConfigModuleBean sysConfigModuleBean) {
		this.sysConfigModuleBean = sysConfigModuleBean;
		updateConfig();
		emailConfig = getConfig();
		return emailConfig;
	}

	public EmailConfigBean getEmailConfig() {
		return emailConfig;
	}

	public void sendMailWithTemplate(String subject, String receiver, String content) {
		String imagePath = EmailService.class.getResource("/").getPath() + "image/";
		Map<String, String> pictures = new HashMap<String, String>();
		pictures.put("image", imagePath + "emailBG.jpg");
		EmailBean emailBean = new EmailBean();
		emailBean.setSubject(subject);
		emailBean.setReceivers(new String[] { receiver });
		emailBean.setPictures(pictures);
		Map<String, Object> root=new HashMap<>();
		root.put("content", content);
		String newContent = FreemarkerUtils.printString("mail.ftl", root);
		System.out.println(newContent);
		emailBean.setContent(newContent);
		sendMail(emailBean);
	}
	public void sendMail(EmailBean emailBean) {
		sendMail(emailBean, null);
	}

	public void sendMail(String subject, String receiver, String content) {
		sendMail(subject, new String[] { receiver }, content, null);
	}

	public void sendMail(String subject, String[] receivers, String content) {
		sendMail(subject, receivers, content, null);
	}

	public void sendMail(String subject, String[] receivers, String content, Map<String, InputStream> attachmentFiles) {
		EmailBean emailBean = new EmailBean();
		emailBean.setSubject(subject);
		emailBean.setReceivers(receivers);
		emailBean.setContent(content);
		sendMail(emailBean, attachmentFiles);
	}

	public void sendMail(EmailBean emailBean, Map<String, InputStream> attachmentFiles) {
		sendMail(emailBean, attachmentFiles, true);
	}

	public void sendMail(final EmailBean emailBean, final Map<String, InputStream> attachmentFiles, boolean asycn) {
		logger.info("Begin to send email[asyn:" + asycn + "]: to->[" + Arrays.toString(emailBean.getReceivers()) + "],subject->[" + emailBean.getSubject() + "]");
		if (asycn) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						EmailUtils.sendMail(emailBean, emailConfig, attachmentFiles, mailSenderImpl);
					} catch (MessagingException e) {
						e.printStackTrace();
						logger.error("send email fail");
					}
				}
			});
		} else {
			try {
				EmailUtils.sendMail(emailBean, emailConfig, attachmentFiles, mailSenderImpl);
			} catch (MessagingException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	String getCategory() {
		return "email";
	}

	@Override
	Class<EmailConfigBean> getConfigBeanClass() {
		return EmailConfigBean.class;
	}

	public JavaMailSenderImpl getMailSenderImpl() {
		return mailSenderImpl;
	}

	public void setMailSenderImpl(JavaMailSenderImpl mailSenderImpl) {
		this.mailSenderImpl = mailSenderImpl;
	}

	public ThreadPoolTaskExecutor getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
		this.threadPool = threadPool;
	}

}
