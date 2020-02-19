<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>


<script src="<c:url value='/static/lib/angular.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<jsp:include page="../fragments/header.jsp" />
<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>
<script src="<c:url value='/static/js/directives/filemodel.js' />"></script>

<script
	src="<c:url value='/static/js/service/iamInterviewPortal/interviewportal.admin.service.js' />"></script>
<script
	src="<c:url value='/static/js/controller/iamInterviewPortal/interviewportal.admin.controller.js' />"></script>
<head>
<title>Panel Details</title>

</head>

<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="InterviewAdminController as ctrl">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Panel Upload</span>
			</div>

			<div>
				<span class="lead"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
			</div>



			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">

					<div class="form-group">
						<label class="control-label col-sm-4 col-xs-12" for="file">Please
							upload the file (*.xlsx File) : <span style="color:red">*</span>
						</label>
						<div class="col-xs-4 input-max controls ">
							<input id="fileSelector" class="inline-block" type="file" file-model="myFile" />
						</div>
						
						<div class="col-xs-4 input-max controls ">
							<input type="submit" value="Upload data"
								class="btn btn-primary btn-sm" ng-disabled="disableUploadBtn">
						</div>
						<br>
						
					</div>
					
					
				</form>

				<form ng-submit="ctrl.enter()" name="myForm1"
					class="form-horizontal">
					<div ng-if="pageableAdminPanelAll.length">
						<table class="table table-striped">
							<thead>
								<tr>
									<!-- 	<th> <span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span> </th>  -->
									<th><input type="checkbox" ng-model="IsAllChecked" ng-click="CheckUncheckAll()" /></th>
									<th>Emp ID</th>
									<th>Employee Name</th>
									<th>Account</th>
									<th>Location</th>
									<th>Designation</th>
									<th>Phone</th>
									<th>Technology</th>
									<th>Product</th>
								</tr>
							</thead>
							<tbody>

								<!-- <tr dir-paginate="d in pageableAdminPanelAll|itemsPerPage:pageableAdminPanelAll.size" total-items="pageableAdminPanelAll.totalElements"> -->
								<tr ng-repeat="d in pageableAdminPanelAll">
									<td ng-if="d.valid"><input type="checkbox" ng-model="d.selected" /></td>
									<td ng-if="!d.valid"></td>
									<td><span ng-bind="d.empId"></span></td>
									<td><span ng-bind="d.name"></span></td>
									<td><span ng-bind="d.account"></span></td>
									<td><span ng-bind="d.location"></span></td>
									<td><span ng-bind="d.designation"></span></td>
									<td><span ng-bind="d.phone"></span></td>
									<td><span ng-bind="d.technology"></span></td>
									<td><span ng-bind="d.product"></span></td>

								</tr>

							</tbody>

						</table>
						<input type="submit" value="Enter data" class="btn btn-primary btn-sm">
						<!--  	<button type="button" ng-click="ctrl.enter(pageableAdminPanelAll)" class="btn btn-warning btn-sm" >Enter</button> -->
					</div>
				</form>
			</div>
		</div>
		
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Panel Details</span>
			</div>

			<div ng-if="allPanel.length">
				<table class="table table-striped">
					<thead>
						<tr>
							<!-- 	<th> <span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span> </th>  -->
							
							<th>Emp ID</th>
							<th>Employee Name</th>
							<th>Account</th>
							<th>Location</th>
							<th>Designation</th>
							<th>Phone</th>
							<th>Technology</th>
							<th>Product</th>
							<th>Status</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr dir-paginate="d in pageableAdminPanelAll|itemsPerPage:pageableAdminPanelAll.size" total-items="pageableAdminPanelAll.totalElements"> -->
						<tr ng-repeat="d in allPanel">
						<!-- 	<td><input type="radio" name="panelSelect"  value="{{d.empId}}" ng-model="ctrl.select"/></td>  -->
							<td><span ng-bind="d.empId"></span></td>
							<td><span ng-bind="d.name"></span></td>
							<td><span ng-bind="d.account"></span></td>
							<td><span ng-bind="d.location"></span></td>
							<td><span ng-bind="d.designation"></span></td>
							<td><span ng-bind="d.phone"></span></td>
							<td><span ng-bind="d.technology"></span></td>
							<td><span ng-bind="d.product"></span></td>
							<td><span ng-bind="d.status"></span></td>
							<td><span class="glyphicon glyphicon-pencil"
								aria-hidden="true" style="color: blue"
								ng-click="ctrl.editPanel(d.empId)"></span></td>
							<td><span class="glyphicon glyphicon-remove" aria-hidden="true"
								style="color: red" ng-click="ctrl.deletePanel(d.empId)"">
							</span></td>

						</tr>
					</tbody>
				</table>
				<button style="float: right; padding: 5px 10px;" type="button" ng-click="ctrl.panelExport()"
									class="btn btn-warning btn-sm">Export to .CSV</button> 			
				
			</div>
			<br>
			
			
			<div ng-if="panelUploadSuccess"> <span style="color:green">
							<h4>	&nbsp;&nbsp; Data updated sucessfully. </h4></span>
			</div>
			
			 <div ng-if="errorMessage" style="color:red">
			 		<h4><b>&nbsp;&nbsp;{{errorMessage}}</b> </h4></span>
			 </div>				
			<br>
			<label class="col-md-4 control-lable">Add/Edit Panel Details</label>
		<br><br>
			<form ng-submit="ctrl.addUpdatePanel()" name="myForm2" class="form-horizontal">
			
			<div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Emp Id:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.admin.empId" ng-required="ctrl.select" name="empId" ng-readonly="empIdReadOnly"  class="form-control input-sm" />
							</div>
						</div>						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Employee Name:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.admin.name" name="name" ng-readonly="empIdReadOnly" class="form-control input-sm" />
							</div>
							
						</div>						
			  </div>
			  <div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Account:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.admin.account" name="account" class="form-control input-sm" />
							</div>
						</div>						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Location:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.admin.location" name="location" class="form-control input-sm" />
							</div>							
						</div>						
			  </div>
			  <div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Designation:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.admin.designation" name="designation" class="form-control input-sm" />
							</div>
						</div>						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Phone:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<input type="text" ng-model="ctrl.admin.phone" name="phone" class="form-control input-sm" />
							</div>							
						</div>						
			  </div>
			  <div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Technology:<span style="color:red">*</span></label>
							<div class="col-md-8">
							<!-- <input type="textarea" ng-model="ctrl.admin.technology" name="technology" class="form-control input-sm" />  -->	
								
								<textarea class="form-control" rows="5" id="technology"
									ng-model="ctrl.admin.technology"></textarea>
								
							</div>
						</div>						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable">Product:<span style="color:red">*</span></label>
							<div class="col-md-8">
							<!--	<input type="textarea" ng-model="ctrl.admin.product" name="product" class="form-control input-sm" /> -->	
							<textarea class="form-control" rows="5" id="product"
									ng-model="ctrl.admin.product"></textarea>
							</div>							
						</div>						
			  </div>
			  
			  <div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file">Status:<span style="color:red">*</span></label>
							<div class="col-md-8">
								<select name="status" ng-model="ctrl.admin.status">							
									<option value="ACTIVE">ACTIVE</option>
									<option value="INACTIVE">INACTIVE</option>
								</select>
							</div>
						</div>						
						<div class="form-group col-md-6">
							
							<div class="col-md-8">
								
							</div>							
						</div>						
			  </div>
			  <div class="row col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable" for="file"></label>
							<div class="col-md-8">
								<input type="submit" value=" Add " class="btn btn-primary btn-sm" ng-disabled="!disableUpdateBtn ">
								&nbsp;&nbsp;&nbsp;
								
								<button type="button" ng-click="ctrl.updateSinglePanel()" class="btn btn-primary btn-sm" ng-disabled="disableUpdateBtn">Update</button>
							
								&nbsp;&nbsp;&nbsp; 
								
								<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm">Reset</button>
							</div>
						</div>						
						<div class="form-group col-md-6">
							<label class="col-md-4 control-lable"></label>
							<div class="col-md-8">
								
							</div>							
						</div>						
			  </div>	  
			<br><br>
			
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
				
		   </form>
						
		
		</div>


		<!-- shan start here -->



		<!-- shan end here -->




		<jsp:include page="../fragments/footer.jsp" />
	</div>


</body>

</html>