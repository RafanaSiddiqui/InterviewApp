package com.ip.erss.competency.iamautomation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.dao.BulkAccountBaseDAO;
import com.ip.erss.competency.iamautomation.model.AccountBase;
import com.ip.erss.competency.iamautomation.model.BulkAccountBase;
import com.ip.erss.competency.iamautomation.repository.AccountBaseRepository;
import com.ip.erss.competency.iamautomation.repository.BulkAccountBaseRepository;
import com.ip.erss.competency.iamautomation.service.BulkAccountBaseService;

@Service
@Transactional
public class BulkAccountBaseServiceImpl implements BulkAccountBaseService{
	
	@Autowired
	private BulkAccountBaseDAO bulkAccountBaseDAO;
	
	@Autowired
	private BulkAccountBaseRepository bulkAccountBaseRepository;
	
	@Autowired
	private AccountBaseRepository accountBaseRepository;

	@Override
	public List<BulkAccountBase> loadAllBulkAccountBases() {
		return bulkAccountBaseRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addBulkAccountBase(BulkAccountBase bulkAccountBase) {
		bulkAccountBaseRepository.saveAndFlush(bulkAccountBase);
	}

	@Override
	public BulkAccountBase fetchBulkAccountBase(Long bulkID) {
		BulkAccountBase bulkAccountBase = bulkAccountBaseRepository.findOne(bulkID);
		return bulkAccountBase;
	}

	/*@Override
	public void addAccountBaseBulk(List<AccountBase> listAccountBase) {
		if(null!=listAccountBase) {
			accountBaseRepository.save(listAccountBase);
			accountBaseRepository.flush();
//			for(AccountBase accountBase : listAccountBase) {
//				accountBaseRepository.saveAndFlush(accountBase);
//			}
		}
	}*/
	
	@Override
	public void addOrUpdateAccountBaseList(List<AccountBase> listAccountBase) {
		AccountBase accountBase = null;
		
		try {
			if(null!=listAccountBase) {
				for(AccountBase accBase : listAccountBase) {
					accountBase = bulkAccountBaseDAO.getAccountBaseByCustomerID(accBase.getCustomerId());
					if(null!=accountBase) {
						accBase.setAccountBaseId(accountBase.getAccountBaseId());
					}
					
					System.out.println("Customer ID === "+accBase.getCustomerId());
					bulkAccountBaseDAO.addOrUpdateAccountBase(accBase);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	@Override
	public void deleteAccountBase(Long bulkID) {
		bulkAccountBaseRepository.delete(bulkID);
	}

	@Override
	public void deleteAccountBase(BulkAccountBase bulkAccountBase) {
		bulkAccountBaseRepository.delete(bulkAccountBase);
	}

	@Override
	public void addOrUpdateBulkAccountBase(BulkAccountBase bulkAccountBase) {
		bulkAccountBaseDAO.addOrUpdateBulkAccountBase(bulkAccountBase);
	}

	@Override
	public List<AccountBase> getUpdatedAccountBaseList(List<AccountBase> accountBaseList) {
		List<AccountBase> accountBaseListNew  = new ArrayList<AccountBase>();
		AccountBase accountBase = null;
		
		if(null!=accountBaseList) {
			for(AccountBase accBase : accountBaseList) {
				accountBase = bulkAccountBaseDAO.getAccountBaseByCustomerID(accBase.getCustomerId());
				if(null!=accountBase) {
					accBase.setAccountBaseId(accountBase.getAccountBaseId());
				}
				
				accountBaseListNew.add(accBase);
			}
		}
		
		return accountBaseListNew;
	}
	

}
