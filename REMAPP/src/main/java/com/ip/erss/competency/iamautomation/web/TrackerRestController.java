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
import com.ip.erss.competency.iamautomation.model.Tracker;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;
import com.ip.erss.competency.iamautomation.service.TrackerService;

@RestController
@EnableAutoConfiguration
public class TrackerRestController {

	private final Logger logger = LoggerFactory.getLogger(TrackerRestController.class);
	
	@Autowired
	private TrackerService trackerService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_tracker_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@RequestMapping(value="/loadAllTrackers/", method = RequestMethod.GET)
	public List<Tracker> loadAllTrackers() {
		
		List<Tracker> trackerList = trackerService.loadAllTrackers();
		
		return trackerList;
		
	}
	
	@RequestMapping(value="/indexAllTrackers/", method = RequestMethod.GET)
	public List<Tracker> indexAllTrackers() throws JsonProcessingException {
		
		List<Tracker> trackerList = trackerService.loadAllTrackers();
		
		for(Tracker tracker : trackerList) {
			if(isElasticSearchEnable) {
				ObjectMapper mapper = new ObjectMapper();
				String trackerJson = mapper.writeValueAsString(tracker);
			 	ElasticDetails elasticDetails = new ElasticDetails();
				elasticDetails.setElasticUrl(elasticSearchUrl);
				elasticDetails.setElasticIndex(elasticSearchIndex);
				elasticDetails.setElasticType(elasticSearchType);
				String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, trackerJson, tracker.getTrackerId()); 
				logger.info("status  ::  " + status);
			 }
		}
		
		return trackerList;
		
	}
	
	@RequestMapping(value = "/trackerRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addTracker(@RequestBody Tracker tracker) throws JsonProcessingException {
		logger.info("inside addTracker tracker  ::  "+tracker);
		tracker.setCreatedDate(new Date());
		trackerService.addTracker(tracker);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String trackerJson = mapper.writeValueAsString(tracker);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, trackerJson, tracker.getTrackerId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loadTrackerById/{trackerId}", method = RequestMethod.GET)
	public Tracker loadTrackerById(@PathVariable("trackerId") long trackerId) {
		logger.info("inside loadTrackerById trackerId  ::  "+trackerId);
		Tracker tracker = trackerService.loadTrackerById(trackerId);
		return tracker;
	}
	
	@RequestMapping(value = "/updateTracker/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTracker(@RequestBody Tracker tracker) throws JsonProcessingException {
		logger.info("inside updateAcountBase tracker  ::  "+tracker);
		tracker.setCreatedDate(new Date());
		trackerService.updateTracker(tracker);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String trackerJson = mapper.writeValueAsString(tracker);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, trackerJson, tracker.getTrackerId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteTracker/{trackerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTracker(@PathVariable("trackerId") long trackerId) {
		logger.info("inside deleteTracker trackerId  ::  "+trackerId);
		trackerService.deleteTracker(trackerId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, trackerId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
