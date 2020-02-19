'use strict';

angular.module('myApp').
	controller('InterviewedCandidatesViewDashboardController', ['$scope', '$window', '$uibModal', '$timeout', '$filter', 'CandidateFeedbackService', 'JobDetailsService', 
	function($scope, $window, $uibModal, $timeout, $filter, CandidateFeedbackService, JobDetailsService) {
    
	var self = this;

	self.candidateFeedbackROView = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
    		interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
    		interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
    		interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''};
    
    loadAllScheduledInterviews();
    
    $scope.interview_date = '';
    $scope.jd_Id  = '';
    
    self.resetIntCanViewFeedback = resetIntCanViewFeedback;
    
	function loadAllScheduledInterviews() {
	   	console.log('Enter InterviewedCandidatesViewDashboardController :: loadAllScheduledInterviews');
	   	
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
	               console.error('Inside InterviewedCandidatesViewDashboardController :: loadAllScheduledInterviews, ERROR!!	errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	function loadAllCandidateFeedbackByJdId(jdId) {
	   	console.log('Enter InterviewedCandidatesViewDashboardController :: loadAllCandidateFeedbackByJdId, jdId -->', jdId);
	   	
	   	document.getElementById("candidatesDiv").style.visibility = "visible";
	   	
	   	CandidateFeedbackService.loadAllCandidateFeedbacksByJdId(jdId)
	   	 .then(
	            function(f) {
	            	$scope.feedbacksList = f;
	            	
	            	$scope.selectCount = 0;
	            	$scope.rejectCount = 0;
	            	$scope.l1selectcount = 0;
	            	$scope.l1rejectcount = 0;
	            	$scope.l2selectcount = 0;
	            	$scope.l2rejectcount = 0;
	            	$scope.actualfootfall = 0;
	            	
	            	angular.forEach($scope.feedbacksList, function(value, key){
	            		console.log(key + ' : ' + value);
	            		if(value.interviewLevelHRSelectionStatus == "Select") {
	            			$scope.selectCount++;
	            		}
	            		if(value.interviewLevelHRSelectionStatus == "Reject") {
	            			$scope.rejectCount++;
	            		}
	            		if(value.interviewLevelL1SelectionStatus == "Select") {
	            			$scope.l1selectcount++;
	            			$scope.actualfootfall++;
	            		}
	            		if(value.interviewLevelL1SelectionStatus == "Reject") {
	            			$scope.l1rejectcount++;
	            			$scope.actualfootfall++;
	            		}
	            		if(value.interviewLevelL2SelectionStatus == "Select") {
	            			$scope.l2selectcount++;
	            		}
	            		if(value.interviewLevelL2SelectionStatus == "Reject") {
	            			$scope.l2rejectcount++;
	            		}
	            	});
	            	
	            	angular.forEach($scope.scheduledInterviews, function(value, key){
	            		if(jdId == value.jobdescId){
	            			value.selectCount = $scope.selectCount;
	            			value.rejectCount = $scope.rejectCount;
	            			value.l1selectcount = $scope.l1selectcount;
	            			value.l1rejectcount = $scope.l1rejectcount;
	            			value.l2selectcount = $scope.l2selectcount;
	            			value.l2rejectcount = $scope.l2rejectcount;
	            			value.actualfootfall = $scope.actualfootfall;
	            		}
	            	});
	            },
	           function(errResponse){
	               console.error('Inside InterviewedCandidatesViewDashboardController :: loadAllCandidateFeedbackByJdId, ERROR!! errResponse-->', errResponse);
	           }
	   	 );
	
	};
	
	$scope.onSelectRadioL2 = function(d) {
		console.log("Enter InterviewedCandidatesViewDashboardController :: onSelectRadio, d --> ", d);

		self.candidateFeedbackROView = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
				interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
				interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''}
		
  	  	loadAllCandidateFeedbackByJdId(d.jobdescId);
		document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
  	  	$scope.interview_date = d.driveDate;
  	  	$scope.jd_Id = d.jobdescId;
  	  	
  	  	console.log("Exit InterviewedCandidatesViewDashboardController :: onSelectRadio, $scope.interview_date  ::", $scope.interview_date);
  	  	console.log("Exit InterviewedCandidatesViewDashboardController :: onSelectRadio, $scope.jd_Id  ::", $scope.jd_Id);
    }

	$scope.onSelectRadioL2CandidateTable = function(f) {
		console.log("Enter InterviewedCandidatesViewDashboardController :: onSelectRadioCandidateTable, f --> ", f);
		self.candidateFeedbackROView = f;
		document.getElementById("candidatesFeedbackDiv").style.visibility = "visible";
	  	console.log("Exit InterviewedCandidatesViewDashboardController");
	}
	
	function resetIntCanViewFeedback() {
		self.candidateFeedbackROView = {candidateId:null,jdId:'',date:'',fullName:'',contactNumber:'',technology:'',skillset:'',experience:'',relevantExperience:'',
				interviewLevelL1:'',interviewLevelL1Feedback:'',interviewLevelL1SelectionStatus:'',interviewLevelL1PanelName:'',interviewLevelL1PanelName:'',
				interviewLevelL2:'',interviewLevelL2Feedback:'',interviewLevelL2SelectionStatus:'',interviewLevelL2PanelName:'',interviewLevelL2PanelId:'',
				interviewLevelHR:'',interviewLevelHRFeedback:'',interviewLevelHRSelectionStatus:'',interviewLevelHRPanelName:'',interviewLevelHRPanelId:''}
		
		//loadAllCandidateFeedbackByJdId($scope.jd_Id);
		
		//document.getElementById('candidateSelected').checked = false;
		
		var ele = document.getElementsByName("candidateSelected");
		   for(var i=0;i<ele.length;i++)
		      ele[i].checked = false;
		   
		document.getElementById("candidatesFeedbackDiv").style.visibility = "hidden";
	}
	
}]);
