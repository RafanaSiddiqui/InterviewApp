<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>


<script src="<c:url value='/static/lib/angular.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<jsp:include page="../fragments/header.jsp" />
<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>
<script src="<c:url value='/static/js/directives/filemodel.js' />"></script>

<script
	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.compteam.service.js' />"></script>
<script
	src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.compteam.controller.js' />"></script>
<head>
<title>Panel Details</title>

</head>

<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="InterviewAdminController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Top Panelist</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>


			<div class="formcontainer">
				<div class="form-group">
					<label class="control-label col-sm-4 col-xs-12" >Top Interview Panel : 
					</label> <br>
					</div>
					<div align="center">
					<input  type="radio" ng-click="ctrl.getTopPanel(7)" value="7" ng-model="toppanel"> Weekly &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input  type="radio" ng-click="ctrl.getTopPanel(15)" value="15" ng-model="toppanel"> Fortnightly &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input  type="radio" ng-click="ctrl.getTopPanel(30)" value="30" ng-checked="true" ng-model="toppanel"> Monthly &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input  type="radio" ng-click="ctrl.getTopPanel(90)" value="90" ng-model="toppanel" > Quarterly &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input  type="radio" ng-click="ctrl.getTopPanel(180)" value="180" ng-model="toppanel"> Half Yearly &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input  type="radio" ng-click="ctrl.getTopPanel(365)" value="365" ng-model="toppanel"> Yearly
					</div>
					<br>
				
					<div ng-if="allPanel.length">
						<table class="table table-striped">
							<thead>
								<tr>
									<!-- 	<th> <span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span> </th>  -->

									<th>Emp ID</th>
									<th>Name</th>
									<th>Account</th>
									<th>Designation</th>
									<th>Technology</th>
									<th>Product</th>
									<th>Status</th>

									<th>Number of days interview conducted</th>

								</tr>
							</thead>
							<tbody>
								<!-- <tr dir-paginate="d in pageableAdminPanelAll|itemsPerPage:pageableAdminPanelAll.size" total-items="pageableAdminPanelAll.totalElements"> -->
								<tr ng-repeat="d in allPanel">
									<!-- 	<td><input type="radio" name="panelSelect"  value="{{d.empId}}" ng-model="ctrl.select"/></td>  -->
									<td><span ng-bind="d.empId"></span></td>
									<td><span ng-bind="d.name"></span></td>
									<td><span ng-bind="d.account"></span></td>
									<td><span ng-bind="d.designation"></span></td>
									<td><span ng-bind="d.technology"></span></td>
									<td><span ng-bind="d.product"></span></td>
									<td><span ng-bind="d.status"></span></td>
									<td><span ng-bind="d.interviewCount"></span></td>
									
									</span>
									</td>

								</tr>
							</tbody>
						</table>

					</div>
				
			</div>
		</div>

		<jsp:include page="../fragments/footer.jsp" />
	</div>


</body>

</html>