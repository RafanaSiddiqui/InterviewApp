'use strict';

angular.module('myApp').factory('MasterDemandService', ['$http', '$q', function($http, $q){

    var MASTER_DEMAND_REQUEST_REST_SERVICE_URI = '/ermapp/masterDemandRequest/';
    var LOAD_DEMAND_REQUEST_REST_SERVICE_URI = '/ermapp/loadMasterDemandRequest/';
    var LOAD_PAGEABLE_DEMAND_REQUEST_REST_SERVICE_URI = '/ermapp/loadPageableMasterDemandRequest/';
    var LOAD_PAGEABLE_DEMAND_REQUEST_BY_SOID_REST_SERVICE_URI = '/ermapp/loadPageableDemandReqBySoId/';
    var LOAD_ALL_CATEGORIES_OBJ_REST_SERVICE_URI = '/ermapp/fetchConfiguration/';
    var FLAG_SELECT_SEND_MAIL = '/ermapp/flagSelectSendMail/';
    var LOAD_ALL_CONF_REST_SERVICE_URI = '/ermapp/loadAllConfiguration/';

    var factory = {
    		loadAllDemandRequests : loadAllDemandRequests,
    		loadDemandRequestById : loadDemandRequestById,
    		createDemandRequest : createDemandRequest,
    		updateDemandRequest : updateDemandRequest,
    		deleteDemandRequest : deleteDemandRequest,
    		loadAllConfigurations : loadAllConfigurations,
    		loadAllDemandPageableRequests : loadAllDemandPageableRequests,
    		loadAllDemandReqBySoId : loadAllDemandReqBySoId,
    		loadAllConfigurationObjs : loadAllConfigurationObjs,
    		flagSelectSendMail : flagSelectSendMail
    };

    return factory;

    function loadAllDemandRequests() {
        var deferred = $q.defer();
        $http.get(LOAD_DEMAND_REQUEST_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllDemandRequests ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function flagSelectSendMail(flagSelect, soId) {
        var deferred = $q.defer();
        $http.get(FLAG_SELECT_SEND_MAIL + flagSelect + '/soId/' + soId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while flagSelectSendMail ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadAllDemandPageableRequests(pageNumber, pageSize) {
    	pageNumber = pageNumber > 0?pageNumber - 1:0;
        var deferred = $q.defer();
        $http.get(LOAD_PAGEABLE_DEMAND_REQUEST_REST_SERVICE_URI + '/' + pageNumber + '/size/' + pageSize)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllDemandPageableRequests ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function loadAllDemandReqBySoId(soId, pageNumber, pageSize) {
    	pageNumber = pageNumber > 0?pageNumber - 1:0;
        var deferred = $q.defer();
        $http.get(LOAD_PAGEABLE_DEMAND_REQUEST_BY_SOID_REST_SERVICE_URI + '/' + pageNumber + '/size/' + pageSize + '/soId/' + soId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllDemandReqBySoId ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadDemandRequestById(demandRequestID) {
        var deferred = $q.defer();
        $http.get(LOAD_DEMAND_REQUEST_REST_SERVICE_URI + demandRequestID)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllDemandRequests ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createDemandRequest(demandRequest) {
    	console.log('inside MasterDemandService createDemandRequest demandRequest  ::  ',demandRequest);
        var deferred = $q.defer();
        $http.post(MASTER_DEMAND_REQUEST_REST_SERVICE_URI, demandRequest)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating demandRequest');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateDemandRequest(demandRequest) {
        var deferred = $q.defer();
        $http.put(MASTER_DEMAND_REQUEST_REST_SERVICE_URI, demandRequest)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating demandRequest');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteDemandRequest(demandRequestId) {
        var deferred = $q.defer();
        $http.delete(MASTER_DEMAND_REQUEST_REST_SERVICE_URI + demandRequestId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('demandRequest deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting demandRequestId');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadAllConfigurations() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_CONF_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllConfiguration ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadAllConfigurationObjs(category) {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_CATEGORIES_OBJ_REST_SERVICE_URI + category)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllConfigurationObjs ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


}]);
