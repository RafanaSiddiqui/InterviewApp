package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.SupervisorAssociateMapping;

public interface SupervisorAssociateMappingRepository extends JpaRepository<SupervisorAssociateMapping, Long>{

	SupervisorAssociateMapping findById(long skillAttestationId);
	
	//List<SupervisorAssociateMapping> findAllByOrderByCreatedDateDesc();

	List<SupervisorAssociateMapping> findBySupervisorId(String accountName);

}
