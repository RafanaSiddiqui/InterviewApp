'use strict';

angular.module('myApp').controller('UpdateJDController', ['$scope', '$window','UpdateJDService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', '$filter', function($scope, $window, UpdateJDService, $uibModal, $timeout, UserDetailsService, MasterDemandService, $filter) {
	 var self = this;
	
	
	//self.interviewPortalJobDetails={jobdescId:null,skill:'',driveDate:'',l1Description:''};
	
//	self.interviewPortalJobDetails={jobdescId:null, skill:'',l1Description:''};

	$scope.successfulUpdate = false;
	$scope.errorMessage = false;
	
	self.reset = reset;
	
	self.update =   update;
		
		function update(myForm){

			console.log('Inside UpdateJDController :: update Method');

	    	
		    if(self.interviewPortalJobDetails.skill != null 
		    				&& self.interviewPortalJobDetails.skill != undefined
		    					&& self.interviewPortalJobDetails.l1Description != null 
		    						&& self.interviewPortalJobDetails.l1Description != undefined
		    							&& self.interviewPortalJobDetails.l1Description != '') {
		    	UpdateJDService.updateJDDetails(self.interviewPortalJobDetails)
		     	 .then(
		     			 function(d) {
		     				console.log("Inside UpdateJDController :: Update Method -->Updated successfully");
		     				reset();
		     				$scope.successfulUpdate= $scope.successfulUpdate = true;
		     				$scope.errorMessage=false;
		     			 },
		     			 function(errResponse){
		             console.error('Inside UpdateJDController :: Update Method, ERROR!! errResponse-->',errResponse);
		           //  $scope.errorMessage = $scope.errorMessage = true;
		             
	  	            $scope.errorMessage="Exception occurred, please contact administrator!";

		         });
		    
		    }else if(self.interviewPortalJobDetails.l1Description == null
					|| self.interviewPortalJobDetails.l1Description == undefined 
						|| self.interviewPortalJobDetails.l1Description == ""){
		    //	alert(" JD Description cannot be empty.")
		    	
  	            $scope.errorMessage="JD Description cannot be empty. Please enter a description.";

		    }
		}
	 
	
	 function reset(){
	       	console.log('Inside UpdateJDController :: Reset Method');
			$scope.successfulUpdate= $scope.successfulUpdate = false;
			$scope.errorMessage=false;
	    	//self.interviewPortalJobDetails={jobdescId:null,skill:'',driveDate:'',l1Description:''};
	    	self.interviewPortalJobDetails={jobdescId:null,skill:'',l1Description:''};

	        $scope.myForm.$setPristine(); //reset Form
	       // $window.location.href = '/ermapp/showUpdateJDforL1';
	    }
}]);
