package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW_CANDIDATE_FEEDBACK")
public class InterviewPortalCandidateFeedback {
	
	@Override
	public String toString() {
		return "IntCandidateFeedbackDetails [candidateId=" + candidateId + ", jdId=" + jdId + ", date=" + date
				+ ", fullName=" + fullName + ", contactNumber=" + contactNumber + ", technology=" + technology
				+ ", skillset=" + skillset + ", experience=" + experience + ", relevantExperience=" + relevantExperience
				+ ", interviewLevelL1=" + interviewLevelL1 + ", interviewLevelL1Feedback=" + interviewLevelL1Feedback
				+ ", interviewLevelL1SelectionStatus=" + interviewLevelL1SelectionStatus
				+ ", interviewLevelL1PanelName=" + interviewLevelL1PanelName + ", interviewLevelL1PanelId="
				+ interviewLevelL1PanelId + ", interviewLevelL2=" + interviewLevelL2 + ", interviewLevelL2Feedback="
				+ interviewLevelL2Feedback + ", interviewLevelL2SelectionStatus=" + interviewLevelL2SelectionStatus
				+ ", interviewLevelL2PanelName=" + interviewLevelL2PanelName + ", interviewLevelL2PanelId="
				+ interviewLevelL2PanelId + ", interviewLevelHR=" + interviewLevelHR + ", interviewLevelHRFeedback="
				+ interviewLevelHRFeedback + ", interviewLevelHRSelectionStatus=" + interviewLevelHRSelectionStatus
				+ ", interviewLevelHRPanelName=" + interviewLevelHRPanelName + ", interviewLevelHRPanelId="
				+ interviewLevelHRPanelId + "]";
	}
	
	public InterviewPortalCandidateFeedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InterviewPortalCandidateFeedback(Long candidateId, Long jdId, Date date, String fullName, String contactNumber,
			String technology, String skillset, Integer experience, Integer relevantExperience, String interviewLevelL1,
			String interviewLevelL1Feedback, String interviewLevelL1SelectionStatus, String interviewLevelL1PanelName,
			String interviewLevelL1PanelId, String interviewLevelL2, String interviewLevelL2Feedback,
			String interviewLevelL2SelectionStatus, String interviewLevelL2PanelName, String interviewLevelL2PanelId,
			String interviewLevelHR, String interviewLevelHRFeedback, String interviewLevelHRSelectionStatus,
			String interviewLevelHRPanelName, String interviewLevelHRPanelId) {
		super();
		this.candidateId = candidateId;
		this.jdId = jdId;
		this.date = date;
		this.fullName = fullName;
		this.contactNumber = contactNumber;
		this.technology = technology;
		this.skillset = skillset;
		this.experience = experience;
		this.relevantExperience = relevantExperience;
		this.interviewLevelL1 = interviewLevelL1;
		this.interviewLevelL1Feedback = interviewLevelL1Feedback;
		this.interviewLevelL1SelectionStatus = interviewLevelL1SelectionStatus;
		this.interviewLevelL1PanelName = interviewLevelL1PanelName;
		this.interviewLevelL1PanelId = interviewLevelL1PanelId;
		this.interviewLevelL2 = interviewLevelL2;
		this.interviewLevelL2Feedback = interviewLevelL2Feedback;
		this.interviewLevelL2SelectionStatus = interviewLevelL2SelectionStatus;
		this.interviewLevelL2PanelName = interviewLevelL2PanelName;
		this.interviewLevelL2PanelId = interviewLevelL2PanelId;
		this.interviewLevelHR = interviewLevelHR;
		this.interviewLevelHRFeedback = interviewLevelHRFeedback;
		this.interviewLevelHRSelectionStatus = interviewLevelHRSelectionStatus;
		this.interviewLevelHRPanelName = interviewLevelHRPanelName;
		this.interviewLevelHRPanelId = interviewLevelHRPanelId;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "CANDIDATE_ID")
	private Long candidateId;

	@Column(name = "jd_id")
	private Long jdId;
	
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name = "TECHNOLOGY")
	private String technology;
	
	@Column(name = "SKILLSET")
	private String skillset;
	
	@Column(name = "EXPERIENCE")
	private Integer experience;
	
	@Column(name = "RELEVANT_EXPERIENCE")
	private Integer relevantExperience;
	
	@Column(name = "INTERVIEW_LEVEL_L1")
	private String interviewLevelL1;
	
	@Column(name = "INTERVIEW_LEVEL_L1_FEEDBACK")
	private String interviewLevelL1Feedback;

	@Column(name = "INTERVIEW_LEVEL_L1_SELECTION_STATUS")
	private String interviewLevelL1SelectionStatus;
	
	@Column(name = "INTERVIEWER_LEVEL_L1_PANEL_NAME")
	private String interviewLevelL1PanelName;
	
	@Column(name = "INTERVIEWER_LEVEL__L1_PANEL_ID")
	private String interviewLevelL1PanelId;
	
	@Column(name = "INTERVIEW_LEVEL_L2")
	private String interviewLevelL2;
	
	@Column(name = "INTERVIEW_LEVEL_L2_FEEDBACK")
	private String interviewLevelL2Feedback;

	@Column(name = "INTERVIEW_LEVEL_L2_SELECTION_STATUS")
	private String interviewLevelL2SelectionStatus;
	
	@Column(name = "INTERVIEWER_LEVEL_L2_PANEL_NAME")
	private String interviewLevelL2PanelName;
	
	@Column(name = "INTERVIEWER_LEVEL__L2_PANEL_ID")
	private String interviewLevelL2PanelId;
	
	@Column(name = "INTERVIEW_LEVEL_HR")
	private String interviewLevelHR;
	
	@Column(name = "INTERVIEW_LEVEL_HR_FEEDBACK")
	private String interviewLevelHRFeedback;

	@Column(name = "INTERVIEW_LEVEL_HR_SELECTION_STATUS")
	private String interviewLevelHRSelectionStatus;
	
	@Column(name = "INTERVIEWER_LEVEL_HR_PANEL_NAME")
	private String interviewLevelHRPanelName;
	
	@Column(name = "INTERVIEWER_LEVEL_HR_PANEL_ID")
	private String interviewLevelHRPanelId;

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getJdId() {
		return jdId;
	}

	public void setJdId(Long jdId) {
		this.jdId = jdId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getSkillset() {
		return skillset;
	}

	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(Integer relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public String getInterviewLevelL1() {
		return interviewLevelL1;
	}

	public void setInterviewLevelL1(String interviewLevelL1) {
		this.interviewLevelL1 = interviewLevelL1;
	}

	public String getInterviewLevelL1Feedback() {
		return interviewLevelL1Feedback;
	}

	public void setInterviewLevelL1Feedback(String interviewLevelL1Feedback) {
		this.interviewLevelL1Feedback = interviewLevelL1Feedback;
	}

	public String getInterviewLevelL1SelectionStatus() {
		return interviewLevelL1SelectionStatus;
	}

	public void setInterviewLevelL1SelectionStatus(String interviewLevelL1SelectionStatus) {
		this.interviewLevelL1SelectionStatus = interviewLevelL1SelectionStatus;
	}

	public String getInterviewLevelL1PanelName() {
		return interviewLevelL1PanelName;
	}

	public void setInterviewLevelL1PanelName(String interviewLevelL1PanelName) {
		this.interviewLevelL1PanelName = interviewLevelL1PanelName;
	}

	public String getInterviewLevelL1PanelId() {
		return interviewLevelL1PanelId;
	}

	public void setInterviewLevelL1PanelId(String interviewLevelL1PanelId) {
		this.interviewLevelL1PanelId = interviewLevelL1PanelId;
	}

	public String getInterviewLevelL2() {
		return interviewLevelL2;
	}

	public void setInterviewLevelL2(String interviewLevelL2) {
		this.interviewLevelL2 = interviewLevelL2;
	}

	public String getInterviewLevelL2Feedback() {
		return interviewLevelL2Feedback;
	}

	public void setInterviewLevelL2Feedback(String interviewLevelL2Feedback) {
		this.interviewLevelL2Feedback = interviewLevelL2Feedback;
	}

	public String getInterviewLevelL2SelectionStatus() {
		return interviewLevelL2SelectionStatus;
	}

	public void setInterviewLevelL2SelectionStatus(String interviewLevelL2SelectionStatus) {
		this.interviewLevelL2SelectionStatus = interviewLevelL2SelectionStatus;
	}

	public String getInterviewLevelL2PanelName() {
		return interviewLevelL2PanelName;
	}

	public void setInterviewLevelL2PanelName(String interviewLevelL2PanelName) {
		this.interviewLevelL2PanelName = interviewLevelL2PanelName;
	}

	public String getInterviewLevelL2PanelId() {
		return interviewLevelL2PanelId;
	}

	public void setInterviewLevelL2PanelId(String interviewLevelL2PanelId) {
		this.interviewLevelL2PanelId = interviewLevelL2PanelId;
	}

	public String getInterviewLevelHR() {
		return interviewLevelHR;
	}

	public void setInterviewLevelHR(String interviewLevelHR) {
		this.interviewLevelHR = interviewLevelHR;
	}

	public String getInterviewLevelHRFeedback() {
		return interviewLevelHRFeedback;
	}

	public void setInterviewLevelHRFeedback(String interviewLevelHRFeedback) {
		this.interviewLevelHRFeedback = interviewLevelHRFeedback;
	}

	public String getInterviewLevelHRSelectionStatus() {
		return interviewLevelHRSelectionStatus;
	}

	public void setInterviewLevelHRSelectionStatus(String interviewLevelHRSelectionStatus) {
		this.interviewLevelHRSelectionStatus = interviewLevelHRSelectionStatus;
	}

	public String getInterviewLevelHRPanelName() {
		return interviewLevelHRPanelName;
	}

	public void setInterviewLevelHRPanelName(String interviewLevelHRPanelName) {
		this.interviewLevelHRPanelName = interviewLevelHRPanelName;
	}

	public String getInterviewLevelHRPanelId() {
		return interviewLevelHRPanelId;
	}

	public void setInterviewLevelHRPanelId(String interviewLevelHRPanelId) {
		this.interviewLevelHRPanelId = interviewLevelHRPanelId;
	}
	
}
