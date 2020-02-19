package com.ip.erss.competency.iamautomation.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.service.AssociateSearchService;

@RestController
@EnableAutoConfiguration
public class AssociateSearchController {

	private final Logger logger = LoggerFactory.getLogger(AssociateSearchController.class);

	@Autowired
	private AssociateSearchService associateSearchService;

	@RequestMapping(value = "/loadAssociates/", method = RequestMethod.GET)
	public List<AssociateDetails> loadAllMasterDemandRequests() {
		List<AssociateDetails> associatesList = associateSearchService.loadAssociates();
		return associatesList;
	}
	
	@RequestMapping(value = "/searchAssociates/", method = RequestMethod.POST)
	public List<AssociateDetails> searchAssociateDetails(@RequestBody AssociateDetails associateDetails) {
		System.out.println("associateDetails  ::  "+associateDetails);
		List<AssociateDetails> associatesList = associateSearchService.searchAssociateDetails(associateDetails);
		return associatesList;
	}

}
