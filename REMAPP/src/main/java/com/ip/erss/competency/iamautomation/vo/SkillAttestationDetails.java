package com.ip.erss.competency.iamautomation.vo;

import java.util.Date;
import java.util.List;

import com.ip.erss.competency.iamautomation.model.SkillDetails;

public class SkillAttestationDetails {

	private Long skillAttestationId;
	
	private String associateId;
	
	private String associateName;
	
	private String grade;
	
	private String location;
	
	private String region;
	
	private String supervisorId;
	
	private String supervisorName;
	
	private Date createdDate;
	
	private List<SkillDetails> skillDetails;
	
	public Long getSkillAttestationId() {
		return skillAttestationId;
	}

	public void setSkillAttestationId(Long skillAttestationId) {
		this.skillAttestationId = skillAttestationId;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<SkillDetails> getSkillDetails() {
		return skillDetails;
	}

	public void setSkillDetails(List<SkillDetails> skillDetails) {
		this.skillDetails = skillDetails;
	}
		
}
