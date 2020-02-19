<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tracker</title>

</head>
<body ng-app="myApp" class="ng-cloak">
		
	<div class="container" ng-controller="TrackerController as ctrl">
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
								<!-- <select class="form-control" name="accountName" ng-change="onSelectCustName(ctrl.tracker.accountName)"
									ng-options="custDetails.name as custDetails.name for custDetails in customerDetails"
									ng-model="ctrl.tracker.accountName" ng-select="ctrl.tracker.accountName" required>
								</select> -->
								
								<ui-select ng-model="accountBase.selected" theme="bootstrap" style="min-width: 300px;"
						               class="btn-group bootstrap-select"
						               append-to-body="true"
						               search-enabled="true"
						               ng-required="true" ng-change="onSelectCustName(accountBase.selected)">
						      		<ui-select-match placeholder="Select BotName">
						        	{{$select.selected.accountName}}
						      		</ui-select-match>
						      		<ui-select-choices repeat="accountBase in accountBaseList | filter: $select.search">
						        	<span ng-bind-html="accountBase.accountName"></span>
						      		</ui-select-choices>
					    		</ui-select>
								
								<div class="has-error" ng-show="myForm.accountName.$dirty">
								<span ng-show="myForm.accountName.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Competency Manager</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.tracker.compManager"
									name="compManager" class="form-control input-sm" ng-disabled="true"/>
									
  								<div class="has-error" ng-show="myForm.compManager.$dirty">
									<span ng-show="myForm.compManager.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Project Id</label>
							<div class="col-md-4">
								
								<input type="text" ng-model="ctrl.tracker.projectId"
									name="projectId" placeholder="Enter Project Id" class="form-control input-sm"/>
								
								<div class="has-error" ng-show="myForm.projectId.$dirty">
									<span ng-show="myForm.projectId.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
      									<input type="text" ng-model="ctrl.tracker.projectName"
											name="projectName" class="form-control input-sm"
											placeholder="Enter Project Name" required/>
										<div class="has-error" ng-show="myForm.projectName.$dirty">
											<span ng-show="myForm.projectName.$error.required" style="color: red">This is a required field</span>
										</div>
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
				<button type="button" ng-click="addAccountDetails()"
						class="btn btn-success btn-sm">Add Automation Details</button>
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
								<select class="form-control" name="automationCategeory"
									ng-options="category.value as category.value for category in categoryList"
									ng-model="automationDetails.automationCategeory" ng-select="automationDetails.automationCategeory" required>
								</select>
								<div class="has-error" ng-show="myForm.accountName.$dirty">
								<span ng-show="myForm.accountName.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Automation Items</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="automationDetails.automationItems"
									name="automationItems" class="form-control input-sm"/> -->
								<select class="form-control" name="automationItems"
									ng-options="items.value as items.value for items in itemList"
									ng-model="automationDetails.automationItems" ng-select="automationDetails.automationItems" required>
								</select>
  								<div class="has-error" ng-show="myForm.compManager.$dirty">
									<span ng-show="myForm.compManager.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Possibility</label>
							<div class="col-md-8">
								<input type="radio" ng-model="automationDetails.possibility" value="Yes" name="possibility" required>Yes
  								<input type="radio" ng-model="automationDetails.possibility" value="No" name="possibility" required>No
								<div class="has-error" ng-show="myForm.possibility.$dirty">
									<span ng-show="myForm.billability.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
					</div>
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Phase</label>
							<div class="col-md-8">
								<select class="form-control" name="phase"
									ng-options="phase.value as phase.value for phase in phaseList"
									ng-model="automationDetails.phase" ng-select="automationDetails.phase" required>
								</select>
								<div class="has-error" ng-show="myForm.phase.$dirty">
								<span ng-show="myForm.phase.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
					</div>
						
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Status</label>
							<div class="col-md-8">
								<select class="form-control" name="status"
									ng-options="status.value as status.value for status in statusList"
									ng-model="automationDetails.status" ng-select="automationDetails.status" required>
								</select>
  								<div class="has-error" ng-show="myForm.status.$dirty">
									<span ng-show="myForm.status.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
					</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Comments</label>
							<div class="col-md-10">
								<textarea ng-model="automationDetails.comments"
									name="comments" class="username form-control input-sm" required style="height: 200px;"></textarea>	
								<div class="has-error" ng-show="myForm.comments.$dirty">
									<span ng-show="myForm.comments.$error.required" style="color: red">This is a required field</span>
								</div>
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
												<th width="20%">

												<span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
													ng-click="addTarget($index)"></span>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="target in automationDetails.automationTarget">
												<td>
												<!-- <input type="text" ng-model="target.year" name="year" placeholder="Year" style="width: 100px"> -->
												<select class="form-control" name="year"
													ng-options="year.value as year.value for year in yearList"
													ng-model="target.year" ng-select="target.year" style="width: 100px">
												</select>
												</td>
												<td><input type="text" ng-model="target.q1" name="q1" placeholder="Q1" style="width: 100px"></td>
												<td><input type="text" ng-model="target.q2" name="q2" placeholder="Q2" style="width: 100px"></td>
												<td><input type="text" ng-model="target.q3" name="q3" placeholder="Q3" style="width: 100px"></td>
												<td><input type="text" ng-model="target.q4" name="q4" placeholder="Q4" style="width: 100px"></td>
												
												<td width="20%">

												<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 					ng-click="deleteTarget($parent.$index)"  ng-show="$last"></span>
												</td>
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
												<th width="20%">

												<span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
													ng-click="addActual($index)"></span>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="actual in automationDetails.automationActual">
												<td>
												<select class="form-control" name="year"
													ng-options="year.value as year.value for year in yearList"
													ng-model="actual.year" ng-select="actual.year" style="width: 100px">
												</select>
												</td>
												<td><input type="text" ng-model="actual.q1" name="q1" placeholder="Q1" style="width: 100px"></td>
												<td><input type="text" ng-model="actual.q2" name="q2" placeholder="Q2" style="width: 100px"></td>
												<td><input type="text" ng-model="actual.q3" name="q3" placeholder="Q3" style="width: 100px"></td>
												<td><input type="text" ng-model="actual.q4" name="q4" placeholder="Q4" style="width: 100px"></td>
												
												<td width="20%">

												<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 					ng-click="deleteActual($parent.$index)"  ng-show="$last"></span>
												</td>
											</tr>

										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12" align="right">
					<div class="col-md-12">
						<button type="button" ng-click="deleteAccountDetails()"
							class="btn btn-danger btn-sm">Delete</button>
					</div>
					</div>
					</div>
					</uib-accordion>
					</div>
					</div>
					<div class="form-actions floatRight">
						<input type="button"
							value="Add"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
							ng-click="ctrl.submit(myForm)">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm">Cancel</button>
					</div>
					</div>
					
					<!-- <div class="col-md-2">
						<button type="button" ng-click="addAccountDetails()"
						class="btn btn-success btn-sm">Add Account Details</button>
					</div> -->
					<!-- <div class="col-md-2" style="padding-top: 150px;">
						<button type="button" ng-click="ctrl.reset()"
						class="btn btn-danger btn-sm">Delete</button>
					</div> -->
					<!-- </div>
					</div>
			
			</div>
			</div>
			
					
				
			</div> -->
		<!-- </div> -->
		<jsp:include page="../fragments/footer.jsp" />
		
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/tracker.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/tracker.controller.js' />"></script>
	<script src="<c:url value='/static/js/service/account.base.service.js' />"></script>
</body>


</html>