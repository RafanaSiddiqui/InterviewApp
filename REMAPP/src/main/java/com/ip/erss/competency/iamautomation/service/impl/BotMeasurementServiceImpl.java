package com.ip.erss.competency.iamautomation.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.BotMeasurements;
import com.ip.erss.competency.iamautomation.model.Configuration;
import com.ip.erss.competency.iamautomation.repository.BotMeasurementRepository;
import com.ip.erss.competency.iamautomation.service.BotMeasurementService;

@Service
@Transactional
public class BotMeasurementServiceImpl implements BotMeasurementService {
	
	@Autowired
	private BotMeasurementRepository botMeasurementRepository;
	
	@Override
	public List<BotMeasurements> loadAllBotMeasurements() {
		return botMeasurementRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addBotMeasurement(BotMeasurements botMeasurement) {
		botMeasurementRepository.saveAndFlush(botMeasurement);
	}

	@Override
	public BotMeasurements loadBotMeasurementById(long botMeasurementId) {
		return botMeasurementRepository.findById(botMeasurementId);
	}

	@Override
	public void updateBotMeasurement(BotMeasurements botMeasurement) {
		botMeasurementRepository.saveAndFlush(botMeasurement);
	}

	@Override
	public void deleteBotMeasurement(long botMeasurementId) {
		botMeasurementRepository.delete(botMeasurementId);
	}

	@Override
	public List<Configuration> getDefaulterList(Date createdMonth) {
		return botMeasurementRepository.getDefaulterList(createdMonth);
	}


}
