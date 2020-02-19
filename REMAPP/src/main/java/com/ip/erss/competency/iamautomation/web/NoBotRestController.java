package com.ip.erss.competency.iamautomation.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ip.erss.competency.iamautomation.model.NoBot;
import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.model.UserRoles;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;
import com.ip.erss.competency.iamautomation.service.MailNotificationService;
import com.ip.erss.competency.iamautomation.service.NoBotService;
import com.ip.erss.competency.iamautomation.service.UserService;

@RestController
@EnableAutoConfiguration
public class NoBotRestController {

	private final Logger logger = LoggerFactory.getLogger(NoBotRestController.class);
	
	@Autowired
	private NoBotService noBotService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Autowired
	private MailNotificationService mailNotificationService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_NoBot_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@Value("${spring.mail.host}")
	private String smtpHost;

	@Value("${spring.mail.port}")
	private String smtpPort;
	
	@RequestMapping(value = "/noBotRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addNoBot(@RequestBody NoBot noBot) throws JsonProcessingException {
		try {
		logger.info("inside addNoBot NoBot  ::  "+noBot);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		noBot.setCreatedDate(new Date());
		noBot.setStatus("PENDING");
		noBot.setCreatedBy(userName);
		
		noBotService.addNoBot(noBot);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String NoBotJson = mapper.writeValueAsString(noBot);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, NoBotJson, noBot.getNoBotId()); 
			logger.info("status  ::  " + status);
		 }
		if(noBot.getStatus().equalsIgnoreCase("PENDING")) {
			Map<String, String> mailPropMap = new HashMap<String, String>();
			mailPropMap.put("smtphost", smtpHost);
			mailPropMap.put("smtpport", smtpPort);
			mailPropMap.put("customerName", noBot.getCustomerName());
			mailPropMap.put("projectName", noBot.getProjectName());
			/*mailPropMap.put("mailContent",
					"No Bot Declaration request is submitted and its is pending for your approval\n " + "Please find below details,\n"+
					"Customer Name : "+noBot.getCustomerName()+"\n"+
					"Project Name  ::  "+noBot.getProjectName());*/
			 
			 List<UserInfo> userInfoList = userService.getUserListByRoleApprover("ROLE_ADMIN", "NOBOT");
			 
			 StringBuilder ccList = new StringBuilder();
			 if(userInfoList != null) {
			 for(UserInfo user : userInfoList) {
				 ccList.append(user.getMailId());
				 ccList.append(",");
			 }
			}
			
			mailPropMap.put("toList", ccList.toString());
		    mailPropMap.put("ccList", ccList.toString());
			mailNotificationService.sendEmail("E03", mailPropMap);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/loadAllNoBots/", method = RequestMethod.GET)
	public List<NoBot> loadAllnoBots() {
		List<NoBot> noBotList = null;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			//System.out.println("userName  ::  " + userName);
			UserInfo userInfo = userService.getUserInfo(userName);
			
			List<UserRoles> userRoleList = userInfo.getUserRolesList();
			
			for(UserRoles userRole : userRoleList) {
				if(userRole.getRoleName().equalsIgnoreCase("ROLE_ADMIN") || userRole.getRoleName().equalsIgnoreCase("ROLE_NOBOT")) {
					noBotList = noBotService.loadAllNoBotApprovalsPending();
					break;
				} else {
					//noBotList = noBotService.loadAllNoBots();
					noBotList = noBotService.loadAllNoBotsName(userName);
					break;
				}
			}
			
		//List<NoBot> noBotList = noBotService.loadAllNoBots();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return noBotList;
		
	}
	
	@RequestMapping(value="/indexAllNoBots/", method = RequestMethod.GET)
	public List<NoBot> indexAllNoBots() throws JsonProcessingException {
		List<NoBot> noBotList = noBotService.loadAllNoBots();
		for(NoBot noBot : noBotList) {
			if(isElasticSearchEnable) {
				ObjectMapper mapper = new ObjectMapper();
				String NoBotJson = mapper.writeValueAsString(noBot);
			 	ElasticDetails elasticDetails = new ElasticDetails();
				elasticDetails.setElasticUrl(elasticSearchUrl);
				elasticDetails.setElasticIndex(elasticSearchIndex);
				elasticDetails.setElasticType(elasticSearchType);
				String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, NoBotJson, noBot.getNoBotId()); 
				logger.info("status  ::  " + status);
			 }
		}
		return noBotList;
		
	}
	
	@RequestMapping(value="/loadAllNoBotsByStatus/", method = RequestMethod.GET)
	public List<NoBot> loadAllNoBotsByStatus() {
		
		List<NoBot> noBotList = noBotService.loadAllNoBotsByStatus();
		
		return noBotList;
		
	}
	
	@RequestMapping(value = "/loadNoBotById/{noBotId}", method = RequestMethod.GET)
	public NoBot loadNoNoBotById(@PathVariable("noBotId") long noBotId) {
		logger.info("inside loadNoBotById noBotId  ::  "+noBotId);
		NoBot noBot = noBotService.loadNoBotById(noBotId);
		return noBot;
	}
	
	@RequestMapping(value = "/updateNoBot/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateNoBot(@RequestBody NoBot noBot) throws JsonProcessingException {
		logger.info("inside updateNoBot NoBot  ::  "+noBot);
		try {
		noBot.setCreatedDate(new Date());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		if(noBot.getStatus().equalsIgnoreCase("APPROVED") || noBot.getStatus().equalsIgnoreCase("REJECTED")) {
			noBot.setApprovedBy(userName);
		}
		noBotService.updateNoBot(noBot);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String noBotJson = mapper.writeValueAsString(noBot);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, noBotJson, noBot.getNoBotId()); 
			logger.info("status  ::  " + status);
		 }
		if(noBot.getStatus().equalsIgnoreCase("APPROVED") || noBot.getStatus().equalsIgnoreCase("REJECTED")) {
			Map<String, String> mailPropMap = new HashMap<String, String>();
			mailPropMap.put("smtphost", smtpHost);
			mailPropMap.put("smtpport", smtpPort);
			mailPropMap.put("customerName", noBot.getCustomerName());
			mailPropMap.put("projectName", noBot.getProjectName());
			/*mailPropMap.put("mailContent",
					"No Bot Declaration request is approved " + "Please find below details,\n"+
					"Customer Name : "+noBot.getCustomerName()+"\n"+
					"Project Name  ::  "+noBot.getProjectName());*/
			UserInfo userInfo = userService.getUserInfo(userName);
			List<UserInfo> userInfoList = userService.getUserListByRoleApprover("ROLE_ADMIN", "NOBOT");
			 
			 StringBuilder ccList = new StringBuilder();
			 if(userInfoList != null) {
			 for(UserInfo user : userInfoList) {
				 ccList.append(user.getMailId());
				 ccList.append(",");
			 }
			}
			
			mailPropMap.put("toList", userInfo.getMailId());
		    mailPropMap.put("ccList", ccList.toString());
		    mailPropMap.put("name", userInfo.getFirstName());
			mailNotificationService.sendEmail("E02", mailPropMap);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteNoBot/{noBotId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteNoBot(@PathVariable("noBotId") long noBotId) {
		logger.info("inside deleteNoBot noBotId  ::  "+noBotId);
		noBotService.deleteNoBot(noBotId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, noBotId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
