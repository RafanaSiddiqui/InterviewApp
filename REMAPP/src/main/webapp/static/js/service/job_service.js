'use strict';

angular.module('myApp').factory('JobService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'job/';
    var API_REST_SERVICE_URI ='api/';
 
    var factory = {
    		fetchJobList: fetchJobList,
    		createJob: createJob,
    		updateJob: updateJob,
    		deleteJob: deleteJob,
    		loadAllAPI: loadAllAPI
    		
       
    };

    return factory;
    
    function loadAllAPI() {
        var deferred = $q.defer();
        $http.get(API_REST_SERVICE_URI)
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

    function fetchJobList() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
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

    function createJob(jobDetail) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, jobDetail)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Job');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateJob(jobDetail, jobName) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+jobName, jobDetail)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Job');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteJob(jobName) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+jobName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting JobName');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
