package com.ip.erss.competency.iamautomation.web;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ip.erss.competency.iamautomation.model.DashboardBarChartData;
import com.ip.erss.competency.iamautomation.model.DashboardPieChartData;
import com.ip.erss.competency.iamautomation.service.DashboardService;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ip.erss.competency.iamautomation.service,com.ip.erss.competency.sp.service.impl,"
		+ "com.ip.erss.competency.iamautomation.dao,com.ip.erss.competency.iamautomation.dao.impl,com.ip.erss.competency.sp.service.impl")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private DashboardService dashboardService;
	
	@Value("${kibana.baseUrl}")
	private String baseUrl;

	@RequestMapping(value = "/loadPieChartData/", method = RequestMethod.GET)
	public List<DashboardPieChartData> loadPieChartData() {
		logger.info("Going to fetch PieChartData");
		List<DashboardPieChartData> dashboardPieChartList = null;
		try {
			dashboardPieChartList = dashboardService.loadPieChartData();
			for(DashboardPieChartData dashboardPieChartData : dashboardPieChartList) {
				if(dashboardPieChartData.getAppStatus().equalsIgnoreCase("Completed")) {
					dashboardPieChartData.setChartColor("#84e184");
				} else if(dashboardPieChartData.getAppStatus().equalsIgnoreCase("Pending")) {
					dashboardPieChartData.setChartColor("#f7c96e");
				} else if(dashboardPieChartData.getAppStatus().equalsIgnoreCase("Reviewed")) {
					dashboardPieChartData.setChartColor("#3dc7f5");
				} else {
					dashboardPieChartData.setChartColor("#ff4d4d");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dashboardPieChartList;
	}
	
	
	@RequestMapping(value = "/loadBarChartData/", method = RequestMethod.GET)
	public List<DashboardBarChartData> loadBarChartData() {
		logger.info("Going to fetch BarChartData");
		List<DashboardBarChartData> dashboardBarChartData = null;
		try {
			dashboardBarChartData = dashboardService.loadBarChartData();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dashboardBarChartData;
	}
	
	@RequestMapping(value = "/loadBaseUrl/", method = RequestMethod.GET)
	public List<String> loadBaseUrl() {
		List<String> urlList = new ArrayList<String>(); 
		urlList.add(baseUrl);
		System.out.println("baseurl  ::  "+baseUrl);
		return urlList;
	}

		
}
