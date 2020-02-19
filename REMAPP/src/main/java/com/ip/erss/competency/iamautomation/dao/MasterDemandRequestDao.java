package com.ip.erss.competency.iamautomation.dao;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;

public interface MasterDemandRequestDao {

	Long createDemandRequest(MasterDemandRequest masterDemandRequest);

	List<MasterDemandRequest> loadAllDemandRequests();

	MasterDemandRequest loadMasterDemandRequestById(Long demandRequestId);

	void updateDemandRequest(MasterDemandRequest masterDemandRequest);

	void deleteDemandRequest(Long demandRequestId);

	List<MasterDemandRequest> loadAllDemandRequestsByUser(String userName);
	
}
