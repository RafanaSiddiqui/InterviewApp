package com.ip.erss.competency.iamautomation.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Tracker")
public class Tracker {
	
	@Id
	@GeneratedValue
	@Column(name = "TRACKER_ID")
	private Long trackerId;
	
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	
	@Column(name = "COMP_MANAGER")
	private String compManager;
	
	@Column(name = "PROJECT_ID")
	private String projectId;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "ACCOUNT_BASE_ID")
	private Long accountBaseId;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID", referencedColumnName = "TRACKER_ID")
   	@JsonProperty("automationDetails")
	private List<AutomationDetails> automationDetails;

	public Tracker() {
		super();
	}

	public Tracker(Long trackerId, String accountName, String compManager, String projectId, String projectName,
			List<AutomationDetails> automationDetails, Date createdDate, Long accountBaseId) {
		super();
		this.trackerId = trackerId;
		this.accountName = accountName;
		this.compManager = compManager;
		this.projectId = projectId;
		this.projectName = projectName;
		this.automationDetails = automationDetails;
		this.createdDate = createdDate;
		this.accountBaseId = accountBaseId;
	}

	public Long getTrackerId() {
		return trackerId;
	}

	public void setTrackerId(Long trackerId) {
		this.trackerId = trackerId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompManager() {
		return compManager;
	}

	public void setCompManager(String compManager) {
		this.compManager = compManager;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<AutomationDetails> getAutomationDetails() {
		return automationDetails;
	}

	public void setAutomationDetails(List<AutomationDetails> automationDetails) {
		this.automationDetails = automationDetails;
	}

	public Long getAccountBaseId() {
		return accountBaseId;
	}

	public void setAccountBaseId(Long accountBaseId) {
		this.accountBaseId = accountBaseId;
	}
	
}
