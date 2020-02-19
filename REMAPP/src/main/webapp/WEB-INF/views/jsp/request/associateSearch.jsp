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
		ng-controller="AssociateSearchController as ctrl">
		
		 <!-- <form class="form-inline" ng-submit="searchBySoId(soId)">
        	<div class="form-group">
            	<input type="text" ng-model="soId" class="form-control" placeholder="Search By SOID">
            	<input type="button"
							value="Search"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"
							ng-click="searchBySoId(soId)">
        	</div>
    	</form> -->
    	<div class="panel panel-default">
    	<div class="panel-heading">
			Search Associates
		</div>
    	<div class="formcontainer">
    	<form >
    	<div class="row" style="padding-top: 30px">
        <div class="col-md-2">
            <input type="text" ng-model="associateDetails.associateID" class="form-control" placeholder="AssociateId">
        </div>
        <div class="col-md-2">
            <input type="text" ng-model="associateDetails.keySkills" class="form-control" placeholder="Key Skill">
        </div>
        <div class="col-md-2">
            <!-- <input type="text" ng-model="associateDetails.grade" class="form-control" placeholder="Grade"> -->
            <select class="form-control" name="grade" placeholder="Select Grade"
				ng-options="grade for grade in grades"
				ng-model="associateDetails.grade" ng-select="associateDetails.grade">
				<option value="" selected="selected">Select Grade</option>
			</select>
            
        </div>
        <div class="col-md-2">
            <input type="text" ng-model="associateDetails.location" class="form-control" placeholder="Location">
        </div>
        
        <div class="form-actions floatRight">
			<button type="button" ng-click="searchAssociates()"
				class="btn btn-primary btn-sm" ng-disabled="myForm.$pristine">Search
				<span  class="glyphicon glyphicon-search" aria-hidden="true"></span>
			</button>
			
		</div>
        
        </div>
    	</form>
		</div>
		</div>
		<!-- <div class="panel panel-default"> -->
			<!-- Default panel contents -->
			<div class="tablecontainer">
			
				<div class="row">
					<div class="col-md-4" dir-paginate="associates in associateList|orderBy:sortKey:reverse|filter:search|itemsPerPage:9">
					<div class="panel panel-success">
					<div class="panel-heading" align="justify">
						<span>{{associates.firstName}} {{associates.lastName}} ({{associates.associateID}})</span><br>
						<span>Grade: {{associates.grade}}</span><br>
					</div>
					<div align="justify" style="padding-left: 15px">
						<br>
						<span>Available From: {{associates.availableTime}}</span><br>
						<span>Current Location: {{associates.location}}</span><br>		
						
						<hr>
						<span>Key Skills: {{associates.keySkills}}</span><br>
						<span>Detailed Skills: {{associates.detailedSkills}}</span><br>
						<span>Vertical: {{associates.vertical}}</span><br>
						<span>Contact Number: {{associates.contactNumber}}</span><br>
					</div>
					<div>
					</div>
					</div>
					</div>
				</div>
			
			</div>
		<!-- </div> -->
	
		<div style="float: right;">
				<!-- <dir-pagination-controls
					max-size="10"
					direction-links="true"
					boundary-links="true" 
					on-page-change="ctrl.loadAllDemandPageableRequests(newPageNumber, pageableDemandRequest.size)">
				</dir-pagination-controls> -->
				
				<dir-pagination-controls
			       	max-size="10"
			       direction-links="true"
			       boundary-links="true" >
			    </dir-pagination-controls>
				
	    </div>
	
	</div>

	<script src="<c:url value='/static/js/service/associate_search_service.js' />"></script>
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/associate_search_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>