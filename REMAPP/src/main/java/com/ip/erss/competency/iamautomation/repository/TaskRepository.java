package com.ip.erss.competency.iamautomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ip.erss.competency.iamautomation.model.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long>{
	
}
