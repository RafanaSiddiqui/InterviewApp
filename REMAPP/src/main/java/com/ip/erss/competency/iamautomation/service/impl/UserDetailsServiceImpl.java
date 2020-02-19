package com.ip.erss.competency.iamautomation.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.RequestDAO;
import com.ip.erss.competency.iamautomation.dao.UserDAO;
import com.ip.erss.competency.iamautomation.dao.impl.RequestDAOImpl;
import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.model.UserRoles;
import com.ip.erss.competency.iamautomation.service.UserService;
@Service
@Transactional
public class UserDetailsServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public void addUserDetails(UserInfo usrDetails) {
		userDAO.addUserDetails(usrDetails);
		
	}


	@Override
	public boolean isActiveUser(String userName) {
		
		return userDAO.isActiveUser(userName);
	}


	@Override
	public List<UserInfo> getAllUsers() {
		
		return userDAO.getAllUsers();
	}


	@Override
	public void deleteUser(Long UserId) {
		userDAO.deleteUser(UserId);
		
	}


	@Override
	public UserInfo fetchUser(Long userId) {
		
		return userDAO.fetchUser(userId);
	}


	@Override
	public void updatePassword(UserInfo usrInfo) {

		userDAO.updatePassword(usrInfo);
		
	}


	@Override
	public UserInfo getUserInfo(String userName) {
		
		return userDAO.getUserInfo(userName);
	}


	@Override
	public void updateUser(UserInfo userInfo) {
		
		userDAO.updateUser(userInfo);
		
		
	}


	public Collection<GrantedAuthority> loadRolesFromDatabase(String username)
			throws UsernameNotFoundException {
		
		logger.info("userName  ::  "+username);
		UserInfo userInfo = userDAO.getUserInfo(username);
		
 		List<UserRoles> userRolesList = userInfo.getUserRolesList();
 		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		if(userRolesList != null) {
			for(UserRoles role : userRolesList) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
				authorityList.add(authority);
	 		}
		}
		
		logger.info("authorityList :: "+authorityList);
		return authorityList;
	}


	@Override
	public List<UserInfo> getUserListByRole(String role) {
		return userDAO.getUserListByRole(role);
	}


	@Override
	public List<UserInfo> getUserListByRoleApprover(String role, String approverType) {
		return userDAO.getUserListByRoleApprover(role, approverType);
	}


}
