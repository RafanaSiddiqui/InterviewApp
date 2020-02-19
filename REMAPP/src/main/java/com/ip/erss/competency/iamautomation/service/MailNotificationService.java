package com.ip.erss.competency.iamautomation.service;

import java.util.Map;

/**
 * @author 134930
 *
 */
public interface MailNotificationService {
	
	/**
	 * @param templateId String
	 * @param response Map<String, String>
	 */
	public void sendEmail(String templateId, Map<String, String> response);

}
