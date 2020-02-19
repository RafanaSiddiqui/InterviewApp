package com.ip.erss.competency.iamautomation.dao;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.EmailTemplate;

public interface EmailTemplateDao {

	 void create(EmailTemplate emailTemplate);
	 
	 List<EmailTemplate> loadAll();
	 void delete(long id);

	EmailTemplate getApiRequestById(long id);

	void update(EmailTemplate emailTemplate);
	EmailTemplate getTemplateByid(String templateId);  
		
	}
	 
	 

