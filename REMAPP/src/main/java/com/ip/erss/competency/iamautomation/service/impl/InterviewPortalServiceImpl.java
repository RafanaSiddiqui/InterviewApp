
package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ip.erss.competency.iamautomation.dao.InterviewPortalDao;
import com.ip.erss.competency.iamautomation.model.InterviewPortalCandidateFeedback;
import com.ip.erss.competency.iamautomation.model.InterviewPortalIntrvwPanelMapping;
import com.ip.erss.competency.iamautomation.model.InterviewPortalJobDetails;
import com.ip.erss.competency.iamautomation.model.InterviewPortalOpenRR;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanelReport;
import com.ip.erss.competency.iamautomation.repository.InterviewPortalCanFeedbackRepository;
import com.ip.erss.competency.iamautomation.service.InterviewPortalService;
import com.ip.erss.competency.iamautomation.vo.InterviewPanelReportVO;

@Service
@Transactional
public class InterviewPortalServiceImpl implements InterviewPortalService {

	@Autowired
	private InterviewPortalDao portalDao;
	
	@Autowired
	private InterviewPortalCanFeedbackRepository canFeedbackRepo;
	
	@Override
	public void createRequirementDetails(InterviewPortalJobDetails interviewPortalHRJobDeails)  {
		
		portalDao.createRequirementDetails(interviewPortalHRJobDeails);
	}

	@Override
	public List<InterviewPortalJobDetails> getCreateRequirementPanel(String userName) {
		List<InterviewPortalJobDetails> jobdetailsList = null; 
		jobdetailsList=portalDao.getCreateRequirementPanel(userName);
		return jobdetailsList ;
	
	}

	@Override
	public void uploadRequirementDetailsXLS(List<InterviewPortalJobDetails> jobDetailsList) {
		portalDao.uploadRequirementDetailsXLS(jobDetailsList);
		
	}

	@Override
	public InterviewPortalJobDetails getCandidateCVByID(long jobDetailId) {
		System.out.println("Inside SerivceImpl UploadCandidateCV!!");
		return portalDao.findById(jobDetailId);
	}

	@Override
	public void uploadRequirementDetailsZip(InterviewPortalJobDetails jobDetailsZip) {
		portalDao.uploadRequirementDetailsZip(jobDetailsZip);
	}

	@Override
	public void deleteUploadedCVZip(long jobDetailId) {
		System.out.println("Inside SerivceImpl deleteUploadedCVZip!!");
		portalDao.deleteUploadedCVZip(jobDetailId);
	}
	
	
	@Override
	public void uploadOpenRR(List<InterviewPortalOpenRR> openRRList) {
		
		portalDao.uploadOpenRR(openRRList);
		
	}

	@Override
	public List<InterviewPortalOpenRR> getOpenRRPanel(long jobDetailId) {
		return portalDao.getOpenRRPanel(jobDetailId);
	}

	

	@Override
	public List<InterviewPortalJobDetails> findAll() {
		return portalDao.loadAllScheduledInterviews();
	}

	@Override
	public List<InterviewPortalOpenRR> loadOpenRRById(long jobDetailsId) {

		return portalDao.loadOpenRRById(jobDetailsId);
	}

	@Override
	public void updateL2JDDesc(String rrId,long jdId,String l2JdDesc) {
		portalDao.updateL2JDDesc(rrId,jdId,l2JdDesc);
	}
	

	@Override
	public void deleteJDbyJobDescID(Long jobDescId) {
		portalDao.deleteJDbyJobDescID(jobDescId);
	}

	
	@Override
	public InterviewPortalOpenRR getRRById(long Id) {
		System.out.println("Inside SerivceImpl UploadCandidateCV!!");
		return portalDao.getRRById(Id);
	}
	
	@Override
	public void deleteRRbyId(Long Id) {
		portalDao.deleteRRbyId(Id);
	}
	
	//Admin START
	
	@Override
	public void createUpdateAdminPanelXls(List<InterviewPortalPanel> xls, String event){
		portalDao.createUpdateAdminPanelXls(xls, event);		
	}
	
	@Override
	public List<InterviewPortalPanel> getAllAdminPanel(){
		return portalDao.getAllAdminPanel();
	}
	
	@Override
	public InterviewPortalPanel getPanelByEmpId(Long empid) {
		return portalDao.getPanelByEmpId(empid);
	}
	
	@Override
	public void deletePanelByEmpId(Long empid) {
		portalDao.deletePanelByEmpId(empid);
	}

	

	
	
	//Comp Lead Starts
	
	/**
	 * @param interviewPortalJobDeails
	 * Update JD1 screen - Update the L1 description
	 */		
	@Override
		public void updateJDForL1(InterviewPortalJobDetails interviewPortalJobDeails) {
			
			portalDao.updateJDForL1(interviewPortalJobDeails);
					
		}
		
	/**
	 * @param skill
	 * @return List<InterviewPortalPanel>
	 *  Comp Lead - Interview Panel Mapping screen - View panel for a Skill
	 */
	@Override
	public List<InterviewPortalPanel> panelAvailableForIntervwBySkill(String skill) {
		// TODO Auto-generated method stub
		return portalDao.panelAvailableForIntervwBySkill(skill);
	}
	

	
	/**
	 * @param jdId
	 * @return List<InterviewPortalIntrvwPanelMapping>
	 * Comp Lead -  View Panel status screen - get panel for an Interview Drive
	 */
	@Override
	public List<InterviewPortalIntrvwPanelMapping> panelAcceptedForIntervwByJD(Long jdId) {
		return portalDao.loadPanelAcceptedForIntervwByJD(jdId);

	}


	/**
	 * @param jdPanel
	 * @return boolean
	 * Comp Lead -  Interview Panel Mapping screen - Add panel for a drive
	 */
	@Override
	public boolean addPanelForInterview(InterviewPortalIntrvwPanelMapping jdPanel) {
	 return	portalDao.addPanelForInterview(jdPanel);
		
	}
	
	//Comp Lead Ends
	
	/* Candidate Module Start */
	@Override
	public List<InterviewPortalCandidateFeedback> findAllCandidatesFeedbackListByJdId(Long jdId) {
		return portalDao.findAllCandidatesFeedbackListByJdId(jdId);
	}

	@Override
	public void addCandidateInterviewFeedback(InterviewPortalCandidateFeedback feedback) {
		canFeedbackRepo.saveAndFlush(feedback);
	}
	
	@Override
	public void updateCandidateInterviewFeedback(InterviewPortalCandidateFeedback feedback) {
		canFeedbackRepo.saveAndFlush(feedback);
	}
	/* Candidate Module End */
	
	public void addPanelReporting(InterviewPortalPanelReport report) {
		portalDao.addPanelReporting(report);
	}
	
	public List<InterviewPanelReportVO> getTopPanel(int days){
		return portalDao.getTopPanel(days);
	}
}
