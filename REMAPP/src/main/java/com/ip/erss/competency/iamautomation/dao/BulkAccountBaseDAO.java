package com.ip.erss.competency.iamautomation.dao;

import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.BulkAccountBase;

public interface BulkAccountBaseDAO {
	void addOrUpdateAccountBase(AccountBase accountBase);
	void addOrUpdateBulkAccountBase(BulkAccountBase bulkAccountBase);
	AccountBase getAccountBaseByCustomerID(String customerId);
}