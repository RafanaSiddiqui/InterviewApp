<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>ERSS Resource Manager</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="MasterDemandController as ctrl">
		
		 <!-- <form class="form-inline" ng-submit="searchBySoId(soId)">
        	<div class="form-group">
            	<input type="text" ng-model="soId" class="form-control" placeholder="Search By SOID">
            	<input type="button"
							value="Search"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
							ng-click="searchBySoId(soId)">
        	</div>
    	</form> -->
    	
    	<form class="row">
        <div class="col-lg-6 col-md-6">
            <label >Search</label>
            <input type="text" ng-model="search" class="form-control" placeholder="Search By Any Column">
        </div>
        
        <div class="col-lg-6 col-md-6">
        <div class="pull-right" ng-show="userRole!='ROLE_DELIVERY_LEAD'">
            <button type="button" ng-click="fnDownLoad()"
				class="btn btn-primary btn-sm" ng-disabled="myForm.$pristine">Download File
				<span  class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
			</button>
		</div>
        </div>
        
    	</form>
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Master Demand Request </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead>
						<tr>
							<th ng-click="sort('demandRequestId')">Sl NO#
								 <span class="glyphicon sort-icon" ng-show="sortKey=='demandRequestId'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('soId')">SO ID
								<span class="glyphicon sort-icon" ng-show="sortKey=='soId'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('priority')">Priority
								<span class="glyphicon sort-icon" ng-show="sortKey=='priority'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('accountName')">Account Name
								<span class="glyphicon sort-icon" ng-show="sortKey=='accountName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('projectName')">Project Name
								<span class="glyphicon sort-icon" ng-show="sortKey=='projectName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('requestorID')">Requestor ID
								<span class="glyphicon sort-icon" ng-show="sortKey=='requestorID'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('creationDate')">Creation Date
								<span class="glyphicon sort-icon" ng-show="sortKey=='creationDate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('status')">Status
								<span class="glyphicon sort-icon" ng-show="sortKey=='status'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
		

							<th width="10%"><span class="glyphicon glyphicon-plus"
								aria-hidden="true" style="color: green" ng-click="ctrl.createNewMasterDemand()" ng-show="userRole!='ROLE_TSC' && userRole!='ROLE_TAG'"></span>
							</th>
						</tr>
					</thead>
					<tbody>
					
						<tr ng-show="pageableDemandRequest.length <= 0"><td colspan="5" style="text-align:center;">No Requests Created!!</td></tr>
					
						<!-- <tr dir-paginate="d in pageableDemandRequest|itemsPerPage:pageableDemandRequest.size" total-items="pageableDemandRequest.totalElements"> -->
						<tr dir-paginate="d in pageableDemandRequest|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
							<td><span ng-bind="{{$index + 1}}"></span></td>
							<td><span ng-bind="d.soId"></span></td>
							<td><span ng-bind="d.priority"></span></td>
							<td><span ng-bind="d.accountName"></span></td>
							<td><span ng-bind="d.projectName"></span></td>
							<td><span ng-bind="d.requestorID"></span></td>
							<td><span ng-bind="d.creationDate"></span></td>
							<td><span ng-bind="d.status"></span></td>
							<td>
							<span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.editDemandRequest(d.demandRequestId)"></span>
							<span style="display: inline-block; width: 5%;"></span>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.removeDemandRequest(d.demandRequestId)" ng-show="checkRole('ROLE_ADMIN')">
							</span>
							<span  class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="color: block" ng-click="ctrl.openDemandRequest(d.demandRequestId)">
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
					on-page-change="ctrl.loadAllDemandPageableRequests(newPageNumber, pageableDemandRequest.size)">
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
	<script src="<c:url value='/static/js/controller/masterdemand_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>