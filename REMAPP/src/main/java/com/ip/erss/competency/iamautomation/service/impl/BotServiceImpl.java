package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.repository.BotRepository;
import com.ip.erss.competency.iamautomation.service.BotService;

@Service
@Transactional
public class BotServiceImpl implements BotService {
	
	@Autowired
	private BotRepository botRepository;
	
	@Override
	public List<Bots> loadAllBots() {
		return botRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addBot(Bots bot) {
		botRepository.saveAndFlush(bot);
	}

	@Override
	public Bots loadBotById(long botId) {
		return botRepository.findByBotId(botId);
	}

	@Override
	public void updateBot(Bots bot) {
		botRepository.saveAndFlush(bot);
	}

	@Override
	public void deleteBot(long botId) {
		botRepository.delete(botId);
	}

	@Override
	public List<Bots> loadBotByName(String botName) {
		return botRepository.findByBotName(botName);
	}


}
