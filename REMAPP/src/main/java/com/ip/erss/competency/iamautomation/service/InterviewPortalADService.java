package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import javax.naming.NamingException;

import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;


public interface InterviewPortalADService {

	InterviewPortalPanel getEmployeeDetails(String employeeid, String empName);
	
	/**
	 * @param employeeid
	 * @return String 
	 * Comments - Added on 24-09-2018 to get Email ID for an Employee
	 */
	public String getEmployeeEmail(String employeeid);
}
