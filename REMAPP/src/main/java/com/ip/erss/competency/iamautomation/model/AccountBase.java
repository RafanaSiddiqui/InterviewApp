package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_BASE")
public class AccountBase {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long accountBaseId;
	
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@Column(name = "VERTICAL")
	private String vertical;
	
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	
	@Column(name = "BFD")
	private Double bfd;
	
	@Column(name = "BTB")
	private Double btb;
	
	@Column(name = "BTM")
	private Double btm;
	
	@Column(name = "NBL")
	private Double nbl;
	
	@Column(name = "COMPETENCY_MANAGER_ID")
	private String competencyManagerId;
	
	@Column(name = "COMPETENCY_MANAGER")
	private String competencyManager;
	
	@Column(name = "PRIMARY_LOCATION")
	private String primaryLocation;
	
	@Column(name = "ODC_DETAILS")
	private String odcDetails;
	
	@Column(name = "TECK_STACK")
	private String teckStack;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	public AccountBase() {
		super();
	}

	public AccountBase(Long accountBaseId, String customerId, String vertical, String accountName, Double bfd, Double btb,
			Double btm, Double nbl, String competencyManagerId, String competencyManager, String primaryLocation,
			String odcDetails, String teckStack, Date createdDate) {
		super();
		this.accountBaseId = accountBaseId;
		this.customerId = customerId;
		this.vertical = vertical;
		this.accountName = accountName;
		this.bfd = bfd;
		this.btb = btb;
		this.btm = btm;
		this.nbl = nbl;
		this.competencyManagerId = competencyManagerId;
		this.competencyManager = competencyManager;
		this.primaryLocation = primaryLocation;
		this.odcDetails = odcDetails;
		this.teckStack = teckStack;
		this.createdDate = createdDate;
	}

	public Long getAccountBaseId() {
		return accountBaseId;
	}

	public void setAccountBaseId(Long accountBaseId) {
		this.accountBaseId = accountBaseId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Double getBfd() {
		return bfd;
	}

	public void setBfd(Double bfd) {
		this.bfd = bfd;
	}

	public Double getBtb() {
		return btb;
	}

	public void setBtb(Double btb) {
		this.btb = btb;
	}

	public Double getBtm() {
		return btm;
	}

	public void setBtm(Double btm) {
		this.btm = btm;
	}

	public Double getNbl() {
		return nbl;
	}

	public void setNbl(Double nbl) {
		this.nbl = nbl;
	}

	public String getCompetencyManagerId() {
		return competencyManagerId;
	}

	public void setCompetencyManagerId(String competencyManagerId) {
		this.competencyManagerId = competencyManagerId;
	}

	public String getCompetencyManager() {
		return competencyManager;
	}

	public void setCompetencyManager(String competencyManager) {
		this.competencyManager = competencyManager;
	}

	public String getPrimaryLocation() {
		return primaryLocation;
	}

	public void setPrimaryLocation(String primaryLocation) {
		this.primaryLocation = primaryLocation;
	}

	public String getOdcDetails() {
		return odcDetails;
	}

	public void setOdcDetails(String odcDetails) {
		this.odcDetails = odcDetails;
	}

	public String getTeckStack() {
		return teckStack;
	}

	public void setTeckStack(String teckStack) {
		this.teckStack = teckStack;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
