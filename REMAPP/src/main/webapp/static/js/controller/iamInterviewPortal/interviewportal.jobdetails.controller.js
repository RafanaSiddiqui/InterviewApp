'use strict';
angular.module('myApp').controller('GetRequirementController', ['$scope', '$window','JobDetailsService','$filter',function($scope, $window, JobDetailsService, $filter){
	
	var self = this;
	 self.interviewjobdetails={jobdescId:null, skill:'', location:'', experience:'', footFall:'', panelsCount:'', uploadedCV:'', cvCount:'', driveDate:'', description:''};
    self.submit = submit;
    self.createHrRequirement=createHrRequirement;
    self.loadAllJobPanelList=loadAllJobPanelList;
    self.upload=upload;
    self.uploadCV=uploadCV;
    self.reset-=reset;
    self.deleteJD = deleteJD;
    var file = $scope.myFile;
    loadAllJobPanelList()
    ;  
    function submit(){
	   createHrRequirement();
   }
	
   
   function createHrRequirement(){
	   $scope.errorMessage=false;
	   $scope.successMessage=false;
	   
	   JobDetailsService.addJobDetails(self.interviewjobdetails)
           .then(function(d) {
	            	reset();
	            	loadAllJobPanelList();
  	            },
           function(errResponse){
  	            	$scope.errorMessage="Exception occurred, please contact administrator!";
           }
       );
   }  
   
   function loadAllJobPanelList() {
	JobDetailsService
			.loadAllJobPanelList()
			.then(
					function(d) {
						$scope.pageableJobDetailsPanel = d;
					   	angular.forEach($scope.pageableJobDetailsPanel, function(value, key){
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

					function(errResponse) {
						$scope.errorMessage="Exception occurred, please contact administrator!";
					});

}
   

function upload() {
	 $scope.errorMessage=false;
	 
	 
	 if($scope.myFile == undefined || $scope.myFile == null || $scope.myFile == ''){
		 $scope.errorMessage="No file chosen to upload the requirement. Please choose a file !!";
		
	}
 	else{
		    var filename = $scope.myFile.name;
	        var index = filename.lastIndexOf(".");
	        var strsubstring = filename.substring(index, filename.length);
	        if (strsubstring != '.xlsx' )
	        {
	            $scope.errorMessage = 'Please upload correct File Name, File extension should be .xlsx. ';
	            $scope.myFile = '';
	        }else{
	        	JobDetailsService
					.uploadHRRequirementXLS($scope.myFile)
					.then(
							function() {
								angular.element(document.querySelector('#fileSelector')).val(null);
								$scope.myFile=null;
								$scope.successMessage="File Successfully Uploaded!";
								loadAllJobPanelList();
							},
		
							function(errResponse) {
								$scope.successMessage=false;
								$scope.errorMessage="Exception occurred, please contact administrator!";
								
							});
			 }
 	}

}

function uploadCV(jobDetailId) {
	 $scope.errorMessage=false;
	 
	$window.location.href = '/ermapp/interview/hr/cvUpload/'+jobDetailId;
}


function reset(){
	 self.interviewjobdetails={jobdescId:null, skill:'', location:'', experience:'', footFall:'', panelsCount:'', uploadedCV:'', cvCount:'', driveDate:'', description:''};
	  $scope.myForm.$setPristine();
}


$scope.clear = function () {
	
};


function deleteJD(jobDetailId){
	   $scope.errorMessage=false;
	   $scope.successMessage=false;
	
	JobDetailsService.deleteJD(jobDetailId)
   	 .then(
	            function(d) {

	            	loadAllJobPanelList();
	            	$scope.errorMessage= false;
	            },

           function(errResponse){
               console.error('Error while deleting JD');
               $scope.errorMessage="Exception occurred, please contact administrator !!";
           }
   	 );
	reset();       

}


   
}])


