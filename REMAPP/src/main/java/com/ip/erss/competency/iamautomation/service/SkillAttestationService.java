package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.SkillAttestation;
import com.ip.erss.competency.iamautomation.model.SkillDetails;
import com.ip.erss.competency.iamautomation.model.SupervisorAssociateMapping;

public interface SkillAttestationService {

	List<SkillAttestation> loadAllSkillAttestation();

	void addSkillAttestation(SkillAttestation skillAttestation);

	SkillAttestation loadSkillAttestationById(long skillAttestationId);

	void updateSkillAttestation(SkillAttestation skillAttestation);

	void deleteSkillAttestation(long skillAttestationId);

	List<SupervisorAssociateMapping> findBySupervisorId(String userName);

	List<SkillAttestation> findAllBySupervisorId(String userName);

	List<SkillDetails> findAllByAssociateId(String associateId);

	void updateSkillDetails(SkillDetails skillDetails);

	void deleteSkill(long skillId);

	boolean existsByAssociateId(String associateId);

}
