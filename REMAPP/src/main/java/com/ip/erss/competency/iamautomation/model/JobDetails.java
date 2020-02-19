/**
 * 
 */
package com.ip.erss.competency.iamautomation.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 134930
 *
 */
public class JobDetails {

	Long apiRequestID;
	String jobName;
	String jobDescription;
	
	String jobKey= null;
	String api;
	String jobParameter;
	String jobClass;
	List<ApiRequest> apiRequestDetails;
	Map<String, String> jobDataMap = new HashMap<String, String>(0);
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return the jobKey
	 */
	public String getJobKey() {
		return jobKey;
	}
	/**
	 * @param jobKey the jobKey to set
	 */
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
	/**
	 * @return the api
	 */
	public String getApi() {
		return api;
	}
	/**
	 * @param api the api to set
	 */
	public void setApi(String api) {
		this.api = api;
	}
	/**
	 * @return the jobParameter
	 */
	public String getJobParameter() {
		return jobParameter;
	}
	/**
	 * @param jobParameter the jobParameter to set
	 */
	public void setJobParameter(String jobParameter) {
		this.jobParameter = jobParameter;
	}
	/**
	 * @return the jobClass
	 */
	public String getJobClass() {
		return jobClass;
	}
	/**
	 * @param jobClass the jobClass to set
	 */
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	/**
	 * @return the jobDataMap
	 */
	public Map<String, String> getJobDataMap() {
		return jobDataMap;
	}
	/**
	 * @param jobDataMap the jobDataMap to set
	 */
	public void setJobDataMap(Map<String, String> jobDataMap) {
		this.jobDataMap = jobDataMap;
	}
	/**
	 * @return the apiRequestDetails
	 */
	public List<ApiRequest> getApiRequestDetails() {
		return apiRequestDetails;
	}
	/**
	 * @param apiRequestDetails the apiRequestDetails to set
	 */
	public void setApiRequestDetails(List<ApiRequest> apiRequestDetails) {
		this.apiRequestDetails = apiRequestDetails;
	}
	/**
	 * @return the apiRequestID
	 */
	public Long getApiRequestID() {
		return apiRequestID;
	}
	/**
	 * @param apiRequestID the apiRequestID to set
	 */
	public void setApiRequestID(Long apiRequestID) {
		this.apiRequestID = apiRequestID;
	}
	

}
