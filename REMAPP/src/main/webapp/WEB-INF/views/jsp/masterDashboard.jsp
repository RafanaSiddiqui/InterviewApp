<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAM Automation App - Dashboard</title>
<script src="<c:url value='/static/js/service/master.dashboard.service.js' />"></script>
<script src="<c:url value='/static/js/controller/master.dashboard.controller.js' />"></script>
</head>
<body ng-app="myApp" class="ng-cloak">

		<div class="container" ng-controller="MasterDashboardController as ctrl">
		
	<!-- <div class="row">
		<iframe name="myIframe" width="100%" height="850px" style="overflow-y:scroll !important; overflow-x:hidden !important;" frameborder="0" ng-src="{{trustSrc(baseUrl)}}" scrolling="auto"></iframe>
	</div> -->
	
	
	<div class="panel panel-default">
    	<div class="panel-heading">
			Master Dashboard
		</div>
	<div class="row" style="padding-top: 10px;padding-left: 5px;">
	<div class="col-md-12">
		<iframe name="myIframe" width="90%" height="850px" style="overflow-y:scroll !important; overflow-x:hidden !important;" frameborder="0" ng-src="{{trustSrc(baseUrl)}}" scrolling="auto"></iframe>
		</div>
	</div>
	</div>
		
</div>


</body>
<jsp:include page="fragments/footer.jsp" />
</html>