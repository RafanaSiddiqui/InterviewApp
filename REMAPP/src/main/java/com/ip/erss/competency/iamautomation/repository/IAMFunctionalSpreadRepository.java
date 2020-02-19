package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.IAMFunctionalSpread;

public interface IAMFunctionalSpreadRepository extends JpaRepository<IAMFunctionalSpread, Long>{

	IAMFunctionalSpread findById(long iamFunctionalSpreadId);
	
	List<IAMFunctionalSpread> findAllByOrderByCreatedDateDesc();

}
