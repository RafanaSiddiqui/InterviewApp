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
<title>IAM Automation Configuration</title>

</head>
<body ng-app="myApp" class="ng-cloak">
	<div class=container ng-controller="ConfigurationController as ctrl">
	
		<div>
			<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead"> IAM Automation Configuration Details </span>
			</div>
			<div class="formcontainer">

				<div class="tablecontainer">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Category</th>
								<th>ConfigurationDetails</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="configurationByCat in ctrl.configurationByCategoryList">
								<td>
									<span ng-bind="configurationByCat.category"></span> 
								</td>
								<td ng-show="configurationByCat.category!='IAM Product' && configurationByCat.category!='ProcessExecution'">
									<div class="tablecontainer">
										<table class="table table-hover">
											<thead>
												<tr>
													<th ng-show="configurationByCat.category=='DashBoardUrls' || configurationByCat.category=='CustomerDetails' || configurationByCat.category=='BotDashBoardUrls' || configurationByCat.category=='SecurityDomain' || configurationByCat.category=='ServiceCategory' || configurationByCat.category=='Service'">Name</th>
													<th>Value</th>
													<th ng-show=false>settingID</th>
													<th ng-show=false>category</th>
													<th>Actions</th>
													<th>
														<div class="add-row-editable-table">
  															<button class="btn btn-primary" ng-click="addEntry($index)"><span class="glyphicon glyphicon-plus"></span></button>
														</div>
													</th>
												</tr>
											</thead>
											<tbody>
												<tr
													ng-repeat="configurationList in configurationByCat.configurationList" class="editable-row">
													<td ng-show="configurationByCat.category=='DashBoardUrls' || configurationByCat.category=='CustomerDetails' || configurationByCat.category=='BotDashBoardUrls' || configurationByCat.category=='SecurityDomain' || configurationByCat.category=='ServiceCategory' || configurationByCat.category=='Service'">
														<span editable-text="configurationList.name" e-name="name" e-form="rowform" e-required>
												          {{ configurationList.name }}
												        </span>
													</td>
													<td>
														<!-- <span ng-bind="configurationList.value"></span> -->
														<span editable-text="configurationList.value" e-name="value" e-form="rowform" e-required>
												          {{ configurationList.value }}
												        </span>
													</td>
													<td ng-show=false>
														<span ng-bind="configurationList.settingID"></span>
														<span editable-text="configurationList.settingID" e-name="settingID" e-form="rowform" e-required>
												          {{ configurationList.settingID }}
												        </span>
													</td>
													<td ng-show=false>
														<span ng-bind="configurationByCat.category"></span>
														<span editable-text="configurationByCat.category" e-name="category" e-form="rowform" e-required>
												          {{ configurationByCat.category }}
												        </span>
													</td>
													<td>
												      <form editable-form name="rowform" ng-show="rowform.$visible" class="form-buttons form-inline"
												            shown="inserted == configurationList">
												        <button type="submit" ng-disabled="rowform.$waiting" ng-click="editEntry(rowform.$data)" class="btn btn-primary editable-table-button btn-xs">
												          <span class="glyphicon glyphicon-ok"></span>
												        </button>
												        <button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default editable-table-button btn-xs">
												          <span class="glyphicon glyphicon-remove"></span>
												        </button>
												      </form>
												      <div class="buttons" ng-show="!rowform.$visible">
												        <button class="btn btn-primary btn-xs editable-table-button" ng-click="rowform.$show()"><span class="glyphicon glyphicon-pencil"></span></button>
												        <button class="btn btn-danger btn-xs editable-table-button" ng-click="removeEntry($parent.$index, $index)"><span class="glyphicon glyphicon-trash"></span>	</button>
												      </div>
												    </td>
												</tr>
											</tbody>
										</table>
										</div>
								</td>
								<td ng-show="configurationByCat.category=='IAM Product' || configurationByCat.category=='ProcessExecution'">
									<div ng-repeat="configurationList in configurationByCat.configurationList">
										<div ng-repeat="nameList in configurationList.name.split(',')">
											<input type="radio" ng-model="configurationList.value" ng-value="nameList" ng-click="updateRadioConfiguration(configurationList, globalConfiguration)">{{nameList}}
										</div>
        							</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>

	<script src="<c:url value='/static/lib/xeditable.js' />"></script>
	<script src="<c:url value='/static/lib/ui-bootstrap-tpls.js' />"></script>
	<script src="<c:url value='/static/js/service/configuration_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/configuration_controller.js' />"></script>
</body>


</html>