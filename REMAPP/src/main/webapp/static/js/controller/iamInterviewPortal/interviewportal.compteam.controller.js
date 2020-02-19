'use strict';

angular.module('myApp').controller('InterviewAdminController', ['$scope', '$window','InterviewAdminService', '$uibModal', '$http', function($scope, $window, InterviewAdminService, $uibModal, $http) {
    var self = this;

    self.getTopPanel = getTopPanel;
   
     
   
   loadAllTopPanel(); // to be enable /disabled
   
   function loadAllTopPanel() {
	  
       getTopPanel(30);
  };
  
 function getTopPanel(days) {	
	   
	   InterviewAdminService.getTopPanel(days)
	   	 .then(
		            function(d) {
		            	$scope.allPanel = d;
		            },
	           function(errResponse){
	               console.error('Error while fetching Requests');
	           }
	   	 );	   
	};
    

}]);
