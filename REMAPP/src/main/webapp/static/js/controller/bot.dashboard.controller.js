'use strict';

angular.module('myApp').controller('BotDashboardController', ['$scope', 'BotDashboardService', '$sce', function($scope, BotDashboardService, $sce) {
	
	loadBaseUrl();
	loadAllDashboards();
	
	function loadBaseUrl() {
     	 console.log('loadBaseUrl');
     	BotDashboardService.loadBaseUrl()
     	 .then(
     	            function(d) {
     	            	$scope.baseUrl = d[0];
     	            	console.log('$scope.baseUrl  ::  '+$scope.baseUrl);
     	            },

             function(errResponse){
                 console.error('Error while loading loadBaseUrl');
             }
     	 );

     };
     
     $scope.trustSrc = function(src) {
    	    return $sce.trustAsResourceUrl(src);
     }
     
     $scope.dashboard = {};
     
     function loadAllDashboards() {
    	 console.log('inside loadAllDashboards');
    	 BotDashboardService.fetchDashboards("BotDashBoardUrls")
    	 .then(
	            function(d) {
	            	$scope.dashboards = d;
	            	console.log('$scope.dashboards  ::  ',$scope.dashboards);
	            	$scope.dashBoardUrl = $sce.trustAsResourceUrl($scope.dashboards[0].value);
	            	$scope.dashboard = $scope.dashboards[0];
	            },

            function(errResponse){
                console.error('Error while loadAllDashboards');
            }
    	 );

    };
    
    $scope.showDashboard = function(url) {
    	console.log("inside showDashboard url  ::  ",url.value);
    	$scope.dashBoardUrl = url.value;
    	$scope.dashBoardUrl = $sce.trustAsResourceUrl($scope.dashBoardUrl);
    }
	
}]);
