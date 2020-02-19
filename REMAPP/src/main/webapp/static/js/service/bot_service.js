'use strict';

angular.module('myApp').factory('BotService', ['$http', '$q', function($http, $q){

    var BOT_REST_SERVICE = '/ermapp/botRequest/';
    var LOAD_ALL_BOTS_REST_SERVICE_URI = '/ermapp/loadAllBots/';
    var LOAD_BOT_BY_ID_REST_SERVICE = '/ermapp/loadBotById/'
    var UPDATE_BOT_REST_SERVICE_URI = '/ermapp/updateBot/';
    var DELETE_BOT_REST_SERVICE_URI = '/ermapp/deleteBot/';
    var BOTNAME_CHECK_REST_SERVICE_URI = '/ermapp/checkBotName/';
    var LOAD_ALL_ACCOUNTBASES_REST_SERVICE_URI = '/ermapp/loadAllAccountBases/';
    
    var factory = {
    		loadAllBots : loadAllBots,
    		loadBotById : loadBotById,
    		addBot : addBot,
    		updateBot : updateBot,
    		deleteBot : deleteBot,
    		checkBotName : checkBotName,
    		loadAllAccountBase : loadAllAccountBase
    };

    return factory;

    function loadAllBots() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_BOTS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAllBots in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadBotById(botId) {
        var deferred = $q.defer();
        $http.get(LOAD_BOT_BY_ID_REST_SERVICE + botId)
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

    function addBot(bot) {
    	console.log('inside BotService addBot bot  ::  ',bot);
        var deferred = $q.defer();
        $http.post(BOT_REST_SERVICE, bot)
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


    function updateBot(bot) {
        var deferred = $q.defer();
        $http.put(UPDATE_BOT_REST_SERVICE_URI, bot)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating bot in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteBot(botId) {
        var deferred = $q.defer();
        $http.delete(DELETE_BOT_REST_SERVICE_URI + botId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('bot deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting bot in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function checkBotName(botName) {
        var deferred = $q.defer();
        $http.get(BOTNAME_CHECK_REST_SERVICE_URI + botName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while checkBotName ');
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
