package com.ip.erss.competency.iamautomation.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ip.erss.competency.iamautomation.model.Configuration;
import com.ip.erss.competency.iamautomation.service.ConfigurationService;
import com.ip.erss.competency.iamautomation.utils.AppConstants;
import com.ip.erss.competency.iamautomation.vo.ConfigurationByCategory;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ip.erss.competency.iamautomation.service,com.ip.erss.competency.sp.service.impl,"
		+ "com.ip.erss.competency.iamautomation.dao,com.ip.erss.competency.iamautomation.dao.impl")
public class ConfigurationRESTController {

	private final Logger logger = LoggerFactory.getLogger(ConfigurationRESTController.class);

	@Autowired
	ConfigurationService configurationService;

	@RequestMapping(value = "/configuration/", method = RequestMethod.POST)
	public ResponseEntity<Void> createconfiguration(@RequestBody Configuration configuration) {
		logger.info("Creating API register ID --> should be null " + configuration.getSettingID());

		logger.info("Creating API register ID --> should be null " + configuration.toString());
		configurationService.createConfiguration(configuration);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/configuration/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateConfiguration(@RequestBody Configuration iamSetting) {
		logger.info("updating AppRequest -->AppRequest ID --> should NOT be null " + iamSetting.getSettingID());

		Configuration iamSetting1 = configurationService.fetchConfiguration(iamSetting.getSettingID());
		if (iamSetting1 == null) {
			logger.info("there is no records found " + iamSetting.getSettingID());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		configurationService.updateConfiguration(iamSetting);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/configuration/{settingID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteConfiguration(@PathVariable("settingID") Long settingID) {

		Configuration iamSetting = configurationService.fetchConfiguration(settingID);
		if (iamSetting == null) {
			logger.info("there is no records found " + settingID);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		configurationService.deleteConfiguration(settingID);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/configurationShowAll/", method = RequestMethod.GET)
	public List<Configuration> fetchAllConfigurationDetails() {
		logger.info("Going to fetch All ConfigurationDetails ");
		return configurationService.fetchAllConfigurationDetails();

	}

	@RequestMapping(value = "/configurationByCategory/", method = RequestMethod.GET)
	public List<ConfigurationByCategory> fetchAllconfigurationByCategory() {
		logger.info("Going to fetch All configurationByCategory ");

		Map<String, List<Configuration>> categoryConfigMap = new HashMap<String, List<Configuration>>();

		List<Configuration> configurationList = configurationService.fetchAllConfigurationDetails();
		for (Configuration configuration : configurationList) {

			if (categoryConfigMap.get(configuration.getCategory()) != null) {
				categoryConfigMap.get(configuration.getCategory()).add(configuration);
			} else {
				List<Configuration> configurationListNew = new ArrayList<Configuration>();
				configurationListNew.add(configuration);
				categoryConfigMap.put(configuration.getCategory(), configurationListNew);
			}
		}

		List<ConfigurationByCategory> configurationByCategoryList = new ArrayList<ConfigurationByCategory>();

		categoryConfigMap.entrySet().forEach(entry -> {
		    logger.info("Key : " + entry.getKey() + " Value : " + entry.getValue());
		    ConfigurationByCategory ConfigurationByCategory = new ConfigurationByCategory();
		    ConfigurationByCategory.setCategory(entry.getKey());
		    ConfigurationByCategory.setConfigurationList(entry.getValue());
		    configurationByCategoryList.add(ConfigurationByCategory);
		});

		return configurationByCategoryList;

	}

	@RequestMapping(value = "/configurationByCategoryandName/{category}/{name}", method = RequestMethod.GET)
	public List<Configuration> fetchConfigurationBycategoryandName(@PathVariable("category") String category,
			@PathVariable("name") String name) {
		List<Configuration> configList = new ArrayList<Configuration>();
		if(null != category && null != name) {
			configList =  configurationService.fetchConfiguration(category, name);
		}
		return configList;
	}


	@RequestMapping(value = "/configurationByCategory/{category}", method = RequestMethod.GET)
	public List<Configuration> fetchConfigurationBycategoryandName(@PathVariable("category") String category) {
		List<Configuration> configList = new ArrayList<Configuration>();
		if(null != category) {
			configList = configurationService.fetchConfiguration(category);
		}
		return configList;
	}

	@RequestMapping(value = "/fetchtemplateGUIConfiguration/", method = RequestMethod.GET)
	public List<Configuration> fetchtemplateGUIConfiguration() {

		logger.info("Going to fetch fetchtemplateGUIConfiguration");

		List<Configuration> iamTemplateGUIConfigutations = null;

		List<Configuration> iamProductConfigutation = configurationService.fetchConfiguration(AppConstants.CAT_IAM_PRODUCT);

		for(Configuration configuration : iamProductConfigutation) {
				if(configuration.getValue().equalsIgnoreCase("SailPoint")) {
					iamTemplateGUIConfigutations = configurationService.fetchConfiguration(AppConstants.CAT_SAILPOINT_TEMPLATE);
				} else if(configuration.getValue().equalsIgnoreCase("OIM")) {
					iamTemplateGUIConfigutations = configurationService.fetchConfiguration(AppConstants.CAT_OIM_TEMPLATE);
			}
		}

		return iamTemplateGUIConfigutations;
	}

}
