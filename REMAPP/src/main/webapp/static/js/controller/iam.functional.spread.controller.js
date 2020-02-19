'use strict';

angular.module('myApp').controller('IAMFunctionalSpreadController', ['$scope', '$window','IAMFunctionalSpreadService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', 'AccountBaseService', '$filter', function($scope, $window, IAMFunctionalSpreadService, $uibModal, $timeout, UserDetailsService, MasterDemandService, AccountBaseService, $filter) {
    var self = this;

    self.iamFunctionalSpread = {id:null, accountID:'', customerName:'', vertical:'', createdDate:'', servicesDetails:[{id:null, securityDomainCode:'', securityDomain:'', serviceCategoryCode:'', serviceCategory:'', offeringCode:'', service:'', teckStackTierRefCode:'', useCasePresent:'', supportFTE:'', engineeringFTE:'', automationPossible:'', createdDate:'', openPanel:true}]}
    self.servicesDetails = [{id:null, securityDomainCode:'', securityDomain:'', serviceCategoryCode:'', serviceCategory:'', offeringCode:'', service:'', teckStackTierRefCode:'', useCasePresent:'', supportFTE:'', engineeringFTE:'', automationPossible:'', createdDate:'', openPanel:true}];
    self.iamFunctionalSpreadList = [];

    loadAllIAMFunctionalSpread();
    getUserRole();
    loadAllConfigurations();
    loadAllAccountBase();
    
    self.submit = submit;
    self.update = update;
    self.reset = reset;
    self.createNewIAMFunctionalSpread = createNewIAMFunctionalSpread;
    self.editIAMFunctionalSpread = editIAMFunctionalSpread;
    self.deleteIAMFunctionalSpread = deleteIAMFunctionalSpread;
    self.openIAMFunctionalSpread = openIAMFunctionalSpread;
    
    $scope.roleList = [];

    $scope.accountBase = {};
    $scope.accountBase.selected = {};
    
    $scope.custName = {};
    $scope.custName.selected = {};
    
    $scope.securityDomain = {};
    $scope.securityDomain.selected = {};
    
    $scope.serviceCat = {};
    $scope.serviceCat.selected = {};
    
    $scope.serviceDetails = {};
    $scope.serviceDetails.selected = {};
    
    $scope.sort = function(keyname) {
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	  

    function loadAllIAMFunctionalSpread() {
    	 console.log('inside loadAllIAMFunctionalSpread');
    	 IAMFunctionalSpreadService.loadAllIAMFunctionalSpread()
    	 .then(
	            function(d) {
	            	$scope.pageableIAMFunctionalSpread = d;
	            	angular.forEach($scope.pageableIAMFunctionalSpread, function(value, key){
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
	                console.log('$scope.pageableIAMFunctionalSpread  ::  ',$scope.pageableIAMFunctionalSpread);
	            },

            function(errResponse){
                console.error('Error while fetching pageableIAMFunctionalSpreads');
            }
    	 );

    };
    
    function loadAllConfigurations() {
   	 console.log('inside loadAllConfigurations');
   	 MasterDemandService.loadAllConfigurations()
   	 .then(
	            function(d) {
	            	$scope.allConfList = d;
	            	angular.forEach($scope.allConfList, function(value, key){
	            		console.log('value.category  ::  '+value.category);
	            		
	            		if(value.category == "SecurityDomain") {
	            			$scope.securityDomains = value.configurationList;
	            		} else if(value.category == "ServiceCategory") {
	            			$scope.serviceCategory = value.configurationList;
	            		} else if(value.category == "Service") {
	            			$scope.service = value.configurationList;
	            		}
	            	});
	            },

           function(errResponse){
               console.error('Error while loadAllConfigurations');
           }
   	 );

 };
 
 
 function loadAllAccountBase() {
	 console.log(' Testt   ::::::: inside loadAllAccountBase');
	 AccountBaseService.loadAllAccountBase()
	 .then(
            function(d) {
            	$scope.accountBaseList = d;
            },

        function(errResponse){
            console.error('Error while fetching accountBaseList');
        }
	 );
};
   
 
$scope.onSelectVertical = function(accountBase) {
	self.iamFunctionalSpread.vertical = accountBase.vertical;
	$scope.customerList = $filter('filter')($scope.accountBaseList, { vertical: self.iamFunctionalSpread.vertical });
	$scope.custName = {};
    $scope.custName.selected = {};
 }

 $scope.onSelectCustName = function(custName) {
	 self.iamFunctionalSpread.customerName = custName.accountName;
	 self.iamFunctionalSpread.accountID = custName.customerId;
 }
 
 $scope.onSelectSecurityDomain = function(securityDomain, index) {
		
		self.servicesDetails[index].securityDomainCode = securityDomain.value;
		self.servicesDetails[index].securityDomain = securityDomain.name;
		if(self.servicesDetails[index].offeringCode!=''){
			if(self.servicesDetails[index].securityDomainCode=='1.0'){
				self.servicesDetails[index].teckStackTierRefCode = "IAM tier 1-"+self.servicesDetails[index].offeringCode;
			}else if(self.servicesDetails[index].securityDomainCode=='2.0'){
				self.servicesDetails[index].teckStackTierRefCode = "IT GRC tier 1-"+self.servicesDetails[index].offeringCode;
			}else if(self.servicesDetails[index].securityDomainCode=='3.0'){
				self.servicesDetails[index].teckStackTierRefCode = "Cyber Security tier 1-"+self.servicesDetails[index].offeringCode;
			}
			self.iamFunctionalSpread.servicesDetails[index].teckStackTierRefCode = self.servicesDetails[index].teckStackTierRefCode;
		}
 }
 
 $scope.onSelectServiceCategory = function(serviceCat, index) {
		self.servicesDetails[index].serviceCategoryCode = serviceCat.value;
		self.servicesDetails[index].serviceCategory = serviceCat.name;
}
 
 $scope.onSelectService = function(serviceDetails, index) {
		self.servicesDetails[index].offeringCode = serviceDetails.value;
		self.servicesDetails[index].service = serviceDetails.name;
		if(self.servicesDetails[index].securityDomainCode!=''){
			//self.teckStackTierRefCode = "IAM tier "+self.securityDomainCode.substring(0,self.securityDomainCode.indexOf("."))+"-"+serviceDetails.value;
			if(self.servicesDetails[index].securityDomainCode=='1.0'){
				self.servicesDetails[index].teckStackTierRefCode = "IAM tier 1-"+serviceDetails.value;
			}else if(self.servicesDetails[index].securityDomainCode=='2.0'){
				self.servicesDetails[index].teckStackTierRefCode = "IT GRC tier 1-"+serviceDetails.value;
			}else if(self.servicesDetails[index].securityDomainCode=='3.0'){
				self.servicesDetails[index].teckStackTierRefCode = "Cyber Security tier 1-"+serviceDetails.value;
			}
			self.iamFunctionalSpread.servicesDetails[index].teckStackTierRefCode = self.servicesDetails[index].teckStackTierRefCode;
		}
}
 
    function loadAllPageableIAMFunctionalSpreads(pageNumber, pageSize) {
   	 console.log('inside loadAllPageableIAMFunctionalSpreads pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	IAMFunctionalSpreadService.loadAllPageableIAMFunctionalSpreads(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	console.log('reponse  ::  ',d);
	            	self.pageablIAMFunctionalSpread = d.content;
	            	$scope.pageableIAMFunctionalSpread = d;
	            },

           function(errResponse){
               console.error('Error while fetching loadAllPageableIAMFunctionalSpread');
           }
   	 );

   };
   
    function openIAMFunctionalSpread (iamFunctionalSpreadId) {
    	console.log("inside openIAMFunctionalSpread");
    	loadIAMFunctionalSpreadById(iamFunctionalSpreadId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewIAMFunctionalSpread.jsp',
               size: 'lg',
               controllerAs: 'IAMFunctionalSpreadController',
               scope: $scope

             });
    };
    
    function editIAMFunctionalSpread (iamFunctionalSpreadId) {
    	console.log("inside editIAMFunctionalSpread  ::  "+iamFunctionalSpreadId);
    	$window.location.href = 'editIAMFunctionalSpread/'+iamFunctionalSpreadId;
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.editIAMFunctionalSpreadById = function(id) {
    	loadIAMFunctionalSpreadById(id);
    };
    
    function loadIAMFunctionalSpreadById(iamFunctionalSpreadId) {
   	 console.log('loadIAMFunctionalSpreadById  ::  ',iamFunctionalSpreadId);
   	IAMFunctionalSpreadService.loadIAMFunctionalSpreadById(iamFunctionalSpreadId)
   	 .then(
   	       function(d) {
   	            	$scope.iamFunctionalSpread = d;
   	            	self.iamFunctionalSpread = d;
   	            	console.log("$scope.iamFunctionalSpread  ::  ",$scope.iamFunctionalSpread);
   	            	$scope.accountBase.selected.vertical = d.vertical;
   	            	$scope.custName.selected.accountName = d.customerName;
   	            	$scope.securityDomain.selected.name = d.securityDomain;
   	            	$scope.serviceCat.selected.name = d.serviceCategory;
   	            	$scope.serviceDetails.selected.name = d.service;
   	            	
   	            	self.iamFunctionalSpread.servicesDetails = $scope.iamFunctionalSpread.servicesDetails;
   	            	self.servicesDetails = $scope.iamFunctionalSpread.servicesDetails;
   	            	
   	            	self.iamFunctionalSpread.vertical = d.vertical;
   	            	$scope.customerList = $filter('filter')($scope.accountBaseList, { vertical: self.iamFunctionalSpread.vertical });
   	            	
   	            	if($scope.iamFunctionalSpread.createdDate != null) {
   	            		$scope.iamFunctionalSpread.createdDate = new Date($scope.iamFunctionalSpread.createdDate);
   	            	}
   	            	
	   	        	 self.iamFunctionalSpread.servicesDetails.map(function(item){
	   	     			 item.openPanel = false;
	   	     	    });
   	            	
   	        },

           function(errResponse) {
               console.error('Error while fetching loadIAMFunctionalSpreadById');
           }
   	 );

   }



    
    function addIAMFunctionalSpread(iamFunctionalSpread) {
    	console.log('inside addIAMFunctionalSpread iamFunctionalSpread  ::  ',iamFunctionalSpread);
    	IAMFunctionalSpreadService.addIAMFunctionalSpread(iamFunctionalSpread)
            .then(
            		iamFunctionalSpread,
            function(errResponse){
                console.error('Error while adding IAMFunctionalSpread');
            }
        );
    }

    function updateIAMFunctionalSpread(iamFunctionalSpread) {
    	IAMFunctionalSpreadService.updateIAMFunctionalSpread(iamFunctionalSpread)
            .then(
            		iamFunctionalSpread,
            function(errResponse){
                console.error('Error while updating IAMFunctionalSpread');
            }
        );
    }

    function submit(myForm) {    		
    		console.log('self.IAMFunctionalSpread  ::  ', self.iamFunctionalSpread);
    		console.log('self.servicesDetails  ::  ', self.servicesDetails);
		    addIAMFunctionalSpread(self.iamFunctionalSpread);
	        reset();
	        $window.location.href = '/ermapp/showAllIAMFunctionalSpread';
    }
    
    function update(myForm) {    	
   	  	updateIAMFunctionalSpread(self.iamFunctionalSpread);
   	  	reset();
        $window.location.href = '/ermapp/showAllIAMFunctionalSpread';
    }
    
    function deleteIAMFunctionalSpread(iamFunctionalSpreadId){
        console.log('inside deleteIAMFunctionalSpread iamFunctionalSpreadId  ::  ', iamFunctionalSpreadId);

        IAMFunctionalSpreadService.deleteIAMFunctionalSpread(iamFunctionalSpreadId)
	   	 .then(
   	            function(d) {
   	            	console.log("IAMFunctionalSpread deleted  ::  ");
   	            	reset();
   	            	$window.location.href = '/ermapp/showAllIAMFunctionalSpread';
   	            },

	           function(errResponse){
	               console.error('Error while deleting IAMFunctionalSpread');
	           }
	   	 );
        

    }

    function createNewIAMFunctionalSpread() {
    	 console.log('inside createNewIAMFunctionalSpread');
    	 $window.location.href = 'addIAMFunctionalSpread';

    }

     function reset() {    	 
    	self.iamFunctionalSpread = {id:null, accountID:'', customerName:'', vertical:'', createdDate:'', servicesDetails:[{id:null, securityDomainCode:'', securityDomain:'', serviceCategoryCode:'', serviceCategory:'', offeringCode:'', service:'', teckStackTierRefCode:'', useCasePresent:'', supportFTE:'', engineeringFTE:'', automationPossible:'', createdDate:'', openPanel:true}]}
        $window.location.href = '/ermapp/showAllIAMFunctionalSpread';
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
     
     
     $scope.addServiceDetails = function() {
  		console.log('inside addServiceDetails');
  		self.iamFunctionalSpread.servicesDetails.push({id:null, securityDomainCode:'', securityDomain:'', serviceCategoryCode:'', serviceCategory:'', offeringCode:'', service:'', teckStackTierRefCode:'', useCasePresent:'', supportFTE:'', engineeringFTE:'', automationPossible:'', createdDate:'', openPanel:true});
  		self.servicesDetails = self.iamFunctionalSpread.servicesDetails;
  	}
     
     $scope.openAllPanels = function(){
 		 self.iamFunctionalSpread.servicesDetails.map(function(item){
 	     item.openPanel = true;
 	    });
 	  };

 	  $scope.closeAllPanels = function(){
 		 self.iamFunctionalSpread.servicesDetails.map(function(item){
 	     item.openPanel = false;
 	    });
 	  }
 	  
 	 $scope.deleteServiceDetails = function() {
  		console.log('inside deleteAccountDetails');
  		var lastItem = self.iamFunctionalSpread.servicesDetails.length-1;
  		self.iamFunctionalSpread.servicesDetails.splice(lastItem);
  	}

}]);
