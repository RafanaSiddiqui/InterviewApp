package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.NoBot;
import com.ip.erss.competency.iamautomation.model.Tasks;

public interface NoBotService {
	
	List<NoBot> loadAllNoBots();
	
	void addNoBot(NoBot bot);

	NoBot loadNoBotById(long botId);

	void updateNoBot(NoBot bot);

	void deleteNoBot(long botId);

	void addTask(Tasks tasks);

	List<NoBot> loadAllNoBotApprovalsPending();

	List<NoBot> loadAllNoBotsByStatus();

	List<NoBot> loadAllNoBotsName(String userName);
	
}
