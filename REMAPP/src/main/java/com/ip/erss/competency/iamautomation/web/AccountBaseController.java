package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountBaseController {

	private final Logger logger = LoggerFactory.getLogger(AccountBaseController.class);
	
	@RequestMapping(value = "/showAllAccountBase", method = RequestMethod.GET)
	public String showAllAccountBases(Model model) {
		logger.info("invoking showAllAccountBases");
		return "accountBase/showAllAccountBase";
	}
	
	@RequestMapping(value = "/addAccountBase", method = RequestMethod.GET)
	public String addBot(Model model) {
		logger.info("invoking addAccountBase");
		return "accountBase/createAccountBase";
	}
	
	@RequestMapping(value = "/editAccountBase/{accountBaseId}", method = RequestMethod.GET)
	public String editBot(@PathVariable("accountBaseId") Integer accountBaseId, Model model) {
		  logger.info("editAccountBase()");	
		  model.addAttribute("accountBaseId", accountBaseId);
		  return "accountBase/editAccountBase";
	}
	
}
