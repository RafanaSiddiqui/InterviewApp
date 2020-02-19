package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.Bots;
import com.ip.erss.competency.iamautomation.repository.AccountBaseRepository;
import com.ip.erss.competency.iamautomation.repository.BotRepository;
import com.ip.erss.competency.iamautomation.service.AccountBaseService;
import com.ip.erss.competency.iamautomation.service.BotService;

@Service
@Transactional
public class AccountBaseServiceImpl implements AccountBaseService {
	
	@Autowired
	private AccountBaseRepository accountBaseRepository;
	
	@Override
	public List<AccountBase> loadAllAccountBases() {
		return accountBaseRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addAccountBase(AccountBase accountBase) {
		accountBaseRepository.saveAndFlush(accountBase);
	}

	@Override
	public AccountBase loadAccountBaseById(long accountBaseId) {
		return accountBaseRepository.findByAccountBaseId(accountBaseId);
	}

	@Override
	public void updateAccountBase(AccountBase accountBase) {
		accountBaseRepository.saveAndFlush(accountBase);
	}

	@Override
	public void deleteAccountBase(long accountBaseId) {
		accountBaseRepository.delete(accountBaseId);
	}

	@Override
	public List<AccountBase> loadAccountBaseByName(String accountName) {
		return accountBaseRepository.findByAccountName(accountName);
	}


}
