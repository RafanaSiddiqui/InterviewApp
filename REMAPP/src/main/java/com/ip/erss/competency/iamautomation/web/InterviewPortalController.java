
package com.ip.erss.competency.iamautomation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/interview")
public class InterviewPortalController {

	private final Logger logger = LoggerFactory.getLogger(InterviewPortalController.class);
	@RequestMapping(value = "/hr/getRequirementDetails", method = RequestMethod.GET)
	public String getRequirementDetails(Model model) {
		return "iamInterviewPortal/hrGetRequirement";		
		
	}
	
	@RequestMapping(value = "/hr/cvUpload/{jobDetailId}", method = RequestMethod.GET)
	public String cvUpload(@PathVariable("jobDetailId") Integer jobDetailId, Model model) {
		model.addAttribute("jobDetailId", jobDetailId);
		return "iamInterviewPortal/candidateCVUpload";		
		
	}
		
	@RequestMapping(value = "/hr/getRRDetails", method = RequestMethod.GET)
	public String getRRDetails(Model model) {
		return "iamInterviewPortal/uploadOpenRR";		
		
	}
	
	
	@RequestMapping(value = "/compLead/viewRRJd", method = RequestMethod.GET)
	public String viewRRJd(Model model) {
		return "iamInterviewPortal/compLeadViewRRJD";
	}
	
	@RequestMapping(value = "/compLead/viewRRJd/{jobDetailId}", method = RequestMethod.GET)
	public String viewRRJdForJdId(@PathVariable("jobDetailId") Integer jobDetailId,Model model) {
		model.addAttribute("jobDetailId",jobDetailId);
		return "iamInterviewPortal/compLeadViewRRJD";
	}
	
	
	
	// Admin
	@RequestMapping(value = "/admin/panel", method = RequestMethod.GET)
	public String iamInterviewPortal(Model model) {
		logger.info("invoking iamInterviewPortal");
		return "iamInterviewPortal/adminPanelUpload";
	}
	
	
	//Comp Lead starts
	
	// Comp Lead - Show General Description for JD1 screen	
	@RequestMapping(value = "/compLead/showUpdateJDforL1", method = RequestMethod.GET)
	public String showUpdateJDforL1(Model model) {
		logger.info("invoking showUpdateJDforL1........."+model.getClass()+"..........model..."+model);
		return "iamInterviewPortal/compLeadUpdateJDL1";
	}
	
	
	// Comp Lead - Show View Panel screen
	@RequestMapping(value = "/compLead/viewPanelStatus", method = RequestMethod.GET)
	public String viewPanelStatus(Model model) {
		logger.info("invoking viewPanelStatus........."+model.getClass()+"..........model..."+model);
		return "iamInterviewPortal/compLeadViewPanel";
	}
	
	// Comp Lead - Show Interview Panel Mapping
	@RequestMapping(value = "/compLead/interviewPanelMapping", method = RequestMethod.GET)
	public String interviewPanelMapping(Model model) {
		logger.info("invoking interviewPanelMapping........."+model.getClass()+"..........model..."+model);
		return "iamInterviewPortal/compLeadIntPanelMapping";
	}
	
	//Comp Lead End
	
	/* Candidate Module Start */
	
	@RequestMapping(value = "/panel/L1Dashboard", method = RequestMethod.GET)
	public String showPanelDashboardL1(Model model) {
		logger.info("Enter method :: showPanelDashboardL1");
		return "iamInterviewPortal/panelDashboardL1";
	}
	
	@RequestMapping(value = "/panel/L2Dashboard", method = RequestMethod.GET)
	public String showPanelDashboardL2(Model model) {
		logger.info("Enter method :: showPanelDashboardL2");
		return "iamInterviewPortal/panelDashboardL2";
	}
	
	@RequestMapping(value = "/panel/HRDashboard", method = RequestMethod.GET)
	public String showPanelDashboardHR(Model model) {
		logger.info("Enter method :: showPanelDashboardHR");
		return "iamInterviewPortal/panelDashboardHR";
	}
	
	@RequestMapping(value = "/compTeam/candidateDashboard", method = RequestMethod.GET)
	public String showIntCandidatesView(Model model) {
		logger.info("Enter method :: showIntCandidatesView");
		return "iamInterviewPortal/InterviewedCandidatesFinalView";
	}
	
	@RequestMapping(value = "/compTeam/toppanel", method = RequestMethod.GET)
	public String topIntervewPanel(Model model) {	
		logger.info("Enter method :: topIntervewPanel");
		return "iamInterviewPortal/toppanel";
	}
	
	/* Candidate Module End */
}
