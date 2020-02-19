package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASSOCIATE_DETAILS")
public class AssociateDetails {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name = "ASSOCIATE_ID")
	private String associateID;
	
	@Column(name = "VERTICAL")
	private String vertical;
	
	@Column(name = "GRADE")
	private String grade;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "KEY_SKILLS")
	private String keySkills;
	
	@Column(name = "DETAILED_SKILLS")
	private String detailedSkills;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "AVAILABLE_TIME")
	private int availableTime;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	public AssociateDetails() {
		super();
	}

	public AssociateDetails(Long id, String associateID, String vertical, String grade, String firstName,
			String lastName, String keySkills, String detailedSkills, String location, int availableTime, String contactNumber) {
		super();
		this.id = id;
		this.associateID = associateID;
		this.vertical = vertical;
		this.grade = grade;
		this.firstName = firstName;
		this.lastName = lastName;
		this.keySkills = keySkills;
		this.detailedSkills = detailedSkills;
		this.location = location;
		this.availableTime = availableTime;
		this.contactNumber = contactNumber;
	}
	
	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getKeySkills() {
		return keySkills;
	}

	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}

	public String getDetailedSkills() {
		return detailedSkills;
	}

	public void setDetailedSkills(String detailedSkills) {
		this.detailedSkills = detailedSkills;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(int availableTime) {
		this.availableTime = availableTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssociateID() {
		return associateID;
	}

	public void setAssociateID(String associateID) {
		this.associateID = associateID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "AssociateDetails [associateID=" + associateID + ", vertical=" + vertical + ", grade=" + grade
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", keySkills=" + keySkills
				+ ", detailedSkills=" + detailedSkills + ", location=" + location + ", availableTime=" + availableTime +", contactNumber="+contactNumber
				+ "]";
	}
	
	
	
	
	

}
