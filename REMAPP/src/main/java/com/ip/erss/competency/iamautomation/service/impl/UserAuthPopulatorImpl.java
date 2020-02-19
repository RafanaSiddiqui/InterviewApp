package com.ip.erss.competency.iamautomation.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.service.UserService;


@Service("userAuthService")
public class UserAuthPopulatorImpl implements LdapAuthoritiesPopulator {

	@Autowired
	private UserService userService;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,
			String username) {
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
		UserInfo userInfo = null;
		
		try {
			userInfo = userService.getUserInfo(username);
			if(null != userInfo) {
				GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
				System.out.println("userInfo.getRole() :: "+userInfo.getRole());
				UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), 
						"", Arrays.asList(authority));
				System.out.println("userDetails.getAuthorities() "+userDetails.getAuthorities()+" "+userDetails.getUsername());
				gas.add(authority);
			} else {
				UserInfo usrDetails = new UserInfo();
				usrDetails.setFirstName("");
				//userService.addUserDetails(usrDetails);
			}
			
		} catch (Exception e) {
			System.out.println("User Not Found");
			e.printStackTrace();
		}
		return gas;
	}
}
