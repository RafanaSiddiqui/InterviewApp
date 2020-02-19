package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOMATION_ACTUAL")
public class AutomationActual {

	@Id
	@GeneratedValue
	@Column(name = "ACTUAL_ID")
	private Long actualId;
	
	@Column(name = "YEAR")
	private String year;
	
	@Column(name = "Q1")
	private String q1;
	
	@Column(name = "Q2")
	private String q2;
	
	@Column(name = "Q3")
	private String q3;
	
	@Column(name = "Q4")
	private String q4;

	public AutomationActual() {
		super();
	}

	public AutomationActual(Long actualId, String year, String q1, String q2, String q3, String q4) {
		super();
		this.actualId = actualId;
		this.year = year;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.q4 = q4;
	}

	public Long getActualId() {
		return actualId;
	}

	public void setActualId(Long actualId) {
		this.actualId = actualId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQ1() {
		return q1;
	}

	public void setQ1(String q1) {
		this.q1 = q1;
	}

	public String getQ2() {
		return q2;
	}

	public void setQ2(String q2) {
		this.q2 = q2;
	}

	public String getQ3() {
		return q3;
	}

	public void setQ3(String q3) {
		this.q3 = q3;
	}

	public String getQ4() {
		return q4;
	}

	public void setQ4(String q4) {
		this.q4 = q4;
	}
	
}
