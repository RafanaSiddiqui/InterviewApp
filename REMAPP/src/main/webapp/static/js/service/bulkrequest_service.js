'use strict';

angular.module('myApp').factory('BulkRequestService', ['$http', '$q', function($http, $q){

    var BULK_REQUEST_REST_SERVICE_URI = 'bulkRequest/';

    var REQUEST_REST_LOADBY_ID='bulkrequestByID/';
    var NEW_REQUEST_URI = 'createNewRequest/';
    var REQUEST_SHOWALAL_REST_SERVICE_URI = 'requestShowAllBulk/';

    var EXECUTE_APP_ON_BOARD_URI = 'executeAppOnboard/';

    var REQUEST_DELETE_URI = 'request/';

    var factory = {
    		loadAllBulkRequests: loadAllBulkRequests,
    		loadBulkRequest:loadBulkRequest,
    		createBulkRequest: createBulkRequest,
    		updateBulkAppRequest:updateBulkAppRequest,
    		executeAppOnboard:executeAppOnboard,
    		deleteBulkAppRequest:deleteBulkAppRequest
    };

    return factory;

    function loadAllBulkRequests() {
        var deferred = $q.defer();
        $http.get(REQUEST_SHOWALAL_REST_SERVICE_URI)
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

    function loadBulkRequest(bulkrequestID) {
        var deferred = $q.defer();
        $http.get(REQUEST_REST_LOADBY_ID+bulkrequestID)
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



    function createBulkRequest(bulkappRequest,file) {
    	var fd = new FormData();
    	fd.append('file', file);
    	fd.append('name', bulkappRequest.name);
    	fd.append('description', bulkappRequest.description);

        var deferred = $q.defer();
        console.log('File Details In Service-->',file);
        console.log('bulkappRequest-->',bulkappRequest);
        $http.post(BULK_REQUEST_REST_SERVICE_URI, fd,{
        	transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
        })
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                /*console.error('Error while creating API');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str); */ 
               deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateBulkAppRequest(bulkappRequest,file) {
    	var fd = new FormData();
    	fd.append('file', file);
    	fd.append('bulkRequestID', bulkappRequest.bulkRequestID);
    	fd.append('name', bulkappRequest.name);
    	fd.append('description', bulkappRequest.description);

        var deferred = $q.defer();
        console.log('File Details In update Service-->',file);
        console.log('bulkappRequest-Upadet->',bulkappRequest);
        $http.put(BULK_REQUEST_REST_SERVICE_URI, fd,{
        	transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
        })
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

    function deleteBulkAppRequest(appRequestId) {
        var deferred = $q.defer();
        $http.delete(BULK_REQUEST_REST_SERVICE_URI+appRequestId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('apprequest deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while creating API');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function executeAppOnboard(appRequestId) {
        var deferred = $q.defer();
        $http.post(EXECUTE_APP_ON_BOARD_URI+appRequestId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while executeAppOnboard');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }




}]);
