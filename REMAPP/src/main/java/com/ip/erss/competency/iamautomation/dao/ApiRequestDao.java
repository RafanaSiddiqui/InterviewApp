package com.ip.erss.competency.iamautomation.dao;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.ApiRequest;

public interface ApiRequestDao {
    void create(ApiRequest apiRequest);

    void update(ApiRequest apiRequest);

    ApiRequest getApiRequestById(long id);

    void delete(long id);
    void deleteAllApi(ApiRequest apiRequest);
	List<ApiRequest> loadAll();
	
	ApiRequest getApiRequestByName(String name);
}
