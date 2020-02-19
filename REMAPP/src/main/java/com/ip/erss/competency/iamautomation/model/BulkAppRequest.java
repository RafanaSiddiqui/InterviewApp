/**
 *
 */
package com.ip.erss.competency.iamautomation.model;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 134930
 *
 */
@Entity
@Table(name = "BULK_APP_REQUEST")
public class BulkAppRequest implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2795143852228332015L;

	public BulkAppRequest() {
		super();
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long bulkRequestID;


	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_DATE")
	private Date updateDate;

	@Column(name = "FILE")
	@Lob
	private byte[] fileData;

	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BulkAppRequest [bulkRequestID=" + bulkRequestID + ", name="
				+ name + ", description=" + description + ", status=" + status
				+ ", createdDate=" + createdDate + ", updateDate=" + updateDate
				+ ", fileData=" + Arrays.toString(fileData) + ", fileName="
				+ fileName + "]";
	}
	/**
	 * @param bulkRequestID
	 * @param name
	 * @param description
	 * @param status
	 * @param createdDate
	 * @param updateDate
	 * @param fileData
	 * @param fileName
	 */
	public BulkAppRequest(Long bulkRequestID, String name, String description,
			String status, Date createdDate, Date updateDate, byte[] fileData,
			String fileName, String contentType) {
		super();
		this.bulkRequestID = bulkRequestID;
		this.name = name;
		this.description = description;
		this.status = status;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.fileData = fileData;
		this.fileName = fileName;
		this.contentType = contentType;
	}
	/**
	 * @return the fileData
	 */
	public byte[] getFileData() {
		return fileData;
	}
	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bulkRequestID
	 */
	public Long getBulkRequestID() {
		return bulkRequestID;
	}
	/**
	 * @param bulkRequestID the bulkRequestID to set
	 */
	public void setBulkRequestID(Long bulkRequestID) {
		this.bulkRequestID = bulkRequestID;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	




}
