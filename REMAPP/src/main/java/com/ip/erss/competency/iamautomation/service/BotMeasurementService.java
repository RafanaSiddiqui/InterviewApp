package com.ip.erss.competency.iamautomation.service;

import java.util.Date;
import java.util.List;

import com.ip.erss.competency.iamautomation.model.BotMeasurements;
import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.model.Configuration;

public interface BotMeasurementService {

	List<BotMeasurements> loadAllBotMeasurements();

	void addBotMeasurement(BotMeasurements botMeasurement);

	BotMeasurements loadBotMeasurementById(long botMeasurementId);

	void updateBotMeasurement(BotMeasurements botMeasurement);

	void deleteBotMeasurement(long botMeasurementId);

	List<Configuration> getDefaulterList(Date createdMonth);
	
}
