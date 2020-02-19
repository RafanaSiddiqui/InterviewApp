'use strict';

angular.module('myApp').controller('EmailController', ['$scope', 'EmailService', function($scope, EmailService) {
    var self = this;
    
    self.emailTemplate= {id:null,name:'',templateID:'',description:'',from:'',subject:'',to:'',cc:'',message:''};
    
    self.emailTemplateList=[];
    fetchEmailTemplateList();
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    function fetchEmailTemplateList(){
    	EmailService.fetchEmailTemplateList()
    	 .then(
    	            function(d) {
    	                self.emailTemplateList = d;
    	            },
            
            function(errResponse){
                console.error('Error while fetching EmailTemplate');
            }
    	 );
        
    }

    function createEmail(emailTemplate){
    	EmailService.createEmail(emailTemplate)
            .then(
            	fetchEmailTemplateList,
            function(errResponse){
                console.error('Error while creating EmailTemplate');
            }
        );
    }

    function updateEmail(emailTemplate, id){
    	EmailService.updateEmail(emailTemplate, id)
            .then(
            		fetchEmailTemplateList,
            function(errResponse){
                console.error('Error while updating EmailTemplate');
            }
        );
    }
    
    function deleteEmail(id){
    	EmailService.deleteEmail(id)
            .then(
            		fetchEmailTemplateList,
            function(errResponse){
                console.error('Error while deleting EmailTemplate');
            }
        );
    }
    
    function submit() {
        if(self.emailTemplate.id===null){
            console.log('Saving New Email Template', self.emailTemplate.name);
            createEmail(self.emailTemplate);
        }else{
        	updateEmail(self.emailTemplate, self.emailTemplate.id);
            console.log('updating Job  ', self.emailTemplate.name);
        }
        reset();
    }
    
    function edit(id1){
        console.log('template to be edited', id1);
        for(var i = 0; i < self.emailTemplateList.length; i++){
        	
        	 console.log('self.emailTemplateList[i].name-->', self.emailTemplateList[i].name);
            if(self.emailTemplateList[i].id === id1) {
            	console.log('Found Job', id1);
                self.emailTemplate = angular.copy(self.emailTemplateList[i]);
                $scope.showme=true;
                break;
            }
        }
    }
    
    function remove(id1){
        console.log('Job to be deleted', id1);
        
        for(var i = 0; i < self.emailTemplateList.length; i++){
        	
       	 console.log('self.emailTemplateList[i].name- deleted->', self.emailTemplateList[i].name);
           if(self.emailTemplateList[i].id === id1) {
           	console.log('Found Job to be deleted', id1);
            reset();
            deleteEmail(id1);
               break;
           }
          
       }
        
       
    }
    
    function reset(){
    	$scope.showme=false;
    	 console.log('$scope.showme->', $scope.showme);
    	  self.emailTemplate= {id:null,name:'',templateID:'',description:'',from:'',subject:'',to:'',cc:'',message:''};
    	  
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
