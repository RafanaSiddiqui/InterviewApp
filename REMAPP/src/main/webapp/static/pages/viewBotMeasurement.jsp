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
<body ng-app="myApp" class="ng-cloak" ng-controller="BotMeasurementController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Bot Measurement</span>
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
								{{botMeasurement.botName}}
							</div>
						</div>
						
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8">
								{{botMeasurement.competency}}
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Id</label>
							<div class="col-md-8">
      							{{botMeasurement.projectId}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
								{{botMeasurement.projectName}}
								
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								{{botMeasurement.customerName}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency Manager</label>
							<div class="col-md-8">
								{{botMeasurement.compManager}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Created Date</label>
							<div class="col-md-8">
								{{botMeasurement.createdDate}}
							</div>
						</div>
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort Before Bot (Hrs)</label>
							<div class="col-md-8">
								{{botMeasurement.manualEffortBeforeBot}}
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ManualEffort After Bot (Hrs)</label>
							<div class="col-md-8">
								{{botMeasurement.manualEffortAfterBot}}								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">No Of Executions</label>
							<div class="col-md-8">
								{{botMeasurement.noOfExecutions}}
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
					
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">No Of Failures</label>
							<div class="col-md-8">
								{{botMeasurement.noOfFailures}}							
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Total Hrs Saved (Hrs)</label>
							<div class="col-md-8">
								{{botMeasurement.totalHrsSaved}}							
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
						<label class="col-md-4 control-lable">Data Entered By</label>
						<div class="col-md-8">
							{{botMeasurement.dataEnteredBy}}								
						</div>
					</div>
					</div>
					
					</form>
						<div class="form-actions floatRight">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm">OK</button>
					</div>
					</div>
					
				
			</div>
		
	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/bot_measurement_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_measurement_controller.js' />"></script>
</body>


</html>