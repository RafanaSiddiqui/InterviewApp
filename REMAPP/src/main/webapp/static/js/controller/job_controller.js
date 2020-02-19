'use strict';

angular.module('myApp').controller('JobController', ['$scope', 'JobService', function($scope, JobService) {
    var self = this;
    self.jobDetail={jobName:'',jobDescription:'',jobKey:null,api:'',jobClass:'',apiRequestID:null};
    self.apiRequest={id:null,apiName:'',desc:'',endPointUrl:''};
    self.apiRequestList=[];
    self.jobDetailList=[];
    fetchJobList();
    loadAllAPI(); 
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.addJob = addJob;
    self.selectAction = selectAction;
    
    function loadAllAPI(){
    	JobService.loadAllAPI()
    	 .then(
    	            function(d) {
    	                self.apiRequestList = d;
    	            },
            
            function(errResponse){
                console.error('Error while fetching API');
            }
    	 );
        
    }

    function fetchJobList(){
    	JobService.fetchJobList()
    	 .then(
    	            function(d) {
    	                self.jobDetailList = d;
    	            },
            
            function(errResponse){
                console.error('Error while fetching Job');
            }
    	 );
        
    }

    function createJob(jobDetail){
    	JobService.createJob(jobDetail)
            .then(
            	fetchJobList,
            function(errResponse){
                console.error('Error while creating Job');
            }
        );
    }

    function updateJob(jobDetail, jobName){
    	JobService.updateJob(jobDetail, jobName)
            .then(
            		fetchJobList,
            function(errResponse){
                console.error('Error while updating Job');
            }
        );
    }
    
    function deleteJob(jobName){
    	JobService.deleteJob(jobName)
            .then(
            		fetchJobList,
            function(errResponse){
                console.error('Error while deleting Job');
            }
        );
    }
    
    function submit() {
        if(self.jobDetail.jobKey===null){
            console.log('Saving New Job', self.jobDetail.jobName);
            console.log("self.jobDetail.apiRequestID  ::  ",self.jobDetail.apiRequestID);
            //self.jobDetail.apiRequestID=$scope.selectedItem.id;
            createJob(self.jobDetail);
        }else{
        	//self.jobDetail.apiRequestID=$scope.selectedItem.id;
        	updateJob(self.jobDetail, self.jobDetail.jobName);
            console.log('updating Job  ', self.jobDetail.jobName);
        }
        reset();
    }
    
    function edit(jobName1){
    	loadAllAPI();
        console.log('job to be edited', jobName1);
        for(var i = 0; i < self.jobDetailList.length; i++){
        	
        	 console.log('self.jobDetailList[i].jobName-->', self.jobDetailList[i].jobName);
            if(self.jobDetailList[i].jobName === jobName1) {
            	console.log('Found Job', jobName1);
                self.jobDetail = angular.copy(self.jobDetailList[i]);
                $scope.showme=true;
                break;
            }
        }
    }
    
    function remove(jobName1){
        console.log('Job to be deleted', jobName1);
        
        for(var i = 0; i < self.jobDetailList.length; i++){
        	
       	 console.log('self.jobDetailList[i].jobName- deleted->', self.jobDetailList[i].jobName);
           if(self.jobDetailList[i].jobName === jobName1) {
           	console.log('Found Job to be deleted', jobName1);
            reset();
            deleteJob(jobName1);
          
               break;
           }
          
       }
        
       
    }
    
    function reset(){
    	$scope.showme=false;
    	console.log('$scope.showme->', $scope.showme);
    	 self.jobDetail={jobName:'',jobDescription:'',jobKey:null,api:'',jobClass:'',apiRequestID:null};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    function addJob(){
    	$scope.showme=true;
    	
    	console.log('$scope.showme- add job>', $scope.showme);
    	loadAllAPI();
    	console.log('$API List count- add job>', self.apiRequestList.length);
    	
    	  for(var i = 0; i < self.apiRequestList.length; i++){
          	
    	       	 console.log('self.apiRequestList[i].jobName- to be avl in dropdown->', self.apiRequestList[i].apiName);
    	       
    	          
    	       }
    }
    
    function selectAction(){
    	
    	
    	self.jobDetail.apiRequestID=$scope.selectedItem.id;
    	console.log('selectAction -apiRequestID->', self.jobDetail.apiRequestID);
    	
    	
    }
   

}]);
