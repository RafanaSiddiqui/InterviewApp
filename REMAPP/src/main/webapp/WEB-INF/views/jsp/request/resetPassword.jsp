<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>IAM Automation App - User </title>

</head>
<body ng-app="myApp" class="ng-cloak">

	<div class="container" ng-controller="UserController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Reset Password </span> <input type="hidden"
					ng-model="ctrl.requestIDforEdit1" value="{{requestIDEdit}}" />

			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form name="myForm"
					class="form-horizontal">

					

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">User Name</label>
							<div class="col-md-4">
								<input type="text" ng-model="ctrl.userInfo.username" name="username"
									class="form-control input-sm" placeholder="Enter UserName" readonly/>

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.frequency.$error.required">This is
										a required field</span> <span ng-show="myForm.frequency.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">New Password</label>
							<div class="col-md-4">
								<input type="password" ng-model="ctrl.userInfo.password" name="password"
									class="form-control input-sm" placeholder="Enter Password" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.frequency.$error.required">This is
										a required field</span> <span ng-show="myForm.frequency.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Confirm Password</label>
							<div class="col-md-4">
								<input type="password" ng-model="confirmPassword" name="confirmPassword"
									class="form-control input-sm" placeholder="Re-Type Password" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.frequency.$error.required">This is
										a required field</span> <span ng-show="myForm.frequency.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

				</form>

				<div class="form-actions floatRight">
					<input type="submit"
						value="{{!ctrl.userInfo.username? 'Add' : 'Update'}}"
						class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
						ng-click="ctrl.resetPassword(myForm)">
					<button type="button" ng-click="ctrl.reset()"
						class="btn btn-warning btn-sm">Cancel</button>
				</div>

			</div>
			
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>

	<script src="<c:url value='/static/js/service/userdetails_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/manageuser_controller.js' />"></script>
<!-- <script src="<c:url value='/static/js/directives/confirm_password.js' />"></script>  -->
</body>


</html>