'use strict';

angular.module('myApp').factory('InterviewAdminService', ['$http', '$q', function($http, $q){

    var ADMIN_PANEL_UPLOAD_REST_SERVICE_URI = '/ermapp/interview/admin/fileUpload/';
    var ADMIN_PANEL_ALL_REST_SERVICE_URI = '/ermapp/interview/admin/getallpanel/';
    var UPDATE_PANEL_ARRAY_REST_SERVICE_URI = '/ermapp/interview/admin/allpanelupdate/';
    var CREATE_PANEL_REST_SERVICE_URI = '/ermapp/interview/admin/create/';
    var DELETE_PANEL_REST_SERVICE_URI = '/ermapp/interview/admin/delete/';
    var EDIT_PANEL_REST_SERVICE_URI = '/ermapp/interview/admin/edit/';
    
    var UPDATE_PANEL_REST_SERVICE_URI = '/ermapp/interview/admin/update?event=';
    

   
    var factory = {
    		loadAllAdminPanelList: loadAllAdminPanelList,
    		adminPanelUpload: adminPanelUpload,
    		updatePanelList: updatePanelList,
    		createPanel: createPanel,
    		deletePanel: deletePanel,
    		editPanel: editPanel,
    		updatePanel : updatePanel
    };

    return factory;
    
    function editPanel(empId) {
        var deferred = $q.defer();
        $http.put(EDIT_PANEL_REST_SERVICE_URI + empId)
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
    
    function deletePanel(empId) {
        var deferred = $q.defer();
        $http.delete(DELETE_PANEL_REST_SERVICE_URI + empId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log('Panel deleted  ::  ')
            },
            function(errResponse){
                console.error('Error while deleting panel');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function updatePanel(admin, eve) {
    	
    	var deferred = $q.defer();    	
    	
    	$http.post(UPDATE_PANEL_REST_SERVICE_URI+ eve,
    			admin).then(
    	              function(response) {
    	                     deferred.resolve(response.data);
    	                     }, function(errResponse) {
    	                           deferred.reject(errResponse);
    	                     });
    	       return deferred.promise;
    	}
    
    function createPanel(admin) {
    
    	var deferred = $q.defer();    	
    	
    	$http.post(CREATE_PANEL_REST_SERVICE_URI,
    			admin).then(
    	              function(response) {
    	                     deferred.resolve(response.data);
    	                     }, function(errResponse) {
    	                           deferred.reject(errResponse);
    	                     });
    	       return deferred.promise;
    	}
    
    function updatePanelList(pageableAdminPanelAll) {
    
        var deferred = $q.defer();
        $http.post(UPDATE_PANEL_ARRAY_REST_SERVICE_URI, pageableAdminPanelAll)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating panel in service');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loadAllAdminPanelList() {
        var deferred = $q.defer();
        $http.get(ADMIN_PANEL_ALL_REST_SERVICE_URI)
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

    function adminPanelUpload(file) {
    	
    	var fd = new FormData();
    	fd.append('file', file);
    	
        var deferred = $q.defer();
        console.log('File Details In Service-->',file);
       
        $http.post(ADMIN_PANEL_UPLOAD_REST_SERVICE_URI, fd,{
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
                console.error('Error while creating API');

               deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
