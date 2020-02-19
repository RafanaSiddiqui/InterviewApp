package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.ApiRequestDao;
import com.ip.erss.competency.iamautomation.dao.DashboardDao;
import com.ip.erss.competency.iamautomation.model.ApiRequest;
import com.ip.erss.competency.iamautomation.model.DashboardBarChartData;
import com.ip.erss.competency.iamautomation.model.DashboardPieChartData;
import com.ip.erss.competency.iamautomation.service.ApiRequestService;
import com.ip.erss.competency.iamautomation.service.DashboardService;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {
	
	private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);
	
	@Autowired
	private	DashboardDao dashboardDao;

	@Override
	public List<DashboardPieChartData> loadPieChartData() {
		return dashboardDao.loadPieChartData();
	}

	@Override
	public List<DashboardBarChartData> loadBarChartData() {
		return dashboardDao.loadBarChartData();
	}
	
    
	
}
