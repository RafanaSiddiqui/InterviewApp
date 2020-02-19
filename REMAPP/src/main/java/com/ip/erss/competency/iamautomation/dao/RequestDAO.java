/**
 *
 */
package com.ip.erss.competency.iamautomation.dao;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.model.BulkAppRequest;

/**
 * @author 134930
 *
 */
public interface RequestDAO {


	/**
	 * @param bulkAppRequest
	 */
	void createBulkRequest(BulkAppRequest bulkAppRequest);

	/**
	 * @param bulkAppRequest
	 */
	void updateBulkRequest(BulkAppRequest bulkAppRequest);

	/**
	 * @param appRequestID
	 */
	void deleteBulkRequest(Long appRequestID);

	/**
	 * @param appRequestID
	 * @return
	 */
	BulkAppRequest fetchBulkRequest(Long appRequestID);

	/**
	 * @return
	 */
	List<BulkAppRequest> fetchAllBulkRequest();

	/**
	 * @param status
	 * @return
	 */
	List<BulkAppRequest> fetchAllBulkRequestbyStatus(String status);


	

	List<BulkAppRequest> fetchAllBulkRequestbyIdStatus(String id, String status);

	
	void createBulkAssociate(List<AssociateDetails> associateDetailsList);
	
	
}
