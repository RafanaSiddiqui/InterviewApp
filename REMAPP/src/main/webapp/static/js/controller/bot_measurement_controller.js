'use strict';

angular.module('myApp').controller('BotMeasurementController', ['$scope', '$window','BotMeasurementService', 'BotService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', '$filter', function($scope, $window, BotMeasurementService, BotService, $uibModal, $timeout, UserDetailsService, MasterDemandService, $filter) {
    var self = this;


    self.botMeasurement = {id:null, botId:null, competency:'', botName:'', projectId:'', projectName:'', customerName:'', createdDate:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', noOfExecutions:'', noOfFailures:'', totalHrsSaved:'', dataEnteredBy:'', compManager:'', createdMonth:'', accountBaseId:''}
    $scope.botMeasurement = {id:null, botId:null, competency:'', botName:'', projectId:'', projectName:'', customerName:'', createdDate:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', noOfExecutions:'', noOfFailures:'', totalHrsSaved:'', dataEnteredBy:'', compManager:'', createdMonth:'', accountBaseId:''}
    //$scope.botMeasurementClone = {id:null, botId:null, competency:'', botName:'', projectId:'', projectName:'', customerName:'', createdDate:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', noOfExecutions:'', noOfFailures:'', totalHrsSaved:'', dataEnteredBy:'', compManager:'', createdMonth:'', accountBaseId:''}
    self.botMeasurementList = [];

    loadAllBotMeasurements();
    loadAllBots();
    loadAllConfigurations();
    getUserRole();
    loadAllAccountBase();
    //loadAllTemplate();

    self.submit = submit;
    self.update = update;
    self.clone = clone;
    //self.remove = remove;
    self.reset = reset;
    self.createNewBotMeasurement = createNewBotMeasurement;
    self.editBotMeasurement = editBotMeasurement;
    self.deleteBotMeasurement = deleteBotMeasurement;
    self.openBotMeasurement = openBotMeasurement;
    $scope.statusList = ["Pending", "Reviewed", "Completed"];
	
    $scope.botMsrts = {};
    $scope.botMsrts.selected = {};
    
    self.botMeasurementClone = {};
    
    $scope.roleList = [];
    
	$scope.sort = function(keyname) {
	        $scope.sortKey = keyname;   //set the sortKey to the param passed
	        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	  }
	  
      $scope.fnDownLoad = function () {
	 	   console.log("inside fnDownLoad");
	 	   var s = "downloadExcelFile/";
	       $window.open(s);
	    };
    
        function loadAllBots() {
       	 console.log('inside loadAllBots');
       	 BotService.loadAllBots()
       	 .then(
   	            function(d) {
   	            	$scope.pageableBots = d;
   	            	angular.forEach($scope.pageableBots, function(value, key){
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
   	                console.log('$scope.pageableBots  ::  ',$scope.pageableBots);
   	            },

               function(errResponse){
                   console.error('Error while fetching bots');
               }
       	 );

       };
        
       function loadAllConfigurations() {
    	   	 console.log('inside loadAllConfigurations');
    	   	 MasterDemandService.loadAllConfigurations()
    	   	 .then(
    		            function(d) {
    		            	$scope.allConfList = d;
    		            	//console.log('$scope.allConfList  ::  ',$scope.allConfList);
    		            	angular.forEach($scope.allConfList, function(value, key){
    		            		//console.log('value.category  ::  '+value.category);
    		            		if(value.category == "CustomerDetails") {
    		            			$scope.customerDetails = value.configurationList;
    		            		} else if(value.category == "Competency") {
    		            			$scope.competencies = value.configurationList;
    		            		}
    		            		//console.log('value.configurationList  ::  ',value.configurationList);
    		            	});
    		            },

    	           function(errResponse){
    	               console.error('Error while loadAllConfigurations');
    	           }
    	   	 );

    	 };   
    
    	 $scope.MONTH_NAMES = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
    		  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    		];
    	 
    function loadAllBotMeasurements() {
    	 console.log('inside loadAllBotMeasurements');
    	 BotMeasurementService.loadAllBotMeasurements()
    	 .then(
	            function(d) {
	            	$scope.pageableBotMeasurements = d;
	            	angular.forEach($scope.pageableBotMeasurements, function(value, key) {
   	            		console.log('value  ::  '+value);
   	            		crDate = new Date(value.createdMonth);
   	            		
   	            		var dd = crDate.getDate();
   	            		var mm = crDate.getMonth()+1; //January is 0!

   	            		var yyyy = crDate.getFullYear();
   	            		if(dd<10){
   	            		    dd='0'+dd;
   	            		} 
   	            		if(mm<10){
   	            		    mm=mm;
   	            		} 
   	            		
   	            		var crDate = $scope.MONTH_NAMES[mm-1]+' '+yyyy;
   	            		
   	            		value.createdMonth = crDate;
   	            		
   	            	});
	                console.log('$scope.pageableBotMeasurements  ::  ',$scope.pageableBotMeasurements);
	            },

            function(errResponse){
                console.error('Error while fetching bots');
            }
    	 );

    };
    
    function loadAllAccountBase() {
   	 //console.log('inside loadAllAccountBase');
   	 BotMeasurementService.loadAllAccountBase()
   	 .then(
               function(d) {
               	$scope.accountBaseList = d;
                   //console.log('$scope.accountBaseList  ::  ',$scope.accountBaseList);
               },

           function(errResponse){
               console.error('Error while fetching pageableAccountBases');
           }
   	 );

   };
    
    function loadAllDemandPageableRequests(pageNumber, pageSize) {
   	 console.log('inside loadAllDemandPageableRequests pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	 BotMeasurementService.loadAllDemandPageableRequests(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	console.log('reponse  ::  ',d);
	            	self.pageableBots = d.content;
	            	$scope.pageableBots = d;
	            },

           function(errResponse){
               console.error('Error while fetching Requests');
           }
   	 );

   };
   
   
   /*function loadAllMasterRequests() {
	   	 console.log('inside loadAllMasterRequests');
	   	 BotMeasurementService.loadAllMasterRequests(pageNumber, pageSize)
	   	 .then(
		            function(d) {
		            	console.log('reponse  ::  ',d);
		            	self.demandRequestList = d;
		            	$scope.pageableDemandRequest = d;
		            	
		            	angular.forEach($scope.pageableDemandRequest, function(value, key){
		            		console.log('value  ::  '+value);
		            		if(value.creationDate != null || value.creationDate === "") {
		            		crDate = new Date(value.creationDate);
		            		
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
		            		
		            		value.creationDate = crDate;
		            		}
		            	});
		            	
		            },

	           function(errResponse){
	               console.error('Error while fetching Requests');
	           }
	   	 );

	   };*/
   
   
    function openBotMeasurement (botId) {
    	console.log("inside openBot");
    	loadBotMeasurementById(botId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewBotMeasurement.jsp',
               size: 'lg',
               controllerAs: 'BotMeasurementController',
               scope: $scope

             });
    };
    
    function editBotMeasurement (botMeasurementId) {
    	console.log("inside editBot  ::  "+botMeasurementId);
    	$window.location.href = 'editBotMeasurement/'+botMeasurementId;
    	/*loadBotMeasurementById(botMeasurementId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/editBotMeasurement.jsp',
               size: 'lg',
               controllerAs: 'BotMeasurementController',
               scope: $scope
             });*/
    };
    
    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.searchBySoId = function(soId) {
    	console.log("soId  ::  "+soId);
    	
    	BotMeasurementService.loadBotById(soId, 1, 10)
      	 .then(
   	            function(d) {
   	            	console.log('reponse  ::  ',d);
   	            	self.demandRequestList = d.content;
   	            	$scope.pageableDemandRequest = d;
   	            	
   	            	angular.forEach($scope.pageableDemandRequest.content, function(value, key){
   	            		console.log('value  ::  '+value);
   	            		crDate = new Date(value.createdMonth);
   	            		
   	            		var dd = crDate.getDate();
   	            		var mm = crDate.getMonth()+1; //January is 0!

   	            		var yyyy = crDate.getFullYear();
   	            		if(dd<10){
   	            		    dd='0'+dd;
   	            		} 
   	            		if(mm<10){
   	            		    mm='0'+mm;
   	            		} 
   	            		var crDate = Mon+' '+yy;
   	            		
   	            		value.creationDate = crDate;
   	            		
   	            	});
   	            	
   	            },

              function(errResponse){
                  console.error('Error while fetching Requests');
              }
      	 );
    	
    }
    
    $scope.editBotMeasurementById = function(botMeasurementId) {
    	//console.log('botMeasurementId  ::  '+botMeasurementId);
    	loadBotMeasurementById(botMeasurementId);
    };
    
    $scope.onSelectCustName = function(custName) {
   	 console.log("custName  ::  "+custName);
   	 self.botMeasurement.compManager = $filter('filter')($scope.customerDetails, {name: custName})[0].value;
   	 $scope.botMeasurement.compManager = $filter('filter')($scope.customerDetails, {name: custName})[0].value;
   	 console.log("self.botMeasurement.compManager  ::  ",self.botMeasurement.compManager);
    }
    
    $scope.onSelectBotName = function(botMeasurement) {
      	 console.log("botMeasurement  ::  ",botMeasurement);
      	 /*self.botMeasurement.manualEffortBeforeBot = $filter('filter')($scope.pageableBots, {botId: botMeasurement.botId})[0].manualEffortBeforeBot;
      	 self.botMeasurement.manualEffortAfterBot = $filter('filter')($scope.pageableBots, {botId: botMeasurement.botId})[0].manualEffortAfterBot;
      	 $scope.botMeasurement.manualEffortBeforeBot = $filter('filter')($scope.pageableBots, {botId: botMeasurement.botId})[0].manualEffortBeforeBot;
      	 $scope.botMeasurement.manualEffortAfterBot = $filter('filter')($scope.pageableBots, {botId: botMeasurement.botId})[0].manualEffortAfterBot;*/
      	 
      	 self.botMeasurement.manualEffortBeforeBot = botMeasurement.manualEffortBeforeBot;
     	 self.botMeasurement.manualEffortAfterBot = botMeasurement.manualEffortAfterBot;
     	 $scope.botMeasurement.manualEffortBeforeBot = botMeasurement.manualEffortBeforeBot;
     	 $scope.botMeasurement.manualEffortAfterBot = botMeasurement.manualEffortAfterBot;
      	 
      	 $scope.botMeasurement.competency = botMeasurement.competency;
      	 self.botMeasurement.competency = botMeasurement.competency;
      	 self.botMeasurement.botId = botMeasurement.botId;
      	 $scope.botMeasurement.botId = botMeasurement.botId;
      	 self.botMeasurement.botName = botMeasurement.botName;
      	 $scope.botMeasurement.botName = botMeasurement.botName;
      	 
      	 $scope.botMeasurement.projectId = botMeasurement.projectId;
      	 self.botMeasurement.projectId = botMeasurement.projectId;
      	 $scope.botMeasurement.projectName = botMeasurement.projectName;
     	 self.botMeasurement.projectName = botMeasurement.projectName;
     	 
     	 $scope.botMeasurement.customerName = botMeasurement.customerName;
     	 self.botMeasurement.customerName = botMeasurement.customerName;
     	 /*$scope.botMeasurement.compManager = botMeasurement.compManager;
    	 self.botMeasurement.compManager = botMeasurement.compManager;*/
     	 $scope.botMeasurement.compManager = botMeasurement.compManager;
     	 self.botMeasurement.compManager = botMeasurement.compManager;
     	 $scope.botMeasurement.accountBaseId = botMeasurement.accountBaseId;
     	 self.botMeasurement.accountBaseId = botMeasurement.accountBaseId;
     	 
     	 console.log('$scope.botMeasurement.accountBaseId  ::  ',$scope.botMeasurement.accountBaseId);
     	 
     }
    
    $scope.onChangeExec = function() {
    	self.botMeasurement.totalHrsSaved=(self.botMeasurement.manualEffortBeforeBot-self.botMeasurement.manualEffortAfterBot)*(self.botMeasurement.noOfExecutions-self.botMeasurement.noOfFailures);
    	$scope.botMeasurement.totalHrsSaved=($scope.botMeasurement.manualEffortBeforeBot-$scope.botMeasurement.manualEffortAfterBot)*($scope.botMeasurement.noOfExecutions-$scope.botMeasurement.noOfFailures);
    	$scope.botMeasurementClone.totalHrsSaved = ($scope.botMeasurementClone.manualEffortBeforeBot-$scope.botMeasurementClone.manualEffortAfterBot)*($scope.botMeasurementClone.noOfExecutions-$scope.botMeasurementClone.noOfFailures);
    	/*console.log("self.botMeasurement.totalHrsSaved  ::  "+self.botMeasurement.totalHrsSaved);
    	console.log("$scope.botMeasurement.totalHrsSaved  ::  "+$scope.botMeasurement.totalHrsSaved);*/
    }
    
    $scope.onChangeExecClone = function() {
    	$scope.botMeasurementClone.totalHrsSaved = ($scope.botMeasurementClone.manualEffortBeforeBot-$scope.botMeasurementClone.manualEffortAfterBot)*($scope.botMeasurementClone.noOfExecutions-$scope.botMeasurementClone.noOfFailures);
    	/*console.log("self.botMeasurement.totalHrsSaved  ::  "+self.botMeasurement.totalHrsSaved);
    	console.log("$scope.botMeasurement.totalHrsSaved  ::  "+$scope.botMeasurement.totalHrsSaved);*/
    }
    
    function loadBotMeasurementById(botMeasurementId) {
   	 console.log('loadBotMeasurementById  ::  ',botMeasurementId);
   	BotMeasurementService.loadBotMeasurementById(botMeasurementId)
   	 .then(
   			 
   	       function(d) {
   	            	$scope.botMeasurement = d;
   	            	self.botMeasurement = d;
   	            	$scope.botMsrts.selected.botName = d.botName;
   	            	/*console.log('$scope.botMeasurement  ::  ',$scope.botMeasurement);
   	            	$scope.compManager = $filter('filter')($scope.accountBaseList, {id: d.accountBaseId})[0].competencyManager;*/
   	            	if($scope.botMeasurement.createdDate != null) {
   	            		$scope.botMeasurement.createdDate = new Date($scope.botMeasurement.createdDate);
   	            	}
   	            	if($scope.botMeasurement.createdMonth != null) {
   	            		$scope.botMeasurement.createdMonth = new Date($scope.botMeasurement.createdMonth);
   	            	}
   	                console.log("$scope.botMeasurement  ::  ",$scope.botMeasurement);
   	        },

           function(errResponse) {
               console.error('Error while fetching demandRequest');
           }
   	 );

   }



    
    function addBotMeasurement(botMeasurement) {
    	console.log('inside addBot bot  ::  ',botMeasurement);
    	BotMeasurementService.addBotMeasurement(botMeasurement)
            .then(
            		botMeasurement,
            function(errResponse){
                console.error('Error while adding bot');
            }
        );
    }

    function updateBotMeasurement(botMeasurement) {
    	BotMeasurementService.updateBotMeasurement(botMeasurement)
            .then(
            		botMeasurement,
            function(errResponse){
                console.error('Error while updating botMeasurement');
            }
        );
    }

    
    function submit(myForm) {
	    /*if(self.botMeasurement != null) {
	    	self.botMeasurement.botId = self.botMeasurement.botName.botId;
	    	self.botMeasurement.botName = self.botMeasurement.botName.botName;
	    }*/
	    console.log('self.botMeasurement  ::  ', self.botMeasurement);
	    addBotMeasurement(self.botMeasurement);
        reset();
        $window.location.href = '/ermapp/showAllMeasurements';
    }
    
    function update(myForm) {
   	  	updateBotMeasurement($scope.botMeasurement);
   	  	reset();
        $window.location.href = '/ermapp/showAllMeasurements';
    }
    
    function deleteBotMeasurement(botId){
        console.log('inside deleteBot botId  ::  ', botId);

        BotMeasurementService.deleteBotMeasurement(botId)
	   	 .then(
   	            function(d) {
   	            	console.log("bot deleted  ::  ");
   	            	//reset();
   	            	$window.location.href = 'showAllMeasurements';
   	            },

	           function(errResponse){
	               console.error('Error while deleting bot');
	           }
	   	 );
        

    }

    function createNewBotMeasurement() {
    	
    	 console.log('inside createNewBot');
    	 $window.location.href = 'createBotMeasurement';

    }

     function reset() {
    	 
    	$scope.botMeasurement = {id:null, botId:null, competency:'', botName:'', projectId:'', projectName:'', customerName:'', createdDate:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', noOfExecutions:'', noOfFailures:'', totalHrsSaved:'', dataEnteredBy:'', compManager:'', createdMonth:'', accountBaseId:''}
        $window.location.href = '/ermapp/showAllMeasurements';
        
    }
	
	 function getUserRole()
   {
   	UserDetailsService.getUserRole()
   	.then( function(d) {
   		$scope.userRole = d.role;
   		self.botMeasurement.dataEnteredBy = d.username;
   		$scope.botMeasurement.dataEnteredBy = d.username;
   		$scope.userRolesList = d.userRolesList;
		
		if($scope.userRolesList != null) {
			angular.forEach($scope.userRolesList, function(value, key) {
				$scope.roleList.push(value.roleName);
			});
		}
   		console.log('userRole is',$scope.userRole);
        },
             function(errResponse){
                 console.error('Error while getUserRole');
             }
     	 );
   	}
	 
	 $scope.checkRole = function(roleName) {
			//console.log("roleName  ::  ",roleName);
			if($scope.roleList.indexOf(roleName) != -1){
				return true;
			} else {
				return false;
			}
		}
	 
	 $scope.getDefaulterList = function(botMonth) {
		 console.log("botMonth  ::  ",botMonth);
		 BotMeasurementService.getDefaulterList(botMonth)
	   	 .then(
	   			 
	   	       function(d) {
	   	            	$scope.compManList = d;
	   	        },

	           function(errResponse) {
	               console.error('Error while fetching getDefaulterList');
	           }
	   	 );
	 }
	 
	 $scope.botListDownLoad = function (botMonth) {
   	   console.log("inside botListDownLoad");
   	   var s = "downloadDefaulterFile/"+botMonth;
         $window.open(s);
      };
      
      $scope.onSelectRadio = function(botMeasurement) {
    	  console.log("botMeasurement  ::  ",botMeasurement);
    	  $scope.botMeasurementClone = JSON.parse(angular.toJson(botMeasurement));
    	  console.log("$scope.botMeasurementClone  ::  ",$scope.botMeasurementClone);
      }
      
      $scope.cloneBotMeasurement = function(botMeasurementClone) {
    	$scope.botMeasurementClone = JSON.parse(angular.toJson(botMeasurementClone));
    	//$scope.botMeasurementClone = botMeasurementClone;
    	console.log("$scope.botMeasurementClone  ::  ",$scope.botMeasurementClone);
    	$scope.$uibModalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'static/pages/cloneBotMeasurement.jsp',
            size: 'lg',
            controllerAs: 'BotMeasurementController',
            scope: $scope
        });
    	 
    	//self.clone();
    	  
      }
      
      function clone() {
  	    console.log('inside clone $scope.botMeasurementClone  ::  ', $scope.botMeasurementClone);
  	    $scope.botMeasurementClone.id = null;
  	    addBotMeasurement($scope.botMeasurementClone);
        reset();
        $window.location.href = '/ermapp/showAllMeasurements';
      }

}]);
