(function () {
  'use strict';

  angular.module('esra').controller('RecommendationsCtrl', RecommendationsCtrl);

  /** @ngInject */
  function RecommendationsCtrl($scope, $http, es, $location, $window, $uibModal, fileUpload) {

	  $scope.hits = {};
	  $scope.isNoResult = false;
	  $scope.isFileAvailable = false;
	  $scope.errorCode = $location.search().error_code;
	  $scope.errorDescription = $location.search().error_description;
	  $scope.type = $location.search().type;
	  $scope.filename = "";

	  console.log("$scope.type  ::  "+$scope.type);
	  console.log("$scope.errorCode  ::  "+$scope.errorCode);

	  es.cluster.health(function (err, resp) {
	        if (err) {
	            $scope.data = err.message;
	            console.log("err.message  ::  "+err.message);
	            $scope.isNoResult = true;
	            console.log("$scope.isNoResult  ::  "+$scope.isNoResult);
	        } else {
	            $scope.data = resp;
	            elasticSearch();
	        }
	   });


	  function elasticSearch() {

		  if($scope.errorCode != "null") {
		  es.search({
			    type: $scope.type,
			    body: {
			    	"_source": ["error_code", "error_description", "product_recommended_solution", "product_recommended_rootcause","kb_recommended_solution","kb_recommended_rootcause","kb_rca_document","kb_recommended"],
			        "query": {
			        "bool": {
			          "must": [
			            {
				        "match" : {
				            "error_code" : {
				                "query" : $scope.errorCode,
				                "operator" : "and"
				            }
				        }
				        }
			          ]
			        }
			      }
			    }
			    }).then(function (response) {
			    	$scope.hits = response.hits.hits;
			    	console.log('inside if $scope.hits  ::  '+$scope.hits.length);
			    	if($scope.hits.length == 0) {
			    		$scope.isNoResult = true;
			    	}
			    });
		  } else {
			  es.search({
				    /*index: 'filebeat-iam-solution',*/
				    type: $scope.type,
				    body: {
				    	"_source": ["error_code", "error_description", "product_recommended_solution", "product_recommended_rootcause","kb_recommended_solution","kb_recommended_rootcause","kb_rca_document","kb_recommended"],
				        "query": {
				        "bool": {
						"must": [
						 {
						 "match" : {
						 "error_description" : {
							 "query" : $scope.errorDescription,
							 	"operator" : "and"
						 		}
						 	}
						 }
						 ],
						 "must_not": {
							 "exists": {
								 "field": "error_code"
						         }
						 	}
				        }
				      }
				    }
				    }).then(function (response) {
				    	$scope.hits = response.hits.hits;
				    	console.log('inside else $scope.hits  ::  '+$scope.hits.length);
				    	if($scope.hits.length == 0) {
				    		$scope.isNoResult = true;
				    	}
				    });
		  }
	  }



	  $scope.solution = {};

	  $scope.saveActualSolution = function(solutionForm, myfile) {
		  //checkFile($scope.filename);
		  console.log("$scope.isFileAvailable  ::  "+$scope.isFileAvailable);
		  if($scope.isFileAvailable == true) {
			  $scope.open('/static/pages/errorModal.html');
		  }
		  if(solutionForm.actualSolution.$valid && solutionForm.actualRootCause.$valid && $scope.filename != "" && $scope.isFileAvailable == false) {
			  console.log("$scope.hits.length  ::  "+$scope.hits.length);

			  for(var i=0;i<$scope.hits.length;i++) {
				  if($scope.hits[i]._source.kb_recommended == undefined) {
					  var _source = {
			        			 "error_code":$scope.hits[i]._source.error_code,
			        			 "error_description":$scope.hits[i]._source.error_description,
			        			 "product_recommended_solution":$scope.hits[i]._source.product_recommended_solution,
			        			 "product_recommended_rootcause":$scope.hits[i]._source.product_recommended_rootcause,
			        			 "kb_recommended":[]};
				  console.log("$scope.hits[i]._source.kb_recommended  ::  "+$scope.hits[i]._source.kb_recommended);
				  $scope.hits[i]._source = _source;
			  }

			  }
		  angular.forEach($scope.hits, function(value, key){
			  	 $scope.kb_recommended = [];
		         $scope.index = value._index;
		         $scope.type = value._type;
		         $scope.id = value._id;
		         $scope.source = {};

		         $scope.source = value._source;
		         if(value._source.kb_recommended != undefined) {
		        	 console.log("inside kb_recommended if");
		        	 $scope.kb_recommended = value._source.kb_recommended;
		         }

		   });

		  $scope.kb_recommended.push({"id": $scope.kb_recommended.length+1,"solution": $scope.solution.actualSolution,"root_cause": $scope.solution.actualRootCause,"rca_document": $scope.filename});
		  console.log("$scope.filename  ::  "+$scope.filename);
		  console.log('$scope.index  ::  '+$scope.index);

		  uploadFile();

		  es.update({
			  index: $scope.index,
			  type: $scope.type,
			  id: $scope.id,
			    body: {
			    		"doc": { "kb_recommended": $scope.kb_recommended }
			    }
			    }).then(function (response) {
			    	console.log('response  ::  '+response.result);
			    	if(response.result == "updated") {
			    		$scope.solution.actualSolution = "";
			    		$scope.solution.actualRootCause = "";
			    		$scope.filename = "";
			    		$scope.open('/static/pages/successModal.html');
			    	}
		   });
	  };
	  };

	  function uploadFile() {
	        var file = $scope.file;
	        console.dir(file);
	        var uploadUrl = "uploadFile";
	        fileUpload.uploadFileToUrl(file, uploadUrl);
	   };

	  $scope.onFileClick = function(){
		    $scope.filename = event.target.files[0].name;
	        $scope.file = event.target.files[0];
	        checkFile($scope.filename);
	    };

	    $scope.fnDownLoad = function (fileName) {
    	   console.log("fileName  ::  "+fileName);
    	   var s = 'downloadFile/'+ fileName;
           $http.get('downloadFile/'+fileName)
               .then(
               function (response) {
            	   console.log('response  ::  '+response);
            	   $window.open(s);
               },
               function(errResponse){
                   console.error('Error while fnDownLoad ');
                   $scope.open('/static/pages/errorNoFileModal.html');
               }
           );
       };

       function checkFile(fileName) {
    	   $scope.isFileAvailable = false;
    	   console.log("fileName  ::  "+fileName);
    	   es.search({
   		    type: $scope.type,
   		    body: {
   		    	"query": {
   		        "bool": {
   		          "must": [
   		            { "match": { "kb_recommended.rca_document":   {
   		            	"query" : fileName,
   		                "operator" : "and"
   		            	}
   		              }
   		            }
   		          ]
   		        }
   		      }
   		    }
   		    }).then(function (response) {
   		    	console.log('File Name total hits  ::  '+response.hits.total);
   		    	if(response.hits.total != 0) {
   		    		$scope.isFileAvailable = true;
   		    	}
   		    });
       }
       $scope.open = function (page, size) {
       $uibModal.open({
           animation: true,
           templateUrl: page,
           size: size,
           resolve: {
             items: function () {
               return $scope.items;
             }
           }
         });
  };
  }
})();

