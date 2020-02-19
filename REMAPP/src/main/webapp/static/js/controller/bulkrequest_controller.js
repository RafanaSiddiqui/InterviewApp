'use strict';

angular.module('myApp').controller('BulkRequestController', ['$scope', '$window','BulkRequestService', '$uibModal', '$http', function($scope, $window, BulkRequestService, $uibModal, $http) {
    var self = this;
    var requestIDforEdit1 = null;
    var file = $scope.myFile;


    self.bulkRequest={bulkRequestID:null,name:'',description:'',status:'',createdDate:'',updateDate:'',fileData:'',fileName:''};
    self.bulkRequestList=[];

    
    loadAllBulkRequests();

    self.submit = submit;
    self.onloadFun = onloadFun;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.createBulkRequest=createBulkRequest;
    self.loadBulkRequest =loadBulkRequest;
    self.updateBulkAppRequest=updateBulkAppRequest;
    self.addNewHeader = addNewHeader;
    self.removeHeader = removeHeader;
    self.createNewRequest=createNewRequest;
    self.loadAllBulkRequests = loadAllBulkRequests;


    function onloadFun(someValue) {
    	//alert("someValue  ::  "+someValue);
    }



    function addNewHeader() {
    	isAddHeaderSubmit=true;
    	console.log('addNewHeader -->', isAddHeaderSubmit);
        self.applicationTypeDetails.push({});
      };


      function removeHeader() {
          var lastItem = self.applicationTypeDetails.length-1;
          self.applicationTypeDetails.splice(lastItem);
          self.isAddHeaderSubmit=false;
        };


    function loadAllBulkRequests(){
    	 console.log('Load All Requests: --> ');
    	 BulkRequestService.loadAllBulkRequests()
    	 .then(
    	            function(d) {
    	                self.bulkRequestList = d;
    	            },

            function(errResponse){
                console.error('Error while fetching Requests');
            }
    	 );

    }




    function loadBulkRequest(bulkRequestID){
   	 console.log('Load  Request for bulkRequestID ID : --> ',bulkRequestID);
   	BulkRequestService.loadBulkRequest(bulkRequestID)
   	 .then(
   	            function(d) {
   	            	$scope.bulkRequest = d;
   	            	self.bulkRequest = d;
   	                console.log("self.appRequest.applicationName  ::  "+self.bulkRequest.name);

   	            },

           function(errResponse){
               console.error('Error while fetching Requests');
           }
   	 );

   }

    $scope.loadRequest1 = function (requestID) {
    	loadBulkRequest(requestID);
    };


    function createBulkRequest(bulkRequest){
    	console.log('File Details-->',$scope.myFile);
    	BulkRequestService.createBulkRequest(bulkRequest,$scope.myFile)
            .then(function(d) {
	            	console.log("Request created  ::  ");
   	            	//reset();
   	            	$window.location.href = '/ermapp/bulkRequestUpload';
   	            }
            		,
            function(errResponse){
                console.error('Error while creating Request');
            }
        );
    }

    function updateBulkAppRequest(bulkRequest){
    	BulkRequestService.updateBulkAppRequest(bulkRequest,$scope.myFile)
            .then(
            		loadAllBulkRequests,
            function(errResponse){
                console.error('Error while updating bulkupload');
            }
        );
    }


    function submit() {
        if(self.bulkRequest.bulkRequestID===null){
            console.log('Saving New request');
            createBulkRequest(self.bulkRequest);
        }else{
        	 console.log('updating bulkRequest ID : --> ', self.bulkRequest.bulkRequestID);

        	 updateBulkAppRequest(self.bulkRequest);

        }
        //reset();

        //$window.location.href = 'bulkRequestUpload/';
    }

    function edit(id1){
        console.log('bulkrequest ID ----->', id1);
        $window.location.href = 'editBulkRequest/'+id1;
    }






    function remove(requestId){
        console.log('Request to be deleted', requestId);

        BulkRequestService.deleteBulkAppRequest(requestId)
	   	 .then(
	   	            function(d) {
	   	            	console.log("Request deleted  ::  ");
	   	            	//reset();
	   	            	$window.location.href = 'bulkRequestUpload';
	   	            },

	           function(errResponse){
	               console.error('Error while deleting Request');
	           }
	   	 );

        reset();


    }

    function createNewRequest(){
    	 $window.location.href = 'addResource';

    }









     function reset(){

    	$scope.showme=false;

    	 self.bulkRequest={bulkRequestID:null,name:'',description:'',status:'',createdDate:'',updateDate:'',fileData:'',fileName:''};
    	    self.bulkRequestList=[];



        $scope.myForm.$setPristine(); //reset Form
        var file ='';
        //loadAllBulkRequests();

        $window.location.href = '/ermapp/bulkRequestUpload';
    }
     
     $scope.fnDownLoad = function (appBulkReqId) {
  	   console.log("appBulkReqId  ::  "+appBulkReqId);
  	   var s = 'downloadBulkFile/'+ appBulkReqId;
         $http.get('downloadBulkFile/'+appBulkReqId)
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

}]);
