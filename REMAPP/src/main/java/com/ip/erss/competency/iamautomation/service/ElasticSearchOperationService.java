package com.ip.erss.competency.iamautomation.service;

import com.ip.erss.competency.iamautomation.model.ElasticDetails;

public interface ElasticSearchOperationService {

	String loadRequestToElastic(ElasticDetails elasticDetails, String masterDemandRequestJson, Long masterDemandRequestId);

	String deleteElasticEntry(ElasticDetails elasticDetails, Long demandRequestId);

}
