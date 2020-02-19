package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOBOTS")
public class NoBot {

	@Id
	@GeneratedValue
	@Column(name = "NO_BOT_ID")
	private long noBotId;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "BOT_AVAILABILITY")
	private boolean isAvailable;

	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "APPROVED_BY")
	private String approvedBy;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "DEC_CHECK")
	private boolean decCheck;
	
	public NoBot() {
		super();
	}

	public NoBot(long noBotId, String customerName, String projectName, boolean isAvailable, String comments,
			Date createdDate, String status, String approvedBy, String createdBy, boolean decCheck) {
		super();
		this.noBotId = noBotId;
		this.customerName = customerName;
		this.projectName = projectName;
		this.isAvailable = isAvailable;
		this.comments = comments;
		this.createdDate = createdDate;
		this.status = status;
		this.approvedBy = approvedBy;
		this.createdBy = createdBy;
		this.decCheck = decCheck;
	}



	/**
	 * @return the noBotId
	 */
	public long getNoBotId() {
		return noBotId;
	}

	/**
	 * @param noBotId
	 *            the noBotId to set
	 */
	public void setNoBotId(long noBotId) {
		this.noBotId = noBotId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable
	 *            the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDecCheck() {
		return decCheck;
	}

	public void setDecCheck(boolean decCheck) {
		this.decCheck = decCheck;
	}

}
