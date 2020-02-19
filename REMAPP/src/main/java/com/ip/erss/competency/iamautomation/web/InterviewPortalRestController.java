package com.ip.erss.competency.iamautomation.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ip.erss.competency.iamautomation.model.InterviewPortalCandidateFeedback;
import com.ip.erss.competency.iamautomation.model.InterviewPortalIntrvwPanelMapping;
import com.ip.erss.competency.iamautomation.model.InterviewPortalJobDetails;
import com.ip.erss.competency.iamautomation.model.InterviewPortalOpenRR;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;
import com.ip.erss.competency.iamautomation.model.InterviewPortalPanelReport;
import com.ip.erss.competency.iamautomation.service.InterviewPortalADService;
import com.ip.erss.competency.iamautomation.service.InterviewPortalService;
import com.ip.erss.competency.iamautomation.service.MailNotificationService;
import com.ip.erss.competency.iamautomation.service.UserService;
import com.ip.erss.competency.iamautomation.vo.InterviewPanelReportVO;


@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ip.erss.competency.iamautomation.service,com.ip.erss.competency.sp.service.impl,"
		+ "com.ip.erss.competency.iamautomation.dao,com.ip.erss.competency.iamautomation.dao.impl")
@RequestMapping(value="/interview")
public class InterviewPortalRestController {

	
	private static final Logger logger = LoggerFactory.getLogger(InterviewPortalRestController.class);
	
	@Autowired
	private InterviewPortalService service;
	
	@Autowired
	private InterviewPortalADService adService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private MailNotificationService mailNotificationService;
		
	@Value("${spring.mail.host}")
	private String smtpHost;

	@Value("${spring.mail.port}")
	private String smtpPort;
	
	@RequestMapping(value = "/hr/createJobDetails/", method = RequestMethod.POST)
	public ResponseEntity<Void> createHrDetails(@RequestBody InterviewPortalJobDetails jobDetails ){
		
		HttpHeaders headers = new HttpHeaders();
		try {
			service.createRequirementDetails(jobDetails);
		} catch(Exception e) {
			
			logger.info("Exception occurred during HR - create requirement!",e.getMessage());
			
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
			
		}
		
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/hr/interviewJobDetailsPanel/", method = RequestMethod.GET)
	public List<InterviewPortalJobDetails> interviewJobDetailsPanel() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		List<InterviewPortalJobDetails> jobDetailsList = null;
		jobDetailsList = service.getCreateRequirementPanel(userName);
		return jobDetailsList;
	}
	
	
	
	@RequestMapping(value = "/hr/uploadRequirementXLS/", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadHRRequirementXLS(@RequestParam(value = "file") MultipartFile file) throws IOException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		List<InterviewPortalJobDetails> list = new ArrayList<InterviewPortalJobDetails>();
		InputStream inputStream = null;
		HttpHeaders headers = new HttpHeaders();
		if (null != file) {
			String strFileName = file.getOriginalFilename();
			String fileExt = getFileExtension(strFileName);
			try {
				inputStream = file.getInputStream();
				/*
				 * Workbook workbook = null; if(fileExt.equalsIgnoreCase("xlsx")){ workbook =
				 * new XSSFWorkbook(inputStream); }else { workbook = new
				 * HSSFWorkbook(inputStream); }
				 */

				XSSFWorkbook wb = new XSSFWorkbook(inputStream);
				XSSFWorkbook test = new XSSFWorkbook();
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;

				Iterator rows = sheet.rowIterator();
				int rowCount = 0;
				
				while (rows.hasNext()) {
					int cellCount = 0;					
					row = (XSSFRow) rows.next();
					if (rowCount != 0) {
						InterviewPortalJobDetails xlsJobDetails = new InterviewPortalJobDetails();
						
						Iterator cells = row.cellIterator();
						while (cells.hasNext()) {
							cell = (XSSFCell) cells.next();
							if(cellCount !=5) {
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							}
							if (cellCount == 0) {
								String empno = cell.getStringCellValue();
								xlsJobDetails.setSkill(cell.getStringCellValue());
							}							
							if (cellCount == 1) {
								xlsJobDetails.setLocation(cell.getStringCellValue());
							}
							if (cellCount == 2) {
								xlsJobDetails.setExperience(cell.getStringCellValue());
							}
							if (cellCount == 3) {
								xlsJobDetails.setFootFall(cell.getStringCellValue());
							}
							if (cellCount == 4) {
								xlsJobDetails.setPanelsCount(cell.getStringCellValue());
							}
							if (cellCount == 5) {
								xlsJobDetails.setDriveDate(cell.getDateCellValue());
							}
							if (cellCount == 6) {
								xlsJobDetails.setSkillOwner(cell.getStringCellValue());
							}
							if (cellCount == 7) {
								xlsJobDetails.setLevel(cell.getStringCellValue());
							}
							if (cellCount == 8) {
								xlsJobDetails.setJobLocation(cell.getStringCellValue());
							}
							if (cellCount == 9) {
								xlsJobDetails.setReqCount(cell.getStringCellValue());
							}
							++cellCount;
						}
						
						list.add(xlsJobDetails);
					}
					++rowCount;
				}
				
				try {
				service.uploadRequirementDetailsXLS(list);
				}catch (Exception e) {
					logger.error("Exception occurred during HR - create requirement!",e.getMessage());
					return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
				}
			}

			catch (Exception e) {
				logger.error("Exception occurred during Upload XLS!",e.getMessage());
				return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
			} finally {
				inputStream.close();
			}
		}
		
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	private String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}
	
	
	
	@RequestMapping(value = "/hr/uploadCandidateCVByID/{jobDetailId}", method = RequestMethod.GET)
	public InterviewPortalJobDetails uploadCandidateCVByID(@PathVariable("jobDetailId") long jobDetailId) {
		return  service.getCandidateCVByID(jobDetailId);
	}
	
	@RequestMapping(value = "/hr/uploadCVZip/", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadCVZip(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "jobdescId") String jobdescId, @RequestParam(value = "skill") String skill,
			@RequestParam(value = "footFall") String footFall, @RequestParam(value = "cvCount") String cvCount)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		if (null != file && file.getSize() > 0) {
			InterviewPortalJobDetails jobDetails = new InterviewPortalJobDetails();
			jobDetails.setUploadedCV(file.getBytes());
			jobDetails.setFileName(file.getOriginalFilename());
			jobDetails.setContentType(file.getContentType());
			jobDetails.setFootFall(footFall);
			jobDetails.setCvCount(cvCount);
			jobDetails.setSkill(skill);
			if (null != jobdescId && !"null".equalsIgnoreCase(jobdescId)) {
				jobDetails.setJobdescId(Long.valueOf(jobdescId));
			}
			try {
			service.uploadRequirementDetailsZip(jobDetails);
			}catch (Exception e) {
				return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/hr/deleteUploadedCVZip/{jobDetailId}", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteUploadedCVZip(@PathVariable(value = "jobDetailId", required = true)
	long jobDetailId, HttpServletResponse response) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		try {
			logger.info("In controller deleteUploadedCVZip job id  ::  "+jobDetailId);
			service.deleteUploadedCVZip(jobDetailId);
			
			}catch (Exception e) {
				logger.error("deleteUploadedCVZip Exception  ::  "+e.getMessage());

				return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
			}
		
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/hr/downloadCandidateCV/{jobDetailId}", method = RequestMethod.GET)
	public ResponseEntity<Void> downloadCandidateCV(@PathVariable(value = "jobDetailId", required = true)
			long jobDetailId, HttpServletResponse response) throws IOException {
		OutputStream outStream = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			InterviewPortalJobDetails candidateCV = service.getCandidateCVByID(jobDetailId);
			if(candidateCV != null) {
				outStream = response.getOutputStream();
				response.setHeader("Content-Disposition", "attachment; filename=\"" + candidateCV.getFileName() +"\"");
				response.setContentType(candidateCV.getContentType());
				FileCopyUtils.copy(candidateCV.getUploadedCV(), response.getOutputStream());
			}

		} catch (IOException ie) {
			logger.error("downloadCVFile IOException  ::  "+ie.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		} catch(Exception e) {
			logger.error("downloadCVFile Exception  ::  "+e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		} finally {
			outStream.flush();
			outStream.close();
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hr/uploadOpenRRXLS/", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadOpenRR(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "jobdescId") long jobdescId) throws IOException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		List<InterviewPortalOpenRR> list = new ArrayList<InterviewPortalOpenRR>();
		InputStream inputStream = null;
		HttpHeaders headers = new HttpHeaders();
		if (null != file) {
			String strFileName = file.getOriginalFilename();
			try {
				inputStream = file.getInputStream();
				/*
				 * Workbook workbook = null; if(fileExt.equalsIgnoreCase("xlsx")){ workbook =
				 * new XSSFWorkbook(inputStream); }else { workbook = new
				 * HSSFWorkbook(inputStream); }
				 */

				XSSFWorkbook wb = new XSSFWorkbook(inputStream);
				XSSFWorkbook test = new XSSFWorkbook();
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;

				Iterator rows = sheet.rowIterator();
				int rowCount = 0;

				while (rows.hasNext()) {
					int cellCount = 0;
					row = (XSSFRow) rows.next();
					if (rowCount != 0) {
						InterviewPortalOpenRR xlsOpenRR = new InterviewPortalOpenRR();
						xlsOpenRR.setDate(new Date());
						xlsOpenRR.setJobdescId(jobdescId);
						Iterator cells = row.cellIterator();
						while (cells.hasNext()) {
							cell = (XSSFCell) cells.next();
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);

							if (cellCount == 0) {
								String empno = cell.getStringCellValue();
								xlsOpenRR.setRrId(cell.getStringCellValue());
							}
							if (cellCount == 1) {
								xlsOpenRR.setSkill(cell.getStringCellValue());
							}
							if (cellCount == 2) {
								xlsOpenRR.setLevel(cell.getStringCellValue());
							}
							if (cellCount == 3) {
								xlsOpenRR.setDemand(cell.getStringCellValue());
							}
							if (cellCount == 4) {
								xlsOpenRR.setFitment(cell.getStringCellValue());
							}
							if (cellCount == 5) {
								xlsOpenRR.setHiringManager(cell.getStringCellValue());
							}
							if (cellCount == 6) {
								xlsOpenRR.setRecruiter(cell.getStringCellValue());
							}
							if (cellCount == 7) {
								xlsOpenRR.setAccountName(cell.getStringCellValue());
							}
							if (cellCount == 8) {
								xlsOpenRR.setGap(cell.getStringCellValue());
							}
							if (cellCount == 9) {
								xlsOpenRR.setAgeingDays(cell.getStringCellValue());
							}
							if (cellCount == 10) {
								xlsOpenRR.setLocation(cell.getStringCellValue());
							}
							if (cellCount == 11) {
								xlsOpenRR.setVerticalGrp(cell.getStringCellValue());
							}
							if (cellCount == 12) {
								xlsOpenRR.setStatus(cell.getStringCellValue());
							}
							++cellCount;
						}

						list.add(xlsOpenRR);
					}
					++rowCount;
				}

				try {
					service.uploadOpenRR(list);
				} catch (Exception e) {
					logger.error("Exception occurred during HR - create requirement!", e.getMessage());
					return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
				}
			}

			catch (Exception e) {
				logger.error("Exception occurred during Upload RR!", e.getMessage());
				return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
			} finally {
				inputStream.close();
			}
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/hr/interviewOpenRRPanel/{jobDetailId}", method = RequestMethod.GET)
	public List<InterviewPortalOpenRR> interviewOpenRRPanel(@PathVariable(value = "jobDetailId") long jobDetailId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<InterviewPortalOpenRR> openRRList = null;
		openRRList = service.loadOpenRRById(jobDetailId);
		return openRRList;
	}
	
	
	@RequestMapping(value = "/hr/deleteJobRequirement/{jobDetailId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteJobRequiement(@PathVariable("jobDetailId") Long jobDetailId, UriComponentsBuilder ucBuilder) {
		//logger.info("Going to Delete JD : " + jobDetailId);
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setLocation( ucBuilder.path("/interview/hr/deleteJobRequirement/{jobDetailId}").buildAndExpand(jobDetailId).toUri());
			InterviewPortalJobDetails JobRequirement = service.getCandidateCVByID(jobDetailId);
			if (JobRequirement == null) {
				logger.info("there is no records found " + jobDetailId);
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
			service.deleteJDbyJobDescID(jobDetailId);		
		} catch(Exception e)
		{
			logger.error("Exception occurred during HR - delete requirement!", e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/hr/deleteRR/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRR(@PathVariable("Id") Long Id, UriComponentsBuilder ucBuilder) {
		logger.info("Going to Delete RR : " + Id);
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setLocation( ucBuilder.path("/interview/hr/deleteRR/{Id}").buildAndExpand(Id).toUri());
			InterviewPortalOpenRR rrDetail = service.getRRById(Id);
			if (rrDetail == null) {
				logger.info("there is no records found " + Id);
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
			service.deleteRRbyId(Id);		
		} catch(Exception e)
		{
			logger.error("Exception occurred during HR - delete RR!", e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/compLead/loadOpenRRById/{jdId}", method = RequestMethod.GET)
	public List<InterviewPortalOpenRR> loadOpenRRById(@PathVariable("jdId") Long jdId) {
		logger.info("Entering method :: loadOpenRRById, jdId -->"+jdId);
		return service.loadOpenRRById(jdId);
	}
	
	
	@RequestMapping(value = "/compLead/updatL2JD/", method = RequestMethod.POST)
	public  ResponseEntity<Void> updatL2JD(@RequestParam(value ="rrId") String rrId,@RequestParam(value ="jobdescId") long jdId,@RequestParam(value ="l2JdDesc") String l2JdDesc) {
		
		logger.info("Inside UpdateL2JD Java Rest Controller!!");
		HttpHeaders headers = new HttpHeaders();
		try {
			service.updateL2JDDesc(rrId,jdId,l2JdDesc);
		}catch (Exception e) {
			logger.error("Exception occurred during Comp Lead  - updatL2JD!", e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}
	
	
	
// Admin Start
	
	@RequestMapping(value = "/admin/fileUpload/", method = RequestMethod.POST)
	public List<InterviewPortalPanel> adminpanelupload(@RequestParam(value = "file") MultipartFile file) throws IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
				
		List<InterviewPortalPanel> list = new ArrayList<InterviewPortalPanel>();

		InputStream inputStream = null;
		if (null != file) {
			try {
			String strFileName = file.getOriginalFilename();
			// FileInputStream input = new FileInputStream(file);
			String fileExt = getFileExtension(strFileName);


				inputStream = file.getInputStream();
	

				XSSFWorkbook wb = new XSSFWorkbook(inputStream);
				XSSFWorkbook test = new XSSFWorkbook();
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;

				Iterator rows = sheet.rowIterator();
				int rowCount = 0;
				
				while (rows.hasNext()) {
					int cellCount = 0;					
					row = (XSSFRow) rows.next();
					if (rowCount != 0) {
						InterviewPortalPanel panel = new InterviewPortalPanel();
						panel.setCreationDate(new Date());
						panel.setInsertedBy(userName);
						
						InterviewPortalPanel adInfo = new InterviewPortalPanel();
						boolean isValid = true;
						
						Iterator cells = row.cellIterator();
						while (cells.hasNext()) {
							cell = (XSSFCell) cells.next();
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							
							if (cellCount == 0) {
								String empno = cell.getStringCellValue();
								try {

									adInfo = adService.getEmployeeDetails(empno.trim(),"" );

									isValid = adInfo.isValid();
								}catch(Exception ex) {
									isValid = false;
								}
								panel.setEmpId(Long.parseLong(empno));
							}							
							if (cellCount == 1) {
								//
								if(isValid) {
									panel.setName(adInfo.getName());
								}else {
									panel.setName(cell.getStringCellValue());
								}								
							}
							if (cellCount == 2) {
								if(isValid) {
									panel.setAccount(cell.getStringCellValue());
								}
								else {
									panel.setAccount("Invalid Emp Id");
								}
							}
							if (cellCount == 3) {
								if(isValid) {
									panel.setLocation(cell.getStringCellValue());
								}
								else {
									panel.setLocation("-");
								}
								
							}
							if (cellCount == 4) {
								if(isValid) {
									panel.setDesignation(cell.getStringCellValue());
								}
								else {
									panel.setDesignation("-");
								}
							
							}
							if (cellCount == 5) {
								if(isValid) {
									panel.setPhone(cell.getStringCellValue());
								}
								else {
									panel.setPhone("-");
								}
								
							}
							if (cellCount == 6) {
								if(isValid) {
									panel.setTechnology(cell.getStringCellValue());
								}
								else {
									panel.setTechnology("-");
								}
								
							}
							if (cellCount == 7) {
								if(isValid) {
									panel.setProduct(cell.getStringCellValue());
								}
								else {
									panel.setProduct("-");
								}
								
							}
							
							panel.setValid(isValid);
							
							// System.out.print(cell.getStringCellValue() + " ");

							++cellCount;
						}
						
						// System.out.println();
						list.add(panel);
					}
					++rowCount;
				}

			//	service.createAdminPanelXls(list);
			}

			catch (Exception e) {
				logger.error("Exception while file upload  ::  " + e.getMessage());

				e.printStackTrace();
			} finally {
				inputStream.close();
				// bufferedReader.close();
			}
		}
		return list;
	}

	@RequestMapping(value = "/admin/getallpanel/", method = RequestMethod.GET)
	public List<InterviewPortalPanel> loadAllPanelList() {
			
		List<InterviewPortalPanel> allAdminPanelList = null;
		allAdminPanelList = service.getAllAdminPanel();
		return allAdminPanelList;
	}
	
	@RequestMapping(value = "/admin/allpanelupdate/", method = RequestMethod.POST)
	public List<InterviewPortalPanel> updatePanelList(@RequestBody List<InterviewPortalPanel> panelArray) throws JsonProcessingException {
		List<InterviewPortalPanel> list = new ArrayList<InterviewPortalPanel>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		for(InterviewPortalPanel panel : panelArray) {
			
			if(panel.isSelected()){	
				panel.setCreationDate(new Date());
				panel.setInsertedBy(userName);
				panel.setStatus("ACTIVE");
				list.add(panel);
			}
		}
		service.createUpdateAdminPanelXls(list,"create");
		List<InterviewPortalPanel> allAdminPanelList = null;
		allAdminPanelList = service.getAllAdminPanel();
		return allAdminPanelList;
	}
	
	/*@RequestMapping(value = "/admin/create/", method = RequestMethod.POST)
	public List<InterviewPortalPanel> createSinglePanel(@RequestBody InterviewPortalPanel panel) throws JsonProcessingException {
		String event ="create";
		List<InterviewPortalPanel> list = new ArrayList<InterviewPortalPanel>();
		list.add(panel);
		service.createUpdateAdminPanelXls(list,event);
		
		List<InterviewPortalPanel> allAdminPanelList = null;
		allAdminPanelList = service.getAllAdminPanel();
		return allAdminPanelList;
	}*/
	
	@RequestMapping(value = "/admin/create/", method = RequestMethod.POST)
	public ResponseEntity<Void> createSinglePanel(@RequestBody InterviewPortalPanel panel) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation( ucBuilder.path("/interview/admin/create/").buildAndExpand(empId).toUri());
		String event ="create";
		
		InterviewPortalPanel adInfo = new InterviewPortalPanel();
		
		try {
		
			adInfo = adService.getEmployeeDetails(String.valueOf(panel.getEmpId()),"" );
	
			boolean isValid = adInfo.isValid();
			if(isValid) {
				panel.setName(adInfo.getName());
				List<InterviewPortalPanel> list = new ArrayList<InterviewPortalPanel>();			
				list.add(panel);
				service.createUpdateAdminPanelXls(list,event);
				return new ResponseEntity<Void>(headers, HttpStatus.OK);
	
			}else {
				return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
	
			}
		}
		catch (Exception e) {
			logger.error("Exception occurred during Admin - create Panel !", e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		//List<InterviewPortalPanel> allAdminPanelList = null;
		//allAdminPanelList = service.getAllAdminPanel();
	}
	
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public List<InterviewPortalPanel> updateSinglePanel(@RequestBody InterviewPortalPanel panel, @RequestParam("event") String event) throws JsonProcessingException {
	
		List<InterviewPortalPanel> list = new ArrayList<InterviewPortalPanel>();
		list.add(panel);
		service.createUpdateAdminPanelXls(list,event);
		
		List<InterviewPortalPanel> allAdminPanelList = null;
		allAdminPanelList = service.getAllAdminPanel();
		return allAdminPanelList;
	}
	
	@RequestMapping(value = "/admin/delete/{empId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePanel(@PathVariable("empId") Long empId,
			UriComponentsBuilder ucBuilder) {
		logger.info("Going to Delete panel : " + empId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation( ucBuilder.path("/interview/admin/delete/{empId}").buildAndExpand(empId).toUri());
		InterviewPortalPanel panel = service.getPanelByEmpId(empId);
		if (panel == null) {
			logger.info("there is no records found " + empId);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		service.deletePanelByEmpId(empId);		
	
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/edit/{empId}", method = RequestMethod.PUT)
	public InterviewPortalPanel editPanel(@PathVariable("empId") Long empId,
			UriComponentsBuilder ucBuilder) {
		logger.info("Going to Delete panel : " + empId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation( ucBuilder.path("/interview/admin/edit/{empId}").buildAndExpand(empId).toUri());
		InterviewPortalPanel panel = service.getPanelByEmpId(empId);
		
		return panel;
	}
	
	
	@RequestMapping(value = "/admin/panelExport/", method = RequestMethod.GET)
	public void panelExport(HttpServletResponse response) throws IOException {
		
		OutputStream outStream = null;
		try {
			List<InterviewPortalPanel> allAdminPanelList = null;
			allAdminPanelList = service.getAllAdminPanel();
			
				outStream = response.getOutputStream();
				response.setHeader("Content-Disposition", "attachment; filename=\"" + "Panel.csv" +"\"");
				response.setContentType("text/csv");
				StringBuilder sb = new StringBuilder();
				sb.append("Emp ID,").append("Empoyee Name,").append("Account,").append("Location,")
				 .append("Designation,").append("Phone,").append("Technology,").append("Product,").append("Status").append("\n");
				
				for(InterviewPortalPanel xls : allAdminPanelList) {
					sb.append(xls.getEmpId() + ",");
					sb.append(xls.getName() + ",");
					sb.append(xls.getAccount() + ",");
					sb.append(xls.getLocation() + ",");
					sb.append(xls.getDesignation() + ",");
					sb.append(xls.getPhone() + ",");
					sb.append(xls.getTechnology() + ",");
					sb.append(xls.getProduct() + ",");
					sb.append(xls.getStatus() + "\n");					
				}
				
				//.append("xyz,").append("pqr");
				outStream.write(sb.toString().getBytes());
				outStream.flush();
				outStream.close();				

		} catch (IOException ie) {
			logger.error("IOException  ::  "+ie.getMessage());
		} catch(Exception e) {
			logger.error("Exception  ::  "+e.getMessage());
		} finally {
			outStream.flush();
			outStream.close();
		}

	}

/*	private String getFileExtension(String fileName) {
		// String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}*/
// Admin End
	
	
	//Comp Lead starts
	
	// Comp Lead - Update JD1 screen - Update the L1 description
	@RequestMapping(value = "/compLead/updateJDforL1", method = RequestMethod.POST)
	public ResponseEntity<Void> updateJDforL1(@RequestBody InterviewPortalJobDetails jobDetails) {
		
		logger.info("Inside updateJDforL1 Java Rest Controller!!");
		HttpHeaders headers = new HttpHeaders();
		
		try {
			service.updateJDForL1(jobDetails);
		
		}catch (Exception e) {
			logger.error("Exception occurred during Comp Lead  - updateJDforL1!", e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
		
	}
	
	
	
	// Comp Lead -  View Panel status screen - get panel for an Interview Drive
	@RequestMapping(value = "/compLead/loadPanelAcceptedForJdId/{jdId}", method = RequestMethod.GET)
	public List<InterviewPortalIntrvwPanelMapping> panelAcceptedForIntervwByJD(@PathVariable("jdId") Long jdId) {
		logger.info("Entering method :: panelAcceptedForIntervwByJD, jdId -->"+jdId);
		return service.panelAcceptedForIntervwByJD(jdId);
	}
	
	
	//  Comp Lead - Interview Panel Mapping screen - View panel for a Skill
	@RequestMapping(value = "/compLead/loadPanelForSkill/{skill}", method = RequestMethod.GET)
	public List<InterviewPortalPanel> panelAvailableForIntervwBySkill(@PathVariable("skill") String skill) {
		logger.info("Entering method :: panelAvailableForIntervwBySkill, skill -->"+skill);
		return service.panelAvailableForIntervwBySkill(skill);
	}
	
	
	//   Comp Lead -  Interview Panel Mapping screen - Add panel for a drive
	@RequestMapping(value = "/compLead/addPanelForInterview/", method = RequestMethod.POST)
	public ResponseEntity<Void> addPanelForInterview(@RequestBody InterviewPortalIntrvwPanelMapping panel) throws JsonProcessingException {
		
		logger.info("Entering method :: addPanelForInterview, panel -->"+panel);
		HttpHeaders headers = new HttpHeaders();
		
	
		
		try {
		// Send mail to Panel once mapped with the interview
			if (service.addPanelForInterview(panel))
			{
				Map<String, String> mailPropMap = new HashMap<String, String>();
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String panelEmailID = adService.getEmployeeEmail(Long.toString(panel.getPanelID()));
				String senderEmailID =  adService.getEmployeeEmail(auth.getName());
				
	
				mailPropMap.put("smtphost", smtpHost);
				mailPropMap.put("smtpport", smtpPort);
				mailPropMap.put("mailContent","You are requested to take " + panel.getInterviewLevel() + " level interview for drive on "+new SimpleDateFormat("dd-MM-yyyy").format(panel.getInterviewDate()));
				mailPropMap.put("skill",panel.getSkill());
				mailPropMap.put("date",new SimpleDateFormat("dd-MM-yyyy").format(panel.getInterviewDate()));			
				mailPropMap.put("toList",panelEmailID);
			    mailPropMap.put("ccList", senderEmailID);
				logger.info("Entering method :: addPanelForInterview, mailPropMap -->"+mailPropMap);
	
				mailNotificationService.sendEmail("E10", mailPropMap);
	
			}
		}catch (Exception e) {
			logger.error("Exception occurred during Comp Lead  - updatL2JD!", e.getMessage());
			return new ResponseEntity<Void>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

		
	}
	
	//Comp Lead Ends
	
	
	/* Candidate Module Start */
	
	@RequestMapping(value = "/panel/loadAllCandidateFeedbacksByJdId/{jdId}", method = RequestMethod.GET)
	public List<InterviewPortalCandidateFeedback> loadAllCandidatesFeedbackListByJdId(@PathVariable("jdId") Long jdId) {
		logger.info("Enter method :: loadAllCandidatesFeedbackListByJdId, jdId -->", jdId);
		return service.findAllCandidatesFeedbackListByJdId(jdId);
	}
	
	@RequestMapping(value = "/panel/addCandidateFeedback/", method = RequestMethod.POST)
	public ResponseEntity<Object> addCandidateFeedback(@RequestBody InterviewPortalCandidateFeedback candidateFeedback) throws JsonProcessingException {
		logger.info("Enter method :: addCandidateFeedback, candidateFeedback -->", candidateFeedback.toString());
		
		try {
			

			service.addCandidateInterviewFeedback(candidateFeedback);
			
			InterviewPortalPanelReport report = new InterviewPortalPanelReport();
			report.setJobID(candidateFeedback.getJdId());
			report.setPanelID(Long.parseLong(candidateFeedback.getInterviewLevelL1PanelId()));
			//report.setInterviewDate(candidateFeedback.getDate());
			
			//Logic to enter only date and not time part in the Panel report table
			Date feedbackDate =  candidateFeedback.getDate();
			
			// Get Calendar object set to the date and time of the given Date object
			Calendar cal = Calendar.getInstance();
			cal.setTime(feedbackDate);
			 
			// Set time fields to zero
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			 
			// Put it back in the Date object
			feedbackDate = cal.getTime();	
			

			
			report.setInterviewDate(feedbackDate);	
			report.setInterviewLevel("L1");
			report.setTechnology(candidateFeedback.getTechnology());
			service.addPanelReporting(report);
			
		} catch(Exception e) {
			logger.error("Exception occurred during Comp Team  - addCandidateFeedback !", e.getMessage());
			return new ResponseEntity<Object>( HttpStatus.EXPECTATION_FAILED);

		}

	
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/panel/updateCandidateFeedback/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCandidateFeedback(@RequestBody InterviewPortalCandidateFeedback candidateFeedback) throws JsonProcessingException {
		logger.info("Enter method :: updateCandidateFeedback, candidateFeedback -->", candidateFeedback.toString());
		service.updateCandidateInterviewFeedback(candidateFeedback);
		try {
			if(StringUtils.isBlank(candidateFeedback.getInterviewLevelHR())) {
				InterviewPortalPanelReport report = new InterviewPortalPanelReport();
				report.setJobID(candidateFeedback.getJdId());
				report.setPanelID(Long.parseLong(candidateFeedback.getInterviewLevelL1PanelId()));
				
				//Logic to enter only date and not time part in the Panel report table
				Date feedbackDate =  candidateFeedback.getDate();
				
				// Get Calendar object set to the date and time of the given Date object
				Calendar cal = Calendar.getInstance();
				cal.setTime(feedbackDate);
				 
				// Set time fields to zero
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				 
				// Put it back in the Date object
				feedbackDate = cal.getTime();			

				
				report.setInterviewDate(feedbackDate);			
				report.setInterviewLevel("L2");
				report.setTechnology(candidateFeedback.getTechnology());
				service.addPanelReporting(report);
			}
			
		}catch(Exception e) {
			logger.error("Exception occurred during Comp Team  - updateCandidateFeedback !", e.getMessage());
			return new ResponseEntity<Object>( HttpStatus.EXPECTATION_FAILED);
		}
		
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/compTeam/toppanel/{days}", method = RequestMethod.PUT)
	public List<InterviewPanelReportVO> getTopPanel(@PathVariable("days") int days,
			UriComponentsBuilder ucBuilder) {
		//logger.info("Going to Delete panel : " + empId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation( ucBuilder.path("/interview/compTeam/toppanel/{days}").buildAndExpand(days).toUri());
		List<InterviewPanelReportVO> panelList = service.getTopPanel(days);
		
		return panelList;
	}
	/* Candidate Module End */
}
