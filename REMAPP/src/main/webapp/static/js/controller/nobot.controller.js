'use strict';

angular.module('myApp').controller('NoBotController', ['$scope', '$window','NoBotService', '$uibModal', '$timeout', 'MasterDemandService', '$filter', 'UserDetailsService', function($scope, $window, NoBotService, $uibModal, $timeout, MasterDemandService, $filter, UserDetailsService) {
    var self = this;

    self.noBot = {noBotId:null, customerName:'', projectName:'', isAvailable:'', comments:'', createdDate:'', status:'', approvedBy:'', decCheck:''}
    $scope.noNot = {noBotId:null, customerName:'', projectName:'', isAvailable:'', comments:'', createdDate:'', status:'', approvedBy:'', decCheck:''}

   
    loadAllNoBot();
    getUserRole();
    loadAllConfigurations();
    
    self.submit = submit;
    self.update = update;
    //self.remove = remove;
    self.reset = reset;
    self.createNewNoBot = createNewNoBot;
    self.editNoBot = editNoBot;
    self.deleteNoBot = deleteNoBot;
    self.openNoBot = openNoBot;
    $scope.botApprover = false;
    $scope.roleList = [];
    
    $scope.sort = function(keyname) {
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	  
    function loadAllNoBot() {
    	 console.log('inside loadAllBots');
    	 NoBotService.loadAllNoBot()
    	 .then(
	            function(d) {
	            	$scope.pageableNoBot = d;
	            	angular.forEach($scope.pageableNoBot, function(value, key){
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
	                console.log('$scope.pageableNoBot  ::  ',$scope.pageableNoBot);
	            },

            function(errResponse){
                console.error('Error while fetching pageableNoBots');
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
	            		}
	            	});
	            },

           function(errResponse){
               console.error('Error while loadAllConfigurations');
           }
   	 );

 };
   
 
 $scope.onSelectCustName = function(custName) {
	 console.log("custName  ::  "+custName);
	 self.noBot.competencyManager = $filter('filter')($scope.customerDetails, {name: custName})[0].value;
 }
    function loadAllPageableNoBots(pageNumber, pageSize) {
   	 console.log('inside loadAllPageableNoBots pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	NoBotService.loadAllPageableNoBots(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	console.log('reponse  ::  ',d);
	            	self.pageablNoBot = d.content;
	            	$scope.pageableNoBot = d;
	            },

           function(errResponse){
               console.error('Error while fetching loadAllPageableNoBot');
           }
   	 );

   };
   
    function openNoBot (noBotId) {
    	console.log("inside openBot");
    	loadNoBotById(noBotId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewNoBotDecForm.jsp',
               size: 'lg',
               controllerAs: 'NoBotController',
               scope: $scope

             });
    };
    
    function editNoBot (noBotId) {
    	console.log("inside editNoBot  ::  "+noBotId);
    	$window.location.href = 'editNoBot/'+noBotId;
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.editNoBotById = function(noBotId) {
    	console.log('noBotId  ::  '+noBotId);
    	loadNoBotById(noBotId);
    };
    
    function loadNoBotById(noBotId) {
   	 console.log('loadNoBotById  ::  ',noBotId);
   	NoBotService.loadNoBotById(noBotId)
   	 .then(
   	       function(d) {
   	            	$scope.noBot = d;
   	            	self.noBot = d;
   	            	console.log("$scope.noBot  ::  ",$scope.noBot);
   	            	
   	            	if($scope.noBot.createdDate != null) {
   	            		$scope.noBot.createdDate = new Date($scope.noBot.createdDate);
   	            	}
   	            	
   	                console.log("$scope.noBot  ::  ",$scope.noBot);
   	        },

           function(errResponse) {
               console.error('Error while fetching loadNoBotById');
           }
   	 );

   }



    
    function addNoBot(noBot) {
    	console.log('inside addNoBot noBot  ::  ',noBot);
    	NoBotService.addNoBot(noBot)
            .then(
            		noBot,
            function(errResponse){
                console.error('Error while adding NoBot');
            }
        );
    }

    function updateNoBot(noBot) {
	    
    	NoBotService.updateNoBot(noBot)
            .then(
            		noBot,
            function(errResponse){
                console.error('Error while updating NoBot');
            }
        );
    }

    function submit(myForm) {
    		
    		console.log('self.NoBot  ::  ', self.noBot);
		    addNoBot(self.noBot);
	        reset();
	        $window.location.href = '/ermapp/showAllNoBots';
    }
    
    function update(myForm) {
    	
   	  	updateNoBot(self.noBot);
   	  	reset();
        $window.location.href = '/ermapp/showAllNoBots';
    }
    
    function deleteNoBot(noBotId){
        console.log('inside deleteNoBot noBotId  ::  ', noBotId);

        NoBotService.deleteNoBot(noBotId)
	   	 .then(
   	            function(d) {
   	            	console.log("NoBot deleted  ::  ");
   	            	reset();
   	            	$window.location.href = '/ermapp/showAllNoBots';
   	            },

	           function(errResponse){
	               console.error('Error while deleting NoBot');
	           }
	   	 );
        

    }

    function createNewNoBot() {
    	
    	 console.log('inside createNewNoBot');
    	 $window.location.href = 'addNoBotDec';

    }


   
    /*function add(nobot) {
    	console.log('inside addBot nobot  ::  ',nobot);
    	Service.add(nobot)
            .then(
            		nobot,
            function(errResponse){
                console.error('Error while adding bot');
            }
        );
    }
    
    function submit(myForm) {
    	add(self.nobot);
    	reset();
    } */
   

     function reset() {
    	 self.nobot = {noBotId:null, customerName:'', projectName:'', isAvailable:'', comments:'', createdDate:'', status:'', approvedBy:'', decCheck:''}
         $window.location.href = '/ermapp/showAllNoBots';   
    }
	
     function getUserRole()
	 {
     UserDetailsService.getUserRole()
   	 .then( function(d) {
   		$scope.userRole = d.role;
   		$scope.userRolesList = d.userRolesList;
		
		if($scope.userRolesList != null) {
			angular.forEach($scope.userRolesList, function(value, key) {
				$scope.roleList.push(value.roleName);
			});
		}
   		/*if(d.role == "ROLE_ADMIN" && d.approverType == "NOBOT") {
   			$scope.botApprover = true;
   		} else {
   			$scope.botApprover = false;
   		}*/
   		console.log('$scope.botApprover  ::  ',$scope.botApprover);
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
     
     $scope.showNoBotByStatus = function() {
    	 console.log('inside loadAllNoBotByStatus');
    	 NoBotService.loadAllNoBotByStatus()
    	 .then(
	            function(d) {
	            	$scope.pageableNoBot = d;
	            	angular.forEach($scope.pageableNoBot, function(value, key){
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
	                console.log('$scope.pageableNoBot  ::  ',$scope.pageableNoBot);
	            },

            function(errResponse){
                console.error('Error while fetching pageableNoBots');
            }
    	 );
     }

}]);
