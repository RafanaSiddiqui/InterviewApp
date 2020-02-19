'use strict';
angular.module('myApp').controller('UserController', ['$scope', '$window','UserDetailsService','$filter',function($scope, $window, UserDetailsService, $filter) {
    var self = this;
    
    self.userInfo ={userId:null,firstName:'',lastName:'',mailId:'',username:'',password:'',role:'',approverType:'', userRolesList:[]};
    
    self.userInfoList=[];
    getAllUsers();
    getUserRole();
    loadAllRoles();
    //loadAllUserRoles();
    
    self.submit = submit;
    self.reset = reset;
    self.remove = remove;
    self.edit = edit;
    self.resetPassword = resetPassword;
    
    self.userRolesList = [];
    $scope.roleList = [];
    
    function submit(myForm){
    	//alert('UserName '+self.userInfo.username+' Password '+self.userInfo.password);
    	//console.log('selectAppType on submit-->', self.userInfo.lastName);
    	//console.log('self.userRolesList  ::  ',self.userRolesList);
    	
    	//console.log("self.userInfo  ::  ",self.userInfo);
    	self.userInfo.userRolesList = [];
    	angular.forEach(self.userRolesList, function (value, key) {
    		//console.log("value  ::  ",value);
    		self.userInfo.userRolesList.push({roleId:'', roleName:value});
    	});
    	if(self.userInfo.userId===null) {
    		UserDetailsService.addUserDetails(self.userInfo);
    	}
    	else
    	{
    		UserDetailsService.updateUserDetails(self.userInfo);
    	}
    	reset();
    }
    
    $scope.loadUser = function (userId) {
    	loadUserDetails(userId);
    }
    
    function resetPassword(myForm)
    {
    	//console.log('Update Password');
    	
    	UserDetailsService.updatePassword(self.userInfo)
     	 .then(
     			 function(d) {
     				//console.log("Password updated ::  ",d);
	            	$window.location.href = 'findUser';
     			 },
     			 function(errResponse){
             console.error('Error while resetPassword');
         });
    }
    
    function loadUserDetails(userId)
    {
    	console.log('loadUserDetails for UserId: --> ',userId);
    	UserDetailsService.loadUserDetails(userId)
      	 .then(
      	            function(d) {
      	            	self.userInfo = d;
      	            	//self.userRolesList = self.userInfo.userRolesList;
      	            	//console.log("self.userRolesList  ::  ",self.userRolesList);
      	            	angular.forEach(self.userInfo.userRolesList, function (value, key) {
      	            		//console.log("value  ::  ",value);
      	            		self.userRolesList.push(value.roleName);
      	                	//console.log("self.userRolesList  ::  ",self.userRolesList);
      	            	});
      	            },

              function(errResponse){
                  console.error('Error while fetching loadUserDetails');
              }
      	 );
    }
    
    function reset(){
    	$scope.showme=false;
    	//console.log('$scope.showme->', $scope.showme);
    	 self.userInfo={firstName:'',lastName:'',mailId:'',username:'',password:'',role:'',approverType:''};
        $scope.myForm.$setPristine(); //reset Form
        $window.location.href = '/ermapp/findUser';
    }
    
    function edit(userId){
        //console.log('request ID ----->', userId);
        $window.location.href = 'EditUser/'+userId;
    }
    
function getAllUsers(){
	
	UserDetailsService.getAllUsers()
 	 .then(
 	            function(d) {
 	                self.userInfoList = d;
 	            },

         function(errResponse){
             console.error('Error while fetching getAllUsers');
         }
 	 );
	
}

function loadAllRoles(){
	//console.log("inside loadAllRoles");
	UserDetailsService.loadAllRoles()
 	 .then(
 	            function(d) {
 	                $scope.rolesList = d;
 	                //console.log("$scope.rolesList  ::  ",$scope.rolesList);
 	            },

         function(errResponse){
             console.error('Error while loadAllRoles');
         }
 	 );
	
}

function loadAllUserRoles(){
	console.log("inside loadAllRoles");
	UserDetailsService.loadAllUserRoles()
 	 .then(
 	            function(d) {
 	                $scope.rolesUserList = d;
 	                console.log("$scope.rolesUserList  ::  ",$scope.rolesUserList);
 	            },

         function(errResponse){
             console.error('Error while loadAllUserRoles');
         }
 	 );
	
}
    
function remove(userId){
	
	UserDetailsService.deleteUser(userId)
  	 .then(
  			function(d) {
	            	//console.log("User deleted  ::  ",d);
	            	$window.location.href = 'findUser';
	            },

          function(errResponse){
              console.error('Error while remove user');
          }
  	 );
	
}  

function getUserRole()
{
	UserDetailsService.getUserRole()
	.then( function(d) {
		//alert('$scope.userRole '+$scope.userRole+' $scope.username '+$scope.username);
		
		$scope.username = d.username;
		$scope.userRole = d.role;
		$scope.userRolesList = d.userRolesList;
		
		if($scope.userRolesList != null) {
			angular.forEach($scope.userRolesList, function(value, key) {
				$scope.roleList.push(value.roleName);
			});
		}
		
		//console.log("$scope.rolesList  ::  ",$scope.rolesList);
		
		$scope.fullName = d.firstName + ' ' + d.lastName;
		
		//console.log('userRole is  ::  ',d);
		//console.log('$scope.userRole '+$scope.userRole+' $scope.username '+$scope.username);
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

	$scope.showOrHideMenu = function(role) {
		//console.log("role  ::  "+role);
		if(role == "ROLE_TSC" || role == "ROLE_TAG") {
			return true;
		} 
	}

    
    
    
}]);