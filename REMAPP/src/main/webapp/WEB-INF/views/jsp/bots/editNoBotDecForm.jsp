<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM BOTS</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="NoBotController as ctrl" ng-init="editNoBotById('${fn:escapeXml(noBotId)}')">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead" ng-show="userRole=='ROLE_BOTS'">Edit NoBot Declarations</span>
				<span class="lead" ng-show="botApprover">Approve Or Reject NoBot Declarations</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			
			<div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								<select class="form-control" name="customerName"
									ng-options="custDetails.name as custDetails.name for custDetails in customerDetails"
									ng-model="ctrl.noBot.customerName" ng-select="ctrl.noBot.customerName" required>
								</select>
								 <div class="has-error" ng-show="myForm.accountName.$dirty">
								<span ng-show="myForm.accountName.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12" >
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Project Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.noBot.projectName"
									name="projectName" class="form-control input-sm"
									placeholder="Enter Project Name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.projectName.$dirty">
									<span ng-show="myForm.projectName.$error.required" style="color: red">This is a required field</span> 
									<span ng-show="myForm.projectName.$error.validationError" style="color:red">Project Name Already Exists</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Comments</label>
							<div class="col-md-10">
								<!-- <input type="text" ng-model="ctrl.bot.useCaseOrPurpose"
									name="useCaseOrPurpose" class="username form-control input-sm" required/> -->
								<textarea ng-model="ctrl.noBot.comments"
									name="comments" class="username form-control input-sm" required style="height: 200px;"></textarea>	
								<div class="has-error" ng-show="myForm.comments.$dirty">
									<span ng-show="myForm.comments.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-12">
						<div class="col-md-2" >
							<input type="checkbox" ng-model="ctrl.noBot.decCheck" name="decCheck">
						</div>
						<label class="col-md-10 control-lable">I agree that there is no bot implemented</label>
						</div>
						<div class="has-error" ng-show="myForm.decCheck.$dirty">
							<span ng-show="myForm.decCheck.$error.required" style="color: red">This is a required field</span>
						</div>
					</div>
					
					<div class="row col-md-12" ng-show="checkRole('ROLE_ADMIN')">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Approve or Reject</label>
							<div class="col-md-8">
								<input type="radio" ng-model="ctrl.noBot.status" value="APPROVED" name="status" required>APPROVE
  								<input type="radio" ng-model="ctrl.noBot.status" value="REJECTED" name="status" required>REJECT
								<div class="has-error" ng-show="myForm.status.$dirty">
									<span ng-show="myForm.status.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					<!-- <div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-4 control-lable">Click the check box to declare</label>
							<div class="col-md-8" >
								<input type="checkbox">
							</div>						
						</div>
					</div> -->
					
					</form>
						<div class="form-actions floatRight">
						<input type="button"
							value="Update"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
							ng-click="ctrl.update(myForm)">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm">Cancel</button>
					</div>
					</div>
					
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
		
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/nobot.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/nobot.controller.js' />"></script>
</body>


</html>