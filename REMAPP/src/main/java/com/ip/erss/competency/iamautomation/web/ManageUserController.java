package com.ip.erss.competency.iamautomation.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.service.UserService;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ip.erss.competency.iamautomation.service,com.ip.erss.competency.sp.service.impl,"
		+ "com.ip.erss.competency.iamautomation.dao,com.ip.erss.competency.iamautomation.dao.impl")
public class ManageUserController {
	
	private final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	
	@Autowired
	UserService userDetailsService;
	
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody UserInfo usrDetails) {
		try {
			logger.debug("Invoke ManageUserController addUser Method");
			logger.debug("UserDetails UserName "+usrDetails.getUsername());
			userDetailsService.addUserDetails(usrDetails);
			logger.debug("After addUserDetails");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateUsrDetails", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody UserInfo userInfo) {
		
		logger.debug("Invoke ManageUserController addUser Method");
		userDetailsService.updateUser(userInfo);
		logger.debug("After addUserDetails");
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<Void> updatePassword(@RequestBody UserInfo usrDetails) {
		String username = usrDetails.getUsername();
		UserInfo u = userDetailsService.getUserInfo(username);
		logger.debug("Invoke ManageUserController updatePassword Method");
		userDetailsService.updatePassword(u);
		logger.debug("After addUserDetails");
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "/userByID/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserInfo> fetchAppRequestByID(@PathVariable("userId") long userId) {
		logger.info("Going to fetch App Request " + userId);

		UserInfo userInfo = userDetailsService.fetchUser(userId);

		if (userInfo == null) {
			logger.info("there is no records found " + userId);
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	//
	public List<UserInfo> getUsers() {
		
		List<UserInfo> userInfo = userDetailsService.getAllUsers();
		
		return userInfo;

	}
	
	//getUserRole
	@RequestMapping(value = "/getUserRole", method = RequestMethod.GET)
	public UserInfo getUserRole() {
		UserInfo userInfo = new UserInfo();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("User logged in is "+auth.getName());
		/*Collection<? extends GrantedAuthority> authorities = auth.getAuthorities(); 
		userInfo.setUsername(auth.getName());
		for (GrantedAuthority authority : authorities) {
			System.out.println("Authority is "+authority);
			userInfo.setRole(authority.getAuthority());
		}*/
		
		userInfo = userDetailsService.getUserInfo(auth.getName());
		
		return userInfo;
	}

	@RequestMapping(value = "/getUsers/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {

		UserInfo user = userDetailsService.fetchUser(userId);
		if (user == null) {
			logger.info("there is no records found " + userId);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userDetailsService.deleteUser(userId);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	

}
