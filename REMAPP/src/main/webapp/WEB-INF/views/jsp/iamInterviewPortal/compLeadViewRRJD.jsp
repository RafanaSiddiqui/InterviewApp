<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
	<script	src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.viewrrjd.controller.js'/>"></script>
	<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.job.service.js'/>"></script>
	<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.openrr.service.js'/>"></script>
	<script	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.cvupload.service.js'/>"></script>
	
<title>ERSS Resource Manager</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="ViewRRJDController as ctrl" ng-init="updateRRForJdId('${fn:escapeXml(jobDetailId)}')">
		
	<form class="row">
	 	<div class="panel-heading">
            <label class="lead" >Update JD For Requirement</label>
        </div>
       </form>
	        
    	<div class="panel panel-default" ng-show="jdPanel1">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">Interviews Scheduled:</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			 		<div class="tablecontainer" >
			 			<table class="table table-striped">
			 				<thead>
			 					<tr>
			 						<th style="text-align:center;">#</th>
					 				<th style="text-align:center;">JOB_ID</th>
									<th style="text-align:center;">SKILL</th>
									<th style="text-align:center;">SKILL_OWNER</th>
									<th style="text-align:center;">LEVEL</th>
									<th style="text-align:center;">JOB LOCATION</th>
									<th style="text-align:center;">DRIVE LOCATION</th>
									<th style="text-align:center;">DRIVE DATE</th>
									<th style="text-align:center;">REQUIREMENT COUNT</th>
									<th style="text-align:center;">NO OF CANDIDATE</th>
			 					</tr>
			 				</thead>
			 				<tbody>
				 				
								<tr ng-show ="ctrl.jobdetailsPanel.jobdescId">
									<td style="text-align:center;"><input name ="radio1" type="radio" ng-click="onSelectRadio(ctrl.jobdetailsPanel)" ng-value="ctrl.jobdetailsPanel"></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.jobdescId"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.skill"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.skillOwner"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.level"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.jobLocation"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.location"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.driveDate"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.reqCount"></span></td>
									<td style="text-align:center;"><span ng-bind="ctrl.jobdetailsPanel.footFall"></span></td>
								</tr>
			 				</tbody>
			 			</table>
			 			
			 		</div>
    	</div>
    	
    	<div class="panel panel-default" ng-show="jdPanel2">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">Interviews Scheduled:</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			 		<div class="tablecontainer">
			 			<table class="table table-striped">
			 				<thead>
			 					<tr>
			 						<th style="text-align:center;">#</th>
					 				<th style="text-align:center;">JOB_ID</th>
									<th style="text-align:center;">SKILL</th>
									<th style="text-align:center;">SKILL_OWNER</th>
									<th style="text-align:center;">LEVEL</th>
									<th style="text-align:center;">JOB LOCATION</th>
									<th style="text-align:center;">DRIVE LOCATION</th>
									<th style="text-align:center;">DRIVE DATE</th>
									<th style="text-align:center;">REQUIREMENT COUNT</th>
									<th style="text-align:center;">NO OF CANDIDATE</th>
			 					</tr>
			 				</thead>
			 				<tbody>
				 				
								<tr ng-show="scheduledInterviews.length <= 0"><td colspan="10" style="text-align:center;">No Open RR!!</td></tr>
								<!-- <tr dir-paginate="d in scheduledInterviews|orderBy:sortKey:reverse|filter:search|itemsPerPage:2">  -->
								<tr ng-repeat="d in scheduledInterviews">
									<td style="text-align:center;"><input name ="radio1" type="radio" ng-click="onSelectRadio(d)" ng-value="d"></td>
									<td style="text-align:center;"><span ng-bind="d.jobdescId"></span></td>
									<td style="text-align:center;"><span ng-bind="d.skill"></span></td>
									<td style="text-align:center;"><span ng-bind="d.skillOwner"></span></td>
									<td style="text-align:center;"><span ng-bind="d.level"></span></td>
									<td style="text-align:center;"><span ng-bind="d.jobLocation"></span></td>
									<td style="text-align:center;"><span ng-bind="d.location"></span></td>
									<td style="text-align:center;"><span ng-bind="d.driveDate"></span></td>
									<td style="text-align:center;"><span ng-bind="d.reqCount"></span></td>
									<td style="text-align:center;"><span ng-bind="d.footFall"></span></td>
								</tr>
			 				</tbody>
			 			</table>
			 		</div>
    	</div>
    	
    	
    	
    	<div class="panel panel-default" ng-show = "showPanel">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">Open RR :</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			
			 		<div class="tablecontainer">
			 			<table class="table table-striped">
			 				<thead>
			 					<tr>
			 							<th style="text-align:center;">#</th>
			 							<th style="text-align:center;">RR ID</th>
										<th style="text-align:center;">JD ID</th>
										<th style="text-align:center;">ACCOUNT NAME</th>
										<th style="text-align:center;">GAP</th>
										<th style="text-align:center;">AGEING DAYS</th>
										<th style="text-align:center;">LOCATION</th>
										<th style="text-align:center;">VERTICAL GROUP</th>
										<th style="text-align:center;">SKILL</th>
										<th style="text-align:center;">LEVEL</th>
										<th style="text-align:center;">DEMAND</th>
										<th style="text-align:center;">FITMENT</th>
										<th style="text-align:center;">HIRING MANAGER</th>
										<th style="text-align:center;">RECRUITER</th>
										<th style="text-align:center;">STATUS</th>
			 					</tr>
			 				</thead>
			 				<tbody>
						 		<tr ng-show="pageableOpenRRPanel.length <= 0"><td colspan="15" style="text-align:center;">No Open RR !!</td></tr>
								<!-- <tr dir-paginate="p in pageableOpenRRPanel|orderBy:sortKey:reverse|filter:search|itemsPerPage:10"> -->
										<tr ng-repeat="p in pageableOpenRRPanel">
										<td style="text-align:center;"><input name ="radio2" type="radio" ng-click="onSelectRadio2(p)" ng-value="p"></td>
										<td style="text-align:center;"><span ng-bind="p.rrId"></span></td>
										<td style="text-align:center;"><span ng-bind="p.jobdescId"></span></td>
										<td style="text-align:center;"><span ng-bind="p.accountName"></span></td>
										<td style="text-align:center;"><span ng-bind="p.gap"></span></td>
										<td style="text-align:center;"><span ng-bind="p.ageingDays"></span></td>
										<td style="text-align:center;"><span ng-bind="p.location"></span></td>
										<td style="text-align:center;"><span ng-bind="p.verticalGrp"></span></td>
										<td style="text-align:center;"><span ng-bind="p.skill"></span></td>
										<td style="text-align:center;"><span ng-bind="p.level"></span></td>
										<td style="text-align:center;"><span ng-bind="p.demand"></span></td>
										<td style="text-align:center;"><span ng-bind="p.fitment"></span></td>
										<td style="text-align:center;"><span ng-bind="p.hiringManager"></span></td>
										<td style="text-align:center;"><span ng-bind="p.recruiter"></span></td>
										<td style="text-align:center;"><span ng-bind="p.status"></span></td>
								</tr>
			 				</tbody>
			 			</table>
			 		</div>
    	</div>
    	
    	<div class="panel panel-default" ng-show ="showPanel2">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">JD Update:</span>
			</div>
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			 <div class="formcontainer">
			 	<form  name="myForm" ng-submit="ctrl.updateL2Jd(l2jd)" class="form-horizontal">
				 	<div class="row col-md-12">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-lable">L1 JD:</label>
								<div class="col-md-8">
									<!-- <input type="text" style='height: 120px' ng-model="l1jd" name="l1jd" class="form-control input-sm" readonly /> -->
									<textarea class="form-control" rows="5" id="product" ng-model="l1jd" readonly></textarea> 
								</div>
							</div>
							
							<div class="form-group col-md-6">
								<label class="col-md-4 control-lable">L2 JD :</label>
								<div class="col-md-8">
									<!-- <input type="text" align="top" style='height: 120px' ng-model="l2jd" placeholder="Please Enter L2 JD" name="l2jd" class="form-control input-sm" /> -->
									<textarea class="form-control" rows="5" id="product" placeholder="Please Enter L2 JD" ng-model="l2jd" ></textarea> 
									<div class="has-error"
										ng-show="myform.l2jd.$dirty">
										<span ng-show="myform.l2jd.$error.required"  style="color: red">This is a required field</span>
									</div>
								</div>
							</div>
					</div>
						<div class="row">
						<div class="form-actions floatRight" align="center">
							<input type="submit" value="Update JD" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
						</div>
					</div>
			 	</form>	
			 </div>
    	</div>
    	
    	 
    	
    	<div class="panel panel-default" ng-show ="showPanel2">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">Consolidated L2 JD :</span>
			</div>
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			 
			 <table class="table table-striped">
						<thead>
							<tr>
								<th>RR ID</th>
								<th>L2 DESCRIPTION</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-show="pageableOpenRRPanel.length <= 0"><td colspan="2" style="text-align:center;">No Data Available!!</td></tr>
							<tr dir-paginate="d in pageableOpenRRPanel|orderBy:sortKey:reverse|itemsPerPage:10">
								<td><span ng-bind="d.rrId"></span></td>
								<td><span ng-bind="!d.l2JdDesc=='' ? d.l2JdDesc : '-'"></span></td>
							</tr>
							
						</tbody>
					</table>
					<div style="float: right;">								
								<dir-pagination-controls
							       	max-size="10"
							       direction-links="true"
							       boundary-links="true" >
							    </dir-pagination-controls>
								
							</div>
    	</div>
	</div>
</body>

<jsp:include page="../fragments/footer.jsp" />
</html>