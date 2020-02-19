package com.ip.erss.competency.iamautomation.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.EmailTemplate;
import com.ip.erss.competency.iamautomation.service.MailService;


@Service
public class MailServiceImpl implements MailService{
	
   private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
   @Autowired
   private JavaMailSender mailSender;
	

	@Override
	public void sendEmail(EmailTemplate emailTemplate) {
		logger.info("=========Testing===============");
		SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("balaji996@gmail");
		 	message.setTo("mbalaji2510@gmail.com");
	        message.setSubject("TestMessage");
	        message.setText("Dear Balaji we have one notice");
		
	        try {
			 	mailSender.send(message);
	            logger.info("Message has been sent.............................");
	        }
	        catch (MailException ex) {
	        	logger.error("MailException  ::  "+ex.getMessage());
	        }
		
	}


}
