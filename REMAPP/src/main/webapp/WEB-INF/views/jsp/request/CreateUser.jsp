<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />
  <head>
    <title>IAM Automation Scheduler</title>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="container" ng-controller="UserController as ctrl">

                

          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Create New User </span></div>
              <div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			  </div>
             <div class="formcontainer">
                  <form name="myForm" class="form-horizontal">
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">First Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.userInfo.firstName" name="firstName" class="username form-control input-sm" placeholder="Enter First Name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.firstName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.firstName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.firstName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Last Name</label>
                              <div class="col-md-7">

                              <input type="text" class="form-control" rows="5" name="lastName" ng-model="ctrl.userInfo.lastName" placeholder="Enter Last Name">
                              <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.lastName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.lastName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.lastName.$invalid">This field is invalid </span>
                                  </div> 
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Email ID</label>
                              <div class="col-md-7">
                               <input type="email" ng-model="ctrl.userInfo.mailId" name="mailId" class="form-control input-sm" placeholder="Enter Mail Id"/>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.mailId.$error.required">This is a required field</span>
                                      <span ng-show="myForm.mailId.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Login User Id</label>
                              <div class="col-md-7">
                               <input type="text" ng-model="ctrl.userInfo.username" name="username" class="form-control input-sm" placeholder="Enter User Name"/>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                     <!-- <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Password</label>
                              <div class="col-md-7">
                               <input type="password" ng-model="ctrl.userInfo.password" name="password" class="form-control input-sm" placeholder="Enter Password"/>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.password.$error.required">This is a required field</span>
                                      <span ng-show="myForm.password.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Confirm Password</label>
							<div class="col-md-7">
								<input type="password" ng-model="confirmPassWord"
									name="confirmPassWord" class="username form-control input-sm"
									placeholder="Re-Type Password" required ng-minlength="3" />
							<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.confirmPassWord.$error.required">This
										is a required field</span> <span
										ng-show="myForm.confirmPassWord.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.apiName.$invalid">This
										field is invalid </span><span ng-show="passWord != confirmPassword">Password mismatch</span>
								</div> 
							</div>
						</div>
					</div> -->
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Role</label>
                              <div class="col-md-7">
                               <!-- <select class="form-control"
									ng-model="ctrl.userInfo.role" >
									<option>ROLE_USER</option>
									<option>ROLE_ADMIN</option>
								</select>  -->
								
								<!-- <select class="form-control" name="status"
									ng-options="roles for roles in rolesList"
									ng-model="ctrl.userInfo.role" ng-select="ctrl.userInfo.role">
								</select> -->
								
								<!-- <span ng-repeat="roles in rolesList">
								    <input type="checkbox" id="{{roles}}" ng-model="ctrl.userRolesList[roles]"/>
								    <label for="{{roles}}">{{roles}}</label>
								</span> -->
								
								<label ng-repeat="role in rolesList" class="checkcontainer">
								  <input type="checkbox" checklist-model="ctrl.userRolesList" checklist-value="role"> {{role}}
								  <span class="checkmark"></span>
								</label>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.role.$error.required">This is a required field</span>
                                      <span ng-show="myForm.role.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
					  
					  <!-- <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Approver Type</label>
                              <div class="col-md-7">
                               <input type="text" ng-model="ctrl.userInfo.approverType" name="approverType" class="form-control input-sm" placeholder="Enter Approver Type"/>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.approverType.$error.required">This is a required field</span>
                                      <span ng-show="myForm.approverType.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div> -->
					  	
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.userInfo.userId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid" ng-click="ctrl.submit()">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Cancel</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
<jsp:include page="../fragments/footer.jsp" />
      </div>

       <script src="<c:url value='/static/lib/angular.js' />"></script> 
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/userdetails_service.js' />"></script>
<script src="<c:url value='/static/js/controller/manageuser_controller.js' />"></script>
  </body>
</html>