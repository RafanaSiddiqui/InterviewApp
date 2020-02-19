package com.ip.erss.competency.iamautomation.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.SystemPropertyUtils;

import com.ip.erss.competency.iamautomation.model.EmailTemplate;
import com.ip.erss.competency.iamautomation.service.EmailTemplateService;
import com.ip.erss.competency.iamautomation.service.MailNotificationService;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

@Service
public class MailNotificationServiceImpl implements MailNotificationService{

	private static final Logger logger = LoggerFactory.getLogger(MailNotificationService.class);

	@Autowired
	private EmailTemplateService emailTemplateService;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(String templateId, Map<String, String> response) {

		EmailTemplate emailTemplate = emailTemplateService.getByTemplateid(templateId);
		JavaMailSender mailSender= null;
			mailSender = getMailSender(response);
			MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(emailTemplate, response);

	        try {
	            mailSender.send(preparator);
	            logger.info("Message With Attachement has been sent.............................");
	        } catch (MailException ex) {
	        	//logger.error("MailException sendEmail  ::  "+ex.getMessage());
	        }

	}

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(
			EmailTemplate emailTemplate,Map<String, String> placeholderMap) {

		 MimeMessagePreparator preparator = new MimeMessagePreparator() {

	            public void prepare(MimeMessage mimeMessage) throws Exception {
	                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	                
	                String[] recipientList = null;
	                String[] ccList = null;
	                
	                logger.info("toList  ::  "+placeholderMap.get("toList"));
	                logger.info("ccList  ::  "+placeholderMap.get("ccList"));
	                if(null != placeholderMap ) {
	                	recipientList = placeholderMap.get("toList").split(",");
		                ccList = placeholderMap.get("ccList").split(",");
	                }
	                
	                logger.info("recipientList  ::  "+recipientList);
	                logger.info("ccList  ::  "+ccList);
	                
	                /*recipientList = emailTemplate.getTo().split(",");
	                ccList = emailTemplate.getCc().split(",");*/
	                helper.setFrom(emailTemplate.getFrom());
	                helper.setTo(recipientList);
	                helper.setCc(ccList);
	                // freemaker Email template Implementation

	                Map<String, Object> model = new HashMap<String, Object>();

	                model.put("emailTemplate", emailTemplate);
	                model.putAll(placeholderMap);
	                model.forEach((k,v)->logger.info(" Model map - Key : " + k + " Model map - Value : " + v));

	                String subjectTest1 = geFreeMarkerTemplateSubject(model);
	                helper.setSubject(subjectTest1);
	                logger.info("subject Template content : "+subjectTest1);
	                String text = geFreeMarkerTemplateContent(model);//Use Freemarker or Velocity
	                logger.info("Template content : "+text);


	              /*  String content = "Dear " + emailTemplate.getName()
	                        + ", thank you for placing order. Your order id is " + emailTemplate.getName() + ".";*/

	                helper.setText(text, true);

	                // Add a resource as an attachment
	              //  helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));

	            }

				private String geFreeMarkerTemplateSubject(Map<String, Object> model) {
					// TODO Auto-generated method stub
					StringBuffer subjectContent = new StringBuffer();
					String template1 = "template1";
					StringTemplateLoader stringLoader = new StringTemplateLoader();
					stringLoader.putTemplate(template1,emailTemplate.getSubject());
					logger.info("=========Subject======"+emailTemplate.getSubject());
					Configuration cfg = new Configuration();
					cfg.setTemplateLoader(stringLoader);
					  try{
					        /* content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
					        		 cfg.getTemplate("fm_mailTemplate.txt"),model));
					         */
						  subjectContent.append(FreeMarkerTemplateUtils.processTemplateIntoString(
						        		 cfg.getTemplate(template1),model));
						  logger.info("=====Subject Line"+subjectContent.toString());
					         return subjectContent.toString();
					        }catch(Exception e){
					            logger.info("Exception occured while processing fmtemplate:"+e.getMessage());
					        }
					          return "";
				}

				private String geFreeMarkerTemplateContent(
						Map<String, Object> model) {
					StringBuffer content = new StringBuffer();
					String template = "template";
					StringTemplateLoader stringLoader = new StringTemplateLoader();
					stringLoader.putTemplate(template,emailTemplate.getMessage());
					Configuration cfg = new Configuration();
					cfg.setTemplateLoader(stringLoader);
					//cfg.setClassForTemplateLoading(this.getClass(), "emailTemplate.getMessage()");
			        try{
			        /* content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
			        		 cfg.getTemplate("fm_mailTemplate.txt"),model));
			         */
			        	content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
				        		 cfg.getTemplate(template),model));
			         return content.toString();
			        }catch(Exception e){
			            logger.info("Exception occured while processing fmtemplate:"+e.getMessage());
			        }
			          return "";

				}
	        };

		return preparator;
	}

	private MimeMessagePreparator getContentAsInlineResourceMessagePreparator(final EmailTemplate emailTemplate) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setSubject("Your order on Demoapp with Inline resource");
                helper.setFrom(emailTemplate.getFrom());
                helper.setTo(emailTemplate.getTo());

                String content = "Dear " + emailTemplate.getName()
                        + ", thank you for placing order. Your order id is " + emailTemplate.getName() + ".";

                // Add an inline resource.
                // use the true flag to indicate you need a multipart message
                helper.setText("<html><body><p>" + content + "</p><img src='cid:company-logo'></body></html>", true);
                helper.addInline("company-logo", new ClassPathResource("banner.png"));
            }
        };
        return preparator;
    }

	 public JavaMailSender getMailSender(Map<String, String> response) {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	 	       // Using gmail.
	        mailSender.setHost(response.get("smtphost"));
	        //logger.info("==========Testing port for String value"+response.get("smtpport"));
	        int port = Integer.parseInt(response.get("smtpport"));
	        //logger.info("======port no===="+port);
	        //logger.info("======Testing port======"+mailSender.getHost());
	        mailSender.setPort(port);
	        //logger.info("======Testing host======"+mailSender.getPort());
	        Properties javaMailProperties = new Properties();
	        javaMailProperties.put("mail.smtp.starttls.enable", "true");
	        javaMailProperties.put("mail.smtp.auth", "false");
	        javaMailProperties.put("mail.transport.protocol", "smtp");
	        javaMailProperties.put("mail.debug", "true");

	        mailSender.setJavaMailProperties(javaMailProperties);
	        return mailSender;
	    }
}
