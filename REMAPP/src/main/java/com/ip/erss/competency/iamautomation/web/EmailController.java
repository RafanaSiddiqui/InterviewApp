package com.ip.erss.competency.iamautomation.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ip.erss.competency.iamautomation.model.EmailTemplate;
import com.ip.erss.competency.iamautomation.service.EmailTemplateService;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ip.erss.iam.itsmautomation.configuration,com.ip.erss.iam.itsmautomation.controller,"
+"com.ip.erss.iam.itsmautomation.dao,com.ip.erss.iam.itsmautomation.model,com.ip.erss.iam.itsmautomation.service,com.ip.erss.iam.itsmautomation.utils,"
		+"com.ip.erss.competency.iamautomation.web, com.ip.erss.competency.iamautomation.service, com.ip.erss.competency.iamautomation.dao,com.ip.erss.competency.iamautomation.exception, com.ip.erss.competency.iamautomation.validator")

public class EmailController {  

	@Autowired
	private EmailTemplateService emailTemplateService;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@RequestMapping(value = "/emailTemplateService/", method = RequestMethod.POST)
	public ResponseEntity<Void> createEmailTemplate(@RequestBody EmailTemplate emailTemplate,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating createEmailTemplate " + emailTemplate.getId());
		emailTemplateService.create(emailTemplate);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/createApi/{id}").buildAndExpand(emailTemplate.getName()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/emailTemplateService/", method = RequestMethod.GET)
	public List<EmailTemplate> loadAll() {
		logger.info("EmailTemplates request received for loadAllEmailTemplates");

		Map<String, String> response = new HashMap<String, String>();
		List<EmailTemplate> emailList = new ArrayList<EmailTemplate>();
		try {

			emailList = emailTemplateService.loadAll();
			logger.info("EmailTemplates request received for loadAllEmailTemplates" + emailList.size());
			response.put("status", "success");
			for (EmailTemplate template : emailList) {

				response.put(template.getId().toString(), template.toString());
			}

		} catch (Exception e) {
			//logger.error("Error occurred while trying to process EmailTemplates", e);
			//logger.error("exception occured while creating EmailTemplates" + e);
			//e.printStackTrace();
			response.put("status", "fail");
		}

		return emailList;
	}

	@RequestMapping(value = "/emailTemplateService/{id}", method = RequestMethod.PUT)
	public ResponseEntity<EmailTemplate> upateEmailTemp(@PathVariable("id") long id,
			@RequestBody EmailTemplate emailTemplate) {
		logger.info("Updating API Register  " + id);

		EmailTemplate currentEmail = emailTemplateService.getEmailTemplate(id);
		if (currentEmail == null) {
			return new ResponseEntity<EmailTemplate>(HttpStatus.NOT_FOUND);
		}

		currentEmail.setName(emailTemplate.getName());
		currentEmail.setFrom(emailTemplate.getFrom());
		currentEmail.setSubject(emailTemplate.getSubject());
		currentEmail.setTo(emailTemplate.getTo());
		currentEmail.setCc(emailTemplate.getCc());
		currentEmail.setMessage(emailTemplate.getMessage());
		currentEmail.setTemplateID(emailTemplate.getTemplateID());
		currentEmail.setDescription(emailTemplate.getDescription());
		emailTemplateService.updateApi(currentEmail);
		logger.info("createEmailTemplate details update " + id + "success");
		return new ResponseEntity<EmailTemplate>(currentEmail, HttpStatus.OK);

	}

	@RequestMapping(value = "/emailTemplateService/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EmailTemplate> deleteEmail(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting email templates with id " + id);

		EmailTemplate currentEmailTemp = emailTemplateService.getEmailTemplate(id);
		if (currentEmailTemp == null) {
			logger.error("Unable to delete. Email Template with id " + id + " not found");
			return new ResponseEntity<EmailTemplate>(HttpStatus.NOT_FOUND);
		}
		emailTemplateService.deleteApi(id);
		return new ResponseEntity<EmailTemplate>(HttpStatus.NO_CONTENT);
	}

	// -------------- get Email template Details base on  templete id--------

		@RequestMapping(value = "/emailTemplateService/{templateID}", method = RequestMethod.GET)
		public ResponseEntity<EmailTemplate> getEmailTemplate(@PathVariable("templateID") String templateID) {
			logger.info("EmailTemplate request received for getEmailTemplate");
			EmailTemplate currentEmailTemp = null;
			if(templateID != null) {
				currentEmailTemp = emailTemplateService.getByTemplateid(templateID);
			}
			if (currentEmailTemp == null) {
				//logger.info("there is no records found " + currentEmailTemp);
				return new ResponseEntity<EmailTemplate>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<EmailTemplate>(currentEmailTemp,HttpStatus.OK);
		}

}
