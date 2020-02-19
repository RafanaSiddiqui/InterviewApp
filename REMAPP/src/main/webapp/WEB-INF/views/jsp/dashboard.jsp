<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="./fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM Automation App - Dashboard</title>
<script src="<c:url value='/static/js/service/dashboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/dashboard_controller.js' />"></script>
</head>
<body ng-app="myApp" class="ng-cloak">

		<div class="container" ng-controller="MasterDashboardController as ctrl">
		
	<div class="row">
		<div class="col-md-3">
		<div class="panel panel-success">
		<div class="panel-heading">
			<span class="lead">Completed</span>
		</div>
		<div align="center">
			<h1>{{completedCount}}</h1>
		</div>
		<div>
		</div>
		</div>
		</div>
		<div class="col-md-3">
		<div class="panel panel-warning">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Pending</span>
		</div>
		<div align="center">
			<h1>{{pendingCount}}</h1>
		</div>
		<div>
		</div>
		</div>
		</div>
		
		<div class="col-md-3">
		<div class="panel panel-danger">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Failed</span>
		</div>
		<div align="center">
			<h1>{{failedCount}}</h1>
		</div>
		<div>
		</div>
		</div>
		</div>
		<div class="col-md-3">
		<div class="panel panel-info">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Reviewed</span>
		</div>
		<div align="center">
			<h1>{{reviewedCount}}</h1>
		</div>
		<div>
		</div>
		</div>
		
		</div>
	
	</div>
		
	<div class="row">
	<div class="col-md-6">
	<div class="panel panel-success">
		<!-- Default panel contents -->
		<div class="panel-heading" style="background-color: #efe6f2">
			<span class="lead">App Status and Count </span>
		</div>
		
		<div>
			<nvd3 options="pieChartOptions" data="pieChartData"></nvd3>
		</div>
		
	</div>
	</div>
	
	<div class="col-md-6">
	<div class="panel panel-success">
		<!-- Default panel contents -->
		<div class="panel-heading" style="background-color: #efe6f2">
			<span class="lead">Completed App Count</span>
		</div>
		<div>
		<nvd3 options="historicalBarChartOptions" data="historicalBarChartOptionsData"></nvd3>
		</div>
		</div>
	</div>
	</div>
</div>


</body>
<jsp:include page="./fragments/footer.jsp" />
</html>