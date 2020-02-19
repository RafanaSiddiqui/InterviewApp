/**
 *
 */
package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ip.erss.competency.iamautomation.dao.UserDAO;
import com.ip.erss.competency.iamautomation.model.UserInfo;
import com.ip.erss.competency.iamautomation.service.UserService;

/**
 * @author 134930
 *
 */
@Controller
public class AppController {


	private final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	UserService userDetailsService;
	
	@Autowired
	private UserDAO userAuthenticationDao;

	@RequestMapping(value = "/Configuration", method = RequestMethod.GET)
	public String showTestData(Model model) {

		logger.debug("Invoke Configuration page");
		return "configuration/Configuration";

	}

	@RequestMapping(value = "/emailTemplate", method = RequestMethod.GET)
	public String showEmailTemplate(Model model) {

		logger.debug("Invoke Configuration page");
		return "itsm/ITSMemailTemplate";

	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public String showJobDetails(Model model) {

		logger.debug("Invoke Configuration page");
		return "itsm/ITSMJob";

	}

	@RequestMapping(value = "/scheduler", method = RequestMethod.GET)
	public String showscheduler(Model model) {

		logger.debug("Invoke Configuration page");
		return "itsm/ITSMScheduler";

	}
	
	@RequestMapping(value = "/manageUser", method = RequestMethod.GET)
	public String manageUser() {

		logger.debug("Invoke Create User Page");
		return "request/ManageUser";

	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String showAPI(Model model) {

		logger.debug("Invoke Configuration page");
		return "itsm/ITSMapi";

	}


	@RequestMapping(value = "/bulkRequestUpload", method = RequestMethod.GET)
	public String showALLbulkRequestUpload(Model model) {

		logger.debug("Invoke bulkRequestUpload page");
		return "request/ShowALLBulkRequest";

	}
	
	@RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
	public String createUser() {

		logger.debug("Invoke Create User page");
		return "request/CreateUser";

	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword() {

		logger.debug("Invoke Create User page");
		return "request/resetPassword";

	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.GET)
	public String findUser(Model model) {

		logger.debug("Invoke findUser page");
		return "request/FindUser";

	}
	
	@RequestMapping(value = "/EditUser/{userId}", method = RequestMethod.GET)
	public String editRequest(@PathVariable("userId") long userId, Model model) {
		logger.debug("invocking Edit User-->");

		  logger.debug("showTestData()");

		  model.addAttribute("userId",userId);


		return "request/EditUser";

	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String formLogin(Model model,@RequestParam(value = "error",required = false) String error, @RequestParam(value = "logout",required = false) String logout) {
		logger.info("inside login  ::  ");
		//logger.debug("userName  "+userName);
		//if(null==error)
		//{
			//@RequestParam("username") String username
		//userDetailsService.isActiveUser(username);
		//}
		
		if ( null != error) {
			logger.info("error  ::  "+error);
			model.addAttribute("error", "Invalid Credentials provided");
		}

		if ( null != logout ) {
			model.addAttribute("logoutMsg", "Logged out Successfully");
		}

		return "login";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashboard(Model model) {

		logger.debug("Invoke bulkRequestUpload page");
		return "dashboard";

	}
	
	@RequestMapping(value = "/masterDashboard", method = RequestMethod.GET)
	public String showMasterDashboard(Model model) {

		logger.debug("Invoke bulkRequestUpload page");
		return "masterDashboard";

	}
	
	@RequestMapping(value = "/dashboards", method = RequestMethod.GET)
	public String showDashboards(Model model) {

		logger.debug("Invoke bulkRequestUpload page");
		return "dashboards";

	}
	
}
