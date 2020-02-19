package com.ip.erss.competency.iamautomation.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ip.erss.competency.iamautomation.model.ApiRequest;
import com.ip.erss.competency.iamautomation.model.Headers;
import com.ip.erss.competency.iamautomation.service.ApiRequestService;
import com.ip.erss.competency.iamautomation.utils.AppConstants;
import com.ip.erss.competency.iamautomation.utils.EncryptDecryptUtil;

@RestController
@EnableAutoConfiguration
public class ApiRequestController {

	@Autowired
	private ApiRequestService apiRequestService;

	private static final Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

	@RequestMapping(value = "/api/", method = RequestMethod.POST)
	public ResponseEntity<Void> createApi(@RequestBody ApiRequest apiRequest, UriComponentsBuilder ucBuilder) {
		logger.info("Creating API register ID --> should be null " + apiRequest.getId());

		logger.info("Creating API register  getApiName -->" + apiRequest.getApiName());
		logger.info("Creating API register  Header list -->" + apiRequest.getHeaderList());
		if (apiRequest.getHeaderList() != null) {
			logger.info("Creating API register  Header list" + apiRequest.getHeaderList().size());
			apiRequest.setHeaderList(getEncryptedHeaderList(apiRequest.getHeaderList()));
		}

		apiRequestService.create(apiRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(apiRequest.getApiName()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/", method = RequestMethod.GET)
	public List<ApiRequest> loadAll() throws Exception {
		logger.info("Api request received for loadAllAPI");
		List<ApiRequest> apiRequests = new ArrayList<ApiRequest>();


			apiRequests = apiRequestService.loadAll();
			apiRequests = getDeccryptedApiList(apiRequests);
			logger.info("Api request received for loadAllAPI" + apiRequests.size());


		return apiRequests;
	}

	// ------------------- Update a API Register
	// --------------------------------------------------------

	@RequestMapping(value = "/api/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiRequest> updateApiregister(@PathVariable("id") Long id,
			@RequestBody ApiRequest apiRequest) {

		logger.info("Updating API Register  " + id);

		ApiRequest currentAPI = apiRequestService.getapiRequest(id);
		if (currentAPI == null) {
			return new ResponseEntity<ApiRequest>(HttpStatus.NOT_FOUND);
		}

		currentAPI.setApiName(apiRequest.getApiName());
		currentAPI.setDesc(apiRequest.getDesc());
		currentAPI.setEndPointUrl(apiRequest.getEndPointUrl());
		currentAPI.setHeaderList(getEncryptedHeaderList(apiRequest.getHeaderList()));
		apiRequestService.updateApi(currentAPI);
		logger.info("API Registry details update " + id + "success");
		return new ResponseEntity<ApiRequest>(currentAPI, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiRequest> getAPIDetailsById(@PathVariable("id") long id) {
		logger.info("Api request received for getAPIDetails");
		ApiRequest currentAPI = apiRequestService.getapiRequest(id);
		if (currentAPI == null) {
			logger.info("there is no records found " + id);
			return new ResponseEntity<ApiRequest>(HttpStatus.NOT_FOUND);
		} else {
			currentAPI.setHeaderList(getDecryptedHeaderList(currentAPI.getHeaderList()));
		}
		return new ResponseEntity<ApiRequest>(currentAPI, HttpStatus.OK);
	}

	// ------------------- Delete a API Register
	// --------------------------------------------------------

	@RequestMapping(value = "/api/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ApiRequest> deleteApi(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id " + id);

		ApiRequest currentAPI = apiRequestService.getapiRequest(id);
		if (currentAPI == null) {
			logger.info("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<ApiRequest>(HttpStatus.NOT_FOUND);
		}
		apiRequestService.deleteApi(id);
		return new ResponseEntity<ApiRequest>(HttpStatus.NO_CONTENT);
	}

	// -------------- get API Details base on API name--------

	@RequestMapping(value = "/api/getByapiName/{apiName}", method = RequestMethod.GET)
	public ResponseEntity<ApiRequest> getAPIDetails(@PathVariable("apiName") String apiName) {
		logger.info("Api request received for getAPIDetails");
		ApiRequest currentAPI = null;
		if(apiName != null) {
			currentAPI = apiRequestService.getapiByName(apiName);
		}

		if (currentAPI == null) {
			//logger.info("there is no records found " + apiName);
			return new ResponseEntity<ApiRequest>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ApiRequest>(currentAPI, HttpStatus.OK);
	}

	// ------------------- Delete All API
	// registers--------------------------------------------------------

	@RequestMapping(value = "/deleteApis/", method = RequestMethod.DELETE)
	public ResponseEntity<ApiRequest> deleteAllAPIs() {
		logger.info("Deleting All API's");
		List<ApiRequest> apiRequests = new ArrayList<ApiRequest>();
		apiRequests = apiRequestService.loadAll();
		for (ApiRequest apidata : apiRequests) {

			apiRequestService.deleteAllApi(apidata);
		}

		return new ResponseEntity<ApiRequest>(HttpStatus.NO_CONTENT);
	}

	public static List<Headers> getEncryptedHeaderList(List<Headers> headerList) {

		List<Headers> encryptHeaderList = new ArrayList<Headers>();

		for(Headers headers : headerList) {
			Headers encryptHeader = new Headers();
			encryptHeader.setHeaderId(headers.getHeaderId());
			encryptHeader.setKey(headers.getKey());
			if(headers.getKey().equalsIgnoreCase(AppConstants.USER_PASS)) {
				encryptHeader.setValue(EncryptDecryptUtil.doEncrypt(headers.getValue()));
			} else {
				encryptHeader.setValue(headers.getValue());
			}
			encryptHeaderList.add(encryptHeader);
		}

		return encryptHeaderList;
	}

	public static List<ApiRequest> getDeccryptedApiList(List<ApiRequest> apiRequestList) {

		List<ApiRequest> encryptApiList = new ArrayList<ApiRequest>();

		for(ApiRequest apiReq : apiRequestList) {
			ApiRequest apiRequest = new ApiRequest();
			apiRequest.setId(apiReq.getId());
			apiRequest.setApiName(apiReq.getApiName());
			apiRequest.setDesc(apiReq.getDesc());
			apiRequest.setEndPointUrl(apiReq.getEndPointUrl());
			apiRequest.setHeaderList(getDecryptedHeaderList(apiReq.getHeaderList()));
			encryptApiList.add(apiRequest);
		}

		return encryptApiList;
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
