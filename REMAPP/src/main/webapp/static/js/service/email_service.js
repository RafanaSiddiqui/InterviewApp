'use strict';

angular.module('myApp').factory('EmailService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'emailTemplateService/';

    var factory = {
    		fetchEmailTemplateList: fetchEmailTemplateList,
    		createEmail: createEmail,
    		updateEmail: updateEmail,
    		deleteEmail: deleteEmail
    		
       
    };

    return factory;

    function fetchEmailTemplateList() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while emailTemplateList ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createEmail(emailTemplate) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, emailTemplate)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating email template');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateEmail(emailTemplate, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, emailTemplate)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating email template');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteEmail(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting email template');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
