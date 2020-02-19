package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.lang.Double;

@Entity
@Table(name = "BOTS")
public class Bots {
	
	@Id
	@GeneratedValue
	@Column(name = "BOT_ID")
	private long botId;
	
	@Column(name = "BOT_NAME")
	private String botName;
	
	@Column(name = "AUTOMATION_SOLUTION")
	private String automationSolution;
	
	@Column(name = "PROJECT_ID")
	private String projectId;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "BOT_CATEGORY")
	private String botCategory;
	
	@Column(name = "BOT_CREATED_BY")
	private String botCreatedBy;
	
	@Lob
	@Column(name = "USE_CASE_OR_PURPOSE")
	private String useCaseOrPurpose;
	
	@Column(name = "MANUAL_EFFORT_BEFORE_BOT")
	private Double manualEffortBeforeBot;
	
	@Column(name = "MANUAL_EFFORT_AFTER_BOT")
	private Double manualEffortAfterBot;
	
	@Column(name = "Support_Or_Engineering")
	private String supportOrEngineering;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "COMP_MANAGER")
	private String compManager;
	
	@Column(name = "COMPETENCY")
	private String competency;
	
	@Column(name = "AUTOMATION_SOLUTION_ACTUAL")
	private String automationSolutionActual;
	
	@Column(name = "ACCOUNT_BASE_ID")
	private Long accountBaseId;
	
	@Column(name = "SECURITY_DOMAIN")
	private String securityDomain;
	
	@Column(name = "SERVICE_CATEGORY")
	private String serviceCategory;
	
	@Column(name = "SERVICE")
	private String service;
	
	@Column(name = "FUNCTION_TYPE")
	private String functionType;
	
	@Column(name = "OPERATIONAL_METRICS")
	private String operationalMetrics;

	public Bots() {
		super();
	}

	public Bots(long botId, String botName, String automationSolution, String projectId, String projectName,
			String customerName, String botCategory, String botCreatedBy, String useCaseOrPurpose,
			Double manualEffortBeforeBot, Double manualEffortAfterBot, String supportOrEngineering, Date createdDate,
			String compManager, String competency, String automationSolutionActual, Long accountBaseId,
			String securityDomain, String serviceCategory, String service, String functionType,
			String operationalMetrics) {
		super();
		this.botId = botId;
		this.botName = botName;
		this.automationSolution = automationSolution;
		this.projectId = projectId;
		this.projectName = projectName;
		this.customerName = customerName;
		this.botCategory = botCategory;
		this.botCreatedBy = botCreatedBy;
		this.useCaseOrPurpose = useCaseOrPurpose;
		this.manualEffortBeforeBot = manualEffortBeforeBot;
		this.manualEffortAfterBot = manualEffortAfterBot;
		this.supportOrEngineering = supportOrEngineering;
		this.createdDate = createdDate;
		this.compManager = compManager;
		this.competency = competency;
		this.automationSolutionActual = automationSolutionActual;
		this.accountBaseId = accountBaseId;
		this.securityDomain = securityDomain;
		this.serviceCategory = serviceCategory;
		this.service = service;
		this.functionType = functionType;
		this.operationalMetrics = operationalMetrics;
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

	public String getAutomationSolution() {
		return automationSolution;
	}

	public void setAutomationSolution(String automationSolution) {
		this.automationSolution = automationSolution;
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

	public String getBotCategory() {
		return botCategory;
	}

	public void setBotCategory(String botCategory) {
		this.botCategory = botCategory;
	}

	public String getBotCreatedBy() {
		return botCreatedBy;
	}

	public void setBotCreatedBy(String botCreatedBy) {
		this.botCreatedBy = botCreatedBy;
	}

	public String getUseCaseOrPurpose() {
		return useCaseOrPurpose;
	}

	public void setUseCaseOrPurpose(String useCaseOrPurpose) {
		this.useCaseOrPurpose = useCaseOrPurpose;
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

	public String getSupportOrEngineering() {
		return supportOrEngineering;
	}

	public void setSupportOrEngineering(String supportOrEngineering) {
		this.supportOrEngineering = supportOrEngineering;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCompManager() {
		return compManager;
	}

	public void setCompManager(String compManager) {
		this.compManager = compManager;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public String getAutomationSolutionActual() {
		return automationSolutionActual;
	}

	public void setAutomationSolutionActual(String automationSolutionActual) {
		this.automationSolutionActual = automationSolutionActual;
	}

	public Long getAccountBaseId() {
		return accountBaseId;
	}

	public void setAccountBaseId(Long accountBaseId) {
		this.accountBaseId = accountBaseId;
	}

	public String getSecurityDomain() {
		return securityDomain;
	}

	public void setSecurityDomain(String securityDomain) {
		this.securityDomain = securityDomain;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getOperationalMetrics() {
		return operationalMetrics;
	}

	public void setOperationalMetrics(String operationalMetrics) {
		this.operationalMetrics = operationalMetrics;
	}
	
}
