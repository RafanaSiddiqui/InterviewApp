<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ERSS Resource Manager</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="ViewPanelController as ctrl">
		
	<form class="row">
	 	<div class="panel-heading">
            <label class="lead" >View Panel Status</label>
        </div>
       </form>
	        
    	<div class="panel panel-default">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">Interviews Scheduled :</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			
			 <div class="formcontainer">
			 	
			 	<form  name="myForm" class="form-horizontal">
			 		<div class="tablecontainer">
			 			<table class="table table-striped">
			 				<thead>
			 					<tr>
			 						<th style="text-align:center;">Sl No.#</th>
					 				<th style="text-align:center;">JD_ID</th>
									<th style="text-align:center;">SKILL</th>
									<th style="text-align:center;">SKILL_OWNER</th>
									<th style="text-align:center;">LEVEL</th>
									<th style="text-align:center;">LOCATION</th>
									<th style="text-align:center;">Drive Date</th>
									<th style="text-align:center;">No. of Req</th>
									<th style="text-align:center;">No. of Cand</th>
									<th style="text-align:center;">JD View-Edit</th>
			 					</tr>
			 				</thead>
			 				<tbody>
				 				
								<tr ng-show="scheduledInterviews.length <= 0"><td colspan="10" style="text-align:center;">No Scheduled Interviews!!</td></tr>
								<!-- <tr dir-paginate="d in scheduledInterviews|itemsPerPage:scheduledInterviews.size" total-items="scheduledInterviews.totalElements"> -->
								<!-- <tr dir-paginate="d in scheduledInterviews|orderBy:sortKey:reverse|filter:search|itemsPerPage:10"> -->
									<tr ng-repeat="d in scheduledInterviews">
									<!-- <td><span ng-bind="{{$index + 1}}"></span></td> -->
									<td style="text-align:center;"><input type="radio" ng-model="$parent.selectedObj" ng-click="onSelectRadio(d)" ng-value="d"></td>
									<td style="text-align:center;"><span ng-bind="d.jobdescId"></span></td>
									<td style="text-align:center;"><span ng-bind="d.skill"></span></td>
									<td style="text-align:center;"><span ng-bind="d.skillOwner"></span></td>
									<td style="text-align:center;"><span ng-bind="d.level"></span></td>
									<td style="text-align:center;"><span ng-bind="d.location"></span></td>
									<td style="text-align:center;"><span ng-bind="d.driveDate"></span></td>
									<td style="text-align:center;"><span ng-bind="d.reqCount"></span></td>
									<td style="text-align:center;"><span ng-bind="d.footFall"></span></td>
									<td style="text-align:center;"><a id="viewJD2" ng-href="${pageContext.request.contextPath}/interview/compLead/viewRRJd/{{d.jobdescId}}">View/Edit</a></td>
								</tr>
			 				</tbody>
			 			</table>
			 			
			 		</div>
			 	</form>	
			 </div>
			
			  
			  
    	</div>
    	
    	
    	<div class="panel panel-default" ng-show = "showPanel">
    		<div class="panel-heading" style="background-color: #efe6f2">
				<span style="font-size: 18px">Panel Accepted :</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			
			 <div class="formcontainer">
			 	
			 	<form  name="myForm" class="form-horizontal">
			 		<div class="tablecontainer">
			 			<table class="table table-striped">
			 				<thead>
			 					<tr>
			 						<th style="text-align:center;">Sl No.#</th>
					 				<th style="text-align:center;">EMP_ID</th>
									<th style="text-align:center;">NAME</th>
									<th style="text-align:center;">ACCOUNT</th>
									<th style="text-align:center;">LOCATION</th>
									<th style="text-align:center;">DESIGNATION</th>
									<th style="text-align:center;">PHONE</th>
									<th style="text-align:center;">TECHNOLOGY</th>
									<th style="text-align:center;">PRODUCT</th>
									<th style="text-align:center;">STATUS</th>
									<th style="text-align:center;">LEVEL</th>
			 					</tr>
			 				</thead>
			 				<tbody>
						 		<tr ng-show="acceptedPanelList.length <= 0"><td colspan="11" style="text-align:center;">No Panel present for the Interview !!</td></tr>
								<!-- <tr dir-paginate="d in feedbacks|itemsPerPage:scheduledInterviews.size" total-items="feedbacks.totalElements"> -->
								<tr dir-paginate="p in acceptedPanelList|orderBy:sortKey:reverse|filter:search|itemsPerPage:10">
									<td  style="text-align:center;"><span ng-bind="{{$index + 1}}"></span></td>
									<td  style="text-align:center;"><span ng-bind="p.panelDetails.empId"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.name"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.account"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.location"></span></td>
									<td  style="text-align:center;"><span ng-bind="p.panelDetails.designation"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.phone"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.technology"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.product"></span></td>
									<td style="text-align:center;"><span ng-bind="p.panelDetails.status"></span></td>
									<td style="text-align:center;"><span ng-bind="p.interviewLevel"></span></td>							
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
			 	</form>	
			 </div>
			
    	</div>
    	
    			 <div class="tablecontainer"  style="width: 100%;" ng-show = "errorMessage">
			 			<table  style="width:100%;">			 				
					          <tr>
					          		<td>
						          	  <span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
						           </td>
					           </tr>
			 				<tr>
						           <td >	
						           <div class="panel-heading" style="background-color: #efe6f2; text-align:center;">					              
										<span style="font-size: 14px; text-align:center; color:red;"> Error Occurred. Please try again !!</span>
			                        </div> 	 
				                     </td>
					            </tr>
					            					            
				           </table>
				      </div> 

	</div>
	

	<script src="<c:url value='/static/js/service/iamInterviewPortal/interviewPortalViewPanel_service.js' />"></script>
	<script src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.job.service.js' />"></script>
	<script src="<c:url value='/static/js/controller/iamInterviewPortal/interviewPortalViewPanel_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>