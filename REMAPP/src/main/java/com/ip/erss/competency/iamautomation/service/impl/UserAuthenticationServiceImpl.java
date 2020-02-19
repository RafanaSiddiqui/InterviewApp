package com.ip.erss.competency.iamautomation.service.impl;


import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.service.UserService;

@Service("customUserDetailsService")
@Transactional
public class UserAuthenticationServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	//private UserAuthenticationDaoImpl userAuthenticationDao = new UserAuthenticationDaoImpl();
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		System.out.println("userName  ::  "+username);
		UserInfo userInfo = userService.getUserInfo(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		System.out.println("userInfo.getRole() :: "+userInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), "", Arrays.asList(authority));
		System.out.println("userDetails.getAuthorities() "+userDetails.getAuthorities()+" "+userDetails.getUsername());
		return userDetails;
	}
	
	
} 
