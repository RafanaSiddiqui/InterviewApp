package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.model.BulkAppRequest;

public interface BulkRequestService {

	/**
	 * @param appRequest
	 */
	void createBulkRequest(BulkAppRequest bulkAppRequest);

	/**
	 * @param appRequest
	 */
	void updateBulkRequest(BulkAppRequest bulkAppRequest);

	/**
	 * @param appRequestID
	 */
	void deleteBulkRequest(Long bulkRequestID);


	/**
	 * @param appRequestID
	 */
	BulkAppRequest fetchBulkRequest(Long bulkRequestID);


	/**
	 * @param appRequestID
	 */
	List<BulkAppRequest> fetchAllBulkRequest();


	/**
	 * @param appRequestID
	 */
	List<BulkAppRequest> fetchAllBulkRequestbyStatus(String status);

	List<BulkAppRequest> fetchAllBulkRequestbyIdStatus(String id, String status);
	
	void createBulkAssociate(List<AssociateDetails> associateDetailsList);


}
