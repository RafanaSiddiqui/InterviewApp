package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.model.IAMFunctionalSpread;
import com.ip.erss.competency.iamautomation.repository.BotRepository;
import com.ip.erss.competency.iamautomation.repository.IAMFunctionalSpreadRepository;
import com.ip.erss.competency.iamautomation.service.BotService;
import com.ip.erss.competency.iamautomation.service.IAMFunctionalSpreadService;

@Service
@Transactional
public class IAMFunctionalSpreadServiceImpl implements IAMFunctionalSpreadService {
	
	@Autowired
	private IAMFunctionalSpreadRepository iamFunctionalSpreadRepository;
	
	@Override
	public List<IAMFunctionalSpread> loadAllIAMFunctionalSpreads() {
		return iamFunctionalSpreadRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addIAMFunctionalSpread(IAMFunctionalSpread iamFunctionalSpread) {
		iamFunctionalSpreadRepository.saveAndFlush(iamFunctionalSpread);
	}

	@Override
	public IAMFunctionalSpread loadIAMFunctionalSpreadById(long iamFunctionalSpreadId) {
		return iamFunctionalSpreadRepository.findById(iamFunctionalSpreadId);
	}

	@Override
	public void updateIAMFunctionalSpread(IAMFunctionalSpread iamFunctionalSpread) {
		iamFunctionalSpreadRepository.saveAndFlush(iamFunctionalSpread);
	}

	@Override
	public void deleteIAMFunctionalSpread(long iamFunctionalSpreadId) {
		iamFunctionalSpreadRepository.delete(iamFunctionalSpreadId);
	}

}
