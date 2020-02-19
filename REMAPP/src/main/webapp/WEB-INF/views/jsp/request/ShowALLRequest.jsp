<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>IAM Automation App - Request</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="RequestController as ctrl">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Application Onboard Request </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID #</th>
							<th>Application Name</th>
							<th>Application Type</th>
							<th>Bulk Request Id #</th>
							<th>Template Name</th>
							<th>Status</th>
							<th>Created Date</th>
							<th>Updated Date</th>


							<th width="10%"><span class="glyphicon glyphicon-plus"
								aria-hidden="true" style="color: green" ng-click="ctrl.createNewRequest()"></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.appRequestList">
							<td><span ng-bind="u.requestID"></span></td>
							<td><span ng-bind="u.applicationName"></span></td>
							<td><span ng-bind="u.applicationType"></span></td>
							<td><span ng-bind="u.bulkAppRequest.bulkRequestID"></span></td>
							<td><span ng-bind="u.template.templateName"></span></td>
							<td><span ng-bind="u.status"></span></td>
							<td><span ng-bind="u.createdDate"></span></td>
							<td><span ng-bind="u.updateDate"></span></td>
							<td>
							<span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.edit(u.requestID)"></span>
							<span style="display: inline-block; width: 5%;"></span>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.remove(u.requestID)">
							</span>
							<span ng-show="u.status=='Reviewed'" class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="color: block" ng-click="ctrl.openOnBoardRequest(u.requestID)">
							</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>



	<script src="<c:url value='/static/lib/angular.js' />"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/request_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/request_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>