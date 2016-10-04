package issac.demo.model.sys;

import java.util.Map;

public class EmailBean {
	private String[] receivers;
	private String subject;
	private String content;
	/** 
	* 邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址 
	*/
	private Map<String, String> pictures;
	/** 
	* 邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址 
	*/
	private Map<String, String> attachments;

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getPictures() {
		return pictures;
	}

	public void setPictures(Map<String, String> pictures) {
		this.pictures = pictures;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "EmailBean [subject=" + subject + ", content=" + content + "]";
	}

}
