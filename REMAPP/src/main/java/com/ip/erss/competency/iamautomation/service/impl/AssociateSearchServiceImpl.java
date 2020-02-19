package com.ip.erss.competency.iamautomation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.AssociateDetails;
import com.ip.erss.competency.iamautomation.repository.AssociateDetailsRepository;
import com.ip.erss.competency.iamautomation.service.AssociateSearchService;

@Service
@Transactional
public class AssociateSearchServiceImpl implements AssociateSearchService {

	@Autowired
	private AssociateDetailsRepository associateDetailsRepository;
	
	@Override
	public List<AssociateDetails> loadAssociates() {
		return associateDetailsRepository.findAll();
	}
	
	@Override
	public List<AssociateDetails> searchAssociateDetails(AssociateDetails associateDetails) {

		List<AssociateDetails> associateDetailsList = associateDetailsRepository.findAll(new Specification<AssociateDetails>() {

			@Override
			public Predicate toPredicate(Root<AssociateDetails> root, CriteriaQuery< ?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (associateDetails.getAssociateID() != null) {
					predicates.add(cb.like(cb.lower(root.get("associateID")), 
                                                    "%" + associateDetails.getAssociateID().toLowerCase() + "%"));
				}
				
				if (associateDetails.getFirstName() != null) {
					predicates.add(cb.like(cb.lower(root.get("firstName")), 
                                                    "%" + associateDetails.getFirstName().toLowerCase() + "%"));
				}

				if (associateDetails.getLastName() != null) {
					predicates.add(cb.like(cb.lower(root.get("lastName")), 
                                                    "%" + associateDetails.getLastName().toLowerCase() + "%"));
				}
				
				if (associateDetails.getKeySkills() != null) {
					predicates.add(cb.like(cb.lower(root.get("keySkills")), 
                                                    "%" + associateDetails.getKeySkills().toLowerCase() + "%"));
				}
				
				if (associateDetails.getDetailedSkills() != null) {
					predicates.add(cb.like(cb.lower(root.get("detailedSkills")), 
                                                    "%" + associateDetails.getDetailedSkills().toLowerCase() + "%"));
				}
				
				if (associateDetails.getGrade() != null) {
					predicates.add(cb.like(cb.lower(root.get("grade")), 
                                                    associateDetails.getGrade().toLowerCase()));
				}
				
				if (associateDetails.getLocation() != null) {
					predicates.add(cb.like(cb.lower(root.get("location")), 
                                                    "%" + associateDetails.getLocation().toLowerCase() + "%"));
				}
				
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		});
		return associateDetailsList;
	}
	
}
