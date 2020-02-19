package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import javax.naming.NamingException;

import com.ip.erss.competency.iamautomation.model.SkillAttestation;


public interface AdUtilService {

	List<SkillAttestation> getSupervisorDetails(String userName);

	List<SkillAttestation> getSupervisorDetails(List<String> supervisorList) throws NamingException;
	
}
