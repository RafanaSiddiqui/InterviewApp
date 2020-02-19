package com.ip.erss.competency.iamautomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ip.erss.competency.iamautomation.model.AssociateDetails;

public interface AssociateDetailsRepository extends JpaRepository<AssociateDetails, Long>, JpaSpecificationExecutor<AssociateDetails>  {     

}
