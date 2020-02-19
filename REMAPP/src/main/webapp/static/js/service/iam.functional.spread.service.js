'use strict';

angular.module('myApp').factory('IAMFunctionalSpreadService', ['$http', '$q', function($http, $q) {

    var IAM_FUNCTION_SPREAD_REST_SERVICE = '/ermapp/iamFunctionalSpreadRequest/';
    var LOAD_ALL_IAM_FUNCTION_SPREADS_REST_SERVICE_URI = '/ermapp/loadAllIAMFunctionalSpreads/';
    var LOAD_IAM_FUNCTION_SPREAD_BY_ID_REST_SERVICE = '/ermapp/loadIAMFunctionalSpreadById/'
    var UPDATE_IAM_FUNCTION_SPREAD_REST_SERVICE_URI = '/ermapp/updateIAMFunctionalSpread/';
    var DELETE_IAM_FUNCTION_SPREAD_REST_SERVICE_URI = '/ermapp/deleteIAMFunctionalSpread/';

    var factory = {
    		loadAllIAMFunctionalSpread : loadAllIAMFunctionalSpread,
    		loadIAMFunctionalSpreadById : loadIAMFunctionalSpreadById,
    		addIAMFunctionalSpread : addIAMFunctionalSpread,
    		updateIAMFunctionalSpread : updateIAMFunctionalSpread,
    		deleteIAMFunctionalSpread : deleteIAMFunctionalSpread
    };

    return factory;

    function loadAllIAMFunctionalSpread() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_IAM_FUNCTION_SPREADS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllIAMFunctionalSpreads in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadIAMFunctionalSpreadById(iamFunctionalSpreadId) {
        var deferred = $q.defer();
        $http.get(LOAD_IAM_FUNCTION_SPREAD_BY_ID_REST_SERVICE + iamFunctionalSpreadId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadIAMFunctionalSpreadById ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addIAMFunctionalSpread(iamFunctionalSpread) {
        var deferred = $q.defer();
        $http.post(IAM_FUNCTION_SPREAD_REST_SERVICE, iamFunctionalSpread)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while adding IAMFunctionalSpread in IAMFunctionalSpread service');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateIAMFunctionalSpread(iamFunctionalSpread) {
        var deferred = $q.defer();
        $http.put(UPDATE_IAM_FUNCTION_SPREAD_REST_SERVICE_URI, iamFunctionalSpread)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating IAMFunctionalSpread in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteIAMFunctionalSpread(iamFunctionalSpreadId) {
        var deferred = $q.defer();
        $http.delete(DELETE_IAM_FUNCTION_SPREAD_REST_SERVICE_URI + iamFunctionalSpreadId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('bot deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting IAMFunctionalSpread in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
