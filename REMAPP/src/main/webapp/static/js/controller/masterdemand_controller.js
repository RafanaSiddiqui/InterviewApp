'use strict';

angular.module('myApp').controller('MasterDemandController', ['$scope', '$window','MasterDemandService', '$uibModal', '$timeout', 'UserDetailsService', function($scope, $window, MasterDemandService, $uibModal, $timeout, UserDetailsService) {
    var self = this;


    self.demandRequest = {demandRequestID:null, soId:'', billability:'', reqStartDate:'', priority:'', grade:'', pdp:'', vertical:'', accountName:'', offShore:'', city:'', country:'', projectID:'', projectName:'', creationDate:'', flaggedForHire:false, flaggedForHireDate:'', technicalSkills:'', requestorName:'', requestorID:'', competency:'', status:''}
    self.demandRequestList = [];

    loadAllDemandRequests();
    //loadAllDemandPageableRequests(1, 10);
    /*loadAllCities();
    loadAllCountries();
    loadAllPriorities();
    loadAllGrades();
    loadAllTechnicalSkills();
    loadAllCompetency();*/
    
    loadAllConfigurations();
    
    getUserRole();
    //loadAllTemplate();

    self.submit = submit;
    self.update = update;
    //self.remove = remove;
    self.reset = reset;
    self.createNewMasterDemand = createNewMasterDemand;
    self.editDemandRequest = editDemandRequest;
    self.removeDemandRequest = removeDemandRequest;
    self.openDemandRequest = openDemandRequest;
    
    $scope.statusList = ["Pending", "Reviewed", "Completed"];
    


	  
	  $scope.sort = function(keyname) {
	        $scope.sortKey = keyname;   //set the sortKey to the param passed
	        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	  }
	  
      $scope.fnDownLoad = function () {
     	   //console.log("inside fnDownLoad");
     	   var s = "downloadExcelFile/";
           $window.open(s);
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
		            		if(value.category == "City") {
		            			$scope.cities = value.configurationList;
		            		} else if(value.category == "Country") {
		            			$scope.countries = value.configurationList;
		            		} else if(value.category == "Priority") {
		            			$scope.priorities = value.configurationList;
		            		} else if(value.category == "Grade") {
		            			$scope.grades = value.configurationList;
		            		} else if(value.category == "Technical Skills") {
		            			$scope.technicalSkills = value.configurationList;
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
        
	  function loadAllCities() {
	    	 //console.log('inside loadAllCities');
	    	 MasterDemandService.loadAllConfigurationObjs("City")
	    	 .then(
		            function(d) {
		            	$scope.cities = d;
		            	//console.log('$scope.cities  ::  ',$scope.cities);
		            },

	            function(errResponse){
	                console.error('Error while loadAllCities');
	            }
	    	 );

	  };
	  
		  function loadAllCountries() {
		    	 //console.log('inside loadAllCities');
		    	 MasterDemandService.loadAllConfigurationObjs("Country")
		    	 .then(
			            function(d) {
			            	$scope.countries = d;
			            	//console.log('$scope.countries  ::  ',$scope.countries);
			            },

		            function(errResponse){
		                console.error('Error while loadAllCountries');
		            }
		    	 );

		    };
		    
		    function loadAllPriorities() {
		    	 //console.log('inside loadAllPriorities');
		    	 MasterDemandService.loadAllConfigurationObjs("Priority")
		    	 .then(
			            function(d) {
			            	$scope.priorities = d;
			            	//console.log('$scope.priorities  ::  ',$scope.priorities);
			            },

		            function(errResponse){
		                console.error('Error while loadAllPriorities');
		            }
		    	 );

		    };
		    
		    function loadAllGrades() {
		    	 //console.log('inside loadAllGrades');
		    	 MasterDemandService.loadAllConfigurationObjs("Grade")
		    	 .then(
			            function(d) {
			            	$scope.grades = d;
			            	//console.log('$scope.grades  ::  ',$scope.grades);
			            },

		            function(errResponse){
		                console.error('Error while loadAllGrades');
		            }
		    	 );

		    };
		    
		    function loadAllTechnicalSkills() {
		    	 //console.log('inside loadAllTechnicalSkills');
		    	 MasterDemandService.loadAllConfigurationObjs("Technical Skills")
		    	 .then(
			            function(d) {
			            	$scope.technicalSkills = d;
			            	//console.log('$scope.technicalSkills  ::  ',$scope.technicalSkills);
			            },

		            function(errResponse){
		                console.error('Error while loadAllTechnicalSkills');
		            }
		    	 );

		    };
		    
		    function loadAllCompetency() {
		    	 //console.log('inside loadAllCompetency');
		    	 MasterDemandService.loadAllConfigurationObjs("Competency")
		    	 .then(
			            function(d) {
			            	$scope.competencies = d;
			            	//console.log('$scope.competencies  ::  ',$scope.competencies);
			            },

		            function(errResponse){
		                console.error('Error while loadAllCompetency');
		            }
		    	 );

		    };
		    
    function loadAllDemandRequests() {
    	 //console.log('inside loadAllDemandRequests');
    	 MasterDemandService.loadAllDemandRequests()
    	 .then(
	            function(d) {
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
	                //console.log('self.demandRequestList  ::  ',self.demandRequestList);
	            },

            function(errResponse){
                console.error('Error while fetching Requests');
            }
    	 );

    };
    
    $scope.flagSelect = function(flagSelect, soId) {
    	//console.log("flagSelect  ::  ",flagSelect);
    	//console.log("soId  ::  ",soId);
    	
    	MasterDemandService.flagSelectSendMail(flagSelect, soId)
      	 .then(
   	            function(d) {
   	            	//console.log('reponse  ::  ',d);
   	            },

              function(errResponse){
                  console.error('Error while fetching Requests');
              }
      	 );
    	
    }
    
    function loadAllDemandPageableRequests(pageNumber, pageSize) {
   	 //console.log('inside loadAllDemandPageableRequests pageNumber  ::  '+pageNumber+'  ::  pageSize  ::  '+pageSize);
   	 MasterDemandService.loadAllDemandPageableRequests(pageNumber, pageSize)
   	 .then(
	            function(d) {
	            	//console.log('reponse  ::  ',d);
	            	self.demandRequestList = d.content;
	            	$scope.pageableDemandRequest = d;
	            	
	            	angular.forEach($scope.pageableDemandRequest.content, function(value, key){
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

   };
   
   
   /*function loadAllMasterRequests() {
	   	 //console.log('inside loadAllMasterRequests');
	   	 MasterDemandService.loadAllMasterRequests(pageNumber, pageSize)
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
   
   
    function openDemandRequest (demandRequestID) {
    	//console.log("inside openDemandRequest");
    	loadDemandRequestById(demandRequestID);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/ViewMasterDemand.jsp',
               size: 'lg',
               controllerAs: 'RequestController',
               scope: $scope

             });
    };
    
    function editDemandRequest (demandRequestID) {
    	//console.log("inside editDemandRequest  ::  "+demandRequestID);
    	//$window.location.href = 'editDemandRequest/'+demandRequestID;
    	loadDemandRequestById(demandRequestID);
    	$scope.$uibModalInstance = $uibModal.open({
               animation: true,
               templateUrl: 'static/pages/EditMasterDemand.jsp',
               size: 'lg',
               controllerAs: 'MasterDemandController',
               scope: $scope

             });
    };

    $scope.cancel = function () {
    	$scope.$uibModalInstance.close();
    };

    $scope.searchBySoId = function(soId) {
    	//console.log("soId  ::  "+soId);
    	
    	MasterDemandService.loadAllDemandReqBySoId(soId, 1, 10)
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
    
    $scope.editDemandRequestById = function(demadRequestID) {
    	//console.log('demadRequestID  ::  '+demadRequestID);
    	loadDemandRequestById(demadRequestID);
    };
    
    function loadDemandRequestById(demadRequestID) {
   	 //console.log('loadDemandRequestById  ::  ',demadRequestID);
   	MasterDemandService.loadDemandRequestById(demadRequestID)
   	 .then(
   	       function(d) {
   	            	$scope.demandRequest = d;
   	            	self.demandRequest = d;
   	            	if($scope.demandRequest.reqStartDate != null) {
   	            		$scope.demandRequest.reqStartDate = new Date($scope.demandRequest.reqStartDate);
   	            	}
   	            	if($scope.demandRequest.creationDate != null) {
   	            		$scope.demandRequest.creationDate = new Date($scope.demandRequest.creationDate);
   	            	}
   	            	if($scope.demandRequest.flaggedForHireDate != null) {
   	            		$scope.demandRequest.flaggedForHireDate = new Date($scope.demandRequest.flaggedForHireDate);
   	            	}
   	            	
   	                //console.log("self.demandRequest  ::  ",self.demandRequest);
   	        },

           function(errResponse) {
               console.error('Error while fetching demandRequest');
           }
   	 );

   }

    function createDemandRequest(demandRequest) {
    	//console.log('inside createDemandRequest');
    	MasterDemandService.createDemandRequest(demandRequest)
            .then(
            		loadAllDemandRequests,
            function(errResponse){
                console.error('Error while creating demandRequest');
            }
        );
    }

    function updateDemandRequest(demandRequest) {
    	MasterDemandService.updateDemandRequest(demandRequest)
            .then(
            		loadAllDemandRequests,
            function(errResponse){
                console.error('Error while updating Job');
            }
        );
    }

    
    function submit(myForm) {
	    //console.log('self.demandRequest  ::  ', self.demandRequest);
    	createDemandRequest(self.demandRequest);
        reset();
        $window.location.href = '/ermapp/masterDemand';
    }
    
    function update(myForm) {
   	  	updateDemandRequest($scope.demandRequest);
   	  	reset();
        $window.location.href = '/ermapp/masterDemand';
    }
    
    function removeDemandRequest(demandRequestId){
        //console.log('inside removeDemandRequest requestId  ::  ', demandRequestId);

        MasterDemandService.deleteDemandRequest(demandRequestId)
	   	 .then(
   	            function(d) {
   	            	//console.log("Request deleted  ::  ");
   	            	//reset();
   	            	$window.location.href = 'masterDemand';
   	            },

	           function(errResponse){
	               console.error('Error while deleting Request');
	           }
	   	 );
        

    }

    function createNewMasterDemand() {
    	
    	 //console.log('inside createNewMasterDemand');
    	 $window.location.href = 'createNewMasterDemand';

    }

     function reset() {
    	 
        self.demandRequest={demandRequestID:'', soId:'', billability:'', reqStartDate:'', grade:'', pdp:'', vertical:'', accountName:'', offShore:'', city:'', country:'', projectID:'', projectName:'', creationDate:'', flaggedForHire:'', flaggedForHireDate:'', technicalSkills:'', requestorName:'', requestorID:'', competency:''}
        $window.location.href = '/ermapp/masterDemand';
        
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
	 
	$scope.showTextOrPlain  = function(role) {
		
		//console.log("showTextOrPlain role  ::  "+role);
		
		if(role == 'ROLE_DELIVERY_LEAD') {
			return true;
		}else{
			return false;
		}
	}
     

}]);
