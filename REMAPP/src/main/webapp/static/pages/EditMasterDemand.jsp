<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ERSS Resource Manager</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div ng-controller="MasterDemandController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Edit Master Demand </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.update()" name="myForm"
					class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">SO ID</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.soId"
									name="soId" class="username form-control input-sm"
									placeholder="Enter SO ID" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.soId.$dirty">
									<span ng-show="myForm.soId.$error.required">This
										is a required field</span> 
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Billability</label>
							<div class="col-md-8" >
								<input type="radio" ng-model="demandRequest.billability" value="Yes">Yes
  								<input type="radio" ng-model="demandRequest.billability" value="No">No
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.billability.$error.required">This
										is a required field</span>
								</div>
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requirement Start Date</label>
							<div class="col-md-8">
								<input type="date" ng-model="demandRequest.reqStartDate"
									name="reqStartDate" class="username form-control input-sm"/>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Priority</label>
							<div class="col-md-8" >
								<!-- <select class="form-control" id="exampleSelect1"
									ng-model="demandRequest.priority">
									<option>Critical</option>
									<option>High</option>
									<option>Low</option>
								</select> -->
								
								<select class="form-control" name="status"
									ng-options="priority.value as priority.value for priority in priorities"
									ng-model="demandRequest.priority" ng-select="demandRequest.priority">
								</select>
								
							</div>
							
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Grade</label>
							<div class="col-md-8" >
								<!-- <input type="text" ng-model="demandRequest.grade"
									name="grade" class="username form-control input-sm"/> -->
									
								<select class="form-control" name="status"
									ng-options="grade.value as grade.value for grade in grades"
									ng-model="demandRequest.grade" ng-select="demandRequest.grade">
								</select>
								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">PDP</label>
							<div class="col-md-8" >
								<input type="radio" ng-model="demandRequest.pdp" value="Yes">Yes
  								<input type="radio" ng-model="demandRequest.pdp" value="No">No
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Vertical</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.vertical"
									name="grade" class="username form-control input-sm"/>
							</div>
							
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Account Name</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.accountName"
									name="grade" class="username form-control input-sm"/>
								
							</div>
							
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Offshore</label>
							<div class="col-md-8" >
								<input type="radio" ng-model="demandRequest.offShore" value="Yes">Yes
  								<input type="radio" ng-model="demandRequest.offShore" value="No">No
							</div>
							
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">City</label>
							<div class="col-md-8" >
								<!-- <input type="text" ng-model="demandRequest.city"
									name="grade" class="username form-control input-sm"/> -->
									
								<select class="form-control" name="status"
									ng-options="city.value as city.value for city in cities"
									ng-model="demandRequest.city" ng-select="demandRequest.city">
								</select>
							</div>
							
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Country</label>
							<div class="col-md-8" >
								<!-- <input type="text" ng-model="demandRequest.country"
									name="grade" class="username form-control input-sm"/> -->
								<select class="form-control" name="status"
									ng-options="country.value as country.value for country in countries"
									ng-model="demandRequest.country" ng-select="demandRequest.country">
								</select>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project ID</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.projectID"
									name="grade" class="username form-control input-sm"/>
								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.projectName"
									name="grade" class="username form-control input-sm"/>
							</div>
							
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Flagged for Hire</label>
							<div class="col-md-8" >
								<input type="checkbox" ng-model="demandRequest.flaggedForHire">
							</div>
							
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Flagged for Hire Date</label>
							<div class="col-md-8" >
								<input type="date" ng-model="demandRequest.flaggedForHireDate"
									name="grade" class="username form-control input-sm"/>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Technical Skills</label>
							<div class="col-md-8" >
								<!-- <input type="text" ng-model="demandRequest.technicalSkills"
								name="technicalSkills" class="username form-control input-sm"> -->
								
								<select class="form-control" name="status"
									ng-options="skill.value as skill.value for skill in technicalSkills"
									ng-model="demandRequest.technicalSkills" ng-select="demandRequest.technicalSkills">
								</select>
								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requestor Name</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.requestorName"
									name="grade" class="username form-control input-sm"/>
							</div>
							
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requestor ID</label>
							<div class="col-md-8" >
								<input type="text" ng-model="demandRequest.requestorID"
								name="requestorID" class="username form-control input-sm">
							</div>
							
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8" >
								<!-- <input type="text" ng-model="demandRequest.competency"
									name="grade" class="username form-control input-sm"/> -->
									
								<select class="form-control" name="status"
									ng-options="competency.value as competency.value for competency in competencies"
									ng-model="demandRequest.competency" ng-select="demandRequest.competency">
								</select>
								
							</div>
							
							
							</div>
							
							<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Status</label>
							<div class="col-md-8" >
								<select class="form-control" name="status"
									ng-options="status for status in statusList"
									ng-model="demandRequest.status" ng-select="demandRequest.status" ng-disabled="demandRequest.status=='Completed'">
								</select>
								
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
		</div>
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/masterdemand_controller.js' />"></script>
</body>


</html>