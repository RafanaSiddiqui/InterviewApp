package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ip.erss.competency.iamautomation.model.NoBot;

public interface NoBotRepository extends JpaRepository<NoBot, Long>{

	NoBot findByNoBotId(long noBotId);

	List<NoBot> findAllByOrderByCreatedDateDesc();
	
	@Query(value = "select n from NoBot n where n.status = 'PENDING'")
	List<NoBot> loadAllNoBotApprovalsPending();
	
	@Query(value = "select n from NoBot n where n.status <> 'PENDING'")
	List<NoBot> loadAllNoBotsByStatus();
	
	@Query(value = "select n from NoBot n where n.createdBy = :userName")
	List<NoBot> loadAllNoBotsName(@Param("userName") String userName);

	
}
