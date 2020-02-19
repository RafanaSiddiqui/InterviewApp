package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.AccountBase;

public interface AccountBaseRepository extends JpaRepository<AccountBase, Long>{

	AccountBase findByAccountBaseId(long accountBaseId);
	
	List<AccountBase> findAllByOrderByCreatedDateDesc();

	List<AccountBase> findByAccountName(String accountName);

}
