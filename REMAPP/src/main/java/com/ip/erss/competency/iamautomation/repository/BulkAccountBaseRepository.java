package com.ip.erss.competency.iamautomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.BulkAccountBase;

public interface BulkAccountBaseRepository extends JpaRepository<BulkAccountBase, Long>{
	List<BulkAccountBase> findAllByOrderByCreatedDateDesc();
}
