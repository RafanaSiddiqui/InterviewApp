'use strict';

angular.module('myApp').
	controller('PanelL2DashboardController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', '$http', 'CandidateFeedbackService', 'JobDetailsService', 'MasterDemandService', 'UserDetailsService', 
		function($scope, $window, $uibModal, $timeout, $filter,$http, CandidateFeedbackService, JobDetailsService, MasterDemandService, UserDetailsService) {
    
	var self = this;
	
    $scope.interview_date = '';
    $scope.jd_Id  = '';
    
    $scope.interview_level_l2 = 'L2';
    
    getUserDetails();
    loadAllConfigurations();
    
    self.candidateFeedbackL2 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
    		interviewLevelL2:$scope.interview_level_l2,interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',
    		interviewLevelL2PanelName:$scope.loggedInUserFullName,interviewLevelL2PanelId:$scope.loggedInUserId
    		};

	/*self.candidateFeedbackL2 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
    		interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
    		interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''};*/
    
    loadAllScheduledInterviews();
    
    self.submitL2FeedbackForm = submitL2FeedbackForm;
    self.resetL2FeedbackForm = resetL2FeedbackForm;
    
	function loadAllScheduledInterviews() {
	   	console.log('Enter PanelL2DashboardController :: loadAllScheduledInterviews');
	   	
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
	               console.error('Inside PanelL2DashboardController :: loadAllScheduledInterviews, ERROR!!	errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	function loadAllCandidateFeedbackByJdId(jdId) {
	   	console.log('Enter PanelL2DashboardController :: loadAllCandidateFeedbackByJdId, jdId -->', jdId);
	   	
	   	document.getElementById("candidatesDiv").style.visibility = "visible";
	   	
	   	CandidateFeedbackService.loadAllCandidateFeedbacksByJdId(jdId)
	   	 .then(
	            function(f) {
	            	$scope.feedbacksList = f;
	            },
	
	           function(errResponse){
	               console.error('Inside PanelL2DashboardController :: loadAllCandidateFeedbackByJdId, ERROR!! errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	$scope.onSelectRadioL2 = function(d) {
		console.log("Enter PanelL2DashboardController :: onSelectRadio, d --> ", d);
		//resetL2FeedbackForm();
		/*self.candidateFeedbackL2 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
				interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
				interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''};*/
		
		self.candidateFeedbackL2 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
	    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
	    		interviewLevelL2:$scope.interview_level_l2,interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',
	    		interviewLevelL2PanelName:$scope.loggedInUserFullName,interviewLevelL2PanelId:$scope.loggedInUserId
	    		};
		
		$scope.L2FeedbackForm.$setPristine();
		
  	  	loadAllCandidateFeedbackByJdId(d.jobdescId);
  	  	
  	  	document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
  	  	$scope.interview_date = d.driveDate;
  	  	$scope.jd_Id = d.jobdescId;
  	  	
  	  	console.log("Exit PanelL2DashboardController :: onSelectRadio, $scope.interview_date  ::", $scope.interview_date);
  	  	console.log("Exit PanelL2DashboardController :: onSelectRadio, $scope.jd_Id  ::", $scope.jd_Id);
    }

	$scope.onSelectRadioL2CandidateTable = function(f) {
		console.log("Enter PanelL2DashboardController :: onSelectRadioCandidateTable, f --> ", f);
		
		self.candidateFeedbackL2 = f;
		self.candidateFeedbackL2.interviewLevelL2 = $scope.interview_level_l2 ;
		self.candidateFeedbackL2.interviewLevelL2PanelName = $scope.loggedInUserFullName ;
		self.candidateFeedbackL2.interviewLevelL2PanelId = $scope.loggedInUserId ;
		
		document.getElementById("candidatesFeedbackDiv").style.visibility = "visible";
		
		$scope.L2FeedbackDone = self.candidateFeedbackL2.interviewLevelL2Feedback;
		
		if($scope.L2FeedbackDone)
			document.getElementById("submitL2FeedbackButton").style.visibility = "hidden";
		else
			document.getElementById("submitL2FeedbackButton").style.visibility = "visible";
		
	  	console.log("Exit PanelL2DashboardController");
	}
	
	function updateCandidateFeedback(candidateFeedbackL2) {
    	console.log('Enter PanelL2DashboardController :: updateCandidateFeedback, candidateFeedbackL2-->', candidateFeedbackL2);
    	CandidateFeedbackService.updateCandidateFeedback(candidateFeedbackL2)
            .then(
            function(d) {
            	console.log('Inside PanelL2DashboardController :: updateCandidateFeedback, candidateFeedbackL2 --> ', candidateFeedbackL2);
            	resetL2FeedbackForm();
                loadAllCandidateFeedbackByJdId($scope.jd_Id);
            },
            function(errResponse){
            	reset();
                console.error('Inside PanelL2DashboardController :: updateCandidateFeedback, ERROR errResponse-->', errResponse);
            }
        );
    }
	
	function submitL2FeedbackForm(L2FeedbackForm) {
		console.log('Enter PanelL2DashboardController :: submit, self.candidateFeedbackL2-->', self.candidateFeedbackL2);
		self.candidateFeedbackL2.jdId = $scope.jd_Id ;
		self.candidateFeedbackL2.date = new Date();
		updateCandidateFeedback(self.candidateFeedbackL2);
        
	}
	
	function resetL2FeedbackForm() {
		self.candidateFeedbackL2 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
	    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
	    		interviewLevelL2:$scope.interview_level_l2,interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',
	    		interviewLevelL2PanelName:$scope.loggedInUserFullName,interviewLevelL2PanelId:$scope.loggedInUserId
	    		};
		
		/*self.candidateFeedbackL2 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
				interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
				interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''}*/
		
		loadAllCandidateFeedbackByJdId($scope.jd_Id);
		
		document.getElementById('candidateSelected').checked = false;
		document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
		document.getElementById("submitL2FeedbackButton").style.visibility = "hidden";
		
		$scope.L2FeedbackForm.$setPristine();
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
	    	 console.error('Inside PanelL2DashboardController :: getUserDetails, ERROR!! errResponse-->', errResponse);
	        }
	  	 );
	}
	
	
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
	               console.error('Inside PanelL2DashboardController :: loadAllConfigurations, ERROR!! errResponse-->', errResponse);
	           }
	   	 );

	 };
	
}]);