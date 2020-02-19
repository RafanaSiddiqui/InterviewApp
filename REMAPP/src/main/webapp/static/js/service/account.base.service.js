'use strict';

angular.module('myApp').factory('AccountBaseService', ['$http', '$q', function($http, $q) {

    var ACCOUNTBASE_REST_SERVICE = '/ermapp/accountBaseRequest/';
    var LOAD_ALL_ACCOUNTBASES_REST_SERVICE_URI = '/ermapp/loadAllAccountBases/';
    var LOAD_ACCOUNTBASE_BY_ID_REST_SERVICE = '/ermapp/loadAccountBaseById/'
    var UPDATE_ACCOUNTBASE_REST_SERVICE_URI = '/ermapp/updateAccountBase/';
    var DELETE_ACCOUNTBASE_REST_SERVICE_URI = '/ermapp/deleteAccountBase/';

    var factory = {
    		loadAllAccountBase : loadAllAccountBase,
    		loadAccountBaseById : loadAccountBaseById,
    		addAccountBase : addAccountBase,
    		updateAccountBase : updateAccountBase,
    		deleteAccountBase : deleteAccountBase
    };

    return factory;

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
    
    function loadAccountBaseById(accountBaseId) {
        var deferred = $q.defer();
        $http.get(LOAD_ACCOUNTBASE_BY_ID_REST_SERVICE + accountBaseId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadAccountBaseById ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addAccountBase(accountBase) {
    	console.log('inside AccountBaseService addAccountBase accountBase  ::  ',accountBase);
        var deferred = $q.defer();
        $http.post(ACCOUNTBASE_REST_SERVICE, accountBase)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while adding AccountBase in AccountBase service');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateAccountBase(accountBase) {
        var deferred = $q.defer();
        $http.put(UPDATE_ACCOUNTBASE_REST_SERVICE_URI, accountBase)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating AccountBase in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteAccountBase(accountBaseId) {
        var deferred = $q.defer();
        $http.delete(DELETE_ACCOUNTBASE_REST_SERVICE_URI + accountBaseId)
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
    
}]);
