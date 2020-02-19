package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPERVISOR_ASSOCIATE_MAPPING")
public class SupervisorAssociateMapping {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ASSOCIATE_ID")
	private String associateId;
	
	@Column(name = "ASSOCIATE_NAME")
	private String associateName;
	
	@Column(name = "GRADE")
	private String grade;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "REGION")
	private String region;
	
	@Column(name = "SUPERVISOR_ID")
	private String supervisorId;
	
	@Column(name = "SUPERVISOR_NAME")
	private String supervisorName;

	public SupervisorAssociateMapping() {
		super();
	}

	public SupervisorAssociateMapping(Long id, String associateId, String associateName, String grade, String location,
			String region, String supervisorId, String supervisorName) {
		super();
		this.id = id;
		this.associateId = associateId;
		this.associateName = associateName;
		this.grade = grade;
		this.location = location;
		this.region = region;
		this.supervisorId = supervisorId;
		this.supervisorName = supervisorName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
