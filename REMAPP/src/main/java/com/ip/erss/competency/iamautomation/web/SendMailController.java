package com.ip.erss.competency.iamautomation.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ip.erss.competency.iamautomation.service.MailNotificationService;
import com.ip.erss.competency.iamautomation.service.MailService;



@RestController
@EnableAutoConfiguration
public class SendMailController {
	private static final Logger logger = LoggerFactory.getLogger(SendMailController.class);
	@Autowired
	private MailService mail;
	@Autowired
	private MailNotificationService mailNotificationService;
	@RequestMapping(value = "/sendMail/{templateId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> sendMail(@PathVariable("templateId") String templateId,HttpServletRequest request) {

		Map<String, String> response = new HashMap<String, String>();
		if(request != null) {
			response = getHeadersInfo(request);
		}

		try {

			//mail.sendEmail(null);
			if(templateId != null) {
				mailNotificationService.sendEmail(templateId,response);
			}
			//response.put("status", "success");



		} catch (Exception e) {
			//logger.error("Error occurred while trying to process while sending", e);
			//logger.error("exception occured while creatingwhile sending" + e);
			//e.printStackTrace();
			//response.put("status", "fail");
		}

		return response;

}

	// get header list

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();
        if(request != null) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            //System.out.println("=====key===="+key);
            map.put(key, value);
        }
        }
        return map;
    }

}
