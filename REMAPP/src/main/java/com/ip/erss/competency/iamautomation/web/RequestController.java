package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestController {

	private final Logger logger = LoggerFactory.getLogger(RequestController.class);


	// list page
	@RequestMapping(value = "/listAllRequests", method = RequestMethod.GET)
	public String showTestData(Model model) {

		/*
		 * logger.debug("showTestData()"); model.addAttribute("testDataList",
		 * testDataService.fetchTestData("mocktestcase"));
		 */
		return "request/ShowALLRequest";

	}

	@RequestMapping(value = "/createNewRequest", method = RequestMethod.GET)
	public String createNewRequest(Model model) {

		logger.debug("invocking createNewRequest -->");

		/*
		 * logger.debug("showTestData()"); model.addAttribute("testDataList",
		 * testDataService.fetchTestData("mocktestcase"));
		 */
		return "request/CreateNewRequest";

	}

	@RequestMapping(value = "/editRequest/{requestID}", method = RequestMethod.GET)
	public String editRequest(@PathVariable("requestID") Integer requestID, Model model) {
		logger.debug("invocking Edit Request -->");

		  logger.debug("showTestData()");

		  model.addAttribute("requestID",requestID);


		return "request/EditRequest";

	}

	@RequestMapping(value = "/editTemplate/{requestID}", method = RequestMethod.GET)
	public String editTemplate(@PathVariable("requestID") Integer requestID, Model model) {
		logger.debug("invocking Edit Template -->");
		model.addAttribute("requestID",requestID);
		return "template/editTemplate";

	}


	@RequestMapping(value = "/createBulkRequest", method = RequestMethod.GET)
	public String createBulkRequest(Model model) {

		logger.debug("invocking createBulkRequeste -->");
		return "request/CreateBulkRequest";

	}

	@RequestMapping(value = "/editBulkRequest/{bulkRequestID}", method = RequestMethod.GET)
	public String editBulkRequest(@PathVariable("bulkRequestID") Integer bulkRequestID, Model model) {
		logger.debug("invocking Edit Request -->");

		  logger.debug("editBulkRequest()");	

		  model.addAttribute("bulkRequestID",bulkRequestID);


		return "request/EditBulkRequest";

	}
	
	@RequestMapping(value = "/masterDemand", method = RequestMethod.GET)
	public String showAllMasterDemand(Model model) {

		return "request/ShowMasterDemand";

	}
	
	@RequestMapping(value = "/createNewMasterDemand", method = RequestMethod.GET)
	public String createNewMasterDemand(Model model) {
		
		logger.debug("invocking createNewRequest -->");
		return "request/CreateNewMasterDemand";

	}
	
	@RequestMapping(value = "/editDemandRequest/{demandRequestID}", method = RequestMethod.GET)
	public String editDemandRequest(@PathVariable("demandRequestID") Integer demandRequestID, Model model) {
		logger.debug("invocking Edit Master Demand Request demandRequestID  ::  "+demandRequestID);


		  model.addAttribute("demandRequestID", demandRequestID);

		return "request/EditMasterDemand";

	}
	
	@RequestMapping(value = "/addResource", method = RequestMethod.GET)
	public String addResourceForm(Model model) {

		return "request/addResource";

	}
	
	@RequestMapping(value = "/associateSearch", method = RequestMethod.GET)
	public String searchAssociates(Model model) {

		return "request/associateSearch";

	}
	
}
