'use strict';

angular.module('myApp').factory('TrackerService', ['$http', '$q', function($http, $q) {

    var TRACKER_REST_SERVICE = '/ermapp/trackerRequest/';
    var LOAD_ALL_TRACKERS_REST_SERVICE_URI = '/ermapp/loadAllTrackers/';
    var LOAD_TRACKER_BY_ID_REST_SERVICE = '/ermapp/loadTrackerById/'
    var UPDATE_TRACKER_REST_SERVICE_URI = '/ermapp/updateTracker/';
    var DELETE_TRACKER_REST_SERVICE_URI = '/ermapp/deleteTracker/';

    var factory = {
    		loadAllTracker : loadAllTracker,
    		loadTrackerById : loadTrackerById,
    		addTracker : addTracker,
    		updateTracker : updateTracker,
    		deleteTracker : deleteTracker
    };

    return factory;

    function loadAllTracker() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_TRACKERS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllTrackers in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadTrackerById(trackerId) {
        var deferred = $q.defer();
        $http.get(LOAD_TRACKER_BY_ID_REST_SERVICE + trackerId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadTrackerById ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addTracker(tracker) {
    	console.log('inside TrackerService addTracker tracker  ::  ',tracker);
        var deferred = $q.defer();
        $http.post(TRACKER_REST_SERVICE, tracker)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while adding Tracker in Tracker service');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateTracker(tracker) {
        var deferred = $q.defer();
        $http.put(UPDATE_TRACKER_REST_SERVICE_URI, tracker)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Tracker in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteTracker(trackerId) {
        var deferred = $q.defer();
        $http.delete(DELETE_TRACKER_REST_SERVICE_URI + trackerId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('bot deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting Tracker in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
