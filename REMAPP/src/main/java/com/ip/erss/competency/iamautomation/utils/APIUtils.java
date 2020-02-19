package com.ip.erss.competency.iamautomation.utils;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.ip.erss.competency.iamautomation.model.ApiRequest;

public class APIUtils {

	private static final Logger logger = LoggerFactory.getLogger(APIUtils.class);
	
	public ApiRequest fetchApiDetails(Long apiID, String serverPort) {
		RestTemplate restTemplate = new RestTemplate();
		ApiRequest apiRequest = null;
		try {
			apiRequest = restTemplate.getForObject(AppConstants.API_REST_SERVICE_URI.replace(AppConstants.DOLLAR, serverPort)+ "/" + apiID, ApiRequest.class);
			logger.info("======Testing APIheaders=========="+ apiRequest.getHeaderList().size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return apiRequest;
		
		
	}


	 /* POST */
    public boolean invokeAcknowledgementAPI(ApiRequest apiRequest ) {
    	logger.info("Testing create User API----------");
        String REST_SERVICE_URI=apiRequest.getEndPointUrl();
        RestTemplate restTemplate = new RestTemplate();
        logger.info("====apiRequest details in ====="+apiRequest.getApiName());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI, apiRequest, ApiRequest.class);
       	return false;
    }

	/*public String getAipHears(ApiRequest apiRequest){
		String apiEndpointUrl= apiRequest.getEndPointUrl();
		String userName="";
		 String userValue="";
		 String userPass="";
		String pwdValue =""	;
		//String json = "{\"name\":\"mkyong\", \"age\":29}";
		String input="";
		List<Headers> apiHeaderList = apiRequest.getHeaderList();
        for (Headers headers : apiHeaderList) {
               if(headers.getKey().equalsIgnoreCase(AppConstants.USER_NAME)){
               userName= headers.getKey();
               userValue = headers.getValue();
               if(headers.getKey().equalsIgnoreCase(AppConstants.PASSWORD)){
                    userPass= headers.getKey();
                    pwdValue = headers.getValue();
               }
        }


	}
		return input;*/


}
