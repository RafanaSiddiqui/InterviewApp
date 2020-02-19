'use strict';

angular.module('myApp').factory('NoBotService', ['$http', '$q', function($http, $q) {

	var NOBOT_REST_SERVICE = '/ermapp/noBotRequest/';
    var LOAD_ALL_NOBOTS_REST_SERVICE_URI = '/ermapp/loadAllNoBots/';
    var LOAD_ALL_NOBOTSBY_STATUS_REST_SERVICE_URI = '/ermapp/loadAllNoBotsByStatus/';
    var LOAD_NOBOT_BY_ID_REST_SERVICE = '/ermapp/loadNoBotById/'
    var UPDATE_NOBOT_REST_SERVICE_URI = '/ermapp/updateNoBot/';
    var DELETE_NOBOT_REST_SERVICE_URI = '/ermapp/deleteNoBot/';

    var factory = {
    		loadAllNoBot : loadAllNoBot,
    		loadAllNoBotByStatus : loadAllNoBotByStatus,
    		loadNoBotById : loadNoBotById,
    		addNoBot : addNoBot,
    		updateNoBot : updateNoBot,
    		deleteNoBot : deleteNoBot
    };

    return factory;

    function loadAllNoBot() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_NOBOTS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllNoBots in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadAllNoBotByStatus () {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_NOBOTSBY_STATUS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllNoBotByStatus in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadNoBotById(noBotId) {
        var deferred = $q.defer();
        $http.get(LOAD_NOBOT_BY_ID_REST_SERVICE + noBotId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadNoBotById ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addNoBot(noBot) {
    	console.log('inside NoBotService addNoBot noBot  ::  ',noBot);
        var deferred = $q.defer();
        $http.post(NOBOT_REST_SERVICE, noBot)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while adding NoBot in NoBot service');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateNoBot(noBot) {
        var deferred = $q.defer();
        $http.put(UPDATE_NOBOT_REST_SERVICE_URI, noBot)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating NoBot in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteNoBot(noBotId) {
        var deferred = $q.defer();
        $http.delete(DELETE_NOBOT_REST_SERVICE_URI + noBotId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('bot deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting NoBot in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    

}]);
