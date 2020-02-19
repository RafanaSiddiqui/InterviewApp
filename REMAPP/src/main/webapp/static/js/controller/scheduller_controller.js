'use strict';

angular.module('myApp').controller('SchedulerController', ['$scope', 'SchedulerService', function($scope, SchedulerService) {
    var self = this;
    self.triggerDetail={triggerName:'',triggerDescription:'',jobKey:'',jobName:'',frequency:'',lastExecutiontime:'',nextFireTime:'',triggerKey:null};
    self.triggerDetailList=[];
    
    self.jobDetail={jobName:'',jobDescription:'',jobKey:null,api:'',jobClass:'',apiRequestID:null};
    self.jobDetailList=[];
    
    fetchTriggerList();
    fetchJobList();
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.selectAction = selectAction;
    self.addTrigger = addTrigger;

    function fetchTriggerList(){
    	SchedulerService.fetchTriggerList()
    	 .then(
    	            function(d) {
    	                self.triggerDetailList = d;
    	            },
            
            function(errResponse){
                console.error('Error while fetching Triggers');
            }
    	 );
        
    }
    
    function fetchJobList(){
    	SchedulerService.fetchJobList()
    	 .then(
    	            function(d) {
    	                self.jobDetailList = d;
    	            },
            
            function(errResponse){
                console.error('Error while fetching Job');
            }
    	 );
        
    }

    function createTrigger(triggerDetail){
    	SchedulerService.createTrigger(triggerDetail)
            .then(
            fetchTriggerList,
            function(errResponse){
                console.error('Error while creating Trigger');
            }
        );
    }

    function updateTrigger(triggerDetail, triggerName){
    	SchedulerService.updateTrigger(triggerDetail, triggerName)
            .then(
            	fetchTriggerList,
            function(errResponse){
                console.error('Error while updating Trigger');
            }
        );
    }
    
    function deleteTrigger(triggerName){
    	SchedulerService.deleteTrigger(triggerName)
            .then(
            	fetchTriggerList,
            function(errResponse){
                console.error('Error while deleting Trigger');
            }
        );
    }
    
    function submit() {
        if(self.triggerDetail.triggerKey===null){
            console.log('Saving New Scheduler', self.triggerDetail.triggerName);
            console.log("self.triggerDetail.jobName  ::  ",self.triggerDetail.jobName);
            //self.triggerDetail.jobName=$scope.selectedItem.jobName;
            createTrigger(self.triggerDetail);
        }else{
        	//self.triggerDetail.jobName=$scope.selectedItem.jobName;
        	console.log("self.triggerDetail.jobName  ::  ",self.triggerDetail.jobName);
        	updateTrigger(self.triggerDetail, self.triggerDetail.triggerName);
            console.log('triggerDetail updated  ', self.triggerDetail.triggerName);
        }
        reset();
    }
    
    function edit(triggerName1){
        console.log('triggerName to be edited', triggerName1);
        for(var i = 0; i < self.triggerDetailList.length; i++){
        	
        	 console.log('self.triggerDetail[i].triggerName-->', self.triggerDetailList[i].triggerName);
            if(self.triggerDetailList[i].triggerName === triggerName1) {
            	console.log('Found Trigger', triggerName1);
                self.triggerDetail = angular.copy(self.triggerDetailList[i]);
                $scope.showme=true;
                break;
            }
        }
    }
    
    function remove(triggerName1){
        console.log('trigger to be deleted', triggerName1);
        
        for(var i = 0; i < self.triggerDetailList.length; i++){
        	
       	 console.log('self.triggerDetail[i].triggerName- deleted->', self.triggerDetailList[i].triggerName);
           if(self.triggerDetailList[i].triggerName === triggerName1) {
           	console.log('Found Trigger to be deleted', triggerName1);
            reset();
            deleteTrigger(triggerName1);
               break;
           }
          
       }
        
       
    }
    
    function reset(){
    	$scope.showme=false;
    	console.log('$scope.showme->', $scope.showme);
    	self.triggerDetail={triggerName:'',triggerDescription:'',jobKey:'',jobName:'',frequency:'',lastExecutiontime:'',nextFireTime:'',triggerKey:null};
    	self.triggerDetail.jobName='';
    		
        $scope.myForm.$setPristine(); //reset Form
        fetchTriggerList();
        fetchJobList();
    }
    
  function selectAction(){
    	
    	console.log('selectAction  name-->', $scope.selectedItem.jobName);
    	
    	self.triggerDetail.jobName=$scope.selectedItem.jobName;
    	console.log('selectAction -Job Name->', self.jobDetail.jobName);
    	
    	
    }
  
  
  function addTrigger(){
  	$scope.showme=true;
  
  
  }

}]);
