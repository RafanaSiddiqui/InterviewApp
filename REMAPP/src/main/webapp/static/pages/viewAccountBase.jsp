<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Account Base</title>

</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="BotController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Account Base </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.update()" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Id</label>
							<div class="col-md-8">
								{{accountBase.customerId}}							
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Vertical</label>
							<div class="col-md-8">
								{{accountBase.vertical}}
							</div>
						</div>
						
					</div>
						
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Account Name</label>
							<div class="col-md-8">
								{{accountBase.accountName}}
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Primary Location</label>
							<div class="col-md-8">
								{{accountBase.primaryLocation}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Competency Manager Id</label>
							<div class="col-md-8">
								{{accountBase.competencyManagerId}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Competency Manager</label>
							<div class="col-md-8">
								{{accountBase.competencyManager}}
							</div>
						</div>
						
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">BFD</label>
							<div class="col-md-4">
								{{accountBase.bfd}}
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">BTB</label>
							<div class="col-md-8">
      							{{accountBase.btb}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">BTM</label>
							<div class="col-md-4">
								{{accountBase.btm}}							
							</div>
							
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">NBL</label>
							<div class="col-md-8">
      							{{accountBase.nbl}}
							</div>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">ODC Details</label>
							<div class="col-md-8">
								{{accountBase.odcDetails}}
							</div>
						</div>
						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Teck Stack</label>
							<div class="col-md-8">
								{{accountBase.teckStack}}
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
	<script src="<c:url value='/static/js/service/account.base.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/account.base.controller.js' />"></script>
</body>


</html>