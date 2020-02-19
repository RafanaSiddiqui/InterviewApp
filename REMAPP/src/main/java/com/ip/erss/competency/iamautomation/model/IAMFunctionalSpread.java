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
@Table(name = "IAM_FUNCTIONAL_SPREAD")
public class IAMFunctionalSpread {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ACCOUNT_ID")
	private String accountID;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "VERTICAL")
	private String vertical;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "FUNCTIONAL_SPREAD_ID", referencedColumnName = "ID")
   	@JsonProperty("servicesDetails")
	private List<Services> servicesDetails;
	
	public IAMFunctionalSpread() {
		super();
	}

	public IAMFunctionalSpread(Long id, String securityDomainCode, String securityDomain, String serviceCategoryCode,
			String serviceCategory, String offeringCode, String service, String teckStackTierRefCode, String accountID,
			String customerName, String useCasePresent, String supportFTE, String engineeringFTE,
			String automationPossible, String vertical, Date createdDate, List<Services> servicesDetails) {
		super();
		this.id = id;
		this.accountID = accountID;
		this.customerName = customerName;
		this.vertical = vertical;
		this.createdDate = createdDate;
		this.servicesDetails = servicesDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Services> getServicesDetails() {
		return servicesDetails;
	}

	public void setServicesDetails(List<Services> servicesDetails) {
		this.servicesDetails = servicesDetails;
	}
	
}
