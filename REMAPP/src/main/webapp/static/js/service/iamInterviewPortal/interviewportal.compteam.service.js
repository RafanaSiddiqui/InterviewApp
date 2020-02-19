'use strict';

angular.module('myApp').factory('InterviewAdminService', ['$http', '$q', function($http, $q){


    var COMP_TOP_PANEL_REST_SERVICE_URI = '/ermapp/interview/compTeam/toppanel/'; 
    

   
    var factory = {    	
    		getTopPanel: getTopPanel
    };

    return factory;
    
       
    function getTopPanel(days) {
        var deferred = $q.defer();
        $http.put(COMP_TOP_PANEL_REST_SERVICE_URI + days)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('Panel edit request  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting panel');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
