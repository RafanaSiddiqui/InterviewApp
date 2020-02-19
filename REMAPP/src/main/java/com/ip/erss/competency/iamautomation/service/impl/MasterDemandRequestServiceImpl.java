package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.dao.MasterDemandRequestDao;
import com.ip.erss.competency.iamautomation.model.MasterDemandRequest;
import com.ip.erss.competency.iamautomation.model.Roles;
import com.ip.erss.competency.iamautomation.model.UserRoles;
import com.ip.erss.competency.iamautomation.repository.MasterDemandRepository;
import com.ip.erss.competency.iamautomation.repository.RolesRepository;
import com.ip.erss.competency.iamautomation.repository.UserRolesRepository;
import com.ip.erss.competency.iamautomation.service.MasterDemandRequestService;

@Service
@Transactional
public class MasterDemandRequestServiceImpl implements MasterDemandRequestService {
	
	@Autowired
	private MasterDemandRequestDao masterDemandRequestDao;
	
	@Autowired
	private MasterDemandRepository masterDemandRepository;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	@Override
	public Long createDemandRequest(MasterDemandRequest masterDemandRequest) {
		return masterDemandRequestDao.createDemandRequest(masterDemandRequest);
	}

	@Override
	public List<MasterDemandRequest> loadAllDemandRequests() {
		return masterDemandRequestDao.loadAllDemandRequests();
	}

	@Override
	public MasterDemandRequest loadMasterDemandRequestById(Long demandRequestId) {
		return masterDemandRequestDao.loadMasterDemandRequestById(demandRequestId);
	}

	@Override
	public void updateDemandRequest(MasterDemandRequest masterDemandRequest) {
		masterDemandRequestDao.updateDemandRequest(masterDemandRequest);
	}

	@Override
	public void deleteDemandRequest(Long demandRequestId) {
		masterDemandRequestDao.deleteDemandRequest(demandRequestId);
		
	}

	@Override
	public Page<MasterDemandRequest> loadPageableMasterDemandRequest(PageRequest pageRequest) {
		return masterDemandRepository.findAll(pageRequest);
		
	}

	@Override
	public Page<MasterDemandRequest> loadPageableDemandReqBySoId(String soId, Pageable pageable) {
		return masterDemandRepository.findBySoIdContaining(soId, pageable);
	}

	@Override
	public List<Roles> fetchAllRoles() {
		return rolesRepository.findAll();
	}

	@Override
	public List<MasterDemandRequest> loadAllDemandRequestsByUser(String userName) {
		return masterDemandRequestDao.loadAllDemandRequestsByUser(userName);
	}

	@Override
	public List<MasterDemandRequest> loadAllDemandRequestsByFlag() {
		return masterDemandRepository.findByFlaggedForHireTrue();
	}

	@Override
	public List<UserRoles> fetchAllUserRoles() {
		return userRolesRepository.findAll();
	}



}
