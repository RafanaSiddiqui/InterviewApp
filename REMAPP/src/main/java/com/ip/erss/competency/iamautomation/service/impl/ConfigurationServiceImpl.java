package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.ConfigurationDAO;
import com.ip.erss.competency.iamautomation.model.Configuration;
import com.ip.erss.competency.iamautomation.service.ConfigurationService;

@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigurationDAO configurationDAO;

	@Override
	public void createConfiguration(Configuration configuration) {
		configurationDAO.createConfiguration(configuration);

	}

	@Override
	public void updateConfiguration(Configuration configuration) {
		configurationDAO.updateConfiguration(configuration);

	}

	@Override
	public void deleteConfiguration(Long settingID) {
		configurationDAO.deleteConfiguration(settingID);

	}

	@Override
	public Configuration fetchConfiguration(Long settingID) {

		return configurationDAO.fetchConfiguration(settingID);
	}

	@Override
	public List<Configuration> fetchAllConfigurationDetails() {

		return configurationDAO.fetchAllConfigurationDetails();
	}

	@Override
	public List<Configuration> fetchConfiguration(String category, String name) {

		return configurationDAO.fetchConfiguration(category, name);
	}

	@Override
	public List<Configuration> fetchConfiguration(String category) {
		return configurationDAO.fetchConfiguration(category);
	}

}
