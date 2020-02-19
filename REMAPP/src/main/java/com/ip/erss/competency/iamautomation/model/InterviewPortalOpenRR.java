package com.ip.erss.competency.iamautomation.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW_RR_DETAILS")
public class InterviewPortalOpenRR {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long Id;

	@Column(name="RR_ID")
	private String rrId;
	
	@Column(name="JD_ID")
	private Long jobdescId;
	
	@Column(name="DEMAND")
	private String demand;
	
	@Column(name="ACCOUNT_NAME")
	private String accountName;
	
	@Column(name="GAP")
	private String gap;
	
	@Column(name="LEVEL")
	private String level;
	
	@Column(name="AGEING_DAYS")
	private String ageingDays;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="VERTICAL_GROUP")
	private String verticalGrp;
	
	@Column(name="FITMENT")
	private String fitment;
	
	@Column(name="SKILL")
	private String skill;
	
	@Column(name="HIRING_MANAGER")
	private String hiringManager;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="RECRUITER")
	private String recruiter;

	@Column(name="L2_JD")
	private String l2JdDesc;
	

	private Long primaryID;
	
	public Long getPrimaryID() {
		return primaryID;
	}



	public void setPrimaryID(Long primaryID) {
		this.primaryID = primaryID;
	}



	public InterviewPortalOpenRR() {
		super();
	}

	

	/**
	 * @param id
	 * @param rrId
	 * @param jobdescId
	 * @param demand
	 * @param accountName
	 * @param gap
	 * @param level
	 * @param ageingDays
	 * @param location
	 * @param verticalGrp
	 * @param fitment
	 * @param skill
	 * @param hiringManager
	 * @param status
	 * @param date
	 * @param recruiter
	 * @param l2JdDesc
	 */
	public InterviewPortalOpenRR(Long id, String rrId, Long jobdescId, String demand, String accountName, String gap,
			String level, String ageingDays, String location, String verticalGrp, String fitment, String skill,
			String hiringManager, String status, Date date, String recruiter, String l2JdDesc) {
		super();
		this.Id = id;
		this.rrId = rrId;
		this.jobdescId = jobdescId;
		this.demand = demand;
		this.accountName = accountName;
		this.gap = gap;
		this.level = level;
		this.ageingDays = ageingDays;
		this.location = location;
		this.verticalGrp = verticalGrp;
		this.fitment = fitment;
		this.skill = skill;
		this.hiringManager = hiringManager;
		this.status = status;
		this.date = date;
		this.recruiter = recruiter;
		this.l2JdDesc = l2JdDesc;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}


	/**
	 * @return the demand
	 */
	public String getDemand() {
		return demand;
	}


	/**
	 * @param demand the demand to set
	 */
	public void setDemand(String demand) {
		this.demand = demand;
	}


	/**
	 * @return the rrId
	 */
	public String getRrId() {
		return rrId;
	}

	/**
	 * @param rrId the rrId to set
	 */
	public void setRrId(String rrId) {
		this.rrId = rrId;
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
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the gap
	 */
	public String getGap() {
		return gap;
	}

	/**
	 * @param gap the gap to set
	 */
	public void setGap(String gap) {
		this.gap = gap;
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
	 * @return the ageingDays
	 */
	public String getAgeingDays() {
		return ageingDays;
	}

	/**
	 * @param ageingDays the ageingDays to set
	 */
	public void setAgeingDays(String ageingDays) {
		this.ageingDays = ageingDays;
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

	/**
	 * @return the verticalGrp
	 */
	public String getVerticalGrp() {
		return verticalGrp;
	}

	/**
	 * @param verticalGrp the verticalGrp to set
	 */
	public void setVerticalGrp(String verticalGrp) {
		this.verticalGrp = verticalGrp;
	}

	/**
	 * @return the fitment
	 */
	public String getFitment() {
		return fitment;
	}

	/**
	 * @param fitment the fitment to set
	 */
	public void setFitment(String fitment) {
		this.fitment = fitment;
	}

	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}

	/**
	 * @return the hiringManager
	 */
	public String getHiringManager() {
		return hiringManager;
	}

	/**
	 * @param hiringManager the hiringManager to set
	 */
	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the recruiter
	 */
	public String getRecruiter() {
		return recruiter;
	}

	/**
	 * @param recruiter the recruiter to set
	 */
	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	/**
	 * @return the l2JdDesc
	 */
	public String getL2JdDesc() {
		return l2JdDesc;
	}


	/**
	 * @param l2JdDesc the l2JdDesc to set
	 */
	public void setL2JdDesc(String l2JdDesc) {
		this.l2JdDesc = l2JdDesc;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InterviewPortalOpenRR [Id=" + Id + ", rrId=" + rrId + ", jobdescId=" + jobdescId + ", demand=" + demand
				+ ", accountName=" + accountName + ", gap=" + gap + ", level=" + level + ", ageingDays=" + ageingDays
				+ ", location=" + location + ", verticalGrp=" + verticalGrp + ", fitment=" + fitment + ", skill="
				+ skill + ", hiringManager=" + hiringManager + ", status=" + status + ", date=" + date + ", recruiter="
				+ recruiter + ", l2JdDesc=" + l2JdDesc + "]";
	}


	
	

}
