'use strict';

angular.module('myApp').factory('UserDetailsService', ['$http', '$q', function($http, $q){
	
	var CREATE_USER_URI='/ermapp/createUser';
	
	var SHOW_ALL_USERS_URI='/ermapp/getUsers';
	
	var USERS_UPDATE_URI='/ermapp/getUsers/';
	
	var GET_USER_ROLE_URI='/ermapp/getUserRole';
	
	var REQUEST_REST_LOADBY_USERID='/ermapp/userByID/';
	
	var USER_UPDATE_REST_URI='/ermapp/updateUsrDetails';
	
	var PASSWORD_UPDATE_URI = '/ermapp/updatePassword';
	
	var FETCH_ALL_ROLES = '/ermapp/fetchAllRoles/';
	
	var FETCH_ALL_USER_ROLES = '/ermapp/fetchAllUserRoles/';
	
	var factory = {
			addUserDetails: addUserDetails,
			getAllUsers: getAllUsers,
			deleteUser: deleteUser,
			getUserRole: getUserRole,
			loadUserDetails: loadUserDetails,
			updateUserDetails: updateUserDetails,
			updatePassword: updatePassword,
			loadAllRoles : loadAllRoles,
			loadAllUserRoles : loadAllUserRoles
    };

    return factory;
	
	function addUserDetails(userInfo)
	{
		var deferred = $q.defer();
		//console.log("inside UserDetailsService addUserDetails method");
		
		$http
				.post(CREATE_USER_URI,userInfo)
				.then(getAllUsers,
						function(errResponse) {
							console
									.error('Error while addUserDetails');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	
	}
	
	function loadAllRoles()
	{
		var deferred = $q.defer();
		
		$http
				.get(FETCH_ALL_ROLES)
				.then(
						function (response) {
			                deferred.resolve(response.data);
			            },
						function(errResponse) {
							deferred.reject(errResponse);
						});
		return deferred.promise;
	}
	
	function loadAllUserRoles()
	{
		var deferred = $q.defer();
		
		$http
				.get(FETCH_ALL_USER_ROLES)
				.then(
						function (response) {
			                deferred.resolve(response.data);
			            },
						function(errResponse) {
							deferred.reject(errResponse);
						});
		return deferred.promise;
	}
	
	function getAllUsers()
	{
		var deferred = $q.defer();
		//console.log("inside UserDetailsService getAllUsers method");
		
		$http
				.get(SHOW_ALL_USERS_URI)
				.then(
						function (response) {
			                deferred.resolve(response.data);
			                //console.log('getAllUsers ')
			            },
						function(errResponse) {
							console
									.error('Error while getAllUsers');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	}
	
	function deleteUser(userId)
	{
		var deferred = $q.defer();
		$http
				.delete(USERS_UPDATE_URI+userId)
				.then(
						function(response) {
							deferred
									.resolve(response.data);
						},
						function(errResponse) {
							console
									.error('[Template Servic]Error While deleting Templates');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	}
	
	function getUserRole()
	{
		var deferred = $q.defer();
		//console.log("inside getUserRole function");
		
		$http
				.get(GET_USER_ROLE_URI)
				.then(
						function (response) {
			                deferred.resolve(response.data);
			                //console.log('getUserRole ')
			            },
						function(errResponse) {
							console
									.error('Error while getUserRole');
							deferred
									.reject(errResponse);
						});
		return deferred.promise;
	}
	
	function updatePassword(userInfo)
	{
		var deferred = $q.defer();
		//console.log("inside updatePassword function");
        $http.post(PASSWORD_UPDATE_URI,userInfo)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while  updatePassword in service js');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
	}
	
	function loadUserDetails(userId) {
        var deferred = $q.defer();
        $http.get(REQUEST_REST_LOADBY_USERID+userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while  loadUserDetails in service js');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
	function updateUserDetails(userInfo)
	{
		 var deferred = $q.defer();
	        $http.put(USER_UPDATE_REST_URI, userInfo)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating API');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	}
}]);