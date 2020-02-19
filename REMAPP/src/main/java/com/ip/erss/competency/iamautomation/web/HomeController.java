package com.ip.erss.competency.iamautomation.web;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("index()");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		boolean botUsers = false;
		boolean superVisors = false;
		boolean panel = false;
		boolean ctl = false;
		boolean hr = false;
		boolean compteam = false;
		for (GrantedAuthority authority : authorities) {
			logger.info("Authority is  ::  " + authority);
			if(authority.getAuthority().equalsIgnoreCase("ROLE_BOTS")) {
				botUsers = true;
				break;
			} if(authority.getAuthority().equalsIgnoreCase("ROLE_SUPERVISOR")) {
				superVisors = true;
				break;
			}
			 if(authority.getAuthority().equalsIgnoreCase("ROLE_PANEL")) {
					panel = true;
					break;
				}
			 if(authority.getAuthority().equalsIgnoreCase("ROLE_TSC")) {
					hr = true;
					break;
				}
			 if(authority.getAuthority().equalsIgnoreCase("ROLE_COMPTEAM")) {
					compteam = true;
					break;
				}
			 if(authority.getAuthority().equalsIgnoreCase("ROLE_CTL")) {
					ctl = true;
					break;
				}
		}
		
		if(superVisors) {
			return "redirect:/showAllSkillAttestation";
		}
		
		if(botUsers) {
			return "redirect:/showAllBots";
		}
		
		if(panel) {
			return "redirect:/interview/panel/L1Dashboard";
		}
		
		if(ctl) {
			return "redirect:/interview/compLead/showUpdateJDforL1";
		}
		if(hr) {
			return "redirect:/interview/hr/getRequirementDetails";
		}
		if(compteam) {
			return "redirect:/interview/compTeam/candidateDashboard";
		}
	
		
		/*if(role.equalsIgnoreCase("ROLE_BOTS")) {
			return "redirect:/showAllBots";
		}*/
		
		return "redirect:/interview/admin/panel";
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHome(Model model) {

		logger.debug("show Home()");
		return "home";

	}
	

}
