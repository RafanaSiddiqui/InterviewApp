package com.ip.erss.competency.iamautomation.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ip.erss.competency.iamautomation.dao.ElasticSearchOperationDao;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.web.AccountBaseRestController;

@Repository
public class ElasticSearchOperationDaoImpl implements ElasticSearchOperationDao {
	
	private final Logger logger = LoggerFactory.getLogger(ElasticSearchOperationDaoImpl.class);
	
	/*@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;*/
	
	@Override
	public String loadRequestToElastic(ElasticDetails elasticDetails, String masterDemandRequestJson, Long masterDemandRequestId) {
		ResponseEntity<String> masterDemandRequestJsonList = null;
		//try {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("accept", MediaType.ALL_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String absoluteUrl = elasticDetails.getElasticUrl() + "/" + elasticDetails.getElasticIndex() + "/" + elasticDetails.getElasticType() + "/" +masterDemandRequestId;
		
		HttpEntity<String> entity = new HttpEntity<String>(masterDemandRequestJson, headers);
		
		logger.info("masterDemandRequestJson  ::  "+masterDemandRequestJson);
		
		masterDemandRequestJsonList = restTemplate.exchange(absoluteUrl, HttpMethod.POST, entity, String.class);
		/*} catch (Exception e) {
			logger.info("Exception  ::  "+e.getMessage());
		}*/
		
		return masterDemandRequestJsonList.getStatusCode().toString();
	}

	@Override
	public String deleteElasticEntry(ElasticDetails elasticDetails, Long demandRequestId) {
		ResponseEntity<String> masterDemandRequestJsonList = null;
		try {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("accept", MediaType.ALL_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String absoluteUrl = elasticDetails.getElasticUrl() + "/" + elasticDetails.getElasticIndex() + "/" + elasticDetails.getElasticType() + "/" +demandRequestId;
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		//logger.info("masterDemandRequestJson  ::  "+masterDemandRequestJson);
		
		masterDemandRequestJsonList = restTemplate.exchange(absoluteUrl, HttpMethod.DELETE, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return masterDemandRequestJsonList.getStatusCode().toString();
	}

}
