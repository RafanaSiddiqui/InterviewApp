<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>Find User</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="UserController as ctrl">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> User Details </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID #</th>
							<th>Login User Id</th>
							<th>Last Name</th>
							<th>First Name</th>
							<th>Role</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.userInfoList">
							<td><span ng-bind="u.userId"></span></td>
							<td><span ng-bind="u.username"></span></td>
							<td><span ng-bind="u.lastName"></span></td>
							<td><span ng-bind="u.firstName"></span></td>
							<td>
							<ul>
						        <li ng-repeat="role in u.userRolesList">{{role.roleName}}</li>
						    </ul>
							<!-- <span ng-bind="u.role"> -->
							</td>

						<td><span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.edit(u.userId)"></span> <span
								style="display: inline-block; width: 5%;"></span> <span
								class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.remove(u.userId)"></span></td> 
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<script src="<c:url value='/static/js/service/userdetails_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/manageuser_controller.js' />"></script>
</body>

<jsp:include page="../fragments/footer.jsp" />
</html>