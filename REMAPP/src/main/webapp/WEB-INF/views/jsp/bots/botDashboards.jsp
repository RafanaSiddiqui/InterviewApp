<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM Automation App - Dashboard</title>
<script src="<c:url value='/static/js/service/bot.dashboard.service.js' />"></script>
<script src="<c:url value='/static/js/controller/bot.dashboard.controller.js' />"></script>
</head>
<body ng-app="myApp" class="ng-cloak">

		<div ng-controller="BotDashboardController as ctrl">
	
	
	<div class="container panel panel-default">
    	<!-- <div class="panel-heading">
			Dashboards
		</div> -->
    	<div class="formcontainer">
    	<form >
    	<div class="row">
    	<div class="col-md-2">
    		Select Dashboard
    	</div>
        <div class="col-md-6">
            <!-- <input type="text" ng-model="associateDetails.grade" class="form-control" placeholder="Grade"> -->
            <select class="form-control" name="grade" placeholder="Select Dashboard"
				ng-options="dashboard as dashboard.name for dashboard in dashboards"
				ng-model="dashboard" ng-select="dashboard" ng-change="showDashboard(dashboard)">
				<!-- <option value="" selected="selected">Select Dashboard</option> -->
			</select>
            
        </div>
        </div>
    	</form>
		</div>
		</div>
	
	<div class="panel panel-default">
    	<div class="panel-heading">
			{{dashboard.name}}
		</div>
	<div class="row" style="padding-top: 10px;padding-left: 5px;">
	<div class="col-md-12">
		<iframe name="myIframe" width="100%" height="850px" style="overflow-y:scroll !important; overflow-x:hidden !important;" frameborder="0" ng-src="{{dashBoardUrl}}" scrolling="auto"></iframe>
		</div>
	</div>
	</div>
</div>


</body>
<jsp:include page="../fragments/footer.jsp" />
</html>