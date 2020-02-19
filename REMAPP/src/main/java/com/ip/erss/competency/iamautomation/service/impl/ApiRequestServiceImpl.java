package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.ApiRequestDao;
import com.ip.erss.competency.iamautomation.model.ApiRequest;
import com.ip.erss.competency.iamautomation.service.ApiRequestService;

@Service
@Transactional
public class ApiRequestServiceImpl implements ApiRequestService {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiRequestServiceImpl.class);
	
    @Autowired
    private ApiRequestDao apiRequestDao;

    @Override
    public void create(ApiRequest apiRequest) {
        apiRequestDao.create(apiRequest);
    }

	@Override
	public List<ApiRequest> loadAll() {
		
		return apiRequestDao.loadAll();
	}

	@Override
	public ApiRequest getapiRequest(long id) {
		
		return apiRequestDao.getApiRequestById(id);
	}

	@Override
	public void updateApi(ApiRequest apiRequest) {
		logger.info("Inside updateApi method:::::::");
		 apiRequestDao.update(apiRequest);
	}

	@Override
	public void deleteApi(long id) {
		 apiRequestDao.delete(id);
		
	}
	
	@Override
	public void deleteAllApi(ApiRequest apiRequest) {
		 apiRequestDao.deleteAllApi(apiRequest);
		
	}

	@Override
	public ApiRequest getapiByName(String name) {
		
		return apiRequestDao.getApiRequestByName(name);
	}
	
}
