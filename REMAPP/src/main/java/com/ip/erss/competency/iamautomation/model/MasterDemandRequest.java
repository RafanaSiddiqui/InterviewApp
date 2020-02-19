package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MASTER_DEMAND_REQUEST")
public class MasterDemandRequest implements java.io.Serializable {

	private static final long serialVersionUID = 639538031811402577L;

	/**
	 * 
	 */
	public MasterDemandRequest() {
		super();
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long demandRequestId;
	
	@Column(name = "SO_ID")
	private String soId;
	
	@Column(name = "BILLABILITY")
	private String billability;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQ_START_DATE")
	private Date reqStartDate;
	
	@Column(name = "PRIORITY")
	private String priority;
	
	@Column(name = "GRADE")
	private String grade;
	
	@Column(name = "PDP")
	private String pdp;
	
	@Column(name = "VERTICAL")
	private String vertical;
	
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	
	@Column(name = "OFF_SHORE")
	private String offShore;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "PROJECT_ID")
	private String projectID; 
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "FLAGGED_FOR_HIRE")
	private boolean flaggedForHire;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FLAGGED_FOR_HIRE_DATE")
	private Date flaggedForHireDate;
	
	@Column(name = "TECHNICAL_SKILLS")
	private String technicalSkills;
	
	@Column(name = "REQUESTOR_NAME")
	private String requestorName;
	
	@Column(name = "REQUESTOR_ID")
	private String requestorID;
	
	@Column(name = "COMPETENCY")
	private String competency;
	
	@Column(name = "status")
	private String status;
	
	public MasterDemandRequest(Long demandRequestId, String soId, String billability, Date reqStartDate, 
			String priority, String grade, String pdp, String vertical, String accountName, String offShore, String city, 
			String country, String projectID, String projectName, Date creationDate, boolean flaggedForHire, Date flaggedForHireDate, 
			String technicalSkills, String requestorName, String requestorID, String competency, String status) {
		super();
		this.demandRequestId = demandRequestId;
		this.soId = soId;
		this.billability = billability;
		this.reqStartDate = reqStartDate;
		this.priority = priority;
		this.grade = grade;
		this.pdp = pdp;
		this.vertical = vertical;
		this.accountName = accountName;
		this.offShore = offShore;
		this.city = city;
		this.country = country;
		this.projectID = projectID;
		this.projectName = projectName;
		this.creationDate = creationDate;
		this.flaggedForHire = flaggedForHire;
		this.flaggedForHireDate = flaggedForHireDate;
		this.technicalSkills = technicalSkills;
		this.requestorName = requestorName;
		this.requestorID = requestorID;
		this.competency = competency;
		this.status = status;
		
	}
	
	public Long getDemandRequestId() {
		return demandRequestId;
	}

	public void setDemandRequestId(Long demandRequestId) {
		this.demandRequestId = demandRequestId;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getBillability() {
		return billability;
	}

	public void setBillability(String billability) {
		this.billability = billability;
	}

	public Date getReqStartDate() {
		return reqStartDate;
	}

	public void setReqStartDate(Date reqStartDate) {
		this.reqStartDate = reqStartDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPdp() {
		return pdp;
	}

	public void setPdp(String pdp) {
		this.pdp = pdp;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOffShore() {
		return offShore;
	}

	public void setOffShore(String offShore) {
		this.offShore = offShore;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isFlaggedForHire() {
		return flaggedForHire;
	}

	public void setFlaggedForHire(boolean flaggedForHire) {
		this.flaggedForHire = flaggedForHire;
	}

	public Date getFlaggedForHireDate() {
		return flaggedForHireDate;
	}

	public void setFlaggedForHireDate(Date flaggedForHireDate) {
		this.flaggedForHireDate = flaggedForHireDate;
	}

	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public String getRequestorID() {
		return requestorID;
	}

	public void setRequestorID(String requestorID) {
		this.requestorID = requestorID;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
