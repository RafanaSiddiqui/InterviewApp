package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.SkillDetails;


public interface SkillDetailsRepository extends JpaRepository<SkillDetails, Long>{

	List<SkillDetails> findAllByAssociateId(String associateId);

}
