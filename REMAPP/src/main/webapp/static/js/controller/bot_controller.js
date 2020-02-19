'use strict';

angular.module('myApp').controller('BotController', ['$scope', '$window','BotService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', '$filter', function($scope, $window, BotService, $uibModal, $timeout, UserDetailsService, MasterDemandService, $filter) {
    var self = this;


    self.bot = {botId:null, competency:'', botName:'', automationSolution:'', automationSolutionActual:'', projectId:'', projectName:'', customerName:'', botCategory:'', botCreatedBy:'', useCaseOrPurpose:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', supportOrEngineering:'', compManager:'', accountBaseId:'', securityDomain:'', serviceCategory:'', service:'', functionType:'', operationalMetrics:''}
    $scope.bot = {botId:null, competency:'', botName:'', automationSolution:'', automationSolutionActual:'', projectId:'', projectName:'', customerName:'', botCategory:'', botCreatedBy:'', useCaseOrPurpose:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', supportOrEngineering:'', compManager:'', accountBaseId:'', securityDomain:'', serviceCategory:'', service:'', functionType:'', operationalMetrics:''}
    self.botList = [];

    loadAllBots();
    
    getUserRole();
    
    loadAllConfigurations();
    
    loadAllAccountBase();
    
    //loadAllTemplate();

    self.submit = submit;
    self.update = update;
    //self.remove = remove;
    self.reset = reset;
    self.createNewBot = createNewBot;
    self.editBot = editBot;
    self.deleteBot = deleteBot;
    self.openBot = openBot;
    
    $scope.accountBase = {};
    $scope.accountBase.selected = {};
    
    /*$scope.manualEffortBeforeBotHr = 0;
    $scope.manualEffortBeforeBotMin = 0
    $scope.manualEffortAfterBotHr = 0;
    $scope.manualEffortAfterBotMin = 0;*/
    
    /*$scope.statusList = ["Pending", "Reviewed", "Completed"];
    $scope.botCategoryList = ["Complete Automation", "Semi Automation", "Complete Manual"];
    $scope.supportList = ["Support", "Engineering"];
    $scope.solutionList = ["Custom", "Automation Anywhere", "BluePrism", "UIPath", "WinAutomation", "Ayehu", "Others"];*/

    $scope.roleList = [];
    
    $scope.sort = function(keyname) {
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	  
      $scope.fnDownLoad = function () {
     	   //console.log("inside fnDownLoad");
     	   var s = "downloadExcelFile/";
           $window.open(s);
        };

    function loadAllBots() {
    	 //console.log('inside loadAllBots');
    	 BotService.loadAllBots()
    	 .then(
	            function(d) {
	            	$scope.pageableBots = d;
	            	angular.forEach($scope.pageableBots, function(value, key){
   	            		//console.log('value  ::  '+value);
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
	                //console.log('$scope.pageableBots  ::  ',$scope.pageableBots);
	            },

            function(errResponse){
                console.error('Error while fetching bots');
            }
    	 );

    };
    
    function loadAllConfigurations() {
   	 //console.log('inside loadAllConfigurations');
   	 MasterDemandService.loadAllConfigurations()
   	 .then(
	            function(d) {
	            	$scope.allConfList = d;
	            	////console.log('$scope.allConfList  ::  ',$scope.allConfList);
	            	angular.forEach($scope.allConfList, function(value, key){
	            		////console.log('value.category  ::  '+value.category);
	            		if(value.category == "CustomerDetails") {
	            			$scope.customerDetails = value.configurationList;
	            		} else if(value.category == "Competency") {
	            			$scope.competencies = value.configurationList;
	            		} else if(value.category == "ActualSolution") {
	            			$scope.solutionList = value.configurationList;
	            		} else if(value.category == "Support") {
	            			$scope.supportList = value.configurationList;
	            		} else if(value.category == "BotCategory") {
	            			$scope.botCategoryList = value.configurationList;
	            		} else if(value.category == "Status") {
	            			$scope.statusList = value.configurationList;
	            		} else if(value.category == "Status") {
	            			$scope.statusList = value.configurationList;
	            		} else if(value.category == "SecurityDomain") {
	            			$scope.securityDomainList = value.configurationList;
	            		} else if(value.category == "ServiceCategory") {
	            			$scope.serviceCategoryList = value.configurationList;
	            		} else if(value.category == "Service") {
	            			$scope.serviceList = value.configurationList;
	            		} else if(value.category == "Function") {
	            			$scope.functionTypeList = value.configurationList;
	            		} else if(value.category == "OperationalMetrics") {
	            			$scope.operationalMetricsList = value.configurationList;
	            		}
	            		
	            		//console.log('value.configurationList  ::  ',value.configurationList);
	            	});
	            },

           function(errResponse){
               console.error('Error while loadAllConfigurations');
           }
   	 );

 };
 
 function loadAllAccountBase() {
	 console.log('inside loadAllAccountBase');
	 BotService.loadAllAccountBase()
	 .then(
            function(d) {
            	$scope.accountBaseList = d;
                console.log('$scope.accountBaseList  ::  ',$scope.accountBaseList);
            },

        function(errResponse){
            console.error('Error while fetching pageableAccountBases');
        }
	 );

};
 
 $scope.onSelectCustName = function(accountBase) {
	 console.log("accountBase  ::  ",accountBase);
	 /*self.bot.compManager = $filter('filter')($scope.accountBaseList, {accountName: custName})[0].value;
	 $scope.bot.compManager = $filter('filter')($scope.accountBaseList, {accountName: custName})[0].value;*/
	 self.bot.customerName = accountBase.accountName;
	 $scope.bot.customerName = accountBase.accountName;
	 self.bot.accountBaseId = accountBase.accountBaseId;
	 $scope.bot.accountBaseId = accountBase.accountBaseId;
	 self.bot.compManager = accountBase.competencyManager;
	 $scope.bot.compManager = accountBase.competencyManager;
	 
	 //console.log("self.bot.compManager  ::  ",self.bot.compManager);
 }
    function loadAllDemandPageableRequests(pageNumber, pageSize) {
   	 //console.log('inside loadAllDemandPageableRequests pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	 BotService.loadAllDemandPageableRequests(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	//console.log('reponse  ::  ',d);
	            	self.pageableBots = d.content;
	            	$scope.pageableBots = d;
	            },

           function(errResponse){
               console.error('Error while fetching Requests');
           }
   	 );

   };
   
   
   /*function loadAllMasterRequests() {
	   	 //console.log('inside loadAllMasterRequests');
	   	 BotService.loadAllMasterRequests(pageNumber, pageSize)
	   	 .then(
		            function(d) {
		            	//console.log('reponse  ::  ',d);
		            	self.demandRequestList = d;
		            	$scope.pageableDemandRequest = d;
		            	
		            	angular.forEach($scope.pageableDemandRequest, function(value, key){
		            		//console.log('value  ::  '+value);
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
   
   
    function openBot (botId) {
    	//console.log("inside openBot");
    	loadBotById(botId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewBot.jsp',
               size: 'lg',
               controllerAs: 'BotController',
               scope: $scope

             });
    };
    
    function editBot (botId) {
    	//console.log("inside editBot  ::  "+botId);
    	$window.location.href = 'editBot/'+botId;
    	/*loadBotById(botId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/editBot.jsp',
               size: 'lg',
               controllerAs: 'BotController',
               scope: $scope
             });*/
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.searchBySoId = function(soId) {
    	//console.log("soId  ::  "+soId);
    	
    	BotService.loadBotById(soId, 1, 10)
      	 .then(
   	            function(d) {
   	            	//console.log('reponse  ::  ',d);
   	            	self.demandRequestList = d.content;
   	            	$scope.pageableDemandRequest = d;
   	            	
   	            	angular.forEach($scope.pageableDemandRequest.content, function(value, key){
   	            		//console.log('value  ::  '+value);
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
   	            		
   	            	});
   	            	
   	            },

              function(errResponse){
                  console.error('Error while fetching Requests');
              }
      	 );
    	
    }
    
    $scope.editBotById = function(botId) {
    	//console.log('botId  ::  '+botId);
    	loadBotById(botId);
    };
    
    function loadBotById(botId) {
   	 //console.log('loadBotById  ::  ',botId);
   	BotService.loadBotById(botId)
   	 .then(
   	       function(d) {
   	            	$scope.bot = d;
   	            	self.bot = d;
   	            	$scope.accountBase.selected.accountName = d.customerName;
   	            	//console.log("$scope.bot  ::  ",$scope.bot);
   	            	$scope.manualEffortBeforeBotHr = decimalToHr(self.bot.manualEffortBeforeBot);
   	            	$scope.manualEffortBeforeBotMin = decimalToMin(self.bot.manualEffortBeforeBot);
   	            	$scope.manualEffortAfterBotHr = decimalToHr(self.bot.manualEffortAfterBot);
   	            	$scope.manualEffortAfterBotMin = decimalToMin(self.bot.manualEffortAfterBot);
   	            	
   	            	if($scope.bot.createdDate != null) {
   	            		$scope.bot.createdDate = new Date($scope.bot.createdDate);
   	            	}
   	            	
   	                //console.log("$scope.bot  ::  ",$scope.bot);
   	        },

           function(errResponse) {
               console.error('Error while fetching demandRequest');
           }
   	 );

   }

    function addBot(bot) {
    	//console.log('inside addBot bot  ::  ',bot);
    	BotService.addBot(bot)
            .then(
            		bot,
            function(errResponse){
                console.error('Error while adding bot');
            }
        );
    }

    function updateBot(bot) {
    	$scope.manualEffortBeforeBotHr = $scope.manualEffortBeforeBotHr==undefined?0:$scope.manualEffortBeforeBotHr;
        $scope.manualEffortBeforeBotMin = $scope.manualEffortBeforeBotMin==undefined?0:$scope.manualEffortBeforeBotMin;
        $scope.manualEffortAfterBotHr = $scope.manualEffortAfterBotHr==undefined?0:$scope.manualEffortAfterBotHr;
        $scope.manualEffortAfterBotMin = $scope.manualEffortAfterBotMin==undefined?0:$scope.manualEffortAfterBotMin;
    	
	    self.bot.manualEffortBeforeBot = filterTimeFormat($scope.manualEffortBeforeBotHr+':'+$scope.manualEffortBeforeBotMin);
	    self.bot.manualEffortAfterBot = filterTimeFormat($scope.manualEffortAfterBotHr+':'+$scope.manualEffortAfterBotMin);
	    
    	BotService.updateBot(bot)
            .then(
            		bot,
            function(errResponse){
                console.error('Error while updating bot');
            }
        );
    }

    function decimalToHr(deciValue) {
    	var value = deciValue + '';
    	var hr = deciValue;
    	if(value.indexOf('.')) {
    		var temp = new Array();
    		temp = value.split('.');
    		hr = parseInt(temp[0]);
    	}
    	return hr;
    }
    
    function decimalToMin(deciValue) {
    	/*var value = deciValue + '';
    	var temp = new Array();
    	var minutes = 0;
    	if(value.indexOf('.')) {
    		temp = value.split('.');
    		minutes = 100 / temp[1];
    		minutes = 60 / minutes;
    	}
    	return parseInt(minutes+1);*/
    	
    	var decimalTime = parseFloat(deciValue);
    	decimalTime = decimalTime * 60 * 60;
    	var hours = Math.floor((decimalTime / (60 * 60)));
    	decimalTime = decimalTime - (hours * 60 * 60);
    	var minutes = Math.floor((decimalTime / 60));
    	decimalTime = decimalTime - (minutes * 60);
    	var seconds = Math.round(decimalTime);
    	return parseInt(minutes);
    	
    }
    
    function filterTimeFormat(time) {
    	
    	var hours;
    	var minutes;
    	var time;
    	
    	
    	// Number of decimal places to round to
    	var decimal_places = 2;

    	// Maximum number of hours before we should assume minutes were intended. Set to 0 to remove the maximum.
    	var maximum_hours = 15;

    	// 3
    	var int_format = time.match(/^\d+$/);

    	// 1:15
    	var time_format = time.match(/([\d]*):([\d]+)/);

    	// 10m
    	var minute_string_format = time.toLowerCase().match(/([\d]+)m/);

    	// 2h
    	var hour_string_format = time.toLowerCase().match(/([\d]+)h/);

    	if (time_format != null) {
    		hours = parseInt(time_format[1]);
    		minutes = parseFloat(time_format[2]/60);
    		time = hours + minutes;
    	} else if (minute_string_format != null || hour_string_format != null) {
    		if (hour_string_format != null) {
    			hours = parseInt(hour_string_format[1]);
    		} else {
    			hours = 0;
    		}
    		if (minute_string_format != null) {
    			minutes = parseFloat(minute_string_format[1]/60);
    		} else {
    			minutes = 0;
    		}
    		time = hours + minutes;
    	} else if (int_format != null) {
    		// Entries over 15 hours are likely intended to be minutes.
    		time = parseInt(time);
    		if (maximum_hours > 0 && time > maximum_hours) {
    			time = (time/60).toFixed(decimal_places);
    		}
    	}

    	// make sure what ever we return is a 2 digit float
    	time = parseFloat(time).toFixed(decimal_places);

    	return time;
    }
    
    function timeToDecimal(t) {
    	//console.log("t  ::  "+t);
        var arr = t.split(':');
        return parseFloat(parseInt(arr[0], 10) + '.' + parseInt((arr[1]/6)*10, 10));
    } 
    
    function submit(myForm) {
    	
    	if(self.bot.automationSolution != null && self.bot.automationSolution != 'Others') {
    		self.bot.automationSolutionActual = self.bot.automationSolution; 
    	}
    	
    	checkBotName(self.bot.botName, myForm);
    	
    	/*if(!$scope.isValidationFailed) {
	    	$scope.manualEffortBeforeBotHr = $scope.manualEffortBeforeBotHr==undefined?0:$scope.manualEffortBeforeBotHr;
	        $scope.manualEffortBeforeBotMin = $scope.manualEffortBeforeBotMin==undefined?0:$scope.manualEffortBeforeBotMin;
	        $scope.manualEffortAfterBotHr = $scope.manualEffortAfterBotHr==undefined?0:$scope.manualEffortAfterBotHr;
	        $scope.manualEffortAfterBotMin = $scope.manualEffortAfterBotMin==undefined?0:$scope.manualEffortAfterBotMin;
	    	
		    self.bot.manualEffortBeforeBot = timeToDecimal($scope.manualEffortBeforeBotHr+":"+$scope.manualEffortBeforeBotMin);
		    self.bot.manualEffortAfterBot = timeToDecimal($scope.manualEffortAfterBotHr+":"+$scope.manualEffortAfterBotMin);
		    //console.log('self.bot  ::  ', self.bot);
		    addBot(self.bot);
	        //reset();
	        //$window.location.href = '/ermapp/showAllBots';
    }*/
    }
    
    
    function checkBotName (botName, myForm) {
    	//console.log('botName  ::  '+botName);
    	var isIdentityAvailable;
    	BotService.checkBotName(botName)
	   	 .then(
            function(d) {
            	//console.log('d  ::  ',d);
            	$scope.isbotNameAvailable = d;
            	if($scope.isbotNameAvailable) {
            		$scope.isValidationFailed = true;
            		myForm.botName.$error.validationError = true;
            		//console.log('invalid onwer name');
            	} else {
            		myForm.botName.$error.validationError = false;
            		$scope.isValidationFailed = false;
            		$scope.manualEffortBeforeBotHr = $scope.manualEffortBeforeBotHr==undefined?0:$scope.manualEffortBeforeBotHr;
        	        $scope.manualEffortBeforeBotMin = $scope.manualEffortBeforeBotMin==undefined?0:$scope.manualEffortBeforeBotMin;
        	        $scope.manualEffortAfterBotHr = $scope.manualEffortAfterBotHr==undefined?0:$scope.manualEffortAfterBotHr;
        	        $scope.manualEffortAfterBotMin = $scope.manualEffortAfterBotMin==undefined?0:$scope.manualEffortAfterBotMin;
        	    	
        		    self.bot.manualEffortBeforeBot = filterTimeFormat($scope.manualEffortBeforeBotHr+':'+$scope.manualEffortBeforeBotMin);
        		    self.bot.manualEffortAfterBot = filterTimeFormat($scope.manualEffortAfterBotHr+':'+$scope.manualEffortAfterBotMin);
        		    console.log('self.bot  ::  ', self.bot);
        		    addBot(self.bot);
        	        reset();
        	        //$window.location.href = '/ermapp/showAllBots';
            	}
            },
           function(errResponse){
               console.error('Error while checkIdentity');
           }
	   	 );
    	
    	return $scope.isValidationFailed;
    	
    };
    
    function update(myForm) {
    	if($scope.bot.automationSolution != null && $scope.bot.automationSolution != 'Others') {
    		$scope.bot.automationSolutionActual = $scope.bot.automationSolution; 
    	}
   	  	updateBot($scope.bot);
   	  	reset();
        $window.location.href = '/ermapp/showAllBots';
    }
    
    function deleteBot(botId){
        //console.log('inside deleteBot botId  ::  ', botId);

        BotService.deleteBot(botId)
	   	 .then(
   	            function(d) {
   	            	//console.log("bot deleted  ::  ");
   	            	//reset();
   	            	$window.location.href = 'showAllBots';
   	            },

	           function(errResponse){
	               console.error('Error while deleting bot');
	           }
	   	 );
        

    }

    function createNewBot() {
    	
    	 //console.log('inside createNewBot');
    	 $window.location.href = 'addBot';

    }

     function reset() {
    	 
    	$scope.bot = {botId:null, competency:'', botName:'', automationSolution:'', automationSolutionActual:'', projectId:'', projectName:'', customerName:'', botCategory:'', botCreatedBy:'', useCaseOrPurpose:'', manualEffortBeforeBot:'', manualEffortAfterBot:'', supportOrEngineering:'', compManager:'', accountBaseId:'', securityDomain:'', serviceCategory:'', service:'', functionType:'', operationalMetrics:''}
        $window.location.href = '/ermapp/showAllBots';
        
    }
	
	 function getUserRole()
   {
   	UserDetailsService.getUserRole()
   	.then( function(d) {
   		////console.log('user details  ::  ',d);
   		self.bot.botCreatedBy = d.username;
   		$scope.bot.botCreatedBy = d.username;
   		$scope.userRole = d.role;
   		
   		$scope.userRolesList = d.userRolesList;
		
		if($scope.userRolesList != null) {
			angular.forEach($scope.userRolesList, function(value, key) {
				$scope.roleList.push(value.roleName);
			});
		}
   		
   		//console.log('userRole is',$scope.userRole);
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
	 

}]);
