package com.ip.erss.competency.iamautomation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ip.erss.competency.iamautomation.model.BotMeasurements;
import com.ip.erss.competency.iamautomation.model.Configuration;

public interface BotMeasurementRepository extends JpaRepository<BotMeasurements, Long>{

	BotMeasurements findById(long botMeasurementId);
	
	List<BotMeasurements> findAllByOrderByCreatedDateDesc();
	
	@Query(value = "select c from Configuration c where c.category = 'CustomerDetails' "
			+ "and c.name not in (select customerName from BotMeasurements where month(createdMonth) = month(?))")
	List<Configuration> getDefaulterList(Date createdMonth);

}
