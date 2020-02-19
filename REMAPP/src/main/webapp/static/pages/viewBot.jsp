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
				<span class="lead"> Bot </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.update()" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8">
								{{bot.competency}}
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Bot Name</label>
							<div class="col-md-8">
								{{bot.botName}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Automation Solution</label>
							<div class="col-md-4">
								{{bot.automationSolution}}
							</div>
							
							<div class="col-md-4" ng-if="bot.automationSolution=='Others'">
								{{bot.automationSolutionActual}}
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Id</label>
							<div class="col-md-8">
      							{{bot.projectId}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
								{{bot.projectName}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								{{bot.customerName}}	
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency Manager</label>
							<div class="col-md-8">
								{{bot.compManager}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Bot Category</label>
							<div class="col-md-8">
								{{bot.botCategory}}
							</div>
						</div>
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Bot Created By (Emp Id)</label>
							<div class="col-md-8">
								{{bot.botCreatedBy}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Support/ Engineering</label>
							<div class="col-md-8">
								{{bot.supportOrEngineering}}								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort Before Bot (Hrs)</label>
							<div class="col-md-8">
								{{manualEffortBeforeBotHr}}:{{manualEffortBeforeBotMin}} 
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort After Bot (Hrs)</label>
							<div class="col-md-8">
								{{manualEffortAfterBotHr}}:{{manualEffortAfterBotMin}}							
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Security Domain</label>
							<div class="col-md-8">
								{{bot.securityDomain}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service Category</label>
							<div class="col-md-8">
								{{bot.serviceCategory}}
							</div>
						</div>
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Service</label>
							<div class="col-md-8">
								{{bot.service}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Function Type</label>
							<div class="col-md-8">
								{{bot.functionType}}
							</div>
						</div>
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Operational Metrics</label>
							<div class="col-md-8">
								{{bot.operationalMetrics}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">UseCase/ Purpose</label>
							<div class="col-md-8">
								{{bot.useCaseOrPurpose}}
							</div>
						</div>
						
					</div>
					
					</form>
						<div class="form-actions floatRight">
						<button type="button" ng-click="cancel()"
							class="btn btn-warning btn-sm">OK</button>
					</div>
					</div>
					
				
			</div>
			
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_controller.js' />"></script>
</body>


</html>