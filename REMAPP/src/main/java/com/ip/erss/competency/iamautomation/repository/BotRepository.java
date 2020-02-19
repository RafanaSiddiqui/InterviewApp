package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.Bots;

public interface BotRepository extends JpaRepository<Bots, Long>{

	Bots findByBotId(long botId);
	
	List<Bots> findAllByOrderByCreatedDateDesc();

	List<Bots> findByBotName(String botName);

}
