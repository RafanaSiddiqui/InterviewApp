package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;
import com.ip.erss.competency.iamautomation.model.Roles;
import com.ip.erss.competency.iamautomation.model.UserRoles;

public interface MasterDemandRequestService {

	Long createDemandRequest(MasterDemandRequest masterDemandRequest);

	List<MasterDemandRequest> loadAllDemandRequests();

	MasterDemandRequest loadMasterDemandRequestById(Long demandRequestId);

	void updateDemandRequest(MasterDemandRequest masterDemandRequest);

	void deleteDemandRequest(Long demandRequestId);

	Page<MasterDemandRequest> loadPageableMasterDemandRequest(PageRequest pageRequest);

	Page<MasterDemandRequest> loadPageableDemandReqBySoId(String soId, Pageable pageable);

	List<Roles> fetchAllRoles();

	List<MasterDemandRequest> loadAllDemandRequestsByUser(String userName);

	List<MasterDemandRequest> loadAllDemandRequestsByFlag();

	List<UserRoles> fetchAllUserRoles();

}
