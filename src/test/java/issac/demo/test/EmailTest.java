package issac.demo.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import issac.demo.model.sys.EmailBean;
import issac.demo.model.sys.EmailConfigBean;
import issac.demo.service.EmailService;
import issac.demo.utils.EmailUtils;

public class EmailTest extends AbstractBaseTest {
	
	JavaMailSenderImpl mailSender;

	@Resource
	EmailService emailConfigService;

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
		EmailUtils.sendMail(emailBean, mailSender);
	}
	
	@Test
	public void simpleTest2() throws InterruptedException {
		/*	EmailConfig config = emailConfigService.getConfig();
			System.out.println(config);*/
		/*	emailService.sendMail("test mail", "admin@yx.com", "helloworld!");
			Thread.sleep(10000);
			System.out.println("EmailTest.simpleTest2()");
			Thread.sleep(30000);
			System.out.println("EmailTest.simpleTest2()");*/

		EmailConfigBean emailConfig = new EmailConfigBean();
		emailConfig.setHost("localhost");
		System.out.println(emailConfigService.refresh(emailConfig));
	}
}
