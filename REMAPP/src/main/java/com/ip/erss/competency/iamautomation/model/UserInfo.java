package com.ip.erss.competency.iamautomation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {
	
	@Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long userId;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="USERNAME")
	private String username;
	
	/*@Column(name="PASSWORD")
	private String password;*/
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="mailId")
	private String mailId;
	
	@Column(name="APPROVER_TYPE")
	private String approverType;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   	@JoinColumn(name = "ID", referencedColumnName = "USER_ID")
   	@JsonProperty("userRolesList")
	private List<UserRoles> userRolesList;
	
	public UserInfo() {
		super();
	}
	
	public UserInfo(Long userId, String firstName, String lastName, String username, String role, String mailId,
			String approverType, List<UserRoles> userRolesList) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.role = role;
		this.mailId = mailId;
		this.approverType = approverType;
		this.userRolesList = userRolesList;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/*public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getApproverType() {
		return approverType;
	}
	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}

	public List<UserRoles> getUserRolesList() {
		return userRolesList;
	}

	public void setUserRolesList(List<UserRoles> userRolesList) {
		this.userRolesList = userRolesList;
	}
	
}
