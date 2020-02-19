package com.ip.erss.competency.iamautomation.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.dao.ElasticSearchOperationDao;
import com.ip.erss.competency.iamautomation.model.ElasticDetails;
import com.ip.erss.competency.iamautomation.service.ElasticSearchOperationService;

@Service
@Transactional
public class ElasticSearchOperationServiceImpl implements ElasticSearchOperationService {

	@Autowired
	private ElasticSearchOperationDao elasticSearchOperationDao;
	
	@Override
	public String loadRequestToElastic(ElasticDetails elasticDetails, String masterDemandRequestJson, Long masterDemandRequestId) {
		return elasticSearchOperationDao.loadRequestToElastic(elasticDetails, masterDemandRequestJson, masterDemandRequestId);
	}

	@Override
	public String deleteElasticEntry(ElasticDetails elasticDetails, Long demandRequestId) {
		return elasticSearchOperationDao.deleteElasticEntry(elasticDetails, demandRequestId);
	}

}
