package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TrackerController {

	private final Logger logger = LoggerFactory.getLogger(TrackerController.class);
	
	@RequestMapping(value = "/showAllTracker", method = RequestMethod.GET)
	public String showAllTracker(Model model) {
		logger.info("invoking showAllTracker");
		return "tracker/showAllTracker";
	}
	
	@RequestMapping(value = "/addTracker", method = RequestMethod.GET)
	public String addBot(Model model) {
		logger.info("invoking addTracker");
		return "tracker/createTracker";
	}
	
	@RequestMapping(value = "/editTracker/{trackerId}", method = RequestMethod.GET)
	public String editBot(@PathVariable("trackerId") Integer trackerId, Model model) {
		  logger.info("editTracker()");	
		  model.addAttribute("trackerId", trackerId);
		  return "tracker/editTracker";
	}
	
}
