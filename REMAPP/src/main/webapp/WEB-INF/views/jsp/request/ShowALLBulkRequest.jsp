<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>IAM Automation Bulk Application Onboard - Request</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="BulkRequestController as ctrl">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Bulk Application Onboard Request </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Sl NO#</th>
							<th>Bulk Request Name</th>
							<th>File Name</th>
							<th>Status</th>
							<th>Created Date</th>
							<!-- <th>Updated Date</th> -->


							<th width="10%"><span class="glyphicon glyphicon-plus"
								aria-hidden="true" style="color: green" ng-click="ctrl.createNewRequest()"></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.bulkRequestList">
							<td><span ng-bind="{{$index + 1}}"></span></td>
							<td><span ng-bind="u.name"></span></td>
							<td><span><a href ng-click="fnDownLoad(u.bulkRequestID)">{{ u.fileName }}</a></span></td>
							<td><span ng-bind="u.status"></span></td>
							<td><span ng-bind="u.createdDate"></span></td>
							<!-- <td><span ng-bind="u.updateDate"></span></td> -->
							<td>
							<!-- <span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.edit(u.bulkRequestID)"></span> -->
							<span style="display: inline-block; width: 5%;"></span>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.remove(u.bulkRequestID)">
							</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<script src="<c:url value='/static/js/service/bulkrequest_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bulkrequest_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>