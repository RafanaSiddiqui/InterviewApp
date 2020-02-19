package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOT_MEASUREMENTS")
public class BotMeasurements {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@Column(name = "BOT_ID")
	private long botId;
	
	@Column(name = "BOT_NAME")
	private String botName;
	
	@Column(name = "PROJECT_ID")
	private String projectId;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "MANUAL_EFFORT_BEFORE_BOT")
	private Double manualEffortBeforeBot;
	
	@Column(name = "MANUAL_EFFORT_AFTER_BOT")
	private Double manualEffortAfterBot;
	
	@Column(name = "NO_OF_EXECUTIONS")
	private Double noOfExecutions;
	
	@Column(name = "NO_OF_FAILURES")
	private Double noOfFailures;
	
	@Column(name = "TOTAL_HRS_SAVED")
	private Double totalHrsSaved;
	
	@Column(name = "DATA_ENTERED_BY")
	private String dataEnteredBy;
	
	@Column(name = "COMP_MANAGER")
	private String compManager;
	
	@Column(name = "CREATED_MONTH")
	private Date createdMonth;
	
	@Column(name = "COMPETENCY")
	private String competency;
	
	@Column(name = "ACCOUNT_BASE_ID")
	private Long accountBaseId;

	public BotMeasurements() {
		super();
	}

	public BotMeasurements(long id, long botId, String botName, String projectId, String projectName,
			String customerName, Date createdDate, Double manualEffortBeforeBot, Double manualEffortAfterBot,
			Double noOfExecutions, Double noOfFailures, Double totalHrsSaved, String dataEnteredBy, String compManager,
			Date createdMonth, String competency, Long accountBaseId) {
		super();
		this.id = id;
		this.botId = botId;
		this.botName = botName;
		this.projectId = projectId;
		this.projectName = projectName;
		this.customerName = customerName;
		this.createdDate = createdDate;
		this.manualEffortBeforeBot = manualEffortBeforeBot;
		this.manualEffortAfterBot = manualEffortAfterBot;
		this.noOfExecutions = noOfExecutions;
		this.noOfFailures = noOfFailures;
		this.totalHrsSaved = totalHrsSaved;
		this.dataEnteredBy = dataEnteredBy;
		this.compManager = compManager;
		this.createdMonth = createdMonth;
		this.competency = competency;
		this.accountBaseId = accountBaseId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBotId() {
		return botId;
	}

	public void setBotId(long botId) {
		this.botId = botId;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getManualEffortBeforeBot() {
		return manualEffortBeforeBot;
	}

	public void setManualEffortBeforeBot(Double manualEffortBeforeBot) {
		this.manualEffortBeforeBot = manualEffortBeforeBot;
	}

	public Double getManualEffortAfterBot() {
		return manualEffortAfterBot;
	}

	public void setManualEffortAfterBot(Double manualEffortAfterBot) {
		this.manualEffortAfterBot = manualEffortAfterBot;
	}

	public Double getNoOfExecutions() {
		return noOfExecutions;
	}

	public void setNoOfExecutions(Double noOfExecutions) {
		this.noOfExecutions = noOfExecutions;
	}

	public Double getNoOfFailures() {
		return noOfFailures;
	}

	public void setNoOfFailures(Double noOfFailures) {
		this.noOfFailures = noOfFailures;
	}

	public Double getTotalHrsSaved() {
		return totalHrsSaved;
	}

	public void setTotalHrsSaved(Double totalHrsSaved) {
		this.totalHrsSaved = totalHrsSaved;
	}

	public String getDataEnteredBy() {
		return dataEnteredBy;
	}

	public void setDataEnteredBy(String dataEnteredBy) {
		this.dataEnteredBy = dataEnteredBy;
	}

	public String getCompManager() {
		return compManager;
	}

	public void setCompManager(String compManager) {
		this.compManager = compManager;
	}

	public Date getCreatedMonth() {
		return createdMonth;
	}

	public void setCreatedMonth(Date createdMonth) {
		this.createdMonth = createdMonth;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public Long getAccountBaseId() {
		return accountBaseId;
	}

	public void setAccountBaseId(Long accountBaseId) {
		this.accountBaseId = accountBaseId;
	}
	
}
