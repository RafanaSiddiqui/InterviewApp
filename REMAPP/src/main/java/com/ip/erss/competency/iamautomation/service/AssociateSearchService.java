package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.AssociateDetails;

public interface AssociateSearchService {

	List<AssociateDetails> loadAssociates();
	
	List<AssociateDetails> searchAssociateDetails(AssociateDetails associateDetails);
	
}
