<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM BOTS</title>

</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="BotController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Edit Bot </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.update()" name="myForm"
					class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Bot Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="bot.botName"
									name="botName" class="form-control input-sm"
									placeholder="Enter Bot Name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.botName.$dirty">
									<span ng-show="myForm.botName.$error.required" style="color: red">This is a required field</span> 
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Automation Solution</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="bot.automationSolution"
									name="automationSolution" class="form-control input-sm"
									placeholder="Enter Automation Solution" required ng-minlength="3" /> -->
									
								<select class="form-control" name="automationSolution"
									ng-options="sList for sList in solutionList"
									ng-model="ctrl.bot.automationSolution" ng-select="ctrl.bot.automationSolution" required>
								</select>
									
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
      									<input type="text" ng-model="bot.projectId"
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
								<input type="text" ng-model="bot.projectName"
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
								<!-- <input type="text" ng-model="bot.customerName"
									name="customerName" class="form-control input-sm"
									placeholder="Enter Customer Name" required ng-minlength="3" /> -->
									
								<select class="form-control" name="customerName" ng-change="onSelectCustName(bot.customerName)"
									ng-options="custDetails.name as custDetails.name for custDetails in customerDetails"
									ng-model="bot.customerName" ng-select="bot.customerName" required>
								</select>
								
								<div class="has-error" ng-show="myForm.customerName.$dirty">
									<span ng-show="myForm.customerName.$error.required" style="color: red">This is a required field</span>
								</div>	
								
							</div>
							</div>
							
							<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency Manager</label>
							<div class="col-md-8">
								<input type="text" ng-model="bot.compManager"
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
								<!-- <input type="text" ng-model="bot.botCategory"
									name="botCategory" class="form-control input-sm"
									placeholder="Enter Bot Category" required ng-minlength="3" /> -->
									
								<select class="form-control" name="botCategory"
									ng-options="bList for bList in botCategoryList"
									ng-model="bot.botCategory" ng-select="bot.botCategory" required>
								</select>	
								
  								<div class="has-error" ng-show="myForm.botCategory.$dirty">
									<span ng-show="myForm.botCategory.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Bot Created By</label>
							<div class="col-md-8">
								<input type="text" ng-model="bot.botCreatedBy"
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
							<div class="col-md-8">
								<input type="number" ng-model="bot.manualEffortBeforeBot"
									name="manualEffortBeforeBot" class="username form-control input-sm" required/>
  								<div class="has-error" ng-show="myForm.manualEffortBeforeBot.$dirty">
									<span ng-show="myForm.manualEffortBeforeBot.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort After Bot (Hrs)</label>
							<div class="col-md-8">
								<input type="number" ng-model="bot.manualEffortAfterBot"
									name="manualEffortAfterBot" class="username form-control input-sm" required/>
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
							<!-- <input type="text" ng-model="bot.supportOrEngineering"
									name="supportOrEngineering" class="username form-control input-sm" required/> -->
									
								<select class="form-control" name="supportOrEngineering"
									ng-options="sList for sList in supportList"
									ng-model="bot.supportOrEngineering" ng-select="bot.supportOrEngineering" required>
								</select>	
								
								<div class="has-error" ng-show="myForm.supportOrEngineering.$dirty">
									<span ng-show="myForm.supportOrEngineering.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">UseCase/ Purpose</label>
							<div class="col-md-10">
								<!-- <input type="text" ng-model="bot.useCaseOrPurpose"
									name="useCaseOrPurpose" class="username form-control input-sm" required/> -->
									
								<textarea ng-model="bot.useCaseOrPurpose"
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
							value="Update"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
							ng-click="ctrl.update(myForm)">
						<button type="button" ng-click="cancel()"
							class="btn btn-warning btn-sm">Cancel</button>
					</div>
					</div>
					
				
			</div>

	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_controller.js' />"></script>
</body>


</html>