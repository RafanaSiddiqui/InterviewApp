package com.ip.erss.competency.iamautomation.dao;

import com.ip.erss.competency.iamautomation.model.ElasticDetails;

public interface ElasticSearchOperationDao {

	String loadRequestToElastic(ElasticDetails elasticDetails, String masterDemandRequestJson, Long masterDemandRequestId);

	String deleteElasticEntry(ElasticDetails elasticDetails, Long demandRequestId);

}
