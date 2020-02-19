package com.ip.erss.competency.iamautomation.dao;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.DashboardBarChartData;
import com.ip.erss.competency.iamautomation.model.DashboardPieChartData;

public interface DashboardDao {
    
	List<DashboardPieChartData> loadPieChartData();

	List<DashboardBarChartData> loadBarChartData();
	
}
