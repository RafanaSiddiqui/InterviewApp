<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM Functional Spread</title>

</head>
<body ng-app="myApp" class="ng-cloak"
	ng-controller="IAMFunctionalSpreadController as ctrl">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Functional Spread </span> <input
					type="hidden" ng-model="ctrl.iamFunctionalSpread.id" value="{{id}}" />
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="panel-body">
				<div class="panel-body">
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Vertical</label>
							<div class="col-md-8">
								{{iamFunctionalSpread.vertical}}
							</div>

						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								{{iamFunctionalSpread.customerName}}
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Service Details</span> <span
					style="padding-left: 55%">
					<button type="button" ng-click="openAllPanels()"
						class="btn btn-success btn-sm">Open All</button>
					<button type="button" ng-click="closeAllPanels()"
						class="btn btn-success btn-sm">Collapse All</button>
				</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>


			<div class="panel-body">
				<uib-accordion close-others="false" class="col-md-15">
				<div uib-accordion-group class="panel-default"
					ng-repeat="servicesDetails in ctrl.iamFunctionalSpread.servicesDetails"
					is-open="servicesDetails.openPanel">
					<div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>

					<uib-accordion-heading>
					{{servicesDetails.service}}<i class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': servicesDetails.openPanel, 'glyphicon-chevron-right': !servicesDetails.openPanel}"></i>
					</uib-accordion-heading>

					<div class="row col-md-12">

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Security Domain</label>
							<div class="col-md-8">
							{{servicesDetails.securityDomain}}
							</div>

						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service Category</label>
							<div class="col-md-8">
								{{servicesDetails.serviceCategory}}
							</div>

						</div>

					</div>


					<div class="row col-md-12">


						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service</label>
							<div class="col-md-8">
								{{servicesDetails.service}}
							</div>

						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Tech
								Stack Tier Ref Code</label>
							<div class="col-md-8">
								{{servicesDetails.teckStackTierRefCode}}
							</div>
						</div>

					</div>


					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Support FTE</label>
							<div class="col-md-8">
								{{servicesDetails.supportFTE}}
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Engineering FTE</label>
							<div class="col-md-8">
								{{servicesDetails.engineeringFTE}}
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Use Case
								Present</label>
							<div class="col-md-8">
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].useCasePresent" ng-disabled="true"
									value="Yes" name="{{$index}}.useCasePresent" required>Yes
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].useCasePresent" ng-disabled="true"
									value="No" name="{{$index}}.useCasePresent" required>No
								<div class="has-error" ng-show="myForm.useCasePresent.$dirty">
									<span ng-show="myForm.useCasePresent.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Automation
								Possible</label>
							<div class="col-md-8">
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].automationPossible" ng-disabled="true"
									value="Yes" name="{{$index}}.automationPossible" required>Yes
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].automationPossible" ng-disabled="true"
									value="No" name="{{$index}}.automationPossible" required>No
								<div class="has-error"
									ng-show="myForm.automationPossible.$dirty">
									<span ng-show="myForm.automationPossible.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				</uib-accordion>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal"></form>
				<div class="form-actions floatRight">
					<button type="button" ng-click="ctrl.reset()"
						class="btn btn-warning btn-sm">Cancel</button>
				</div>
			</div>

		</div>


	</div>

	<script
		src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script
		src="<c:url value='/static/js/service/account.base.service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/account.base.controller.js' />"></script>
</body>


</html>