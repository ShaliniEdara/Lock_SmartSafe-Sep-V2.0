package com.safesmart.safesmart.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	public SimpleMailMessage constructEmail(String subject, String body, String emailId) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(emailId);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

	public void constructMimeMessageEmail(String subject, String body, String toEmailId) throws MessagingException, MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

		Session session = Session.getInstance(props, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(env.getProperty("spring.mail.username"),
						env.getProperty("spring.mail.password"));
		    }
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(env.getProperty("support.email")));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse(toEmailId));
		message.setSubject(subject);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(body, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
		
		System.out.println("Sent message successfully....");
	}
	
	public void sendEmailWithAttachment(String subject, String body, String toEmailId) throws MessagingException, IOException {

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(toEmailId);
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(msg);

    }

}
