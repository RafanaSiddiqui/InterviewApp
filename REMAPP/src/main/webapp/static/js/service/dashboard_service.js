'use strict';

angular.module('myApp').factory('DashboardService', ['$http', '$q', function($http, $q){

    var LOAD_PIECHART_DATA = 'loadPieChartData/';
    var LOAD_BARCHART_DATA = 'loadBarChartData/';


    var factory = {
    		loadPieChartData : loadPieChartData,
    		loadBarChartData : loadBarChartData
    };

    return factory;

    function loadPieChartData() {
		var deferred = $q.defer();
		$http
				.get(LOAD_PIECHART_DATA)
				.then(
						function(response) {
							deferred
									.resolve(response.data);
						},
						function(errResponse) {
							console
									.error('Error while loadPieChartData');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	}
    
    function loadBarChartData() {
		var deferred = $q.defer();
		$http
				.get(LOAD_BARCHART_DATA)
				.then(
						function(response) {
							deferred
									.resolve(response.data);
						},
						function(errResponse) {
							console
									.error('Error while loadBarChartData');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	}

    
}]);
