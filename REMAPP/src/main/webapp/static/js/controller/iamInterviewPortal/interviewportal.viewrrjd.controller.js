'use strict';

angular.module('myApp').controller('ViewRRJDController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', 'JobDetailsService','UploadRRService','UploadCVService', function($scope, $window, $uibModal, $timeout, $filter, JobDetailsService,UploadRRService,UploadCVService) {
    var self = this;
    self.l2jd=self.l2jd;
    self.loadAllScheduledInterviews=loadAllScheduledInterviews;
    self.loadAllRRPanelList=loadAllRRPanelList;
    self.updateL2Jd=updateL2Jd;
    self.loadUpdateJDForRR=loadUpdateJDForRR;
    self.jobdetailsPanel = {jobdescId:'', skill:'',footFall:'',cvCount:'',location:'', experience:'', panelsCount:'', driveDate:'', reqCount:''};
    
	function loadAllScheduledInterviews() {
	   	console.log('Inside loadAllScheduledInterviews :: loadAllScheduledInterviews');
	   	JobDetailsService.loadAllJobPanelList()
	   	 .then(
		            function(d) {
		            	$scope.scheduledInterviews = d;
	            	 angular.forEach($scope.scheduledInterviews, function(value, key){
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
		            },
	
	           function(errResponse){
		            	$scope.errorMessage="Exception occurred, please contact administrator!";
	           }
	   	 );
	
	};
	
	
	$scope.onSelectRadio = function(d) {
  	  console.log("Enter onSelectRadio  ::  ",d);
  	$scope.showPanel = true;
  	$scope.showPanel2 = false;
  	$scope.l1jd=d.l1Description;
  	$scope.l2jd='';
  	loadAllRRPanelList(d.jobdescId);
  	  console.log("Exit onSelectRadio");
    }
	
	function loadAllRRPanelList(jdId) {
		console.log("Inside LoadAllJobPanelList!!",jdId);
		UploadRRService
				.loadAllRRPanelList(jdId)
				.then(
						function(d) {
							$scope.pageableOpenRRPanel = d;
						},

						function(errResponse) {
							$scope.errorMessage="Exception occurred, please contact administrator!";
						});

	}
	
	$scope.onSelectRadio2 = function(d) {
	  	console.log("Enter onSelectRadio2 ,rrId>> ::  ",d.rrId);
	  	$scope.showPanel2 = true;
	  	$scope.rrDetails=d;

		if (d.l2JdDesc == 'null') {
		$scope.l2jd = "";
		} else {
			$scope.l2jd = d.l2JdDesc;
		}
	  	
}
	
	function updateL2Jd(l2jd){
    $scope.rrDetails.l2JdDesc=l2jd;
	UploadRRService
		.updatL2JD($scope.rrDetails)
		.then(
				function(d) {
					console.log("L2 JD Saved in DB");
				},

				function(errResponse) {
					$scope.errorMessage="Exception occurred, please contact administrator!";
				});
		
	}
	
	$scope.updateRRForJdId = function(jobDetailId) {
		if (!jobDetailId) {
			$scope.jdPanel2=true;
			loadAllScheduledInterviews();
		}else{
		loadUpdateJDForRR(jobDetailId);
		}
	};
	
	
	function loadUpdateJDForRR(jobDetailId){
		UploadCVService
		.loadCandidateCVById(jobDetailId)
		.then(
				function(d) {
					
					$scope.jobdetailsPanel = d;
					self.jobdetailsPanel = d;
					$scope.jdPanel1=true;
					$scope.jdPanel2=false;
					
					if(d.driveDate != null || d.driveDate === "") {
	            		crDate = new Date(d.driveDate);
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
	            		
	            		d.driveDate = crDate;
	            		}
					
					//self.jobDetailsCV = d;
				},

				function(errResponse) {
					$scope.errorMessage="Exception occurred, please contact administrator!";
	});		
		
	}
	
	
	
	
}]);
    