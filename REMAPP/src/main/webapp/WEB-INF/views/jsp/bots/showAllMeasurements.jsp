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
<title>ERSS Resource Manager</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="BotMeasurementController as ctrl">
		
    	<form class="row">
        <div class="col-lg-6 col-md-6">
            <label >Search</label>
            <input type="text" ng-model="search" class="form-control" placeholder="Search By Any Column">
        </div>
        
    	</form>
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">All Bot Measurements</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Sl NO#
							</th>
							<!-- <th>Select for Clone</th> -->
							<th ng-click="sort('botId')">BOT Identifier
								<span class="glyphicon sort-icon" ng-show="sortKey=='botId'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('botName')">BOT Name
								<span class="glyphicon sort-icon" ng-show="sortKey=='botName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('botCategory')">Created Month
								<span class="glyphicon sort-icon" ng-show="sortKey=='botCategory'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('projectName')">Manual effort before BOT (Person Hours)
								<span class="glyphicon sort-icon" ng-show="sortKey=='projectName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('createdDate')">Manual effort after BOT (Person Hours)
								<span class="glyphicon sort-icon" ng-show="sortKey=='creationDate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('createdDate')">No of Executions
								<span class="glyphicon sort-icon" ng-show="sortKey=='creationDate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('createdDate')">No Of Failures
								<span class="glyphicon sort-icon" ng-show="sortKey=='creationDate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('createdDate')">Total hours saved
								<span class="glyphicon sort-icon" ng-show="sortKey=='creationDate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th width="10%">
							<!-- <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color: green" ng-click="ctrl.createNewBotMeasurement()"></span> -->
							<button type="submit" ng-click="ctrl.createNewBotMeasurement()" class="btn btn-success">
					          <span>Add</span>
					        </button>
							</th>
							<!-- <th width="10%">
							<span aria-hidden="true" style="color: green" ng-click="cloneBotMeasurement()">Clone</span>
							<button type="submit" ng-click="cloneBotMeasurement()" class="btn btn-success">
					          <span>Clone</span>
					        </button>
							</th> -->
						</tr>
					</thead>
					<tbody>
						<!-- <tr ng-if="botMeasurementClone != null" style="background-color: #b7b4b4">
							<td><span ng-bind="{{$index}}"></span></td>
							<td>.</td>
							<td><span ng-bind="botMeasurementClone.botId"></span></td>
							<td><span ng-bind="botMeasurementClone.botName"></span></td>
							<td>
							<input type="month" ng-model="botMeasurementClone.createdMonth"
									name="createdDate" class="form-control input-sm"/>
							<span editable-month="d.createdMonth" e-name="noOfExecutions" e-form="rowform" e-required>
									{{d.createdMonth | date : "MMM yyyy"}}
								</span>
							</td>
							<td><span ng-bind="botMeasurementClone.manualEffortBeforeBot"></span></td>
							<td><span ng-bind="botMeasurementClone.manualEffortAfterBot"></span></td>
							<td>
							<input type="text" ng-model="botMeasurementClone.noOfExecutions"
									name="createdDate" class="form-control input-sm" ng-change="onChangeExecClone()"/>
							</td>
							<td>
							<input type="text" ng-model="botMeasurementClone.noOfFailures"
									name="createdDate" class="form-control input-sm" ng-change="onChangeExecClone()"/>
							</td>
							<td><span ng-bind="botMeasurementClone.totalHrsSaved"></span></td>
							<td>
							<button type="submit" ng-click="ctrl.clone()" class="btn btn-success">
					          <span>Clone</span>
					        </button>
							</td>
							
						</tr> -->
						<tr ng-show="pageableBotMeasurements.length <= 0"><td colspan="5" style="text-align:center;">No Measurements Created!!</td></tr>
					
						<!-- <tr dir-paginate="d in pageableBotMeasurements|itemsPerPage:pageableBotMeasurements.size" total-items="pageableBotMeasurements.totalElements"> -->
						<tr dir-paginate="d in pageableBotMeasurements|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
							<td><span ng-bind="{{$index + 1}}"></span></td>
							<!-- <td><input type="radio" ng-model="$parent.selectedObj" ng-click="onSelectRadio(d)" ng-value="d"></td> -->
							<td><span ng-bind="d.botId"></span></td>
							<td><span ng-bind="d.botName"></span></td>
							<td><span ng-bind="d.createdMonth"></span></td>
							<td><span ng-bind="d.manualEffortBeforeBot"></span></td>
							<td><span ng-bind="d.manualEffortAfterBot"></span></td>
							<td><span ng-bind="d.noOfExecutions"></span></td>
							<td><span ng-bind="d.noOfFailures"></span></td>
							<td><span ng-bind="d.totalHrsSaved"></span></td>
							<td>
							<span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.editBotMeasurement(d.id)"></span>
							<span style="display: inline-block; width: 5%;"></span>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.deleteBotMeasurement(d.id)" ng-show="checkRole('ROLE_ADMIN')">
							</span>
							<span style="display: inline-block; width: 5%;"></span>
							<span  class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="color: block" ng-click="ctrl.openBotMeasurement(d.id)">
							</span>
							<span style="display: inline-block; width: 5%;"></span>
							<span aria-hidden="true"
								style="color: green" ng-click="cloneBotMeasurement(d)"><i class="glyphicon glyphicon-duplicate"></i>
							</span>
							</td>
						</tr>
					</tbody>
				</table>
				<div style="float: right;">
				<!-- <dir-pagination-controls
					max-size="10"
					direction-links="true"
					boundary-links="true" 
					on-page-change="ctrl.loadAllDemandPageableRequests(newPageNumber, pageableBotMeasurements.size)">
				</dir-pagination-controls> -->
				
				<dir-pagination-controls
			       	max-size="10"
			       direction-links="true"
			       boundary-links="true" >
			    </dir-pagination-controls>
				
				</div>
			</div>
		</div>

	</div>
	
	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/bot_measurement_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_measurement_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>