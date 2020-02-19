<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>IAM Automation App - User </title>

</head>
<body ng-app="myApp" class="ng-cloak">

	<div class="container" ng-controller="UserController as ctrl"
		ng-init="loadUser('${userId}')">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Edit Request </span> <input type="hidden"
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
							<label class="col-md-2 control-lable" for="file">First Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.userInfo.firstName"
									name="firstName" class="username form-control input-sm"
									placeholder="Enter First name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.firstName.$error.required">This
										is a required field</span> <span
										ng-show="myForm.firstName.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.apiName.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Last
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.userInfo.lastName" name="lastName"
									class="form-control input-sm"
									placeholder="Enter Last Name" />

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
							<label class="col-md-2 control-lable" for="file">User Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.userInfo.username" name="username"
									class="form-control input-sm" placeholder="Enter User name" />

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
							<label class="col-md-2 control-lable" for="file">Email Id</label>
							<div class="col-md-7">
								<input type="email" ng-model="ctrl.userInfo.mailId" name="mailId"
									class="form-control input-sm" placeholder="Enter Email Id" />

								<div class="has-error" ng-show="myForm.mailId.$dirty">
									<span ng-show="myForm.mailId.$error.required">This is
										a required field</span> <span ng-show="myForm.mailId.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>



					<div class="row">

						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="exampleSelect1">Role</label>

							<div class="col-md-7">

					<!--  			<select class="form-control" name="role"
									ng-options="template.templateName for template in ctrl.templateList"
									ng-model="selectedTemplate" ng-change="ctrl.selectAction()"> 


								</select> -->
								<!-- <br />
								<br /> <span>API Name: {{selectedTemplate.templateName}}, Template ID:
									{{selectedTemplate.templateID}}</span> -->
									
									<!-- <select class="form-control" name="role" ng-model="ctrl.userInfo.role">
									<option>ROLE_USER</option>
									<option>ROLE_ADMIN</option>
									</select> -->
								<!-- <select class="form-control" name="status"
									ng-options="roles for roles in rolesList"
									ng-model="ctrl.userInfo.role" ng-select="ctrl.userInfo.role">
								</select> -->
								
								<label ng-repeat="role in rolesList" class="checkcontainer">
								  <input type="checkbox" checklist-model="ctrl.userRolesList" checklist-value="role"> {{role}}
								  <span class="checkmark"></span>
								</label>
								
							</div>
						</div>
					</div>
					
					
					<!-- <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Approver Type</label>
                              <div class="col-md-7">
                               <input type="text" ng-model="ctrl.userInfo.approverType" name="approverType" class="form-control input-sm" placeholder="Enter Approver Type"/>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.approverType.$error.required">This is a required field</span>
                                      <span ng-show="myForm.approverType.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div> -->

				</form>

				<div class="form-actions floatRight">
					<input type="submit"
						value="{{!ctrl.userInfo.userId ? 'Add' : 'Update'}}"
						class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
						ng-click="ctrl.submit(myForm)">
					<button type="button" ng-click="ctrl.reset()"
						class="btn btn-warning btn-sm">Cancel</button>
				</div>

			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>

	<script src="<c:url value='/static/js/service/userdetails_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/manageuser_controller.js' />"></script>
</body>


</html>