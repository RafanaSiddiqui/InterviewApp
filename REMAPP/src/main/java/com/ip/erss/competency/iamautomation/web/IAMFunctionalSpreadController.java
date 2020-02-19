package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IAMFunctionalSpreadController {

	private final Logger logger = LoggerFactory.getLogger(IAMFunctionalSpreadController.class);
	
	@RequestMapping(value = "/showAllIAMFunctionalSpread", method = RequestMethod.GET)
	public String showAllIAMFunctionalSpreads(Model model) {
		logger.info("invoking showAllIAMFunctionalSpreads");
		return "iamFunctionalSpread/showAllIAMFunctionalSpread";
	}
	
	@RequestMapping(value = "/addIAMFunctionalSpread", method = RequestMethod.GET)
	public String addBot(Model model) {
		logger.info("invoking addIAMFunctionalSpread");
		return "iamFunctionalSpread/createIAMFunctionalSpread";
	}
	
	@RequestMapping(value = "/editIAMFunctionalSpread/{id}", method = RequestMethod.GET)
	public String editBot(@PathVariable("id") Integer id, Model model) {
		  logger.info("editIAMFunctionalSpread()");	
		  model.addAttribute("id", id);
		  return "iamFunctionalSpread/editIAMFunctionalSpread";
	}
	
}
