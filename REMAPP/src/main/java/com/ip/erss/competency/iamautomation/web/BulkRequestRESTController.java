package com.ip.erss.competency.iamautomation.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.model.BulkAppRequest;
import com.ip.erss.competency.iamautomation.model.Headers;
import com.ip.erss.competency.iamautomation.service.BulkRequestService;
import com.ip.erss.competency.iamautomation.utils.AppConstants;
import com.ip.erss.competency.iamautomation.utils.EncryptDecryptUtil;


@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ip.erss.competency.iamautomation.service,com.ip.erss.competency.sp.service.impl,"
		+ "com.ip.erss.competency.iamautomation.dao,com.ip.erss.competency.iamautomation.dao.impl,com.ip.erss.competency.sp.service.impl")
public class BulkRequestRESTController {

	private static final Logger logger = LoggerFactory.getLogger(BulkRequestRESTController.class);

	@Autowired
	BulkRequestService bulkRequestService;
	

	@RequestMapping(value="/createNewPageRedirect/", method = RequestMethod.POST)
	public ResponseEntity<Void> redirectToNewPage() {
		logger.info("Inside createNewPageRedirect -->>>");
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     UriComponentsBuilder b = UriComponentsBuilder.fromPath("/createNewRequest");
	             UriComponents uriComponents =  b.queryParam("responseDTOList", "empty").build();
	     return new ResponseEntity<Void>(headers,HttpStatus.FOUND);
	}

	@RequestMapping(value = "/bulkRequest/", method = RequestMethod.POST)
	public void createBulkRequest(@RequestParam(value="file") MultipartFile file, @RequestParam(value="name") String name,  @RequestParam(value="description") String description) throws IOException {
		
		if(null != file) {
			InputStream inputStream=file.getInputStream();
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		    System.out.println("Inside Post Method");
		try {
			BulkAppRequest bulkAppRequest = new BulkAppRequest();
			bulkAppRequest.setFileName(file.getOriginalFilename());
			bulkAppRequest.setName(name);
			bulkAppRequest.setDescription(description);
			bulkAppRequest.setCreatedDate(new Date(System.currentTimeMillis()));
			bulkAppRequest.setStatus("Pending");
			bulkAppRequest.setFileData(file.getBytes());
			bulkAppRequest.setContentType(file.getContentType());
			bulkRequestService.createBulkRequest(bulkAppRequest);

			List<AssociateDetails> associateDetailsList = new ArrayList<AssociateDetails>();

		    ObjectMapper mapper = new ObjectMapper();
		    String fileRecord;
		    String header = "";
		    String headers[] = null;
		    int headerSplit = 6;
		    String keyArray[] = null;
		    if(null != bufferedReader) {
		    	 if(!(header = bufferedReader.readLine()).isEmpty() && null != header ) {
				     headers = header.split("~");
				     keyArray = Arrays.copyOfRange(headers, headerSplit, headers.length);
		    	 }
		    System.out.println("Before While loop");
		    while ((fileRecord = bufferedReader.readLine()) != null )
		    {	 

		    	 AssociateDetails associate = new AssociateDetails();
		    	 String[] values = fileRecord.split("~");
		    	 //Long id = Long.parseLong(values[0]);
		    	 associate.setAssociateID(values[0]);
		    	 associate.setFirstName(values[1]);
		    	 associate.setLastName(values[2]);
		    	 associate.setVertical(values[3]);
		    	 associate.setGrade(values[4]);
		    	 associate.setKeySkills(values[5]);
		    	 associate.setDetailedSkills(values[6]);
		    	 associate.setLocation(values[9]);
		    	 int avilableTime = Integer.parseInt(values[7]);
		    	 associate.setAvailableTime(avilableTime);
		    	 associate.setContactNumber(values[8]);
		    	 System.out.println("AssociateDetails  ::  "+associate.toString());
		    	 associateDetailsList.add(associate);
	    }
		
		    bulkRequestService.createBulkAssociate(associateDetailsList);
		}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception createBulkRequest  ::  "+e.getMessage());
		} finally {
			inputStream.close();
			bufferedReader.close();
		}
		}
		}


	@RequestMapping(value = "/updateBulkRequest/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBulkRequest(@RequestBody BulkAppRequest bulkAppRequest) {

		bulkRequestService.updateBulkRequest(bulkAppRequest);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/bulkRequest/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBulkRequest(@RequestParam(value="file") MultipartFile file,@RequestParam(value="bulkRequestID") Long bulkRequestID, @RequestParam(value="name") String name,  @RequestParam(value="description") String description) throws IOException {
		BulkAppRequest bulkAppRequest = bulkRequestService.fetchBulkRequest(bulkRequestID);
		bulkAppRequest.setFileName(file.getOriginalFilename());
		bulkAppRequest.setName(name);
		bulkAppRequest.setDescription(description);
		bulkAppRequest.setFileData(file.getBytes());
		bulkAppRequest.setUpdateDate(new Date(System.currentTimeMillis()));
		bulkAppRequest.setContentType(file.getContentType());
		//bulkAppRequest.setStatus("Pending");
		bulkRequestService.updateBulkRequest(bulkAppRequest);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(bulkAppRequest.getName()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/bulkRequest/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBulkRequest(@PathVariable("id") Long bulkappRequestID, UriComponentsBuilder ucBuilder) {

		//bulkAppRequest.setStatus("Pending");
		bulkRequestService.deleteBulkRequest(bulkappRequestID);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(bulkAppRequest.getName()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/requestShowAllBulk/", method = RequestMethod.GET)
	public List<BulkAppRequest> fetchAllBulkAppRequest() {
		logger.info("Going to fetch All App Request ");
		List<BulkAppRequest> bulkappRequestList = bulkRequestService.fetchAllBulkRequest();
		return bulkappRequestList;
	}


	@RequestMapping(value = "/bulkrequestByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<BulkAppRequest> fetchBulkAppRequestByID(@PathVariable("id") long id) {
		logger.info("Going to fetch Bulk App Request " + id);

		BulkAppRequest bulkAppRequest = bulkRequestService.fetchBulkRequest(id);

		if (bulkAppRequest == null) {
			logger.info("there is no records found " + id);
			return new ResponseEntity<BulkAppRequest>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BulkAppRequest>(bulkAppRequest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bulkrequestByStatus/{status}", method = RequestMethod.GET)
	public List<BulkAppRequest> fetchBulkAppRequestByStatus(@PathVariable("status") String status) {
		logger.info("Going to fetch Bulk App Request " + status);
		List<BulkAppRequest> bulkAppRequestList = bulkRequestService.fetchAllBulkRequestbyStatus(status);
		return bulkAppRequestList;
	}
	
	@RequestMapping(value = "/bulkrequestByIdStatus/{id}/{status}", method = RequestMethod.GET)
	public List<BulkAppRequest> fetchBulkAppRequestByIdStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
		logger.info("Going to fetch Bulk App Request " + status);
		List<BulkAppRequest> bulkAppRequestList = bulkRequestService.fetchAllBulkRequestbyIdStatus(id, status);
		return bulkAppRequestList;
	}
	
	@RequestMapping(value = "/downloadBulkFile/{bulkAppReqId}", method = RequestMethod.GET)
	public void downloadBulkFile(@PathVariable(value = "bulkAppReqId", required = true)
			long bulkAppReqId, HttpServletResponse response) throws IOException {

		BulkAppRequest bulkAppRequest = bulkRequestService.fetchBulkRequest(bulkAppReqId);
		OutputStream outStream = null;
		try {
			if(bulkAppRequest != null) {
				outStream = response.getOutputStream();
				response.setHeader("Content-Disposition", "attachment; filename=\"" + bulkAppRequest.getFileName() +"\"");
				response.setContentType(bulkAppRequest.getContentType());
				FileCopyUtils.copy(bulkAppRequest.getFileData(), response.getOutputStream());
				
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
	
	private Map<String, String> prepareInputDataMap(List<Headers> headerList) throws Exception {

		Map<String, String> acknowledgementMap = new HashMap<String, String>();
		for (Headers headers : headerList) {
			acknowledgementMap.put(headers.getKey(),headers.getValue());
		}
		List<String> acknowledgementInputData = new ArrayList<String>(10);
		acknowledgementInputData.add(AppConstants.REMEDY_USER_NAME);
		/*acknowledgementInputData.add(AppConstants.REMEDY_PASSWORD);*/
		acknowledgementInputData.add(AppConstants.EMAIL_TEMPLATE);
		//acknowledgementInputData.add(AppConstants.DEFAULT_ASSIGNEE);
		//acknowledgementInputData.add(AppConstants.ASSIGNEMENT_GROUP);

		for (String inputData : acknowledgementInputData) {
			if(!acknowledgementMap.containsKey(inputData)){
				throw new Exception("ERROR_001- Insuffecient InputData .. please add input data in API headers");
			}
		}

		//neetd perofrm the validation

		return acknowledgementMap;
	}
	
	public static List<Headers> getDecryptedHeaderList(List<Headers> headerList) {

		List<Headers> encryptHeaderList = new ArrayList<Headers>();

		for(Headers headers : headerList) {
			Headers encryptHeader = new Headers();
			encryptHeader.setHeaderId(headers.getHeaderId());
			encryptHeader.setKey(headers.getKey());
			if(headers.getKey().equalsIgnoreCase(AppConstants.USER_PASS)) {
				encryptHeader.setValue(EncryptDecryptUtil.doDecrypt(headers.getValue()));
			} else {
				encryptHeader.setValue(headers.getValue());
			}
			encryptHeaderList.add(encryptHeader);
		}

		return encryptHeaderList;
	}
	
}
