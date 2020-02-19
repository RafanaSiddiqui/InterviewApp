'use strict';

angular.module('myApp').factory('MasterDashboardService', ['$http', '$q', function($http, $q){

    var LOAD_BASE_URL = 'loadBaseUrl/';
    var LOAD_DASHBOARDS_REST_SERVICE_URI = 'fetchDashboards/';


    var factory = {
    		loadBaseUrl : loadBaseUrl,
    		fetchDashboards : fetchDashboards
    };

    return factory;

    function loadBaseUrl() {
		var deferred = $q.defer();
		$http
				.get(LOAD_BASE_URL)
				.then(
						function(response) {
							deferred
									.resolve(response.data);
						},
						function(errResponse) {
							console
									.error('Error while loadBaseUrl');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	}
    
    function fetchDashboards(category) {
        var deferred = $q.defer();
        $http.get(LOAD_DASHBOARDS_REST_SERVICE_URI + category)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetchDashboards ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
