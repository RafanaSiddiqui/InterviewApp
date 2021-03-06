package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.Configuration;

public interface ConfigurationService {

	/**
	 * @param iamSetting
	 */
	void createConfiguration(Configuration iamSetting);

	/**
	 * @param iamSetting
	 */
	void updateConfiguration(Configuration iamSetting);

	/**
	 * @param settingID
	 */
	void deleteConfiguration(Long settingID);

	/**
	 * @param settingID
	 */
	Configuration fetchConfiguration(Long settingID);

	/**
	 * @return List<AppRequest>
	 */
	List<Configuration> fetchAllConfigurationDetails();

	/**
	 * @param category
	 * @param name
	 *
	 * @return
	 */
	List<Configuration> fetchConfiguration(String category, String name);

	/**
	 * @param category

	 *
	 * @return
	 */
	List<Configuration> fetchConfiguration(String category);

}
