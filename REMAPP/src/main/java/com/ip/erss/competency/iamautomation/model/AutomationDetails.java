package com.ip.erss.competency.iamautomation.model;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "AUTOMATION_DETAILS")
public class AutomationDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	
	@Column(name = "AUTOMATION_CATEGEORY")
	private String automationCategeory;
	
	@Column(name = "AUTOMATION_ITEMS")
	private String automationItems;
	
	@Column(name = "POSSIBILITY")
	private String possibility;
	
	@Column(name = "PHASE")
	private String phase;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "OPEN_PANEL")
	private boolean openPanel;
	
	@Lob
	@Column(name = "COMMENTS")
	private String comments;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "ID", referencedColumnName = "ACCOUNT_ID")
   	@JsonProperty("automationTarget")
	private List<AutomationTarget> automationTarget;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "ID", referencedColumnName = "ACCOUNT_ID")
   	@JsonProperty("automationActual")
	private List<AutomationActual> automationActual;
	
	public AutomationDetails() {
		super();
	}
	
	public AutomationDetails(Long accountId, String automationCategeory, String automationItems, String possibility,
			String phase, String status, boolean openPanel, String comments, List<AutomationTarget> automationTarget,
			List<AutomationActual> automationActual) {
		super();
		this.accountId = accountId;
		this.automationCategeory = automationCategeory;
		this.automationItems = automationItems;
		this.possibility = possibility;
		this.phase = phase;
		this.status = status;
		this.openPanel = openPanel;
		this.comments = comments;
		this.automationTarget = automationTarget;
		this.automationActual = automationActual;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAutomationCategeory() {
		return automationCategeory;
	}

	public void setAutomationCategeory(String automationCategeory) {
		this.automationCategeory = automationCategeory;
	}

	public String getAutomationItems() {
		return automationItems;
	}

	public void setAutomationItems(String automationItems) {
		this.automationItems = automationItems;
	}

	public String getPossibility() {
		return possibility;
	}

	public void setPossibility(String possibility) {
		this.possibility = possibility;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AutomationTarget> getAutomationTarget() {
		return automationTarget;
	}

	public void setAutomationTarget(List<AutomationTarget> automationTarget) {
		this.automationTarget = automationTarget;
	}

	public List<AutomationActual> getAutomationActual() {
		return automationActual;
	}

	public void setAutomationActual(List<AutomationActual> automationActual) {
		this.automationActual = automationActual;
	}

	public boolean isOpenPanel() {
		return openPanel;
	}

	public void setOpenPanel(boolean openPanel) {
		this.openPanel = openPanel;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

		
}
