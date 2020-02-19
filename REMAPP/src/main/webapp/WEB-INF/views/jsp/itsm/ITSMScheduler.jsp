<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/header.jsp" />
  <head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IAM Automation Scheduler</title>
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="container" ng-controller="SchedulerController as ctrl">

                <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading" style="background-color: #efe6f2"><span class="lead">IAM Scheduler List </span></div>
              <div class="tablecontainer">
                  <table class="table table-striped">
                      <thead>
                          <tr>
                              <th>SchedulerName</th>
                              <th>JobName</th>
                              <th>Frequency</th>
                               <th>Last Execution Time</th>
                               <th>Next Execution Time</th>
                               <th width="10%">
								 <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color:green"
								ng-click="ctrl.addTrigger()"></span>
							</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.triggerDetailList">
                              <td><span ng-bind="u.triggerName"></span></td>
                              <td><span ng-bind="u.jobName"></span></td>
                              <td><span ng-bind="u.frequency"></span></td>
                                <td><span ng-bind="u.lastExecutiontime"></span></td>
                                 <td><span ng-bind="u.nextFireTime"></span></td>
                                  <td>

                                  <span class="glyphicon glyphicon-pencil" aria-hidden="true" style="color:blue"
								ng-click="ctrl.edit(u.triggerName)"></span>
								<span style="display:inline-block; width: 5%;"></span>

								<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:red"
								 ng-click="ctrl.remove(u.triggerName)"></span>
                             </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>

          <div class="panel panel-default" ng-show="showme">
              <div class="panel-heading"><span class="lead">IAM Scheduler </span></div>
             <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.triggerDetail.triggerKey" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Scheduler Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.triggerDetail.triggerName" name="triggerName" class="username form-control input-sm" placeholder="Enter Scheduler name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.triggerName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.triggerName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.triggerName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">

                              <textarea class="form-control" rows="5" id="comment" ng-model="ctrl.triggerDetail.triggerDescription" placeholder="Enter Description. [This field is validation free]"></textarea>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Frequency</label>
                              <div class="col-md-7">
                               <input type="text" ng-model="ctrl.triggerDetail.frequency" class="form-control input-sm" placeholder="Scheduller frequency."/>

                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.frequency.$error.required">This is a required field</span>
                                      <span ng-show="myForm.frequency.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      	<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Job Name</label>
							<div class="col-md-7">


								<select class="form-control" name="apiRequestList"
									ng-options="jobDetail.jobName as jobDetail.jobName for jobDetail in ctrl.jobDetailList"
									ng-model="ctrl.triggerDetail.jobName" ng-select="ctrl.triggerDetail.jobName">
									<option value="">--Select Job--</option>

						</select> <br />
								<br />

							</div>
						</div>
					</div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.triggerDetail.triggerKey ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid" ng-click="showme=false">
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
      <script src="<c:url value='/static/js/service/scheduler_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/scheduller_controller.js' />"></script>
  </body>
</html>