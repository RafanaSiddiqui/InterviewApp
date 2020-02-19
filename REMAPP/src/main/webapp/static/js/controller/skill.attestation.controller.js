'use strict';

angular.module('myApp').controller('SkillAttestationController', ['$scope', '$window','SkillAttestationService', '$uibModal', '$timeout', 'UserDetailsService', 'MasterDemandService', '$filter', function($scope, $window, SkillAttestationService, $uibModal, $timeout, UserDetailsService, MasterDemandService, $filter) {
    var self = this;


    //self.skillAttestation = {skillAttestationId:null, supervisorAssociateMapping:{}, skillDetails:[], createdDate:''}
    self.skillAttestation = {id:null, associateId:'', associateName:'', grade:'', location:'', region:'', supervisorId:'', supervisorName:''};
    self.skillAttestationDetails = {id:null, associateId:'', associateName:'', grade:'', location:'', region:'', supervisorId:'', supervisorName:'', skillDetails:[]};
    self.skillDetails = [{id:null, skill:'', proficiency:'', skillActual:'', associateId:'', selectSkill:''}];
    self.supervisorAssociateMapping = {id:null, associateId:'', associateName:'', grade:'', location:'', region:'', supervisorId:'', supervisorName:''};
    self.skillAttestationList = [];
    self.skillDetailsList = [];

    loadAllSkillAttestation();
    getUserRole();
    loadAllConfigurations();
    
    //loadAllTemplate();

    self.submit = submit;
    self.update = update;
    //self.remove = remove;
    self.reset = reset;
    self.createNewSkillAttestation = createNewSkillAttestation;
    self.editSkillAttestation = editSkillAttestation;
    self.deleteSkillAttestation = deleteSkillAttestation;
    self.openSkillAttestation = openSkillAttestation;
    
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

    function loadAllSkillAttestation() {
    	 console.log('inside loadAllBots');
    	 SkillAttestationService.loadAllSkillAttestation()
    	 .then(
	            function(d) {
	            	$scope.pageableSkillAttestation = d;
	            	angular.forEach($scope.pageableSkillAttestation, function(value, key){
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
	                console.log('$scope.pageableSkillAttestation  ::  ',$scope.pageableSkillAttestation);
	            },

            function(errResponse){
                console.error('Error while fetching pageableSkillAttestations');
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
	            		if(value.category == "AssociateSkills") {
	            			$scope.associateSkillsList = value.configurationList;
	            		} else if(value.category == "Proficiency") {
	            			$scope.proficiencyList = value.configurationList;
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
	 self.skillAttestation.competencyManager = $filter('filter')($scope.customerDetails, {name: custName})[0].value;
 }
    function loadAllPageableSkillAttestations(pageNumber, pageSize) {
   	 console.log('inside loadAllPageableSkillAttestations pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	SkillAttestationService.loadAllPageableSkillAttestations(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	console.log('reponse  ::  ',d);
	            	self.pageablSkillAttestation = d.content;
	            	$scope.pageableSkillAttestation = d;
	            },

           function(errResponse){
               console.error('Error while fetching loadAllPageableSkillAttestation');
           }
   	 );

   };
   
    function openSkillAttestation (skillAttestationId) {
    	console.log("inside openSkillAttestation  ::  ",skillAttestationId);
    	loadSkillAttestationById(skillAttestationId);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/viewSkillAttestation.jsp',
               size: 'lg',
               controllerAs: 'SkillAttestationController',
               scope: $scope
             });
    };
    
    function editSkillAttestation (skillAttestationId, associateId) {
    	console.log("inside editSkillAttestation  ::  "+skillAttestationId);
    	$window.location.href = 'editSkillAttestation/'+skillAttestationId+"/"+associateId;
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.editSkillAttestationById = function(skillAttestationId) {
    	console.log('skillAttestationId  ::  '+skillAttestationId);
    	loadSkillAttestationById(skillAttestationId);
    };
    
    $scope.loadSkillDetails = function(associateId) {
    	console.log("associateId  ::  ",associateId);
    	SkillAttestationService.loadSkillDetails(associateId)
      	 .then(
      	       function(d) {
      	    	   		$scope.skillDetailsList = d;
      	            	self.skillDetailsList = d;
      	            	if(self.skillDetailsList.length != 0) {
      	            		$scope.feededSkill = self.skillDetailsList[0].skill;
	      	          	}
	      	          	if($scope.feededSkill != undefined && $scope.feededSkill != null && $scope.feededSkill != " ") {
	      	          		//console.log("$scope.feededSkill  ::  ",$scope.feededSkill);
	      	          		$scope.feededSkillList = $scope.feededSkill.split(",");
	      	          	} 
	      	          	//console.log('$scope.feededSkillList  ::  ',$scope.feededSkillList);
      	            	console.log("$scope.skillDetailsList  ::  ",$scope.skillDetailsList);
      	        },

              function(errResponse) {
                  console.error('Error while fetching loadSkillAttestationById');
              }
      	 );
    }
    
    function loadSkillAttestationById(skillAttestationId) {
   	 console.log('loadSkillAttestationById  ::  ',skillAttestationId);
   	  SkillAttestationService.loadSkillAttestationById(skillAttestationId)
   	 .then(
   	       function(d) {
   	            	$scope.skillAttestation = d;
   	            	self.skillAttestation = d;
   	            	console.log("$scope.skillAttestation  ::  ",$scope.skillAttestation);
   	            	
   	            	if($scope.skillAttestation.createdDate != null) {
   	            		$scope.skillAttestation.createdDate = new Date($scope.skillAttestation.createdDate);
   	            	}
   	            	
   	                console.log("$scope.skillAttestation  ::  ",$scope.skillAttestation);
   	        },

           function(errResponse) {
               console.error('Error while fetching loadSkillAttestationById');
           }
   	 );

   }
    
   


    
    function addSkillAttestation(skillAttestation) {
    	console.log('inside addSkillAttestation skillAttestation  ::  ',skillAttestation);
    	SkillAttestationService.addSkillAttestation(skillAttestation)
            .then(
            		skillAttestation,
            function(errResponse){
                console.error('Error while adding SkillAttestation');
            }
        );
    }

    function updateSkillAttestation(skillAttestation) {
	    
    	SkillAttestationService.updateSkillAttestation(skillAttestation)
            .then(
            		skillAttestation,
            function(errResponse){
                console.error('Error while updating SkillAttestation');
            }
        );
    }

    function submit(myForm) {
    		
    		console.log('self.SkillAttestation  ::  ', self.SkillAttestation);
    		console.log('self.skillDetailsList  ::  ',skillDetailsList);
    		self.skillDetailsList.skillDetails.map(function(item){
    		  if(item.selectSkill != "Others") {
    			  console.log("inside Others");
    			  item.skillActual = item.selectSkill;
    		  }
       	    });
    		
		    addSkillAttestation(self.skillAttestation);
	        reset();
	        $window.location.href = '/ermapp/showAllSkillAttestation';
    }
    
    function update(myForm) {
    	//console.log("self.skillAttestation  ::  ",self.skillAttestation);
    	self.skillAttestationDetails = self.skillAttestation;
    	self.skillAttestationDetails.skillDetails = self.skillDetailsList;
    	if(self.skillAttestationDetails.skillDetails.length != 0) {
    		$scope.itemSkill = self.skillAttestationDetails.skillDetails[0].skill;
    	}
    	if($scope.itemSkill == undefined) {
    		$scope.itemSkill = "";
    	}
    	console.log("$scope.itemSkill ::  ",$scope.itemSkill);
    	self.skillAttestationDetails.skillDetails.map(function(item){
    	  item.skill = $scope.itemSkill;
    	  item.associateId = self.skillAttestation.associateId;
		  if(item.selectSkill != "Others") {
			  item.skillActual = item.selectSkill;
		  }
	    });
    	console.log("self.skillAttestationDetails  ::  ",self.skillAttestationDetails);
   	  	updateSkillAttestation(self.skillAttestationDetails);
   	  	reset();
        $window.location.href = '/ermapp/showAllSkillAttestation';
    }
    
    function deleteSkillAttestation(skillAttestationId){
        console.log('inside deleteSkillAttestation skillAttestationId  ::  ', skillAttestationId);

        SkillAttestationService.deleteSkillAttestation(skillAttestationId)
	   	 .then(
   	            function(d) {
   	            	console.log("SkillAttestation deleted  ::  ");
   	            	reset();
   	            	$window.location.href = '/ermapp/showAllSkillAttestation';
   	            },

	           function(errResponse){
	               console.error('Error while deleting SkillAttestation');
	           }
	   	 );
        

    }

    function createNewSkillAttestation() {
    	
    	 console.log('inside createNewSkillAttestation');
    	 $window.location.href = 'addSkillAttestation';

    }

     function reset() {
    	 
    	self.skillAttestation = {id:null, accountName:'', competencyManager:'', type:'', primaryLocation:'', totalFTE:'', odcDetails:'', teckStack:'', secondaryLocation:'', createdDate:''}
        $window.location.href = '/ermapp/showAllSkillAttestation';
        
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
     
     $scope.addSkill = function() {
  		console.log('inside addAccountDetails');
  		self.skillDetailsList.push({});
  	 }
     
     $scope.removeSkill = function(skillId) {
    	 console.log('skillId   ::  ',skillId);
    	 
    	 SkillAttestationService.deleteSkill(skillId)
	   	 .then(
   	            function(d) {
   	            	console.log("skill deleted  ::  ");
   	            },

	           function(errResponse){
	               console.error('Error while deleting skill');
	           }
	   	 );
    	 
        var lastItem = self.skillDetailsList.length-1;
        self.skillDetailsList.splice(lastItem);
     };

}]);
