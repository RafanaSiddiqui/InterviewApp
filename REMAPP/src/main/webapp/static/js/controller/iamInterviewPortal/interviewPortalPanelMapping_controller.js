'use strict';

angular.module('myApp').controller('panelMappingController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', 'ViewPanelService', 'JobDetailsService', function($scope, $window, $uibModal, $timeout, $filter, ViewPanelService, JobDetailsService) {
    var self = this;
    
    $scope.showPanel = false;
    $scope.successfulInvite = false;
    $scope.errorMessage = false;

    
   // $scope.Jd_Id = null;
    $scope.driveDate = null;
    
	self.jdPanel={id:null, jobID:null, skill:'', panelID:null,interviewDate:null, interviewLevel:''};
	
	self.invite = invite;  

    loadAllScheduledInterviews();
    
    
	function loadAllScheduledInterviews() {
	   	console.log('Inside panelMappingController :: loadAllScheduledInterviews');
	   	JobDetailsService.loadAllJobPanelList()
	   	 .then(
		            function(d) {
		            	$scope.scheduledInterviews = d;
		            	console.log('Inside panelMappingController :: loadAllScheduledInterviews'+ $scope.scheduledInterviews);
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
	      				$scope.errorMessage= false;

		            },
	
	           function(errResponse){
	               console.error('Inside panelMappingController, error in loadAllScheduledInterviews');
	  	            $scope.errorMessage="Exception occurred, please contact administrator!!";

	              
	           }
	   	 );
	
	};
	
	
	
	function loadPanelForSkill(skill) {
	   	console.log('Inside panelMappingController :: loadPanelForSkill, skill -->' + skill);

	   	ViewPanelService.loadPanelForSkill(skill)
	   	 .then(
		            function(p) {
		            	$scope.availablePanelList = p;

	      				$scope.errorMessage =  false;
		            },
	
	           function(errResponse){
	               console.error('Inside panelMappingController, error in loadPanelForSkill');
	  	            $scope.errorMessage="Error occurred while loading panel for the skill, please contact administrator!!";
	           }
	   	 );

	};
	
	
	
	$scope.onSelectRadio = function(d) {
  	  
		console.log("Enter onSelectRadio  ::  ",d);
	  	$scope.showPanel = $scope.showPanel = true;
	  	reset();
	  	$scope.Jd_Id = d.jobdescId;
	  	$scope.skill = d.skill;
	    $scope.driveDate = d.driveDate;
	    $scope.IsAllChecked = false;
	  	loadPanelForSkill(d.skill);
   
	  	console.log("Exit onSelectRadio");
    }
	
	
	 $scope.CheckUncheckHeader = function () {
		 
		
		 if($scope.availablePanelList != undefined ) {
			 $scope.EnableDisableInviteBtn();
			//console.log('  $scope.availablePanelList.length '+ $scope.availablePanelList.length);
	         $scope.IsAllChecked = true;
	         for (var i = 0; i < $scope.availablePanelList.length; i++) {
	        	 
	             if (!$scope.availablePanelList[i].Selected) {
	                 $scope.IsAllChecked = false;
	                 break;
	             }
	         };
	 	}
     };
     

	 $scope.EnableDisableInviteBtn = function () {
		 
			
		 if($scope.availablePanelList != undefined ) {
			
			//console.log(' in $scope.EnableDisableInviteBtn ');
	         for (var i = 0; i < $scope.availablePanelList.length; i++) {
	 		//	console.log('  $scope.availablePanelList.length   i '+  i);	        	 
	             if ($scope.availablePanelList[i].Selected) {
	                 $scope.disableInviteBtn = false;
	                 break;
	             }else{
	            	 $scope.disableInviteBtn = true;
	             }
	             
	         };
	 	}
     };
     
	
	
	 $scope.CheckUncheckAll = function () {
		 
         for (var i = 0; i < $scope.availablePanelList.length; i++) {
             $scope.availablePanelList[i].Selected = $scope.IsAllChecked;
             if (!$scope.availablePanelList[i].Selected) {
            	 $scope.disableInviteBtn = true;
             }
            else{
           	 $scope.disableInviteBtn = false;
            }
         }
     };

     
     
     function reset(){
	       	if($scope.availablePanelList != undefined ) {
		        for (var i = 0; i < $scope.availablePanelList.length; i++) {
		        	$scope.availablePanelList[i].Selected = false;
		          };
		         
		        $scope.CheckUncheckHeader();
	       }
			$scope.successfulInvite = $scope.successfulInvite = false;
			$scope.errorMessage = $scope.errorMessage = false;
	        $scope.myForm.$setPristine(); //reset Form
	       // $window.location.href = '/ermapp/showUpdateJDforL1';
	    }
		  
   
	function invite(myForm){

       	
    	angular.forEach(self.selectedPanel, function (value, key) {
    		
    		self.jdPanel={id:null, jobID:null, skill:'', panelID:'',interviewDate:null, interviewLevel:''};

    		
    		if(self.interviewLevel == null
					|| self.interviewLevel == undefined 
						|| self.interviewLevel == "")
    		{
  	            $scope.errorMessage="Please select an interview round.";

    		}else{
    		
	    		self.jdPanel.jobID = $scope.Jd_Id;
	    		self.jdPanel.skill = $scope.skill;
	    		self.jdPanel.panelID = value.empId;
	    		self.jdPanel.interviewLevel = self.interviewLevel;    		
	    		self.jdPanel.interviewDate = new Date($scope.driveDate);
	    		console.log("Inside panelMappingController: invite method : jdPanel -----> ",self.jdPanel);
	    		
	
	    		ViewPanelService.mapPanelForInterview(self.jdPanel)
		     	 .then(
		     			 function(d) {
		     				console.log("Inside panelMappingController: invite method : Updated successfully");
		     				$scope.successfulInvite = $scope.successfulInvite = true;
		      				$scope.errorMessage = false;
	
		     		        $scope.myForm.$setPristine(); //reset Form
	
		     			 },
		     			 function(errResponse){
		             console.error('"Inside panelMappingController: invite method: Error while adding Panel'+errResponse);
		  	         $scope.errorMessage="Error occurred while inviting the panel, please contact administrator!!";
	
		         });
    		}
    	//	self.userInfo.userRolesList.push({roleId:'', roleName:value});
    		
    	});

    
    
    }
	
	
}]);
    