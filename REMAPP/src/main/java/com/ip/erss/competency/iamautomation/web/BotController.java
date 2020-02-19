package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BotController {

	private final Logger logger = LoggerFactory.getLogger(BotController.class);
	
	@RequestMapping(value = "/showAllBots", method = RequestMethod.GET)
	public String showAllBots(Model model) {
		logger.info("invoking showAllBots");
		return "bots/showAllBots";
	}
	
	@RequestMapping(value = "/showAllMeasurements", method = RequestMethod.GET)
	public String showAllMeasurments(Model model) {
		logger.info("invoking showAllMeasurements");
		return "bots/showAllMeasurements";
	}
	
	@RequestMapping(value = "/addBot", method = RequestMethod.GET)
	public String addBot(Model model) {
		logger.info("invoking addBot");
		return "bots/createBot";
	}
	
	@RequestMapping(value = "/createBotMeasurement", method = RequestMethod.GET)
	public String addBotMeasurement(Model model) {
		logger.info("invoking addMeasurement");
		return "bots/createBotMeasurement";
	}
	
	@RequestMapping(value = "/editBotMeasurement/{botMeasurementId}", method = RequestMethod.GET)
	public String editBotMeasurement(@PathVariable("botMeasurementId") Integer botMeasurementId, Model model) {
		logger.info("invocking Edit editBotMeasurement -->");

		  logger.info("editBotMeasurement()");	

		  model.addAttribute("botMeasurementId", botMeasurementId);


		return "bots/editBotMeasurement";

	}
	
	@RequestMapping(value = "/editBot/{botId}", method = RequestMethod.GET)
	public String editBot(@PathVariable("botId") Integer botId, Model model) {
		  logger.info("editBot()");	
		  model.addAttribute("botId", botId);
		  return "bots/editBot";
	}
	
	@RequestMapping(value = "/botDashboards", method = RequestMethod.GET)
	public String showBotDashboards(Model model) {

		logger.debug("Invoke showBotDashboards");
		return "bots/botDashboards";

	}
	
	@RequestMapping(value = "/defaulterList", method = RequestMethod.GET)
	public String showDefaulterList(Model model) {

		logger.debug("Invoke showDefaulterList");
		return "bots/defaulterList";

	}
	
	@RequestMapping(value = "/showAllNoBots", method = RequestMethod.GET)
	public String showAllNoBots(Model model) {
		logger.info("invoking showAllNoBots");
		return "bots/showAllNoBots";
	}
	
	@RequestMapping(value = "/addNoBotDec", method = RequestMethod.GET)
	public String createNoBotDecForm(Model model) {
		logger.info("invoking createNoBotDecForm");
		return "bots/createNoBotDecForm";
	}
	
	@RequestMapping(value = "/editNoBot/{noBotId}", method = RequestMethod.GET)
	public String editNoBot(@PathVariable("noBotId") Integer noBotId, Model model) {
		  logger.info("editNoBot()");	
		  model.addAttribute("noBotId", noBotId);
		  return "bots/editNoBotDecForm";
	}
	
}
