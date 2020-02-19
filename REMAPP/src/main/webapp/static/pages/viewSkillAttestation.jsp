<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Skill Attestation</title>

</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="SkillAttestationController as ctrl">
<!-- <div class="container" > -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Edit Skill Attestation </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.update()" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Supervisor ID</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.supervisorAssociateMapping.supervisorId}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Supervisor Name</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.supervisorAssociateMapping.supervisorName}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Associate ID</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.supervisorAssociateMapping.associateId}}
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Associate Name</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.supervisorAssociateMapping.associateName}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Grade</label>
							<div class="col-md-4">
								{{ctrl.skillAttestation.supervisorAssociateMapping.grade}}								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Location</label>
							<div class="col-md-8">
      							{{ctrl.skillAttestation.supervisorAssociateMapping.location}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Country</label>
							<div class="col-md-8">
      							{{ctrl.skillAttestation.supervisorAssociateMapping.region}}
							</div>
						</div>
						
						<!-- <div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Secondary Location</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.supervisorAssociateMapping.region}}
							</div>
						</div> -->
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Skills</label>
							<div class="col-md-7">

								<div class="tablecontainer">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Skill</th>
												<th>Proficiency</th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="skills in ctrl.skillAttestation.skillDetails">
												<td>{{skills.skill}}</td>
												<td>{{skills.proficiency}}</td>
											</tr>

										</tbody>
									</table>

								</div>
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
		<!-- </div> -->
		
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/skill.attestation.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/skill.attestation.controller.js' />"></script>
</body>


</html>