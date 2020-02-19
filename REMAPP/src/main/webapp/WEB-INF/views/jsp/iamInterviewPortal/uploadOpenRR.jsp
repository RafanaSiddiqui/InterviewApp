<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />
<head>
<title>Upload Open RR</title>
<script src="<c:url value='/static/js/directives/filemodel.js' />"></script>
<script src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.uploadrr.controller.js'/>"></script>
<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.openrr.service.js'/>"></script>
<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.job.service.js' />"></script>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="OpenRRController as ctrl">
			<div class="panel panel-default">
				<div class="panel-heading" style="background-color: #efe6f2">
					<span style="font-size: 18px">Interviews Scheduled:</span>
				</div>
				<div>
					<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
				</div>
				<div class="formcontainer">
						<div class="tablecontainer">
							<table class="table table-striped">
								<thead>
									<tr>
										<th style="text-align: center;">#</th>
										<th style="text-align: center;">JOB_ID</th>
										<th style="text-align: center;">SKILL</th>
										<th style="text-align: center;">SKILL_OWNER</th>
										<th style="text-align: center;">LEVEL</th>
										<th style="text-align: center;">JOB LOCATION</th>
										<th style="text-align: center;">DRIVE LOCATION</th>
										<th style="text-align: center;">DRIVE DATE</th>
										<th style="text-align: center;">REQUIREMENT COUNT</th>
										<th style="text-align: center;">NO OF CANDIDATE</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-show="scheduledInterviews.length <= 0">
										<td colspan="10" style="text-align: center;">No Open RR!!</td>
									</tr>
									<tr
										dir-paginate="d in scheduledInterviews|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
										<td style="text-align: center;"><input type="radio"
											ng-model="$parent.selectedObj" ng-click="onSelectRadio(d.jobdescId)"
											ng-value="d"></td>
										<td style="text-align: center;"><span
											ng-bind="d.jobdescId"></span></td>
										<td style="text-align: center;"><span ng-bind="d.skill"></span></td>
										<td style="text-align: center;"><span
											ng-bind="d.skillOwner"></span></td>
										<td style="text-align: center;"><span ng-bind="d.level"></span></td>
										<td style="text-align: center;"><span
											ng-bind="d.jobLocation"></span></td>
										<td style="text-align: center;"><span
											ng-bind="d.location"></span></td>
										<td style="text-align: center;"><span
											ng-bind="d.driveDate"></span></td>
										<td style="text-align: center;"><span
											ng-bind="d.reqCount"></span></td>
										<td style="text-align: center;"><span
											ng-bind="d.footFall"></span></td>
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
						</div>
				</div>
			</div>
	
	
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			

		<div class="panel panel-default" ng-show="showPanel">
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Upload Open RR </span>
			</div>
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="formcontainer" style=" padding-left:0px">
				<form ng-submit="ctrl.upload()" name="myForm"
					class="form-horizontal">
					 <div ng-if="errorMessage" style="color:red"><b>{{errorMessage}}</b></div>
				   <div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>
						<label class="control-label col-sm-4 col-xs-12" for="file">Please
							upload the file : (*.xlsx File)<span class="required"  style="color:red">*</span>
						</label>
						<div class="col-xs-4 input-max controls ">
							<input id="fileSelector" class="inline-block" type="file" file-model="myFile" />
						</div>
						<div class="col-xs-4 input-max controls ">
							<input type="submit" value="Upload"	class="btn btn-primary btn-sm" />
						</div>
						<div>
								<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
						</div>
							<table class="table table-striped">
								<thead>
									<tr>
										<th style="text-align:center;">RR ID</th>
										<th style="text-align:center;">JD ID</th>
										<th style="text-align:center;">ACCOUNT NAME</th>
										<th style="text-align:center;">GAP</th>
										<th style="text-align:center;">AGEING DAYS</th>
										<th style="text-align:center;">LOCATION</th>
										<th style="text-align:center;">VERTICAL GROUP</th>
										<th style="text-align:center;">SKILL</th>
										<th style="text-align:center;">LEVEL</th>
										<th style="text-align:center;">DEMAND</th>
										<th style="text-align:center;">FITMENT</th>
										<th style="text-align:center;">HIRING MANAGER</th>
										<th style="text-align:center;">RECRUITER</th>
										<th style="text-align:center;">STATUS</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-show="pageableOpenRRPanel.length <= 0">
										<td colspan="14" style="text-align: center;">No Data Available!!</td>
									</tr>
									<tr ng-repeat="d in pageableOpenRRPanel">
										<td style="text-align:center;"><span ng-bind="d.rrId"></span></td>
										<td style="text-align:center;"><span ng-bind="d.jobdescId"></span></td>
										<td style="text-align:center;"><span ng-bind="d.accountName"></span></td>
										<td style="text-align:center;"><span ng-bind="d.gap"></span></td>
										<td style="text-align:center;"><span ng-bind="d.ageingDays"></span></td>
										<td style="text-align:center;"><span ng-bind="d.location"></span></td>
										<td style="text-align:center;"><span ng-bind="d.verticalGrp"></span></td>
										<td style="text-align:center;"><span ng-bind="d.skill"></span></td>
										<td style="text-align:center;"><span ng-bind="d.level"></span></td>
										<td style="text-align:center;"><span ng-bind="d.demand"></span></td>
										<td style="text-align:center;"><span ng-bind="d.fitment"></span></td>
										<td style="text-align:center;"><span ng-bind="d.hiringManager"></span></td>
										<td style="text-align:center;"><span ng-bind="d.recruiter"></span></td>
										<td style="text-align:center;"><span ng-bind="d.status"></span></td>
										<td>
											<span class="glyphicon glyphicon-remove" aria-hidden="true"
												style="color: red" ng-click="ctrl.deleteRR(d.primaryID)">
											</span>
										</td>
									</tr>
								</tbody>
							</table>
				</form>
			</div>
		</div>

		
	</div>

</body>
<jsp:include page="../fragments/footer.jsp" />
</html>