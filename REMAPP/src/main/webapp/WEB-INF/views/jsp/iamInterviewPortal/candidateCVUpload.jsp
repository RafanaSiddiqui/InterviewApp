<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />
<head>
<title>Candidate Resume Upload</title>
	<%-- <script src="<c:url value='/static/lib/angular.js' />"></script>
	<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>--%>
	<script src="<c:url value='/static/js/directives/filemodel.js' />"></script> 
	<script	src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.cvupload.controller.js'/>"></script>
	<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.cvupload.service.js'/>"></script>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="container" ng-controller="UploadCandidateCVController as ctrl" ng-init="uploadCandidateCVById('${fn:escapeXml(jobDetailId)}')">
		<div class="panel panel-default">
			<div class="panel-heading"  style="background-color: #efe6f2">
				<span class="lead">Candidate Resume Upload</span>
			</div>
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="formcontainer">
				<form name="myForm" ng-submit="ctrl.uploadCV('${fn:escapeXml(jobDetailId)}')" class="form-horizontal">
				 <div ng-if="errorMessage" style="color:red"><b>{{errorMessage}}</b></div>
				   <div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Skill:<span style="color:red">*</span></label>
							<div class="col-md-7">
								 <input type="text"
									ng-model="ctrl.jobdetails.skill" name="skill"	class="username form-control input-sm" placeholder="Enter Skill" ng-minlength="3"  required />
								<div class="has-error" ng-show="myForm.skill.$dirty">
									<span ng-show="myForm.skill.$error.required" style="color:red">This is a required field.</span>
									<span ng-show="myForm.skill.$error.minlength" style="color:red">Minimum length required is 3.</span>
									<span ng-show="myForm.skill.$invalid" style="color:red">This field is invalid. </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Targeted Footfall:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control  input-sm" name="footFall" ng-model="ctrl.jobdetails.footFall" placeholder="Enter FootFall Details" required /> 
								<div class="has-error" ng-show="myForm.footFall.$dirty">
									<span ng-show="myForm.footFall.$error.required" style="color:red">This is a required field.</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">No of CV Attached:<span style="color:red">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control  input-sm" name="cvCount" ng-model="ctrl.jobdetails.cvCount" placeholder="Enter Panel Details" required />
								<div class="has-error" ng-show="myForm.cvCount.$dirty">
									<span ng-show="myForm.cvCount.$error.required" style="color:red">This is a required field.</span> 
								</div>
							</div>
						</div>
					</div>

					<div  class="form-horizontal">
						<label class="control-label col-sm-4 col-xs-12" for="file">Upload CV :(*.zip file)<span class="required" style="color:red">*</span>
						</label>
						<div  class="col-xs-4 input-max controls ">
							<input id="fileSelector" class="inline-block" type="file" file-model="myFile" />
						</div>
					</div>
					<div>
							<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
					</div>
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Submit" class="btn btn-primary btn-sm"	ng-disabled="myForm.$invalid" >
							<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" >Back</button>
						</div>
					</div>
					<div>
						<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID
								</th>
								<th>Skill
								</th>
								<th>Candidate CV(zip)
								</th>
								<th>No Of CV Attached
								</th>
								<th></th>	
							</tr>
						</thead>
						<tbody>
								<tr ng-show="ctrl.jobDetailsCV.fileName">					
								<td><span ng-bind="{{$index + 1}}"></span></td>
								<td><span ng-bind="ctrl.jobDetailsCV.skill"></span></td>
								<td><span ><a href ng-click="fnDownLoad(ctrl.jobDetailsCV.jobdescId)">{{ctrl.jobDetailsCV.fileName}}</a></span></td>
								<td><span ng-bind="ctrl.jobDetailsCV.cvCount"></span></td>
								<td>
									<span class="glyphicon glyphicon-remove" aria-hidden="true"
										style="color: red" ng-click="ctrl.deleteUploadedCV(ctrl.jobDetailsCV.jobdescId)">
									</span>
								</td>
								</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>
</html>