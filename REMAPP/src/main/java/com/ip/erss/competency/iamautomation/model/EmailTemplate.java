package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_TEMPLATE")
public class EmailTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "TEMPLATEID")
	private String templateID;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME")
	private String name;

	@Column(name = "E_FROM")
	private String from;

	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "TO")
	private String to;

	@Column(name = "CC")
	private String cc;
	
	@Lob 
	@Column(name = "MESSAGE")
	private String message;

	public EmailTemplate() {
	}

	public Long getId() {
		return id;
	}

	public EmailTemplate(Long id, String name, String templateID,String description, String from, String subject, String to, String cc,
			String message) {
		super();
		this.id = id;
		this.name = name;
		this.templateID = templateID;
		this.from = from;
		this.subject = subject;
		this.to = to;
		this.cc = cc;
		this.message = message;
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EmailTemplate [id=" + id + ", name=" + name + ", from=" + from + ", subject=" + subject + ", to=" + to
				+ ", cc=" + cc + ", message=" + message + "templateID :" + templateID + "description :" + description
				+ "]";
	}

	/**
	 * @return the templateID
	 */
	public String getTemplateID() {
		return templateID;
	}

	/**
	 * @param templateID
	 *            the templateID to set
	 */
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
