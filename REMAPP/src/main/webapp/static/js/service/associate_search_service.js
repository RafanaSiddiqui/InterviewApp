'use strict';

angular.module('myApp').factory('AssociateSearchService', ['$http', '$q', function($http, $q){

    var ASSOCIATE_SEARCH_REST_SERVICE_URI = 'loadAssociates/';
    var SEARCH_ASSOCIATE_REST_SERVICE_URI = 'searchAssociates/';

    var factory = {
    		loadAllAssociates : loadAllAssociates,
    		searchAssociates : searchAssociates
    };

    return factory;

    function loadAllAssociates() {
        var deferred = $q.defer();
        $http.get(ASSOCIATE_SEARCH_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('AssociateSearchService Error while loadAllAssociates ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function searchAssociates(associateDetails) {
        var deferred = $q.defer();
        $http.post(SEARCH_ASSOCIATE_REST_SERVICE_URI, associateDetails)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('AssociateSearchService Error while searchAssociates ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
