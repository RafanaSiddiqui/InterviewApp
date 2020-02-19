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
		ng-controller="PanelL2DashboardController as ctrl">

		<form class="row">
			<div class="col-lg-6 col-md-6">
				<label><h2>
						L2 Panel Dashboard
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
							<td><span ><a href ng-click="fnDownLoad(d.jobdescId)">{{d.fileName}}</a></span></td>
							<td style="text-align:center;"><a id="viewJD2" ng-href="${pageContext.request.contextPath}/interview/compLead/viewRRJd/{{d.jobdescId}}">View/Edit</a></td>										
						</tr>
					</tbody>
				</table>
			<!-- </div> -->
		</div>


		<div class="panel panel-default" id="candidatesDiv">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">L1 Interviewed Candidates List</span>
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
							<th>L1 STATUS</th>
							<th>L1 FEEDBACK</th>
							<th>L2 STATUS</th>
							<th>L2 FEEDBACK</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="feedbacksList.length <= 0">
							<td colspan="11" style="text-align: center;">No Feedbacks
								Submitted by L1 !!</td>
						</tr>
						<!-- <tr ng-repeat="f in feedbacksList"> -->
						
						<tr ng-show="f.interviewLevelL1SelectionStatus=='Select'"
						 dir-paginate="f in feedbacksList|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
							<td><input type="radio"
								ng-click="onSelectRadioL2CandidateTable(f)" ng-value="f"
								id="candidateSelected" name="candidateSelected"></td>
							<td><span ng-bind="f.candidateId"></span></td>
							<td><span ng-bind="f.fullName"></span></td>
							<td><span ng-bind="f.contactNumber"></span></td>
							<td><span ng-bind="f.technology"></span></td>
							<td><span ng-bind="f.skillset"></span></td>
							<td><span ng-bind="f.interviewLevelL1"></span></td>
							<td><span ng-bind="f.interviewLevelL1SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL1Feedback"></span></td>
							<td><span ng-bind="f.interviewLevelL2SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL2Feedback"></span></td>
						</tr>
						
							<tr ng-show="f.interviewLevelL1SelectionStatus=='Reject'"
						dir-paginate="f in feedbacksList|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">	
						 <td><input type="hidden"></td>								
							<td><span ng-bind="f.candidateId"></span></td>
							<td><span ng-bind="f.fullName"></span></td>
							<td><span ng-bind="f.contactNumber"></span></td>
							<td><span ng-bind="f.technology"></span></td>
							<td><span ng-bind="f.skillset"></span></td>
							<td><span ng-bind="f.interviewLevelL1"></span></td>
							<td><span ng-bind="f.interviewLevelL1SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL1Feedback"></span></td>	
							<td><span ng-bind="f.interviewLevelL2SelectionStatus"></span></td>
							<td><span ng-bind="f.interviewLevelL2Feedback"></span></td>			
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
				<span class="lead">L2 Candidate Feedback Form</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submitL2FeedbackForm(L2FeedbackForm)"
					name="L2FeedbackForm" class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Date</label>
							<div>
								<b><span ng-bind="interview_date"></span><b>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Last Interview Level</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL1"
									name="interviewLevelL1" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL1.$dirty">
									<span ng-show="L2FeedbackForm.interviewLevelL1.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Candidate Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.candidateFeedbackL2.fullName"
									name="fullName" class="form-control input-sm" readonly />

								<div class="has-error" ng-show="L2FeedbackForm.fullName.$dirty">
									<span ng-show="L2FeedbackForm.fullName.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L1 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL1PanelName"
									name="interviewLevelL1PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL1PanelName.$dirty">
									<span
										ng-show="L2FeedbackForm.interviewLevelL1PanelName.$error.required"
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
									ng-model="ctrl.candidateFeedbackL2.technology"
									name="technology" class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="L2FeedbackForm.technology.$dirty">
									<span ng-show="L2FeedbackForm.technology.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Feedback</label>
							<div class="col-md-8">
								<!-- <input type="text" style='height: 120px'
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL1Feedback"
									name="interviewLevelL1Feedback" class="form-control input-sm"
									readonly /> -->
									
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL1Feedback"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL1Feedback"
									readonly></textarea>

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL1Feedback.$dirty">
									<span
										ng-show="L2FeedbackForm.interviewLevelL1Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Total
								Years of Experience</label>
							<div class="col-md-4">
								<input type="number"
									ng-model="ctrl.candidateFeedbackL2.experience"
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
									ng-model="ctrl.candidateFeedbackL2.relevantExperience"
									name="relevantExperience" class="form-control input-sm"
									readonly />
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L1
								Selection Status</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL1SelectionStatus"
									name="interviewLevelL1SelectionStatus"
									class="form-control input-sm" readonly />

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL1SelectionStatus.$dirty">
									<span
										ng-show="L2FeedbackForm.interviewLevelL1SelectionStatus.$error.required"
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
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL2"
									name="interviewLevelL2" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL2.$dirty">
									<span ng-show="L2FeedbackForm.interviewLevelL2.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">L2 Interviewer Name</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL2PanelName"
									name="interviewLevelL2PanelName" class="form-control input-sm"
									readonly />

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL2PanelName.$dirty">
									<span
										ng-show="L2FeedbackForm.interviewLevelL2PanelName.$error.required"
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
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL2Feedback"
									name="interviewLevelL2Feedback" class="form-control input-sm"
									placeholder="Enter L2 candidate feedback" required /> -->
									
								<textarea class="form-control input-sm" rows="5" id="interviewLevelL2Feedback"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL2Feedback"
									placeholder="Enter L2 candidate feedback" required ng-disabled= "L2FeedbackDone"></textarea>  

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL2Feedback.$dirty">
									<span
										ng-show="L2FeedbackForm.interviewLevelL2Feedback.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">L2
								Selection Status</label>
							<div class="col-md-8">
								<select class="form-control" name="interviewLevelL2SelectionStatus"
									ng-options="interviewLevelL2SelectionStatus.value as interviewLevelL2SelectionStatus.value for interviewLevelL2SelectionStatus in IntCandidateStatus"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL2SelectionStatus"
									ng-select="ctrl.candidateFeedbackL2.interviewLevelL2SelectionStatus"
									ng-disabled= "L2FeedbackDone" required>
								</select>

								<div class="has-error" ng-show="L2FeedbackForm.interviewLevelL2SelectionStatus.$dirty">
									<span ng-show="L2FeedbackForm.interviewLevelL2SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
							<!-- <div class="col-md-8">
								<input type="text"
									ng-model="ctrl.candidateFeedbackL2.interviewLevelL2SelectionStatus"
									name="interviewLevelL2SelectionStatus"
									class="form-control input-sm"
									placeholder="Enter L2 selection status" required />

								<div class="has-error"
									ng-show="L2FeedbackForm.interviewLevelL2SelectionStatus.$dirty">
									<span
										ng-show="L2FeedbackForm.interviewLevelL2SelectionStatus.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div> -->
						</div>

					</div>

				</form>
				<div class="form-actions floatRight" align="center">
					<input type="button" value="Submit L2 Feedback"
						class="btn btn-primary btn-sm" id = "submitL2FeedbackButton"
						ng-disabled="L2FeedbackForm.$invalid"
						ng-click="ctrl.submitL2FeedbackForm(L2FeedbackForm)">
					<button type="button" ng-click="ctrl.resetL2FeedbackForm()"
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
		src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.panel.L2dashboard.controller.js' />"></script>

</body>
</html>