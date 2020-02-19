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
<title>Account Base</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="AccountBaseController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Add New Account Base </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Id</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.customerId"
									name="customerId" class="form-control input-sm"/>
								<div class="has-error" ng-show="myForm.customerId.$dirty">
								<span ng-show="myForm.customerId.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Vertical</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.vertical"
									name="vertical" class="form-control input-sm"/>
									
  								<div class="has-error" ng-show="myForm.vertical.$dirty">
									<span ng-show="myForm.vertical.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Account Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.accountName"
									name="accountName" class="form-control input-sm"/>
								<div class="has-error" ng-show="myForm.accountName.$dirty">
								<span ng-show="myForm.accountName.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Primary Location</label>
							<div class="col-md-8">
										<select class="form-control" name="type"
											ng-options="cList.value as cList.value for cList in cityList"
											ng-model="ctrl.accountBase.primaryLocation" ng-select="ctrl.accountBase.primaryLocation" required>
										</select>
											
										<div class="has-error" ng-show="myForm.primaryLocation.$dirty">
											<span ng-show="myForm.primaryLocation.$error.required" style="color: red">This is a required field</span>
										</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Competency Manager Id</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.competencyManagerId"
									name="compManager" class="form-control input-sm"/>
									
  								<div class="has-error" ng-show="myForm.competencyManagerId.$dirty">
									<span ng-show="myForm.competencyManagerId.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Competency Manager</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.competencyManager"
									name="compManager" class="form-control input-sm"/>
									
  								<div class="has-error" ng-show="myForm.compManager.$dirty">
									<span ng-show="myForm.compManager.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">BFD</label>
							<div class="col-md-4">
								
								<input type="number" ng-model="ctrl.accountBase.bfd"
									name="bfd" class="form-control input-sm"
									placeholder="Enter BFD"/>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">BTB</label>
							<div class="col-md-8">
      									<input type="number" ng-model="ctrl.accountBase.btb"
											name="btb" class="form-control input-sm"
											placeholder="Enter Total FTE"/>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">BTM</label>
							<div class="col-md-4">
								
								<input type="number" ng-model="ctrl.accountBase.btm"
									name="btm" class="form-control input-sm"
									placeholder="Enter BTM"/>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">NBL</label>
							<div class="col-md-8">
      									<input type="number" ng-model="ctrl.accountBase.nbl"
											name="nbl" class="form-control input-sm"
											placeholder="Enter Total NBL"/>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ODC Details</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.odcDetails"
									name="odcDetails" class="form-control input-sm" required/>
									
  								<div class="has-error" ng-show="myForm.odcDetails.$dirty">
									<span ng-show="myForm.odcDetails.$error.required" style="color: red">This is a required field</span>
								</div>
								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Teck Stack</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.accountBase.teckStack"
									name="teckStack" class="form-control input-sm" required/>
									
  								<div class="has-error" ng-show="myForm.teckStack.$dirty">
									<span ng-show="myForm.teckStack.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					</form>
						<div class="form-actions floatRight">
						<input type="button"
							value="Add"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
							ng-click="ctrl.submit(myForm)">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm">Cancel</button>
					</div>
					</div>
					
				
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
		
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/account.base.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/account.base.controller.js' />"></script>
</body>


</html>