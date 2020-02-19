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
<title>IAM BOTS</title>

</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="NoBotController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">View No Bot Declaration Form </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit(myForm)" name="myForm"
					class="form-horizontal">
					
					<div class="row col-md-12">
					<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Customer Name</label>
							<div class="col-md-8">
								{{ctrl.noBot.customerName}}
							</div>
							
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Project Name</label>
							<div class="col-md-8">
								{{ctrl.noBot.projectName}}
							</div>
						</div>
					</div>
					<!-- <div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Is Bot Available</label>
							<div class="col-md-8">
								{{ctrl.noBot.available}}
							</div>
						</div>
						
					</div> -->					

					<div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Comments</label>
							<div class="col-md-10">
								{{ctrl.noBot.comments}}
							</div>
						</div>
					</div>
					
					<!-- <div class="row col-md-12">
					<div class="form-group col-md-12">
							<label class="col-md-4 control-lable">Click the check box to declare</label>
							<div class="col-md-8" >
								<input type="checkbox">
							</div>						
						</div>
					</div> -->
					
					</form>
						<div class="form-actions floatRight">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm">OK</button>
					</div>
					</div>
					
			</div>
		
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/nobot.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/nobot.controller.js' />"></script>
</body>


</html>