package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.EmailTemplate;

public interface EmailTemplateService {
	
	void create(EmailTemplate emailTemplate);
    List<EmailTemplate> loadAll();
    EmailTemplate getEmailTemplate(long id);
    void updateApi(EmailTemplate emailTemplate);
    void deleteApi(long id);
    EmailTemplate getByTemplateid(String templateId);
}
