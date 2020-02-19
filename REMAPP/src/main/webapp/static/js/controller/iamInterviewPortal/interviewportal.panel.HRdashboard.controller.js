'use strict';

angular.module('myApp').
	controller('PanelHRDashboardController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', 'CandidateFeedbackService', 'JobDetailsService', 'MasterDemandService', 'UserDetailsService', 
		function($scope, $window, $uibModal, $timeout, $filter, CandidateFeedbackService, JobDetailsService, MasterDemandService, UserDetailsService) {
    
	var self = this;
	
	$scope.interview_date = '';
    $scope.jd_Id  = '';
    
    $scope.interview_level_hr = 'HR';
    
    getUserDetails();
    loadAllConfigurations();
    
    self.candidateFeedbackHR = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
    		interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
    		interviewLevelHR:$scope.interview_level_hr,interviewLevelHRFeedback:'',interviewLevelHRSeletionStatus:'',
    		interviewLevelHRPanelName:$scope.loggedInUserFullName,interviewLevelHRPanelId:$scope.loggedInUserFullName};

	/*self.candidateFeedbackHR = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
    		interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
    		interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''};*/
    
    loadAllScheduledInterviews();
    
    self.submitHRFeedbackForm = submitHRFeedbackForm;
    self.resetHRFeedbackForm = resetHRFeedbackForm;
    
	function loadAllScheduledInterviews() {
	   	console.log('Enter PanelHRDashboardController :: loadAllScheduledInterviews');
	   	
	   	document.getElementById("candidatesDiv").style.visibility = "hidden";
	   	document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
	   	
	   	JobDetailsService.loadAllJobPanelList()
	   	 .then(
		            function(d) {
		            	$scope.scheduledInterviews = d;
		            	angular.forEach($scope.scheduledInterviews, function(value, key){
		            		//console.log('Inside PanelDashboardController :: loadAllScheduledInterviews, value  ::  '+value);
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
		            		var crDate = dd+'-'+mm+'-'+yyyy;
		            		value.driveDate = crDate;
		            		}
		            		
		            	});
		            },
	           function(errResponse){
	               console.error('Inside PanelHRDashboardController :: loadAllScheduledInterviews, ERROR!!	errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	function loadAllCandidateFeedbackByJdId(jdId) {
	   	console.log('Enter PanelHRDashboardController :: loadAllCandidateFeedbackByJdId, jdId -->', jdId);
	   	
	   	document.getElementById("candidatesDiv").style.visibility = "visible";
	   	
	   	CandidateFeedbackService.loadAllCandidateFeedbacksByJdId(jdId)
	   	 .then(
	            function(f) {
	            	$scope.feedbacksList = f;
	            },
	
	           function(errResponse){
	               console.error('Inside PanelHRDashboardController :: loadAllCandidateFeedbackByJdId, ERROR!! errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	$scope.onSelectRadioL2 = function(d) {
		console.log("Enter PanelHRDashboardController :: onSelectRadio, d --> ", d);
		//resetHRFeedbackForm();
		/*self.candidateFeedbackHR = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
				interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
				interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''};*/
		
		self.candidateFeedbackHR = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
	    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
	    		interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
	    		interviewLevelHR:$scope.interview_level_hr,interviewLevelHRFeedback:'',interviewLevelHRSeletionStatus:'',
	    		interviewLevelHRPanelName:$scope.loggedInUserFullName,interviewLevelHRPanelId:$scope.loggedInUserFullName};
		
		$scope.HRFeedbackForm.$setPristine();
		
  	  	loadAllCandidateFeedbackByJdId(d.jobdescId);
  	  	document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
  	  	
  	  	$scope.interview_date = d.driveDate;
  	  	$scope.jd_Id = d.jobdescId;
  	  	
  	  	console.log("Exit PanelHRDashboardController :: onSelectRadio, $scope.interview_date  ::", $scope.interview_date);
  	  	console.log("Exit PanelHRDashboardController :: onSelectRadio, $scope.jd_Id  ::", $scope.jd_Id);
    }

	$scope.onSelectRadioL2CandidateTable = function(f) {
		console.log("Enter PanelHRDashboardController :: onSelectRadioCandidateTable, f --> ", f);
		
		document.getElementById("candidatesFeedbackDiv").style.visibility = "visible";
		
		self.candidateFeedbackHR = f;
		self.candidateFeedbackHR.interviewLevelHR = $scope.interview_level_hr ;
		self.candidateFeedbackHR.interviewLevelHRPanelName = $scope.loggedInUserFullName ;
		self.candidateFeedbackHR.interviewLevelHRPanelId = $scope.loggedInUserId ;
		
	  	console.log("Exit PanelHRDashboardController");
	}
	
	function updateCandidateFeedback(candidateFeedbackHR) {
    	console.log('Enter PanelHRDashboardController :: updateCandidateFeedback, candidateFeedbackHR-->', candidateFeedbackHR);
    	CandidateFeedbackService.updateCandidateFeedback(candidateFeedbackHR)
            .then(
            function(d) {
            	console.log('Inside PanelHRDashboardController :: updateCandidateFeedback, candidateFeedbackHR --> ', candidateFeedbackHR);
            	resetHRFeedbackForm();
                loadAllCandidateFeedbackByJdId($scope.jd_Id);
            },
            function(errResponse){
            	reset();
                console.error('Inside PanelHRDashboardController :: updateCandidateFeedback, ERROR errResponse-->', errResponse);
            }
        );
    }
	
	function submitHRFeedbackForm(HRFeedbackForm) {
		console.log('Enter PanelHRDashboardController :: submit, self.candidateFeedbackHR-->', self.candidateFeedbackHR);
		self.candidateFeedbackHR.jdId = $scope.jd_Id ;
		self.candidateFeedbackHR.date = new Date();
		updateCandidateFeedback(self.candidateFeedbackHR);
	}
	
	function resetHRFeedbackForm() {
		self.candidateFeedbackHR = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
	    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
	    		interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
	    		interviewLevelHR:$scope.interview_level_hr,interviewLevelHRFeedback:'',interviewLevelHRSeletionStatus:'',
	    		interviewLevelHRPanelName:$scope.loggedInUserFullName,interviewLevelHRPanelId:$scope.loggedInUserFullName};
		
		/*self.candidateFeedbackHR = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
				interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
				interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''}*/
		
		loadAllCandidateFeedbackByJdId($scope.jd_Id);
		
		document.getElementById('candidateSelected').checked = false;
		document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
		
		$scope.HRFeedbackForm.$setPristine();
	}
	
	function getUserDetails()
	{
		//console.log('Enter PanelHRDashboardController :: getUserDetails');
		UserDetailsService.getUserRole()
		.then( function(d) {
			$scope.loggedInUserFullName = d.firstName + ' ' + d.lastName;
			$scope.loggedInUserId = d.username;
	     },
	        function(errResponse){
	    	 console.error('Inside PanelHRDashboardController :: getUserDetails, ERROR!! errResponse-->', errResponse);
	        }
	  	 );
	}
	
	function loadAllConfigurations() {
	   	 //console.log('Enter PanelHRDashboardController :: loadAllConfigurations');
	   	 MasterDemandService.loadAllConfigurations()
	   	 .then(
		            function(d) {
		            	$scope.allConfList = d;
		            	//console.log('$scope.allConfList  ::  ',$scope.allConfList);
		            	angular.forEach($scope.allConfList, function(value, key){
		            		//console.log('value.category  ::  '+value.category);
		            		if(value.category == "IntCandidateStatuses") {
		            			$scope.IntCandidateStatus = value.configurationList;
		            		}
		            		//console.log('value.configurationList  ::  ',value.configurationList);
		            	});
		            },

	           function(errResponse){
	               console.error('Inside PanelHRDashboardController :: loadAllConfigurations, ERROR!! errResponse-->', errResponse);
	           }
	   	 );

	 };
	
}]);
