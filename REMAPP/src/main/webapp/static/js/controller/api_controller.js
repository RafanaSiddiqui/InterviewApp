'use strict';

angular.module('myApp').controller('ApiController', ['$scope', 'ApiService', function($scope, ApiService) {
    var self = this;
    var isAddHeaderSubmit =false;
    self.apiRequest={id:null,apiName:'',desc:'',endPointUrl:'',headerList:self.headersList};
    self.apiRequestList=[];
    $scope.passType="password";

    self.headers={headerId:null,key:'',value:''};
    self.headersList = [];
    self.loadHeadList = [{key:"UserName",value:""},
                         {key:"Password",value:""}];

    loadAllAPI();

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    self.addNewHeader = addNewHeader;
    self.removeHeader = removeHeader;


    function addNewHeader() {
    	isAddHeaderSubmit=true;
    	console.log('addNewHeader -->', isAddHeaderSubmit);
    	if(self.headersList.length == 0) {
    		self.headersList.push({key:"UserName",value:""});
    		self.headersList.push({key:"Password",value:""});
    	} else {
    		self.headersList.push({});
    	}
    	};


      function removeHeader() {
          var lastItem = self.headersList.length-1;
          self.headersList.splice(lastItem);
          self.isAddHeaderSubmit=false;
        };


    function loadAllAPI(){
    	 console.log('Load All API: --> ');
    	ApiService.loadAllAPI()
    	 .then(
    	            function(d) {
    	                self.apiRequestList = d;
    	            },

            function(errResponse){
                console.error('Error while fetching API');
            }
    	 );

    }

    function createAPI(apiRequest){
    	ApiService.createAPI(apiRequest)
            .then(
            	loadAllAPI,
            function(errResponse){
                console.error('Error while creating API');
            }
        );
    }

    function updateAPI(apiRequest, id){
    	ApiService.updateAPI(apiRequest, id)
            .then(
            		loadAllAPI,
            function(errResponse){
                console.error('Error while updating API');
            }
        );
    }

    function deleteAPI(id){
    	ApiService.deleteAPI(id)
            .then(
            		loadAllAPI,
            function(errResponse){
                console.error('Error while deleting API');
            }
        );
    }

    function submit() {
    	console.log('On Submit isAddHeaderSubmit -->', isAddHeaderSubmit);
    	if(!(isAddHeaderSubmit&&$scope.showme)){
    	self.apiRequest.headerList=self.headersList;
        if(self.apiRequest.id===null){
            console.log('Saving New API', self.apiRequest.apiName);
            createAPI(self.apiRequest);
        }else{
        	 console.log('updating API Name : --> ', self.apiRequest.apiName);
        	 console.log('updating API id: --> ', self.apiRequest.id);
        	updateAPI(self.apiRequest, self.apiRequest.id);

        }
        reset();
    	}
    }

    function edit(id1){
        console.log('job to be edited', id1);
        for(var i = 0; i < self.apiRequestList.length; i++){

        	 console.log('self.apiRequestList[i].apiName-->', self.apiRequestList[i].headerList);
            if(self.apiRequestList[i].id === id1) {
            	console.log('Found API', id1);
                self.apiRequest = angular.copy(self.apiRequestList[i]);
                self.headersList = angular.copy(self.apiRequestList[i].headerList);
                $scope.showme=true;
                break;
            }
        }
    }

    function remove(id1){
        console.log('API to be deleted', id1);

        for(var i = 0; i < self.apiRequestList.length; i++){

       	 console.log('self.apiRequestList[i].apiName- deleted->', self.apiRequestList[i].apiName);
           if(self.apiRequestList[i].id === id1) {
           	console.log('Found API to be deleted', self.apiRequestList[i].apiName);
            reset();
            deleteAPI(id1);
               break;
           }

       }


    }

    function reset(){

    	$scope.showme=false;
    	console.log('$scope.showme->', $scope.showme);
        self.apiRequest={id:null,apiName:'',desc:'',endPointUrl:'',headerList:self.headersList};
        self.apiRequestList=[];


        self.headers={headerId:null,key:'',value:''};
        self.headersList = [];
    	isAddHeaderSubmit=false;

        $scope.myForm.$setPristine(); //reset Form
        loadAllAPI();
    }

    $scope.showText = function () {
    	$scope.passType="text";
    };

    $scope.hideText = function () {
    	$scope.passType="password";
    };

}]);
