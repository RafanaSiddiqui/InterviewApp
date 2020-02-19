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
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.model.IAMFunctionalSpread;
import com.ip.erss.competency.iamautomation.model.Services;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;
import com.ip.erss.competency.iamautomation.service.IAMFunctionalSpreadService;

@RestController
@EnableAutoConfiguration
public class IAMFunctionalSpreadRestController {

	private final Logger logger = LoggerFactory.getLogger(IAMFunctionalSpreadRestController.class);
	
	@Autowired
	private IAMFunctionalSpreadService iamFunctionalSpreadService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_iamFunctionalSpread_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@RequestMapping(value="/loadAllIAMFunctionalSpreads/", method = RequestMethod.GET)
	public List<IAMFunctionalSpread> loadAllIAMFunctionalSpreads() {
		
		List<IAMFunctionalSpread> iamFunctionalSpreadList = iamFunctionalSpreadService.loadAllIAMFunctionalSpreads();
		
		return iamFunctionalSpreadList;
		
	}
	
	@RequestMapping(value = "/iamFunctionalSpreadRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addIAMFunctionalSpread(@RequestBody IAMFunctionalSpread iamFunctionalSpread) throws JsonProcessingException {
		logger.info("inside addIAMFunctionalSpread iamFunctionalSpread  ::  "+iamFunctionalSpread);
		iamFunctionalSpread.setCreatedDate(new Date());
		for(Services services : iamFunctionalSpread.getServicesDetails()) {
			services.setCreatedDate(new Date());
		}
		iamFunctionalSpreadService.addIAMFunctionalSpread(iamFunctionalSpread);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String iamFunctionalSpreadJson = mapper.writeValueAsString(iamFunctionalSpread);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, iamFunctionalSpreadJson, iamFunctionalSpread.getId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loadIAMFunctionalSpreadById/{iamFunctionalSpreadId}", method = RequestMethod.GET)
	public IAMFunctionalSpread loadIAMFunctionalSpreadById(@PathVariable("iamFunctionalSpreadId") long iamFunctionalSpreadId) {
		logger.info("inside loadIAMFunctionalSpreadById iamFunctionalSpreadId  ::  "+iamFunctionalSpreadId);
		IAMFunctionalSpread iamFunctionalSpread = iamFunctionalSpreadService.loadIAMFunctionalSpreadById(iamFunctionalSpreadId);
		return iamFunctionalSpread;
	}
	
	@RequestMapping(value = "/updateIAMFunctionalSpread/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateIAMFunctionalSpread(@RequestBody IAMFunctionalSpread iamFunctionalSpread) throws JsonProcessingException {
		logger.info("inside updateIAMFunctionalSpread iamFunctionalSpread  ::  "+iamFunctionalSpread);
		iamFunctionalSpread.setCreatedDate(new Date());
		for(Services services : iamFunctionalSpread.getServicesDetails()) {
			services.setCreatedDate(new Date());
		}
		iamFunctionalSpreadService.updateIAMFunctionalSpread(iamFunctionalSpread);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String iamFunctionalSpreadJson = mapper.writeValueAsString(iamFunctionalSpread);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, iamFunctionalSpreadJson, iamFunctionalSpread.getId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteIAMFunctionalSpread/{iamFunctionalSpreadId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteIAMFunctionalSpread(@PathVariable("iamFunctionalSpreadId") long iamFunctionalSpreadId) {
		logger.info("inside deleteIAMFunctionalSpread iamFunctionalSpreadId  ::  "+iamFunctionalSpreadId);
		iamFunctionalSpreadService.deleteIAMFunctionalSpread(iamFunctionalSpreadId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, iamFunctionalSpreadId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/indexAllFunctionalSpread/", method = RequestMethod.GET)
	public List<IAMFunctionalSpread> indexAllFunctionalSpreads() throws JsonProcessingException {
		
		List<IAMFunctionalSpread> functionalSpreadList = iamFunctionalSpreadService.loadAllIAMFunctionalSpreads();
		for(IAMFunctionalSpread functionalSpread : functionalSpreadList) {
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(functionalSpread);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, functionalSpread.getId()); 
			logger.info("status  ::  " + status);
		 }
		}
		return functionalSpreadList;
		
	}
}
