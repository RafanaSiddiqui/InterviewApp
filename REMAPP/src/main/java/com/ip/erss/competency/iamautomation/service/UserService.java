package com.ip.erss.competency.iamautomation.service;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.ip.erss.competency.iamautomation.model.UserInfo;

public interface UserService {
	
	void addUserDetails(UserInfo usrDetails);
	List<UserInfo> getAllUsers();
	
	boolean isActiveUser(String userName);
	
	void deleteUser(Long UserId);
	UserInfo fetchUser(Long userId);
	UserInfo getUserInfo(String userName);
	void updatePassword(UserInfo usrInfo);
	
	void updateUser(UserInfo userInfo);
	Collection<GrantedAuthority> loadRolesFromDatabase(String username);
	List<UserInfo> getUserListByRole(String string);
	List<UserInfo> getUserListByRoleApprover(String role, String approverType);
}
