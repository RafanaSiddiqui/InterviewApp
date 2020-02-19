'use strict';

angular.module('myApp').factory('UpdateJDService', ['$http', '$q', function($http, $q) {
	console.log(".... Update JD service...");
	
	var SHOW_UPDATE_JD_URI='/ermapp/interview/compLead/showUpdateJDforL1';
	
	var UPDATE_JD_URI='/ermapp/interview/compLead/updateJDforL1';
	

	
	var factory = {
			updateJDDetails: updateJDDetails,

    };

    return factory;
	
	function updateJDDetails(interviewPortalJobDetails)
	{
		var deferred = $q.defer();
		console.log("inside UpdateJDService updateJDDetails method"+interviewPortalJobDetails.skill);
		
		$http
				.post(UPDATE_JD_URI,interviewPortalJobDetails)
				.then(
			            function (response) {
			                deferred.resolve(response.data);
			            },
			            function(errResponse){
			                console.error('Error while update JD ');
			                deferred.reject(errResponse);
			            }
			        );
		return deferred.promise;
	
	}
	
}]);
