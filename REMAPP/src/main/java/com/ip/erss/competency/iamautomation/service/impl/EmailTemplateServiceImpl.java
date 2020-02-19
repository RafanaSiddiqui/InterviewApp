package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.EmailTemplateDao;
import com.ip.erss.competency.iamautomation.model.EmailTemplate;
import com.ip.erss.competency.iamautomation.service.EmailTemplateService;

@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);
	
	@Autowired
    private EmailTemplateDao emailTemplateDao;
	@Override
	public void create(EmailTemplate emailTemplate) {
		
		emailTemplateDao.create(emailTemplate);
	}

	@Override
	public List<EmailTemplate> loadAll() {
		
		return emailTemplateDao.loadAll();
	}

	@Override
	public EmailTemplate getEmailTemplate(long id) {
		return emailTemplateDao.getApiRequestById(id);
	}

	@Override
	public void updateApi(EmailTemplate emailTemplate) {
		
		logger.info("Inside updateApi method:::::::");
		emailTemplateDao.update(emailTemplate);
	}

	@Override
	public void deleteApi(long id) {
		emailTemplateDao.delete(id);
		
	}

	@Override
	public EmailTemplate getByTemplateid(String templateId) {
		// TODO Auto-generated method stub
		return emailTemplateDao.getTemplateByid(templateId);
	}

}
