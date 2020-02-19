
package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.InterviewPortalCandidateFeedback;
import com.ip.erss.competency.iamautomation.model.InterviewPortalIntrvwPanelMapping;
import com.ip.erss.competency.iamautomation.model.InterviewPortalJobDetails;
import com.ip.erss.competency.iamautomation.model.InterviewPortalOpenRR;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanelReport;
import com.ip.erss.competency.iamautomation.vo.InterviewPanelReportVO;

public interface InterviewPortalService {
	
	void createRequirementDetails(InterviewPortalJobDetails interviewPortalHRJobDeails);
	
	void uploadRequirementDetailsXLS(List<InterviewPortalJobDetails> jobDetailsList);
	
	void uploadRequirementDetailsZip(InterviewPortalJobDetails jobDetailsList);
	
	List<InterviewPortalJobDetails> getCreateRequirementPanel(String userName);
	
	InterviewPortalJobDetails getCandidateCVByID(long jobDetailId);
	
	void deleteUploadedCVZip(long jobDetailId);
	
	void uploadOpenRR(List<InterviewPortalOpenRR> openRRList);
	
	List<InterviewPortalOpenRR> getOpenRRPanel(long jobDetailsId);
	
	List<InterviewPortalJobDetails> findAll();
	
	List<InterviewPortalOpenRR> loadOpenRRById(long jobDetailsId);
	
	void updateL2JDDesc(String rrId,long jdId,String l2JdDesc);
	
	void deleteJDbyJobDescID(Long jobDescId);
	
	InterviewPortalOpenRR getRRById(long Id);
	
	void deleteRRbyId(Long Id);
	
	
	//Admin Start
	
	void createUpdateAdminPanelXls(List<InterviewPortalPanel> xls, String event); 
	
	List<InterviewPortalPanel> getAllAdminPanel();
	
	InterviewPortalPanel getPanelByEmpId(Long empid);
	
	void deletePanelByEmpId(Long empid);
	
	//Admin End
	
	
	
	//Comp Lead Starts
	
	
	/**
	 * @param interviewPortalJobDeails
	 * Update JD1 screen - Update the L1 description
	 */
	void updateJDForL1(InterviewPortalJobDetails interviewPortalJobDeails);
	
	
	/**
	 * @param skill
	 * @return List<InterviewPortalPanel>
	 *  Comp Lead - Interview Panel Mapping screen - View panel for a Skill
	 */
	List<InterviewPortalPanel> panelAvailableForIntervwBySkill(String skill);
	

	
	/**
	 * @param jdId
	 * @return List<InterviewPortalIntrvwPanelMapping>
	 * Comp Lead -  View Panel status screen - get panel for an Interview Drive
	 */
	List<InterviewPortalIntrvwPanelMapping> panelAcceptedForIntervwByJD(Long jdId);
	
	/**
	 * @param jdPanel
	 * @return boolean
	 * Comp Lead -  Interview Panel Mapping screen - Add panel for a drive
	 */
	public boolean addPanelForInterview(InterviewPortalIntrvwPanelMapping jdPanel);

	//Comp Lead Ends
	

	/* Candidate Module Start */
	
	List<InterviewPortalCandidateFeedback> findAllCandidatesFeedbackListByJdId(Long jdId);
	
	void addCandidateInterviewFeedback(InterviewPortalCandidateFeedback feedback);

	void updateCandidateInterviewFeedback(InterviewPortalCandidateFeedback feedback);
	
	/* Candidate Module End */
	public void addPanelReporting(InterviewPortalPanelReport report);
	
	public List<InterviewPanelReportVO> getTopPanel(int days);


}
