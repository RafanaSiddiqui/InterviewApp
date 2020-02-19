package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICES")
public class Services {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SECURITY_DOMAIN_CODE")
	private String securityDomainCode;
	
	@Column(name = "SECURITY_DOMAIN")
	private String securityDomain;
	
	@Column(name = "SERVICE_CATEGORY_CODE")
	private String serviceCategoryCode;
	
	@Column(name = "SERVICE_CATEGORY")
	private String serviceCategory;
	
	@Column(name = "OFFERING_CODE")
	private String offeringCode;
	
	@Column(name = "SERVICE")
	private String service;
	
	@Column(name = "TECK_STACK_TIER_REF_CODE")
	private String teckStackTierRefCode;
	
	@Column(name = "USE_CASE_PRESENT")
	private String useCasePresent;
	
	@Column(name = "SUPPORT_FTE")
	private String supportFTE;
	
	@Column(name = "ENGINEERING_FTE")
	private String engineeringFTE;
	
	@Column(name = "AUTOMATION_POSSIBLE")
	private String automationPossible;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "OPEN_PANEL")
	private boolean openPanel;

	public Services() {
		super();
	}

	public Services(Long id, String securityDomainCode, String securityDomain, String serviceCategoryCode,
			String serviceCategory, String offeringCode, String service, String teckStackTierRefCode,
			String useCasePresent, String supportFTE, String engineeringFTE, String automationPossible,
			Date createdDate, boolean openPanel) {
		super();
		this.id = id;
		this.securityDomainCode = securityDomainCode;
		this.securityDomain = securityDomain;
		this.serviceCategoryCode = serviceCategoryCode;
		this.serviceCategory = serviceCategory;
		this.offeringCode = offeringCode;
		this.service = service;
		this.teckStackTierRefCode = teckStackTierRefCode;
		this.useCasePresent = useCasePresent;
		this.supportFTE = supportFTE;
		this.engineeringFTE = engineeringFTE;
		this.automationPossible = automationPossible;
		this.createdDate = createdDate;
		this.openPanel = openPanel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecurityDomainCode() {
		return securityDomainCode;
	}

	public void setSecurityDomainCode(String securityDomainCode) {
		this.securityDomainCode = securityDomainCode;
	}

	public String getSecurityDomain() {
		return securityDomain;
	}

	public void setSecurityDomain(String securityDomain) {
		this.securityDomain = securityDomain;
	}

	public String getServiceCategoryCode() {
		return serviceCategoryCode;
	}

	public void setServiceCategoryCode(String serviceCategoryCode) {
		this.serviceCategoryCode = serviceCategoryCode;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public String getOfferingCode() {
		return offeringCode;
	}

	public void setOfferingCode(String offeringCode) {
		this.offeringCode = offeringCode;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTeckStackTierRefCode() {
		return teckStackTierRefCode;
	}

	public void setTeckStackTierRefCode(String teckStackTierRefCode) {
		this.teckStackTierRefCode = teckStackTierRefCode;
	}

	public String getUseCasePresent() {
		return useCasePresent;
	}

	public void setUseCasePresent(String useCasePresent) {
		this.useCasePresent = useCasePresent;
	}

	public String getSupportFTE() {
		return supportFTE;
	}

	public void setSupportFTE(String supportFTE) {
		this.supportFTE = supportFTE;
	}

	public String getEngineeringFTE() {
		return engineeringFTE;
	}

	public void setEngineeringFTE(String engineeringFTE) {
		this.engineeringFTE = engineeringFTE;
	}

	public String getAutomationPossible() {
		return automationPossible;
	}

	public void setAutomationPossible(String automationPossible) {
		this.automationPossible = automationPossible;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isOpenPanel() {
		return openPanel;
	}

	public void setOpenPanel(boolean openPanel) {
		this.openPanel = openPanel;
	}
	
}
