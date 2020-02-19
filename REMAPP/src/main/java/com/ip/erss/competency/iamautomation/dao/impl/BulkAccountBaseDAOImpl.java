package com.ip.erss.competency.iamautomation.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.BulkAccountBaseDAO;
import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.BulkAccountBase;

@Repository
public class BulkAccountBaseDAOImpl implements BulkAccountBaseDAO{
	
	private final Logger logger = LoggerFactory.getLogger(BulkAccountBaseDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public void addOrUpdateBulkAccountBase(BulkAccountBase bulkAccountBase) {
		if(null!=bulkAccountBase) {
			if(null!=bulkAccountBase.getBulkID()) {
				entityManager.merge(bulkAccountBase);
			}else {
				entityManager.persist(bulkAccountBase);
			}
		}
	}

	@Override
	public AccountBase getAccountBaseByCustomerID(String customerId) {
		AccountBase accountBase = null;
		try {
		Query query = entityManager.createQuery(
			      "SELECT p FROM AccountBase p where p.customerId =?");
		query.setParameter(1, customerId);
		accountBase = (AccountBase) query.getSingleResult();
		 
		}catch(Exception e) {
			logger.error("getAccountBaseByCustomerID Exception  ::  "+e.getMessage());
		}
		
		return accountBase;
	}

	@Override
	public void addOrUpdateAccountBase(AccountBase accountBase) {
	//	entityManager.merge(accountBase);
		if(null!=accountBase) {
			if(null!=accountBase.getAccountBaseId()) {
				entityManager.merge(accountBase);
			}else {
				entityManager.persist(accountBase);
			}
		}
	}

}
