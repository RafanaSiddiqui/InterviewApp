package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SkillAttestationController {

	private final Logger logger = LoggerFactory.getLogger(SkillAttestationController.class);
	
	@RequestMapping(value = "/showAllSkillAttestation", method = RequestMethod.GET)
	public String showAllSkillAttestation(Model model) {
		logger.info("invoking showAllSkillAttestation");
		return "skillAttestation/showAllSkillAttestation";
	}
	
	@RequestMapping(value = "/addSkillAttestation", method = RequestMethod.GET)
	public String addBot(Model model) {
		logger.info("invoking addSkillAttestation");
		return "skillAttestation/createSkillAttestation";
	}
	
	@RequestMapping(value = "/editSkillAttestation/{skillAttestationId}/{associateId}", method = RequestMethod.GET)
	public String editBot(@PathVariable("skillAttestationId") Integer skillAttestationId,@PathVariable("associateId") Integer associateId, Model model) {
		  logger.info("editSkillAttestation()");	
		  model.addAttribute("skillAttestationId", skillAttestationId);
		  model.addAttribute("associateId", associateId);
		  return "skillAttestation/editSkillAttestation";
	}
	
}
