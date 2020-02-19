'use strict';

angular.module('myApp').controller('ConfigurationController', ['$scope', '$window','ConfigurationService', function($scope, $window, ConfigurationService) {
    var self = this;
    var isAddHeaderSubmit =false;
    self.configuration={settingID:null,category:'',name:'',value:''};
    self.configurationList=[];

    self.configurationByCategory={category:'',configurationList:self.configurationList};
    self.configurationByCategoryList=[];

    loadAllConfiguration();

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.addNewHeader = addNewHeader;
    self.removeHeader = removeHeader;
    
    $scope.oneAtATime = true;

    function addNewHeader() {
    	isAddHeaderSubmit=true;
    	console.log('addNewHeader -->', isAddHeaderSubmit);
        self.configurationNewList.push({});
      };


      function removeHeader() {
          var lastItem = self.configurationNewList.length-1;
          self.configurationNewList.splice(lastItem);
          self.isAddHeaderSubmit=false;
        };


    function loadAllConfiguration(){
    	 console.log('Load All Configurations: --> ');
    	 ConfigurationService.loadAllConfiguration()
    	 .then(
    	            function(d) {
    	            	console.log("loadAllConfiguration response  ::  ",d);
    	            	/*var str = JSON.stringify(d);
    	            	str = JSON.stringify(d, null, 4); // (Optional) beautiful indented output.
    	            	console.log(str); // Logs output to dev tools console.*/
    	                self.configurationByCategoryList = d;
    	            },

            function(errResponse){
                console.error('Error while fetching Requests');
            }
    	 );

    }

    function createConfiguration(configuration){
    	ConfigurationService.createConfiguration(configuration)
            .then(
            function(d) {
            	loadAllConfiguration();
            	console.log("createConfiguration done");
            },
            function(errResponse){
                console.error('Error while creating Configuration');
            }
        );
    }

    function submit(category) {
    	console.log('category-->', category);
    	console.log('On Submit isAddHeaderSubmit category-->', category);
    	if(!(isAddHeaderSubmit&&$scope.showme)){
    	self.configuration=self.configuration;
    	self.configuration.category=category;
            console.log('Saving New request', self.configuration.category);
            createConfiguration(self.configuration);
        reset();
    	}
    }



    function edit(id1){
        console.log('job to be edited', id1);
        for(var i = 0; i < self.configurationList.length; i++){

        	 console.log('self.appRequestList[i].requestI-->', self.configurationList[i].category);
            if(self.configurationList[i].settingID === id1) {
            	console.log('Found API', id1);
                self.configuration = angular.copy(self.configurationList[i]);
                self.configuration = angular.copy(self.configurationList[i]);
                $scope.showme=true;
                break;
            }
        }
    }

    function remove(id1){
        console.log('API to be deleted', id1);

        for(var i = 0; i < self.configurationList.length; i++){

       	 console.log('self.appRequestList[i].apiName- deleted->', self.configurationList[i].category);
           if(self.configurationList[i].settingID === id1) {
           	console.log('Found API to be deleted', self.configurationList[i].category);
            reset();

               break;
           }

       }


    }

     function reset(){

    	$scope.showme=false;
    	console.log('$scope.showme->', $scope.showme);
    	self.configuration={settingID:null,category:'',name:'',value:''};
        self.configurationList=[];
        self.configurationNew={settingID:null,category:'',name:'',value:''};
        self.configurationNewList=[];
    	isAddHeaderSubmit=false;

        $scope.myForm.$setPristine(); //reset Form
        loadAllConfiguration();
    }

     $scope.addEntry = function(rowId) {

  	   console.log("rowId  ::  "+rowId);
  	   $scope.inserted = {
  			   	 name: '',
  			   	 value: ''
  	   };
  	   if(self.configurationByCategoryList.length != 0) {
  		 self.configurationByCategoryList[rowId].configurationList.push($scope.inserted);
  	   }
  	   console.log('self.configurationNewList[rowId] after  ::  ',self.configurationByCategoryList[rowId]);
     };

     $scope.removeEntry = function(arrayId, rowId) {
  	   console.log("arrayId  ::  "+arrayId+"  ::  rowId  ::  "+rowId);
  	   var settingID = self.configurationByCategoryList[arrayId].configurationList[rowId].settingID;
  	   console.log("settingID  ::  "+settingID);
  	   deleteConfiguration(settingID);
  	   self.configurationByCategoryList[arrayId].configurationList.splice(rowId, 1);
     };


     $scope.editEntry = function(configurationList) {
    	   //console.log("arrayId  ::  "+arrayId+"  ::  rowId  ::  "+rowId);
    	   console.log("$scope.globalConfiguration  ::  "+$scope.globalConfiguration);
    	   console.log("configurationList  ::  ",configurationList);
    	   //configurationList.configGroupName = configurationList.name.substring(0, configurationList.name.indexOf('.'));
    	   if(configurationList.settingID == undefined) {
    		   createConfiguration(configurationList);
    	   } else {
    		   updateConfiguration(configurationList);
    	   }
    	   
     };
     
     $scope.updateRadioConfiguration= function(configurationList) {
  	   	 console.log("configurationList.value  ::  "+configurationList.value);
  		 updateConfiguration(configurationList);
     };

     function updateConfiguration(configuration){
     	ConfigurationService.updateConfiguration(configuration)
             .then(
    		 function(d) {
                console.log("updateConfiguration done");
                loadAllConfiguration();
    		 },
             function(errResponse){
                 console.error('Error while updateConfiguration');
             }
         );
     }

     function deleteConfiguration(settingId){
      	ConfigurationService.deleteConfiguration(settingId)
              .then(
     		 function(d) {
                 console.log("deleteConfiguration done");
                 loadAllConfiguration();
     		 },
              function(errResponse){
                  console.error('Error while deleteConfiguration');
              }
          );
      }



}]);
