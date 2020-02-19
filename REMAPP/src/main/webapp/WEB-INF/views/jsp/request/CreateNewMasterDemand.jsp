<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<title>ERSS Resource Manager</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="MasterDemandController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Create New Master Demand </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">

					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">SO ID</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.demandRequest.soId"
									name="soId" class="username form-control input-sm"
									placeholder="Enter SO ID" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.soId.$dirty">
									<span ng-show="myForm.soId.$error.required" style="color: red">This is a required field</span> 
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Billability</label>
							<div class="col-md-8">
								<input type="radio" ng-model="ctrl.demandRequest.billability" value="Yes" name="billability" required>Yes
  								<input type="radio" ng-model="ctrl.demandRequest.billability" value="No" name="billability" required>No
								<div class="has-error" ng-show="myForm.billability.$dirty">
									<span ng-show="myForm.billability.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requirement Start Date</label>
							<div class="col-md-8">
      									<!-- <input ng-model="ctrl.demandRequest.reqStartDate" type="text" class="angular-datepicker-input"/> -->
      									<input type="date" ng-model="ctrl.demandRequest.reqStartDate"
											name="reqStartDate" class="username form-control input-sm" required/>
										<div class="has-error" ng-show="myForm.reqStartDate.$dirty">
											<span ng-show="myForm.reqStartDate.$error.required" style="color: red">This is a required field</span>
										</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Priority</label>
							<div class="col-md-8">
								<!-- <select class="form-control" id="exampleSelect1"
									ng-model="ctrl.demandRequest.priority">
									<option>Critical</option>
									<option>High</option>
									<option>Low</option>
								</select>  -->
								
								<select class="form-control" name="priority"
									ng-options="priority.value as priority.value for priority in priorities"
									ng-model="ctrl.demandRequest.priority" ng-select="ctrl.demandRequest.priority" required>
								</select>
								
								<div class="has-error" ng-show="myForm.priority.$dirty">
									<span ng-show="myForm.priority.$error.required" style="color: red">This is a required field</span>
								</div>
								
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Grade</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.grade"
									name="grade" class="username form-control input-sm"/> -->
									
								<!-- <select class="form-control" name="grade"
									ng-options="grade for grade in grades"
									ng-model="ctrl.demandRequest.grade" ng-select="ctrl.demandRequest.grade" required>
								</select>
								
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.grade.$error.required" style="color: red">This is a required field</span>
								</div> -->
								
								<select class="form-control" name="grade"
									ng-options="grade.value as grade.value for grade in grades"
									ng-model="ctrl.demandRequest.grade" ng-select="ctrl.demandRequest.grade" required>
								</select>
								<div class="has-error" ng-show="myForm.grade.$dirty">
									<span ng-show="myForm.grade.$error.required" style="color: red">This is a required field</span>
								</div>	
								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">PDP</label>
							<div class="col-md-8">
								<input type="radio" ng-model="ctrl.demandRequest.pdp" value="Yes" required name="pdp">Yes
  								<input type="radio" ng-model="ctrl.demandRequest.pdp" value="No" required name="pdp">No
  								<div class="has-error" ng-show="myForm.pdp.$dirty">
									<span ng-show="myForm.pdp.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Vertical</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.demandRequest.vertical"
									name="vertical" class="username form-control input-sm" required/>
							<div class="has-error" ng-show="myForm.vertical.$dirty">
								<span ng-show="myForm.vertical.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Account Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.demandRequest.accountName"
									name="accountName" class="username form-control input-sm" required/>
									
								<div class="has-error" ng-show="myForm.accountName.$dirty">
									<span ng-show="myForm.accountName.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Offshore</label>
							<div class="col-md-8">
								<input type="radio" ng-model="ctrl.demandRequest.offShore" value="Yes" required name="offShore">Yes
  								<input type="radio" ng-model="ctrl.demandRequest.offShore" value="No" required name="offShore">No
  								<div class="has-error" ng-show="myForm.offShore.$dirty">
									<span ng-show="myForm.offShore.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">City</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.city"
									name="grade" class="username form-control input-sm"/> -->
								
								<select class="form-control" name="city"
									ng-options="city.value as city.value for city in cities"
									ng-model="ctrl.demandRequest.city" ng-select="ctrl.demandRequest.city" required>
								</select>
								<div class="has-error" ng-show="myForm.city.$dirty">
									<span ng-show="myForm.city.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Country</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.country"
									name="grade" class="username form-control input-sm"/> -->
									
								<select class="form-control" name="country"
									ng-options="country.value as country.value for country in countries"
									ng-model="ctrl.demandRequest.country" ng-select="ctrl.demandRequest.country" required>
								</select>
								<div class="has-error" ng-show="myForm.country.$dirty">
									<span ng-show="myForm.country.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project ID</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.demandRequest.projectID"
									name="projectID" class="username form-control input-sm" required/>
								<div class="has-error" ng-show="myForm.projectID.$dirty">
									<span ng-show="myForm.projectID.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.demandRequest.projectName"
									name="projectName" class="username form-control input-sm" required/>
								<div class="has-error" ng-show="myForm.projectName.$dirty">
									<span ng-show="myForm.projectName.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-6" ng-show="!checkRole('ROLE_DELIVERY_LEAD')">
							<label class="col-md-4 control-lable">Flagged for Hire</label>
							<div class="col-md-8">
								<input type="checkbox" ng-model="ctrl.demandRequest.flaggedForHire">
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6" ng-show="!checkRole('ROLE_DELIVERY_LEAD')">
							<label class="col-md-4 control-lable">Flagged for Hire Date</label>
							<div class="col-md-8">
      									<!-- <input ng-model="ctrl.demandRequest.reqStartDate" type="text" class="angular-datepicker-input"/> -->
      									<input type="date" ng-model="ctrl.demandRequest.flaggedForHireDate"
									name="grade" class="username form-control input-sm" value=""/>
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Technical Skills</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.technicalSkills"
								name="technicalSkills" class="username form-control input-sm"> -->
								
								<select class="form-control" name="technicalSkills"
									ng-options="skill.value as skill.value for skill in technicalSkills"
									ng-model="ctrl.demandRequest.technicalSkills" ng-select="ctrl.demandRequest.technicalSkills" required>
								</select>
								<div class="has-error" ng-show="myForm.technicalSkills.$dirty">
									<span ng-show="myForm.technicalSkills.$error.required" style="color: red">This is a required field</span>
								</div>								
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<!-- <div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requestor Name</label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.demandRequest.requestorName"
									name="requestorName" class="username form-control input-sm" required/>
									
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.requestorName.$error.required" style="color: red">This is a required field</span>
								</div>
							</div>
							
						</div> -->
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8">
								<!-- <input type="text" ng-model="ctrl.demandRequest.competency"
									name="grade" class="username form-control input-sm"/> -->
									
								<select class="form-control" name="competency"
									ng-options="competency.value as competency.value for competency in competencies"
									ng-model="ctrl.demandRequest.competency" ng-select="ctrl.demandRequest.competency" required>
								</select>
								<div class="has-error" ng-show="myForm.competency.$dirty">
								<span ng-show="myForm.competency.$error.required" style="color: red">This is a required field</span>
							</div>
							</div>
							
						</div>
							
					</div>
					
					</form>
						<div class="form-actions floatRight">
						<input type="button"
							value="{{!ctrl.appRequest.requestID ? 'Create' : 'Update'}}"
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
	<script src="<c:url value='/static/js/controller/masterdemand_controller.js' />"></script>
</body>


</html>