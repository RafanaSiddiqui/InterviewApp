<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<script src="<c:url value='/static/lib/angular.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<jsp:include page="../fragments/header.jsp" />
<script
	src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
<script
	src="<c:url value='/static/js/service/account.base.service.js' />"></script>
<script
	src="<c:url value='/static/js/service/iam.functional.spread.service.js' />"></script>
<script
	src="<c:url value='/static/js/controller/iam.functional.spread.controller.js' />"></script>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM Functional Spread</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container"
		ng-controller="IAMFunctionalSpreadController as ctrl"
		ng-init="editIAMFunctionalSpreadById('${fn:escapeXml(id)}')">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Edit Functional Spread </span> <input
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
								<ui-select ng-model="accountBase.selected" theme="bootstrap"
									style="min-width: 300px;" class="btn-group bootstrap-select"
									append-to-body="true" search-enabled="true" ng-required="true"
									ng-change="onSelectVertical(accountBase.selected)">
								<ui-select-match placeholder="Select Vertical">
								{{$select.selected.vertical}} </ui-select-match> <ui-select-choices
									repeat="accountBase in accountBaseList | unique : 'vertical' | filter: $select.search">
								<span ng-bind-html="accountBase.vertical"></span> </ui-select-choices> </ui-select>
								<div class="has-error" ng-show="myForm.vertical.$dirty">
									<span ng-show="myForm.vertical.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>

						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								<ui-select ng-model="custName.selected" theme="bootstrap"
									style="min-width: 300px;" class="btn-group bootstrap-select"
									append-to-body="true" search-enabled="true" ng-required="true"
									ng-change="onSelectCustName(custName.selected)"> <ui-select-match
									placeholder="Select Customer">
								{{$select.selected.accountName}} </ui-select-match> <ui-select-choices
									repeat="custName in customerList | filter: $select.search">
								<span ng-bind-html="custName.accountName"></span> </ui-select-choices> </ui-select>
								<div class="has-error" ng-show="myForm.customerName.$dirty">
									<span ng-show="myForm.customerName.$error.required"
										style="color: red">This is a required field</span>
								</div>
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
					<button type="button" ng-click="addServiceDetails()"
						class="btn btn-success btn-sm">Add Service Details</button>
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
					{{servicesDetails.service}}<i
						class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': servicesDetails.openPanel, 'glyphicon-chevron-right': !servicesDetails.openPanel}"></i>
					</uib-accordion-heading>

					<div class="row col-md-12">

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Security Domain</label>
							<div class="col-md-8">
								<ui-select ng-model="servicesDetails.securityDomain"
									theme="bootstrap" style="min-width: 300px;"
									class="btn-group bootstrap-select" append-to-body="true"
									search-enabled="true" ng-required="true"
									ng-change="onSelectSecurityDomain(servicesDetails.securityDomain,$index)">
								<ui-select-match placeholder="Select Security Domain">
								{{servicesDetails.securityDomain}} </ui-select-match> <ui-select-choices
									repeat="securityDomain in securityDomains | filter: $select.search">
								<span ng-bind-html="securityDomain.name"></span> </ui-select-choices> </ui-select>
								<div class="has-error" ng-show="myForm.securityDomain.$dirty">
									<span ng-show="myForm.securityDomain.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>

						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service Category</label>
							<div class="col-md-8">
								<ui-select ng-model="servicesDetails.serviceCategory"
									theme="bootstrap" style="min-width: 300px;"
									class="btn-group bootstrap-select" append-to-body="true"
									search-enabled="true" ng-required="true"
									ng-change="onSelectServiceCategory(servicesDetails.serviceCategory,$index)">
								<ui-select-match placeholder="Select Service Category">
								{{servicesDetails.serviceCategory}} </ui-select-match> <ui-select-choices
									repeat="serviceCat in serviceCategory | filter: $select.search">
								<span ng-bind-html="serviceCat.name"></span> </ui-select-choices> </ui-select>
								<div class="has-error" ng-show="myForm.serviceCategory.$dirty">
									<span ng-show="myForm.serviceCategory.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>

						</div>

					</div>

					<input type="hidden"
						ng-model="servicesDetails[$index].OfferingCode"
						value={{servicesDetails[$index].OfferingCode}} />

					<!-- <input type="text"
						ng-model="servicesDetails.serviceCategory"
						value={{servicesDetails.serviceCategory1}} />
						
							<input type="text"
						ng-model="servicesDetails.service"
						value={{servicesDetails.service}} /> -->

					<div class="row col-md-12">


						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service</label>
							<div class="col-md-8">
								<ui-select ng-model="servicesDetails.service" theme="bootstrap"
									style="min-width: 300px;" class="btn-group bootstrap-select"
									append-to-body="true" search-enabled="true" ng-required="true"
									ng-change="onSelectService(servicesDetails.service,$index)">
								<ui-select-match placeholder="Select Service">
								{{servicesDetails.service}} </ui-select-match> <ui-select-choices
									repeat="serviceDetails in service | filter: $select.search">
								<span ng-bind-html="serviceDetails.name"></span> </ui-select-choices> </ui-select>
								<div class="has-error" ng-show="myForm.service.$dirty">
									<span ng-show="myForm.service.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>

						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Tech
								Stack Tier Ref Code</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].teckStackTierRefCode"
									name="teckStackTierRefCode" class="form-control input-sm"
									ng-disabled="true" />

								<div class="has-error"
									ng-show="myForm.teckStackTierRefCode.$dirty">
									<span ng-show="myForm.teckStackTierRefCode.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>


					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Support FTE</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].supportFTE"
									name="supportFTE" class="form-control input-sm" />

								<div class="has-error" ng-show="myForm.supportFTE.$dirty">
									<span ng-show="myForm.supportFTE.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Engineering FTE</label>
							<div class="col-md-8">
								<input type="text"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].engineeringFTE"
									name="engineeringFTE" class="form-control input-sm" />

								<div class="has-error" ng-show="myForm.engineeringFTE.$dirty">
									<span ng-show="myForm.engineeringFTE.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Use Case
								Present</label>
							<div class="col-md-8">
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].useCasePresent"
									value="Yes" name="{{$index}}.useCasePresent" required>Yes
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].useCasePresent"
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
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].automationPossible"
									value="Yes" name="{{$index}}.automationPossible" required>Yes
								<input type="radio"
									ng-model="ctrl.iamFunctionalSpread.servicesDetails[$index].automationPossible"
									value="No" name="{{$index}}.automationPossible" required>No
								<div class="has-error"
									ng-show="myForm.automationPossible.$dirty">
									<span ng-show="myForm.automationPossible.$error.required"
										style="color: red">This is a required field</span>
								</div>
							</div>
						</div>

					</div>

					<div class="row col-md-12" align="right">
						<div class="col-md-12">
							<button type="button" ng-click="deleteServiceDetails()"
								class="btn btn-danger btn-sm">Delete</button>
						</div>
					</div>
				</div>
				</uib-accordion>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal"></form>
				<div class="form-actions floatRight">
					<input type="button" value="Update" class="btn btn-primary btn-sm"
						ng-disabled="myForm.$invalid" ng-click="ctrl.submit(myForm)">
					<button type="button" ng-click="ctrl.reset()"
						class="btn btn-warning btn-sm">Cancel</button>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />


</body>


</html>