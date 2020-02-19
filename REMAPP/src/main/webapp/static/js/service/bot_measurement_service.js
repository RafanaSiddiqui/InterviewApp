'use strict';

angular.module('myApp').factory('BotMeasurementService', ['$http', '$q', function($http, $q) {

    var BOT_MEASUREMENT_REST_SERVICE = '/ermapp/botMeasurementRequest/';
    var LOAD_ALL_BOT_MEASUREMENT_REST_SERVICE_URI = '/ermapp/loadAllBotMeasurements/';
    var LOAD_BOT_MEASUREMENT_BY_ID_REST_SERVICE = '/ermapp/loadBotMeasurementById/'
    var UPDATE_BOT_MEASUREMENT_REST_SERVICE_URI = '/ermapp/updateBotMeasurement/';
    var DELETE_BOT_MEASUREMENT_REST_SERVICE_URI = '/ermapp/deleteBotMeasurement/';
    var DEFAULTER_LIST_REST_SERVICE_URI = '/ermapp/getDefaulters/';
    var LOAD_ALL_ACCOUNTBASES_REST_SERVICE_URI = '/ermapp/loadAllAccountBases/';
    
    var factory = {
		loadAllBotMeasurements : loadAllBotMeasurements,
		loadBotMeasurementById : loadBotMeasurementById,
		addBotMeasurement : addBotMeasurement,
		updateBotMeasurement : updateBotMeasurement,
		deleteBotMeasurement : deleteBotMeasurement,
		getDefaulterList : getDefaulterList,
		loadAllAccountBase : loadAllAccountBase
    };

    return factory;

    function loadAllBotMeasurements() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_BOT_MEASUREMENT_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllBotMeasurements in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadBotMeasurementById(botMeasurementId) {
        var deferred = $q.defer();
        $http.get(LOAD_BOT_MEASUREMENT_BY_ID_REST_SERVICE + botMeasurementId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadBotMeasurementById ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addBotMeasurement(botMeasurement) {
    	console.log('inside BotService addBot botMeasurement  ::  ',botMeasurement);
        var deferred = $q.defer();
        $http.post(BOT_MEASUREMENT_REST_SERVICE, botMeasurement)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while adding bot in bot service');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateBotMeasurement(botMeasurement) {
        var deferred = $q.defer();
        $http.put(UPDATE_BOT_MEASUREMENT_REST_SERVICE_URI, botMeasurement)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating botMeasurement in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteBotMeasurement(botMeasurementId) {
        var deferred = $q.defer();
        $http.delete(DELETE_BOT_MEASUREMENT_REST_SERVICE_URI + botMeasurementId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('bot deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting botMeasurement in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getDefaulterList(botMonth) {
        var deferred = $q.defer();
        $http.get(DEFAULTER_LIST_REST_SERVICE_URI + botMonth)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while getDefaulterList in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadAllAccountBase() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_ACCOUNTBASES_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllAccountBases in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
