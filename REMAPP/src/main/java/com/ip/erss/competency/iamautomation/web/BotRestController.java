package com.ip.erss.competency.iamautomation.web;


import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.service.BotService;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;

@RestController
@EnableAutoConfiguration
public class BotRestController {

	private final Logger logger = LoggerFactory.getLogger(BotRestController.class);
	
	@Autowired
	private BotService botService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_bot_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@RequestMapping(value="/loadAllBots/", method = RequestMethod.GET)
	public List<Bots> loadAllBots() {
		
		List<Bots> botList = botService.loadAllBots();
		
		return botList;
		
	}
	
	@RequestMapping(value="/indexAllBots/", method = RequestMethod.GET)
	public List<Bots> indexAllBots() throws JsonProcessingException {
		
		List<Bots> botList = botService.loadAllBots();
		
		for(Bots bot : botList) {
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(bot);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, bot.getBotId()); 
			logger.info("status  ::  " + status);
		 }
		}
		return botList;
		
	}
	
	@RequestMapping(value = "/botRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addBot(@RequestBody Bots bot) throws JsonProcessingException {
		logger.info("inside addBot bot  ::  "+bot);
		if(bot.getUseCaseOrPurpose() != null) {
			bot.setUseCaseOrPurpose(bot.getUseCaseOrPurpose().trim().replaceAll(" +", " "));
		}
		bot.setCreatedDate(new Date());
		botService.addBot(bot);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(bot);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, bot.getBotId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loadBotById/{botId}", method = RequestMethod.GET)
	public Bots loadBotById(@PathVariable("botId") long botId) {
		logger.info("inside loadBotById botId  ::  "+botId);
		Bots bot = botService.loadBotById(botId);
		return bot;
	}
	
	@RequestMapping(value = "/updateBot/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBot(@RequestBody Bots bot) throws JsonProcessingException {
		logger.info("inside updateBot bot  ::  "+bot);
		if(bot.getUseCaseOrPurpose() != null) {
			bot.setUseCaseOrPurpose(bot.getUseCaseOrPurpose().trim().replaceAll(" +", " "));
		}
		bot.setCreatedDate(new Date());
		botService.updateBot(bot);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(bot);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, bot.getBotId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteBot/{botId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBot(@PathVariable("botId") long botId) {
		logger.info("inside deleteBot botId  ::  "+botId);
		botService.deleteBot(botId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, botId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/checkBotName/{botName}", method = RequestMethod.GET)
	public boolean checkBotName(@PathVariable("botName") String botName) {
		List<Bots> botList = botService.loadBotByName(botName);
		logger.info("botList  ::  "+botList);
		if(null != botList && !botList.isEmpty()) {
			return true;
		}
		return false;
	}
}
