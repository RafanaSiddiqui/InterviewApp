package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.ApiRequestDao;
import com.ip.erss.competency.iamautomation.model.ApiRequest;

@Repository
public class ApiRequestDaoImpl implements ApiRequestDao {
	
	private final Logger logger = LoggerFactory.getLogger(ApiRequestDaoImpl.class);
	
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(ApiRequest apiRequest) {
        entityManager.persist(apiRequest);
    }

    @Override
    public void update(ApiRequest apiRequest) {
        entityManager.merge(apiRequest);
    }

    @Override
    public ApiRequest getApiRequestById(long id) {
        return entityManager.find(ApiRequest.class, id);

    }

    @Override
    public void delete(long id) {
        ApiRequest apiRequest = getApiRequestById(id);
        if (apiRequest != null) {
            entityManager.remove(apiRequest);
        }
    }

    @Override
    public List<ApiRequest> loadAll() {
    	List<ApiRequest> listApiRequest = null;
    	try {
    		listApiRequest = entityManager.createQuery("SELECT p FROM ApiRequest p").getResultList();
        } catch(Exception e) {
        	logger.error("loadAll Exception  ::  "+e.getMessage());
    	}
    	return listApiRequest;
    }

    public void deleteAllApi(ApiRequest apiRequest) {

    	entityManager.remove(apiRequest);

    }

	@Override
	public ApiRequest getApiRequestByName(String name) {
		ApiRequest apiRequest = null;
		try {
		Query query = entityManager.createQuery(
			      "SELECT p FROM ApiRequest p where p.apiName =?");
		query.setParameter(1, name);
		apiRequest = (ApiRequest) query.getSingleResult();
		 
		}catch(Exception e) {
			logger.error("getApiRequestByName Exception  ::  "+e.getMessage());
		}
		return apiRequest;
	}



}
