package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.Bots;

public interface BotService {

	List<Bots> loadAllBots();

	void addBot(Bots bot);

	Bots loadBotById(long botId);

	void updateBot(Bots bot);

	void deleteBot(long botId);

	List<Bots> loadBotByName(String botName);
	
}
