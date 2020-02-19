package com.ip.erss.competency.iamautomation.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "INTERVIEW_PANEL_REPORT")
public class InterviewPortalPanelReport implements java.io.Serializable{
	
	
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "JD_ID")
	private Long jobID;
	
	
	@Column(name = "PANEL_ID")
	private Long panelID;
	
	@Column(name = "INTERVIEW_DATE")
	private Date interviewDate;
	
	@Column(name = "INTERVIEW_LEVEL")
	private String interviewLevel;
	
	@Column(name = "TECHNOLOGY")
	private String technology;
	
	/*@OneToOne(cascade=CascadeType.ALL)
   	@JoinColumn(name = "PANEL_ID", referencedColumnName = "EMP_ID", insertable = false, updatable = false )
   	@JsonProperty("panelDetails")*/
	/*@Transient
	private InterviewPortalPanel panelDetails;*/		
	
	@Transient
	private long interviewCount;	

	/*public void setPanelDetails(InterviewPortalPanel panelDetails) {
		this.panelDetails = panelDetails;
	}*/

	public long getInterviewCount() {
		return interviewCount;
	}

	public void setInterviewCount(long interviewCount) {
		this.interviewCount = interviewCount;
	}

	public InterviewPortalPanelReport() {
		super();
	}	
	
	@Override
	public String toString() {
		return "InterviewPortalPanelReport [id=" + id + ", jobID=" + jobID + ", panelID=" + panelID
				+ ", interviewDate=" + interviewDate + ", interviewLevel=" + interviewLevel + "]";
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPanelID() {
		return panelID;
	}



	public void setPanelID(Long panelID) {
		this.panelID = panelID;
	}



	public Date getInterviewDate() {
		return interviewDate;
	}



	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}



	public String getInterviewLevel() {
		return interviewLevel;
	}



	public void setInterviewLevel(String interviewLevel) {
		this.interviewLevel = interviewLevel;
	}
	
	/*public InterviewPortalPanel getPanelDetails() {
		return panelDetails;
	}

	public void setPanelList(InterviewPortalPanel panelList) {
		this.panelDetails = panelDetails;
	}*/

	public Long getJobID() {
		return jobID;
	}

	public void setJobID(Long jobID) {
		this.jobID = jobID;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

}
