package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BulkAccountBaseController {

	private final Logger logger = LoggerFactory.getLogger(BulkAccountBaseController.class);
			
	@RequestMapping(value = "/showAllBulkAccountBase", method = RequestMethod.GET)
	public String showAllAccountBases(Model model) {
		logger.info("invoking showAllBulkAccountBases");
		return "bulkAccountBase/showAllBulkAccountBase";
	}
	
	@RequestMapping(value = "/addBulkAccountBase", method = RequestMethod.GET)
	public String addBot(Model model) {
		logger.info("invoking addAccountBase");
		return "bulkAccountBase/createBulkAccountBase";
	}
	
	@RequestMapping(value = "/editBulkAccountBase/{bulkID}", method = RequestMethod.GET)
	public String editBulkAccountBase(@PathVariable("bulkID") Integer bulkID, Model model) {
		  logger.info("editBulkAccountBase()");	
		  model.addAttribute("bulkID", bulkID);
		  return "bulkAccountBase/editBulkAccountBase";
	}
}
