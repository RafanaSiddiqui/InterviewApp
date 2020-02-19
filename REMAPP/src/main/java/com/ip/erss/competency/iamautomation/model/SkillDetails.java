package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "SKILL_DETAILS")
public class SkillDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Lob
	@Column(name = "SKILL")
	private String skill;
	
	@Column(name = "SKILL_ACTUAL")
	private String skillActual;
	
	@Column(name = "PROFICIENCY")
	private String proficiency;
	
	@Column(name = "ASSOCIATE_ID")
	private String associateId;
	
	@Column(name = "SELECT_SKILL")
	private String selectSkill;

	public SkillDetails() {
		super();
	}

	public SkillDetails(Long id, String skill, String proficiency, String skillActual, String associateId, String selectSkill) {
		super();
		this.id = id;
		this.skill = skill;
		this.proficiency = proficiency;
		this.skillActual = skillActual;
		this.associateId = associateId;
		this.selectSkill = selectSkill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getSkillActual() {
		return skillActual;
	}

	public void setSkillActual(String skillActual) {
		this.skillActual = skillActual;
	}
	
	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getSelectSkill() {
		return selectSkill;
	}

	public void setSelectSkill(String selectSkill) {
		this.selectSkill = selectSkill;
	}
	
}
