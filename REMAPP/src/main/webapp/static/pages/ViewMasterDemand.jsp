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
				<span class="lead"> View Master Demand </span>
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
							<div class="col-md-8">
								{{demandRequest.soId}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Billability</label>
							<div class="col-md-8">
								{{demandRequest.billability}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requirement Start Date</label>
							{{demandRequest.reqStartDate}}
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Priority</label>
							<div class="col-md-8">
								{{demandRequest.priority}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Grade</label>
							<div class="col-md-8">
								{{demandRequest.grade}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">PDP</label>
							<div class="col-md-8">
								{{demandRequest.pdp}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Vertical</label>
							<div class="col-md-8">
								{{demandRequest.vertical}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Account Name</label>
							<div class="col-md-8">
								{{demandRequest.accountName}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Offshore</label>
							<div class="col-md-8">
								{{demandRequest.offShore}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">City</label>
							<div class="col-md-8">
								{{demandRequest.city}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Country</label>
							<div class="col-md-8">
								{{demandRequest.country}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project ID</label>
							<div class="col-md-8">
								{{demandRequest.projectID}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Project Name</label>
							<div class="col-md-8">
								{{demandRequest.projectID}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Flagged for Hire</label>
							<div class="col-md-8">
								{{demandRequest.flaggedForHire}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Flagged for Hire Date</label>
							<div class="col-md-8">
								{{demandRequest.flaggedForHireDate}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Technical Skills</label>
							<div class="col-md-8">
								{{demandRequest.technicalSkills}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requestor Name</label>
							<div class="col-md-8">
								{{demandRequest.requestorName}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Requestor ID</label>
							<div class="col-md-8">
								{{demandRequest.requestorID}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Competency</label>
							<div class="col-md-8">
								{{demandRequest.competency}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Status</label>
							<div class="col-md-8">
								{{demandRequest.status}}
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
		</div>

	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/masterdemand_controller.js' />"></script>
</body>


</html>