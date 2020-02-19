package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.BulkAccountBase;

public interface BulkAccountBaseService {
	List<BulkAccountBase> loadAllBulkAccountBases();
	void addBulkAccountBase(BulkAccountBase bulkAccountBase);
	void addOrUpdateBulkAccountBase(BulkAccountBase bulkAccountBase);
	BulkAccountBase fetchBulkAccountBase(Long bulkID);
	void addOrUpdateAccountBaseList(List<AccountBase> accountBase);
	void deleteAccountBase(Long bulkID);
	void deleteAccountBase(BulkAccountBase bulkAccountBase);
	
	List<AccountBase> getUpdatedAccountBaseList(List<AccountBase> accountBaseList);
}
