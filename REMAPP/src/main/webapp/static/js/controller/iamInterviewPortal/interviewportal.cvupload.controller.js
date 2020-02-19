'use strict';
angular.module('myApp').controller('UploadCandidateCVController', ['$scope', '$window','$uibModal','UploadCVService','$filter','$http',function($scope, $window,$uibModal, UploadCVService, $filter,$http){
	
	var self = this;
	var file = $scope.myFile;
	self.jobDetails = {jobdescId:'', skill:'',footFall:'',cvCount:'',location:'', experience:'', panelsCount:'', driveDate:'', reqCount:''};
    self.loadCandidateCVById=loadCandidateCVById;
    self.reset=reset;
    self.uploadCV=uploadCV;
    self.loadCandidateCVBySingleId=loadCandidateCVBySingleId;
	self.deleteUploadedCV = deleteUploadedCV;

	$scope.uploadCandidateCVById = function(jobDetailId) {
		 $scope.errorMessage=false;
		loadCandidateCVById(jobDetailId);
	};

	function loadCandidateCVById(jobDetailId){
		UploadCVService
				.loadCandidateCVById(jobDetailId)
				.then(
						function(d) {
							$scope.jobdetails = d;
							self.jobdetails = d;
							self.jobDetailsCV = d;
							 $scope.errorMessage=false;
						},
		
						function(errResponse) {
							$scope.errorMessage="Exception occurred, please contact administrator!";
			});		
	};

	
function uploadCV(jobdetailId) {
	 $scope.errorMessage=false;
	 if($scope.myFile == undefined || $scope.myFile == null || $scope.myFile == ''){
		// console.log("..no files.. "+$scope.myFile);
		 $scope.errorMessage="No file chosen to upload the CV. Please choose a file !!";
		
	}else {
		
		  var filename = $scope.myFile.name;
	        var index = filename.lastIndexOf(".");
	        var strsubstring = filename.substring(index, filename.length);
	        if (strsubstring != '.zip' )
	        {
	            $scope.errorMessage = 'Please upload correct File Name, File extension should be .zip. ';
	            $scope.myFile = null;
	        }else{
				UploadCVService
						.uploadCVZip(self.jobdetails,
								$scope.myFile)
						.then(
								function(d) {
									angular.element(document.querySelector('#fileSelector')).val(null);
									$scope.myFile=null;
									$scope.errorMessage=false;
									loadCandidateCVBySingleId(jobdetailId);
									console.log('Request created');
								},
								function(errResponse) {
									$scope.errorMessage="Exception occurred, please contact administrator!";
								});
	        }
	 }

};
	


function loadCandidateCVBySingleId(jobDetailId){
	 $scope.errorMessage=false;
	UploadCVService
			.loadCandidateCVById(jobDetailId)
			.then(
					function(d) {
						$scope.jobDetailsCV = d;
						self.jobDetailsCV = d;
						 $scope.errorMessage=false;
					},
	
					function(errResponse) {
						$scope.errorMessage="Exception occurred, please contact administrator!";
		});		
};
	

$scope.fnDownLoad = function (jobDetailId) {
	   console.log("downloadCandidateCV  ::  "+jobDetailId);
	   var s = '/ermapp/interview/hr/downloadCandidateCV/'+ jobDetailId;
      $http.get('/ermapp/interview/hr/downloadCandidateCV/'+jobDetailId)
          .then(
          function (response) {
       	   console.log('response  ::  '+response);
       	   $window.open(s);
          },
          function(errResponse){
              console.error('Error while fnDownLoad ');
              $scope.open('/static/pages/errorNoFileModal.html');
          }
      );
  };




 function reset() {
	 
	 $window.location.href = '/ermapp/interview/hr/getRequirementDetails';
        
    };
    
    function deleteUploadedCV(jobDescId){
    	console.log("Enter deleteUploadedCV  jobid ::  "+jobDescId);
 	   $scope.errorMessage=false;
 	   //$scope.successMessage=false;
 	
 	  UploadCVService.deleteUploadedCV(jobDescId)
    	 .then(
 	            function(d) {

 	            	loadCandidateCVBySingleId(jobDescId);
 	            	self.jobdetails.cvCount='';
 	            	$scope.errorMessage= false;
 	            },

            function(errResponse){
                console.error('Error while deleting CV');
                $scope.errorMessage="Exception occurred, please contact administrator !!";
            }
    	 );  

 }

	
}])


