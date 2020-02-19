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
	<div class="container" ng-controller="BotController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Add New Bot </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.competency"
									name="grade" class="username form-control input-sm"/> -->
									
								<select class="form-control" name="competency"
									ng-options="competency.value as competency.value for competency in competencies"
									ng-model="ctrl.bot.competency" ng-select="ctrl.bot.competency" required>
								</select>
								<div class="has-error" ng-show="myForm.competency.$dirty">
								<span ng-show="myForm.competency.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Bot Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.bot.botName"
									name="botName" class="form-control input-sm"
									placeholder="Enter Bot Name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.botName.$dirty">
									<span ng-show="myForm.botName.$error.required" style="color: red">This is a required field</span> 
									<span ng-show="myForm.botName.$error.validationError" style="color:red">Bot Name Already Exists</span>
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Automation Solution</label>
							<div class="col-md-4">
								
								<!-- <input type="text" ng-model="ctrl.bot.automationSolution"
									name="automationSolution" class="form-control input-sm"
									placeholder="Enter Automation Solution" required ng-minlength="3" /> -->
								
								<select class="form-control" name="automationSolution"
									ng-options="sList.value as sList.value for sList in solutionList"
									ng-model="ctrl.bot.automationSolution" ng-select="ctrl.bot.automationSolution" required>
								</select>	
									
								<div class="has-error" ng-show="myForm.automationSolution.$dirty">
									<span ng-show="myForm.automationSolution.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
							<div class="col-md-4" ng-if="ctrl.bot.automationSolution=='Others'">
								
								<input type="text" ng-model="ctrl.bot.automationSolutionActual"
									name="automationSolutionActual" class="form-control input-sm"
									placeholder="Enter Automation Solution" />
								
								<div class="has-error" ng-show="myForm.automationSolution.$dirty">
									<span ng-show="myForm.automationSolution.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Id</label>
							<div class="col-md-8">
      									<input type="text" ng-model="ctrl.bot.projectId"
											name="projectId" class="form-control input-sm"
											placeholder="Enter Project Id" required ng-minlength="3" />
										<div class="has-error" ng-show="myForm.projectId.$dirty">
											<span ng-show="myForm.projectId.$error.required" style="color: red">This is a required field</span>
										</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.bot.projectName"
									name="projectName" class="form-control input-sm"
									placeholder="Enter Project Name" required ng-minlength="3" />
								
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
								<!-- <input type="text" ng-model="ctrl.bot.customerName"
									name="customerName" class="form-control input-sm"
									placeholder="Enter Customer Name" required ng-minlength="3" /> -->
									
									<!-- <select class="form-control" name="customerName" ng-change="onSelectCustName(accountBase)"
										ng-options="accountBase.accountName as accountBase.accountName for accountBase in accountBaseList"
										ng-model="ctrl.bot.customerName" ng-select="ctrl.bot.customerName" required>
									</select> -->
									
								<ui-select ng-model="accountBase.selected" theme="bootstrap" style="min-width: 300px;"
						               class="btn-group bootstrap-select"
						               append-to-body="true"
						               search-enabled="true"
						               ng-required="true" ng-change="onSelectCustName(accountBase.selected)">
						      		<ui-select-match placeholder="Select BotName">
						        	{{$select.selected.accountName}}
						      		</ui-select-match>
						      		<ui-select-choices repeat="accountBase in accountBaseList | filter: $select.search">
						        	<span ng-bind-html="accountBase.accountName"></span>
						      		</ui-select-choices>
					    		</ui-select>
									
								<div class="has-error" ng-show="myForm.customerName.$dirty">
									<span ng-show="myForm.customerName.$error.required" style="color: red">This is a required field</span>
								</div>	
								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency Manager</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.bot.compManager"
									name="compManager" class="form-control input-sm" ng-disabled="true"/>
									
  								<div class="has-error" ng-show="myForm.compManager.$dirty">
									<span ng-show="myForm.compManager.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Bot Category</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.bot.botCategory"
									name="botCategory" class="form-control input-sm"
									placeholder="Enter Bot Category" required ng-minlength="3" /> -->
									
									<select class="form-control" name="botCategory"
										ng-options="bList.value as bList.value for bList in botCategoryList"
										ng-model="ctrl.bot.botCategory" ng-select="ctrl.bot.botCategory" required>
									</select>
									
  								<div class="has-error" ng-show="myForm.botCategory.$dirty">
									<span ng-show="myForm.botCategory.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Bot Created By (Emp Id)</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.bot.botCreatedBy"
									name="botCreatedBy" class="username form-control input-sm" required/>
							<div class="has-error" ng-show="myForm.botCreatedBy.$dirty">
								<span ng-show="myForm.botCreatedBy.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort Before Bot (Hrs)</label>
							<div class="col-md-4">
								<input type="number" ng-model="manualEffortBeforeBotHr"
									name="manualEffortBeforeBot" class="username form-control input-sm" placeholder="hr"/>
  								<div class="has-error" ng-show="myForm.manualEffortBeforeBot.$dirty">
									<span ng-show="myForm.manualEffortBeforeBot.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
							<div class="col-md-4">
								<input type="number" ng-model="manualEffortBeforeBotMin"
									name="manualEffortBeforeBot" class="username form-control input-sm" placeholder="min"/>
  								<div class="has-error" ng-show="myForm.manualEffortBeforeBot.$dirty">
									<span ng-show="myForm.manualEffortBeforeBot.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort After Bot (Hrs)</label>
							<div class="col-md-4">
								<input type="number" ng-model="manualEffortAfterBotHr"
									name="manualEffortAfterBot" class="username form-control input-sm" placeholder="hr"/>
								<div class="has-error" ng-show="myForm.manualEffortAfterBot.$dirty">
									<span ng-show="myForm.manualEffortAfterBot.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
							<div class="col-md-4">
								<input type="number" ng-model="manualEffortAfterBotMin"
									name="manualEffortAfterBot" class="username form-control input-sm" placeholder="min"/>
								<div class="has-error" ng-show="myForm.manualEffortAfterBot.$dirty">
									<span ng-show="myForm.manualEffortAfterBot.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Support/ Engineering</label>
							<div class="col-md-8">
							<!-- <input type="text" ng-model="ctrl.bot.supportOrEngineering"
									name="supportOrEngineering" class="username form-control input-sm" required/> -->
									
								<select class="form-control" name="supportOrEngineering"
									ng-options="sList.value as sList.value for sList in supportList"
									ng-model="ctrl.bot.supportOrEngineering" ng-select="ctrl.bot.supportOrEngineering" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.supportOrEngineering.$dirty">
									<span ng-show="myForm.supportOrEngineering.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Security Domain</label>
							<div class="col-md-8">
								<select class="form-control" name="securityDomain"
									ng-options="secList.name as secList.name for secList in securityDomainList"
									ng-model="ctrl.bot.securityDomain" ng-select="ctrl.bot.securityDomain" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.securityDomain.$dirty">
									<span ng-show="myForm.securityDomain.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service Category</label>
							<div class="col-md-8">
								<select class="form-control" name="serviceCategory"
									ng-options="serCatList.name as serCatList.name for serCatList in serviceCategoryList"
									ng-model="ctrl.bot.serviceCategory" ng-select="ctrl.bot.serviceCategory" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.serviceCategory.$dirty">
									<span ng-show="myForm.serviceCategory.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service</label>
							<div class="col-md-8">
								<select class="form-control" name="service"
									ng-options="servList.name as servList.name for servList in serviceList"
									ng-model="ctrl.bot.service" ng-select="ctrl.bot.service" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.service.$dirty">
									<span ng-show="myForm.service.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Function Type</label>
							<div class="col-md-8">
								<select class="form-control" name="functionType"
									ng-options="fList.value as fList.value for fList in functionTypeList"
									ng-model="ctrl.bot.functionType" ng-select="ctrl.bot.functionType" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.function.$dirty">
									<span ng-show="myForm.function.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Operational Metrics</label>
							<div class="col-md-8">
								<select class="form-control" name="operationalMetrics"
									ng-options="operList.value as operList.value for operList in operationalMetricsList"
									ng-model="ctrl.bot.operationalMetrics" ng-select="ctrl.bot.operationalMetrics" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.operationalMetrics.$dirty">
									<span ng-show="myForm.operationalMetrics.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">UseCase/ Purpose</label>
							<div class="col-md-10">
								<!-- <input type="text" ng-model="ctrl.bot.useCaseOrPurpose"
									name="useCaseOrPurpose" class="username form-control input-sm" required/> -->
								<textarea ng-model="ctrl.bot.useCaseOrPurpose"
									name="useCaseOrPurpose" class="username form-control input-sm" required style="height: 200px;"></textarea>	
								<div class="has-error" ng-show="myForm.useCaseOrPurpose.$dirty">
									<span ng-show="myForm.useCaseOrPurpose.$error.required" style="color: red">This is a required field</span>
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
	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_controller.js' />"></script>
</body>


</html>