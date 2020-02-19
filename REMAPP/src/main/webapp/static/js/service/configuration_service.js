'use strict';

angular.module('myApp').factory('ConfigurationService', ['$http', '$q', function($http, $q){

    var CONFIGURATION_REST_SERVICE_URI = 'configuration/';

    var CONFIGURATION_SHOWALAL_REST_SERVICE_URI = 'configurationByCategory/';

    var factory = {
    		loadAllConfiguration: loadAllConfiguration,
    		createConfiguration: createConfiguration,
    		updateConfiguration: updateConfiguration,
    		deleteConfiguration: deleteConfiguration
    };

    return factory;

    function loadAllConfiguration() {
        var deferred = $q.defer();
        $http.get(CONFIGURATION_SHOWALAL_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while Request List ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createConfiguration(configuration) {
        var deferred = $q.defer();
        $http.post(CONFIGURATION_REST_SERVICE_URI, configuration)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while createConfiguration  ::  ',errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function updateConfiguration(configuration) {
        var deferred = $q.defer();
        $http.put(CONFIGURATION_REST_SERVICE_URI, configuration)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updateConfiguration  ::  ',errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteConfiguration(settingID) {
        var deferred = $q.defer();
        $http.delete(CONFIGURATION_REST_SERVICE_URI+settingID)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleteConfiguration  ::  ',errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
