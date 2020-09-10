package com.tom.common.util;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.tom.common.project.ProjectProperties;
import com.tom.util.StringUtil;

@Component
public class MailSenderUtil {

	String host = "smtp.gmail.com";
	String from = "";
	String to = null;
	String cc = null;
	String bcc = null;
	StringBuilder message;
	String header = "";
	String footer = "";
	String subject = "";

	MimeMessage mimeMessage = null;

	public MailSenderUtil() {
// Default constructor
	}

	public void omniOneMailSender(String to, String cc, String bcc, String header, String footer, String subject,
			StringBuilder message) throws javax.mail.MessagingException {
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.header = header;
		this.footer = footer;
		this.from = "amitsavaniya@gmail.com";
//		this.from = ProjectProperties.get("adminMail");
		this.subject = subject;
		this.message = message;

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
// *** BEGIN CHANGE
		properties.put("mail.smtp.user", "amitsavaniya@gmail.com");

		Session session = Session.getInstance(properties);
		this.mimeMessage = new MimeMessage(session);
		send(session);
	}

	public void send(Session session) throws MessagingException {
		setContent();
		mimeMessage.setFrom(new InternetAddress(from));
		mimeMessage.setSubject(subject);
		mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
		if (!StringUtil.isNullorEmptyWithNullString(cc)) {
			mimeMessage.addRecipient(RecipientType.CC, new InternetAddress(cc));
		}
		Transport t = session.getTransport("smtp");
		t.connect("amitsavaniya@gmail.com", "Aks@2012");
		t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		t.close();
	}

	public void setContent() throws MessagingException {
		ContentType cType = new ContentType();
		cType.setPrimaryType("text");
		cType.setSubType("html");
		mimeMessage.setHeader("Content-Type", "text/html");
		mimeMessage.setContent(header + message + footer, cType.toString());
	}

	public static void main(String args[]) throws MessagingException {
		MailSenderUtil m = new MailSenderUtil();
		StringBuilder message = new StringBuilder("sasasasasasas");
		m.omniOneMailSender("savaniyaamit@gmail.com", "", "A1", "A2", "", "Hello", message);
	}
}
