package com.ip.erss.competency.iamautomation.web;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.erss.competency.iamautomation.model.BotMeasurements;
import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.model.Configuration;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;
import com.ip.erss.competency.iamautomation.service.BotMeasurementService;
import com.ip.erss.competency.iamautomation.service.BotService;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;

@RestController
@EnableAutoConfiguration
public class BotMeasurementRestController {

	private final Logger logger = LoggerFactory.getLogger(BotMeasurementRestController.class);
	
	@Autowired
	private BotMeasurementService botMeasurementService;
	
	@Autowired
	private ElasticSearchOperationService elasticSearchOperationService;
	
	@Autowired
	private BotService botService;
	
	@Value("${elastic_search_url}")
	private String elasticSearchUrl;
	
	@Value("${elastic_search_botmeasurement_index}")
	private String elasticSearchIndex;
	
	@Value("${elastic_search_type}")
	private String elasticSearchType;
	
	@Value("${enable.elastic.search}")
	private boolean isElasticSearchEnable;
	
	@RequestMapping(value="/loadAllBotMeasurements/", method = RequestMethod.GET)
	public List<BotMeasurements> loadAllBotMeasurements() {
		
		List<BotMeasurements> botMeasurementList = botMeasurementService.loadAllBotMeasurements();
		
		return botMeasurementList;
		
	}
	
	@RequestMapping(value="/indexAllBotMeasurements/", method = RequestMethod.GET)
	public List<BotMeasurements> indexAllBotMeasurements() throws JsonProcessingException {
		
		List<BotMeasurements> botMeasurementList = botMeasurementService.loadAllBotMeasurements();
		for(BotMeasurements botMeasurement : botMeasurementList) {
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(botMeasurement);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, botMeasurement.getId()); 
			logger.info("status  ::  " + status);
		 }
		}
		return botMeasurementList;
		
	}
	
	@RequestMapping(value = "/botMeasurementRequest/", method = RequestMethod.POST)
	public ResponseEntity<Object> addBotMeasurement(@RequestBody BotMeasurements botMeasurement) throws JsonProcessingException {
		logger.info("inside addBot botMeasurement  ::  "+botMeasurement);
		
		/*if(botMeasurement != null) {
			Bots bot = botService.loadBotById(botMeasurement.getBotId());
			botMeasurement.setBotName(bot.getBotName());
		}*/
		
		botMeasurement.setCreatedDate(new Date());
		botMeasurementService.addBotMeasurement(botMeasurement);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(botMeasurement);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, botMeasurement.getId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loadBotMeasurementById/{botMeasurementId}", method = RequestMethod.GET)
	public BotMeasurements loadBotMeasurementById(@PathVariable("botMeasurementId") long botMeasurementId) {
		logger.info("inside loadBotMeasurementById botMeasurementId  ::  "+botMeasurementId);
		BotMeasurements botMeasurement = botMeasurementService.loadBotMeasurementById(botMeasurementId);
		return botMeasurement;
	}
	
	@RequestMapping(value = "/updateBotMeasurement/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBotMeasurement(@RequestBody BotMeasurements botMeasurement) throws JsonProcessingException {
		logger.info("inside updateBot botMeasurement  ::  "+botMeasurement);
		
		/*if(botMeasurement != null) {
			Bots bot = botService.loadBotById(botMeasurement.getBotId());
			botMeasurement.setBotName(bot.getBotName());
		}*/
		
		botMeasurement.setCreatedDate(new Date());
		botMeasurementService.updateBotMeasurement(botMeasurement);
		
		if(isElasticSearchEnable) {
			ObjectMapper mapper = new ObjectMapper();
			String botJson = mapper.writeValueAsString(botMeasurement);
		 	ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.loadRequestToElastic(elasticDetails, botJson, botMeasurement.getId()); 
			logger.info("status  ::  " + status);
		 }
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteBotMeasurement/{botMeasurementId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBotMeasurement(@PathVariable("botMeasurementId") long botMeasurementId) {
		logger.info("inside deleteBot botMeasurementId  ::  "+botMeasurementId);
		botMeasurementService.deleteBotMeasurement(botMeasurementId);
		
		if(isElasticSearchEnable) {
			ElasticDetails elasticDetails = new ElasticDetails();
			elasticDetails.setElasticUrl(elasticSearchUrl);
			elasticDetails.setElasticIndex(elasticSearchIndex);
			elasticDetails.setElasticType(elasticSearchType);
			String status = elasticSearchOperationService.deleteElasticEntry(elasticDetails, botMeasurementId); 
			logger.info("status  ::  " +status);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getDefaulters/{botMonth}", method = RequestMethod.GET)
	public List<Configuration> getDefaulterList(@PathVariable("botMonth") String botMonth) {
		logger.info("inside getDefaulterList botMonth  ::  "+botMonth);
		List<Configuration> confList = null;
		try {
			Date createdMonth = new Date(botMonth);
			logger.info("createdMonth  ::  "+createdMonth);
			confList = botMeasurementService.getDefaulterList(createdMonth);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return confList;
	}
	
	
	public static String getFullMonth(int month){
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
	
	@RequestMapping(value = "/downloadDefaulterFile/{botMonth}", method = RequestMethod.GET)
	public void downloadDefaulterFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("botMonth") String botMonth) {

		String fileName = "Defaulter_List";
		List<Configuration> confList = null;

		try {
			Date createdMonth = new Date(botMonth);
			confList = botMeasurementService.getDefaulterList(createdMonth);
			
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(createdMonth);
			int year = cal.get(Calendar.YEAR); // 2016

			String month = getFullMonth(createdMonth.getMonth())+ " " +year;
			logger.info("month  ::  "+month);
			
			fileName = fileName+"_"+month+".xls";
			
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

			/*for (int i = 0; i < fields.length; i++) {
				// System.out.println("fields[i].getName() :: "+fields[i].getName());
				Cell cell = header.createCell(i);
				cell.setCellValue(fields[i].getName().toUpperCase());
				cell.setCellStyle(style);
			}*/
			
			Cell cell = header.createCell(0);
			cell.setCellValue("Customer Name");
			cell.setCellStyle(style);
			
			Cell manCell = header.createCell(1);
			manCell.setCellValue("Competency Manager");
			manCell.setCellStyle(style);
			
			Cell dateCell = header.createCell(2);
			dateCell.setCellValue("Month");
			dateCell.setCellStyle(style);
			
			int rowCount = 1;
			for (Configuration configuration : confList) {
				HSSFRow row = sheet.createRow(rowCount++);
				row.createCell(0).setCellValue(configuration.getName());
				row.createCell(1).setCellValue(configuration.getValue());
				row.createCell(2).setCellValue(month);
				
				/*CellStyle cellStyle = wb.createCellStyle();
				CreationHelper createHelper = wb.getCreationHelper();
				cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMM yy"));
				Cell creationDateCell = row.createCell(2);
				creationDateCell.setCellValue(createdMonth);
				creationDateCell.setCellStyle(cellStyle);*/


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
