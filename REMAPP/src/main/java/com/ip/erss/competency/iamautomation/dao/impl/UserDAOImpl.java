package com.ip.erss.competency.iamautomation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.dao.UserDAO;
import com.ip.erss.competency.iamautomation.model.UserInfo;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addUserDetails(UserInfo usrDetails) {
		logger.info("IN USer DAO-->" + usrDetails.toString());
		String role = usrDetails.getRole();
		usrDetails.setRole(role);
		entityManager.persist(usrDetails);
		
	}
	
	@Override
	public UserInfo getUserInfo(String userName) {
		logger.debug("Inside getUserInfo");
		UserInfo userInfo = new UserInfo();
		try {
			Query query = entityManager.createQuery("SELECT u FROM UserInfo u where u.username =?");
			query.setParameter(1, userName);
			userInfo = (UserInfo) query.getSingleResult();
			
			logger.debug("userInfo  ::  "+userInfo.getUsername());
		} catch(Exception e) {
			logger.error("Exception  ::  "+e.getMessage());
		}
		return userInfo;
	}

	@Override
	public boolean isActiveUser(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserInfo> getAllUsers() {
		
		logger.info("Inside getAllUsers");
		List<UserInfo> userInfo = null;
		try {
			userInfo = entityManager.createQuery("SELECT u FROM UserInfo u").getResultList();
		} catch(Exception e) {
			logger.error("fetchAllTemplates Exception  ::  "+e.getMessage());
		}
		return userInfo;
	}

	@Override
	public void deleteUser(Long UserId) {
		UserInfo userInfo = this.fetchUser(UserId);
		if (userInfo != null) {
			entityManager.remove(userInfo);
		}
		
	}

	@Override
	public UserInfo fetchUser(Long UserId) {
		return entityManager.find(UserInfo.class, UserId);
	}

	@Override
	public void updatePassword(UserInfo usrInfo) {
		entityManager.merge(usrInfo);
		
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		entityManager.merge(userInfo);
		
	}

	@Override
	public List<UserInfo> getUserListByRole(String role) {
		logger.info("Inside getAllUsers");
		List<UserInfo> userInfoList = null;
		try {
			
			Query query = entityManager.createQuery("SELECT u FROM UserInfo u where u.role = ?");
			query.setParameter(1, role);
			userInfoList = query.getResultList();
			
		} catch(Exception e) {
			logger.error("fetchAllTemplates Exception  ::  "+e.getMessage());
		}
		return userInfoList;
	}

	@Override
	public List<UserInfo> getUserListByRoleApprover(String role, String approverType) {
		logger.info("Inside getUserListByRoleApprover");
		List<UserInfo> userInfoList = null;
		try {
			
			Query query = entityManager.createQuery("SELECT u FROM UserInfo u where u.role = ? and approverType = ?");
			query.setParameter(1, role);
			query.setParameter(2, approverType);
			userInfoList = query.getResultList();
			
		} catch(Exception e) {
			logger.error("getUserListByRoleApprover Exception  ::  "+e.getMessage());
		}
		return userInfoList;
	}
		
}
