'use strict';

angular.module('myApp').controller('AccountBaseController', ['$scope', '$window','AccountBaseService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', '$filter', function($scope, $window, AccountBaseService, $uibModal, $timeout, UserDetailsService, MasterDemandService, $filter) {
    var self = this;


    self.accountBase = {id:null, customerId:'', vertical:'', accountName:'', bfd:'', btb:'', btm:'', nbl:'', competencyManagerId:'', competencyManager:'', primaryLocation:'', odcDetails:'', teckStack:'', createdDate:''}
    self.accountBaseList = [];

    loadAllAccountBase();
    getUserRole();
    loadAllConfigurations();
    
    //loadAllTemplate();

    self.submit = submit;
    self.update = update;
    //self.remove = remove;
    self.reset = reset;
    self.createNewAccountBase = createNewAccountBase;
    self.editAccountBase = editAccountBase;
    self.deleteAccountBase = deleteAccountBase;
    self.openAccountBase = openAccountBase;
    
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

    function loadAllAccountBase() {
    	 console.log('inside loadAllAccountBase');
    	 AccountBaseService.loadAllAccountBase()
    	 .then(
	            function(d) {
	            	$scope.pageableAccountBase = d;
	            	angular.forEach($scope.pageableAccountBase, function(value, key){
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
	                console.log('$scope.pageableAccountBase  ::  ',$scope.pageableAccountBase);
	            },

            function(errResponse){
                console.error('Error while fetching pageableAccountBases');
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
	            		} else if(value.category == "Type") {
	            			$scope.typeList = value.configurationList;
	            		} else if(value.category == "City") {
	            			$scope.cityList = value.configurationList;
	            		}
	            		//console.log('value.configurationList  ::  ',value.configurationList);
	            	});
	            },

           function(errResponse){
               console.error('Error while loadAllConfigurations');
           }
   	 );

 };
   
 
 $scope.onSelectCustName = function(custName) {
	 console.log("custName  ::  "+custName);
	 self.accountBase.competencyManager = $filter('filter')($scope.customerDetails, {name: custName})[0].value;
 }
    function loadAllPageableAccountBases(pageNumber, pageSize) {
   	 console.log('inside loadAllPageableAccountBases pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	AccountBaseService.loadAllPageableAccountBases(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	console.log('reponse  ::  ',d);
	            	self.pageablAccountBase = d.content;
	            	$scope.pageableAccountBase = d;
	            },

           function(errResponse){
               console.error('Error while fetching loadAllPageableAccountBase');
           }
   	 );

   };
   
    function openAccountBase (accountBaseId) {
    	console.log("inside openBot");
    	loadAccountBaseById(accountBaseId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewAccountBase.jsp',
               size: 'lg',
               controllerAs: 'AccountBaseController',
               scope: $scope

             });
    };
    
    function editAccountBase (accountBaseId) {
    	console.log("inside editAccountBase  ::  "+accountBaseId);
    	$window.location.href = 'editAccountBase/'+accountBaseId;
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.editAccountBaseById = function(accountBaseId) {
    	console.log('accountBaseId  ::  '+accountBaseId);
    	loadAccountBaseById(accountBaseId);
    };
    
    function loadAccountBaseById(accountBaseId) {
   	 console.log('loadAccountBaseById  ::  ',accountBaseId);
   	AccountBaseService.loadAccountBaseById(accountBaseId)
   	 .then(
   	       function(d) {
   	            	$scope.accountBase = d;
   	            	self.accountBase = d;
   	            	console.log("$scope.accountBase  ::  ",$scope.accountBase);
   	            	
   	            	if($scope.accountBase.createdDate != null) {
   	            		$scope.accountBase.createdDate = new Date($scope.accountBase.createdDate);
   	            	}
   	            	
   	                console.log("$scope.accountBase  ::  ",$scope.accountBase);
   	        },

           function(errResponse) {
               console.error('Error while fetching loadAccountBaseById');
           }
   	 );

   }



    
    function addAccountBase(accountBase) {
    	console.log('inside addAccountBase accountBase  ::  ',accountBase);
    	AccountBaseService.addAccountBase(accountBase)
            .then(
            		accountBase,
            function(errResponse){
                console.error('Error while adding AccountBase');
            }
        );
    }

    function updateAccountBase(accountBase) {
	    
    	AccountBaseService.updateAccountBase(accountBase)
            .then(
            		accountBase,
            function(errResponse){
                console.error('Error while updating AccountBase');
            }
        );
    }

    function submit(myForm) {
    		
    		console.log('self.AccountBase  ::  ', self.AccountBase);
		    addAccountBase(self.accountBase);
	        reset();
	        $window.location.href = '/ermapp/showAllAccountBase';
    }
    
    function update(myForm) {
    	
   	  	updateAccountBase(self.accountBase);
   	  	reset();
        $window.location.href = '/ermapp/showAllAccountBase';
    }
    
    function deleteAccountBase(accountBaseId){
        console.log('inside deleteAccountBase accountBaseId  ::  ', accountBaseId);

        AccountBaseService.deleteAccountBase(accountBaseId)
	   	 .then(
   	            function(d) {
   	            	console.log("AccountBase deleted  ::  ");
   	            	reset();
   	            	$window.location.href = '/ermapp/showAllAccountBase';
   	            },

	           function(errResponse){
	               console.error('Error while deleting AccountBase');
	           }
	   	 );
        

    }

    function createNewAccountBase() {
    	
    	 console.log('inside createNewAccountBase');
    	 $window.location.href = 'addAccountBase';

    }

     function reset() {
    	 
    	self.accountBase = {id:null, customerId:'', vertical:'', accountName:'', bfd:'', btb:'', btm:'', nbl:'', competencyManagerId:'', competencyManager:'', primaryLocation:'', odcDetails:'', teckStack:'', createdDate:''}
        $window.location.href = '/ermapp/showAllAccountBase';
        
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

}]);
