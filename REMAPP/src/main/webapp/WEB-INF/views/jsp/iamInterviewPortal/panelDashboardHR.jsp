<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />

<head>
<style>
table, td, tr, th {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>

<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="PanelHRDashboardController as ctrl">

		<form class="row">
			<div class="col-lg-6 col-md-6">
				<label><h2>
						HR Panel Dashboard
						<h2></label>
			</div>
		</form>

		<div class="panel panel-default" id="interviewsDiv">
			<div class="panel-heading" style='background-color: #efe6f2;'>
				<span class="lead">Scheduled Interviews</span>
			</div>
			<!-- <div class="tablecontainer"> -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>JD. ID</th>
							<th>SKILL</th>
							<th>SKILL OWNER</th>
							<th>LEVEL</th>
							<th>LOCATION</th>
							<th>DRIVE DATE</th>
							<th>REQUIREMENT COUNT</th>
							<th>CANDIDATE COUNT</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="scheduledInterviews.length <= 0">
							<td colspan="9" style="text-align: center;">No Scheduled
								Interviews!!</td>
						</tr>
						<tr ng-repeat="d in scheduledInterviews">
							<td><input type="radio"
								ng-click="onSelectRadioL2(d)" ng-value="d"
								id="scheduledInterviewSelected"
								name="scheduledInterviewSelected"></td>
							<td><span ng-bind="d.jobdescId"></span></td>
							<td><span ng-bind="d.skill"></span></td>
							<td><span ng-bind="d.skillOwner"></span></td>
							<td><span ng-bind="d.level"></span></td>
							<td><span ng-bind="d.location"></span></td>
							<td><span ng-bind="d.driveDate"></span></td>
							<td><span ng-bind="d.reqCount"></span></td>
							<td><span ng-bind="d.cvCount"></span></td>
						</tr>
					</tbody>
				</table>
			<!-- </div> -->
		</div>


		<div class="panel panel-default" id="candidatesDiv">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">L1-L2 Interviewed Candidates List</span>
			</div>
			<!-- <div class="tablecontainer"> -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>ID#</th>
							<th>NAME</th>
							<th>PHONE</th>
							<th>TECHNOLOGY</th>
							<th>SKILLSET</th>
							<th>LAST INTERVIEWED LEVEL</th>
							<th>L2 STATUS</th>
							<th>L2 FEEDBACK</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="feedbacksList.length <= 0">
							<td colspan="9" style="text-align: center;">No Feedbacks
								Submitted by L1-L2 !!</td>
						</tr>
						
						<tr ng-show="f.interviewLevelL2SelectionStatus=='Select'"
						ng-repeat="f in feedbacksList">
							<td><input type="radio"
								ng-click="onSelectRadioL2CandidateTable(f)" ng-value="f"
								id="candidateSelected" name="candidateSelected"></td>
							<td><span ng-bind="f.candidateId"></span></td>
							<td><span ng-bind="f.fullName"></span></td>
							<td><span ng-bind="f.contactNumber"></span></td>
							<td><span ng-bind="f.technology"></span></td>
							<td><span ng-bind="f.skillset"></span></td>
							<td><span ng-bind="f.interviewLevelL2"></span></td>
							<td><span ng-bind="f.interviewLevelL2SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL2Feedback"></span></td>
						</tr>
						
						
							<tr ng-show="f.interviewLevelL2SelectionStatus=='Reject' || f.interviewLevelL1SelectionStatus=='Reject'"
						ng-repeat="f in feedbacksList">
						<td><input type="hidden"></td>	
							<td><span ng-bind="f.candidateId"></span></td>
							<td><span ng-bind="f.fullName"></span></td>
							<td><span ng-bind="f.contactNumber"></span></td>
							<td><span ng-bind="f.technology"></span></td>
							<td><span ng-bind="f.skillset"></span></td>
							<td><span ng-bind="f.interviewLevelL2"></span></td>
							<td><span ng-bind="f.interviewLevelL2SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL2Feedback"></span></td>
						</tr>
						
						
					</tbody>
				</table>
			<!-- </div> -->
		</div>

		<div class="panel panel-default" id="candidatesFeedbackDiv">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">HR Candidate Feedback Form</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submitHRFeedbackForm(HRFeedbackForm)"
					name="HRFeedbackForm" class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Date</label>
							<div>
								<b><span ng-bind="interview_date"></span><b>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Interview Level</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL1"
									name="interviewLevelL1" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL1.$dirty">
									<span ng-show="HRFeedbackForm.interviewLevelL1.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Candidate Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.candidateFeedbackHR.fullName"
									name="fullName" class="form-control input-sm" readonly />

								<div class="has-error" ng-show="HRFeedbackForm.fullName.$dirty">
									<span ng-show="HRFeedbackForm.fullName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L1 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL1PanelName"
									name="interviewLevelL1PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL1PanelName.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelL1PanelName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Technology/Product</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.technology"
									name="technology" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.technology.$dirty">
									<span ng-show="HRFeedbackForm.technology.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Feedback</label>
							<div class="col-md-8">
								<!-- <input type="text" style='height: 120px'
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL1Feedback"
									name="interviewLevelL1Feedback" class="form-control input-sm"
									readonly /> -->
									
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL1Feedback"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL1Feedback"
									readonly></textarea>	

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL1Feedback.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelL1Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Total
								Years of Experience</label>
							<div class="col-md-4">
								<input type="number"
									ng-model="ctrl.candidateFeedbackHR.experience"
									name="experience" class="form-control input-sm" readonly />
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Relevant Years of
								Experience</label>
							<div class="col-md-8">
								<input type="number"
									ng-model="ctrl.candidateFeedbackHR.relevantExperience"
									name="relevantExperience" class="form-control input-sm"
									readonly />
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Selection Status</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL1SelectionStatus"
									name="interviewLevelL1SelectionStatus"
									class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL1SelectionStatus.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelL1SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Interview Level</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL2"
									name="interviewLevelL2" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL2.$dirty">
									<span ng-show="HRFeedbackForm.interviewLevelL2.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L2 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL2PanelName"
									name="interviewLevelL2PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL2PanelName.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelL2PanelName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L2
								Feedback</label>
							<div class="col-md-8">
								<!-- <input type="text" style='height: 120px'
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL2Feedback"
									name="interviewLevelL2Feedback" class="form-control input-sm"
									readonly /> -->
									
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL2Feedback"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL2Feedback"
									readonly></textarea>									

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL2Feedback.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelL2Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L2
								Selection Status</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelL2SelectionStatus"
									name="interviewLevelL2SelectionStatus"
									class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelL2SelectionStatus.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelL2SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Current Interview Level</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelHR"
									name="interviewLevelHR" class="form-control input-sm" 
									readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelHR.$dirty">
									<span ng-show="HRFeedbackForm.interviewLevelHR.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">HR Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelHRPanelName"
									name="interviewLevelHRPanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelHRPanelName.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelHRPanelName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">HR
								Feedback</label>
							<div class="col-md-8">
								<!-- <input type="text" style='height: 120px'
									ng-model="ctrl.candidateFeedbackHR.interviewLevelHRFeedback"
									name="interviewLevelHRFeedback" class="form-control input-sm"
									placeholder="Enter HR candidate feedback" required /> -->
								
								<textarea class="form-control input-sm" rows="5" id="interviewLevelHRFeedback"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelHRFeedback"
									placeholder="Enter HR candidate feedback" required></textarea> 

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelHRFeedback.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelHRFeedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">HR
								Selection Status</label>
							<div class="col-md-8">
								<select class="form-control" name="interviewLevelHRSelectionStatus"
									ng-options="interviewLevelHRSelectionStatus.value as interviewLevelHRSelectionStatus.value for interviewLevelHRSelectionStatus in IntCandidateStatus"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelHRSelectionStatus"
									ng-select="ctrl.candidateFeedbackHR.interviewLevelHRSelectionStatus" 
									placeholder="Please select status" required>
								</select>

								<div class="has-error" ng-show="HRFeedbackForm.interviewLevelHRSelectionStatus.$dirty">
									<span ng-show="HRFeedbackForm.interviewLevelHRSelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
							<!-- <div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackHR.interviewLevelHRSelectionStatus"
									name="interviewLevelHRSelectionStatus"
									class="form-control input-sm"
									placeholder="Enter HR selection status" required />

								<div class="has-error"
									ng-show="HRFeedbackForm.interviewLevelHRSelectionStatus.$dirty">
									<span
										ng-show="HRFeedbackForm.interviewLevelHRSelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div> -->
						</div>

					</div>

				</form>
				<div class="form-actions floatRight" align="center">
					<input type="button" value="Submit HR Feedback"
						class="btn btn-primary btn-sm"
						ng-disabled="HRFeedbackForm.$invalid"
						ng-click="ctrl.submitHRFeedbackForm(HRFeedbackForm)">
					<button type="button" ng-click="ctrl.resetHRFeedbackForm()"
						class="btn btn-warning btn-sm">Cancel</button>
				</div>
			</div>

		</div>

	</div>
	<jsp:include page="../fragments/footer.jsp" />
	<script
		src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script
		src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.candidate.feedback.service.js' />"></script>
	<script
		src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.job.service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.panel.HRdashboard.controller.js' />"></script>

</body>
</html>