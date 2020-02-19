'use strict';

angular.module('myApp').factory('SchedulerService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'scheduler/';
    var JOB_REST_SERVICE_URI = 'job/';

    var factory = {
    		fetchTriggerList: fetchTriggerList,
    		createTrigger: createTrigger,
    		updateTrigger: updateTrigger,
    		deleteTrigger: deleteTrigger,
    		fetchJobList: fetchJobList
    		
       
    };

    return factory;

    function fetchTriggerList() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetchTriggerList ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchJobList() {
        var deferred = $q.defer();
        $http.get(JOB_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetchJobList ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createTrigger(triggerDetail) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, triggerDetail)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateTrigger(triggerDetail, triggerName) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+triggerName, triggerDetail)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteTrigger(triggerName) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+triggerName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting triggerName');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
