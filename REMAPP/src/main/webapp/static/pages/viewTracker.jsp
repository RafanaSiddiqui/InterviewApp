<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tracker</title>

</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="TrackerController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Customer Details </span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="panel-body">
			<!-- <div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal"> -->
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Account Name</label>
							<div class="col-md-8">
								{{ctrl.tracker.accountName}}
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Competency Manager</label>
							<div class="col-md-8">
								{{ctrl.tracker.compManager}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Project Id</label>
							<div class="col-md-4">
								{{ctrl.tracker.projectId}}
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
      						    {{ctrl.tracker.projectName}}
							</div>
						</div>
						
					</div>
					</div>
					</div>
					
			<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Automation Details </span>
				<span style="padding-left: 50%">
				<button type="button" ng-click="openAllPanels()"
						class="btn btn-success btn-sm">Open All</button>
				<button type="button" ng-click="closeAllPanels()"
						class="btn btn-success btn-sm">Collapse All</button>
			    </span>
			    <!-- <span style="padding-left: 60%"><button type="button" ng-click="openAllPanels()"
						class="btn btn-success btn-sm">Open All</button></span> -->
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="panel-body">
					<uib-accordion close-others="false" class="col-md-10">
					<div uib-accordion-group class="panel-default" ng-repeat="automationDetails in ctrl.tracker.automationDetails" is-open="automationDetails.openPanel">
					<div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>
				    <uib-accordion-heading>
        				{{automationDetails.automationCategeory}} - {{automationDetails.automationItems}}<i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': automationDetails.openPanel, 'glyphicon-chevron-right': !automationDetails.openPanel}"></i>
      				</uib-accordion-heading>
					<div class="row col-md-12">
					<div class="form-group col-md-6">
						<label class="col-md-4 control-lable">Automation Categeory</label>
						<div class="col-md-8">
							{{automationDetails.automationCategeory}}
						</div>
					</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Automation Items</label>
							<div class="col-md-8">
								{{automationDetails.automationItems}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Possibility</label>
							<div class="col-md-8">
								{{automationDetails.possibility}}
							</div>
					</div>
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Phase</label>
							<div class="col-md-8">
								{{automationDetails.phase}}
							</div>
							
					</div>
						
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Status</label>
							<div class="col-md-8">
								{{automationDetails.status}}
							</div>
					</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Comments</label>
							<div class="col-md-10">
								{{automationDetails.comments}}
							</div>
						</div>
					
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Automation Target</label>
							<div class="col-md-7">

								<div class="tablecontainer">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Year</th>
												<th>Q1</th>
												<th>Q2</th>
												<th>Q3</th>
												<th>Q4</th>
												<th width="20%"></th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="target in automationDetails.automationTarget">
												<td>{{target.year}}</td>
												<td>{{target.q1}}</td>
												<td>{{target.q2}}</td>
												<td>{{target.q3}}</td>
												<td>{{target.q4}}</td>
												<td width="20%"></td>
											</tr>

										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Automation Actual</label>
							<div class="col-md-7">

								<div class="tablecontainer">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Year</th>
												<th>Q1</th>
												<th>Q2</th>
												<th>Q3</th>
												<th>Q4</th>
												<th width="20%"></th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="actual in automationDetails.automationActual">
												<td>{{actual.year}}</td>
												<td>{{actual.q1}}</td>
												<td>{{actual.q2}}</td>
												<td>{{actual.q3}}</td>
												<td>{{actual.q4}}</td>
												<td width="20%"></td>
											</tr>

										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12" align="right">
					</div>
					</div>
					</uib-accordion>
					</div>
					</div>
					<div class="form-actions floatRight">
						<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm">OK</button>
					</div>
					
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/tracker.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/tracker.controller.js' />"></script>
	<script src="<c:url value='/static/js/service/account.base.service.js' />"></script>
</body>


</html>