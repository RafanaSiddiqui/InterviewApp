<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>IAM Automation Bulk App - Request</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="BulkRequestController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Create Bulk Request </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.bulkRequest.name" name="Name"
									class="username form-control input-sm"
									placeholder="Enter Bulk Request name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.applicationName.$error.required">This
										is a required field</span> <span
										ng-show="myForm.applicationName.$error.minlength">Minimum
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

								<textarea class="form-control" rows="5" id="comment"
									ng-model="ctrl.bulkRequest.description"
									placeholder="Enter Description. [This field is validation free]"></textarea>
								<!-- <input type="text" ng-model="ctrl.apiRequest.desc"
									class="form-control input-sm"
									placeholder="Enter Description. [This field is validation free]" /> -->
							</div>
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-4 col-xs-12" for="file">Please
							upload the file : <span class="required">*</span>
						</label>
						<div class="col-xs-4 input-max controls ">
							<input class="inline-block" type="file" file-model = "myFile"/>
						</div>

					</div>


				<!-- 	<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Upload a
								file</label>
							<div class="col-md-7">
								<form:input type="file" path="file" id="file"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="file" class="help-inline" />
								</div>
							</div>
						</div>
					</div> -->

					<div class="form-actions floatRight">
						<input type="submit"
							value="{{!ctrl.bulkRequest.bulkRequestID ? 'Add' : 'Update'}}"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Cancel</button>
					</div>

				</form>
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>


	<script src="<c:url value='/static/lib/angular.js' />"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>
	<script src="<c:url value='/static/js/directives/filemodel.js' />"></script>
	<script src="<c:url value='/static/js/service/bulkrequest_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bulkrequest_controller.js' />"></script>

</body>


</html>