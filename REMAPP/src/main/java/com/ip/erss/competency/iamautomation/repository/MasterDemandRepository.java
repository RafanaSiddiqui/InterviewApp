package com.ip.erss.competency.iamautomation.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;

public interface MasterDemandRepository extends JpaRepository<MasterDemandRequest, Long> {
	
	//@Query("select m from MasterDemandRequest m where c.soid like%:soId%")
	Page<MasterDemandRequest> findBySoIdContaining(String soId, Pageable pageable);
	
	List<MasterDemandRequest> findByFlaggedForHireTrue();

}
