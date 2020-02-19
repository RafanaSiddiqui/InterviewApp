/**
 *
 */
package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.RequestDAO;
import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.model.BulkAppRequest;

/**
 * @author 134930
 *
 */

@Repository
public class RequestDAOImpl implements RequestDAO {

	private final Logger logger = LoggerFactory.getLogger(RequestDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ip.erss.competency.iamautomation.dao.RequestDAO#createRequest(com.
	 * ip.erss.competency.iamautomation.model.AppRequest)
	 */

	@Override
	public void createBulkRequest(BulkAppRequest bulkAppRequest) {
		entityManager.persist(bulkAppRequest);
	}

	@Override
	public void updateBulkRequest(BulkAppRequest bulkAppRequest) {
		entityManager.merge(bulkAppRequest);
	}

	@Override
	public void deleteBulkRequest(Long appRequestID) {

		BulkAppRequest bulkAppRequest = this.fetchBulkRequest(appRequestID);
		if (bulkAppRequest != null) {
			entityManager.remove(bulkAppRequest);
		}
	}

	@Override
	public BulkAppRequest fetchBulkRequest(Long appRequestID) {

		return entityManager.find(BulkAppRequest.class, appRequestID);
	}

	@Override
	public List<BulkAppRequest> fetchAllBulkRequest() {
		List<BulkAppRequest> bulklistAppRequest = null;
		try {
			bulklistAppRequest = entityManager.createQuery("SELECT p FROM BulkAppRequest p").getResultList();
		} catch(Exception e) {
			logger.error("fetchAllBulkRequest Exception  ::  "+e.getMessage());
		}
		return bulklistAppRequest;
	}

	@Override
	public List<BulkAppRequest> fetchAllBulkRequestbyStatus(String status) {
		List<BulkAppRequest> bulklistAppRequest = null;
		try {
			Query query = entityManager.createQuery("SELECT p FROM BulkAppRequest p where p.status =?");
			query.setParameter(1, status);
			bulklistAppRequest = query.getResultList();
		} catch(Exception e) {
			logger.error("fetchAllBulkRequestbyStatus");
		}
		return bulklistAppRequest;
	}

	@Override
	public List<BulkAppRequest> fetchAllBulkRequestbyIdStatus(String id, String status) {
		List<BulkAppRequest> bulklistAppRequest = null;
		try {
			Query query = entityManager.createQuery("SELECT p FROM BulkAppRequest p where p.bulkRequestID = ? and p.status = ?");
			query.setParameter(1, id);
			query.setParameter(2, status);
			bulklistAppRequest = query.getResultList();
		} catch(Exception e) {
			logger.error("fetchAllBulkRequestbyStatus");
		}
		return bulklistAppRequest;
	}

	@Override
	public void createBulkAssociate(List<AssociateDetails> associateDetailsList) {
		for(AssociateDetails associateDetails : associateDetailsList) {
			entityManager.persist(associateDetails);
		}
		
	}

}
