'use strict';

angular.module('myApp').controller('DashboardController', ['$scope', '$window','DashboardService','$filter', function($scope, $window, DashboardService, $filter) {
	
	loadPieChartData();
	loadBarChartData();
	$scope.completedCount = 0;
	$scope.pendingCount = 0;
	$scope.failedCount = 0;
	$scope.reviewedCount = 0;
	
	function loadPieChartData(){
     	 console.log('loadPieChartData');
     	DashboardService.loadPieChartData()
     	 .then(
     	            function(d) {
     	            	$scope.pieChartData = d;
     	            	console.log("$scope.pieChartData  ::  ",$scope.pieChartData);
     	            	angular.forEach($scope.pieChartData, function(value, key) {
     	            		console.log("value.status  ::  ",value.appStatus);
     	            		if(value.appStatus === 'Completed') {
     	            			$scope.completedCount = value.statusCount;
     	            		} else if(value.appStatus === 'Pending') {
     	            			$scope.pendingCount = value.statusCount;
     	            		} else if(value.appStatus === 'Reviewed') {
     	            			$scope.reviewedCount = value.statusCount;
     	            		} else {
     	            			$scope.failedCount = value.statusCount;
     	            		} 
     	            	});
     	            },

             function(errResponse){
                 console.error('Error loadPieChartData');
             }
     	 );

     }
	
	function loadBarChartData(){
    	 console.log('loadBarChartData');
    	DashboardService.loadBarChartData()
    	 .then(
    	            function(d) {
    	            	$scope.barChartData = d;
    	            	console.log("$scope.barChartData  ::  ",$scope.barChartData);
    	            	$scope.historicalBarChartOptionsData = [
    	    	            {
    	    	                "key" : "Quantity" ,
    	    	                "bar": true,
    	    	                "values" : $scope.barChartData
    	    	        }];
    	            },

            function(errResponse){
                console.error('Error loadPieChartData');
            }
    	 );

    }
	
	//var colors = ["#dff0d8", "#f2dede", "#fcf8e3"];
	$scope.pieChartOptions = {
			chart: {
                type: 'pieChart',
                color: function(d){
                    return (d.chartColor)
                },
                height: 400,
                x: function(d){return d.appStatus;},
                y: function(d){return d.statusCount;},
                showLabels: true,
                duration: 500,
                labelThreshold: 0.01,
                labelSunbeamLayout: true,
                legend: {
                    margin: {
                        top: 5,
                        right: 35,
                        bottom: 5,
                        left: 0
                    }
                }
            }
        };
	
	  $scope.historicalBarChartOptions = {
	            chart: {
	                type: 'historicalBarChart',
	                height: 400,
	                margin : {
	                    top: 20,
	                    right: 20,
	                    bottom: 65,
	                    left: 50
	                },
	                x: function(d){return d[0];},
	                y: function(d){return d[1];},
	                showValues: true,
	                valueFormat: function(d){
	                    return d3.format('')(d);
	                },
	                duration: 100,
	                xAxis: {
	                    axisLabel: 'Updated Date',
	                    tickFormat: function(d) {
	                        return d3.time.format('%d-%m-%y')(new Date(d))
	                    },
	                    rotateLabels: 30,
	                    showMaxMin: true
	                },
	                yAxis: {
	                    axisLabel: 'App Count',
	                    axisLabelDistance: -10,
	                    tickFormat: function(d){
	                        return d3.format('')(d);
	                    }
	                },
	                tooltip: {
	                    keyFormatter: function(d) {
	                        return d3.time.format('%d-%m-%y')(new Date(d));
	                    }
	                },
	                zoom: {
	                    enabled: true,
	                    scaleExtent: [1, 10],
	                    useFixedDomain: false,
	                    useNiceScale: false,
	                    horizontalOff: false,
	                    verticalOff: true,
	                    unzoomEventType: 'dblclick.zoom'
	                },
	                callback: function(){
	                    d3.selectAll('rect.nv-bar')
	                    .style('fill', function(d, i) {
	                          return '#84e184';
	                        });
	                    }
	            }
	        };

}]);
