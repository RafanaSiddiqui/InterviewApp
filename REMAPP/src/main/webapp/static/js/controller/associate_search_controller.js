'use strict';

angular.module('myApp').controller('AssociateSearchController', ['$scope', '$window','AssociateSearchService', 'MasterDemandService', '$uibModal', '$timeout', function($scope, $window, AssociateSearchService, MasterDemandService, $uibModal, $timeout) {
    var self = this;

    loadAllAssociates();
    loadAllGrades();
    
    $scope.associateDetails = {associateID:null, keySkills:null, grade:null, location:null};
		    
    function loadAllAssociates() {
    	 console.log('inside loadAllAssociates');
    	 AssociateSearchService.loadAllAssociates()
    	 .then(
	            function(d) {
	            	$scope.associateList = d;
	                console.log('$scope.associateList  ::  ',$scope.associateList);
	            },

            function(errResponse){
                console.error('Error while fetching Associates');
            }
    	 );

    };
    
    $scope.searchAssociates = function() {
    	console.log("inside searchAssociates $scope.associateDetails  ::  ",$scope.associateDetails);
    	AssociateSearchService.searchAssociates($scope.associateDetails)
    	.then(
    			function(d) {
    				$scope.associateList = d;
    				console.log('$scope.associateList  ::  ',$scope.associateList);
    			},
    			function(errResponse) {
    				console.error('Error while search Associates');
    			}
    			);
    };
    
    function loadAllGrades() {
   	 console.log('inside loadAllGrades');
   	 MasterDemandService.loadAllConfigurationObjs("Grade")
   	 .then(
	            function(d) {
	            	$scope.grades = d;
	            	console.log('$scope.grades  ::  ',$scope.grades);
	            },

           function(errResponse){
               console.error('Error while loadAllGrades');
           }
   	 );

   };
    

}]);
