var app = angular.module('homeApp', []);
app.controller('homeCTRL',  [ '$scope', '$http', '$window',function($scope, $http,$window) {
	
	$scope.Agents = [];
	$scope.Requests = [];
	 $scope.Years = ["2015-2016", "2016-2017","2017-2018", "2018-2019"];
	 _refreshAgentData();
 
	 _refreshFilingRequestData();
	
    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshAgentData() {
    	  $http({
              url : '/MyTaxReturn/v1/agent/agentdetails',
              method : "GET",
              	 headers: {
              	        "Content-Type": "application/json",
              	        "Accept": "application/json"
              	    }
          }).then(function mySuccess(response) {
          	
          	$scope.Agents=response.data;
          	
          
          }, function myError(response) {
            $scope.message = response.statusText;
          });
    }
    
    //----
    
    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshFilingRequestData() {
    	  $http({
              url : '/MyTaxReturn/v1/returnfilingrequest/requestdetails',
              method : "GET",
              	 headers: {
              	        "Content-Type": "application/json",
              	        "Accept": "application/json"
              	    }
          }).then(function mySuccess(response) {
          	
        	
          	$scope.Requests=response.data;
          	
          
          }, function myError(response) {
            $scope.message = response.statusText;
          });
    }
    
    //----
    
    $scope.GetDetails = function (index) {
        var reqID = $scope.Requests[index].requestID;
        $http({
            method : "DELETE",
            url : "/MyTaxReturn/v1/returnfilingrequest/request/" + reqID,
            data : angular.toJson(reqID),
           headers: {
              	        "Content-Type": "application/xml"
              	      
              	    }
        }).then(function mySuccess(response) {
          	
        	
          	//$scope.Requests=response.data;
        	_refreshFilingRequestData();
            $window.alert("RequestID: " +response.data);
            $window.location.reload();
          }, function myError(response) {
            $scope.message = response.statusText;
          });
        
      
    };
    
    //---------------------
 //----
    
    $scope.getReqestID = function (index) {
        var reqID = $scope.Requests[index].requestID;
        
        $scope.requestID=reqID;
       /* $http({
            method : "DELETE",
            url : "/MyTaxReturn/v1/returnfilingrequest/request/" + reqID,
            data : angular.toJson(reqID),
           headers: {
              	        "Content-Type": "application/xml"
              	      
              	    }
        }).then(function mySuccess(response) {
          	
        	
          	//$scope.Requests=response.data;
        	_refreshFilingRequestData();
            $window.alert("RequestID: " +response.data);
            $window.location.reload();
          }, function myError(response) {
            $scope.message = response.statusText;
          });*/
        
      
    };
    
    //---------------------
    
    
    
} ]);