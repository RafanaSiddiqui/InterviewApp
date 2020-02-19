'use strict';

angular.module('myApp').controller('TrackerController', ['$scope', '$window','TrackerService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', 'AccountBaseService', '$filter', function($scope, $window, TrackerService, $uibModal, $timeout, UserDetailsService, MasterDemandService, AccountBaseService, $filter) {
    var self = this;


    self.tracker = {trackerId:null, accountName:'', compManager:'', accountBaseId:'', projectId:'', projectName:'', createdDate:'', automationDetails:[{accountId:null, automationCategeory:'', automationItems:'', possibility:'', phase:'', status:'', openPanel:true, comments:'', automationTarget:[], automationActual:[]}]}
    self.automationDetails = [{accountId:null, automationCategeory:'', automationItems:'', possibility:'', phase:'', status:'', openPanel:true, comments:'', automationTarget:[], automationActual:[]}];
    self.trackerList = [];

    loadAllTracker();
    getUserRole();
    loadAllConfigurations();
    loadAllAccountBase();
    
    //loadAllTemplate();

    self.submit = submit;
    self.update = update;
    //self.remove = remove;
    self.reset = reset;
    self.createNewTracker = createNewTracker;
    self.editTracker = editTracker;
    self.deleteTracker = deleteTracker;
    self.openTracker = openTracker;
    
    $scope.roleList = [];
    $scope.accountBase = {};
    $scope.accountBase.selected = {};

    $scope.sort = function(keyname) {
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	  
      $scope.fnDownLoad = function () {
     	   console.log("inside fnDownLoad");
     	   var s = "downloadExcelFile/";
           $window.open(s);
        };

    function loadAllTracker() {
    	 console.log('inside loadAllBots');
    	 TrackerService.loadAllTracker()
    	 .then(
	            function(d) {
	            	$scope.pageableTracker = d;
	            	angular.forEach($scope.pageableTracker, function(value, key){
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
	                console.log('$scope.pageableTracker  ::  ',$scope.pageableTracker);
	            },

            function(errResponse){
                console.error('Error while fetching pageableTrackers');
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
	            		} else if(value.category == "Automation Category") {
	            			$scope.categoryList = value.configurationList;
	            		} else if(value.category == "Automation Items") {
	            			$scope.itemList = value.configurationList;
	            		} else if(value.category == "Phase") {
	            			$scope.phaseList = value.configurationList;
	            		} else if(value.category == "TrackerStatus") {
	            			$scope.statusList = value.configurationList;
	            		} else if(value.category == "Year") {
	            			$scope.yearList = value.configurationList;
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
	 AccountBaseService.loadAllAccountBase()
	 .then(
            function(d) {
            	$scope.accountBaseList = d;
                console.log('$scope.accountBaseList  ::  ',$scope.accountBaseList);
            },

        function(errResponse){
            console.error('Error while fetching accountBaseList');
        }
	 );

};
 
 $scope.onSelectCustName = function(accountBase) {
	 console.log("accountBase  ::  ",accountBase);
	 //self.tracker.compManager = $filter('filter')($scope.customerDetails, {name: custName})[0].value;
	 self.tracker.accountName = accountBase.accountName;
	 self.tracker.accountBaseId = accountBase.accountBaseId;
	 self.tracker.compManager = accountBase.competencyManager;
 }
    function loadAllPageableTrackers(pageNumber, pageSize) {
   	 console.log('inside loadAllPageableTrackers pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	TrackerService.loadAllPageableTrackers(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	console.log('reponse  ::  ',d);
	            	self.pageablTracker = d.content;
	            	$scope.pageableTracker = d;
	            },

           function(errResponse){
               console.error('Error while fetching loadAllPageableTracker');
           }
   	 );

   };
   
    function openTracker (trackerId) {
    	console.log("inside openBot");
    	loadTrackerById(trackerId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewTracker.jsp',
               size: 'lg',
               controllerAs: 'TrackerController',
               scope: $scope

             });
    };
    
    function editTracker (trackerId) {
    	console.log("inside editTracker  ::  "+trackerId);
    	$window.location.href = 'editTracker/'+trackerId;
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.editTrackerById = function(trackerId) {
    	console.log('trackerId  ::  '+trackerId);
    	loadTrackerById(trackerId);
    };
    
    function loadTrackerById(trackerId) {
   	 console.log('loadTrackerById  ::  ',trackerId);
   	TrackerService.loadTrackerById(trackerId)
   	 .then(
   	       function(d) {
   	            	$scope.tracker = d;
   	            	self.tracker = d;
   	            	$scope.accountBase.selected.accountName = d.accountName;
   	            	/*$scope.compManager = $filter('filter')($scope.accountBaseList, {id: d.accountBaseId})[0].competencyManager;
   	            	console.log("$scope.compManager  ::  ",$scope.compManager);*/
   	            	console.log("$scope.tracker  ::  ",$scope.tracker);
   	            	
   	            	if($scope.tracker.createdDate != null) {
   	            		$scope.tracker.createdDate = new Date($scope.tracker.createdDate);
   	            	}
   	            	
   	                console.log("$scope.tracker  ::  ",$scope.tracker);
   	        },

           function(errResponse) {
               console.error('Error while fetching loadTrackerById');
           }
   	 );

   }

    function addTracker(tracker) {
    	console.log('inside addTracker tracker  ::  ',tracker);
    	TrackerService.addTracker(tracker)
            .then(
            		tracker,
            function(errResponse){
                console.error('Error while adding Tracker');
            }
        );
    }

    function updateTracker(tracker) {
	    
    	TrackerService.updateTracker(tracker)
            .then(
            		tracker,
            function(errResponse){
                console.error('Error while updating Tracker');
            }
        );
    }

    function submit(myForm) {
		console.log('self.tracker  ::  ', self.tracker);
		self.tracker.automationDetails.map(function(item){
   	      item.openPanel = false;
   	    });
	    addTracker(self.tracker);
	    reset();
        $window.location.href = '/ermapp/showAllTracker';
    }
    
    function update(myForm) {
    	self.tracker.automationDetails.map(function(item){
 	      item.openPanel = false;
 	    });
   	  	updateTracker(self.tracker);
   	  	reset();
        $window.location.href = '/ermapp/showAllTracker';
    }
    
    function deleteTracker(trackerId){
        console.log('inside deleteTracker trackerId  ::  ', trackerId);

        TrackerService.deleteTracker(trackerId)
	   	 .then(
   	            function(d) {
   	            	console.log("Tracker deleted  ::  ");
   	            	reset();
   	            	$window.location.href = '/ermapp/showAllTracker';
   	            },

	           function(errResponse){
	               console.error('Error while deleting Tracker');
	           }
	   	 );
        

    }

    function createNewTracker() {
    	
    	 console.log('inside createNewTracker');
    	 $window.location.href = 'addTracker';

    }

     function reset() {
    	 
    	self.tracker = {trackerId:null, accountName:'', compManager:'', accountBaseId:'', projectId:'', projectName:'', createdDate:'', automationDetails:[{accountId:null, automationCategeory:'', automationItems:'', possibility:'', phase:'', status:'', openPanel:true, comments:'', automationTarget:[], automationActual:[]}]}
        $window.location.href = '/ermapp/showAllTracker';
        
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
     	
     	$scope.addAccountDetails = function() {
     		console.log('inside addAccountDetails');
     		self.tracker.automationDetails.push({accountId:null, automationCategeory:'', automationItems:'', possibility:'', phase:'', status:'', openPanel:true, comments:'', automationTarget:[], automationActual:[]});
     	}
     	
     	$scope.deleteAccountDetails = function() {
     		console.log('inside deleteAccountDetails');
     		var lastItem = self.tracker.automationDetails.length-1;
     		self.tracker.automationDetails.splice(lastItem);
     	}
     	
     	$scope.status = {
 		    isCustomHeaderOpen: false,
 		    isFirstOpen: true,
 		    isFirstDisabled: false
 		};
     	
     	$scope.addTarget = function(index) {
     		//console.log('inside addTarget index  ::  '+index);
     		//console.log("self.tracker.automationDetails[index]  ::  ",self.tracker.automationDetails[index]);
     		self.tracker.automationDetails[index].automationTarget.push({});
     	}
     	
     	$scope.deleteTarget = function(index) {
     		//console.log('inside deleteTarget index  ::  '+index);
     		//console.log("self.tracker.automationDetails[index]  ::  ",self.tracker.automationDetails[index]);
     		var lastItem = self.tracker.automationDetails[index].automationTarget.length-1;
     		self.tracker.automationDetails[index].automationTarget.splice(lastItem);
     	}
     	
     	$scope.addActual = function(index) {
     		//console.log('inside addActual index  ::  '+index);
     		//console.log("self.tracker.automationDetails[index]  ::  ",self.tracker.automationDetails[index]);
     		self.tracker.automationDetails[index].automationActual.push({});
     	}
     	
     	$scope.deleteActual = function(index) {
     		//console.log('inside deleteActual index  ::  '+index);
     		//console.log("self.tracker.automationDetails[index]  ::  ",self.tracker.automationDetails[index]);
     		var lastItem = self.tracker.automationDetails[index].automationActual.length-1;
     		self.tracker.automationDetails[index].automationActual.splice(lastItem);
     	}
     	
     	$scope.openPanel = function(idx) {
     	    console.log("inside openPanel idx  :: "+idx);
     	   self.tracker.automationDetails[idx].openPanel = true; 
     	  };

     	  $scope.openAllPanels = function(){
     		 self.tracker.automationDetails.map(function(item){
     	      item.openPanel = true;
     	    });
     	  };

     	  $scope.closeAllPanels = function(){
     		 self.tracker.automationDetails.map(function(item){
     	      item.openPanel = false;
     	    });
     	  }

     	  $scope.toggleAllPanels = function(){
     		 self.tracker.automationDetails.map(function(item){
     	      item.openPanel = !item.openPanel;
     	    });
     	  }

}]);
