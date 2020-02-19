package com.ip.erss.competency.iamautomation.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASKS")
public class Tasks {
	
	@Id
	@GeneratedValue
	@Column(name = "TASK_ID")
	private Long taskId;
	
	@Column(name = "TASK_TYPE")
	private String taskType;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "APPROVED_BY")
	private String approvedBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	private NoBot noBot;
	
	public Tasks() {
		super();
	}

	public Tasks(Long taskId, String taskType, String status, String approvedBy, Date createdDate, NoBot noBot) {
		super();
		this.taskId = taskId;
		this.taskType = taskType;
		this.status = status;
		this.approvedBy = approvedBy;
		this.createdDate = createdDate;
		this.noBot = noBot;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public NoBot getNoBot() {
		return noBot;
	}

	public void setNoBot(NoBot noBot) {
		this.noBot = noBot;
	}
	
}
