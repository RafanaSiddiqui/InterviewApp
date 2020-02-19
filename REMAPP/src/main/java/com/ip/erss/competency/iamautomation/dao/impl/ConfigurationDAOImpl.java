/**
 *
 */
package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.ConfigurationDAO;
import com.ip.erss.competency.iamautomation.model.Configuration;

/**
 * @author 134930
 *
 */
@Repository
public class ConfigurationDAOImpl implements ConfigurationDAO {

	private final Logger logger = LoggerFactory.getLogger(ConfigurationDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ip.erss.competency.iamautomation.dao.ConfigurationDAO#
	 * createConfiguration(com.ip.erss.competency.iamautomation.model.
	 * IAMSetting)
	 */
	@Override
	public void createConfiguration(Configuration iamSetting) {

		entityManager.persist(iamSetting);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ip.erss.competency.iamautomation.dao.ConfigurationDAO#
	 * updateConfiguration(com.ip.erss.competency.iamautomation.model.
	 * IAMSetting)
	 */
	@Override
	public void updateConfiguration(Configuration iamSetting) {
		entityManager.merge(iamSetting);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ip.erss.competency.iamautomation.dao.ConfigurationDAO#
	 * deleteConfiguration(java.lang.Long)
	 */
	@Override
	public void deleteConfiguration(Long settingID) {
		Configuration iamSetting = this.fetchConfiguration(settingID);
		if (iamSetting != null) {
			entityManager.remove(iamSetting);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ip.erss.competency.iamautomation.dao.ConfigurationDAO#
	 * fetchConfiguration(java.lang.Long)
	 */
	@Override
	public Configuration fetchConfiguration(Long settingID) {
		return entityManager.find(Configuration.class, settingID);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ip.erss.competency.iamautomation.dao.ConfigurationDAO#
	 * fetchAllConfigurationDetails()
	 */
	@Override
	public List<Configuration> fetchAllConfigurationDetails() {
		List<Configuration> configurationList = null;
		try {
			configurationList = entityManager.createQuery("SELECT p FROM Configuration p").getResultList();
		} catch(Exception e) {
			logger.error("fetchAllConfigurationDetails Exception  ::  "+e.getMessage());
		}
		return configurationList;
		
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ip.erss.competency.iamautomation.dao.ConfigurationDAO#
	 * fetchConfiguration(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Configuration> fetchConfiguration(String category, String name) {
		List<Configuration> configurationList = null;
		try {
			Query query = entityManager.createQuery("SELECT p FROM Configuration p where p.category =? and p.name=?");
			query.setParameter(1, category);
			query.setParameter(2, name);
			configurationList = query.getResultList();
		} catch(Exception e) {
			logger.error("fetchConfiguration Exception  ::  "+e.getMessage());
		}
		return configurationList;
	}

	@Override
	public List<Configuration> fetchConfiguration(String category) {
		List<Configuration> configurationList = null;
		try {
			Query query = entityManager.createQuery("SELECT p FROM Configuration p where p.category =?");
			query.setParameter(1, category);
			configurationList = query.getResultList();
		} catch(Exception e) {
			logger.error("fetchConfiguration Exception  ::  "+e.getMessage());
		}
		return configurationList;
	}

}
