package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.EmailTemplateDao;
import com.ip.erss.competency.iamautomation.model.EmailTemplate;

@Repository
public class EmailTemplateDaoImpl implements EmailTemplateDao {
	
	private final Logger logger = LoggerFactory.getLogger(EmailTemplateDaoImpl.class);
	
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public void create(EmailTemplate emailTemplate) {
		// TODO Auto-generated method stub
		 entityManager.persist(emailTemplate);
		
	}

	@Override
	public List<EmailTemplate> loadAll() {
		List<EmailTemplate> listEmailTemplates = null;
		try {
			listEmailTemplates = entityManager.createQuery("SELECT p FROM EmailTemplate p").getResultList();
		} catch(Exception e) {
			logger.error("loadAll Exception  ::  "+e.getMessage());
		}
		return listEmailTemplates;
	}

	@Override
	public void delete(long id) {
		EmailTemplate EmailTemplate = getApiRequestById(id);
	        if (EmailTemplate != null) {
	            entityManager.remove(EmailTemplate);
	        }
		
	}

	@Override
	public EmailTemplate getApiRequestById(long id) {
		
		 return entityManager.find(EmailTemplate.class, id);
	}

	@Override
	public void update(EmailTemplate emailTemplate) {
		 entityManager.merge(emailTemplate);
	}

	@Override
	public EmailTemplate getTemplateByid(String templateId) {
		EmailTemplate template = null;
		try {
			Query query = entityManager.createQuery(
			      "SELECT p FROM EmailTemplate p where p.templateID =?");
			query.setParameter(1, templateId);
			template = (EmailTemplate) query.getSingleResult();
		} catch(Exception e) {
			logger.error("getTemplateByid  Exception  ::  "+e.getMessage());
		}
		return template;
	}

   

}
