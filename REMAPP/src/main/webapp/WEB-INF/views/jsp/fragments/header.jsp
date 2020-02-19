<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<title>ip Security Interview Portal</title>

<link href="<c:url value='/resources/core/css/app.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/core/css/bootstrap.min.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/core/css/bootstrap.css' />" rel="stylesheet" />
<%--<link href="<c:url value='/resources/core/css/bootstrap-theme.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/core/css/bootstrap-theme.min.css' />" rel="stylesheet" /> --%>
<link href="<c:url value='/static/css/nv.d3.css' />" rel="stylesheet" />
<%-- <link href="<c:url value='/static/css/main.css' />" rel="stylesheet" /> --%>
<link href="<c:url value='/static/css/select.css' />" rel="stylesheet" />
<link href="<c:url value='/static/css/select2.css' />" rel="stylesheet" />

<script src="<c:url value='/static/lib/angular.js' />"></script>
<script src="<c:url value='/static/lib/angular-sanitize.js' />"></script>
<script src="<c:url value='/static/lib/jquery.min.js' />"></script>
<script src="<c:url value='/resources/core/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/static/lib/dirPagination.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/lib/d3.js' />"></script>
<script src="<c:url value='/static/lib/nv.d3.js' />"></script>
<script src="<c:url value='/static/lib/angular-nvd3.js' />"></script>
<script src="<c:url value='/static/lib/angular-nvd3.min.js' />"></script>
<script src="<c:url value='/static/lib/angular-fusioncharts.js' />"></script>
<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>
<script src="<c:url value='/static/lib/xeditable.js' />"></script>
<script src="<c:url value='/static/lib/select.js' />"></script>
<script src="<c:url value='/static/lib/checklist-model.js' />"></script>
<script src="<c:url value='/static/lib/angular-filter.js' />"></script>
<script src="<c:url value='/static/js/controller/manageuser_controller.js' />"></script>
<script src="<c:url value='/static/js/service/userdetails_service.js' />"></script>

</head>

<body ng-app="myApp"  class="ng-cloak" ng-controller="UserController as ctrl">
<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlAddUser" />

<div class="generic-container">
	<div class="row">

		<div class="col-xs-2">

			<a href="${pageContext.request.contextPath}/"> <img class="logo1"
				src="${pageContext.request.contextPath}/static/img/ip_logo.png"></a>
		</div>
		<div class="col-xs-6">
			<h2>
				ip Security Interview Portal
			</h2>
		</div>
		
		<div class="col-xs-2">
			<h2>
				<small>Welcome {{fullName}}</small><br>
			</h2>
		</div>

		<div class="col-xs-2">
			<h3>
				<a href="javascript:document.getElementById('logout').submit()" class="btn btn-info btn-xs">
		          <span class="glyphicon glyphicon-log-out"></span> Log out
		        </a>
			</h3>
		</div>
		
	</div>
</div>





<nav class="navbar navbar-inverse">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-collapse-3">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> </a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-collapse-3">
			<ul class="nav navbar-nav navbar-right">
				
				<!-- <li class="dropdown" ng-if="userRole == 'ROLE_ADMIN' || userRole != 'ROLE_BOTS'"> 
				{{userRolesList}} -- userRolesList
				{{userRolesList | some: 'roleName.indexOf(\'ROLE_NOBOT\') === 0'}} -- Test
				{{ array | contains: 2 }} -- Test -->
				<!-- <li class="dropdown" ng-show="{{userRolesList | some: 'roleName.indexOf(\'ROLE_NOBOT\') === 0'}}"> -->
				
				<li class="dropdown" ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_TSC')||checkRole('ROLE_CTL')||checkRole('ROLE_PANEL')||checkRole('ROLE_COMPTEAM')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Interview Portal<b class="caret"></b></a>
		            <ul class="dropdown-menu">
			             <li ng-show="checkRole('ROLE_ADMIN')">
							<a href="${pageContext.request.contextPath}/interview/admin/panel">Admin:Panel</a>
						</li >
			             <li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_TSC')">
							<a href="${pageContext.request.contextPath}/interview/hr/getRequirementDetails">HR:Create Requirement</a>
						</li >
			            <li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_TSC')">
							<a href="${pageContext.request.contextPath}/interview/hr/getRRDetails">HR:Upload Open RR</a>
						</li>
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_TSC')">
							<a href="${pageContext.request.contextPath}/interview/panel/HRDashboard">HR:HR Dashboard</a>
						</li>
						
			            <li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_CTL')">
							<a href="${pageContext.request.contextPath}/interview/compLead/showUpdateJDforL1">CTL:JD for L1</a>
						</li>
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_CTL')">
							<a href="${pageContext.request.contextPath}/interview/compLead/viewRRJd">CTL:JD For RR</a>
						</li>
			            <li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_CTL') ">
							<a href="${pageContext.request.contextPath}/interview/compLead/interviewPanelMapping">CTL:Interview Panel Mapping</a>
						</li>
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_CTL')">
							<a href="${pageContext.request.contextPath}/interview/compLead/viewPanelStatus">CTL:View Panel Status</a>
						</li>
						
						
						
						
						<li ng-show="checkRole('ROLE_PANEL')">
							<a href="${pageContext.request.contextPath}/interview/compLead/interviewPanelMapping">Self Panel Mapping</a>
						</li>
						
						
						
						
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_PANEL')">
							<a href="${pageContext.request.contextPath}/interview/panel/L1Dashboard">Panel:L1 Dashboard</a>
						</li>
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_PANEL')">
							<a href="${pageContext.request.contextPath}/interview/panel/L2Dashboard">Panel:L2 Dashboard</a>
						</li>
						
						<li ng-show="checkRole('ROLE_COMPTEAM')">
							<a href="${pageContext.request.contextPath}/interview/admin/panel">CompTeam:Panel Upload</a>
						</li >
						<li ng-show="checkRole('ROLE_COMPTEAM')">
							<a href="${pageContext.request.contextPath}/interview/compLead/viewPanelStatus">CompTeam:View Panel Status</a>
						</li>						
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_COMPTEAM')">
							<a href="${pageContext.request.contextPath}/interview/compTeam/candidateDashboard">CompTeam:Candidate Dashboard</a>
						</li>
						<li ng-show="checkRole('ROLE_ADMIN')||checkRole('ROLE_COMPTEAM')">
							<a href="${pageContext.request.contextPath}/interview/compTeam/toppanel">CompTeam:Top Panelist</a>
						</li>
						
					</ul>
        		</li>
				
				<%-- <li class="dropdown" ng-show="checkRole('ROLE_ADMIN') && (!checkRole('ROLE_BOTS') || !checkRole('ROLE_SUPERVISOR') || !checkRole('ROLE_TSC') || !checkRole('ROLE_CTL') || !checkRole('ROLE_PANEL') || !checkRole('ROLE_COMPTEAM'))">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Master Demand<b class="caret"></b></a>
		            <ul class="dropdown-menu">
			            <li ng-if="!checkRole('ROLE_BOTS')">
							<a href="${pageContext.request.contextPath}/masterDemand">Master Demand Request</a>
						</li>
			            <li ng-if="checkRole('ROLE_ADMIN')">
							<a href="${pageContext.request.contextPath}/masterDashboard">Master Dashboard</a>
						</li>
						<li ng-if="!checkRole('ROLE_BOTS')">
							<a href="${pageContext.request.contextPath}/dashboards">Dashboards</a>
						</li>
					</ul>
        		</li>
        		
        		<li class="dropdown" ng-if="!checkRole('ROLE_BOTS') && !checkRole('ROLE_SUPERVISOR') && !checkRole('ROLE_TSC') && !checkRole('ROLE_CTL') && !checkRole('ROLE_PANEL') && !checkRole('ROLE_COMPTEAM')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Associate Details<b class="caret"></b></a>
		            <ul class="dropdown-menu">
		            <li ng-if="!checkRole('ROLE_DELIVERY_LEAD') && !checkRole('ROLE_BOTS')">
						<a href="${pageContext.request.contextPath}/bulkRequestUpload">Associate Details Feed</a>
					</li>
					
					<li ng-if="!checkRole('ROLE_BOTS')">
						<a href="${pageContext.request.contextPath}/associateSearch">Associate Search</a>
					</li>
					</ul>
        		</li> --%>
				
			    <li class="dropdown" ng-if="checkRole('ROLE_ADMIN')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Manage Users <b class="caret"></b></a>
		            <ul class="dropdown-menu">
		                <li ><a href="${pageContext.request.contextPath}/addNewUser">Add New User</a></li>
		                <li><a href="${pageContext.request.contextPath}/findUser">Find Existing User</a></li>
		            </ul>
        		</li>	
        		
        		<%-- <li class="dropdown" ng-if="checkRole('ROLE_ADMIN') || checkRole('ROLE_BOTS')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Bots<b class="caret"></b></a>
		            <ul class="dropdown-menu">
		                <li ><a href="${pageContext.request.contextPath}/addBot">Register Bot</a></li>
		                <li><a href="${pageContext.request.contextPath}/showAllBots">View Bots</a></li>
		                <li><a href="${pageContext.request.contextPath}/showAllNoBots">No Bot Declaration Form</a></li>
		            </ul>
        		</li> --%>
        		
        		<%-- <li class="dropdown" ng-if="checkRole('ROLE_ADMIN') || checkRole('ROLE_BOTS')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Bots Measurements<b class="caret"></b></a>
		            <ul class="dropdown-menu">
		                <li ><a href="${pageContext.request.contextPath}/createBotMeasurement">Add Measurement</a></li>
		                <li><a href="${pageContext.request.contextPath}/showAllMeasurements">View Measurements</a></li>
		                <li><a href="${pageContext.request.contextPath}/botDashboards">Reports</a></li>
		                <li><a href="${pageContext.request.contextPath}/defaulterList">Defaulter List</a></li>
		            </ul>
        		</li> --%>
        		
        	<%-- 	<li class="dropdown" ng-if="checkRole('ROLE_ADMIN')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Automation<b class="caret"></b></a>
		            <ul class="dropdown-menu">
		                <li><a href="${pageContext.request.contextPath}/showAllAccountBase">AccountBase</a></li>
		                <li><a href="${pageContext.request.contextPath}/showAllBulkAccountBase">AccountBase - Bulk</a></li> 
		                <li><a href="${pageContext.request.contextPath}/showAllTracker">Tracker</a></li>
		                <li><a href="${pageContext.request.contextPath}/showAllIAMFunctionalSpread">Functional Spread</a></li>
		            </ul>
        		</li> --%>
        		
	  			<%-- <li ng-if="userRole  == 'ROLE_ADMIN'">
	  				<a href="${pageContext.request.contextPath}/resetPassword">Reset Password</a>
	  			</li> --%>
				
				<%-- <li ng-if="checkRole('ROLE_ADMIN') || checkRole('ROLE_SUPERVISOR')">
					<a href="${pageContext.request.contextPath}/showAllSkillAttestation">Skill Attestation</a>
				</li> --%>
				
			<%-- 	<li class="dropdown" ng-if="checkRole('ROLE_ADMIN')">
            		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Settings<b class="caret"></b></a>
		            <ul class="dropdown-menu">
		            <li ng-if="checkRole('ROLE_ADMIN')">
						<a href="${pageContext.request.contextPath}/Configuration">Configurations</a>
					</li>
					<li ng-if="checkRole('ROLE_ADMIN')">
						<a href="${pageContext.request.contextPath}/emailTemplate">Email Template</a>
					</li>	
		            </ul>
        		</li> --%>
				
				
			</ul>
		</div>
		<form id="logout" action="logout" method="post" >
	  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
<!-- /.navbar -->




</body>
</html>

 