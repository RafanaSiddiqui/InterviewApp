'use strict';

angular.module('myApp').controller('ViewPanelController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', 'ViewPanelService', 'JobDetailsService', function($scope, $window, $uibModal, $timeout, $filter, ViewPanelService, JobDetailsService) {
    var self = this;
    
    $scope.showPanel = false;
    $scope.errorMessage = true;
    
    loadAllScheduledInterviews();
    
	function loadAllScheduledInterviews() {
	   	console.log('Inside ViewPanelController :: loadAllScheduledInterviews');
	   	JobDetailsService.loadAllJobPanelList()
	   	 .then(
		            function(d) {
		            	$scope.scheduledInterviews = d;
		            	console.log('Inside ViewPanelController :: loadAllScheduledInterviews'+ $scope.scheduledInterviews);
	            	angular.forEach($scope.scheduledInterviews, function(value, key){
		            		//console.log('Inside ViewPanelController :: loadAllScheduledInterviews, value  ::  '+value);
		            		if(value.driveDate != null || value.driveDate === "") {
		            		crDate = new Date(value.driveDate);
		            		var dd = crDate.getDate();
		            		var mm = crDate.getMonth()+1; //January is 0!
	
		            		var yyyy = crDate.getFullYear();
		            		if(dd<10){
		            		    dd='0'+dd;
		            		} 
		            		if(mm<10){
		            		    mm='0'+mm;
		            		} 
		            		var crDate = mm+'/'+dd+'/'+yyyy;
		            		
		            		value.driveDate = crDate;
		            		}
		            		
		            	});
	            	$scope.errorMessage = $scope.errorMessage = false;
		            },
	
	           function(errResponse){
	               console.error('Inside ViewPanelController, error in loadAllScheduledInterviews');
	               $scope.errorMessage = $scope.errorMessage = true;
	           }
	   	 );
	
	};
	
	function loadAcceptedPanelByJdId(jdId) {
	   	console.log('Inside ViewPanelController :: loadAcceptedPanelByJdId, jdId -->' + jdId);
	   	ViewPanelService.loadAcceptedPanelByJdId(jdId)
	   	 .then(
		            function(p) {

		            	$scope.acceptedPanelList = p;
		            	$scope.errorMessage = $scope.errorMessage = false;
		            },
	
	           function(errResponse){
	               console.error('Inside ViewPanelController, error in loadAcceptedPanelByJdId');
	               $scope.errorMessage = $scope.errorMessage = true;
	           }
	   	 );
	
	};
	
	$scope.onSelectRadio = function(d) {
  	  console.log("Enter onSelectRadio  ::  ",d);
  	$scope.showPanel = $scope.showPanel = true;
	$scope.errorMessage = $scope.errorMessage = false;

  	loadAcceptedPanelByJdId(d.jobdescId);
  	  console.log("Exit onSelectRadio");
    }
	
}]);
    