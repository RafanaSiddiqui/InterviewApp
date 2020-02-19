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
<title>IAM Automation API Registry</title>
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
	<div class="container" ng-controller="ApiController as ctrl">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> API </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-striped" >
  <thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>API EndPoint</th>


							<th width="10%">
								 <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
								ng-click="showme=true"></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.apiRequestList">
							<td><span ng-bind="u.apiName"></span></td>
							<td><span ng-bind="u.desc"></span></td>
							<td><span ng-bind="u.endPointUrl"></span></td>


							<td >

								<span class="glyphicon glyphicon-pencil" aria-hidden="true" style="color:blue"
								ng-click="ctrl.edit(u.id)"></span>
								<span style="display:inline-block; width: 5%;"></span>

								<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 ng-click="ctrl.remove(u.id)"></span>
								 </td>


						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default" ng-show="showme">
			<div class="panel-heading">
				<span class="lead">IAM API Registry </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.apiRequest.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">API Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.apiRequest.apiName"
									name="apiName" class="username form-control input-sm"
									placeholder="Enter API name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.apiName.$error.required">This is a
										required field</span> <span ng-show="myForm.apiName.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.apiName.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Description</label>
							<div class="col-md-7">

							<textarea class="form-control" rows="5" id="comment" ng-model="ctrl.apiRequest.desc" placeholder="Enter Description. [This field is validation free]"></textarea>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">EndPoint</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.apiRequest.endPointUrl"
									class="form-control input-sm" placeholder="EndPoint URL." />

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
							<label class="col-md-2 control-lable" for="file">API
								Headers</label>
							<div class="col-md-7">

								<div class="tablecontainer">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Key</th>
												<th>Value</th>


												<th width="20%">

												<span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
								ng-click="ctrl.addNewHeader()"></span>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="headers in ctrl.headersList">
												<td><input type="text" ng-model="headers.key" name=""
													placeholder="Key" ng-disabled="headers.key=='UserName' || headers.key=='Password'"></td>
												<td><input type="text" ng-show="headers.key!='Password'" ng-model="headers.value" name=""
													placeholder="value">
													<input type="{{passType}}" ng-show="headers.key=='Password'" ng-model="headers.value" name=""
													placeholder="value">
													</td>
													<td><span ng-show="headers.key=='Password'" ng-mousedown="showText()" ng-mouseup="hideText()"><i class="glyphicon glyphicon-eye-open" ></i></span>
													</td>
												<td width="20%">

												<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 ng-click="ctrl.removeHeader()"  ng-show="$last"></span>
												</td>
											</tr>

										</tbody>
									</table>

								</div>
							</div>


						</div>
					</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit"
						value="{{!ctrl.apiRequest.id ? 'Add' : 'Update'}}"
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
	<script src="<c:url value='/static/js/service/api_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/api_controller.js' />"></script>
</body>
</html>