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
<title>IAM Automation Job</title>
<style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="JobController as ctrl">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">IAM Job  </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Job rName</th>
							<th>Description</th>
							<th>API EndPoint</th>
							<!-- <th >Job Class</th> -->

								<th width="10%">
								 <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
								ng-click="ctrl.addJob()"></span>
							</th>

						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.jobDetailList">
							<td><span ng-bind="u.jobName"></span></td>
							<td><span ng-bind="u.jobDescription"></span></td>
							<td><span ng-bind="u.api"></span></td>
							<!-- <td><span ng-bind="u.jobClass"></span></td> -->

							<td>

							<span class="glyphicon glyphicon-pencil" aria-hidden="true" style="color:blue"
								ng-click="ctrl.edit(u.jobName)"></span>
								<span style="display:inline-block; width: 5%;"></span>

								<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 ng-click="ctrl.remove(u.jobName)"></span>

							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default" ng-show="showme">
			<div class="panel-heading">
				<span class="lead">IAM Job </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.jobDetail.jobKey" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Job Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.jobDetail.jobName"
									name="jobName" class="username form-control input-sm"
									placeholder="Enter Scheduler name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.jobName.$error.required">This is a
										required field</span> <span ng-show="myForm.jobName.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.jobName.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Description</label>
							<div class="col-md-7">

							  <textarea class="form-control" rows="5" id="comment" ng-model="ctrl.jobDetail.jobDescription" placeholder="Enter Description. [This field is validation free]"></textarea>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Job
								Class</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.jobDetail.jobClass"
									class="form-control input-sm" placeholder="Job Class" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.jobClass.$error.required">This is
										a required field</span> <span ng-show="myForm.jobClass.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">API</label>
							<div class="col-md-7">

								<select class="form-control" name="apiRequestList"
									ng-options="apiRequest.id as apiRequest.apiName for apiRequest in ctrl.apiRequestList"
									ng-model="ctrl.jobDetail.apiRequestID" ng-select="ctrl.jobDetail.apiRequestID">
									<option value="">--Select API--</option>
	
								</select> 
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.jobDetail.jobKey ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
								ng-click="showme=false">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Cancel</button>
						</div>
					</div>
				</form>
			</div>
		</div>
<jsp:include page="../fragments/footer.jsp" />
	</div>

	<script src="<c:url value='/static/lib/angular.js' />"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/job_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/job_controller.js' />"></script>
</body>
</html>