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
	<div class="container" ng-controller="UpdateJDController as ctrl">
		
    	<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">General JD for L1</span>
			</div>
			
			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			  </div>
			  
			  <div class="formcontainer">
                  <form name="myForm" class="form-horizontal" ">
                  
              			<div class="tablecontainer"> 
              			<table class="table table-striped">
								<tr></tr>
	              				<tr>
		              				<div class="row">
										<td>						
											<div class="form-group col-md-12">
												<label class="col-md-10 control-lable" for="skill">Skill<span style="color:red;">*</span></label>
												
											</div>					
										</td>
										<td>
											<div class="col-md-9">
											       <input type="text"  name="skill" class="username form-control input-sm" ng-model="ctrl.interviewPortalJobDetails.skill" 
											               				placeholder="Enter Skill" required ng-minlength="2"/>
														
												   <div class="has-error" ng-show="myForm.$dirty">
				                                      <span ng-show="myForm.skill.$error.required">This is a required field</span>
				                                      <span ng-show="myForm.skill.$error.minlength">Minimum length required is 2</span>
				                                  </div>
											</div>
										</td>
									</div>
			              		</tr>
			              		
			              		<!-- <tr>
									<div class="row">
										<td>						
											<div class="form-group col-md-12">
												<label class="col-md-10 control-lable" for="driveDate">Drive Date</label>
												
											</div>					
										</td>
										<td>
											<div class="col-md-9">
											      <input type="date" ng-model="ctrl.interviewPortalJobDetails.driveDate"
															name="driveDate" class="username form-control input-sm" required/>
												<div class="has-error" ng-show="myForm.driveDate.$dirty">
													<span ng-show="myForm.reqStartDate.$error.required" style="color: red">This is a required field</span>
												</div>
											</div>
										</td>
									</div>
								</tr> -->
								
								<tr>
						            <div class="row">
						               <td>
							              	 <div class="form-group col-md-12">
												<label class="col-md-10 control-lable" for="l1Description">JD Description<span style="color:red;">*</span></label>
												
											</div>	
										</td>
						               <td>
										 <div class="col-md-9">
			                             		 <textarea class="form-control" rows="5" id="l1Description" name= "l1Description" ng-model="ctrl.interviewPortalJobDetails.l1Description"
			                             		 					style="height: 200px;"   placeholder="Enter JD Description." maxlength="10000"></textarea>
			                             	 <div class="has-error" ng-show="myForm.l1Description.$dirty">
			                                      <span ng-show="myForm.l1Description.$error.required">This is a required field</span>
			                                  </div>
			                              </div>
						               </td>
						             </div>
					            </tr>
					            
					            <tr>
					           	 <div class="row">
						            	<td colspan="2">
						              
				                          <div class="form-actions floatRight text-center">
				                              <input type="submit"  value="Update" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid" ng-click="ctrl.update(myform)">
				                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset</button>
				                          </div>
				                     
				                      </td>
			                       </div>
					            </tr>				
		              		</table>	
              			</div>
              			
              		<div class="tablecontainer"  style="width: 100%;" ng-show = "successfulUpdate">
			 			<table  style="width:100%;">			 				
					       
			 				<tr>
						           <td >	
						           <div class="panel-heading" style=" text-align:center;">					              
										<span style="font-size: 14px; text-align:center; color:green;"><b>General JD for L1 description successfully updated !!</b></span>
			                        </div> 	 
				                     </td>
					            </tr>
					            					            
				           </table>
				      </div> 
				      
				     <div class="tablecontainer"  style="width: 100%;"  ng-if="errorMessage">
			 			<table  style="width:100%;">			 				
					       
			 				<tr>
						           <td >	
						           <div class="panel-heading" style=" text-align:center;">	
										<span style="font-size: 14px; text-align:center; color:red;"><b>{{errorMessage}}</b></span>
			                        </div> 	 
				                     </td>
					            </tr>
					            					            
				           </table>
				      </div> 
                  
                  </form>
              </div>
		</div>		
	
	</div>
	
	<script src="<c:url value='/static/js/service/masterdemand_service.js' />"></script>
	<script src="<c:url value='/static/js/service/iamInterviewPortal/interviewPortalUpdateJD_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/iamInterviewPortal/interviewPortalUpdateJD_controller.js' />"></script>

</body>

<jsp:include page="../fragments/footer.jsp" />
</html>