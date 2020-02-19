package com.ip.erss.competency.iamautomation.dao;


import java.util.List;

import com.ip.erss.competency.iamautomation.model.InterviewPortalCandidateFeedback;
import com.ip.erss.competency.iamautomation.model.InterviewPortalIntrvwPanelMapping;
import com.ip.erss.competency.iamautomation.model.InterviewPortalJobDetails;
import com.ip.erss.competency.iamautomation.model.InterviewPortalOpenRR;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanelReport;
import com.ip.erss.competency.iamautomation.vo.InterviewPanelReportVO;

public interface InterviewPortalDao {

	void createRequirementDetails(InterviewPortalJobDetails interviewPortalHRJobDetails);
	
	void uploadRequirementDetailsZip(InterviewPortalJobDetails jobDetailsZip);
	
	List<InterviewPortalJobDetails> getCreateRequirementPanel(String userName);
	
	InterviewPortalJobDetails findById(long jobDetailId);
	
	void deleteUploadedCVZip (long jobDetailId);
	
	void uploadOpenRR(List<InterviewPortalOpenRR> openRRList);
	
	List<InterviewPortalOpenRR> getOpenRRPanel(long jobDetailId);
	
		
	List<InterviewPortalJobDetails> loadAllScheduledInterviews();
	
	List<InterviewPortalOpenRR> loadOpenRRById(long jobDetailsId);
	
	void updateL2JDDesc(String rrId,long jdId,String l2JdDesc);

	void uploadRequirementDetailsXLS(List<InterviewPortalJobDetails> jobDetailsList);
	
	void deleteJDbyJobDescID(Long jobDescId);
	
	InterviewPortalOpenRR getRRById(long Id);
	
	void deleteRRbyId(Long Id);
	
	//Admin Start
	
	public void createUpdateAdminPanelXls(List<InterviewPortalPanel> xls, String event); 	
	
	public List<InterviewPortalPanel> getAllAdminPanel();
	
	public InterviewPortalPanel getPanelByEmpId(Long empid);
	
	public void deletePanelByEmpId(Long empid);

	
	//Admin End
	
	
	
	//Comp Lead Starts
	

	/**
	 * @param interviewPortalJobDetails
	 * Update JD1 screen - Update the L1 description
	 */
	void updateJDForL1 (InterviewPortalJobDetails interviewPortalJobDetails);
	
	/**
	 * @param skill
	 * @return List <InterviewPortalPanel>
	 *  Comp Lead - Interview Panel Mapping screen - View panel for a Skill
	 */
	public List<InterviewPortalPanel> panelAvailableForIntervwBySkill(String skill);
	
	/**
	 * @param jdId
	 * @return List<InterviewPortalIntrvwPanelMapping>
	 * Comp Lead -  View Panel status screen - get panel for an Interview Drive
	 */
	List<InterviewPortalIntrvwPanelMapping> loadPanelAcceptedForIntervwByJD(Long jdId);
	
	
	/**
	 * @param jdPanel
	 * @return boolean
	 * Comp Lead -  Interview Panel Mapping screen - Add panel for a drive
	 */
	public boolean addPanelForInterview(InterviewPortalIntrvwPanelMapping jdPanel);


	//Comp Lead Ends
	
	/* Candidate Module Start */

	void addCandidateFeedback(InterviewPortalCandidateFeedback feedback);
	
	List<InterviewPortalCandidateFeedback> findAllCandidatesFeedbackListByJdId(Long jdId);
	
	/* Candidate Module End */
	
	public void addPanelReporting(InterviewPortalPanelReport report);
	
	public List<InterviewPanelReportVO> getTopPanel(int days);
	
}


