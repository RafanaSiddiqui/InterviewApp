package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.IAMFunctionalSpread;

public interface IAMFunctionalSpreadService {

	List<IAMFunctionalSpread> loadAllIAMFunctionalSpreads();

	void addIAMFunctionalSpread(IAMFunctionalSpread iamFunctionalSpread);

	IAMFunctionalSpread loadIAMFunctionalSpreadById(long iamFunctionalSpreadId);

	void updateIAMFunctionalSpread(IAMFunctionalSpread iamFunctionalSpread);

	void deleteIAMFunctionalSpread(long iamFunctionalSpreadId);
	
}
