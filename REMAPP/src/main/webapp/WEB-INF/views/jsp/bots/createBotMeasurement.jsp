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
<title>IAM BOTS</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="BotMeasurementController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Add New Bot Measurement</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Bot Name</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.botMeasurement.botName"
									name="botName" class="form-control input-sm"
									placeholder="Enter Bot Name" required ng-minlength="3" /> -->
								<!-- <select class="form-control" name="botName"
									ng-options="botMsrts.botId as botMsrts.botName for botMsrts in pageableBots"
									ng-model="ctrl.botMeasurement.botId" ng-select="ctrl.botMeasurement.botId" required ng-change="onSelectBotName(ctrl.botMeasurement.botId)">
								</select> -->
								
								<ui-select ng-model="botMsrts.selected" theme="bootstrap" style="min-width: 300px;"
					               class="btn-group bootstrap-select"
					               append-to-body="true"
					               search-enabled="true"
					               ng-required="true" ng-change="onSelectBotName(botMsrts.selected)">
					      		<ui-select-match placeholder="Select BotName">
					        	{{$select.selected.botName}}
					      		</ui-select-match>
					      		<ui-select-choices repeat="botMsrts in pageableBots | filter: $select.search">
					        	<span ng-bind-html="botMsrts.botName"></span>
					      		</ui-select-choices>
					    		</ui-select>
								
								<div class="has-error" ng-show="myForm.botName.$dirty">
									<span ng-show="myForm.botName.$error.required" style="color: red">This is a required field</span> 
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.competency"
									name="grade" class="username form-control input-sm"/> -->
								{{ctrl.botMeasurement.competency}}
								<!-- <select class="form-control" name="competency"
									ng-options="competency.value as competency.value for competency in competencies"
									ng-model="ctrl.bot.competency" ng-select="ctrl.bot.competency" required>
								</select> -->
								<div class="has-error" ng-show="myForm.competency.$dirty">
								<span ng-show="myForm.competency.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Id</label>
							<div class="col-md-8">
      									<input type="text" ng-model="ctrl.botMeasurement.projectId"
											name="projectId" class="form-control input-sm"
											placeholder="Enter Project Id" required ng-minlength="3" ng-disabled="true"/>
										<div class="has-error" ng-show="myForm.projectId.$dirty">
											<span ng-show="myForm.projectId.$error.required" style="color: red">This is a required field</span>
										</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.botMeasurement.projectName"
									name="projectName" class="form-control input-sm"
									placeholder="Enter Project Name" required ng-minlength="3" ng-disabled="true"/>
								
								<div class="has-error" ng-show="myForm.projectName.$dirty">
									<span ng-show="myForm.projectName.$error.required" style="color: red">This is a required field</span>
								</div>
								
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.botMeasurement.customerName"
									name="customerName" class="form-control input-sm"
									placeholder="Enter Customer Name" required ng-minlength="3" /> -->
								<select class="form-control" name="customerName" ng-change="onSelectCustName(ctrl.botMeasurement.customerName)"
									ng-options="accountBase.accountName as accountBase.accountName for accountBase in accountBaseList"
									ng-model="ctrl.botMeasurement.customerName" ng-select="ctrl.botMeasurement.customerName" required ng-disabled="true">
								</select>	
								
								<div class="has-error" ng-show="myForm.customerName.$dirty">
									<span ng-show="myForm.customerName.$error.required" style="color: red">This is a required field</span>
								</div>	
								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency Manager</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.botMeasurement.compManager"
									name="compManager" class="form-control input-sm" ng-disabled="true"/>
									
  								<div class="has-error" ng-show="myForm.compManager.$dirty">
									<span ng-show="myForm.compManager.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Created Month</label>
							<div class="col-md-8">
								<input type="month" ng-model="ctrl.botMeasurement.createdMonth"
									name="createdDate" class="form-control input-sm"
									placeholder="Enter Created Date" required ng-minlength="3" />
  								<div class="has-error" ng-show="myForm.createdDate.$dirty">
									<span ng-show="myForm.createdDate.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort Before Bot (Hrs)</label>
							<div class="col-md-8">
								<input type="number" ng-model="ctrl.botMeasurement.manualEffortBeforeBot"
									name="manualEffortBeforeBot" class="username form-control input-sm" required ng-disabled="true"/>
  								<div class="has-error" ng-show="myForm.manualEffortBeforebot.$dirty">
									<span ng-show="myForm.manualEffortBeforebot.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort After Bot (Hrs)</label>
							<div class="col-md-8">
								<input type="number" ng-model="ctrl.botMeasurement.manualEffortAfterBot"
									name="manualEffortAfterBot" class="username form-control input-sm" required ng-disabled="true"/>
								<div class="has-error" ng-show="myForm.manualEffortAfterbot.$dirty">
									<span ng-show="myForm.manualEffortAfterbot.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">No Of Executions</label>
							<div class="col-md-8">
								<input type="number" ng-model="ctrl.botMeasurement.noOfExecutions"
									name="noOfExecutions" class="username form-control input-sm" required ng-change="onChangeExec()"/>
  								<div class="has-error" ng-show="myForm.noOfExecutions.$dirty">
									<span ng-show="myForm.noOfExecutions.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">No Of Failures</label>
							<div class="col-md-8">
								<input type="number" ng-model="ctrl.botMeasurement.noOfFailures"
									name="noOfFailures" class="username form-control input-sm" required ng-change="onChangeExec()"/>
								<div class="has-error" ng-show="myForm.noOfFailures.$dirty">
									<span ng-show="myForm.noOfFailures.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Total Hrs Saved (Hrs)</label>
							<div class="col-md-8">
							<input type="number" ng-model="ctrl.botMeasurement.totalHrsSaved"
									name="totalHrsSaved" class="username form-control input-sm" required ng-disabled="true"/>
								<div class="has-error" ng-show="myForm.totalHrsSaved.$dirty">
									<span ng-show="myForm.totalHrsSaved.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Data Entered By</label>
							<div class="col-md-8">
							<input type="text" ng-model="ctrl.botMeasurement.dataEnteredBy"
									name="dataEnteredBy" class="username form-control input-sm" required/>
								<div class="has-error" ng-show="myForm.dataEnteredBy.$dirty">
									<span ng-show="myForm.dataEnteredBy.$error.required" style="color: red">This is a required field</span>
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
		
	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/bot_measurement_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_measurement_controller.js' />"></script>
</body>


</html>