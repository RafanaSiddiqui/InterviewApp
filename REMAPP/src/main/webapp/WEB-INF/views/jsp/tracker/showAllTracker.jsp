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
		ng-controller="TrackerController as ctrl">
		
    	<form class="row">
        <div class="col-lg-6 col-md-6">
            <label >Search</label>
            <input type="text" ng-model="search" class="form-control" placeholder="Search By Any Column">
        </div>
        
    	</form>
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">All Tracker</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Sl NO#
							</th>
							<!-- <th ng-click="sort('id')">Identifier
								<span class="glyphicon sort-icon" ng-show="sortKey=='id'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th> -->
							<th ng-click="sort('accountName')">Account Name
								<span class="glyphicon sort-icon" ng-show="sortKey=='accountName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('compManager')">Competency Manager
								<span class="glyphicon sort-icon" ng-show="sortKey=='compManager'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('projectId')">Project Id
								<span class="glyphicon sort-icon" ng-show="sortKey=='projectId'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('projectName')">Project Name
								<span class="glyphicon sort-icon" ng-show="sortKey=='projectName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('createdDate')">Created Date
								<span class="glyphicon sort-icon" ng-show="sortKey=='creationDate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th width="10%"><span class="glyphicon glyphicon-plus"
								aria-hidden="true" style="color: green" ng-click="ctrl.createNewTracker()"></span>
							</th>
						</tr>
					</thead>
					<tbody>
					
						<tr ng-show="pageableTracker.length <= 0"><td colspan="5" style="text-align:center;">No Trackers Created!!</td></tr>
					
						<!-- <tr dir-paginate="d in pageableBotMeasurements|itemsPerPage:pageableBotMeasurements.size" total-items="pageableBotMeasurements.totalElements"> -->
						<tr dir-paginate="d in pageableTracker|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
							<td><span ng-bind="{{$index + 1}}"></span></td>
							<!-- <td><span ng-bind="d.id"></span></td> -->
							<td><span ng-bind="d.accountName"></span></td>
							<td><span ng-bind="d.compManager"></span></td>
							<td><span ng-bind="d.projectId"></span></td>
							<td><span ng-bind="d.projectName"></span></td>
							<td><span ng-bind="d.createdDate"></span></td>
							<td>
							<span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.editTracker(d.trackerId)"></span>
							<span style="display: inline-block; width: 5%;"></span>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.deleteTracker(d.trackerId)" ng-show="checkRole('ROLE_ADMIN')">
							</span>
							<span  class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="color: block" ng-click="ctrl.openTracker(d.trackerId)">
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
	
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/tracker.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/tracker.controller.js' />"></script>
	<script src="<c:url value='/static/js/service/account.base.service.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>