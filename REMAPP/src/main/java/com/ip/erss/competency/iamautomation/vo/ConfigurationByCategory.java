/**
 * 
 */
package com.ip.erss.competency.iamautomation.vo;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.Configuration;

/**
 * @author 134930
 *
 */
public class ConfigurationByCategory {
	
	/**
	 * Reperesents category
	 */
	private String category;
	
	/**
	 * Reperesents configurationList
	 */
	private List<Configuration> configurationList;

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
	 * @return the configurationList
	 */
	public List<Configuration> getConfigurationList() {
		return configurationList;
	}

	/**
	 * @param configurationList the configurationList to set
	 */
	public void setConfigurationList(List<Configuration> configurationList) {
		this.configurationList = configurationList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		return "ConfigurationByCategory [category=" + category + ", configurationList=" + configurationList + "]";
	}*/
	
	

}
