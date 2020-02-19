'use strict';

angular.module('myApp').factory('SkillAttestationService', ['$http', '$q', function($http, $q) {

    var SKILLATTESTATION_REST_SERVICE = '/ermapp/skillAttestationRequest/';
    var LOADSKILLDETAILS_REST_SERVICE = '/ermapp/loadSkillDetails/';
    var LOAD_ALL_SKILLATTESTATIONS_REST_SERVICE_URI = '/ermapp/loadAllSkillAttestation/';
    var LOAD_SKILLATTESTATION_BY_ID_REST_SERVICE = '/ermapp/loadSkillAttestationById/'
    var UPDATE_SKILLATTESTATION_REST_SERVICE_URI = '/ermapp/updateSkillAttestation/';
    var DELETE_SKILLATTESTATION_REST_SERVICE_URI = '/ermapp/deleteSkillAttestation/';
    var DELETE_SKILL_REST_SERVICE_URI = '/ermapp/deleteSkill/';

    var factory = {
    		loadAllSkillAttestation : loadAllSkillAttestation,
    		loadSkillAttestationById : loadSkillAttestationById,
    		addSkillAttestation : addSkillAttestation,
    		updateSkillAttestation : updateSkillAttestation,
    		deleteSkillAttestation : deleteSkillAttestation,
    		loadSkillDetails : loadSkillDetails,
    		deleteSkill : deleteSkill
    };

    return factory;

    function loadAllSkillAttestation() {
        var deferred = $q.defer();
        $http.get(LOAD_ALL_SKILLATTESTATIONS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllSkillAttestations in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadSkillDetails(associateId) {
        var deferred = $q.defer();
        $http.get(LOADSKILLDETAILS_REST_SERVICE+associateId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while loadAllSkillAttestations in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadSkillAttestationById(skillAttestationId) {
        var deferred = $q.defer();
        $http.get(LOAD_SKILLATTESTATION_BY_ID_REST_SERVICE + skillAttestationId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while loadSkillAttestationById ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addSkillAttestation(skillAttestation) {
    	console.log('inside SkillAttestationService addSkillAttestation skillDetailsList  ::  ',skillDetailsList);
        var deferred = $q.defer();
        $http.post(SKILLATTESTATION_REST_SERVICE, skillAttestation)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while adding SkillAttestation in SkillAttestation service');

                var str = JSON.stringify(errResponse);
		    	str = JSON.stringify(errResponse, null, 4); // (Optional) beautiful indented output.
		    	console.log(str); // Logs output to dev tools console.
		    	//alert(str);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateSkillAttestation(skillAttestation) {
        var deferred = $q.defer();
        $http.put(UPDATE_SKILLATTESTATION_REST_SERVICE_URI, skillAttestation)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating SkillAttestation in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteSkillAttestation(skillAttestationId) {
        var deferred = $q.defer();
        $http.delete(DELETE_SKILLATTESTATION_REST_SERVICE_URI + skillAttestationId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('bot deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting SkillAttestation in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteSkill(skillId) {
        var deferred = $q.defer();
        $http.delete(DELETE_SKILL_REST_SERVICE_URI + skillId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('skill deleted')
            },
            function(errResponse){
                console.error('Error while deleting skill in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
