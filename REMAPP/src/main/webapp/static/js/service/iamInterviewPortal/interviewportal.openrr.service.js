'use strict';

angular.module('myApp').factory('UploadRRService', ['$http', '$q', function($http, $q){
	
	var UPLOAD_OPEN_RR_XLS_URI='/ermapp/interview/hr/uploadOpenRRXLS/';
	var RR_PANEL_REST_SERVICE_BY_ID_URI='/ermapp/interview/hr/interviewOpenRRPanel/';
	var RR_L2_JD_UPDATE='/ermapp/interview/compLead/updatL2JD/';
	var DELETE_RR_REST_SERVICE_URI ='/ermapp/interview/hr/deleteRR/';

	
	var factory = {
			loadAllRRPanelList: loadAllRRPanelList,
			uploadOpenRRXLS:uploadOpenRRXLS,
			updatL2JD:updatL2JD,
			deleteRR: deleteRR
    };

    return factory;
	
    function uploadOpenRRXLS(file,jdId){
    	console.log("Inside uploadOpenRRXLS",jdId);
    	
		 var fd = new FormData();
	    	fd.append('file', file);
	    	fd.append('jobdescId',jdId);
	        var deferred = $q.defer();
	        $http.post(UPLOAD_OPEN_RR_XLS_URI, fd,{
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

 function loadAllRRPanelList(jdId) {
	 var deferred = $q.defer();
        $http.get(RR_PANEL_REST_SERVICE_BY_ID_URI+jdId)
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
 
 
 
 function updatL2JD(rrDetails){
	 
	 var deferred = $q.defer();
	 var fd = new FormData();
	 fd.append('rrId',rrDetails.rrId);
	 fd.append('jobdescId',rrDetails.jobdescId);
	 fd.append('l2JdDesc',rrDetails.l2JdDesc);
	  $http.post(RR_L2_JD_UPDATE, fd,{
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
 
 function deleteRR(Id) {
	 
	   console.log('RR deleted  ::  ')
     var deferred = $q.defer();
     $http.delete(DELETE_RR_REST_SERVICE_URI + Id)
         .then(
         function (response) {
             deferred.resolve(response.data);
             console.log('RR deleted  ::  ')
         },
         function(errResponse){
             console.error('Error while deleting RR');
             deferred.reject(errResponse);
         }
     );
     return deferred.promise;
 }
 
 
	 
}]);