package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.MasterDemandRequestDao;
import com.ip.erss.competency.iamautomation.model.ApiRequest;
import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;

@Repository
public class MasterDemandRequestDaoImpl implements MasterDemandRequestDao {

	private final Logger logger = LoggerFactory.getLogger(MasterDemandRequestDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long createDemandRequest(MasterDemandRequest masterDemandRequest) {
		entityManager.persist(masterDemandRequest);
		entityManager.flush();
		return masterDemandRequest.getDemandRequestId();
	}

	@Override
	public List<MasterDemandRequest> loadAllDemandRequests() {
		List<MasterDemandRequest> masterDemandList = null;
		try {
			masterDemandList = entityManager.createQuery("select m from MasterDemandRequest m order by creationDate desc")
					.getResultList();
		} catch (Exception e) {
			logger.error("Exception  ::  " + e.getMessage());
		}
		return masterDemandList;
	}

	@Override
	public MasterDemandRequest loadMasterDemandRequestById(Long demandRequestId) {
		
		return entityManager.find(MasterDemandRequest.class, demandRequestId);
	}

	@Override
	public void updateDemandRequest(MasterDemandRequest masterDemandRequest) {
		entityManager.merge(masterDemandRequest);		
	}

	@Override
	public void deleteDemandRequest(Long demandRequestId) {
		MasterDemandRequest apiRequest = loadMasterDemandRequestById(demandRequestId);
        if (apiRequest != null) {
            entityManager.remove(apiRequest);
        }
	}

	@Override
	public List<MasterDemandRequest> loadAllDemandRequestsByUser(String userName) {
		List<MasterDemandRequest> masterDemandList = null;
		try {
			
			Query query = entityManager.createQuery("select m from MasterDemandRequest m where requestorID=? order by creationDate desc");
			query.setParameter(1, userName);
			
			masterDemandList = query.getResultList();
			
		} catch (Exception e) {
			logger.error("Exception  ::  " + e.getMessage());
		}
		return masterDemandList;
	}

}