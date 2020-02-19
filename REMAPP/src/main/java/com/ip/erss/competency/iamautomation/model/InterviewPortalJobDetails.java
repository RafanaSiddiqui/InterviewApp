package com.ip.erss.competency.iamautomation.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW_JOB_DETAILS")
public class InterviewPortalJobDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="JD_ID")
	private Long jobdescId;
	
	@Column(name="SKILL")
	private String skill;
	
	@Column(name="SKILL_OWNER")
	private String skillOwner;
	
	@Column(name="DRIVE_LOCATION")
	private String location;
	
	@Column(name="EXPERIENCE")
	private String experience;
	
	@Column(name="FOOTFALL")
	private String footFall;
	
	@Column(name="PANELS_COUNT")
	private String panelsCount;
	
	@Column(name = "UPLOADED_CV")
	@Lob
	private byte[] uploadedCV;
	
	@Column(name="CV_COUNT")
	private String cvCount;
	
	@Column(name="DRIVE_DATE")
	private Date driveDate;
	
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="REQ_COUNT")
	private String reqCount;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@Column(name="L1_DESCRIPTION")
	private String l1Description;
	
	@Column(name="Level")
	private String level;
	
	@Column(name="JOB_LOCATION")
	private String jobLocation;
	/**
	 * 
	 */
	public InterviewPortalJobDetails() {
		super();
	}


	/**
	 * @param jobdescId
	 * @param skill
	 * @param skillOwner
	 * @param location
	 * @param experience
	 * @param footFall
	 * @param panelsCount
	 * @param uploadedCV
	 * @param cvCount
	 * @param driveDate
	 * @param description
	 * @param reqCount
	 * @param fileName
	 * @param contentType
	 * @param l1Description
	 * @param l2Description
	 * @param level
	 * @param jobLocation
	 */
	public InterviewPortalJobDetails(Long jobdescId, String skill, String skillOwner, String location,
			String experience, String footFall, String panelsCount, byte[] uploadedCV, String cvCount, Date driveDate,
			String description, String reqCount, String fileName, String contentType, String l1Description, String level, String jobLocation) {
		super();
		this.jobdescId = jobdescId;
		this.skill = skill;
		this.skillOwner = skillOwner;
		this.location = location;
		this.experience = experience;
		this.footFall = footFall;
		this.panelsCount = panelsCount;
		this.uploadedCV = uploadedCV;
		this.cvCount = cvCount;
		this.driveDate = driveDate;
		this.description = description;
		this.reqCount = reqCount;
		this.fileName = fileName;
		this.contentType = contentType;
		this.l1Description = l1Description;
		this.level = level;
		this.jobLocation = jobLocation;
	}



	/**
	 * @return the jobdescId
	 */
	public Long getJobdescId() {
		return jobdescId;
	}

	/**
	 * @param jobdescId the jobdescId to set
	 */
	public void setJobdescId(Long jobdescId) {
		this.jobdescId = jobdescId;
	}

	/**
	 * @return the technology
	 */
	public String getSkill() {
		return skill;
	}

	/**
	 * @param technology the technology to set
	 */
	public void setSkill(String technology) {
		this.skill = technology;
	}

	/**
	 * @return the skillOwner
	 */
	public String getSkillOwner() {
		return skillOwner;
	}


	/**
	 * @param skillOwner the skillOwner to set
	 */
	public void setSkillOwner(String skillOwner) {
		this.skillOwner = skillOwner;
	}


	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/**
	 * @return the footFall
	 */
	public String getFootFall() {
		return footFall;
	}

	/**
	 * @param footFall the footFall to set
	 */
	public void setFootFall(String footFall) {
		this.footFall = footFall;
	}

	/**
	 * @return the panelsCount
	 */
	public String getPanelsCount() {
		return panelsCount;
	}

	/**
	 * @param panelsCount the panelsCount to set
	 */
	public void setPanelsCount(String panelsCount) {
		this.panelsCount = panelsCount;
	}

	/**
	 * @return the uploadedCV
	 */
	public byte[] getUploadedCV() {
		return uploadedCV;
	}

	/**
	 * @param uploadedCV the uploadedCV to set
	 */
	public void setUploadedCV(byte[] uploadedCV) {
		this.uploadedCV = uploadedCV;
	}

	/**
	 * @return the cvCount
	 */
	public String getCvCount() {
		return cvCount;
	}

	/**
	 * @param cvCount the cvCount to set
	 */
	public void setCvCount(String cvCount) {
		this.cvCount = cvCount;
	}

	/**
	 * @return the driveDate
	 */
	public Date getDriveDate() {
		return driveDate;
	}

	/**
	 * @param driveDate the driveDate to set
	 */
	public void setDriveDate(Date driveDate) {
		this.driveDate = driveDate;
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
	 * @return the reqCount
	 */
	public String getReqCount() {
		return reqCount;
	}


	/**
	 * @param reqCount the reqCount to set
	 */
	public void setReqCount(String reqCount) {
		this.reqCount = reqCount;
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
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the l1Description
	 */
	public String getL1Description() {
		return l1Description;
	}



	/**
	 * @param l1Description the l1Description to set
	 */
	public void setL1Description(String l1Description) {
		this.l1Description = l1Description;
	}



	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}



	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}



	/**
	 * @return the jobLocation
	 */
	public String getJobLocation() {
		return jobLocation;
	}



	/**
	 * @param jobLocation the jobLocation to set
	 */
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}



	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InterviewPortalJobDetails [jobdescId=" + jobdescId + ", skill=" + skill + ", skillOwner=" + skillOwner
				+ ", location=" + location + ", experience=" + experience + ", footFall=" + footFall + ", panelsCount="
				+ panelsCount + ", uploadedCV=" + Arrays.toString(uploadedCV) + ", cvCount=" + cvCount + ", driveDate="
				+ driveDate + ", description=" + description + ", reqCount=" + reqCount + ", fileName=" + fileName
				+ ", contentType=" + contentType + ", l1Description=" + l1Description + ", level=" + level
				+ ", jobLocation=" + jobLocation + "]";
	}



	
}
