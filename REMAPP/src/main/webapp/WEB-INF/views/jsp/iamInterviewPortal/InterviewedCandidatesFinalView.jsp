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
		ng-controller="InterviewedCandidatesViewDashboardController as ctrl">

		<form class="row">
			<div class="col-lg-6 col-md-6">
				<label><h2>
						View Selected Candidates
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
							<th>EXPECTED FOOTFALL</th>
							<th>ACTUAL FOOTFALL</th>
							<th>L1 SELECT COUNT</th>
							<th>L1 REJECT COUNT</th>
							<th>L2 SELECT COUNT</th>
							<th>L2 REJECT COUNT</th>
							<th>HR SELECT COUNT</th>
							<th>HR REJECT COUNT</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="scheduledInterviews.length <= 0">
							<td colspan="11" style="text-align: center;">No Scheduled
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
							<td><span ng-bind="d.footFall"></span></td>
							<td><span ng-bind="d.actualfootfall"></span></td>
							<td><span ng-bind="d.l1selectcount"></span></td>
							<td><span ng-bind="d.l1rejectcount"></span></td>
							<td><span ng-bind="d.l2selectcount"></span></td>
							<td><span ng-bind="d.l2rejectcount"></span></td>
							<td><span ng-bind="d.selectCount"></span></td>
							<td><span ng-bind="d.rejectCount"></span></td>
						</tr>
					</tbody>
				</table>
			<!-- </div> -->
		</div>


		<div class="panel panel-default" id="candidatesDiv">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Interviewed Candidates List</span>
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
							<th>HR STATUS</th>
							<th>HR FEEDBACK</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="feedbacksList.length <= 0">
							<td colspan="9" style="text-align: center;">No Feedbacks
								Submitted by L1-L2-HR !!</td>
						</tr>
						<tr ng-repeat="f in feedbacksList">
							<td><input type="radio"
								ng-click="onSelectRadioL2CandidateTable(f)" ng-value="f"
								id="candidateSelected" name="candidateSelected"></td>
							<td><span ng-bind="f.candidateId"></span></td>
							<td><span ng-bind="f.fullName"></span></td>
							<td><span ng-bind="f.contactNumber"></span></td>
							<td><span ng-bind="f.technology"></span></td>
							<td><span ng-bind="f.skillset"></span></td>
							<td><span ng-bind="f.interviewLevelHR"></span></td>
							<td><span ng-bind="f.interviewLevelHRSelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelHRFeedback"></span></td>
						</tr>
					</tbody>
				</table>
			<!-- </div> -->
		</div>

		<div class="panel panel-default" id="candidatesFeedbackDiv">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Candidate Feedback</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form name="IntCandidateFeedback" class="form-horizontal">

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
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL1"
									name="interviewLevelL1" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL1.$dirty">
									<span ng-show="IntCandidateFeedback.interviewLevelL1.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Candidate Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.candidateFeedbackROView.fullName"
									name="fullName" class="form-control input-sm" readonly />

								<div class="has-error" ng-show="IntCandidateFeedback.fullName.$dirty">
									<span ng-show="IntCandidateFeedback.fullName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L1 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL1PanelName"
									name="interviewLevelL1PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL1PanelName.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelL1PanelName.$error.required"
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
									ng-model="ctrl.candidateFeedbackROView.technology"
									name="technology" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.technology.$dirty">
									<span ng-show="IntCandidateFeedback.technology.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Feedback</label>
							<div class="col-md-8">
								<!-- <input type="text" style='height: 120px'
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL1Feedback"
									name="interviewLevelL1Feedback" class="form-control input-sm"
									readonly /> -->
								
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL1Feedback"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL1Feedback"
									readonly></textarea>	

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL1Feedback.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelL1Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Total
								Years of Experience</label>
							<div class="col-md-4">
								<input type="number"
									ng-model="ctrl.candidateFeedbackROView.experience"
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
									ng-model="ctrl.candidateFeedbackROView.relevantExperience"
									name="relevantExperience" class="form-control input-sm"
									readonly />
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Selection Status</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL1SelectionStatus"
									name="interviewLevelL1SelectionStatus"
									class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL1SelectionStatus.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelL1SelectionStatus.$error.required"
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
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL2"
									name="interviewLevelL2" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL2.$dirty">
									<span ng-show="IntCandidateFeedback.interviewLevelL2.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L2 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL2PanelName"
									name="interviewLevelL2PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL2PanelName.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelL2PanelName.$error.required"
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
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL2Feedback"
									name="interviewLevelL2Feedback" class="form-control input-sm"
									readonly /> -->
									
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL1Feedback"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL2Feedback"
									readonly></textarea>									

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL2Feedback.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelL2Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L2
								Selection Status</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelL2SelectionStatus"
									name="interviewLevelL2SelectionStatus"
									class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelL2SelectionStatus.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelL2SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">HR Interview Level</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelHR"
									name="interviewLevelHR" class="form-control input-sm" 
									readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelHR.$dirty">
									<span ng-show="IntCandidateFeedback.interviewLevelHR.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">HR Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelHRPanelName"
									name="interviewLevelHRPanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelHRPanelName.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelHRPanelName.$error.required"
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
									ng-model="ctrl.candidateFeedbackROView.interviewLevelHRFeedback"
									name="interviewLevelHRFeedback" class="form-control input-sm"
									readonly /> -->
								
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL1Feedback"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelHRFeedback"
									readonly></textarea>	

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelHRFeedback.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelHRFeedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">HR
								Selection Status</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackROView.interviewLevelHRSelectionStatus"
									name="interviewLevelHRSelectionStatus"
									class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="IntCandidateFeedback.interviewLevelHRSelectionStatus.$dirty">
									<span
										ng-show="IntCandidateFeedback.interviewLevelHRSelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

				</form>
				<div class="form-actions floatRight" align="center">
					<button type="button" ng-click="ctrl.resetIntCanViewFeedback()"
						class="btn btn-warning btn-sm">Close</button>
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
		src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.view.dashboard.controller.js' />"></script>

</body>
</html>	