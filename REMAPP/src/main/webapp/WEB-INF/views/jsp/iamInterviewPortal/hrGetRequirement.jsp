<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />
<head>
<title>HR Requirement Details</title>
	<script src="<c:url value='/static/js/directives/filemodel.js' />"></script>
	<script	src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.jobdetails.controller.js'/>"></script>
	<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.job.service.js'/>"></script>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="GetRequirementController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading"  style="background-color: #efe6f2">
				<span class="lead">Create Requirement Details</span>
			</div>
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="formcontainer">
				<form name="myForm" ng-submit="ctrl.submit()" class="form-horizontal">
				   <div ng-if="errorMessage" style="color:red"><b>{{errorMessage}}</b></div>
				   <div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Skill:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text"
									ng-model="ctrl.interviewjobdetails.skill" name="skill"	class="username form-control input-sm " placeholder="Enter Skill" ng-minlength="3"  required />
								<div class="has-error" ng-show="myForm.skill.$dirty">
									<span ng-show="myForm.skill.$error.required" style="color:red">This is a required field.</span>
									<span ng-show="myForm.skill.$error.minlength" style="color:red">Minimum length required is 3.</span>
									<span ng-show="myForm.skill.$invalid" style="color:red">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12" ">
							<label class="col-md-2 control-lable" for="file">Skill Owner:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text"
									ng-model="ctrl.interviewjobdetails.skillOwner" name="skillOwner"	class="username form-control input-sm" placeholder="Enter Skill Owner" ng-minlength="3"  required />
								<div class="has-error" ng-show="myForm.skillOwner.$dirty">
									<span ng-show="myForm.skillOwner.$error.required" style="color:red">This is a required field.</span>
									<span ng-show="myForm.skillOwner.$error.minlength" style="color:red">Minimum length required is 3.</span>
									<span ng-show="myForm.skillOwner.$invalid" style="color:red">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12" ">
							<label class="col-md-2 control-lable" for="file">Level:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text"
									ng-model="ctrl.interviewjobdetails.level" name="level"	class="username form-control input-sm" placeholder="Enter Level"  required />
								<div class="has-error" ng-show="myForm.level.$dirty">
									<span ng-show="myForm.level.$error.required" style="color:red">This is a required field.</span>
									<span ng-show="myForm.level.$error.minlength" style="color:red">Minimum length required is 3.</span>
									<span ng-show="myForm.level.$invalid" style="color:red">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Job Location:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.interviewjobdetails.jobLocation" class="form-control input-sm" name="joblocation" placeholder="Enter Job Location" ng-minlength="3"  required/>
								<div class="has-error" ng-show="myForm.joblocation.$dirty">
									<span ng-show="myForm.joblocation.$error.required" style="color:red">This is	a required field</span>
									<span ng-show="myForm.joblocation.$error.minlength" style="color:red">Minimum length required is 3.</span> 
									<span ng-show="myForm.joblocation.$invalid" style="color:red">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>

				<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Drive Location:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.interviewjobdetails.location" class="form-control input-sm" name="location" placeholder="Enter Drive Location" ng-minlength="3"  required />
								<div class="has-error" ng-show="myForm.location.$dirty">
									<span ng-show="myForm.location.$error.required" style="color:red">This is	a required field</span>
									<span ng-show="myForm.location.$error.minlength" style="color:red">Minimum length required is 3.</span> 
									<span ng-show="myForm.location.$invalid" style="color:red">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Experience:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control  input-sm" name="experience" ng-model="ctrl.interviewjobdetails.experience" placeholder="Enter Experience" required />
								<div class="has-error" ng-show="myForm.experience.$dirty">
									<span ng-show="myForm.experience.$error.required" style="color:red">This is a required field.</span> 
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Targeted Footfall:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control  input-sm" name="footFall" ng-model="ctrl.interviewjobdetails.footFall" placeholder="Enter FootFall Details" required/>
								<div class="has-error" ng-show="myForm.footFall.$dirty">
									<span ng-show="myForm.footfall.$error.required" style="color:red">This is a required field.</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Panel:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control  input-sm" name="panelsCount" ng-model="ctrl.interviewjobdetails.panelsCount" placeholder="Enter Panel Details" required/>
								<div class="has-error" ng-show="myForm.panelsCount.$dirty">
									<span ng-show="myForm.panelsCount.$error.required" style="color:red">This is a required field.</span> 
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">No Of Requirement:</label>
							<div class="col-md-7">
								<input type="text" class="form-control  input-sm" name="reqCount" ng-model="ctrl.interviewjobdetails.reqCount" placeholder="Enter No Of Requirement" />
								<div class="has-error" ng-show="myForm.reqCount.$dirty">
									<span ng-show="myForm.reqCount.$error.required" style="color:red">This is a required field.</span> 
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Drive Date:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="date" class="form-control  input-sm" name="driveDate" ng-model="ctrl.interviewjobdetails.driveDate" placeholder="Enter Drive Date" required/>
								<div class="has-error" ng-show="myForm.driveDate.$dirty">
									<span ng-show="myForm.drivedate.$error.required" style="color:red">This is a required field.</span> 
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Submit" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
						</div>
					</div>

					<br>
					
				</form>
				
				<form name="myFileForm" ng-submit="ctrl.upload()" class="form-horizontal">
				
					<div  class="form-horizontal">
						<label class="control-label col-sm-4 col-xs-12" for="file">Upload Bulk Requirement :(*.xlsx File) <span class="required" style="color:red">*</span>
						</label>
						<div class="form-actions col-xs-4 input-max controls ">
							<input id="fileSelector" class="inline-block" type="file" file-model="myFile"/>
						</div>
						<div class="form-actions col-xs-4 input-max controls ">
							<input type="submit" value="Upload" class="btn btn-primary btn-sm">
						</div>
					</div>
				</form>
					<div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>

					<table class="table table-striped">
						<thead>
							<tr>
								<th>Skill
								</th>
								<th>Skill Owner
								</th>
								<th>Level
								</th>
								<th>Job Location
								</th>
								<th>Drive Location
								</th>
								<th>No Of Requirement
								</th>
								<th>Experience
								</th>
								<th>Footfall
								</th>
								<th>Panel
								</th>
								<th>Drive Date
								</th>
								<th>Upload CV
								</th>
								<th></th>								
							</tr>
						</thead>
						<tbody>
							<tr ng-show="pageableJobDetailsPanel.length <= 0"><td colspan="11" style="text-align:center;">No Data Available!!</td></tr>
						<!-- 	<tr ng-repeat="d in pageableJobDetailsPanel"> -->
							<tr dir-paginate="d in pageableJobDetailsPanel|orderBy:sortKey:reverse|itemsPerPage:10">
								<td><span ng-bind="d.skill"></span></td>
								<td><span ng-bind="d.skillOwner"></span></td>
								<td><span ng-bind="d.level"></span></td>
								<td><span ng-bind="d.jobLocation"></span></td>
								<td><span ng-bind="d.location"></span></td>
								<td><span ng-bind="d.reqCount"></span></td>
								<td><span ng-bind="d.experience"></span></td>
								<td><span ng-bind="d.footFall"></span></td>
								<td><span ng-bind="d.panelsCount"></span></td>
								<td><span ng-bind="d.driveDate"></span></td>								
								<td>
								<span input type="submit" value="Upload" ng-click="ctrl.uploadCV(d.jobdescId)" class="btn btn-primary btn-sm">Upload CV</span>								
								</td>
								<td>
									<span class="glyphicon glyphicon-remove" aria-hidden="true"
										style="color: red" ng-click="ctrl.deleteJD(d.jobdescId)">
									</span>
								</td>
							</tr>
							
						</tbody>
					</table>
					<div style="float: right;">
							
							<dir-pagination-controls
						       	max-size="10"
						       direction-links="true"
						       boundary-links="true" >
						    </dir-pagination-controls>
							
						</div>
				
			</div>
		</div>
	<jsp:include page="../fragments/footer.jsp" />
	</div>
	
</body>


</html>