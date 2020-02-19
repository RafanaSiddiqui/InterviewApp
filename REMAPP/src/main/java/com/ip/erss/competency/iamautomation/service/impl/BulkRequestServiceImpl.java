package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.RequestDAO;
import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.model.BulkAppRequest;
import com.ip.erss.competency.iamautomation.service.BulkRequestService;
@Service
@Transactional
public class BulkRequestServiceImpl implements BulkRequestService {

	private static final Logger logger = LoggerFactory.getLogger(BulkRequestServiceImpl.class);
	
	@Autowired
	private RequestDAO requestDAO;

	@Override
	public void createBulkRequest(BulkAppRequest bulkAppRequest) {
		requestDAO.createBulkRequest(bulkAppRequest);
	}

	@Override
	public void updateBulkRequest(BulkAppRequest bulkAppRequest) {
		requestDAO.updateBulkRequest(bulkAppRequest);

	}

	@Override
	public void deleteBulkRequest(Long appRequestID) {
		requestDAO.deleteBulkRequest(appRequestID);

	}

	@Override
	public BulkAppRequest fetchBulkRequest(Long appRequestID) {
		BulkAppRequest bulkAppRequest = requestDAO.fetchBulkRequest(appRequestID);
		return bulkAppRequest;
	}

	@Override
	public List<BulkAppRequest> fetchAllBulkRequest() {
		return requestDAO.fetchAllBulkRequest();
	}

	@Override
	public List<BulkAppRequest> fetchAllBulkRequestbyStatus(String status) {
		return requestDAO.fetchAllBulkRequestbyStatus(status);
	}

	@Override
	public List<BulkAppRequest> fetchAllBulkRequestbyIdStatus(String id, String status) {
		// TODO Auto-generated method stub
		return requestDAO.fetchAllBulkRequestbyIdStatus(id, status);
	}

	@Override
	public void createBulkAssociate(List<AssociateDetails> associateDetailsList) {
		
		 requestDAO.createBulkAssociate(associateDetailsList);
	}
	
	



}
