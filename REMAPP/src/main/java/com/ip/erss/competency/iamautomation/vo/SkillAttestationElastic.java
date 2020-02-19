package com.ip.erss.competency.iamautomation.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.ip.erss.competency.iamautomation.model.SkillDetails;

public class SkillAttestationElastic {

	private Long id;
	
	private String associateId;
	
	private String associateName;
	
	private String grade;
	
	private String location;
	
	private String region;
	
	private String supervisorId;
	
	private String supervisorName;
	
	private Date createdDate;
	
	private String skill;
	
	private String skillActual;
	
	private String proficiency;
	
	private String selectSkill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSkillActual() {
		return skillActual;
	}

	public void setSkillActual(String skillActual) {
		this.skillActual = skillActual;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getSelectSkill() {
		return selectSkill;
	}

	public void setSelectSkill(String selectSkill) {
		this.selectSkill = selectSkill;
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
		
}
