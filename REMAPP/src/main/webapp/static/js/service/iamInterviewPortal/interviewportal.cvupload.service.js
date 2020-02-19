'use strict';

angular.module('myApp').factory('UploadCVService', ['$http', '$q', function($http, $q){
	
	var UPLOAD_CV_BY_ID_URI='/ermapp/interview/hr/uploadCandidateCVByID/';
	var UPLOAD_CV_ZIP_URI='/ermapp/interview/hr/uploadCVZip/';
	var DELETE_UPLOADED_ZIP_URI='/ermapp/interview/hr/deleteUploadedCVZip/';
	
	var factory = {
			loadCandidateCVById:loadCandidateCVById,
			uploadCVZip:uploadCVZip,
			loadAllJobPanelById:loadAllJobPanelById,
			deleteUploadedCV:deleteUploadedCV
    };

    return factory;
	
	 function loadCandidateCVById(jobDetailId) {
		 
		 console.log("Inside LoadcandidateCVById>>>>",jobDetailId);
		 var deferred = $q.defer();
        $http.get(UPLOAD_CV_BY_ID_URI+jobDetailId)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse) {
	                console.error('Error in service');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise; 
	 }

 function uploadCVZip(jobdetails, file) {
	 console.log("file",file);
	 console.log("jobdetails>>>>>>>>", jobdetails);
	var fd = new FormData();
	fd.append('file', file);
	fd.append('jobdescId', jobdetails.jobdescId);
	fd.append('skill', jobdetails.skill);
	fd.append('footFall', jobdetails.footFall);
	fd.append('cvCount',jobdetails.cvCount)
	var deferred = $q.defer();
  $http.post(UPLOAD_CV_ZIP_URI, fd,{
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
	 
	 
	 function loadAllJobPanelById(jobDetails) {
		console.log("Job details Id>>>",jobDetails.jobdescId);
		 loadCandidateCVById(jobDetails.jobdescId);
		 
		 
	    }
	 
	 
	 function deleteUploadedCV(Id) {
		 
		   console.log('CV deleted  ::  ')
	     var deferred = $q.defer();
	     $http.post(DELETE_UPLOADED_ZIP_URI + Id)
	         .then(
	         function (response) {
	             deferred.resolve(response.data);
	             console.log('CV deleted  ::  ')
	         },
	         function(errResponse){
	             console.error('Error while deleting CV');
	             deferred.reject(errResponse);
	         }
	     );
	     return deferred.promise;
	 }
 
	 
}]);
