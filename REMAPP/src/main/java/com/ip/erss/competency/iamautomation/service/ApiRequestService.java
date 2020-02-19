package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.ApiRequest;

public interface ApiRequestService {
    void create(ApiRequest apiRequest);
    List<ApiRequest> loadAll();
    ApiRequest getapiRequest(long id);
    void updateApi(ApiRequest ApiRequest);
    void deleteApi(long id);
    void deleteAllApi(ApiRequest apiRequest);
    ApiRequest getapiByName(String Name);
}
