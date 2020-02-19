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
<title>IAM Automation Email Template</title>
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
	<div class="container" ng-controller="EmailController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
			<span class="lead">IAM Email Template  </span>


			</div>
			<div class="tablecontainer">
				<table class="table table-striped">
					<thead >
						<tr>
							<th>Name</th>
							<th>ID</th>
							<th>Description</th>

							<th width="10%">
								 <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
								ng-click="showme=true"></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.emailTemplateList">
							<td><span ng-bind="u.name"></span></td>
							<td><span ng-bind="u.templateID"></span></td>
							<td><span ng-bind="u.description"></span></td>


							<td>

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
		<div class="panel panel-default"  ng-show="showme">
			<div class="panel-heading">
				<span class="lead">IAM Email Template </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.emailTemplate.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file"> Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.name"
									name="name" class="username form-control input-sm"
									placeholder="Enter Template name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.name.$error.required">This is a
										required field</span> <span ng-show="myForm.name.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.name.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">ID</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.templateID"
									class="form-control input-sm"
									placeholder="Email Template ID for ex E01" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.templateID.$error.required">This
										is a required field</span> <span ng-show="myForm.templateID.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Description</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.description"
									class="form-control input-sm"
									placeholder="Email Template Description" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.description.$error.required">This
										is a required field</span> <span
										ng-show="myForm.description.$invalid">This field is
										invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">From</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.from"
									class="form-control input-sm" placeholder="From Email Addresse" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.from.$error.required">This is a
										required field</span> <span ng-show="myForm.from.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">To</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.to"
									class="form-control input-sm" placeholder="To Email Addresse" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.to.$error.required">This is a
										required field</span> <span ng-show="myForm.to.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">CC</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.cc"
									class="form-control input-sm" placeholder="CC Email Addresse" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.cc.$error.required">This is a
										required field</span> <span ng-show="myForm.cc.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Subject</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.emailTemplate.subject"
									class="form-control input-sm" placeholder="Email Subject" />

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.subject.$error.required">This is a
										required field</span> <span ng-show="myForm.subject.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Body</label>
							<div class="col-md-7">
							<textarea class="form-control" rows="5" id="comment" ng-model="ctrl.emailTemplate.message" placeholder="Email body"></textarea>

								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.message.$error.required">This is a
										required field</span> <span ng-show="myForm.message.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.emailTemplate.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid" ng-click="showme=false">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm"  ng-disabled="myForm.$pristine">Cancel</button>
						</div>
					</div>
				</form>
			</div>
		</div>
<jsp:include page="../fragments/footer.jsp" />
	</div>


	<script src="<c:url value='/static/js/service/email_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/email_controller.js' />"></script>
</body>
</html>