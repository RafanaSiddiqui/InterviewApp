package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.AccountBase;

public interface AccountBaseService {

	List<AccountBase> loadAllAccountBases();

	void addAccountBase(AccountBase accountBase);

	AccountBase loadAccountBaseById(long accountBaseId);

	void updateAccountBase(AccountBase accountBase);

	void deleteAccountBase(long accountBaseId);

	List<AccountBase> loadAccountBaseByName(String accountName);
	
}
