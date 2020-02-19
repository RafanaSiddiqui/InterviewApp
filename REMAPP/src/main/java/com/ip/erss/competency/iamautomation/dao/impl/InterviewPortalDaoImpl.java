
package com.ip.erss.competency.iamautomation.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.model.InterviewPortalCandidateFeedback;
import com.ip.erss.competency.iamautomation.model.InterviewPortalIntrvwPanelMapping;
import com.ip.erss.competency.iamautomation.model.InterviewPortalJobDetails;
import com.ip.erss.competency.iamautomation.model.InterviewPortalOpenRR;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanelReport;
import com.ip.erss.competency.iamautomation.vo.InterviewPanelReportVO;


@Repository
public class InterviewPortalDaoImpl
		implements com.ip.erss.competency.iamautomation.dao.InterviewPortalDao {

	private final Logger logger = LoggerFactory.getLogger(InterviewPortalDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createRequirementDetails(InterviewPortalJobDetails interviewPortalHRJobDetails) {

		entityManager.persist(interviewPortalHRJobDetails);
	}

	@Override
	public List<InterviewPortalJobDetails> getCreateRequirementPanel(String userName) {
		List<InterviewPortalJobDetails> jobDetailsList = null;
		try {
			jobDetailsList = entityManager
					.createQuery("select e from InterviewPortalJobDetails e order by e.driveDate desc").getResultList();

		} catch (Exception e) {
			logger.error("Exception  occurred during getCreateRequirementPanel :  " + e.getMessage());
		}
		return jobDetailsList;
	}

	@Override
	public void uploadRequirementDetailsXLS(List<InterviewPortalJobDetails> jobDetailsList) {
		for (InterviewPortalJobDetails xls : jobDetailsList) {
			entityManager.persist(xls);
		}

	}

	@Override
	public InterviewPortalJobDetails findById(long jobDetailId) {
		InterviewPortalJobDetails jobDetails = null;
		try {
			jobDetails = (InterviewPortalJobDetails) entityManager.find(InterviewPortalJobDetails.class, jobDetailId);

		} catch (Exception e) {
			logger.error("Exception  occurred :  " + e.getMessage());
		}
		return jobDetails;

	}

	@Override
	public void uploadRequirementDetailsZip(InterviewPortalJobDetails jobDetailsZip) {
		if (null != jobDetailsZip) {
			Query query = entityManager.createQuery(
					"Update InterviewPortalJobDetails jd SET jd.skill = ?,jd.footFall =?,jd.uploadedCV =?,jd.contentType =?,jd.cvCount =?,jd.fileName=?  where jd.jobdescId =? ");
			query.setParameter(1, jobDetailsZip.getSkill());
			query.setParameter(2, jobDetailsZip.getFootFall());
			query.setParameter(3, jobDetailsZip.getUploadedCV());
			query.setParameter(4, jobDetailsZip.getContentType());
			query.setParameter(5, jobDetailsZip.getCvCount());
			query.setParameter(6, jobDetailsZip.getFileName());
			query.setParameter(7, jobDetailsZip.getJobdescId());
			query.executeUpdate();
		}

	}
	
	@Override
	public void deleteUploadedCVZip(long jobDetailId) {
	
		try {
			logger.info("Inside DAO deleteUploadedCVZip.. jobDetailId ."+ jobDetailId);

			Query query = entityManager.createQuery(
					"Update InterviewPortalJobDetails jd SET jd.uploadedCV = null, jd.contentType =null, jd.cvCount ='',jd.fileName='' where jd.jobdescId =? ");
		
			query.setParameter(1, jobDetailId);
			query.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception  ::  " + e.getMessage());
		}
		
	}
	

	@Override
	public void uploadOpenRR(List<InterviewPortalOpenRR> openRRList) {

		for (InterviewPortalOpenRR xls : openRRList) {
			entityManager.persist(xls);
		}

	}

	@Override
	public List<InterviewPortalOpenRR> getOpenRRPanel(long jobDetailId) {
		List<InterviewPortalOpenRR> openRRList = null;
		try {
			openRRList = entityManager.createQuery("select e from InterviewPortalOpenRR e order by e.date desc")
					.getResultList();
		} catch (Exception e) {
			logger.error("Exception occurred during getOpenRRPanel:  " + e.getMessage());
		}
		return openRRList;
	}

	@Override
	public List<InterviewPortalJobDetails> loadAllScheduledInterviews() {
		List<InterviewPortalJobDetails> listScheduledInterviews = null;
		try {
			listScheduledInterviews = entityManager
					.createQuery("select jd from InterviewPortalJobDetails jd order by jd.driveDate desc")
					.getResultList();
		} catch (Exception e) {
			logger.error("Exception occurred during loading all the scheduled interviews  " + e.getMessage());
		}

		return listScheduledInterviews;
	}

	@Override
	public List<InterviewPortalOpenRR> loadOpenRRById(long jobDetailId) {

		List<InterviewPortalOpenRR> openRRList = null;
		List<InterviewPortalOpenRR> sendOpenRRList = new ArrayList<InterviewPortalOpenRR>();

		try {

			Query query = entityManager
					.createQuery("select rr from InterviewPortalOpenRR rr where rr.jobdescId =? order by rr.date desc");

			query.setParameter(1, jobDetailId);

			openRRList = query.getResultList();
			
			for (int i = 0; i < openRRList.size(); i++) {
				
				InterviewPortalOpenRR rr = openRRList.get(i);				
				rr.setPrimaryID(rr.getId());
				sendOpenRRList.add(rr);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception  occurred during loading open RR by Id: " + e.getMessage());
		}
		return sendOpenRRList;
	}

	@Override
	public void updateL2JDDesc(String rrId, long jdId, String l2JdDesc) {

		try {
			Query query = entityManager.createQuery(
					"Update InterviewPortalOpenRR rr SET rr.l2JdDesc = ? where rr.rrId =? and rr.jobdescId =? ");
			query.setParameter(1, l2JdDesc);
			query.setParameter(2, rrId);
			query.setParameter(3, jdId);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Exception  occurred during updating JD: " + e.getMessage());

		}

	}
	
	
	public void deleteJDbyJobDescID(Long jobDescId) {
		
		InterviewPortalJobDetails model = findById(jobDescId);
		logger.info("...in DAO...deleteJDbyJobDescID   model"+model);
		if(model != null) {
			entityManager.remove(model);
		}
	}
	
	
	@Override
	public InterviewPortalOpenRR getRRById(long Id) {
		InterviewPortalOpenRR rrDetails = null;
		try {
			rrDetails = (InterviewPortalOpenRR) entityManager.find(InterviewPortalOpenRR.class, Id);

		} catch (Exception e) {
			logger.error("Exception  occurred :  " + e.getMessage());
		}
		return rrDetails;

	}
	
	public void deleteRRbyId(Long Id) {
		
		InterviewPortalOpenRR model = getRRById(Id);
		logger.info("...in DAO...deleteRRbyId   model"+model);
		if(model != null) {
			entityManager.remove(model);
		}
	}
	
	
	
	//Admin Start
	
	@Override
	public void createUpdateAdminPanelXls(List<InterviewPortalPanel> list, String event) {
		
		for(InterviewPortalPanel xls : list) {	
			if(event.equalsIgnoreCase("create")) {
				entityManager.persist(xls);
			}else {
				entityManager.merge(xls);
			}
		
		}
	}
	
	@Override
	public List<InterviewPortalPanel> getAllAdminPanel(){
		List<InterviewPortalPanel> list = null;
		try {
			list = entityManager.createQuery("select e from InterviewPortalPanel e order by creationDate desc")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception  ::  " + e.getMessage());
		}
		return list;
	}
	
	public InterviewPortalPanel getPanelByEmpId(Long empId) {
		InterviewPortalPanel panel = null;			
		panel=  entityManager.find(InterviewPortalPanel.class, empId);		
		return panel;
	}
	
	public void deletePanelByEmpId(Long empId) {
		InterviewPortalPanel model = getPanelByEmpId(empId);
		if(model != null) {
			entityManager.remove(model);
			
			deleteReportByPanelId(empId);
			
			deletePanelJDMappingByPanelId(empId);
		}
	}
	//Admin End
	
	
	
	//Comp Lead Starts
	
	/**
	 * @param interviewPortalJobDetails
	 * Update JD1 screen - Update the L1 description
	 */
		@Override
		public void updateJDForL1(InterviewPortalJobDetails interviewPortalJobDetails) {
				
			try {	
			
				Query query = entityManager.createQuery("Update InterviewPortalJobDetails jd SET jd.l1Description = ?  where UPPER(jd.skill) =?");
				
				query.setParameter(1, interviewPortalJobDetails.getL1Description());

				query.setParameter(2, (interviewPortalJobDetails.getSkill().trim()).toUpperCase());
				
				int i = query.executeUpdate();
				
				logger.info("Inside DAO updateJDForL1.. update result  ."+ i);
				
			} catch(Exception e) {
				logger.info("Exception  :: >>>>>>>>>>> "+e.getMessage());
					
			}
			
		}
	

	/**
	 * @param jdId
	 * @return List<InterviewPortalIntrvwPanelMapping>
	 * Comp Lead -  View Panel status screen - get panel for an Interview Drive
	 */
	@Override
	public List<InterviewPortalIntrvwPanelMapping> loadPanelAcceptedForIntervwByJD(Long jdId) {
		logger.info("Inside DAO loadPanelAcceptedForIntervw..."+jdId);
		
	
		List<InterviewPortalIntrvwPanelMapping> listAcceptedPanelforJD = null;

		
		try {		
		
			Query query  = entityManager.createQuery("select p from InterviewPortalIntrvwPanelMapping p where jobID = ?");
				
			query.setParameter(1, jdId);
			
			listAcceptedPanelforJD = query.getResultList();
					
				if(listAcceptedPanelforJD != null) {
					logger.info("Inside DAO panel mapping..."+listAcceptedPanelforJD.size());
					logger.info("Inside DAO panel map...panel name "+listAcceptedPanelforJD.get(0).getPanelDetails().getName());
					logger.info("Inside DAO panel map...panel level "+listAcceptedPanelforJD.get(0).getInterviewLevel());
					logger.info("Inside DAO panel map...panel date "+listAcceptedPanelforJD.get(0).getInterviewDate());


				}

			
		
	} catch (Exception e) {
		logger.error("Exception  ::  " + e.getMessage());
	}

		return listAcceptedPanelforJD;
	}




	/**
	 * @param jdPanel
	 * @return boolean
	 * Comp Lead -  Interview Panel Mapping screen - Add panel for a drive
	 */
	@Override
	public boolean addPanelForInterview(InterviewPortalIntrvwPanelMapping jdPanel) {
		logger.info("Inside DAO addPanelForInterview..."+jdPanel.toString());
		
		InterviewPortalIntrvwPanelMapping addedJDPanel = null;
		boolean isPanelMapped = false; 
		try {
						
			Query query  = entityManager.createQuery("select p from InterviewPortalIntrvwPanelMapping p where jobID = ? and panelID = ? and interviewDate = ? and interviewLevel = ?");
			
			query.setParameter(1, jdPanel.getJobID());
			query.setParameter(2, jdPanel.getPanelID());
			query.setParameter(3, jdPanel.getInterviewDate());
			query.setParameter(4, jdPanel.getInterviewLevel());
			List l = query.getResultList();

			logger.info("Inside DAO addPanelForInterview level.. entry already presnet list "+ l.size());
			if(l.size() == 0)
			{
				entityManager.persist(jdPanel);
			}
			isPanelMapped = true;

			
		} catch (Exception e) {
			logger.error("Exception  ::  " + e.getMessage());
			isPanelMapped = false;
		}
		return isPanelMapped;
	}
	
	
	public void deletePanelJDMappingByPanelId(long panelID) {
		try {	
			
			Query query = entityManager.createQuery(
					"Delete from InterviewPortalIntrvwPanelMapping p where p.panelID =? ");
			query.setParameter(1, panelID);		
			query.executeUpdate();
			
		} catch (Exception e) {
			logger.error("Exception while deleting Panel - JD mapping  ::  " + e.getMessage());
		}
	}
	
	
	/**
	 * @param skill
	 * @return List <InterviewPortalPanel>
	 *  Comp Lead - Interview Panel Mapping screen - View panel for a Skill
	 */
	@Override
	public List<InterviewPortalPanel> panelAvailableForIntervwBySkill(String skill)
	{
		List<InterviewPortalPanel> panelAvailable = null;
		
		try {
			

			logger.info("Inside DAO panelAvailableForIntervwBySkill..."+skill);

			Query query  = entityManager.createQuery("select e from InterviewPortalPanel e  where UPPER(e.technology) LIKE ? order by creationDate desc");
		
			query.setParameter(1, ("%"+skill.trim()).toUpperCase()+"%");			
			
			panelAvailable = query.getResultList();
			
			for (InterviewPortalPanel  p : panelAvailable) {

				Query queryInterviewDates  = entityManager.createQuery("select distinct interviewDate  from InterviewPortalPanelReport where panelID = ? order by interviewDate desc");
				
				queryInterviewDates.setParameter(1, p.getEmpId());	
				queryInterviewDates.setFirstResult(0);
				queryInterviewDates.setMaxResults(4);
				
				
				List pastDateList = new ArrayList<>();
				Calendar cal = Calendar.getInstance();
				
				logger.info(".....Inside DAO panelAvailableForIntervwBySkill..		panel is  "+	p.getEmpId());
				
				logger.info(".....Inside DAO queryInterviewDates.getResultList().size().  "+	queryInterviewDates.getResultList().size());

				
				
				for (int i=0; i< queryInterviewDates.getResultList().size(); i++)
				{
					Timestamp ts = (Timestamp) (queryInterviewDates.getResultList().get(i));
										
					if(ts.before(cal.getTime())) {
						pastDateList.add(ts);
					
					}
					
				}					
										
			
					for (int i=0; i< pastDateList.size(); i++) {
				
						if(i==0)
						{
							p.setRecentInterview1(new SimpleDateFormat("dd-MM-yyyy").format(pastDateList.get(i)));
				

						}else if(i==1)
						{
							p.setRecentInterview2(new SimpleDateFormat("dd-MM-yyyy").format(pastDateList.get(i)));
	
						}else if(i==2)
						{
							p.setRecentInterview3(new SimpleDateFormat("dd-MM-yyyy").format(pastDateList.get(i)));
	
						}
						else if(i==3)
						{
							p.setRecentInterview4(new SimpleDateFormat("dd-MM-yyyy").format(pastDateList.get(i)));
	
						}			

					
				}
			
				
				logger.info("Inside DAO panelAvailableForIntervwBySkill.. panelAvailable ."+p.toString());


			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception  ::  " + e.getMessage());
		}
		return panelAvailable;
	}
	//Comp Lead Ends
	
	/* Candidate Module Start */
	
	@Override
	public void addCandidateFeedback(InterviewPortalCandidateFeedback feedback) {
		entityManager.persist(feedback);
	}

	@Override
	public List<InterviewPortalCandidateFeedback> findAllCandidatesFeedbackListByJdId(Long jdId) {
		List<InterviewPortalCandidateFeedback> candidateFeedbackListbyJdId = null;
		try {
			Query query =  entityManager.createQuery("select c from InterviewPortalCandidateFeedback c where c.jdId = ? order by date desc");
			query.setParameter(1, jdId);
			
			candidateFeedbackListbyJdId = query.getResultList();
			
		} catch (Exception e) {
			logger.error("Inside findAllCandidatesFeedbackListByJdId, Exception  ::  " + e.getMessage());
		}
		return candidateFeedbackListbyJdId;
	}
	
	/* Candidate Module End */
	public List<InterviewPanelReportVO> getTopPanel(int days){
			
			List<InterviewPanelReportVO> list = new ArrayList<InterviewPanelReportVO>();
			
			String qry = "SELECT COUNT (DISTINCT interviewDate ) as COUNT , panelID AS panelID " + "FROM InterviewPortalPanelReport AS e where ((sysdate)  - INTERVIEW_DATE ) < " + days + " GROUP BY e.panelID " ;
			
			/*String qry = "SELECT COUNT(panelID) as COUNT, panelID as panelID ,  panelDetails as panelDetails "
					+ "FROM InterviewPortalPanelReport AS e where  ((sysdate)  - INTERVIEW_DATE ) < " + days + " GROUP BY e.panelID order";*/
			// "SELECT COUNT(panelID) as COUNT, panelID as panelID ,  panelDetails as panelDetails FROM InterviewPortalPanelReport AS e GROUP BY e.panelID"
			
			List<Object[]> results = entityManager.createQuery(qry).getResultList();			
			
			for (Object[] result : results) {
				//InterviewPortalPanelReport rep = new InterviewPortalPanelReport();
				InterviewPanelReportVO rep = new InterviewPanelReportVO();
			    long count = ((Number) result[0]).longValue();
			    long panel  = ((Number) result[1]).longValue();
			    //rep.setInterviewCount(count);
			   // rep.setPanelID(panel);			    	
			    InterviewPortalPanel panelDetails = getPanelByEmpId(panel);
			    //InterviewPortalPanel panelDetails = (InterviewPortalPanel) result[2];
			 //   rep.setPanelDetails(panelDetails);
			    if(panelDetails != null)
			    {
			    rep.setEmpId(panelDetails.getEmpId());
			    rep.setName(panelDetails.getName());
			    rep.setAccount(panelDetails.getAccount());
			    rep.setDesignation(panelDetails.getDesignation());
			    rep.setTechnology(panelDetails.getTechnology());
			    rep.setProduct(panelDetails.getProduct());
			    rep.setStatus(panelDetails.getStatus());
			    rep.setInterviewCount(count);
			    }
			    list.add(rep);
			}			
			return list;			
	}
	
	public void addPanelReporting(InterviewPortalPanelReport report) {
		entityManager.persist(report);
	}
	
	public void deleteReportByPanelId(long panelID) {
		Query query = entityManager.createQuery(
				"Delete from InterviewPortalPanelReport rep where rep.panelID =? ");
		query.setParameter(1, panelID);		
		query.executeUpdate();
	}
	
	
}
