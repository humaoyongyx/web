package issac.demo.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import issac.demo.model.sys.EmailBean;
import issac.demo.service.EmailService;
import issac.demo.utils.EmailUtils;
import issac.demo.utils.FreemarkerUtils;

public class EmailTest extends AbstractBaseTest {
	
	@Resource
	JavaMailSenderImpl mailSender;


	@Resource
	EmailService emailService;

	@Test
	public void simpleTest() throws MessagingException {
		EmailBean emailBean = new EmailBean();
		emailBean.setReceivers(new String[] { "admin@yx.com" });
		Map<String, String> attachments = new HashMap<String, String>();
		attachments.put("d1.rar", "C:\\Users\\Administrator\\Desktop\\新建文件夹/bootstrap-login-forms_form-3_index.html_files.rar");
		attachments.put("d2.jpg", "C:\\Users\\Administrator\\Desktop\\新建文件夹/home1.jpg");

		Map<String, String> pictures = new HashMap<String, String>();
		/*	pictures.put("image", "C:\\Users\\Administrator\\Desktop\\新建文件夹/home1.jpg");*/
		pictures.put("image", "C:\\Users\\Administrator\\Desktop\\新建文件夹/home.jpg");

		StringBuilder builder = new StringBuilder();
		/*	builder.append("<html><body background=\"cid:image\">看看附件中的资料，你会知道世界为什么是平的。<br />");
			builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
					builder.append("<img src=\"cid:d1\" /><br />");
					builder.append("<img src=\"cid:d2\" /><br />");
			builder.append("</body></html>");
			String content = builder.toString();*/
		Map<String, Object> root = new HashMap<>();
		root.put("content", "test");
		String newContent = FreemarkerUtils.printString("mail.ftl", root);
		emailBean.setSubject("测试邮件");
		emailBean.setContent(newContent);
		emailBean.setPictures(pictures);
		emailBean.setAttachments(attachments);
		EmailUtils.sendMail(emailBean, mailSender);
	}
	
	@Test
	public void simpleTest2() throws InterruptedException {

		String subject = "hello world";
		String receiver = "admin@yx.com";
		String content = "hello world content";
		emailService.sendMailWithTemplate(subject, receiver, content);
		Thread.sleep(3000);
		System.out.println("complete");
	}
}
