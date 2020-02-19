package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.ApiRequest;
import com.ip.erss.competency.iamautomation.model.DashboardBarChartData;
import com.ip.erss.competency.iamautomation.model.DashboardPieChartData;

public interface DashboardService {

	List<DashboardPieChartData> loadPieChartData();

	List<DashboardBarChartData> loadBarChartData();
	
}
