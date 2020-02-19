'use strict';

angular.module('myApp').factory('ApiService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'api/';

    var factory = {
    		loadAllAPI: loadAllAPI,
    		createAPI: createAPI,
    		updateAPI: updateAPI,
    		deleteAPI: deleteAPI
    		
       
    };

    return factory;

    function loadAllAPI() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetchAPIList ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createAPI(apiRequest) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, apiRequest)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating API');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateAPI(apiRequest, id) {
    	console.log('updating API id: --> ', id);
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, apiRequest)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating API');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteAPI(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting API');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
