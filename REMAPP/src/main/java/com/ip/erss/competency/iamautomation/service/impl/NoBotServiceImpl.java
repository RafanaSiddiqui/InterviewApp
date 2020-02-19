package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.NoBot;
import com.ip.erss.competency.iamautomation.model.Tasks;
import com.ip.erss.competency.iamautomation.repository.NoBotRepository;
import com.ip.erss.competency.iamautomation.repository.TaskRepository;
import com.ip.erss.competency.iamautomation.service.NoBotService;

@Service
@Transactional
public class NoBotServiceImpl implements NoBotService {
	
	@Autowired
	private NoBotRepository noBotRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<NoBot> loadAllNoBots() {
		return noBotRepository.findAllByOrderByCreatedDateDesc();
	}
	
	@Override
	public void addNoBot(NoBot noBot) {
		noBotRepository.saveAndFlush(noBot);
	}

	@Override
	public NoBot loadNoBotById(long noBotId) {
		return noBotRepository.findByNoBotId(noBotId);
	}

	@Override
	public void updateNoBot(NoBot noBotDecForm) {
		noBotRepository.saveAndFlush(noBotDecForm);
	}

	@Override
	public void deleteNoBot(long botId) {
		noBotRepository.delete(botId);
	}

	@Override
	public void addTask(Tasks tasks) {
		taskRepository.saveAndFlush(tasks);
		
	}

	@Override
	public List<NoBot> loadAllNoBotApprovalsPending() {
		return noBotRepository.loadAllNoBotApprovalsPending();
	}

	@Override
	public List<NoBot> loadAllNoBotsByStatus() {
		return noBotRepository.loadAllNoBotsByStatus();
	}

	@Override
	public List<NoBot> loadAllNoBotsName(String userName) {
		return noBotRepository.loadAllNoBotsName(userName);
	}

}
