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
	<div class="container"
		ng-controller="BotMeasurementController as ctrl">
		
    	<div class="panel panel-default">
    	<div class="panel-heading">
			Defaulters List - {{botMonth | date : 'MMM yyyy'}}
		</div>
    	<div class="formcontainer">
    	<form >
    	<div class="row" style="padding-top: 30px">
        <div class="col-md-4">
            <input type="month" ng-model="botMonth" class="form-control" ng-change="getDefaulterList(botMonth)" placeholder="Select Month">
        </div>
        <!-- <div class="col-md-4">
        <div class="form-actions floatRight">
			<button type="button" ng-click="getDefaulterList(botMonth)"
				class="btn btn-primary btn-sm" ng-disabled="myForm.$pristine">Submit
				<span  class="glyphicon glyphicon-search" aria-hidden="true"></span>
			</button>
			
		</div>
        </div> -->
        
        <div class="col-md-6" ng-if="compManList.length>0">
        <div class="pull-right">
            <button type="button" ng-click="botListDownLoad(botMonth)"
				class="btn btn-primary btn-sm" ng-disabled="myForm.$pristine">Download {{botMonth | date : 'MMM'}} List
				<span  class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
			</button>
		</div>
        </div>
        
        </div>
    	</form>
		</div>
		</div>
		<!-- <div class="panel panel-default"> -->
			<!-- Default panel contents -->
			<div class="tablecontainer">
			
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Sl NO#</th>
							<th>Customer Name</th>
							<th>Competency Manager</th>
							<th>Month</th>
							</tr>
					</thead>
					<tbody>
					
						<tr ng-show="compManList <= 0"><td colspan="5" style="text-align:center;">No Records!!</td></tr>
					
						<!-- <tr dir-paginate="d in pageableBotMeasurements|itemsPerPage:pageableBotMeasurements.size" total-items="pageableBotMeasurements.totalElements"> -->
						<tr ng-repeat="c in compManList">
							<td><span ng-bind="{{$index + 1}}"></span></td>
							<td><span ng-bind="c.name"></span></td>
							<td><span ng-bind="c.value"></span></td>
							<td>{{botMonth | date : 'MMM yyyy'}}</td>
						</tr>
					</tbody>
				</table>
			
			</div>
		<!-- </div> -->
	
	</div>

	<script src="<c:url value='/static/js/service/bot_service.js' />"></script>
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/bot_measurement_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/bot_measurement_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>