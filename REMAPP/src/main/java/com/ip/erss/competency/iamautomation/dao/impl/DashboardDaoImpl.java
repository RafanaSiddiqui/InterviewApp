package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.DashboardDao;
import com.ip.erss.competency.iamautomation.model.DashboardBarChartData;
import com.ip.erss.competency.iamautomation.model.DashboardPieChartData;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	
	private final Logger logger = LoggerFactory.getLogger(DashboardDaoImpl.class);
	
    @PersistenceContext
    private EntityManager entityManager;


	@Override
	public List<DashboardPieChartData> loadPieChartData() {
		List<DashboardPieChartData> dashboardPieChartList = new ArrayList<DashboardPieChartData>();
		try {
			Query query = entityManager.createNativeQuery("SELECT distinct count(status), status FROM APP_REQUEST group by status");
			List<Object[]> results =  query.getResultList();
			for (Object[] result : results) {
				DashboardPieChartData dashboardPieChartData = new DashboardPieChartData();
				dashboardPieChartData.setStatusCount(((Number) result[0]).intValue());
				dashboardPieChartData.setAppStatus((String) result[1]);
				dashboardPieChartList.add(dashboardPieChartData);
			}
		 
		}catch(Exception e) {
			logger.error("Exception  ::  "+e.getMessage());
		}
		return dashboardPieChartList;
	}


	@Override
	public List<DashboardBarChartData> loadBarChartData() {
		List<DashboardBarChartData> dashboardBarChartList = new ArrayList<DashboardBarChartData>();
		try {
			Query query = entityManager.createNativeQuery("SELECT DATEDIFF('MILLISECOND',timestamp '1970-01-01 00:00:00' ,  UPDATED_DATE) UPDATED_DATE, count(*) FROM APP_REQUEST where status='Completed' group by UPDATED_DATE");
			dashboardBarChartList = query.getResultList();
		 
		}catch(Exception e) {
			logger.error("Exception  ::  "+e.getMessage());
		}
		return dashboardBarChartList;
	}



}
