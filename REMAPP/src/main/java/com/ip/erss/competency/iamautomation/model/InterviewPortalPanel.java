package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "INTERVIEW_ADMIN_PANEL")
public class InterviewPortalPanel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5581937490076273668L;
	
/*	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;*/
	
	@Id
	@Column(name = "EMP_ID")
	private Long empId;
	
	@Column(name = "EMP_NAME")
	private String name;
	
	@Column(name = "ACCOUNT")
	private String account;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "TECHNOLOGY")
	private String technology;
	
	@Column(name = "PRODUCT")
	private String product;
		
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "INSERTED_BY")
	private String insertedBy;
	
	@Column(name = "STATUS")
	private String status;
	
	@Transient
	private boolean selected;
	
	@Transient
	private boolean valid;
	
	@Transient
	private String recentInterview1;
	
	@Transient
	private String recentInterview2;
	
	@Transient
	private String recentInterview3;
	
	@Transient
	private String recentInterview4;
			
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public InterviewPortalPanel() {
		super();
	}	

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/*public Long getId() {
		return id;
	}*/

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	

	public String getRecentInterview1() {
		return recentInterview1;
	}

	public void setRecentInterview1(String recentInterview1) {
		this.recentInterview1 = recentInterview1;
	}

	public String getRecentInterview2() {
		return recentInterview2;
	}

	public void setRecentInterview2(String recentInterview2) {
		this.recentInterview2 = recentInterview2;
	}

	public String getRecentInterview3() {
		return recentInterview3;
	}

	public void setRecentInterview3(String recentInterview3) {
		this.recentInterview3 = recentInterview3;
	}

	public String getRecentInterview4() {
		return recentInterview4;
	}

	public void setRecentInterview4(String recentInterview4) {
		this.recentInterview4 = recentInterview4;
	}
}
