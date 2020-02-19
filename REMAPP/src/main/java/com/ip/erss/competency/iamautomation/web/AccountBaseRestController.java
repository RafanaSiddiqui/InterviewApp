package com.ip.erss.competency.iamautomation.web;


import java.util.Date;
import java.util.List;

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
import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.service.AccountBaseService;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;

@RestController
@EnableAutoConfiguration
public class AccountBaseRestController {

	private final Logger logger = LoggerFactory.getLogger(AccountBaseRestController.class);
	
	@Autowired
	private AccountBaseService accountBaseService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_accountBase_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@RequestMapping(value="/loadAllAccountBases/", method = RequestMethod.GET)
	public List<AccountBase> loadAllAccountBases() {
		
		List<AccountBase> accountBaseList = accountBaseService.loadAllAccountBases();
		
		return accountBaseList;
		
	}
	
	@RequestMapping(value="/indexAllAccountBases/", method = RequestMethod.GET)
	public List<AccountBase> indexAllAccountBases() throws JsonProcessingException {
		
		List<AccountBase> accountBaseList = accountBaseService.loadAllAccountBases();
		
		for(AccountBase accountBase : accountBaseList) {
			if(isElasticSearchEnable) {
				ObjectMapper mapper = new ObjectMapper();
				String accountBaseJson = mapper.writeValueAsString(accountBase);
			 	ElasticDetails elasticDetails = new ElasticDetails();
				elasticDetails.setElasticUrl(elasticSearchUrl);
				elasticDetails.setElasticIndex(elasticSearchIndex);
				elasticDetails.setElasticType(elasticSearchType);
				String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, accountBaseJson, accountBase.getAccountBaseId()); 
				logger.info("status  ::  " + status);
			 }
		}
		
		return accountBaseList;
		
	}
	
	@RequestMapping(value = "/accountBaseRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addAccountBase(@RequestBody AccountBase accountBase) throws JsonProcessingException {
		logger.info("inside addAccountBase accountBase  ::  "+accountBase);
		accountBase.setCreatedDate(new Date());
		accountBaseService.addAccountBase(accountBase);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String accountBaseJson = mapper.writeValueAsString(accountBase);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, accountBaseJson, accountBase.getAccountBaseId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loadAccountBaseById/{accountBaseId}", method = RequestMethod.GET)
	public AccountBase loadAccountBaseById(@PathVariable("accountBaseId") long accountBaseId) {
		logger.info("inside loadAccountBaseById accountBaseId  ::  "+accountBaseId);
		AccountBase accountBase = accountBaseService.loadAccountBaseById(accountBaseId);
		return accountBase;
	}
	
	@RequestMapping(value = "/updateAccountBase/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAccountBase(@RequestBody AccountBase accountBase) throws JsonProcessingException {
		logger.info("inside updateAcountBase accountBase  ::  "+accountBase);
		accountBase.setCreatedDate(new Date());
		accountBaseService.updateAccountBase(accountBase);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String accountBaseJson = mapper.writeValueAsString(accountBase);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, accountBaseJson, accountBase.getAccountBaseId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteAccountBase/{accountBaseId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAccountBase(@PathVariable("accountBaseId") long accountBaseId) {
		logger.info("inside deleteAccountBase accountBaseId  ::  "+accountBaseId);
		accountBaseService.deleteAccountBase(accountBaseId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, accountBaseId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
