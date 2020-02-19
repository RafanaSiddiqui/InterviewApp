'use strict';

angular.module('myApp').factory('CandidateFeedbackService', ['$http', '$q', function($http, $q) {

	var LOAD_ALL_CANDIDATE_FEEDBACK_BY_JD_ID_REST_SERVICE_URI = '/ermapp/interview/panel/loadAllCandidateFeedbacksByJdId/';
	var ADD_CANDIDATE_FEEDBACK_REST_SERVICE = '/ermapp/interview/panel/addCandidateFeedback/';
	var UPDATE_CANDIDATE_FEEDBACK_REST_SERVICE = '/ermapp/interview/panel/updateCandidateFeedback/';
	
	var factory = {
			loadAllCandidateFeedbacksByJdId : loadAllCandidateFeedbacksByJdId,
			addCandidateFeedback : addCandidateFeedback,
			updateCandidateFeedback : updateCandidateFeedback,
	}
	
	return factory;
	
	function loadAllCandidateFeedbacksByJdId(jdId) {
    	console.log('Enter CandidateFeedbackService :: loadAllCandidateFeedbacksByJdId, jdId-->',jdId);
        var deferred = $q.defer();
        $http.get(LOAD_ALL_CANDIDATE_FEEDBACK_BY_JD_ID_REST_SERVICE_URI + jdId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Inside CandidateFeedbackService :: loadAllCandidateFeedbacksByJdId, ERROR!! errResponse-->', errResponse);
                deferred.reject(errResponse);
            }
        );
        console.log('Exit CandidateFeedbackService :: loadAllCandidateFeedbacksByJdId');
        return deferred.promise;
    }
	
	function addCandidateFeedback(candidateFeedback) {
    	console.log('Enter CandidateFeedbackService :: addCandidateFeedback, candidateFeedback-->', candidateFeedback);
        var deferred = $q.defer();
        $http.post(ADD_CANDIDATE_FEEDBACK_REST_SERVICE, candidateFeedback)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('Inside addCandidateFeedback ::  addCandidateFeedback, response -->', response);
            },
            function(errResponse){
                console.error('Inside CandidateFeedbackService :: addCandidateFeedback, ERROR!! errResponse-->', errResponse);

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        console.log('Exit CandidateFeedbackService :: addCandidateFeedback');
        return deferred.promise;
    }
	
	function updateCandidateFeedback(candidateFeedback) {
    	console.log('Enter CandidateFeedbackService :: updateCandidateFeedback, updateCandidateFeedback-->', candidateFeedback);
        var deferred = $q.defer();
        $http.put(UPDATE_CANDIDATE_FEEDBACK_REST_SERVICE, candidateFeedback)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('Inside addCandidateFeedback ::  updateCandidateFeedback, response -->', response);
            },
            function(errResponse){
                console.error('Inside CandidateFeedbackService :: updateCandidateFeedback, ERROR!! errResponse-->', errResponse);

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        console.log('Exit CandidateFeedbackService :: updateCandidateFeedback');
        return deferred.promise;
    }
    
}]);
