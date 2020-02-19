package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.Tracker;

public interface TrackerRepository extends JpaRepository<Tracker, Long>{

	Tracker findByTrackerId(long trackerId);
	
	List<Tracker> findAllByOrderByCreatedDateDesc();

	List<Tracker> findByAccountName(String accountName);

}
