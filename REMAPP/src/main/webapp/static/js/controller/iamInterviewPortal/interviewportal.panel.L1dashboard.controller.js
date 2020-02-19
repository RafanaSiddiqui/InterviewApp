'use strict';

angular.module('myApp').
	controller('PanelL1DashboardController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', '$http', 'CandidateFeedbackService', 'JobDetailsService', 'MasterDemandService', 'UserDetailsService', 
		function($scope, $window, $uibModal, $timeout, $filter,$http, CandidateFeedbackService, JobDetailsService, MasterDemandService, UserDetailsService) {

    var self = this;
    
    $scope.interview_date = '';
    $scope.jd_Id  = '';
    
    $scope.interview_level_l1 = 'L1';
    
    getUserDetails();
    loadAllConfigurations();
    
    self.candidateFeedbackL1 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
    		interviewLevelL1:$scope.interview_level_l1,interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',
    		interviewLevelL1PanelName:$scope.loggedInUserFullName,interviewLevelL1PanelId:$scope.loggedInUserId
    		};
    
    loadAllScheduledInterviews();
    
    self.submit = submit;
    self.reset = reset;
    
    self.addCandidateFeedback = addCandidateFeedback;
    
	function loadAllScheduledInterviews() {
	   	console.log('Enter PanelL1DashboardController :: loadAllScheduledInterviews', $scope.fullNameL1);
	   	
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
		            		//var crDate = yyyy+'-'+mm+'-'+dd;
		            		
		            		value.driveDate = crDate;
		            		}
		            		
		            	});
		            },
	           function(errResponse){
	               console.error('Inside PanelL1DashboardController :: loadAllScheduledInterviews, ERROR!!	errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
/*	function uploadCV(jobDetailId) {
		 $scope.errorMessage=false;
		 
		$window.location.href = '/ermapp/interview/hr/cvUpload/'+jobDetailId;
	}
	*/
	
	function loadAllCandidateFeedbackByJdId(jdId) {
	   	console.log('Enter PanelL1DashboardController :: loadAllCandidateFeedbackByJdId, jdId -->', jdId);
	   	
	   	document.getElementById("candidatesDiv").style.visibility = "visible";
	   	document.getElementById("candidatesFeedbackDiv").style.visibility = "visible"; 
	   	
	   	CandidateFeedbackService.loadAllCandidateFeedbacksByJdId(jdId)
	   	 .then(
		            function(f) {
		            	$scope.feedbacksList = f;
		            },
	
	           function(errResponse){
	               console.error('Inside PanelL1DashboardController :: loadAllCandidateFeedbackByJdId, ERROR!! errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	$scope.onSelectRadio = function(d) {
		console.log("Enter PanelL1DashboardController :: onSelectRadio, d --> ", d);
		reset();
  	  	loadAllCandidateFeedbackByJdId(d.jobdescId);
  	  	$scope.interview_date = d.driveDate;
  	  	$scope.jd_Id = d.jobdescId;
  	  	console.log("Exit PanelL1DashboardController :: onSelectRadio, $scope.interview_date  ::", $scope.interview_date);
  	  	console.log("Exit PanelL1DashboardController :: onSelectRadio, $scope.jd_Id  ::", $scope.jd_Id);
    }
	
	function addCandidateFeedback(candidateFeedbackL1) {
    	console.log('Enter PanelL1DashboardController :: addCandidateFeedback --> ', candidateFeedbackL1);
    	CandidateFeedbackService.addCandidateFeedback(candidateFeedbackL1)
            .then(
            function(d) {
            	console.log('Enter PanelL1DashboardController :: addCandidateFeedback --> ', candidateFeedbackL1);
            	reset();
                loadAllCandidateFeedbackByJdId($scope.jd_Id);
            },
            function(errResponse){
            	reset();
                console.error('Inside PanelL1DashboardController :: addCandidateFeedback, ERROR errResponse-->', errResponse);
            }
        );
    }
	
	function submit(L1FeedbackForm) {
		console.log('Enter PanelL1DashboardController :: submit, self.candidateFeedbackL1 -->', self.candidateFeedbackL1);
		self.candidateFeedbackL1.jdId = $scope.jd_Id ;
		//self.candidateFeedbackL1.date = $scope.interview_date;
		self.candidateFeedbackL1.date = new Date();
    	addCandidateFeedback(self.candidateFeedbackL1);
        
	}
	
	function reset() {
		self.candidateFeedbackL1 = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:$scope.interview_level_l1,interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',
				interviewLevelL1PanelName:$scope.loggedInUserFullName,interviewLevelL1PanelId:$scope.loggedInUserId
				};
		
		
		$scope.L1FeedbackForm.$setPristine();
	}
	
	function getUserDetails()
	{
		//console.log('Enter PanelL1DashboardController :: getUserDetails');
		UserDetailsService.getUserRole()
		.then( function(d) {
			$scope.loggedInUserFullName = d.firstName + ' ' + d.lastName;
			$scope.loggedInUserId = d.username;
	     },
	        function(errResponse){
	    	 console.error('Inside PanelL1DashboardController :: getUserDetails, ERROR!! errResponse-->', errResponse);
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
	   	 //console.log('Enter PanelL1DashboardController :: loadAllConfigurations');
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
	               console.error('Inside PanelL1DashboardController :: loadAllConfigurations, ERROR!! errResponse-->', errResponse);
	           }
	   	 );

	 };
	
}]);
