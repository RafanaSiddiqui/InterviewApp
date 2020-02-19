package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.model.SkillAttestation;
import com.ip.erss.competency.iamautomation.model.SkillDetails;
import com.ip.erss.competency.iamautomation.model.SupervisorAssociateMapping;
import com.ip.erss.competency.iamautomation.repository.BotRepository;
import com.ip.erss.competency.iamautomation.repository.SkillAttestationRepository;
import com.ip.erss.competency.iamautomation.repository.SkillDetailsRepository;
import com.ip.erss.competency.iamautomation.repository.SupervisorAssociateMappingRepository;
import com.ip.erss.competency.iamautomation.service.BotService;
import com.ip.erss.competency.iamautomation.service.SkillAttestationService;

@Service
@Transactional
public class SkillAttestationServiceImpl implements SkillAttestationService {
	
	@Autowired
	private SkillAttestationRepository skillAttestationRepository;
	
	@Autowired
	private SkillDetailsRepository skillDetailsRepository;
	
	@Autowired 
	private SupervisorAssociateMappingRepository supervisorAssociateMappingRepository;
	
	@Override
	public List<SkillAttestation> loadAllSkillAttestation() {
		return skillAttestationRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addSkillAttestation(SkillAttestation skillAttestation) {
		skillAttestationRepository.saveAndFlush(skillAttestation);
	}

	@Override
	public SkillAttestation loadSkillAttestationById(long skillAttestationId) {
		return skillAttestationRepository.findBySkillAttestationId(skillAttestationId);
	}

	@Override
	public void updateSkillAttestation(SkillAttestation skillAttestation) {
		skillAttestationRepository.saveAndFlush(skillAttestation);
	}

	@Override
	public void deleteSkillAttestation(long skillAttestationId) {
		skillAttestationRepository.delete(skillAttestationId);
	}

	@Override
	public List<SupervisorAssociateMapping> findBySupervisorId(String userName) {
		return supervisorAssociateMappingRepository.findBySupervisorId(userName);
	}

	@Override
	public List<SkillAttestation> findAllBySupervisorId(String userName) {
		return skillAttestationRepository.findAllBySupervisorId(userName);
	}

	@Override
	public List<SkillDetails> findAllByAssociateId(String associateId) {
		return skillDetailsRepository.findAllByAssociateId(associateId);
	}

	@Override
	public void updateSkillDetails(SkillDetails skillDetails) {
		SkillDetails savedSkillDetail = skillDetailsRepository.saveAndFlush(skillDetails);
	}

	@Override
	public void deleteSkill(long skillId) {
		skillDetailsRepository.delete(skillId);
	}

	@Override
	public boolean existsByAssociateId(String associateId) {
		return skillAttestationRepository.existsByAssociateId(associateId);
	}

}
