'use strict';

angular.module('myApp').controller('BulkAccountBaseController', ['$scope', '$window','BulkAccountBaseService', '$uibModal', '$timeout', '$filter', '$http', function($scope, $window, BulkAccountBaseService, $uibModal, $timeout, $filter, $http) {
	var self = this;
	
	var file = $scope.myFile;
	
	self.bulkAccountBase = {bulkID:null, name:'', description:'', status:'', createdDate:'', updatedDate:'', fileData:'', fileName:''}
    self.bulkAccountBaseList = [];
	
	loadAllBulkAccountBase();
	
	self.createNewBulkAccountBase = createNewBulkAccountBase;
	self.submit = submit;
	self.addBulkAccountBase = addBulkAccountBase;
	self.deleteBulkAccountBase = deleteBulkAccountBase;
	self.editBulkAccountBase = editBulkAccountBase;
	self.reset = reset;
	
	 function loadAllBulkAccountBase() {
    	 console.log('inside loadAllBulkAccountBase');
    	 BulkAccountBaseService.loadAllBulkAccountBase()
    	 .then(
	            function(d) {
	            	$scope.pageableBulkAccountBase = d;
	            	angular.forEach($scope.pageableBulkAccountBase, function(value, key){
   	            		console.log('value  ::  '+value);
   	            		crDate = new Date(value.createdDate);
   	            		
   	            		var dd = crDate.getDate();
   	            		var mm = crDate.getMonth()+1; //January is 0!

   	            		var yyyy = crDate.getFullYear();
   	            		if(dd<10){
   	            		    dd='0'+dd;
   	            		} 
   	            		if(mm<10){
   	            		    mm='0'+mm;
   	            		} 
   	            		var crDate = mm+'/'+dd+'/'+yyyy;
   	            		
   	            		value.createdDate = crDate;
   	            		
   	            	});
	                console.log('$scope.pageableBulkAccountBase  ::  ',$scope.pageableBulkAccountBase);
	            },

            function(errResponse){
                console.error('Error while fetching pageableBulkAccountBase');
            }
    	 );

    };
	
	function createNewBulkAccountBase() {
	   	 console.log('inside createNewBulkAccountBase');
	   	 $window.location.href = 'addBulkAccountBase';
	}
	
	function editBulkAccountBase(bulkID) {
		//alert('edit bulk');
    	console.log("inside editBulkAccountBase  ::  "+bulkID);
    	$window.location.href = 'editBulkAccountBase/'+bulkID;
    };
    
    
    $scope.editBulkAccountBaseById = function(bulkID) {
    	console.log('BulkAccountBaseId  ::  '+bulkID);
    	loadBulkAccountBaseById(bulkID);
    };
    
    function loadBulkAccountBaseById(bulkID) {
      	console.log('loadBulkAccountBaseById  ::  ',bulkID);
      	//alert('loadBulkAccountBaseById  ::  ',bulkID);
      	BulkAccountBaseService.loadBulkAccountBaseById(bulkID)
      	 .then(
      	       function(d) {
      	            	$scope.bulkAccountBase = d;
      	            	self.bulkAccountBase = d;
      	            	console.log("$scope.bulkAccountBase  ::  ",$scope.bulkAccountBase);
      	            	
      	            	if($scope.bulkAccountBase.createdDate != null) {
      	            		$scope.bulkAccountBase.createdDate = new Date($scope.bulkAccountBase.createdDate);
      	            	}
      	            	
      	                console.log("$scope.bulkAccountBase  ::  ",$scope.bulkAccountBase);
      	        },

              function(errResponse) {
                  console.error('Error while fetching loadAccountBaseById');
              }
      	 );

      }
    
	
	function submit(myForm) {
		console.log('self.bulkAccountBase  ::  ', self.bulkAccountBase);
	    addBulkAccountBase(self.bulkAccountBase);
        reset();
        $window.location.href = '/ermapp/showAllBulkAccountBase';
	}
	
	function addBulkAccountBase(bulkAccountBase) {
    	console.log('inside addBulkAccountBase bulkAccountBase  ::  ',bulkAccountBase);
    	console.log('File Details-->',$scope.myFile);
    	BulkAccountBaseService.addBulkAccountBase(bulkAccountBase, $scope.myFile)
            .then(
            		bulkAccountBase,
            function(errResponse){
                console.error('Error while adding bulkAccountBase');
            }
        );
    }
	
	function reset() {
		console.log('reset called....')
		self.bulkAccountBase = {bulkID:null, name:'', description:'', status:'', createdDate:'', updatedDate:'', fileData:'', fileName:''}
    	$window.location.href = '/ermapp/showAllBulkAccountBase';
		loadAllBulkAccountBase();
    }
	
	   $scope.fnDownLoad = function (bulkID) {
	  	   console.log("bulkID  ::  "+bulkID);
	  	   var s = 'downloadBulkAccountBaseFile/'+ bulkID;
	         $http.get('downloadBulkAccountBaseFile/'+bulkID)
	             .then(
	             function (response) {
	          	   console.log('response  ::  '+response);
	          	   $window.open(s);
	             },
	             function(errResponse){
	                 console.error('Error while fnDownLoad ');
	                 $scope.open('/static/pages/errorNoFileModal.html');
	             }
	         );
	     };
	     
	     
	     function deleteBulkAccountBase(bulkID){
	         console.log('inside deleteBulkAccountBase bulkID  ::  ', bulkID);

	         BulkAccountBaseService.deleteBulkAccountBase(bulkID)
	 	   	 .then(
	    	            function(d) {
	    	            	console.log("BulkAccountBase deleted  ::  ");
	    	            	reset();
	    	            	$window.location.href = '/ermapp/showAllBulkAccountBase';
	    	            },

	 	           function(errResponse){
	 	               console.error('Error while deleting BulkAccountBase');
	 	           }
	 	   	 );
	         

	     }
	
}]);
