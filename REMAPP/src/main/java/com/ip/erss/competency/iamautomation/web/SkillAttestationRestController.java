package com.ip.erss.competency.iamautomation.web;


import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.model.SkillAttestation;
import com.ip.erss.competency.iamautomation.model.SkillDetails;
import com.ip.erss.competency.iamautomation.service.AdUtilService;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;
import com.ip.erss.competency.iamautomation.service.SkillAttestationService;
import com.ip.erss.competency.iamautomation.vo.SkillAttestationDetails;
import com.ip.erss.competency.iamautomation.vo.SkillAttestationElastic;

@RestController
@EnableAutoConfiguration
public class SkillAttestationRestController {

	private final Logger logger = LoggerFactory.getLogger(SkillAttestationRestController.class);
	
	@Autowired
	private SkillAttestationService skillAttestationService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Autowired
	private AdUtilService adUtilService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_skillAttestation_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@RequestMapping(value="/loadAllSkillAttestation/", method = RequestMethod.GET)
	public List<SkillAttestation> loadAllSkillAttestation() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		logger.info("User logged in is "+auth.getName());
		/*if(userName.equalsIgnoreCase("549445")) {
			userName = "134930";
		}*/
		
		/*List<SkillAttestation> skillAttestationList = skillAttestationService.findAllBySupervisorId(userName);
		if(skillAttestationList == null || skillAttestationList.isEmpty()) {
			adUtilService.getSupervisorDetails(userName);
		}*/
		adUtilService.getSupervisorDetails(userName);
		return skillAttestationService.findAllBySupervisorId(userName);
		
	}
	
	@RequestMapping(value="/loadAllAssociteDetails/", method = RequestMethod.POST)
	public List<SkillAttestation> loadAllAssociteDetails(@RequestBody List<String> supervisorList) throws NamingException {
		
		List<SkillAttestation> associateList = adUtilService.getSupervisorDetails(supervisorList);
		
		
		
		return associateList;
		
	}
	
	@RequestMapping(value="/loadSkillAtteatationBySupervisorApi/{supervisorId}", method = RequestMethod.GET)
	public List<SkillAttestation> loadSkillAtteatationBySupervisorApi(@PathVariable("supervisorId") String supervisorId) {
		
		/*List<SkillAttestation> skillAttestationList = skillAttestationService.findAllBySupervisorId(supervisorId);
		if(skillAttestationList == null || skillAttestationList.isEmpty()) {
			skillAttestationList = adUtilService.getSupervisorDetails(supervisorId);
		}*/
		adUtilService.getSupervisorDetails(supervisorId);
		return skillAttestationService.findAllBySupervisorId(supervisorId);
	}
	
	@RequestMapping(value="/loadSkillDetails/{associateId}", method = RequestMethod.GET)
	public List<SkillDetails> loadSkillDetails(@PathVariable("associateId") String associateId) {
		
		
		List<SkillDetails> skillDetailsList = skillAttestationService.findAllByAssociateId(associateId);
		return skillDetailsList;
		
	}
	
	@RequestMapping(value = "/skillAttestationRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addSkillAttestation(@RequestBody SkillAttestationDetails skillAttestationDetails) throws JsonProcessingException {
		logger.info("inside updateAcountBase skillAttestation  ::  "+skillAttestationDetails);
		skillAttestationDetails.setCreatedDate(new Date());
		//skillAttestationService.addSkillAttestation(skillAttestation);
		
		if(skillAttestationDetails != null && skillAttestationDetails.getSkillDetails() != null) {
			for(SkillDetails skillDetails : skillAttestationDetails.getSkillDetails()) {
				skillAttestationService.updateSkillDetails(skillDetails);
			}
		}
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String skillAttestationJson = mapper.writeValueAsString(skillAttestationDetails);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, skillAttestationJson, skillAttestationDetails.getSkillAttestationId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loadSkillAttestationById/{skillAttestationId}", method = RequestMethod.GET)
	public SkillAttestation loadSkillAttestationById(@PathVariable("skillAttestationId") long skillAttestationId) {
		logger.info("inside loadSkillAttestationById skillAttestationId  ::  "+skillAttestationId);
		SkillAttestation skillAttestation = skillAttestationService.loadSkillAttestationById(skillAttestationId);
		return skillAttestation;
	}
	
	@RequestMapping(value = "/updateSkillAttestation/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSkillAttestation(@RequestBody SkillAttestationDetails skillAttestationDetails) throws JsonProcessingException {
		logger.info("inside updateAcountBase skillAttestation  ::  "+skillAttestationDetails);
		skillAttestationDetails.setCreatedDate(new Date());
		//skillAttestationService.updateSkillAttestation(skillAttestation);
		SkillAttestationElastic skillAttestationElastic = null;
		if(skillAttestationDetails != null && skillAttestationDetails.getSkillDetails() != null) {
			for(SkillDetails skillDetails : skillAttestationDetails.getSkillDetails()) {
				skillAttestationService.updateSkillDetails(skillDetails);
				skillAttestationElastic = new SkillAttestationElastic();
				skillAttestationElastic.setAssociateId(skillAttestationDetails.getAssociateId());
				skillAttestationElastic.setAssociateName(skillAttestationDetails.getAssociateName());
				skillAttestationElastic.setCreatedDate(new Date());
				skillAttestationElastic.setGrade(skillAttestationDetails.getGrade());
				skillAttestationElastic.setId(skillDetails.getId());
				skillAttestationElastic.setLocation(skillAttestationDetails.getLocation());
				skillAttestationElastic.setProficiency(skillDetails.getProficiency());
				skillAttestationElastic.setRegion(skillAttestationDetails.getRegion());
				skillAttestationElastic.setSelectSkill(skillDetails.getSelectSkill());
				skillAttestationElastic.setSkill(skillDetails.getSkill());
				skillAttestationElastic.setSkillActual(skillDetails.getSkillActual());
				skillAttestationElastic.setSupervisorId(skillAttestationDetails.getSupervisorId());
				skillAttestationElastic.setSupervisorName(skillAttestationDetails.getSupervisorName());
				logger.info("skillAttestationElastic  ::  "+skillAttestationElastic);
				if(isElasticSearchEnable) {
					ObjectMapper mapper = new ObjectMapper();
					String skillAttestationJson = mapper.writeValueAsString(skillAttestationElastic);
					logger.info("skillAttestationJson  ::  "+skillAttestationJson);
				 	ElasticDetails elasticDetails = new ElasticDetails();
					elasticDetails.setElasticUrl(elasticSearchUrl);
					elasticDetails.setElasticIndex(elasticSearchIndex);
					elasticDetails.setElasticType(elasticSearchType);
					String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, skillAttestationJson, skillAttestationElastic.getId()); 
					logger.info("status  ::  " + status);
				 }
			}
		}
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateSkillAttestationApi/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSkillAttestationApi(@RequestBody List<SkillAttestationDetails> skillAttestationDetailsList) throws JsonProcessingException {
		logger.info("inside updateAcountBase skillAttestation  ::  "+skillAttestationDetailsList);
		for(SkillAttestationDetails skillAttestationDetails : skillAttestationDetailsList) {
		skillAttestationDetails.setCreatedDate(new Date());
		//skillAttestationService.updateSkillAttestation(skillAttestation);
		SkillAttestationElastic skillAttestationElastic = null;
		if(skillAttestationDetails != null && skillAttestationDetails.getSkillDetails() != null) {
			for(SkillDetails skillDetails : skillAttestationDetails.getSkillDetails()) {
				skillDetails.setAssociateId(skillAttestationDetails.getAssociateId());
				skillAttestationService.updateSkillDetails(skillDetails);
				skillAttestationElastic = new SkillAttestationElastic();
				skillAttestationElastic.setAssociateId(skillAttestationDetails.getAssociateId());
				skillAttestationElastic.setAssociateName(skillAttestationDetails.getAssociateName());
				skillAttestationElastic.setCreatedDate(new Date());
				skillAttestationElastic.setGrade(skillAttestationDetails.getGrade());
				skillAttestationElastic.setId(skillDetails.getId());
				skillAttestationElastic.setLocation(skillAttestationDetails.getLocation());
				skillAttestationElastic.setProficiency(skillDetails.getProficiency());
				skillAttestationElastic.setRegion(skillAttestationDetails.getRegion());
				skillAttestationElastic.setSelectSkill(skillDetails.getSelectSkill());
				skillAttestationElastic.setSkill(skillDetails.getSkill());
				skillAttestationElastic.setSkillActual(skillDetails.getSkillActual());
				skillAttestationElastic.setSupervisorId(skillAttestationDetails.getSupervisorId());
				skillAttestationElastic.setSupervisorName(skillAttestationDetails.getSupervisorName());
				logger.info("skillAttestationElastic  ::  "+skillAttestationElastic);
				if(isElasticSearchEnable) {
					ObjectMapper mapper = new ObjectMapper();
					String skillAttestationJson = mapper.writeValueAsString(skillAttestationElastic);
					logger.info("skillAttestationJson  ::  "+skillAttestationJson);
				 	ElasticDetails elasticDetails = new ElasticDetails();
					elasticDetails.setElasticUrl(elasticSearchUrl);
					elasticDetails.setElasticIndex(elasticSearchIndex);
					elasticDetails.setElasticType(elasticSearchType);
					String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, skillAttestationJson, skillAttestationElastic.getId()); 
					logger.info("status  ::  " + status);
				 }
			}
		}
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteSkillAttestation/{skillAttestationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSkillAttestation(@PathVariable("skillAttestationId") long skillAttestationId) {
		logger.info("inside deleteSkillAttestation skillAttestationId  ::  "+skillAttestationId);
		skillAttestationService.deleteSkillAttestation(skillAttestationId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, skillAttestationId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteSkill/{skillId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSkill(@PathVariable("skillId") long skillId) {
		logger.info("inside deleteSkillAttestation skillId  ::  "+skillId);
		skillAttestationService.deleteSkill(skillId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, skillId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
