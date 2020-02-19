'use strict';
angular.module('myApp').controller('OpenRRController', ['$scope', '$window','UploadRRService','JobDetailsService','$filter','$http',function($scope, $window, UploadRRService,JobDetailsService, $filter,$http){
	
	var self = this;
	self.upload = upload;
	self.loadAllRRPanelList = loadAllRRPanelList;
	self.loadAllScheduledInterviews = loadAllScheduledInterviews;
	self.deleteRR = deleteRR;
	
    loadAllScheduledInterviews();
    
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
	            	 $scope.errorMessage=false;
		            },
	
	           function(errResponse){
		            	$scope.errorMessage="Exception occurred, please contact administrator!";
	           }
	   	 );
	
	};
		
		function upload() {
			 $scope.errorMessage=false;
			 if($scope.myFile == undefined || $scope.myFile == null || $scope.myFile == ''){
				// console.log("..no files.. "+$scope.myFile);
				 $scope.errorMessage="No file chosen to upload the RR. Please choose a file !!";
				
			}
		 	else{
		 		console.log("..in upload.. "+$scope.myFile.name);
				    var filename = $scope.myFile.name;
			        var index = filename.lastIndexOf(".");
			        var strsubstring = filename.substring(index, filename.length);
			        if (strsubstring != '.xlsx' )
			        {
			            $scope.errorMessage = 'Please upload correct File Name, File extension should be .xlsx. ';
			            $scope.myFile = null;
			        }else{
						UploadRRService
								.uploadOpenRRXLS($scope.myFile,$scope.jobDetailsid)
								.then(
										function() {
											angular.element(document.querySelector('#fileSelector')).val(null);
											$scope.myFile=null;
											$scope.errorMessage=false;
											loadAllRRPanelList($scope.jobDetailsid);
							},
						
							function(errResponse) {
								console
								$scope.errorMessage="Exception occurred, please contact administrator!";
								});
		       }
		 	}
		
		};
	
	
	function loadAllRRPanelList(jobDetailsId) {
		console.log("Inside LoadAllJobPanelList!!");
		UploadRRService
				.loadAllRRPanelList(jobDetailsId)
				.then(
						function(d) {
							$scope.pageableOpenRRPanel = d;
							$scope.errorMessage=false;
						},

						function(errResponse) {
							$scope.errorMessage="Exception occurred, please contact administrator!";
						});

	};
	   
	$scope.onSelectRadio = function(jdId) {
	  	  console.log("Enter onSelectRadio  ::  ",jdId);
	  	$scope.jobDetailsid=jdId;
	  	$scope.showPanel  = true;
	  	loadAllRRPanelList(jdId);
	  	 $scope.errorMessage=false;
	    };
	    
    
    function deleteRR(Id){
    	console.log("Enter deleteRR  primaryID ::  "+Id);
 	   $scope.errorMessage=false;
 	   //$scope.successMessage=false;
 	
 	  UploadRRService.deleteRR(Id)
    	 .then(
 	            function(d) {

 	            	loadAllRRPanelList($scope.jobDetailsid);
 	            	$scope.errorMessage= false;
 	            },

            function(errResponse){
                console.error('Error while deleting RR');
                $scope.errorMessage="Exception occurred, please contact administrator !!";
            }
    	 );  

 }

		
	
}])


