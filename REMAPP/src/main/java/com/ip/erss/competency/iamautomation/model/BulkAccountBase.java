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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BULK_ACCOUNT_BASE")
public class BulkAccountBase {
	@Id
	@GeneratedValue
	@Column(name = "BULK_ID")
	private Long bulkID;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "FILE")
	@Lob
	private byte[] fileData;

	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
   	@JoinColumn(name = "BULK_ID", referencedColumnName = "BULK_ID")
   	@JsonProperty("accountBaseList")
	private List<AccountBase> accountBaseList;
	
	public BulkAccountBase() {
		super();
	}


	public BulkAccountBase(Long bulkID, String name, String description, String status, Date createdDate, Date updatedDate, byte[] fileData,
			String fileName, String contentType, List<AccountBase> accountBaseList) {
		super();
		this.bulkID = bulkID;
		this.name = name;
		this.description = description;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.fileData = fileData;
		this.fileName = fileName;
		this.contentType = contentType;
		this.accountBaseList = accountBaseList;
	}

	public Long getBulkID() {
		return bulkID;
	}


	public void setBulkID(Long bulkID) {
		this.bulkID = bulkID;
	}


	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public byte[] getFileData() {
		return fileData;
	}


	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public List<AccountBase> getAccountBaseList() {
		return accountBaseList;
	}


	public void setAccountBaseList(List<AccountBase> accountBaseList) {
		this.accountBaseList = accountBaseList;
	}

	
}
