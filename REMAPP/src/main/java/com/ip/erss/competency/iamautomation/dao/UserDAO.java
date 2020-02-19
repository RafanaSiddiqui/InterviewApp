package com.ip.erss.competency.iamautomation.dao;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.UserInfo;

public interface UserDAO {
	
	void addUserDetails(UserInfo usrDetails);
	boolean isActiveUser(String userName);
	UserInfo getUserInfo(String userName);
	List<UserInfo> getAllUsers();
	
	void deleteUser(Long UserId);
	UserInfo fetchUser(Long UserId);
	void updatePassword(UserInfo usrInfo);
	
	void updateUser(UserInfo userInfo);
	List<UserInfo> getUserListByRole(String role);
	List<UserInfo> getUserListByRoleApprover(String role, String approverType);

}
