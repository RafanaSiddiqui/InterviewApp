<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<script src="<c:url value='/static/lib/angular.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<jsp:include page="../fragments/header.jsp" />
<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>
<script src="<c:url value='/static/js/directives/filemodel.js' />"></script>
<script src="<c:url value='/static/js/service/bulk.account.base.service.js' />"></script>
<script src="<c:url value='/static/js/controller/bulk.account.base.controller.js' />"></script>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bulk Account Base</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="BulkAccountBaseController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> Add New Bulk Account Base </span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>
			<div class="formcontainer">
                  <form name="myForm" class="form-horizontal" ng-submit="ctrl.submit()">
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.bulkAccountBase.name" name="name" class="username form-control input-sm" placeholder="Enter Bulk Account Base Name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.name.$dirty">
                                      <span ng-show="myForm.name.$error.required" style="color: red">This is a required field</span>
                                      <span ng-show="myForm.name.$error.minlength" style="color: red">Minimum length required is 3</span>
                                      <span ng-show="myForm.name.$invalid" style="color: red">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">

                              <textarea class="form-control" rows="5" id="comment" ng-model="ctrl.bulkAccountBase.description" placeholder="Enter Description. [This field is validation free]"></textarea>
                              </div>
                          </div>
                      </div>
                      
                      <div class="form-group">
						<label class="control-label col-sm-4 col-xs-12" for="file">Please
							upload the file : <span class="required">*</span>
						</label>
						<div class="col-xs-4 input-max controls ">
							<input class="inline-block" type="file" file-model = "myFile"/>
						</div>

					</div>
					  	
                      <!-- <div class="row"> -->
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.bulkAccountBase.bulkID ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <!-- <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Cancel</button> -->
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm">Cancel</button>
                          </div>
                      <!-- </div> -->
                  </form>
              </div>
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
</body>

</html>