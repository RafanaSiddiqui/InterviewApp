<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
		ng-controller="PanelL1DashboardController as ctrl">

		<form class="row">
			<div class="col-lg-6 col-md-6">
				<label><h2>
						L1 Panel Dashboard
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
							<th>View CV</th>
							<th>JD View/Edit</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="scheduledInterviews.length <= 0">
							<td colspan="9" style="text-align: center;">No Scheduled
								Interviews!!</td>
						</tr>
						<!-- <tr ng-repeat="d in scheduledInterviews"> -->
						<!-- <tr dir-paginate="d in scheduledInterviews|orderBy:sortKey:reverse|filter:search|itemsPerPage:2"> -->
						<tr ng-repeat="d in scheduledInterviews">
							<td><input type="radio" ng-click="onSelectRadio(d)"
								ng-value="d" id="scheduledInterviewSelected"
								name="scheduledInterviewSelected"></td>
							<td><span ng-bind="d.jobdescId"></span></td>
							<td><span ng-bind="d.skill"></span></td>
							<td><span ng-bind="d.skillOwner"></span></td>
							<td><span ng-bind="d.level"></span></td>
							<td><span ng-bind="d.location"></span></td>
							<td><span ng-bind="d.driveDate"></span></td>
							<td><span ng-bind="d.reqCount"></span></td>
							<td><span ng-bind="d.cvCount"></span></td>	
							<td><span ><a href ng-click="fnDownLoad(d.jobdescId)">{{d.fileName}}</a></span></td>								
							<td style="text-align:center;"><a id="viewJD2" ng-href="${pageContext.request.contextPath}/interview/compLead/viewRRJd/{{d.jobdescId}}">View/Edit</a></td>		
						</tr>
					
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
							<th>ID#</th>
							<th>NAME</th>
							<th>PHONE</th>
							<th>TECHNOLOGY</th>
							<th>SKILLSET</th>
							<th>INTERVIEW LEVEL</th>
							<th>STATUS</th>
							<th>FEEDBACK</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="feedbacksList.length <= 0">
							<td colspan="8" style="text-align: center;">No Feedback
								Submitted!!</td>
						</tr>
						<!-- <tr ng-repeat="f in feedbacksList"> -->
						<tr dir-paginate="f in feedbacksList|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
							<td><span ng-bind="f.candidateId"></span></td>
							<td><span ng-bind="f.fullName"></span></td>
							<td><span ng-bind="f.contactNumber"></span></td>
							<td><span ng-bind="f.technology"></span></td>
							<td><span ng-bind="f.skillset"></span></td>
							<td><span ng-bind="f.interviewLevelL1"></span></td>
							<td><span ng-bind="f.interviewLevelL1SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL1Feedback"></span></td>
						</tr>
					</tbody>
				</table>
				<div style="float: right;">	
					<dir-pagination-controls
				       	max-size="10"
				       direction-links="true"
				       boundary-links="true" >
				    </dir-pagination-controls>
				    </div>
			<!-- </div> -->
		</div>
		
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			
			

		<div class="panel panel-default" id="candidatesFeedbackDiv">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">L1 Candidate Feedback Form</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit(L1FeedbackForm)" name="L1FeedbackForm"
					class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Date</label>
							&nbsp;&nbsp;&nbsp; <b><span ng-bind="interview_date"></span><b>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Current Interview Level</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL1.interviewLevelL1"
									name="interviewLevelL1" class="form-control input-sm" readonly />

								<div class="has-error" ng-show="L1FeedbackForm.interviewLevelL1.$dirty">
									<span ng-show="L1FeedbackForm.interviewLevelL1.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Candidate Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.candidateFeedbackL1.fullName"
									name="fullName" class="form-control input-sm"
									placeholder="Enter candidate full name" required />

								<div class="has-error" ng-show="L1FeedbackForm.fullName.$dirty">
									<span ng-show="L1FeedbackForm.fullName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L1 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL1.interviewLevelL1PanelName"
									name="interviewLevelL1PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="L1FeedbackForm.interviewLevelL1PanelName.$dirty">
									<span
										ng-show="L1FeedbackForm.interviewLevelL1PanelName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Technology/Product</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.candidateFeedbackL1.technology"
									name="technology" class="form-control input-sm"
									placeholder="Enter technology" required />

								<div class="has-error" ng-show="L1FeedbackForm.technology.$dirty">
									<span ng-show="L1FeedbackForm.technology.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
			
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Feedback</label>
							<div class="col-md-8">
								<!-- <input type="text" style='height: 120px'
									ng-model="ctrl.candidateFeedbackL1.interviewLevelL1Feedback"
									name="interviewLevelL1Feedback" class="form-control input-sm"
									placeholder="Enter L1 candidate feedback" required /> -->
									
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL1Feedback"
									ng-model="ctrl.candidateFeedbackL1.interviewLevelL1Feedback"
									placeholder="Enter L1 candidate feedback" required></textarea>  

								<div class="has-error"
									ng-show="L1FeedbackForm.interviewLevelL1Feedback.$dirty">
									<span ng-show="L1FeedbackForm.interviewLevelL1Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Total
								Years of Experience</label>
							<div class="col-md-4">
								<input type="number"
									ng-model="ctrl.candidateFeedbackL1.experience" name="experience"
									class="form-control input-sm" />
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Relevant Years of
								Experience</label>
							<div class="col-md-8">
								<input type="number"
									ng-model="ctrl.candidateFeedbackL1.relevantExperience"
									name="relevantExperience" class="form-control input-sm" />
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Selection Status</label>
							<div class="col-md-8">
								<select class="form-control" name="interviewLevelL1SelectionStatus"
									ng-options="interviewLevelL1SelectionStatus.value as interviewLevelL1SelectionStatus.value for interviewLevelL1SelectionStatus in IntCandidateStatus"
									ng-model="ctrl.candidateFeedbackL1.interviewLevelL1SelectionStatus"
									ng-select="ctrl.candidateFeedbackL1.interviewLevelL1SelectionStatus" required>
								</select>

								<div class="has-error" ng-show="L1FeedbackForm.interviewLevelL1SelectionStatus.$dirty">
									<span ng-show="L1FeedbackForm.interviewLevelL1SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
							<!-- <div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL1.interviewLevelL1SelectionStatus"
									name="interviewLevelL1SelectionStatus"
									class="form-control input-sm"
									placeholder="Enter L1 selection status" required />

								<div class="has-error"
									ng-show="L1FeedbackForm.interviewLevelL1SelectionStatus.$dirty">
									<span
										ng-show="L1FeedbackForm.interviewLevelL1SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div> -->
						</div>

					</div>

				</form>
				<div class="form-actions floatRight" align="center">
					<input type="button" value="Submit L1 Feedback"
						class="btn btn-primary btn-sm" ng-disabled="L1FeedbackForm.$invalid"
						ng-click="ctrl.submit(L1FeedbackForm)">
					<button type="button" ng-click="ctrl.reset()"
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
		src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.panel.L1dashboard.controller.js' />"></script>
		<script src="<c:url value='/static/js/directives/filemodel.js' />"></script> 
		
		
		
	

</body>
</html>