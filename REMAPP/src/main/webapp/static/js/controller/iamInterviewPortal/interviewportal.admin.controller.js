'use strict';

angular.module('myApp').controller('InterviewAdminController', ['$scope', '$window','InterviewAdminService', '$uibModal', '$http', function($scope, $window, InterviewAdminService, $uibModal, $http) {
    var self = this;
    
    self.adminPanelAll = {id:'', empId:'', name:'', account:'', location:'', designation:'', phone:'', technology:'', product:'', creationDate:'', insertedBy:''}
    self.adminPanelAllList = [];
    
    self.admin = { empId:'', name:'', account:'', location:'', designation:'', phone:'', technology:'', product:'', status:''};
    
    //self.panelArray = [];
    
  
    loadAllAdminPanelList(); // to be enable /disabled
  
    var file = $scope.myFile;

    self.submit = submit;  
    self.adminPanelUpload=adminPanelUpload;    
    self.enter = enter;
    self.enterPanelDetails = enterPanelDetails;
    self.addUpdatePanel = addUpdatePanel;
    self.deletePanel = deletePanel;
    self.editPanel = editPanel;
    self.reset = reset;
    self.updateSinglePanel = updateSinglePanel;
    self.panelExport = panelExport;

    
    $scope.CheckUncheckAll = function(){
    	
        angular.forEach($scope.pageableAdminPanelAll, function(item){
        	
        	if(item.valid){
        		item.selected = event.target.checked;
        	}          
           
        });
    }
    
    
    function editPanel(empId){
        //console.log('inside removeDemandRequest requestId  ::  ', demandRequestId);
    	$scope.panelUploadSuccess=false;
    	$scope.errorMessage= false;
    	
    	InterviewAdminService.editPanel(empId)
	   	 .then(
   	            function(d) {
   	            	//console.log("Request deleted  ::  ");
   	            	//reset();
   	            	//loadAllAdminPanelList();
   	            	$scope.admin = d;
   	            	self.admin = d;
   	            	$scope.empIdReadOnly=true;
   	            	$scope.errorMessage= false;
   	            },

	           function(errResponse){
	               console.error('Error while deleting Request');
	               $scope.errorMessage="Exception occurred, please contact administrator !!";
	           }
	   	 );
        
    	$scope.disableUpdateBtn = false;

    }
    
    
    function deletePanel(empId){
        //console.log('inside removeDemandRequest requestId  ::  ', demandRequestId);
    	$scope.panelUploadSuccess=false;
    	$scope.errorMessage= false;
    	
    	InterviewAdminService.deletePanel(empId)
	   	 .then(
   	            function(d) {
   	            	//console.log("Request deleted  ::  ");
   	            	//reset();
   	            	loadAllAdminPanelList();
   	            	$scope.errorMessage= false;
   	            },

	           function(errResponse){
	               console.error('Error while deleting Request');
	               $scope.errorMessage="Exception occurred, please contact administrator !!";
	           }
	   	 );
    	reset();       

    }
    
    
    function addUpdatePanel(){
    	
    	$scope.errorMessage= false;
		$scope.panelUploadSuccess=false;
    	
    	
    	if(self.admin.empId == null || self.admin.empId == '' || self.admin.empId == undefined){
    		
          	$scope.errorMessage="Please enter Emp Id.";

    	}else if(self.admin.name == null || self.admin.name == '' || self.admin.name == undefined){
    		
          	$scope.errorMessage="Please enter Employee name.";

    	}else if(self.admin.account == null || self.admin.account == '' || self.admin.account == undefined){
    		
          	$scope.errorMessage="Please enter Account";

    	}else if(self.admin.location == null || self.admin.location == '' || self.admin.location == undefined){
    		
          	$scope.errorMessage="Please enter Location";

    	}else if(self.admin.designation == null || self.admin.designation == '' || self.admin.designation == undefined){
    		
          	$scope.errorMessage="Please enter Designation";

    	}else if(self.admin.phone == null || self.admin.phone == '' || self.admin.phone == undefined){
    		
          	$scope.errorMessage="Please enter Phone";

    	}else if(self.admin.technology == null || self.admin.technology == '' || self.admin.technology == undefined){
    		
          	$scope.errorMessage="Please enter Technology";

    	}else if(self.admin.product == null || self.admin.product == '' || self.admin.product == undefined){

          	$scope.errorMessage="Please enter Product";

    	}else if(self.admin.status == null || self.admin.status == '' || self.admin.status == undefined){

          	$scope.errorMessage="Please select Status";

    	}else {
    		
          	$scope.errorMessage= false;

          	
	    	InterviewAdminService.createPanel(self.admin)
	         .then(function(d) {
	        	 reset();

	        	
	        	// 	$scope.allPanel = d;
				//	$scope.pageableAdminPanelAll = [];
				
	        	 loadAllAdminPanelList();
	        	 $scope.panelUploadSuccess=true;
	                 },
	         function(errResponse){
	           if(errResponse.status == 417){

	             $scope.errorMessage="Invalid employee or Employee is already mapped to another Technology/Product. Please contact administrator for more details !!";
	            
	           }else{
		             $scope.errorMessage="Exception occurred, please contact administrator !!";
	           }
	         }
	     );
   
    	}	//reset();
 } 

    function adminPanelUpload(){
    	//console.log('File Details-->',$scope.myFile);
    	$scope.panelUploadSuccess=false;    
    	$scope.errorMessage= false;
    	$scope.disableUploadBtn = true;
    	$scope.pageableAdminPanelAll = [];    	
    	
    	if($scope.myFile == undefined || $scope.myFile == null || $scope.myFile == ''){
    		 $scope.disableUploadBtn = false;
    		 $scope.errorMessage="No file chosen to upload the panel. Please choose a file !!";
    		
    	}
    	else{
    		
    		 var filename = $scope.myFile.name;
             var index = filename.lastIndexOf(".");
             var strsubstring = filename.substring(index, filename.length);
             if (strsubstring != '.xlsx' )
             {
            	 angular.element(document.querySelector('#fileSelector')).val(null);
                 $scope.myFile = null;
             	 $scope.disableUploadBtn = false;

                 $scope.errorMessage = 'Please upload correct File Name, File extension should be .xlsx. ';
             }else{
             
		             
				    	InterviewAdminService.adminPanelUpload($scope.myFile)
				            .then(function(d) {
					            	console.log("FILE UPLOADDDD.. ");
					            	$scope.errorMessage= false;
					            	$scope.disableUploadBtn = false;
				   	            	//reset();
				   	            	//$window.location.href = '/ermapp/iaminterviewportal'; // @
					            	
					            	 angular.element(document.querySelector('#fileSelector')).val(null);
					                 $scope.myFile = null;
				   	            	$scope.pageableAdminPanelAll = d;   // RnD -- here onwards
				   	            	angular.forEach($scope.pageableAdminPanelAll, function(value, key){
					            		console.log('value  ::  '+value);	            		           		
					            	});
				   	            },
				            function(errResponse){
				   	            	$scope.disableUploadBtn = false;
				   	            	console.error('Error while creating Request');
				                   $scope.errorMessage="Exception occurred, please contact administrator !!";
				                
				            }
		        );
             }
    	}
    }
    
    function reset(){
    	$scope.panelUploadSuccess=false;
    	$scope.errorMessage= false;

    	$scope.pageableAdminPanelAll = [];
    	
    	self.admin = { empId:'', name:'', account:'', location:'', designation:'', phone:'', technology:'', product:'', status:''};
  
    	$scope.empIdReadOnly=false;
    	$scope.disableUpdateBtn = true;
    	$scope.disableUploadBtn=false;
    }
    
    function updateSinglePanel(){
    	$scope.panelUploadSuccess=false;
    	$scope.errorMessage= false;

        	var eve = "update";
        	
        	if(self.admin.empId == null || self.admin.empId == '' || self.admin.empId == undefined){
        		
              	$scope.errorMessage="Please enter Emp Id.";

        	}else if(self.admin.name == null || self.admin.name == '' || self.admin.name == undefined){
        		
              	$scope.errorMessage="Please enter Employee name.";

        	}else if(self.admin.account == null || self.admin.account == '' || self.admin.account == undefined){
        		
              	$scope.errorMessage="Please enter Account";

        	}else if(self.admin.location == null || self.admin.location == '' || self.admin.location == undefined){
        		
              	$scope.errorMessage="Please enter Location";

        	}else if(self.admin.designation == null || self.admin.designation == '' || self.admin.designation == undefined){
        		
              	$scope.errorMessage="Please enter Designation";

        	}else if(self.admin.phone == null || self.admin.phone == '' || self.admin.phone == undefined){
        		
              	$scope.errorMessage="Please enter Phone";

        	}else if(self.admin.technology == null || self.admin.technology == '' || self.admin.technology == undefined){
        		
              	$scope.errorMessage="Please enter Technology";

        	}else if(self.admin.product == null || self.admin.product == '' || self.admin.product == undefined){

              	$scope.errorMessage="Please enter Product";

        	}else if(self.admin.status == null || self.admin.status == '' || self.admin.status == undefined){

              	$scope.errorMessage="Please select Status";

        	} else {
        		
        	$scope.errorMessage= false;
        	InterviewAdminService.updatePanel(self.admin, eve)
             .then(function(d) {
            	 console.log('panel updated...1234..>>> ');
            	 //reset();
            	 //$scope.admin = null;
            	 	$scope.allPanel = d;
    				$scope.pageableAdminPanelAll = [];
    				$scope.panelUploadSuccess=true;  				

                     },
             function(errResponse){
                 console.error('Error while creating panel');
                 $scope.errorMessage="Exception occurred, please contact administrator !!";
             }
         );
        	reset();
        }
     } 

    function submit() {       
           
            adminPanelUpload();
    }
    
    function enter() {       
        
    	enterPanelDetails();
    	
    }
    
    
 function enterPanelDetails() {
    	
	 	$scope.errorMessage= false;
		$scope.panelUploadSuccess=false;
		
		if($scope.pageableAdminPanelAll != undefined ) {
		
			var count = 0;
		
    	//$scope.panelUploadSuccess=false;	
    	//var panelArray = $scope.pageableAdminPanelAll;
			
			 for (var i = 0; i < $scope.pageableAdminPanelAll.length; i++) {
			 			console.log('  $scope$scope.pageableAdminPanelAll[i].selected '+  $scope.pageableAdminPanelAll[i].selected);	        	 
			             if ($scope.pageableAdminPanelAll[i].selected) {
			            	 count++;
			                 break;
			             }			             
			         };
			         
			         console.log('count  '+  count);	
			         
			if (count > 0){	         
				InterviewAdminService.updatePanelList($scope.pageableAdminPanelAll)
							.then(function(d) { 
								$scope.allPanel = d;
								$scope.pageableAdminPanelAll = [];
								$scope.errorMessage= false;
								$scope.panelUploadSuccess=true;
								$scope.disableUploadBtn=false;
							},
									function(errResponse) {
														console.error('Error while fetching Requests');
														$scope.errorMessage="Exception occurred, please contact administrator !!";
														$scope.panelUploadSuccess=false;
														$scope.disableUploadBtn=false;
									});
			}else {
				
				 console.error('Error while uploading panel from excel');
            	 $scope.panelUploadSuccess=false;
				 $scope.errorMessage="Please select an Employee to Upload !!";
			}
		}
    }

 
   
    
    function loadAllAdminPanelList() {
    	//$scope.panelUploadSuccess=false;
    	$scope.errorMessage= false;
		$scope.panelUploadSuccess=false;
    	$scope.disableUpdateBtn = true;
   	 //console.log('inside loadAllDemandRequests');
    	InterviewAdminService.loadAllAdminPanelList()
   	 .then(
	            function(d) {
	            	$scope.errorMessage= false;
	            	$scope.allPanel = d;
	            	angular.forEach($scope.allPanel, function(value, key){
	            		//console.log('value  ::  '+value);	            		           		
	            	});
	                //console.log('self.demandRequestList  ::  ',self.demandRequestList);
	            },

           function(errResponse){
               console.error('Error while fetching Requests');
               $scope.errorMessage="Exception occurred, please contact administrator !!";
           }
   	 );

   };
   
   function panelExport() {
	   $scope.panelUploadSuccess=false;
	   $scope.errorMessage= false;

  	 
  	   var s = '/ermapp/interview/admin/panelExport/';
         $http.get('/ermapp/interview/admin/panelExport/')
             .then(
             function (response) {
          	   console.log('response  ::  '+response);
          	   $window.open(s);
             },
             function(errResponse){
                 console.error('Error while panelExport ');
                 $scope.open('/static/pages/errorNoFileModal.html');
             });
     }
    

}]);
