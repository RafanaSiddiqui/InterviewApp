<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Skill Attestation</title>

</head>
<body ng-app="myApp" class="ng-cloak">
<div class="container" ng-controller="SkillAttestationController as ctrl" ng-init="editSkillAttestationById('${fn:escapeXml(skillAttestationId)}');loadSkillDetails('${fn:escapeXml(associateId)}')">
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
								{{ctrl.skillAttestation.supervisorId}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Supervisor Name</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.supervisorName}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Associate ID</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.associateId}}
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Associate Name</label>
							<div class="col-md-8">
								{{ctrl.skillAttestation.associateName}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Grade</label>
							<div class="col-md-4">
								{{ctrl.skillAttestation.grade}}								
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Location</label>
							<div class="col-md-8">
      							{{ctrl.skillAttestation.location}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Country</label>
							<div class="col-md-8">
      							{{ctrl.skillAttestation.region}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">RDP Skills</label>
							<div class="col-md-10">
      							{{ctrl.skillDetailsList[0].skill}}
      							<!-- <li ng-repeat="fskill in feededSkillList">
      								{{fskill}}
      							</li> -->
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Skills</label>
							<div class="col-md-7">

								<div class="tablecontainer">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Associate Skill</th>
												<!-- <th>Matched Skill</th> -->
												<th>Proficiency</th>
												<th width="20%">

												<!-- <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
													ng-click="addSkill()"></span> -->
												<button type="button" ng-click="addSkill()"
													class="btn btn-success btn-sm">Add Skills</button>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr data-ng-repeat="skills in ctrl.skillDetailsList">
												<!-- <td>
													<span style="width: 250px">{{skills.skill}}</span>
													
													<input type="text" ng-model="skills.skill"
														name="skillActual" class="form-control input-sm"
														placeholder="Enter Skill" style="width: 250px" ng-disabled="true"/>
												</td> -->
												<td ng-if="skills.selectSkill!='Others'">
												<select class="form-control" name="skill"
													ng-options="aList.value as aList.value for aList in associateSkillsList"
													ng-model="skills.selectSkill" ng-select="skills.selectSkill" style="width: 250px">
												</select>
												
												<!-- <ui-select ng-model="associateSkill.selected" theme="bootstrap" style="min-width: 300px;"
									               class="btn-group bootstrap-select"
									               append-to-body="true"
									               search-enabled="true"
									               ng-required="true" ng-change="onSelectSkill(associateSkill.selected)">
									      		<ui-select-match placeholder="Select Skill">
									        	{{$select.selected.value}}
									      		</ui-select-match>
									      		<ui-select-choices repeat="associateSkill in associateSkillsList | filter: $select.search">
									        	<span ng-bind-html="associateSkill.value"></span>
									      		</ui-select-choices>
									    		</ui-select> -->
												
												</td>
												<td ng-if="skills.selectSkill=='Others'">
													<input type="text" ng-model="skills.skillActual"
														name="skillActual" class="form-control input-sm"
														placeholder="Enter Skill" style="width: 250px"/>
												</td>
												<td>
												<select class="form-control" name="skill"
													ng-options="pList.value as pList.value for pList in proficiencyList"
													ng-model="skills.proficiency" ng-select="skills.proficiency" style="width: 150px">
												</select>
												<!-- <input type="text" ng-model="skills.proficiency" name="proficiency" placeholder="Proficiency" style="width: 100px"> -->
												</td>
												<td width="20%">
												<input type="hidden" ng-model="skills.associateId"
														name="skillActual" class="form-control input-sm" ng-value="ctrl.skillAttestation.associateId"/>
														
												<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 					ng-click="removeSkill(skills.id)"  ng-show="$last"></span>
												</td>
											</tr>

										</tbody>
									</table>

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
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm">Cancel</button>
					</div>
					</div>
					
				
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
		
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/skill.attestation.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/skill.attestation.controller.js' />"></script>
</body>


</html>