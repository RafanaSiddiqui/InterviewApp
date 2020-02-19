/**
 * 
 */
package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 134930
 *
 */

@Entity
@Table(name = "IAM_CONFIGURATION")
public class Configuration implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8936279250827030792L;


	/**
	 * 
	 */
	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue
	@Column(name = "SETTING_ID")
	private Long settingID;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "NAME")
	private String name;
	@Column(name = "VALUE")
	private String value;
	
	@Column
	private String configGroupName;
	
	
	/**
	 * @param settingID
	 * @param category
	 * @param name
	 * @param value
	 */
	public Configuration(Long settingID, String category, String name, String value, String configGroupName) {
		super();
		this.settingID = settingID;
		this.category = category;
		this.name = name;
		this.value = value;
		this.configGroupName = configGroupName;
	}
	/**
	 * @return the settingID
	 */
	public Long getSettingID() {
		return settingID;
	}
	/**
	 * @param settingID the settingID to set
	 */
	public void setSettingID(Long settingID) {
		this.settingID = settingID;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	public String getConfigGroupName() {
		return configGroupName;
	}
	public void setConfigGroupName(String configGroupName) {
		this.configGroupName = configGroupName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IAMSetting [settingID=" + settingID + ", category=" + category + ", name=" + name + ", value=" + value + ",configGroupName=" + configGroupName + "]";
	}

	
	
}
