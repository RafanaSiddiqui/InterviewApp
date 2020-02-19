'use strict';

angular.module('myApp').factory('BulkAccountBaseService', ['$http', '$q', function($http, $q) {
	
	 var LOAD_ALL_BULKACCOUNTBASES_REST_SERVICE_URI = '/ermapp/loadAllBulkAccountBases/';
	 var BULKACCOUNTBASE_REST_SERVICE = '/ermapp/bulkAccountBase/';
	
	 var factory = {
			 loadAllBulkAccountBase : loadAllBulkAccountBase,
			 addBulkAccountBase : addBulkAccountBase,
			 deleteBulkAccountBase : deleteBulkAccountBase,
			 loadBulkAccountBaseById : loadBulkAccountBaseById
	    };	 
	 return factory;

	    function loadAllBulkAccountBase() {
	        var deferred = $q.defer();
	        $http.get(LOAD_ALL_BULKACCOUNTBASES_REST_SERVICE_URI)
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
	    
	    function addBulkAccountBase(bulkAccountBase, file) {
	    	console.log('inside addBulkAccountBase  bulkAccountBase  ::  ',bulkAccountBase);
	    	//alert('Mahesh C');
	    	
	    	var fd = new FormData();
	    	fd.append('file', file);
	    	fd.append('name', bulkAccountBase.name);
	    	fd.append('description', bulkAccountBase.description);
	    	fd.append('bulkID', bulkAccountBase.bulkID);
	    	
	        var deferred = $q.defer();
	        console.log('File Details In Service-->',file);
	        
	        $http.post(BULKACCOUNTBASE_REST_SERVICE, fd,{
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
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function deleteBulkAccountBase(bulkID) {
	        var deferred = $q.defer();
	        $http.delete(BULKACCOUNTBASE_REST_SERVICE + bulkID)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	                console.log('bot deleted  ::  ')
	            },
	            function(errResponse){
	                console.error('Error while deleting AccountBase in service');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    
	    function loadBulkAccountBaseById(bulkID) {
	        var deferred = $q.defer();
	        $http.get(BULKACCOUNTBASE_REST_SERVICE + bulkID)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while loadBulkAccountBaseById ');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
}]);
