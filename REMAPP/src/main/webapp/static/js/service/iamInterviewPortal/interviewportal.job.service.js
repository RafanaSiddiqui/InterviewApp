'use strict';

angular.module('myApp').factory('JobDetailsService', ['$http', '$q', function($http, $q){
	
	var CREATE_JOB_DETAILS_URI='/ermapp/interview/hr/createJobDetails/';
	var JOB_DETAILS_PANEL_REST_SERVICE_URI='/ermapp/interview/hr/interviewJobDetailsPanel/';
	var UPLOAD_JOB_DETAILS_XLS_URI='/ermapp/interview/hr/uploadRequirementXLS/';
	var DELETE_JD_REST_SERVICE_URI ='/ermapp/interview/hr/deleteJobRequirement/'
	
	
	var factory = {
			addJobDetails: addJobDetails,
			loadAllJobPanelList: loadAllJobPanelList,
			uploadHRRequirementXLS:uploadHRRequirementXLS,
			deleteJD: deleteJD
    };

    return factory;
	

function addJobDetails(interviewjobdetails) {
console.log("Inside AddJob details");
var deferred = $q.defer();

$http.post(CREATE_JOB_DETAILS_URI,
		interviewjobdetails).then(
		function(response) {
			deferred.resolve(response.data);
			}, function(errResponse) {
				deferred.reject(errResponse);
			});
	return deferred.promise;

}

 function loadAllJobPanelList() {
	 var deferred = $q.defer();
        $http.get(JOB_DETAILS_PANEL_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loading panel list ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
	
	 function uploadHRRequirementXLS(file){
		 var fd = new FormData();
	    	fd.append('file', file);
	    	
	        var deferred = $q.defer();
	        $http.post(UPLOAD_JOB_DETAILS_XLS_URI, fd,{
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
	 
	 
	   function deleteJD(jobDetailId) {
	        var deferred = $q.defer();
	        $http.delete(DELETE_JD_REST_SERVICE_URI + jobDetailId)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	                console.log('JD deleted  ::  ')
	            },
	            function(errResponse){
	                console.error('Error while deleting JD');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
}]);