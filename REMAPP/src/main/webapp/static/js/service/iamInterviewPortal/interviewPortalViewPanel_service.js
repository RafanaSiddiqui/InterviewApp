'use strict';

angular.module('myApp').factory('ViewPanelService', ['$http', '$q', function($http, $q) {
	
	var LOAD_ACCEPTED_PANEL_FOR_JD_ID_URI = '/ermapp/interview/compLead/loadPanelAcceptedForJdId/';
	var LOAD_PANEL_FOR_SKILL_URI = '/ermapp/interview/compLead/loadPanelForSkill/';
	var MAP_PANEL_FOR_INTERVIEW_URI = '/ermapp/interview/compLead/addPanelForInterview/';


	
	var factory = {
			loadAcceptedPanelByJdId : loadAcceptedPanelByJdId,
			loadPanelForSkill : loadPanelForSkill,
			mapPanelForInterview : mapPanelForInterview
	};
	
	return factory;
	
	function loadAcceptedPanelByJdId(jdId) {
    	console.log('Entering ViewPanelService :: loadAcceptedPanelByJdId, jdId-->',jdId);
        var deferred = $q.defer();
        $http.get(LOAD_ACCEPTED_PANEL_FOR_JD_ID_URI + jdId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Inside ViewPanelService :: loadAcceptedPanelByJdId, ERROR!!');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
	
	function loadPanelForSkill(skill) {
    	console.log('Entering ViewPanelService :: loadPanelForSkill, skill -->',skill);
        var deferred = $q.defer();
        $http.get(LOAD_PANEL_FOR_SKILL_URI + skill)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Inside ViewPanelService :: loadPanelForSkill, ERROR!!');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
	
	function mapPanelForInterview(jdPanel) {
    	console.log('Entering ViewPanelService :: mapPanelForInterview, jdPanel -->'+jdPanel);
    	   var deferred = $q.defer();
           $http.post(MAP_PANEL_FOR_INTERVIEW_URI, jdPanel )
               .then(
               function (response) {
                   deferred.resolve(response.data);
               },
               function(errResponse){
                   console.error('Error while mapping panel in service js');
                   deferred.reject(errResponse);
               }
           );
           return deferred.promise;
    }
	
	
    
}]);