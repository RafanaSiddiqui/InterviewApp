package com.ip.erss.competency.iamautomation.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.erss.competency.iamautomation.model.Configuration;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;
import com.ip.erss.competency.iamautomation.model.Roles;
import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.model.UserRoles;
import com.ip.erss.competency.iamautomation.service.ConfigurationService;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;
import com.ip.erss.competency.iamautomation.service.MailNotificationService;
import com.ip.erss.competency.iamautomation.service.MasterDemandRequestService;
import com.ip.erss.competency.iamautomation.service.UserService;
import com.ip.erss.competency.iamautomation.vo.ConfigurationByCategory;

@RestController
@EnableAutoConfiguration
public class MasterDemandRequestController {

	private final Logger logger = LoggerFactory.getLogger(MasterDemandRequestController.class);

	@Autowired
	private MasterDemandRequestService masterDemandRequestService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private MailNotificationService mailNotificationService;

	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Autowired
	private UserService userService;;

	@Value("${spring.mail.host}")
	private String smtpHost;

	@Value("${spring.mail.port}")
	private String smtpPort;

	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_csr_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;

	@RequestMapping(value = "/loadMasterDemandRequest/", method = RequestMethod.GET)
	public List<MasterDemandRequest> loadAllMasterDemandRequests() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		System.out.println("userName  ::  " + userName);
		UserInfo u = new UserInfo();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<MasterDemandRequest> masterDemandReqList = null;
		for (GrantedAuthority authority : authorities) {
			System.out.println("Authority is  ::  " + authority);
			//u.setRole(authority.getAuthority());
			if (authority.getAuthority().equalsIgnoreCase("ROLE_DELIVERY_LEAD")) {
				masterDemandReqList = masterDemandRequestService.loadAllDemandRequestsByUser(userName);
				break;
			} else if (authority.getAuthority().equalsIgnoreCase("ROLE_TAG")){
				masterDemandReqList = masterDemandRequestService.loadAllDemandRequestsByFlag();
				break;
			} else {
				masterDemandReqList = masterDemandRequestService.loadAllDemandRequests();
				break;
			}
		}

		return masterDemandReqList;
	}

	@RequestMapping(value = "/loadMasterDemandRequest/{demandRequestId}", method = RequestMethod.GET)
	public ResponseEntity<MasterDemandRequest> loadAllMasterDemandRequestById(
			@PathVariable("demandRequestId") Long demandRequestId) {
		MasterDemandRequest masterDemandReq = masterDemandRequestService.loadMasterDemandRequestById(demandRequestId);

		if (masterDemandReq == null) {
			logger.info("there is no records found " + demandRequestId);
			return new ResponseEntity<MasterDemandRequest>(HttpStatus.NOT_FOUND);
		} else {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(masterDemandReq.getReqStartDate());

			Instant reqStartDateInstant = masterDemandReq.getReqStartDate().toInstant();
			// masterDemandReq.setReqStartDate(reqStartDateInstant);

		}
		return new ResponseEntity<MasterDemandRequest>(masterDemandReq, HttpStatus.OK);
	}

	@RequestMapping(value = "/masterDemandRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> createDemandRequest(@RequestBody MasterDemandRequest masterDemandRequest,
			UriComponentsBuilder ucBuilder) throws ParseException, JsonProcessingException {
		logger.info("createDemandRequest SoId  ::  " + masterDemandRequest.getSoId());

		masterDemandRequest.setCreationDate(new Date(System.currentTimeMillis()));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		masterDemandRequest.setRequestorID(auth.getName());
		masterDemandRequest.setRequestorName(auth.getName());
		masterDemandRequest.setStatus("Pending");

		ObjectMapper mapper = new ObjectMapper();
		
		Long masterDemandRequestId = masterDemandRequestService.createDemandRequest(masterDemandRequest);
		logger.info("masterDemandRequestId  ::  " + masterDemandRequestId);
		// masterDemandRequest.setDemandRequestId(masterDemandRequestId);

		 if(isElasticSearchEnable) {
		 String masterDemandRequestJson = mapper.writeValueAsString(masterDemandRequest);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
		 String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, masterDemandRequestJson, masterDemandRequestId); 
		 logger.info("status  ::  " + status);
		 }

		Map<String, String> mailPropMap = new HashMap<String, String>();
		mailPropMap.put("smtphost", smtpHost);
		mailPropMap.put("smtpport", smtpPort);
		mailPropMap.put("soId", masterDemandRequest.getSoId());
		mailPropMap.put("mailContent",
				"Master Demand request - " + masterDemandRequest.getSoId() + " is created");
		String userName = auth.getName();
		 UserInfo userInfo = userService.getUserInfo(userName);
		 List<UserInfo> userInfoList = userService.getUserListByRole("ROLE_TSC");
		 
		 StringBuilder ccList = new StringBuilder();
		 if(userInfoList != null) {
		 for(UserInfo user : userInfoList) {
			 ccList.append(user.getMailId());
			 ccList.append(",");
		 }
		}
		
		mailPropMap.put("toList", userInfo.getMailId());
	    mailPropMap.put("ccList", ccList.toString());
		mailNotificationService.sendEmail("E01", mailPropMap);
		
		if (masterDemandRequest.isFlaggedForHire()) {
			mailPropMap.put("mailContent", "Master Demand request - " + masterDemandRequest.getSoId()
					+ " is Flagged for Hire");
			
			 List<UserInfo> userList = userService.getUserListByRole("ROLE_TAG");
			 
			 StringBuilder cc = new StringBuilder();
			 if(userList != null) {
			 for(UserInfo usr : userList) {
				 cc.append(usr.getMailId());
				 cc.append(",");
			 }
			}
			mailPropMap.put("ccList", cc.toString());
			mailNotificationService.sendEmail("E01", mailPropMap);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/masterDemandRequest/").buildAndExpand(masterDemandRequest.getSoId()).toUri());
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/masterDemandRequest/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateDemandRequest(@RequestBody MasterDemandRequest masterDemandRequest,
			UriComponentsBuilder ucBuilder) throws JsonProcessingException {
		logger.info("createDemandRequest SoId  ::  " + masterDemandRequest.getSoId());

		ObjectMapper mapper = new ObjectMapper();
		String masterDemandRequestJson = mapper.writeValueAsString(masterDemandRequest);
		
		masterDemandRequestService.updateDemandRequest(masterDemandRequest);
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, masterDemandRequestJson, masterDemandRequest.getDemandRequestId()); 
			logger.info("status  ::  " +status);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/masterDemandRequest/").buildAndExpand(masterDemandRequest.getSoId()).toUri());

		Map<String, String> mailPropMap = new HashMap<String, String>();
		mailPropMap.put("smtphost", smtpHost);
		mailPropMap.put("smtpport", smtpPort);
		mailPropMap.put("soId", masterDemandRequest.getSoId());
		mailPropMap.put("mailContent",
				"Master Demand request - " + masterDemandRequest.getSoId() + " is updated");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		 UserInfo userInfo = userService.getUserInfo(userName);
		 List<UserInfo> userInfoList = userService.getUserListByRole("ROLE_TSC");
		 
		 StringBuilder ccList = new StringBuilder();
		 if(userInfoList != null) {
		 for(UserInfo user : userInfoList) {
			 ccList.append(user.getMailId());
			 ccList.append(",");
		 }
		}
		
		mailPropMap.put("toList", userInfo.getMailId());
		mailPropMap.put("ccList", ccList.toString());
		mailNotificationService.sendEmail("E01", mailPropMap);

		if (masterDemandRequest.isFlaggedForHire()) {
			mailPropMap.put("mailContent", "Master Demand request - " + masterDemandRequest.getSoId()
					+ " is Flagged for Hire");
			
			 List<UserInfo> userList = userService.getUserListByRole("ROLE_TAG");
			 
			 StringBuilder cc = new StringBuilder();
			 if(userList != null) {
			 for(UserInfo usr : userList) {
				 cc.append(usr.getMailId());
				 cc.append(",");
			 }
			}
			mailPropMap.put("ccList", cc.toString());
			mailNotificationService.sendEmail("E01", mailPropMap);
		}

		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/masterDemandRequest/{demandRequestId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDemandRequest(@PathVariable("demandRequestId") Long demandRequestId,
			UriComponentsBuilder ucBuilder) {
		logger.info("Going to Delete Demand App Request " + demandRequestId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/masterDemandRequest/{demandRequestId}").buildAndExpand(demandRequestId).toUri());
		MasterDemandRequest masterDemandReq = masterDemandRequestService.loadMasterDemandRequestById(demandRequestId);
		if (masterDemandReq == null) {
			logger.info("there is no records found " + demandRequestId);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		masterDemandRequestService.deleteDemandRequest(demandRequestId);
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, demandRequestId); 
			logger.info("status  ::  " +status);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/loadPageableMasterDemandRequest/{page}/size/{size}", method = RequestMethod.GET)
	public Page<MasterDemandRequest> loadPageableMasterDemandRequest(@PathVariable("page") int page,
			@PathVariable("size") int size) {

		return masterDemandRequestService.loadPageableMasterDemandRequest(new PageRequest(page, size));
	}

	@RequestMapping(value = "/loadPageableDemandReqBySoId/{page}/size/{size}/soId/{soId}", method = RequestMethod.GET)
	public Page<MasterDemandRequest> loadPageableDemandReqBySoId(@PathVariable("page") int page,
			@PathVariable("size") int size, @PathVariable("soId") String soId) {
		Pageable pageable = new PageRequest(page, size);
		return masterDemandRequestService.loadPageableDemandReqBySoId(soId, pageable);
	}

	@RequestMapping(value = "/fetchConfiguration/{category}", method = RequestMethod.GET)
	public List<Object> fetchConfiguration(@PathVariable("category") String category) {

		logger.info("Going to fetch fetchtemplateGUIConfiguration");

		List<Configuration> configurationList = configurationService.fetchConfiguration(category);

		List<Object> objList = new ArrayList<Object>();

		for (Configuration conf : configurationList) {
			Object obj = conf.getValue();
			objList.add(obj);
		}

		return objList;
	}

	@RequestMapping(value = "/loadAllConfiguration/", method = RequestMethod.GET)
	public List<ConfigurationByCategory> loadAllConfiguration() {

		Map<String, List<Configuration>> categoryConfigMap = new HashMap<String, List<Configuration>>();

		List<Configuration> configurationList = configurationService.fetchAllConfigurationDetails();
		for (Configuration configuration : configurationList) {

			if (categoryConfigMap.get(configuration.getCategory()) != null) {
				categoryConfigMap.get(configuration.getCategory()).add(configuration);
			} else {
				List<Configuration> configurationListNew = new ArrayList<Configuration>();
				configurationListNew.add(configuration);
				categoryConfigMap.put(configuration.getCategory(), configurationListNew);
			}
		}

		List<ConfigurationByCategory> configurationByCategoryList = new ArrayList<ConfigurationByCategory>();

		categoryConfigMap.entrySet().forEach(entry -> {
		    //logger.info("Key : " + entry.getKey() + " Value : " + entry.getValue());
		    ConfigurationByCategory ConfigurationByCategory = new ConfigurationByCategory();
		    ConfigurationByCategory.setCategory(entry.getKey());
		    ConfigurationByCategory.setConfigurationList(entry.getValue());
		    configurationByCategoryList.add(ConfigurationByCategory);
		});

		return configurationByCategoryList;
	}
	

	@RequestMapping(value = "/fetchDashboards/{category}", method = RequestMethod.GET)
	public List<Configuration> fetchDashboards(@PathVariable("category") String category) {

		logger.info("Going to fetchDashboards");

		List<Configuration> configurationList = configurationService.fetchConfiguration(category);

		return configurationList;
	}

	@RequestMapping(value = "/fetchAllRoles/", method = RequestMethod.GET)
	public List<Object> fetchAllRoles() {

		logger.info("Going to fetch fetchAllRoles");

		List<Roles> rolesList = masterDemandRequestService.fetchAllRoles();
		List<Object> objList = new ArrayList<Object>();
		for (Roles roles : rolesList) {
			Object obj = roles.getRoleName();
			objList.add(obj);
		}

		return objList;
	}
	
	@RequestMapping(value = "/fetchAllUserRoles/", method = RequestMethod.GET)
	public List<Roles> fetchAllUserRoles() {

		logger.info("Going to fetch fetchAllUserRoles");

		List<Roles> rolesList = masterDemandRequestService.fetchAllRoles();

		return rolesList;
	}

	@RequestMapping(value = "/flagSelectSendMail/{flagSelect}/soId/{soId}", method = RequestMethod.GET)
	public ResponseEntity<Object> flagSelectSendMail(@PathVariable("flagSelect") boolean flagSelect,
			@PathVariable("soId") String soId) throws ParseException {

		Map<String, String> mailPropMap = new HashMap<String, String>();
		mailPropMap.put("smtphost", smtpHost);
		mailPropMap.put("smtpport", smtpPort);
		mailPropMap.put("soId", soId);
		mailPropMap.put("mailContent",
				"Your Master Demand request - " + soId + " <b>Flag for Hire<b> has been updated");
		if (flagSelect) {
			mailNotificationService.sendEmail("E01", mailPropMap);
		}

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateElasticData/", method = RequestMethod.GET)
	public ResponseEntity<Object> updateElasticData() throws ParseException {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.set("accept", MediaType.ALL_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

		String testJson = "{\"Value\":\"local\",\"Name\":\"filetransport\"}";

		HttpEntity<String> entity = new HttpEntity<String>(testJson, headers);

		ResponseEntity<String> testList = restTemplate.exchange(elasticSearchUrl, HttpMethod.POST, entity,
				String.class);

		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/downloadExcelFile/", method = RequestMethod.GET)
	public void downLoadCsv(HttpServletRequest request, HttpServletResponse response) {

		String fileName = "Master_demand_Sheet.xls";

		try {

			List<MasterDemandRequest> masterDemandReqList = masterDemandRequestService.loadAllDemandRequests();

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			Field[] fields = MasterDemandRequest.class.getDeclaredFields();
			// System.out.println("MasterDemandRequestController.class.getDeclaredFields()
			// :: "+MasterDemandRequest.class.getDeclaredFields().length);

			fields = (Field[]) ArrayUtils.remove(fields, 0);

			HSSFRow header = sheet.createRow(0);

			CellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setFont(font);

			for (int i = 0; i < fields.length; i++) {
				// System.out.println("fields[i].getName() :: "+fields[i].getName());
				Cell cell = header.createCell(i);
				cell.setCellValue(fields[i].getName().toUpperCase());
				cell.setCellStyle(style);
			}

			int rowCount = 1;
			for (MasterDemandRequest masterDemandRequest : masterDemandReqList) {
				HSSFRow row = sheet.createRow(rowCount++);
				row.createCell(0).setCellValue(masterDemandRequest.getDemandRequestId());
				row.createCell(1).setCellValue(masterDemandRequest.getSoId());
				row.createCell(2).setCellValue(masterDemandRequest.getBillability());
				row.createCell(3).setCellValue(masterDemandRequest.getReqStartDate());
				row.createCell(4).setCellValue(masterDemandRequest.getPriority());
				row.createCell(5).setCellValue(masterDemandRequest.getGrade());
				row.createCell(6).setCellValue(masterDemandRequest.getPdp());
				row.createCell(7).setCellValue(masterDemandRequest.getVertical());
				row.createCell(8).setCellValue(masterDemandRequest.getAccountName());
				row.createCell(9).setCellValue(masterDemandRequest.getOffShore());
				row.createCell(10).setCellValue(masterDemandRequest.getCity());
				row.createCell(11).setCellValue(masterDemandRequest.getCountry());
				row.createCell(12).setCellValue(masterDemandRequest.getProjectID());
				row.createCell(13).setCellValue(masterDemandRequest.getProjectName());

				CellStyle cellStyle = wb.createCellStyle();
				CreationHelper createHelper = wb.getCreationHelper();
				cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
				Cell creationDateCell = row.createCell(14);
				creationDateCell.setCellValue(masterDemandRequest.getCreationDate());
				creationDateCell.setCellStyle(cellStyle);

				// row.createCell(14).setCellValue(masterDemandRequest.getCreationDate());

				cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm/dd/yy"));
				Cell flaggedForHireDateCell = row.createCell(15);
				flaggedForHireDateCell.setCellValue(masterDemandRequest.getCreationDate());
				flaggedForHireDateCell.setCellStyle(cellStyle);

				// row.createCell(15).setCellValue(masterDemandRequest.getFlaggedForHireDate());

				cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm/dd/yy"));
				Cell reqStartDateCell = row.createCell(16);
				reqStartDateCell.setCellValue(masterDemandRequest.getReqStartDate());
				reqStartDateCell.setCellStyle(cellStyle);

				// row.createCell(16).setCellValue(masterDemandRequest.getReqStartDate());
				row.createCell(17).setCellValue(masterDemandRequest.getTechnicalSkills());
				row.createCell(18).setCellValue(masterDemandRequest.getRequestorName());
				row.createCell(19).setCellValue(masterDemandRequest.getRequestorID());
				row.createCell(20).setCellValue(masterDemandRequest.getCompetency());
				row.createCell(21).setCellValue(masterDemandRequest.getStatus());

			}

			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();

		} catch (IOException e) {
			logger.error("error in downloading xls file :: " + e);
		}

	}

}
