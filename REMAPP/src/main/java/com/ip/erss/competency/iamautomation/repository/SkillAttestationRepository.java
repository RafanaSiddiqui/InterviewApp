package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ip.erss.competency.iamautomation.model.SkillAttestation;

public interface SkillAttestationRepository extends JpaRepository<SkillAttestation, Long>{

	SkillAttestation findBySkillAttestationId(long skillAttestationId);
	
	List<SkillAttestation> findAllByOrderByCreatedDateDesc();
	
	//@Query("select sa from SkillAttestation sa inner join sa.supervisorAssociateMapping sam where sam.supervisorId = :userName")
	List<SkillAttestation> findAllBySupervisorId(String userName);
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SkillAttestation s WHERE s.associateId = :associateId")
	boolean existsByAssociateId(@Param("associateId") String associateId);

	//List<SkillAttestation> findByAccountName(String accountName);

}
