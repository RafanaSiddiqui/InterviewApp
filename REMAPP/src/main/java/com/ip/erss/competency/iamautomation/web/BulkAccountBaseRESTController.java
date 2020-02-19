package com.ip.erss.competency.iamautomation.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.BulkAccountBase;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.service.AccountBaseService;
import com.ip.erss.competency.iamautomation.service.BulkAccountBaseService;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;

@RestController
@EnableAutoConfiguration
public class BulkAccountBaseRESTController {
	
	private final Logger logger = LoggerFactory.getLogger(BulkAccountBaseRESTController.class);
	
	@Autowired
	private BulkAccountBaseService bulkAccountBaseService;
	
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
	
	@RequestMapping(value="/loadAllBulkAccountBases/", method = RequestMethod.GET)
	public List<BulkAccountBase> loadAllBulkAccountBases(){
		List<BulkAccountBase> bulkAccountBaseList = bulkAccountBaseService.loadAllBulkAccountBases();
		return bulkAccountBaseList;
	}
	
	
	@RequestMapping(value = "/bulkAccountBase/", method = RequestMethod.POST)
	public void createBulkRequest(@RequestParam(value="file") MultipartFile file, @RequestParam(value="name") String name,  @RequestParam(value="description") String description, @RequestParam(value="bulkID") String bulkID) throws IOException {
		if(null != file && file.getSize()>0) {
			
			InputStream inputStream = file.getInputStream();	
		    Workbook workbook = new XSSFWorkbook(inputStream);
		    Sheet sheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = sheet.iterator();
		    DataFormatter fmt = new DataFormatter();
		    
		    if(null!=iterator) {
		    	
		    	System.out.println("bulkID=="+bulkID);
		    	
		    	BulkAccountBase bulkAccountBase = new BulkAccountBase();
			    bulkAccountBase.setFileName(file.getOriginalFilename());
			    bulkAccountBase.setName(name);
			    bulkAccountBase.setDescription(description);
			   // bulkAccountBase.setCreatedDate(new Date(System.currentTimeMillis()));
			    bulkAccountBase.setStatus("Pending");
			    bulkAccountBase.setFileData(file.getBytes());
			    bulkAccountBase.setContentType(file.getContentType());
			    if(null!=bulkID && !"null".equalsIgnoreCase(bulkID)) {
			    	bulkAccountBase.setBulkID(Long.valueOf(bulkID));
			    	bulkAccountBase.setUpdatedDate(new Date(System.currentTimeMillis()));
		    	}else {
		    		bulkAccountBase.setCreatedDate(new Date(System.currentTimeMillis()));
		    	}
			   
			    //bulkAccountBaseService.addOrUpdateBulkAccountBase(bulkAccountBase); comment
		    	
		    	int i = 0;
		    	List<AccountBase> listAccountBase = new ArrayList<AccountBase>();
		    	
		    	 while (iterator.hasNext()) {
				    	Row currentRow = iterator.next();
				    	
				    	if(i>0) {
						    	if(currentRow.getCell(0).getCellType()!=3) {
						    		
						    		System.out.println("Customer ID==="+fmt.formatCellValue(currentRow.getCell(0)));
						    		
						    		AccountBase accountBase = new AccountBase();					                
					                accountBase.setCustomerId((fmt.formatCellValue(currentRow.getCell(0))!="") ? fmt.formatCellValue(currentRow.getCell(0)):null);
							    	accountBase.setVertical((fmt.formatCellValue(currentRow.getCell(1))!="") ? fmt.formatCellValue(currentRow.getCell(1)):null);
							    	accountBase.setAccountName((fmt.formatCellValue(currentRow.getCell(2))!="") ? fmt.formatCellValue(currentRow.getCell(2)):null);
						    		accountBase.setBfd((fmt.formatCellValue(currentRow.getCell(3))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(3))):null);
						    		accountBase.setBtb((fmt.formatCellValue(currentRow.getCell(4))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(4))):null);
						    		accountBase.setBtm((fmt.formatCellValue(currentRow.getCell(5))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(5))):null);
						    		accountBase.setNbl((fmt.formatCellValue(currentRow.getCell(6))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(6))):null);
							    		
							    	accountBase.setCompetencyManagerId((fmt.formatCellValue(currentRow.getCell(7))!="") ? fmt.formatCellValue(currentRow.getCell(7)):null);
							    	accountBase.setCompetencyManager((fmt.formatCellValue(currentRow.getCell(8))!="") ? fmt.formatCellValue(currentRow.getCell(8)):null);
							    	accountBase.setPrimaryLocation((fmt.formatCellValue(currentRow.getCell(9))!="") ? fmt.formatCellValue(currentRow.getCell(9)):null);
							    	accountBase.setOdcDetails((fmt.formatCellValue(currentRow.getCell(10))!="") ? fmt.formatCellValue(currentRow.getCell(10)):null);
							    	accountBase.setTeckStack((fmt.formatCellValue(currentRow.getCell(11))!="") ? fmt.formatCellValue(currentRow.getCell(11)):null);
							    	
							    	//accountBase.setBulkAccountBase(bulkAccountBase);
							    	accountBase.setCreatedDate(new Date());
						    		listAccountBase.add(accountBase);
						    		
						    		/*AccountBase accountBase = new AccountBase();					                
					                accountBase.setCustomerId((fmt.formatCellValue(currentRow.getCell(0))!="") ? fmt.formatCellValue(currentRow.getCell(0)):null);
							    	accountBase.setVertical((currentRow.getCell(1).getStringCellValue()!="") ? currentRow.getCell(1).getStringCellValue():null);
							    	accountBase.setAccountName((currentRow.getCell(2).getStringCellValue()!="") ? currentRow.getCell(2).getStringCellValue():null);
						    		accountBase.setBfd((fmt.formatCellValue(currentRow.getCell(3))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(3))):null);
						    		accountBase.setBtb((fmt.formatCellValue(currentRow.getCell(4))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(4))):null);
						    		accountBase.setBtm((fmt.formatCellValue(currentRow.getCell(5))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(5))):null);
						    		accountBase.setNbl((fmt.formatCellValue(currentRow.getCell(6))!="") ? Double.valueOf(fmt.formatCellValue(currentRow.getCell(6))):null);
							    		
							    	accountBase.setCompetencyManagerId((fmt.formatCellValue(currentRow.getCell(7))!="") ? fmt.formatCellValue(currentRow.getCell(7)):null);
							    	accountBase.setCompetencyManager((currentRow.getCell(8).getStringCellValue()!="") ? currentRow.getCell(8).getStringCellValue():null);
							    	accountBase.setPrimaryLocation((currentRow.getCell(9).getStringCellValue()!="") ? currentRow.getCell(9).getStringCellValue():null);
							    	accountBase.setOdcDetails((currentRow.getCell(10).getStringCellValue()!="") ? currentRow.getCell(10).getStringCellValue():null);
							    	accountBase.setTeckStack((currentRow.getCell(11).getStringCellValue()!="") ? currentRow.getCell(11).getStringCellValue():null);*/
							    	
							    	////accountBase.setBulkAccountBase(bulkAccountBase); comment
							    	//accountBase.setCreatedDate(new Date());
						    		//listAccountBase.add(accountBase);
						    		
						    	}
				    	}

				    	i++;
				    	
				    }
		    	 
		    	try {
		    		
					///bulkAccountBaseService.addOrUpdateAccountBaseList(listAccountBase); comment
		    		//if(listAccountBase.size()>0) {
		    		// SET ACCOUNT BASE ID BASED ON EXISTING DATA
		    			//bulkAccountBase.setAccountBaseList(listAccountBase);
		    			bulkAccountBase.setAccountBaseList(bulkAccountBaseService.getUpdatedAccountBaseList(listAccountBase));
		    			bulkAccountBaseService.addOrUpdateBulkAccountBase(bulkAccountBase);
		    			
		    			for(AccountBase accountBase : bulkAccountBase.getAccountBaseList()) {
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
		    		//}
		    		
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	/// accountBaseService.addAccountBase(listAccountBase);
		    }
		}
	}
	
	public List<AccountBase> mapAccountBase(List<AccountBase> listAccountBase) {
		List<AccountBase> listAccountBaseNew = null;
		
		return listAccountBaseNew;
	}
	
	
	/*@RequestMapping(value = "/bulkAccountBase/", method = RequestMethod.POST)
	public void createBulkRequest(@RequestParam(value="file") MultipartFile file, @RequestParam(value="name") String name,  @RequestParam(value="description") String description) throws IOException {
		if(null != file && file.getSize()>0) {
			InputStream inputStream = file.getInputStream();
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		    
		    BulkAccountBase bulkAccountBase = new BulkAccountBase();
		    bulkAccountBase.setFileName(file.getOriginalFilename());
		    bulkAccountBase.setName(name);
		    bulkAccountBase.setDescription(description);
		    bulkAccountBase.setCreatedDate(new Date(System.currentTimeMillis()));
		    bulkAccountBase.setStatus("Pending");
		    bulkAccountBase.setFileData(file.getBytes());
		    bulkAccountBase.setContentType(file.getContentType());
		    bulkAccountBaseService.addBulkAccountBase(bulkAccountBase);
		    
		    String fileRecord;		    
		    List<AccountBase> listAccountBase = new ArrayList<AccountBase>();
		    
		    String header = "";
		    String headers[] = null;
		    int headerSplit = 12;
		    String keyArray[] = null;
		    
		    if(null != bufferedReader) {
		    	if(!(header = bufferedReader.readLine()).isEmpty() && null != header ) {
				     headers = header.split(",");
				     keyArray = Arrays.copyOfRange(headers, headerSplit, headers.length);
				     System.out.println("Bulk Account Base File Column Length = "+keyArray.length);
		    	 }
		    	 System.out.println("Before While loop");
				    while ((fileRecord = bufferedReader.readLine()) != null )
				    {
				    	AccountBase accountBase = new AccountBase();
				    	String[] values = fileRecord.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				    	
				    	for(String st : values) {
				    		System.out.println(">>>> "+st);
				    	}
				    	
				    	
				    	accountBase.setCustomerId(values[0]);
				    	accountBase.setVertical(values[1]);
				    	accountBase.setAccountName(values[2]);
				    	if(null!= values[3] && !values[3].equals("")) {
				    		accountBase.setBfd(Double.valueOf(values[3]));
				    	}if(null!= values[4] && !values[4].equals("")) {
				    		accountBase.setBtb(Double.valueOf(values[4]));
				    	}if(null!= values[5] && !values[5].equals("")) {
				    		accountBase.setBtm(Double.valueOf(values[5]));
				    	}if(null!= values[6] && !values[6].equals("")) {
				    		accountBase.setNbl(Double.valueOf(values[6]));
				    	}
				    		
				    	accountBase.setCompetencyManagerId(values[7]);
				    	accountBase.setCompetencyManager(values[8]);
				    	accountBase.setPrimaryLocation(values[9]);
				    	accountBase.setOdcDetails(values[10]);
				    	accountBase.setTeckStack(values[11]);
				    	accountBase.setCreatedDate(new Date());
				    	
				    	listAccountBase.add(accountBase);
				    }
				    
				    bulkAccountBaseService.addAccountBaseBulk(listAccountBase);
		    }
		    
		}
	}*/
	
	@RequestMapping(value = "/downloadBulkAccountBaseFile/{bulkID}", method = RequestMethod.GET)
	public void downloadBulkFile(@PathVariable(value = "bulkID", required = true)
			long bulkID, HttpServletResponse response) throws IOException {

		BulkAccountBase bulkAccountBase = bulkAccountBaseService.fetchBulkAccountBase(bulkID);
		OutputStream outStream = null;
		try {
			if(bulkAccountBase != null) {
				outStream = response.getOutputStream();
				response.setHeader("Content-Disposition", "attachment; filename=\"" + bulkAccountBase.getFileName() +"\"");
				response.setContentType(bulkAccountBase.getContentType());
				FileCopyUtils.copy(bulkAccountBase.getFileData(), response.getOutputStream());				
			}

		} catch (IOException ie) {
			logger.error("downloadBulkFile IOException  ::  "+ie.getMessage());
		} catch(Exception e) {
			logger.error("downloadBulkFile Exception  ::  "+e.getMessage());
		} finally {
			outStream.flush();
			outStream.close();
		}

	}
	
	/*@RequestMapping(value = "/bulkAccountBase/{bulkID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBulkRequest(@PathVariable("bulkID") Long bulkID, UriComponentsBuilder ucBuilder) {
		bulkAccountBaseService.deleteAccountBase(bulkID);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(bulkAppRequest.getName()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/
	
	@RequestMapping(value = "/bulkAccountBase/{bulkID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBulkRequest(@PathVariable("bulkID") Long bulkID, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		BulkAccountBase bulkAccountBase = bulkAccountBaseService.fetchBulkAccountBase(bulkID);
		if(null!=bulkAccountBase) {
			bulkAccountBaseService.deleteAccountBase(bulkAccountBase);
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/bulkAccountBase/{bulkID}", method = RequestMethod.GET)
	public BulkAccountBase loadBulkAccountBaseById(@PathVariable("bulkID") long bulkID) {
		logger.info("inside loadBulkAccountBaseById accountBaseId  ::  "+bulkID);
		BulkAccountBase bulkAccountBase = bulkAccountBaseService.fetchBulkAccountBase(bulkID);
		return bulkAccountBase;
	}
}
